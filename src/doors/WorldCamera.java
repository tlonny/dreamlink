package doors;

import doors.graphics.IView;
import doors.overlay.VirtualScreen;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class WorldCamera implements IView {

    private static float PITCH_LIMIT = (float)Math.toRadians(85f);
    private static float MOUSE_SENSITIVITY = 1f/900;

    private static float FOV = (float)Math.toRadians(70f);
    private static float NEAR_PLANE = 0.01f;
    private static float FAR_PLANE = 1000f;

    public Vector3f position = new Vector3f();
    public Vector3f rotation = new Vector3f();

    public Matrix4f viewTranslationMatrix = new Matrix4f();
    public Matrix4f viewRotationMatrix = new Matrix4f();
    public Matrix4f viewProjectionMatrix = new Matrix4f();

    public void update(float simFactor) {
        this.rotation.x -= (Game.MOUSE.position.x - Game.WINDOW.dimensions.x /2f) * MOUSE_SENSITIVITY;
        this.rotation.y -= (Game.MOUSE.position.y - Game.WINDOW.dimensions.y /2f) * MOUSE_SENSITIVITY;
        this.rotation.y = Math.min(Math.max(this.rotation.y, - PITCH_LIMIT), PITCH_LIMIT);

        this.position.set(
            Game.PLAYER.spatialComponent.dimensions.x * 0.5f,
            Game.PLAYER.spatialComponent.dimensions.y * 0.95f,
            Game.PLAYER.spatialComponent.dimensions.z * 0.5f
        ).add(Game.PLAYER.spatialComponent.getInterpolatedPosition(simFactor));
    }

    @Override
     public void writeViewTranslationMatrix(Matrix4f matrix) {
        matrix.identity();
        matrix.translate(new Vector3f(this.position).mul(-1f));
    }

    @Override
    public void writeViewRotationMatrix(Matrix4f matrix) {
        matrix.identity();
        matrix.rotateX(-this.rotation.y);
        matrix.rotateY(-this.rotation.x);
    }

    @Override
    public void writeViewProjectionMatrix(Matrix4f matrix) {
        var aspectRatio = (float) VirtualScreen.RESOLUTION.x / (float) VirtualScreen.RESOLUTION.y;
        matrix.identity();
        matrix.perspective(FOV, aspectRatio, NEAR_PLANE, FAR_PLANE);
    }
}
