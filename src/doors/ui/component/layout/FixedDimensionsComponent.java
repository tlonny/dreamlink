package doors.ui.component.layout;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.IComponent;
import doors.ui.component.IExplicitDimensions;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class FixedDimensionsComponent implements IExplicitDimensions {

    public IComponent content;

    private Vector2in dimensions = new Vector2in();
    private Vector2in position = new Vector2in();
    private Vector2in positionCursor = new Vector2in();
    public HorizontalAlignment horizontalAlignment;
    public VerticalAlignment verticalAlignment;

    public FixedDimensionsComponent(IComponent content, Vector2in dimensions, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.content = content;
        this.dimensions.set(dimensions);
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
    }

    public FixedDimensionsComponent(IComponent content, Vector2in dimensions) {
        this(content, dimensions, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        this.content.calculateDimensions();
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
        this.positionCursor.set(origin);

        var childDims = this.content.getDimensions();
        this.positionCursor.x += this.horizontalAlignment.getHorizontalOffset(this.dimensions.x, childDims.x);
        this.positionCursor.y += this.verticalAlignment.getVerticalOffset(this.dimensions.y, childDims.y);
        this.content.update(this.positionCursor, root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.content.writeComponentToSpriteBatch(spriteBatch);
    }

    @Override
    public void setDimensions(int width, int height) {
        this.dimensions.set(width, height);
    }
    
}
