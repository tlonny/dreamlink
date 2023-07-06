package doors.ui.element;

import doors.core.graphics.texture.TextureChannel;
import doors.core.graphics.texture.TextureSample;
import doors.core.ui.IUIElement;
import doors.core.ui.WrapperElement;
import doors.Doors;
import doors.core.utility.vector.Vector3fl;

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
        Doors.SPRITE_BATCH.writeSprite(
            this.textureChannel,
            this.textureSample,
            this.getPosition(),
            this.getDimensions(),
            Vector3fl.WHITE
        );
        super.writeElement();
    }


}
