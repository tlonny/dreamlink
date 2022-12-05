package periwinkle.utility;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class VoxelCaster {

    private Vector3f position;
    private Vector3f direction;
    public Vector3i cursor;

    public void castRay(Vector3f start, Vector3f direction) {
        this.position = new Vector3f(start);
        this.direction = new Vector3f(direction).normalize();
        this.cursor = new Vector3i(
            (int)this.position.x,
            (int)this.position.y,
            (int)this.position.z
        );
    }

    public void advance() {

        var minDistance = Float.MAX_VALUE;
        var minFace = CubeFace.BOTTOM;

        for(var cubeFace : CubeFace.CUBE_FACES) {

            var normal = new Vector3f(cubeFace.normal);
            var normalComponent = normal.dot(1f, 1f, 1f);
            var dot = normal.dot(this.direction);

            if(dot <= 0)
                continue;

            var positionProjection = this.position.dot(normal) * normalComponent;
            var shiftedPositionProjection = positionProjection + normalComponent;
            var voxelPositionProjection = (float)(normalComponent > 0 ? Math.floor(shiftedPositionProjection) : Math.ceil(shiftedPositionProjection));

            var deltaProjection = Math.abs(voxelPositionProjection - positionProjection);
            var distanceAlongDirection = deltaProjection/dot;

            if(distanceAlongDirection < minDistance) {
                minFace = cubeFace;
                minDistance = distanceAlongDirection;
            }
        }

        var delta = new Vector3f(this.direction).mul(minDistance);
        this.position.add(delta);
        this.cursor.add(minFace.normal);
    }

}
