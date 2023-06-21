package doors.perspective;

import org.joml.Matrix4f;
import doors.Camera;
import doors.Config;
import doors.utility.geometry.Vector3fl;

public class WorldPerspective extends Perspective {

    private static float FOV = (float)Math.toRadians(70f);
    private static float NEAR_PLANE = 0.01f;
    private static float FAR_PLANE = 1000f;

    public static WorldPerspective WORLD_PERSPECTIVE = new WorldPerspective();

    public Vector3fl position;
    public Vector3fl rotation;

    public WorldPerspective() {
        this.position = new Vector3fl();
        this.rotation = new Vector3fl();
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
}
