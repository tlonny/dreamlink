package doors.menu;

import doors.graphics.texture.FontDecoration;
import doors.graphics.texture.FontTextureAtlas;
import doors.utility.vector.Vector3fl;

public class TextLabelComponent extends BaseMenuComponent {

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
        this.dimensions.set(FontTextureAtlas.CHARACTER_DIMENSIONS);
        this.dimensions.x *= this.text.length();
    }

    @Override
    public void writeUIComponent() {
        FontTextureAtlas.FONT_TEXTURE_ATLAS.writeText(this.text, this.globalPosition, this.fontDecoration, this.color);
    }
}
