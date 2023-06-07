package doors.perspective;

import org.joml.Matrix4f;

public class FlatPerspective implements IView {

    public static FlatPerspective FLAT_PERSPECTIVE = new FlatPerspective();

    @Override
     public void writeViewTranslationMatrix(Matrix4f matrix) {
        matrix.identity();
    }

    @Override
    public void writeViewRotationMatrix(Matrix4f matrix) {
        matrix.identity();
    }

    @Override
    public void writeViewProjectionMatrix(Matrix4f matrix) {
        matrix.identity();
    }
}
