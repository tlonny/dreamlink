package doors.utility;

import doors.utility.vector.Vector3fl;
import doors.utility.vector.Vector3in;

public class VoxelRayCaster {

    public Vector3fl position = new Vector3fl();
    private Vector3fl direction = new Vector3fl();
    public Vector3in previousRayCursor = new Vector3in();
    public Vector3in rayCursor = new Vector3in();

    private Vector3fl deltaPositionCursor = new Vector3fl();

    public void cast(Vector3fl start, Vector3fl direction) {
        this.position.set(start);
        this.direction.set(direction);
        this.previousRayCursor.set(
            (int)start.x,
            (int)start.y,
            (int)start.z
        );
        this.rayCursor.set(this.previousRayCursor);
    }

    // Advance in one of the 6 cardinal directions by one voxel
    // by finding which jump to the next voxel requires the smallest
    // distance along the ray.
    public void advance() {
        var minDistance = Float.MAX_VALUE; 
        var minFace = CubeFace.FRONT;

        for(var cubeFace : CubeFace.CUBE_FACES) {

            // Get the sign of the normal (+1) or (-1).
            var normalSignum = cubeFace.normal.getFloatSum();

            // Check to see if the ray is going in the same direction as the normal.
            // If its not, then the ray is going in the wrong direction and we
            // should skip this face.
            var projection = cubeFace.normal.getDot(this.direction);

            if(projection <= 0) {
                continue;
            }

            // We are now filtering out half the faces - and will be left with
            // one normal for the X axis, one for the Y and one for the Z.

            // Get the current position of the ray along the axis.
            var positionProjection = cubeFace.normal.getDot(this.position) * normalSignum;

            // Get the distance from the current position to the boundary of the next voxel.
            // We use the cursor to "quantize" our current position and tell us which voxel we're in.
            // You would think this could be inferred from the position with some rounding but
            // unfortunately there is a problem. Consider the scenario where we are in voxel (x,y,z) and
            // our position is (x,q,w). How far do we need to move until we reach the voxel (x-1, y, z)?
            // The answer is 0, but if we move 0, then the position won't change. Therefore, for certain
            // position values, the voxel we are in is ambigious and therefore needs to be tracked
            // separately using the cursor object.
            var shiftedPositionProjection = cubeFace.normal.getDot(this.rayCursor) * normalSignum;
            shiftedPositionProjection += normalSignum > 0 ? 1 : 0;
            var distanceToVoxelBoundary = Math.abs(shiftedPositionProjection - positionProjection);

            // Now work out how far we'd have to travel in the direction of the ray
            // to reach the boundary of the next voxel.
            var distanceAlongDirection = (distanceToVoxelBoundary + 0f) / projection;

            // We want to find the smallest distance, so that we don't accidentally
            // skip over a voxel.
            if(distanceAlongDirection < minDistance) {
                minDistance = distanceAlongDirection;
                minFace = cubeFace;
            }
        }

        this.deltaPositionCursor.set(this.direction).mul(minDistance);
        this.position.add(this.deltaPositionCursor);

        this.previousRayCursor.set(this.rayCursor);
        this.rayCursor.add(minFace.normal);
    }
    
}
