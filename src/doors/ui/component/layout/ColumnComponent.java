package doors.ui.component.layout;

import java.util.ArrayList;
import java.util.Collection;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.IComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class ColumnComponent implements IComponent {

    public int spacing;
    public Collection<IComponent> children = new ArrayList<>();

    private Vector2in dimensions = new Vector2in();
    private Vector2in position = new Vector2in();

    private Vector2in positionCursor = new Vector2in();
    private Vector2in remainingDimensionsCursor = new Vector2in();

    public ColumnComponent(int spacing) {
        this.spacing = spacing;
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
        this.dimensions.set(0);
        for(var child : this.children) {
            var dimensions = child.getDimensions();
            this.dimensions.x = Math.max(this.dimensions.x, dimensions.x);
            this.dimensions.y += dimensions.y;
        }
        this.dimensions.y += this.spacing * Math.max(0, this.children.size() - 1);
    }

    @Override
    public void calculateDimensions() {
        for(var child : this.children) {
            child.calculateDimensions();
        }
        this.calculateSingleDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.remainingDimensionsCursor.set(availableSpace);
        this.remainingDimensionsCursor.y -= this.dimensions.y;

        for(var child : this.children) {
            this.remainingDimensionsCursor.y += child.getDimensions().y;
            child.adjustDimensions(this.remainingDimensionsCursor);
            this.remainingDimensionsCursor.y -= child.getDimensions().y;
        }

        this.calculateSingleDimensions();

    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.position.set(origin);
        this.positionCursor.set(origin);
        for(var component : this.children) {
            component.calculatePosition(this.positionCursor);
            this.positionCursor.y += component.getDimensions().y + this.spacing;
        }
    }

    @Override
    public void update(UIRoot root) {
        for(var component : this.children) {
            component.update(root);
        }
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        for(var component : this.children) {
            component.writeComponentToSpriteBatch(spriteBatch);
        }
    }


}
