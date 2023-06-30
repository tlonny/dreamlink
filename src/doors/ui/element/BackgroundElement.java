package doors.ui.element;

import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSample;
import doors.graphics.sprite.SpriteBatch;
import doors.utility.geometry.Vector3fl;

public class BackgroundElement extends WrapperElement {

    public TextureChannel textureChannel;
    public TextureSample textureSample;

    public BackgroundElement(IUIElement child, TextureChannel textureChannel, TextureSample textureSample) {
        this.child = child;
        this.textureChannel = textureChannel;
        this.textureSample = textureSample;
    }

    @Override
    public void writeElement() {
        SpriteBatch.SPRITE_BATCH.writeSprite(
            this.textureChannel,
            this.textureSample,
            this.getPosition(),
            this.getDimensions(),
            Vector3fl.ONE
        );
        super.writeElement();
    }


}
