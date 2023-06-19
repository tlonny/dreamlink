package doors.ui;

import org.joml.Vector2i;
import org.joml.Vector3f;

import doors.overlay.Overlay;
import doors.overlay.SystemTextureAtlas;
import doors.utility.Color;

public class BorderElement implements IElement {

    private Vector3f color;
    private IElement element;

    public BorderElement(IElement element, Vector3f color) {
        this.element = element;
        this.color = new Vector3f(color);
    }

    public void setColor(Vector3f color) {
        this.color.set(color);
    }

    public void setElement(IElement element) {
        this.element = element;
    }

    public void render(Vector2i position) {
        var dimensionsCursor = new Vector2i(this.getDimensions());
        var positionCursor = new Vector2i(position);

        Overlay.OVERLAY.pushSprite(positionCursor, dimensionsCursor, SystemTextureAtlas.SOLID, Color.BLACK);
        Overlay.OVERLAY.pushSprite(positionCursor.add(1,1), dimensionsCursor.sub(2,2), SystemTextureAtlas.SOLID, Color.WHITE);
        Overlay.OVERLAY.pushSprite(positionCursor.add(1,1), dimensionsCursor.sub(2,2), SystemTextureAtlas.SOLID, this.color);

        this.element.render(position);
    }

    @Override
    public Vector2i getDimensions() {
        return this.element.getDimensions();
    }

}
