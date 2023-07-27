package doors.graphics.text;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.TextureSample;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class Glyph {

    public static Vector2in GLYPH_DIMENSIONS = new Vector2in(8, 16);

    private TextureSample normalTextureSample;
    private TextureSample underlineTextureSample;

    public Glyph(
        TextureSample normalTextureSample,
        TextureSample underlineTextureSample
    ) {
        this.normalTextureSample = normalTextureSample;
        this.underlineTextureSample = underlineTextureSample;
    }

    private TextureSample getTextureSample(FontDecoration fontDecoration) {
        if(fontDecoration == FontDecoration.UNDERLINE) {
            return this.underlineTextureSample;
        }

        return this.normalTextureSample;
    }

    public void writeGlyphToSpriteBatch(
        SpriteBatch spriteBatch, 
        Vector2in position, 
        SpriteBatchHeight height,
        FontDecoration fontDecoration, 
        Vector3fl color
    ) {
        spriteBatch.pushSprite(
            this.getTextureSample(fontDecoration),
            position,
            GLYPH_DIMENSIONS,
            height,
            color
        );
    }

}
