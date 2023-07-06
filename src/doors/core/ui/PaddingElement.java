package doors.core.ui;

import doors.core.utility.vector.Vector2in;

public class PaddingElement implements IUIElement {

    public IUIElement child;
    public int paddingTop;
    public int paddingBottom;
    public int paddingLeft;
    public int paddingRight;

    public Vector2in position;
    public Vector2in dimensions;

    public PaddingElement(IUIElement child, int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {
        this.child = child;
        this.paddingTop = paddingTop;
        this.paddingBottom = paddingBottom;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.position = new Vector2in();
        this.dimensions = new Vector2in();
    }

    public PaddingElement(IUIElement child, int padding) {
        this(child, padding, padding, padding, padding);
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
        this.child.determineDimensions();

        this.dimensions.set(this.child.getDimensions()).add(
            this.paddingLeft + this.paddingRight,
            this.paddingTop + this.paddingBottom
        );
    }

    @Override
    public void determinePosition(Vector2in origin) {
        this.position.set(origin);
        var childOrigin = new Vector2in(origin).add(this.paddingLeft, this.paddingTop);
        this.child.determinePosition(childOrigin);
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

