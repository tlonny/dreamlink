package doors.ui;

import org.joml.Vector2i;
import org.joml.Vector3f;

import doors.graphics.TextureSample;
import doors.overlay.Overlay;

public class SpriteElement implements IElement {

    private TextureSample textureSample;
    private Vector2i dimensions;
    private Vector3f color;

    public SpriteElement(TextureSample textureSample, Vector2i dimensions, Vector3f color) {
        this.textureSample = textureSample;
        this.dimensions = dimensions;
        this.color = new Vector3f(color);
    }

    public void setColor(Vector3f color) {
        this.color.set(color);
    }

    @Override
    public Vector2i getDimensions() {
        return this.dimensions;
    }

    @Override
    public void render(Vector2i position) {
        Overlay.OVERLAY.pushSprite(position, this.dimensions, this.textureSample, this.color);
    }
}
