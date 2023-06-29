package doors.overlay.ui.element;

import java.util.Collection;

import doors.utility.geometry.Vector2in;

public class HorizontalSpanElement implements IUIElement {

    public Collection<AlignmentElement> children;
    public int childPadding;

    private Vector2in position;
    private Vector2in dimensions;

    public HorizontalSpanElement(Collection<AlignmentElement> children, int childPadding) {
        this.children = children;
        this.childPadding = childPadding;

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
        this.dimensions.set(0);
        for(var child : this.children) {
            child.setDimensions();
            this.dimensions.x += child.getDimensions().x;
            this.dimensions.y = Math.max(this.dimensions.y, child.getDimensions().y);
        }
        this.dimensions.x += Math.max(0, this.children.size() - 1) * this.childPadding;
    }

    @Override
    public void setPosition(Vector2in origin) {
        this.position.set(origin);
        var originCursor = new Vector2in(origin);
        for(var child : this.children) {
            var childDimensions = child.getDimensions();

            if(child.verticalAlignment == AlignmentElement.VerticalAlignment.TOP) {
                originCursor.y = origin.y;
            } else if(child.verticalAlignment == AlignmentElement.VerticalAlignment.BOTTOM) {
                originCursor.y = origin.y + (this.dimensions.y - childDimensions.y);
            } else {
                originCursor.y = origin.y + (this.dimensions.y - childDimensions.y) / 2;
            }
            child.setPosition(originCursor);
            originCursor.x += childDimensions.x + this.childPadding;
        }
    }

    @Override
    public void update() {
        for(var child : this.children) {
            child.update();
        }
    }

    @Override
    public void writeElement() {
        for(var child : this.children) {
            child.writeElement();
        }
    }
}
