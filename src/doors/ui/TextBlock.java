package doors.ui;

import org.joml.Vector2i;
import org.joml.Vector3f;

import doors.overlay.Overlay;
import doors.overlay.SystemTextureAtlas;
import doors.utility.Color;

public class TextBlock implements IComponent {

    public String text;
    public boolean underlined;
    private Vector2i dimensions;
    public Vector3f color;

    public TextBlock(String text) {
        this();
        this.setText(text);
    }

    public TextBlock() {
        this.text = "";
        this.dimensions = new Vector2i();
        this.color = new Vector3f(Color.TEXT);
    }

    public int getWidth() {
        return this.text.length() * SystemTextureAtlas.TILE_8_16.x;
    }

    public void setText(String text) {
        this.text = text;
        this.dimensions.set(SystemTextureAtlas.TILE_8_16);
        this.dimensions.x *= this.text.length();
    }

    @Override
    public void paint(Vector2i position) {
        var cursor = new Vector2i(position);
        for(var ix = 0; ix < this.text.length(); ix +=1 ) {
            var character = this.text.charAt(ix);
            var glyph = Glyph.GLYPH_CHARACTER_LOOKUP.get(character);
            Overlay.OVERLAY.paint(
                cursor,
                SystemTextureAtlas.TILE_8_16,
                this.underlined ? glyph.underlineTextureSample : glyph.textureSample,
                this.color
            );
            cursor.x += SystemTextureAtlas.TILE_8_16.x;
        }
    }

    @Override
    public Vector2i getDimensions() {
        return this.dimensions;
    }
}
