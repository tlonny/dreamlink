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

            // Now calculate the distance to the next voxel boundary we encounter...
            // Consider the following situation:
            // Position: 1.3, Cursor: 1, Next Voxel Boundaries: [1, 2]
            // Observe how when travelling in a positive direction, the position of the next voxel boundary
            // is <cursor> + 1, however in the negative direction, it is just <cursor>.

            //Apologies if future Tim doesn't understand the comment below - but its late and I'm tired...

            // N.B. its very important we use the cursor instead of trying to do it all with just the position
            // in the scenario where the position is an integer value, the min distance to the voxel boundary
            // will always be 0 and thus we'll continue going in the same direction forever. By using the cursor
            // we introduce a sort of "error term" that corrects for this. Indeed the first iteration will advance
            // as the distance is zero, but the second iteration will have a distance of 1. 

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
