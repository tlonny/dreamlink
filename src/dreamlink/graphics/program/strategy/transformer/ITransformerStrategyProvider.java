package dreamlink.graphics.program.strategy.transformer;

import org.joml.Matrix4f;

public interface ITransformerStrategyProvider {

    public void setTransformerMatrixUniform(int index, Matrix4f matrix);
    
}
