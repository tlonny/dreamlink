package doors.ui.component.table;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.template.menu.NormalButtonTemplate;
import doors.io.Mouse;
import doors.ui.component.IComponent;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class TableScrollBoxComponent implements IComponent {

    public Vector2in dimensions = new Vector2in();
    public Vector2in position = new Vector2in();
    public Vector2in mousePosition = new Vector2in();

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {

    }

    @Override
    public void onMousePress(UIRoot root) {
        root.draggedComponent = this;
        this.mousePosition.set(Mouse.MOUSE.position);
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);

        if(root.hoveredComponent == this) {
            root.selectedCursor = PointerCursor.POINTER_CURSOR;
        }

        root.captureMouse(this, this.position, this.dimensions, 0);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        NormalButtonTemplate.NORMAL_BUTTON_TEMPLATE.writeMenuTemplate(
            spriteBatch,
            this.position,
            this.dimensions,
            SpriteBatchHeight.UI_NORMAL
        );
    }
    
}
