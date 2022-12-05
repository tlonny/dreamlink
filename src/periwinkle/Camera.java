package periwinkle;

import periwinkle.graphics.Display;
import periwinkle.utility.CubeFace;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

    public static Camera CAMERA = new Camera();

    private static float FOV = (float)Math.toRadians(70f);
    private static float NEAR_PLANE = 0.1f;
    private static float FAR_PLANE = 100000f;

    private static float PITCH_LIMIT = (float)Math.toRadians(85f);
    private static float MOUSE_SENSITIVITY = 1f/900;

    public Vector3f position = new Vector3f();
    public Vector3f rotation = new Vector3f();

    public void update() {
        this.rotation.x -= Display.DISPLAY.getDeltaMouseX() * MOUSE_SENSITIVITY;
        this.rotation.y -= Display.DISPLAY.getDeltaMouseY() * MOUSE_SENSITIVITY;
        this.rotation.y = Math.min(Math.max(this.rotation.y, - PITCH_LIMIT), PITCH_LIMIT);
        Camera.CAMERA.position.set(
            Player.PLAYER.position.x + Player.PLAYER.dimensions.x / 2,
            Player.PLAYER.position.y + Player.PLAYER.dimensions.y / 2 + 1.5,
            Player.PLAYER.position.z + Player.PLAYER.dimensions.z / 2
        );
    }

    public Vector3f getDirectionVector() {
        var normal = new Vector3f(CubeFace.BACK.normal);
        normal.rotateX(this.rotation.y);
        normal.rotateY(this.rotation.x);
        return normal;
    }

    public Matrix3f getBillboardMatrix() {
        return new Matrix3f()
            .identity()
            .rotateY(this.rotation.x);
    }

    public Matrix4f getViewTranslationMatrix() {
        var position = new Vector3f(this.position).mul(-1f);
        return new Matrix4f()
            .identity()
            .translate(position);
    }

    public Matrix4f getViewRotationMatrix() {
        return new Matrix4f()
            .identity()
            .rotateX(-this.rotation.y)
            .rotateY(-this.rotation.x);
    }

    public Matrix4f getProjectionMatrix() {
        var aspectRatio = (float) Display.WIDTH / Display.HEIGHT;
        return new Matrix4f()
            .identity()
            .perspective(FOV, aspectRatio, NEAR_PLANE, FAR_PLANE);
    }

}
