package doors.ui.component.table;

import java.util.ArrayList;
import java.util.List;

import doors.ui.component.IComponent;

public class TableState<T extends IComponent> {

    public List<T> rows = new ArrayList<>();
    public int numVisibleRows;
    public int startIndex;

    public TableState(int numVisibleRows) {
        this.numVisibleRows = numVisibleRows;
        this.startIndex = 0;
    }

    public void setScrollFactor(float factor) {
        this.startIndex = Math.round((this.rows.size() - this.numVisibleRows) * factor);
    }

    
}
