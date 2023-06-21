package doors.perspective;

import org.joml.Matrix4f;

public class FlatPerspective extends Perspective {

    public static FlatPerspective FLAT_PERSPECTIVE = new FlatPerspective();

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
