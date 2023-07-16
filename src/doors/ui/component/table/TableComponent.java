package doors.ui.component.table;

import java.util.ArrayList;
import java.util.List;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.template.menu.FocusedDialogTemplate;
import doors.graphics.texture.MenuTexture;
import doors.io.Mouse;
import doors.ui.component.IComponent;
import doors.ui.component.IconComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class TableComponent<T extends IComponent> implements IComponent {

    private static int SCROLL_BAR_WIDTH = 16;
    private static Vector2in TOP_LEFT_PADDING = new Vector2in(4);
    private static Vector2in BOTTOM_RIGHT_PADDING = new Vector2in(3);

    private Vector2in cellDimensions;
    private int numRows;
    private int startIndex;

    private Vector2in dimensions = new Vector2in();
    private Vector2in position = new Vector2in();
    private Vector2in originCursor = new Vector2in();

    private IconComponent scrollBarComponent = new IconComponent(MenuTexture.MENU_TEXTURE.dither);
    private TableScrollBoxComponent scrollBoxComponent = new TableScrollBoxComponent();
    private int scrollAmount = 0;

    public List<T> rows = new ArrayList<>();

    public TableComponent(int numRows, Vector2in cellDimensions) {
        this.numRows = numRows;
        this.cellDimensions = new Vector2in(cellDimensions);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        var scrollBoxHeight = this.dimensions.y * this.numRows / this.rows.size();
        this.scrollBoxComponent.dimensions.set(SCROLL_BAR_WIDTH, scrollBoxHeight);
        this.scrollBoxComponent.calculateDimensions();

        for(var row : this.rows) {
            row.getDimensions().set(this.cellDimensions);
            row.calculateDimensions();
        }

        this.dimensions
            .set(this.cellDimensions)
            .mul(1, this.numRows)
            .add(TOP_LEFT_PADDING)
            .add(BOTTOM_RIGHT_PADDING);

        this.dimensions.x += SCROLL_BAR_WIDTH;

        this.scrollBarComponent.dimensions.set(SCROLL_BAR_WIDTH, this.dimensions.y);
        this.scrollBarComponent.dimensions.y -= TOP_LEFT_PADDING.y + BOTTOM_RIGHT_PADDING.y;
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
        this.originCursor.set(this.position).add(TOP_LEFT_PADDING);

        var scrollRange = this.scrollBarComponent.dimensions.y - this.scrollBoxComponent.getDimensions().y;
        var maxIndex = Math.max(0, this.rows.size() - this.numRows);
        var scrollFactor = (float)this.scrollAmount / scrollRange;
        this.startIndex = Math.round(maxIndex * scrollFactor);

        for(var ix = 0; ix < this.numRows; ix += 1) {
            if(ix + this.startIndex >= this.rows.size()) {
                break;
            }

            var row = this.rows.get(ix + this.startIndex);
            row.update(this.originCursor, root);
            this.originCursor.y += this.cellDimensions.y;
        }

        this.originCursor.set(origin).add(TOP_LEFT_PADDING);
        this.originCursor.x += this.cellDimensions.x;
        this.scrollBarComponent.update(this.originCursor, root);

        if(this.numRows >= this.rows.size()) {
            return;
        }

        if(root.draggedComponent == this.scrollBoxComponent) {
            this.scrollAmount += Mouse.MOUSE.position.y - this.scrollBoxComponent.mousePosition.y;
            this.scrollBoxComponent.mousePosition.set(Mouse.MOUSE.position);
            this.scrollAmount = Math.max(0, Math.min(this.scrollAmount, scrollRange));
        }

        this.originCursor.y += this.scrollAmount;
        this.scrollBoxComponent.update(this.originCursor, root);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        FocusedDialogTemplate.FOCUSED_DIALOG_TEMPLATE.writeMenuTemplate(
            spriteBatch,
            this.position,
            this.dimensions,
            SpriteBatchHeight.UI_NORMAL
        );

        for(var ix = 0; ix < this.numRows; ix += 1) {
            if(ix + this.startIndex >= this.rows.size()) {
                break;
            }
            this.rows.get(ix + this.startIndex).writeUIComponent(spriteBatch);
        }

        this.scrollBarComponent.writeUIComponent(spriteBatch);

        if(this.numRows >= this.rows.size()) {
            return;
        }

        this.scrollBoxComponent.writeUIComponent(spriteBatch);

    }
    
}
