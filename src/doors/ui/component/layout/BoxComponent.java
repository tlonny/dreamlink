package doors.ui.component.layout;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.IComponent;
import doors.ui.component.layout.alignment.HorizontalAlignment;
import doors.ui.component.layout.alignment.VerticalAlignment;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class BoxComponent implements IComponent {

    private Vector2in minDimensions;
    private Vector2in maxDimensions;

    public HorizontalAlignment horizontalAlignment;
    public VerticalAlignment verticalAlignment;
    public IComponent child = null;

    private Vector2in position = new Vector2in();
    private Vector2in dimensions = new Vector2in();

    private Vector2in originCursor = new Vector2in();

    public BoxComponent(Vector2in minDimensions, Vector2in maxDimensions, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.minDimensions = new Vector2in(minDimensions);
        this.maxDimensions = new Vector2in(maxDimensions);
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
    }

    public BoxComponent(Vector2in minDimensions, Vector2in maxDimensions) {
        this(minDimensions, maxDimensions, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
    }

    public void setMaxDimensions(Vector2in maxDimensions) {
        this.maxDimensions.set(maxDimensions);
    }

    public void setMinDimensions(Vector2in minDimensions) {
        this.minDimensions.set(minDimensions);
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
        this.dimensions.set(this.minDimensions);
        var childDimensions = Vector2in.ZERO;
        if(this.child != null) {
            this.child.calculateDimensions();
            childDimensions = this.child.getDimensions();
        }
        this.dimensions.max(childDimensions);
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.dimensions.max(
            Math.min(availableSpace.x, this.maxDimensions.x),
            Math.min(availableSpace.y, this.maxDimensions.y)
        );
        if(this.child != null) {
            this.child.adjustDimensions(this.dimensions);
        }
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.position.set(origin);

        if(this.child != null) {
            var childDimensions = this.child.getDimensions();
            this.originCursor.set(origin);
            this.originCursor.x += this.horizontalAlignment.getHorizontalOffset(this.dimensions.x, childDimensions.x);
            this.originCursor.y += this.verticalAlignment.getVerticalOffset(this.dimensions.y, childDimensions.y);
            this.child.calculatePosition(this.originCursor);
        }
    }

    @Override
    public void update(UIRoot root) {
        if(this.child != null) {
            this.child.update(root);
        }
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        if(this.child != null) {
            this.child.writeComponentToSpriteBatch(spriteBatch);
        }
    }
    
}
