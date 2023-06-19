package doors.ui;

import java.util.Collection;
import org.joml.Vector2i;

public class VerticalSpanElement implements IElement {

    private Collection<IElement> children;

    private boolean isDirty;
    private Vector2i dimensions;
    private boolean center;

    public VerticalSpanElement(Collection<IElement> children, boolean center) {
        this.dimensions = new Vector2i();
        this.children = children;
        this.isDirty = true;
        this.center = center;
    }

    public void setCenter(boolean center) {
        this.center = center;
    }

    public void setChildren(Collection<IElement> children) {
        this.children = children;
        this.isDirty = true;
    }

    public void render(Vector2i position) {
        var positionCursor = new Vector2i(position);
        for (var child : this.children) {
            var childDims = child.getDimensions();
            positionCursor.x = this.center ? position.x + (this.dimensions.x - childDims.x) / 2 : position.x;
            child.render(positionCursor);
            positionCursor.y += childDims.y;
        }
    }

    @Override
    public Vector2i getDimensions() {
        if(this.isDirty) {
            var xCursor = 0;
            var yCursor = 0;
            for(var child : children) {
                var childDims = child.getDimensions();
                xCursor = Math.max(childDims.x, xCursor);
                yCursor += childDims.y;
            }
            this.dimensions.set(xCursor, yCursor);
            this.isDirty = false;
        }
        return this.dimensions;
    }
}
