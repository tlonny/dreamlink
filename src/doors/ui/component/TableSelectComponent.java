package doors.ui.component;

import java.util.ArrayList;
import java.util.List;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.io.Mouse;
import doors.core.ui.AbstractUIComponent;
import doors.core.ui.AbstractUIRoot;
import doors.core.ui.UILayer;
import doors.utility.vector.Vector2in;

public class TableSelectComponent<T extends AbstractUIComponent & ICanHighlight> extends AbstractUIComponent {

    private static int PADDING = 3;

    private Vector2in dimensions;
    private Vector2in rowDimensions;
    private T selectedRow;
    private List<T> rows = new ArrayList<>();

    public TableSelectComponent(AbstractUIRoot root, Vector2in rowDimensions, int numRows) {
        super(root);
        this.rowDimensions = new Vector2in(rowDimensions);
        this.dimensions = new Vector2in(rowDimensions)
            .mul(1, numRows)
            .add(PADDING * 2);
    }

    public void addRow(T row) {
        this.rows.add(row);
    }

    public void update() {
        super.update();

        if(!this.isPressed) {
            return;
        }

        var relativeHeight = Mouse.MOUSE.position.y - this.position.y - PADDING;
        var selectedRowIndex = relativeHeight / this.rowDimensions.y;
        var selectedRow = this.rows.get(selectedRowIndex);
        var oldSelectedRow = this.selectedRow;
        this.selectedRow = selectedRow == this.selectedRow ? null : selectedRow;

        if(oldSelectedRow != null) {
            oldSelectedRow.setHighlight(false);
        }

        if(this.selectedRow != null) {
            this.selectedRow.setHighlight(true);
        }
    }

    @Override
    public UILayer getLayer() {
        return UILayer.NORMAL;
    }

    @Override
    public void writeUIComponent(MeshBuffer meshBuffer) {
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }
    
}
