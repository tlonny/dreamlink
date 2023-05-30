package doors.spatial;

import org.joml.Vector3f;

import doors.terrain.Terrain;

public class SpatialComponent {

    public Vector3f velocity = new Vector3f();
    public Vector3f dimensions = new Vector3f();
    public Vector3f position = new Vector3f();
    public Vector3f previousPosition = new Vector3f();
    public Terrain terrain;

    public void bufferPosition() {
        this.previousPosition.set(this.position);
    }

    public Vector3f getInterpolatedPosition(float simFactor, Vector3f v) {
        return v.set(this.previousPosition).lerp(this.position, simFactor);
    }

    public Vector3f getInterpolatedPosition(float simFactor) {
        return this.getInterpolatedPosition(simFactor, new Vector3f());
    }

}
