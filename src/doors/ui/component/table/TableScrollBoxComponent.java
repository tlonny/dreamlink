package doors.ui.component.table;

import doors.graphics.spritebatch.SpriteBatch;
import doors.io.Mouse;
import doors.ui.component.IComponent;
import doors.ui.component.border.ButtonBorderComponent;
import doors.ui.component.border.ButtonState;
import doors.ui.component.layout.box.BoxComponent;
import doors.ui.component.layout.box.GrowDimension;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class TableScrollBoxComponent implements IComponent {

    public Vector2in lastMousePosition = new Vector2in();
    private ButtonBorderComponent borderComponent;
    public boolean isDisabled;

    public TableScrollBoxComponent() {
        var boxComponent = new BoxComponent(
            null,
            new GrowDimension(),
            new GrowDimension()
        );

        this.borderComponent = new ButtonBorderComponent(boxComponent);
    }

    @Override
    public Vector2in getDimensions() {
        return this.borderComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.borderComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.borderComponent.calculateDimensions();
    }
    
    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.borderComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.borderComponent.calculatePosition(origin);
    }

    @Override
    public void onMousePress(UIRoot root) {
        if(this.isDisabled) {
            return;
        }

        root.draggedComponent = this;
        this.lastMousePosition.set(Mouse.MOUSE.position);
    }

    @Override
    public void update(UIRoot root) {
        if(root.hoveredComponent == this) {
            root.selectedCursor = PointerCursor.POINTER_CURSOR;
        }

        this.borderComponent.state = this.isDisabled ? ButtonState.DISABLED : ButtonState.NORMAL;
        root.captureMouse(this, this.getPosition(), this.getDimensions(), 0);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.borderComponent.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
