package doors.core.graphics.camera;

import org.joml.Matrix4f;

public class FlatCamera extends Camera {

    public static FlatCamera FLAT_CAMERA = new FlatCamera();

    @Override
    protected void writeViewTranslationMatrix(Matrix4f matrix) {
        matrix.identity();
    }

    @Override
    protected void writeViewRotationMatrix(Matrix4f matrix) {
        matrix.identity();
    }

    @Override
    protected void writeViewProjectionMatrix(Matrix4f matrix) {
        matrix.identity();
    }
}
