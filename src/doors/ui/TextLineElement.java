package doors.ui;

import org.joml.Vector2i;
import org.joml.Vector3f;

import doors.overlay.Overlay;
import doors.overlay.SystemTextureAtlas;

public class TextLineElement implements IElement {

    private boolean underlined;
    private Vector3f color;
    private String text;
    private boolean isDirty;
    private Vector2i dimensions;

    public TextLineElement(String text, boolean underlined, Vector3f color) {
        this.text = text;
        this.underlined = underlined;
        this.color = new Vector3f(color);

        this.dimensions = new Vector2i();
        this.isDirty = true;
    }

    public void setText(String text) {
        this.text = text;
        this.isDirty = true;
    }

    public void setUnderlined(boolean underlined) {
        this.underlined = underlined;
    }

    public void setColor(Vector3f color) {
        this.color.set(color);
    }

    public int getWidth() {
        return this.text.length() * SystemTextureAtlas.TILE_8_16.x;
    }

    @Override
    public void render(Vector2i position) {
        var cursor = new Vector2i(position);
        for(var ix = 0; ix < this.text.length(); ix +=1 ) {
            var character = this.text.charAt(ix);
            var glyph = Glyph.GLYPH_CHARACTER_LOOKUP.get(character);
            Overlay.OVERLAY.pushSprite(
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
        if(this.isDirty) {
            this.dimensions.set(SystemTextureAtlas.TILE_8_16);
            this.dimensions.x *= this.text.length();
            this.isDirty = false;
        }
        return this.dimensions;
    }
}
