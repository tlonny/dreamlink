package doors.ui;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.sprite.FontDecoration;
import doors.core.ui.BaseUIComponent;
import doors.core.utility.vector.Vector3fl;

public class TextLabelComponent extends BaseUIComponent {

    public String text;
    public Vector3fl color;
    public FontDecoration fontDecoration;

    public TextLabelComponent(String text, FontDecoration fontDecoration, Vector3fl color) {
        super();
        this.text = text;
        this.fontDecoration = fontDecoration;
        this.color = new Vector3fl(color);
    }

    public TextLabelComponent(FontDecoration fontDecoration, Vector3fl color) {
        this("", fontDecoration, color);
    }

    @Override
    public void calculateLayout() {
        this.dimensions.set(StandardFont.CHARACTER_DIMENSIONS);
        this.dimensions.x *= this.text.length();
    }

    @Override
    public void writeUIComponent(MeshBuffer meshBuffer) {
        StandardFont.STANDARD_FONT.writeText(meshBuffer, this.text, this.globalPosition, this.fontDecoration, this.color);
    }
}
