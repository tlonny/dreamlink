package doors.ui.component.layout;

import java.util.ArrayList;
import java.util.Collection;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.IComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class VerticalSpanComponent implements IComponent {

    public int spacing;
    public HorizontalAlignment horizontalAlignment;

    public Collection<IComponent> components = new ArrayList<>();

    private Vector2in positionCursor = new Vector2in();
    private Vector2in dimensions = new Vector2in();

    public VerticalSpanComponent(int spacing) {
        this(HorizontalAlignment.CENTER, spacing);
    }

    public VerticalSpanComponent(HorizontalAlignment horizontalAlignment, int spacing) {
        this.horizontalAlignment = horizontalAlignment;
        this.spacing = spacing;
    }

    @Override
    public void calculateDimensions() {
        this.dimensions.set(0);
        for(var component : this.components) {
            component.calculateDimensions();
            var dimensions = component.getDimensions();
            this.dimensions.y += dimensions.y;
            this.dimensions.x = Math.max(this.dimensions.x, dimensions.x);
        }
        this.dimensions.y += this.spacing * Math.max(0, this.components.size() - 1);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.positionCursor.set(origin);
        for(var component : this.components) {
            component.update(this.positionCursor, root);
            this.positionCursor.y += component.getDimensions().y + this.spacing;
        }
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        for(var component : this.components) {
            component.writeComponentToSpriteBatch(spriteBatch);
        }
    }

}
