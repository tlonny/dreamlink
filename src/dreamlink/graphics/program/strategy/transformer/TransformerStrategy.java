package dreamlink.graphics.program.strategy.transformer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class TransformerStrategy {

    private final ITransformerStrategyProvider provider;

    public TransformerStrategy(ITransformerStrategyProvider provider) {
        this.provider = provider;
    }

    private final Matrix4f setTransformerMatrixBuffer = new Matrix4f();
    private final Vector3f setTranslationReversionBuffer = new Vector3f();

    public void setTransformer(int index, Vector3fc position, float yaw) {
        var matrix = this.setTransformerMatrixBuffer.identity();
        var reversion = this.setTranslationReversionBuffer.set(position).mul(-1);

        matrix.translate(position);
        matrix.rotateY(yaw);
        matrix.translate(reversion);
        this.provider.setTransformerMatrixUniform(index, matrix);
    }
    
}
