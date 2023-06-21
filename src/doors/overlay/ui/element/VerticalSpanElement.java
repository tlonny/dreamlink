package doors.overlay.ui.element;

import java.util.Collection;

import doors.utility.geometry.Vector2in;

public class VerticalSpanElement implements IUIElement {

    public Collection<AlignmentElement> children;
    public int childPadding;

    private Vector2in position;
    private Vector2in dimensions;

    public VerticalSpanElement(Collection<AlignmentElement> children, int childPadding) {
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
    public void rebuild() {
        this.dimensions.set(0);
        for(var child : this.children) {
            child.rebuild();
            this.dimensions.x = Math.max(this.dimensions.x, child.getDimensions().x);
            this.dimensions.y += child.getDimensions().y;
        }
        this.dimensions.y += Math.max(0, this.children.size() - 1) * this.childPadding;
    }

    @Override
    public void orient(Vector2in origin) {
        this.position.set(origin);
        var originCursor = new Vector2in(origin);
        for(var child : this.children) {
            var childDimensions = child.getDimensions();

            if(child.horizontalAlignment == AlignmentElement.HorizontalAlignment.LEFT) {
                originCursor.x = origin.x;
            } else if(child.horizontalAlignment == AlignmentElement.HorizontalAlignment.RIGHT) {
                originCursor.x = origin.x + (this.dimensions.x - childDimensions.x);
            } else {
                originCursor.x = origin.x + (this.dimensions.x - childDimensions.x) / 2;
            }
            child.orient(originCursor);
            originCursor.y += childDimensions.y + this.childPadding;
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
