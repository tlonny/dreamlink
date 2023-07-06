package doors.core.ui;

import java.util.Collection;

import doors.core.ui.alignment.VerticalAlignmentWrapper;
import doors.core.utility.vector.Vector2in;

public class HorizontalSpanElement implements IUIElement {

    public Collection<VerticalAlignmentWrapper> children;
    public int childPadding;

    public Vector2in position;
    public Vector2in dimensions;

    public HorizontalSpanElement(Collection<VerticalAlignmentWrapper> children, int childPadding) {
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
            this.dimensions.x += childDimensions.x;
            this.dimensions.y = Math.max(this.dimensions.y, childDimensions.y);
        }
        this.dimensions.x += Math.max(0, this.children.size() - 1) * this.childPadding;
    }

    @Override
    public void determinePosition(Vector2in origin) {
        this.position.set(origin);
        var originCursor = new Vector2in(origin);
        for(var child : this.children) {
            var childDimensions = child.element.getDimensions();
            originCursor.y = origin.y + child.getVerticalOffset(this.dimensions.y);
            child.element.determinePosition(originCursor);
            originCursor.x += childDimensions.x + this.childPadding;
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
