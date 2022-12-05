package dreamlink.graphics.program.strategy.clip;

import org.joml.Vector3fc;
import org.joml.Vector4f;

public class ClipStrategy {

    private final IClipStrategyProvider provider;

    public ClipStrategy(IClipStrategyProvider provider) {
        this.provider = provider;
    }

    private final Vector4f setClipBuffer = new Vector4f();

    public void setClip(Vector3fc position, Vector3fc normal) {
        var clip = this.setClipBuffer.set(
            normal.x(),
            normal.y(),
            normal.z(),
            - position.dot(normal)
        );
        this.provider.setClipUniform(clip);
    }
    
}
