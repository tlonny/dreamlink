package doors;

import doors.utility.CubeFace;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

    private static float FOV = (float)Math.toRadians(70f);
    private static float NEAR_PLANE = 0.1f;
    private static float FAR_PLANE = 100000f;

    private static float PITCH_LIMIT = (float)Math.toRadians(85f);
    private static float MOUSE_SENSITIVITY = 1f/900;

    public Vector3f position = new Vector3f();
    public Vector3f rotation = new Vector3f();

    public Vector3f directionVector = new Vector3f();
    public Matrix3f billboardMatrix = new Matrix3f();
    public Matrix4f viewTranslationMatrix = new Matrix4f();
    public Matrix4f viewRotationMatrix = new Matrix4f();
    public Matrix4f projectionMatrix = new Matrix4f();

    public void prepare(float simFactor) {
        if(!Game.DEBUG_CONSOLE.active) {
            this.rotation.x -= (Game.MOUSE.position.x - Game.DISPLAY.dimensions.x /2f) * MOUSE_SENSITIVITY;
            this.rotation.y -= (Game.MOUSE.position.y - Game.DISPLAY.dimensions.y /2f) * MOUSE_SENSITIVITY;
            this.rotation.y = Math.min(Math.max(this.rotation.y, - PITCH_LIMIT), PITCH_LIMIT);
        }

        this.position.set(
            Game.PLAYER.spatialComponent.dimensions.x * 0.5f,
            Game.PLAYER.spatialComponent.dimensions.y * 0.95f,
            Game.PLAYER.spatialComponent.dimensions.z * 0.5f
        );

        var playerPosition = Game.PLAYER.spatialComponent.getInterpolatedPosition(simFactor);
        this.position.add(playerPosition);

        this.updateDirectionVector();
        this.updateBillboardMatrix();
        this.updateViewTranslationMatrix();
        this.updateViewRotationMatrix();
        this.updateProjectionMatrix();
    }
    
    private void updateDirectionVector() {
        this.directionVector.set(CubeFace.BACK.normal);
        this.directionVector.rotateX(this.rotation.y);
        this.directionVector.rotateY(this.rotation.x);
    }

    private void updateBillboardMatrix() {
        this.billboardMatrix.identity().rotateY(this.rotation.x);
    }

    private void updateViewTranslationMatrix() {
        var position = new Vector3f(this.position).mul(-1f);
        this.viewTranslationMatrix.identity();
        this.viewTranslationMatrix.translate(position);
    }

    private void updateViewRotationMatrix() {
        this.viewRotationMatrix.identity();
        this.viewRotationMatrix.rotateX(-this.rotation.y);
        this.viewRotationMatrix.rotateY(-this.rotation.x);
    }

    private void updateProjectionMatrix() {
        var aspectRatio = (float) Game.DISPLAY.dimensions.x / Game.DISPLAY.dimensions.y;
        this.projectionMatrix.identity();
        this.projectionMatrix.perspective(FOV, aspectRatio, NEAR_PLANE, FAR_PLANE);
    }


}
