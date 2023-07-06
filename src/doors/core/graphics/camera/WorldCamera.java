package doors.core.graphics.camera;

import org.joml.Matrix4f;
import doors.Config;
import doors.core.utility.vector.Vector3fl;

public class WorldCamera extends Camera {

    private static float FOV = (float)Math.toRadians(70f);
    private static float NEAR_PLANE = 0.01f;
    private static float FAR_PLANE = 1000f;

    public static WorldCamera WORLD_CAMERA = new WorldCamera();

    public Vector3fl position;
    public Vector3fl rotation;

    public WorldCamera() {
        this.position = new Vector3fl();
        this.rotation = new Vector3fl();
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
        matrix.identity();
        matrix.perspective(FOV, Config.RESOLUTION.getAspectRatio(), NEAR_PLANE, FAR_PLANE);
    }
}
