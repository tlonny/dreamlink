package doors.ui;

import java.util.Collection;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.io.Mouse;
import doors.core.ui.BaseUIComponent;
import doors.core.ui.UILayer;
import doors.core.utility.Functional.IAction0;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;

public class TableComponent<T extends BaseUIComponent> extends BaseUIComponent {

    private static Vector2in PADDING = new Vector2in(3, 3);

    public Collection<T> entries;
    public T selectedEntry; 
    private IAction0 onChange;

    private Vector2in highlightDimensions;

    public TableComponent(Collection<T> entries, Vector2in dimensions, IAction0 onChange) {
        super();
        this.entries = entries;
        this.dimensions = new Vector2in(dimensions);
        this.highlightDimensions = new Vector2in();
        this.onChange = onChange;
        this.selectedEntry = null;
        this.layer = UILayer.INTERACTIVE;

        for(var entry : this.entries) {
            this.addChild(entry);
        }
    }

    private T getSelectedEntry() {
        var relativeMousePosition = new Vector2in(Mouse.MOUSE.position).sub(this.globalPosition).sub(PADDING);
        for(var entry : this.entries) {
            relativeMousePosition.y -= entry.dimensions.y;
            if(relativeMousePosition.y <= 0) {
                return entry;
            }
        }
        return null;
    }

    public void onPress() {
        var selectedEntry = this.getSelectedEntry();

        if(selectedEntry == this.selectedEntry) {
            return;
        }

        this.selectedEntry = selectedEntry;
        this.onChange.invoke();

        if(this.selectedEntry != null) {
            this.highlightDimensions.set(
                this.dimensions.x - PADDING.x * 2,
                this.selectedEntry.dimensions.y
            );
        }
    }

    @Override
    public void calculateLayout() {
        super.calculateLayout();
        var offsetCursor = new Vector2in(PADDING);

        for(var entry : this.entries) {
            entry.offset.set(offsetCursor);
            offsetCursor.y += entry.dimensions.y;
        }
    }

    private BoxSchema getBoxSchema() {
        if(this.isFocused) {
            return BoxSchema.DIALOG_FOCUS;
        }
        return BoxSchema.DIALOG_BLUR;
    }

    @Override
    public void writeUIComponent(MeshBuffer meshBuffer) {
        this.getBoxSchema().writeBox(meshBuffer, this.globalPosition, this.dimensions);
        for(var entry : this.entries) {
            if(entry == this.selectedEntry) {
                meshBuffer.writeQuad(
                    UITextureAtlas.HIGHLIGHT,
                    entry.globalPosition,
                    this.highlightDimensions,
                    Vector3fl.WHITE
                );
            }
        }
        super.writeUIComponent(meshBuffer);
    }



}
