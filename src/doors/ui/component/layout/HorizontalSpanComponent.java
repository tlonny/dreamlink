package doors.ui.component.layout;

import java.util.ArrayList;
import java.util.Collection;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.IComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class HorizontalSpanComponent implements IComponent {

    public int spacing;
    public VerticalAlignment verticalAlignment;

    public Collection<IComponent> components = new ArrayList<>();

    private Vector2in positionCursor = new Vector2in();
    private Vector2in dimensions = new Vector2in();

    public HorizontalSpanComponent(int spacing) {
        this(VerticalAlignment.CENTER, spacing);
    }

    public HorizontalSpanComponent(VerticalAlignment verticalAlignment, int spacing) {
        this.verticalAlignment = verticalAlignment;
        this.spacing = spacing;
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        this.dimensions.set(0);
        for(var component : this.components) {
            component.calculateDimensions();
            var dimensions = component.getDimensions();
            this.dimensions.x += dimensions.x;
            this.dimensions.y = Math.max(this.dimensions.y, dimensions.y);
        }
        this.dimensions.x += this.spacing * Math.max(0, this.components.size() - 1);
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.positionCursor.set(origin);
        for(var component : this.components) {
            component.update(this.positionCursor, root);
            this.positionCursor.x += component.getDimensions().x + this.spacing;
        }
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        for(var component : this.components) {
            component.writeComponentToSpriteBatch(spriteBatch);
        }
    }
    
}
