package doors.graphics;

import org.joml.Matrix4f;

public interface IView {

    void writeViewProjectionMatrix(Matrix4f matrix);

    void writeViewRotationMatrix(Matrix4f matrix);

    void writeViewTranslationMatrix(Matrix4f matrix);

}
