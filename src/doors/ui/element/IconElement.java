package doors.ui.element;

import doors.core.graphics.texture.TextureChannel;
import doors.core.graphics.texture.TextureSample;
import doors.core.ui.IUIElement;
import doors.Doors;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.Vector2in;

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
    public void determineDimensions() {
    }

    @Override
    public void determinePosition(Vector2in origin) {
        this.position.set(origin);
    }

    @Override
    public void update() {
    }

    @Override
    public void writeElement() {
        Doors.SPRITE_BATCH.writeSprite(
            this.textureChannel,
            this.textureSample,
            this.position,
            this.dimensions,
            Vector3fl.WHITE
        );
    }
    
}
