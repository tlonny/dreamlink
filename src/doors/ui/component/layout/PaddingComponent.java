package doors.ui.component.layout;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.IComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class PaddingComponent implements IComponent {

    private int padding;
    private Vector2in dimensions = new Vector2in();
    private Vector2in originCursor = new Vector2in();

    public IComponent contentComponent;
    
    public PaddingComponent(IComponent content, int padding) {
        this.contentComponent = content;
        this.padding = padding;
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        this.contentComponent.calculateDimensions();
        this.dimensions.set(this.contentComponent.getDimensions());
        this.dimensions.add(this.padding * 2);
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.originCursor.set(origin).add(this.padding);
        this.contentComponent.update(this.originCursor, root);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        this.contentComponent.writeUIComponent(spriteBatch);
    }
    
}
