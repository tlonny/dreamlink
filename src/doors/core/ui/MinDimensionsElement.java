package doors.core.ui;

import doors.core.ui.alignment.BoxAlignmentWrapper;
import doors.core.utility.vector.Vector2in;

public class MinDimensionsElement implements IUIElement {

    public BoxAlignmentWrapper child;
    public Vector2in minDimensions;
    public Vector2in position;
    public Vector2in dimensions;

    public MinDimensionsElement(BoxAlignmentWrapper child, Vector2in dimensions) {
        this.child = child;
        this.minDimensions = new Vector2in(dimensions);

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
    public void determineDimensions() {
        this.child.element.determineDimensions();
        this.dimensions.set(this.minDimensions);
        this.dimensions.max(this.child.element.getDimensions());
    }

    @Override
    public void determinePosition(Vector2in origin) {
        this.position.set(origin);
        var childOrigin = this.child.getOffset(this.dimensions).add(origin);
        this.child.element.determinePosition(childOrigin);
    }

    @Override
    public void update() {
        this.child.element.update();
    }

    @Override
    public void writeElement() {
        this.child.element.writeElement();
    }

}
