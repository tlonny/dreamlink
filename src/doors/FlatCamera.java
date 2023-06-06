package doors;

import doors.graphics.IView;

import org.joml.Matrix4f;

public class FlatCamera implements IView {

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
