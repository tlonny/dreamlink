package doors.ui.component.layout;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.IComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class PaddingComponent implements IComponent {

    public int paddingTop;
    public int paddingBottom;
    public int paddingLeft;
    public int paddingRight;
    private Vector2in dimensions = new Vector2in();
    private Vector2in position = new Vector2in();

    private Vector2in spaceCursor = new Vector2in();
    private Vector2in originCursor = new Vector2in();

    public IComponent content;
    
    public PaddingComponent(IComponent content, int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {
        this.content = content;
        this.paddingTop = paddingTop;
        this.paddingBottom = paddingBottom;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
    }

    public PaddingComponent(IComponent content, int padding) {
        this(content, padding, padding, padding, padding);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public Vector2in getPosition() {
        return this.position;
    }

    private void calculateSingleDimensions() {
        this.dimensions.set(this.content.getDimensions());
        this.dimensions.add(
            this.paddingLeft + this.paddingRight,
            this.paddingTop + this.paddingBottom
        );
    }

    @Override
    public void calculateDimensions() {
        this.content.calculateDimensions();
        this.calculateSingleDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.spaceCursor.set(availableSpace);
        this.spaceCursor.sub(
            this.paddingLeft + this.paddingRight,
            this.paddingTop + this.paddingBottom
        );
        this.content.adjustDimensions(this.spaceCursor);
        this.calculateSingleDimensions();
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.position.set(origin);
        this.originCursor.set(origin).add(this.paddingLeft, this.paddingTop);
        this.content.calculatePosition(this.originCursor);
    }

    @Override
    public void update(UIRoot root) {
        this.content.update(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.content.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
