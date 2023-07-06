package doors.core.ui;

import java.util.Collection;

import doors.core.ui.alignment.HorizontalAlignmentWrapper;
import doors.core.utility.vector.Vector2in;

public class VerticalSpanElement implements IUIElement {

    public Collection<HorizontalAlignmentWrapper> children;
    public int childPadding;

    public Vector2in position;
    public Vector2in dimensions;

    public VerticalSpanElement(Collection<HorizontalAlignmentWrapper> children, int childPadding) {
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
    public void determineDimensions() {
        this.dimensions.set(0);
        for(var child : this.children) {
            child.element.determineDimensions();
            var childDimensions = child.element.getDimensions();
            this.dimensions.x = Math.max(this.dimensions.x, childDimensions.x);
            this.dimensions.y += childDimensions.y;
        }
        this.dimensions.y += Math.max(0, this.children.size() - 1) * this.childPadding;
    }

    @Override
    public void determinePosition(Vector2in origin) {
        this.position.set(origin);
        var originCursor = new Vector2in(origin);
        for(var child : this.children) {
            var childDimensions = child.element.getDimensions();
            originCursor.x = origin.x + child.getHorizontalOffset(this.dimensions.x);
            child.element.determinePosition(originCursor);
            originCursor.y += childDimensions.y + this.childPadding;
        }
    }

    @Override
    public void update() {
        for(var child : this.children) {
            child.element.update();
        }
    }

    @Override
    public void writeElement() {
        for(var child : this.children) {
            child.element.writeElement();
        }
    }
}
