package doors.perspective;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import doors.Camera;
import doors.Config;
import doors.spatial.IHasPosition;
import doors.spatial.IHasRotation;

public class WorldPerspective extends Perspective implements IHasRotation, IHasPosition {

    private static float FOV = (float)Math.toRadians(70f);
    private static float NEAR_PLANE = 0.01f;
    private static float FAR_PLANE = 1000f;

    public Vector3f position;
    public Vector3f rotation;

    public WorldPerspective() {
        this.position = new Vector3f();
        this.rotation = new Vector3f();
    }

    public void alignToCamera() {
        this.position.set(Camera.CAMERA.position);
        this.rotation.set(Camera.CAMERA.rotation);
    }

    @Override
    protected void writeViewTranslationMatrix(Matrix4f matrix) {
        matrix.identity().translate(
            -this.position.x,
            -this.position.y,
            -this.position.z
        );
    }

    @Override
    protected void writeViewRotationMatrix(Matrix4f matrix) {
        matrix.identity();
        matrix.rotateX(-this.rotation.x);
        matrix.rotateY(-this.rotation.y);
    }

    @Override
    protected void writeViewProjectionMatrix(Matrix4f matrix) {
        var aspectRatio = (float) Config.RESOLUTION.x / (float) Config.RESOLUTION.y;
        matrix.identity();
        matrix.perspective(FOV, aspectRatio, NEAR_PLANE, FAR_PLANE);
    }

    @Override
    public Vector3f getPosition() {
        return this.position;
    }

    @Override
    public Vector3f getRotation() {
        return this.rotation;
    }
}
