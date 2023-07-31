package doors.ui.component.layout.box;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.IComponent;
import doors.ui.component.layout.alignment.HorizontalAlignment;
import doors.ui.component.layout.alignment.VerticalAlignment;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class BoxComponent implements IComponent {

    public HorizontalAlignment horizontalAlignment;
    public VerticalAlignment verticalAlignment;
    public IComponent child;

    private Vector2in position = new Vector2in();
    private Vector2in dimensions = new Vector2in();

    private Vector2in dimensionsCursor = new Vector2in();
    private Vector2in originCursor = new Vector2in();
    
    private IDimension width = new WrapDimension();
    private IDimension height = new WrapDimension();

    public BoxComponent(
        IComponent child, 
        IDimension width, 
        IDimension height, 
        HorizontalAlignment horizontalAlignment, 
        VerticalAlignment verticalAlignment
    ) {
        this.child = child;
        this.width = width;
        this.height = height;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
    }

    public BoxComponent(
        IComponent child, 
        IDimension width, 
        IDimension height
    ) {
        this(child, width, height, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
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
        if(this.child != null) {
            this.child.calculateDimensions();
            this.dimensionsCursor.set(this.child.getDimensions());
        } else {
            this.dimensionsCursor.set(Vector2in.ZERO);
        }

        this.dimensions.set(
            this.width.calculateDimension(this.dimensionsCursor.x, this.dimensionsCursor.x),
            this.height.calculateDimension(this.dimensionsCursor.y, this.dimensionsCursor.y)
        );
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.dimensionsCursor.set(
            this.width.getAvailableSpace(availableSpace.x),
            this.height.getAvailableSpace(availableSpace.y)
        );

        if(this.child != null) {
            this.child.adjustDimensions(this.dimensionsCursor);
            this.dimensionsCursor.set(this.child.getDimensions());
        } else {
            this.dimensionsCursor.set(Vector2in.ZERO);
        }

        this.dimensions.set(
            this.width.calculateDimension(this.dimensionsCursor.x, availableSpace.x),
            this.height.calculateDimension(this.dimensionsCursor.y, availableSpace.y)
        );
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
