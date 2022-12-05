package dreamlink.graphics.program.strategy.camera;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3fc;

import dreamlink.window.Window;

public class CameraStrategy {

    private static final float nearPlane = 0.05f;
    private static final float farPlane = 10_000f;
    private static final float fieldOfView = (float)Math.toRadians(75f); 

    private final ICameraStrategyProvider provider;

    public CameraStrategy(ICameraStrategyProvider provider) {
        this.provider = provider;
    }

    private final Matrix4f setViewRotationMatrixMatrix = new Matrix4f();

    private void setViewRotationMatrix(Vector3fc rotation) {
        // If the CAMERA is facing in the positive Z (FRONT) direction, then we will be seeing all of the
        // BACK faces. Thus, we need to rotate our camera by 180 degrees.
        var matrix = this.setViewRotationMatrixMatrix
            .identity()
            .rotateY((float)Math.PI)
            .rotateX(-rotation.x())
            .rotateY(-rotation.y());
        this.provider.setViewRotationMatrixUniform(matrix);
    }

    private final Matrix4f setViewTranslationMatrixMatrix = new Matrix4f();

    private void setViewTranslationMatrix(Vector3fc position) {
        var matrix = this.setViewTranslationMatrixMatrix
            .identity()
            .translate(-position.x(), -position.y(), -position.z());
        this.provider.setViewTranslationMatrixUniform(matrix);
    }

    private final Vector2f setProjectionMatrixResolution = new Vector2f();
    private final Matrix4f setProjectionMatrixMatrix = new Matrix4f();

    private void setProjectionMatrix() {
        var resolution = Window.instance.getResolution(this.setProjectionMatrixResolution);
        var aspectRatio = resolution.x / resolution.y;

        var matrix = this.setProjectionMatrixMatrix
            .identity()
            .perspective(
                CameraStrategy.fieldOfView, 
                aspectRatio, 
                CameraStrategy.nearPlane, 
                CameraStrategy.farPlane
            );
        this.provider.setViewProjectionMatrixUniform(matrix);
    }

    public void setCamera(Vector3fc position, Vector3fc rotation) {
        this.setViewRotationMatrix(rotation);
        this.setViewTranslationMatrix(position);
        this.setProjectionMatrix();
    }
    
}
