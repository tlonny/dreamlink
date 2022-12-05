package dreamlink.graphics.program.strategy.camera;

import org.joml.Matrix4f;

public interface ICameraStrategyProvider {

    public void setViewProjectionMatrixUniform(Matrix4f matrix);

    public void setViewRotationMatrixUniform(Matrix4f matrix);

    public void setViewTranslationMatrixUniform(Matrix4f matrix);
    
}
