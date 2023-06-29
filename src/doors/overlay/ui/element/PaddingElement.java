package doors.overlay.ui.element;

import doors.utility.geometry.Vector2in;

public class PaddingElement implements IUIElement {

    public IUIElement child;
    public int padding;

    private Vector2in position;
    private Vector2in dimensions;

    public PaddingElement(IUIElement child, int padding) {
        this.child = child;
        this.padding = padding;

        this.position = new Vector2in();
        this.dimensions = new Vector2in();
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
    public void setDimensions() {
        this.child.setDimensions();

        var extraDims = this.padding * 2;
        this.dimensions.set(this.child.getDimensions()).add(extraDims, extraDims);
    }

    @Override
    public void setPosition(Vector2in origin) {
        this.position.set(origin);
        var childOrigin = new Vector2in(origin).add(this.padding, this.padding);
        this.child.setPosition(childOrigin);
    }

    @Override
    public void update() {
        this.child.update();
    }

    @Override
    public void writeElement() {
        this.child.writeElement();
    }

}

