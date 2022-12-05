package dreamlink.graphics.program.strategy.model;

import org.joml.Matrix4f;
import org.joml.Vector3fc;

public class ModelStrategy {

    private final IModelStrategyProvider provider;

    public ModelStrategy(IModelStrategyProvider provider) {
        this.provider = provider;
    }

    private final Matrix4f setModelMatrix = new Matrix4f();

    public void setModel(Vector3fc position, float yaw) {
        var matrix = this.setModelMatrix
            .identity()
            .translate(position)
            .rotateY(yaw);

        this.provider.setModelMatrixUniform(matrix);
    }
    
}
