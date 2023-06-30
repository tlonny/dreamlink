package doors.ui.element;

import doors.utility.geometry.Vector2in;

public class MinDimensionsElement implements IUIElement {

    public AlignmentElement child;
    public Vector2in minDimensions;
    public boolean overflow;

    private Vector2in position;
    private Vector2in dimensions;

    public MinDimensionsElement(AlignmentElement child, Vector2in dimensions) {
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
    public void calculateDimensions() {
        this.child.calculateDimensions();
        this.dimensions.set(this.minDimensions);
        this.dimensions.max(this.child.getDimensions());
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.position.set(origin);
        var newOrigin = new Vector2in(origin);
        var childDims = this.child.getDimensions();

        if(child.horizontalAlignment == AlignmentElement.HorizontalAlignment.LEFT) {
            newOrigin.x = origin.x;
        } else if(child.horizontalAlignment == AlignmentElement.HorizontalAlignment.RIGHT) {
            newOrigin.x = origin.x + (this.dimensions.x - childDims.x);
        } else {
            newOrigin.x = origin.x + (this.dimensions.x - childDims.x) / 2;
        }

        if(child.verticalAlignment == AlignmentElement.VerticalAlignment.TOP) {
            newOrigin.y = origin.y;
        } else if(child.verticalAlignment == AlignmentElement.VerticalAlignment.BOTTOM) {
            newOrigin.y = origin.y + (this.dimensions.y - childDims.y);
        } else {
            newOrigin.y = origin.y + (this.dimensions.y - childDims.y) / 2;
        }

        this.child.calculatePosition(newOrigin);
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
