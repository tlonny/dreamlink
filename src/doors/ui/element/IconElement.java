package doors.ui.element;

import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSample;
import doors.graphics.sprite.SpriteBatch;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;

public class IconElement implements IUIElement {

    public TextureSample textureSample;
    public TextureChannel textureChannel;
    public Vector2in dimensions;
    private Vector2in position;

    public IconElement(TextureChannel textureChannel, TextureSample textureSample) {
        this(textureChannel, textureSample, textureSample.dimensions);
    }

    public IconElement(TextureChannel textureChannel, TextureSample textureSample, Vector2in dimensions) {
        this.textureChannel = textureChannel;
        this.textureSample = textureSample;
        this.dimensions = new Vector2in(dimensions);
        this.position = new Vector2in();
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public Vector2in getPosition() {
        return this.position;
    }

    @Override
    public void calculateDimensions() {
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.position.set(origin);
    }

    @Override
    public void update() {
    }

    @Override
    public void writeElement() {
        SpriteBatch.SPRITE_BATCH.writeSprite(
            this.textureChannel,
            this.textureSample,
            this.position,
            this.dimensions,
            Vector3fl.ONE
        );
    }
    
}
