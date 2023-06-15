package doors.graphics.perspective;

import org.joml.Matrix4f;

import doors.graphics.Shader;

public abstract class Perspective {

    private Matrix4f workingMatrix = new Matrix4f();

    protected abstract void writeViewTranslationMatrix(Matrix4f matrix);

    protected abstract void writeViewRotationMatrix(Matrix4f matrix);

    protected abstract void writeViewProjectionMatrix(Matrix4f matrix);

    public void apply() {
        this.writeViewTranslationMatrix(this.workingMatrix);
        Shader.SHADER.setViewTranslationMatrix(this.workingMatrix);

        this.writeViewRotationMatrix(this.workingMatrix);
        Shader.SHADER.setViewRotationMatrix(this.workingMatrix);

        this.writeViewProjectionMatrix(this.workingMatrix);
        Shader.SHADER.setViewProjectionMatrix(this.workingMatrix);
    }

}
