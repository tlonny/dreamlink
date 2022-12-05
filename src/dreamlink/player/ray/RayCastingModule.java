package dreamlink.player.ray;

import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.player.IPlayerModuleProvider;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.utility.maths.Vector3iMaths;
import dreamlink.world.World;
import dreamlink.world.room.module.block.AirBlock;
import dreamlink.world.room.module.terrain.TerrainBlockData;

public class RayCastingModule {

    private static final int numIterations = 45;

    private final IPlayerModuleProvider provider;
    
    private CubeFace rayCubeFace = CubeFace.front;
    private final RayCastData rayCastData = new RayCastData();
    private final Vector3i blockPosition = new Vector3i();
    private final Vector3f rayPosition = new Vector3f();
    private final Vector3f rayDirection = new Vector3f();

    public RayCastingModule(IPlayerModuleProvider provider) {
        this.provider = provider;
    }

    public RayCastData getRayCastData(RayCastData target) {
        return target.set(this.rayCastData);
    }

    private void advanceRay() {
        var minDistance = Float.MAX_VALUE; 
        var minFace = CubeFace.front;

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);

            // Get the sign of the normal (+1) or (-1).
            var normalSignum = Vector3fMaths.dot(Vector3fMaths.one, cubeFace.normal);

            // Check to see if the ray is going in the same direction as the normal.
            // If its not, then the ray is going in the wrong direction and we
            // should skip this face.
            var projection = Vector3fMaths.dot(this.rayDirection, cubeFace.normal);
            if(projection <= 0) {
                continue;
            }

            // We are now filtering out half the faces - and will be left with
            // one normal for the X axis, one for the Y and one for the Z.

            // Get the current position of the ray along the axis.
            var positionProjection = Vector3fMaths.dot(this.rayPosition, cubeFace.normal) * normalSignum;

            // Get the distance from the current position to the boundary of the next voxel.
            // We use the cursor to "quantize" our current position and tell us which voxel we're in.
            // You would think this could be inferred from the position with some rounding but
            // unfortunately there is a problem. Consider the scenario where we are in voxel (x,y,z) and
            // our position is (x,q,w). How far do we need to move until we reach the voxel (x-1, y, z)?
            // The answer is 0, but if we move 0, then the position won't change. Therefore, for certain
            // position values, the voxel we are in is ambigious and therefore needs to be tracked
            // separately using the cursor object.
            var shiftedPositionProjection = Vector3iMaths.dot(cubeFace.normal, this.blockPosition) * normalSignum;
            shiftedPositionProjection += normalSignum > 0 ? 1 : 0;
            var distanceToVoxelBoundary = Math.abs(shiftedPositionProjection - positionProjection);

            // Now work out how far we'd have to travel in the direction of the ray
            // to reach the boundary of the next voxel.
            var distanceAlongDirection = distanceToVoxelBoundary / projection;

            // We want to find the smallest distance, so that we don't accidentally
            // skip over a voxel.
            if(distanceAlongDirection < minDistance) {
                minDistance = distanceAlongDirection;
                minFace = cubeFace;
            }
        }

        this.blockPosition.add(minFace.normal);
        this.rayCubeFace = minFace.getOpposite();
        this.rayPosition.add(
            this.rayDirection.x * minDistance,
            this.rayDirection.y * minDistance,
            this.rayDirection.z * minDistance
        );
    }

    private final TerrainBlockData updateBlockData = new TerrainBlockData();

    public void update() {
        var kinematicsStateModule = this.provider.getKinematicsStateModule();
        var room = World.instance.getRoom();

        kinematicsStateModule.getHeadPosition(this.rayPosition);
        Vector3fMaths.directionFromRotation(this.rayDirection, kinematicsStateModule.rotation);
        Vector3iMaths.castFrom(this.blockPosition, this.rayPosition);

        var isHit = false;
        int ix = 0;

        for(ix = 0; ix < RayCastingModule.numIterations; ix += 1) {
            var blockData = room.getBlockData(this.blockPosition, this.updateBlockData);

            if(blockData.blockID == AirBlock.blockID) {
                this.advanceRay();
            } else {
                isHit = true;
                break;
            }
        }

        this.rayCastData.set(
            this.rayCubeFace,
            this.blockPosition,
            this.rayPosition,
            this.updateBlockData,
            ix,
            isHit
        );
    }
    
}
