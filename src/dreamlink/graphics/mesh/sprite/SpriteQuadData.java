package dreamlink.graphics.mesh.sprite;

import org.joml.Vector2f;
import org.joml.Vector4f;

import dreamlink.graphics.mesh.strategy.texture.ITextureQuad;
import dreamlink.graphics.texture.sample.TextureSample;

public class SpriteQuadData implements ITextureQuad {

    public final Vector2f position = new Vector2f();
    public final Vector2f dimensions = new Vector2f();
    public final Vector4f color = new Vector4f();
    private TextureSample textureSample;

    public SpriteQuadData set(
        Vector2f position,
        Vector2f dimensions,
        TextureSample textureSample,
        Vector4f color
    ) {
        this.position.set(position);
        this.dimensions.set(dimensions);
        this.textureSample = textureSample;
        this.color.set(color);
        return this;
    }

    @Override
    public TextureSample getTextureSample() {
        return this.textureSample;
    }

    @Override
    public int getWindingOffset() {
        return 0;
    }
    
}
