package doors.perspective;

import org.joml.Matrix4f;

public interface IPerspective {

    public void writeViewProjectionMatrix(Matrix4f matrix);

    public void writeViewRotationMatrix(Matrix4f matrix);

    public void writeViewTranslationMatrix(Matrix4f matrix);

}
