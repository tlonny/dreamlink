package doors.ui;

import java.util.Collection;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.sprite.FontDecoration;
import doors.core.io.Mouse;
import doors.core.ui.BaseUIComponent;
import doors.core.ui.UILayer;
import doors.core.utility.Functional.IAction1;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;

public class TextTableComponent extends BaseUIComponent {

    // This class is a bit of a mess and needs to be tidied up
    // but god-damn its nice to have some velocity for once...

    private static Vector2in PADDING = new Vector2in(3, 3);

    private Collection<String> entries;
    private int maxCharacters;
    private TextLabelComponent[] rows;
    private int selectedIndex; 
    private IAction1<String> onSelect;

    private Vector2in highlightDimensions;

    public TextTableComponent(Collection<String> entries, int maxCharacters, int numRows, IAction1<String> onSelect) {
        super();
        this.entries = entries;
        this.rows = new TextLabelComponent[numRows];
        this.maxCharacters = maxCharacters;
        this.highlightDimensions = new Vector2in(StandardFont.CHARACTER_DIMENSIONS).mul(this.maxCharacters, 1);
        this.onSelect = onSelect;
        this.selectedIndex = -1;

        for(var i = 0; i < numRows; i++) {
            this.rows[i] = new TextLabelComponent(FontDecoration.NORMAL, Vector3fl.BLACK);
            this.addChild(this.rows[i]);
        }

        this.setRows();
        this.layer = UILayer.INTERACTIVE;
    }

    private void setRows() {
        // TODO: add scrolling and cursor offsets...
        var counter = 0;
        for(var entry : this.entries) {
            if(counter >= this.rows.length) {
                break;
            }
            this.rows[counter].text = entry;
            counter += 1;
        }
    }

    public void onPress() {
        var relativeMousePosition = new Vector2in(Mouse.MOUSE.position).sub(this.globalPosition).sub(PADDING);
        relativeMousePosition.y /= StandardFont.CHARACTER_DIMENSIONS.y;
        if(relativeMousePosition.y >= 0 && relativeMousePosition.y < this.rows.length) {
            var row = this.rows[relativeMousePosition.y];
            if(row.text.length() > 0) {
                this.selectedIndex = this.selectedIndex == relativeMousePosition.y ? -1 : relativeMousePosition.y;
            }
        }
        var selectedText = this.selectedIndex == -1 ? null : this.rows[this.selectedIndex].text;
        this.onSelect.invoke(selectedText);
    }

    @Override
    public void calculateLayout() {
        super.calculateLayout();
        for(var ix = 0; ix < this.rows.length; ix++) {
            var row = this.rows[ix];
            row.offset.set(PADDING).add(0, ix * StandardFont.CHARACTER_DIMENSIONS.y);
        }

        this.dimensions
            .set(StandardFont.CHARACTER_DIMENSIONS)
            .mul(this.maxCharacters, this.rows.length)
            .add(PADDING).add(PADDING);
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
        for(var ix = 0; ix < this.rows.length; ix++) {
            var row = this.rows[ix];
            if(ix == this.selectedIndex) {
                row.color.set(Vector3fl.WHITE);
                meshBuffer.writeQuad(
                    UITextureAtlas.HIGHLIGHT,
                    row.globalPosition,
                    this.highlightDimensions,
                    Vector3fl.WHITE
                );
            } else {
                row.color.set(Vector3fl.BLACK);
            }

        }
        super.writeUIComponent(meshBuffer);
    }



}
