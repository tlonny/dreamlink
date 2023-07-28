package doors.ui.component.table;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.IComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class TableRowsComponent<T extends IComponent> implements IComponent {

    private int cellHeight;
    public int startIndex;

    private Vector2in dimensions = new Vector2in();
    private Vector2in position = new Vector2in();
    private Vector2in originCursor = new Vector2in();
    private Vector2in spaceCursor = new Vector2in();
    private TableState<T> tableState;

    public TableRowsComponent(TableState<T> tableState, int cellHeight) {
        this.tableState = tableState;
        this.cellHeight = cellHeight;
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
        var maxWidth = 10;
        for(var row : this.tableState.rows) {
            row.calculateDimensions();
            maxWidth = Math.max(maxWidth, row.getDimensions().x);
        }

        this.dimensions.set(maxWidth, this.tableState.numVisibleRows * this.cellHeight);
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.spaceCursor.set(
            availableSpace.x,
            this.cellHeight
        );

        for(var row : this.tableState.rows) {
            row.adjustDimensions(this.spaceCursor);
        }
        this.dimensions.x = availableSpace.x;
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.position.set(origin);
        this.originCursor.set(this.position);

        // Capture the start index so that it doesn't update between the update
        // and the writeComponentToSpriteBatch calls. If this happens, we get
        // weird flickering at the top and bottom of the table.
        this.startIndex = this.tableState.startIndex;

        for(var ix = 0; ix < this.tableState.numVisibleRows; ix += 1) {
            if(ix + this.tableState.startIndex >= this.tableState.rows.size()) {
                break;
            }

            var row = this.tableState.rows.get(ix + this.startIndex);
            row.calculatePosition(this.originCursor);
            this.originCursor.y += this.cellHeight;
        }

    }

    @Override
    public void update(UIRoot root) {
        for(var ix = 0; ix < this.tableState.numVisibleRows; ix += 1) {
            if(ix + this.tableState.startIndex >= this.tableState.rows.size()) {
                break;
            }

            var row = this.tableState.rows.get(ix + this.startIndex);
            row.update(root);
        }
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        for(var ix = 0; ix < this.tableState.numVisibleRows; ix += 1) {
            if(ix + this.tableState.startIndex >= this.tableState.rows.size()) {
                break;
            }

            var row = this.tableState.rows.get(ix + this.startIndex);
            row.writeComponentToSpriteBatch(spriteBatch);
        }
    }
    
}
