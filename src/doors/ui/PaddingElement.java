package doors.ui;

import org.joml.Vector2i;

public class PaddingElement implements IElement {

    private int padding;
    private IElement element;
    private boolean isDirty;
    private Vector2i dimensions;

    public PaddingElement(IElement element, int padding) {
        this.element = element;
        this.padding = padding;

        this.dimensions = new Vector2i();
        this.isDirty = true;
    }

    public void setElement(IElement element) {
        this.element = element;
        this.isDirty = true;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        this.isDirty = true;
    }

    public void render(Vector2i position) {
        var positionCursor = new Vector2i(position).add(this.padding, this.padding);
        this.element.render(positionCursor);
    }

    @Override
    public Vector2i getDimensions() {
        if(this.isDirty) {
            this.dimensions.set(this.element.getDimensions());
            this.dimensions.x += this.padding * 2;
            this.dimensions.y += this.padding * 2;
            this.isDirty = false;
        }
        return this.dimensions;
    }

}
