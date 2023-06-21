package doors.overlay.ui.element;

import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSample;
import doors.overlay.SpriteBatch;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;

public class BackgroundElement implements IUIElement {

    public IUIElement child;
    public TextureChannel textureChannel;
    public TextureSample textureSample;

    public BackgroundElement(IUIElement child, TextureChannel textureChannel, TextureSample textureSample) {
        this.child = child;
        this.textureChannel = textureChannel;
        this.textureSample = textureSample;
    }

    @Override
    public Vector2in getDimensions() {
        return this.child.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.child.getPosition();
    }

    @Override
    public void rebuild() {
        this.child.rebuild();
    }

    @Override
    public void orient(Vector2in origin) {
        this.child.orient(origin);
    }

    @Override
    public void update() {
        this.child.update();
    }

    @Override
    public void writeElement() {
        SpriteBatch.SPRITE_BATCH.writeSprite(
            this.textureChannel,
            this.textureSample,
            this.child.getPosition(),
            this.child.getDimensions(),
            Vector3fl.ONE
        );
        this.child.writeElement();
    }


}
