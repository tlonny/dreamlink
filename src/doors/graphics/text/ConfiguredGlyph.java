package doors.graphics.text;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class ConfiguredGlyph {

    public Glyph glyph;
    public Vector3fl color = new Vector3fl();
    public Vector3fl highlightColor = new Vector3fl();
    public FontDecoration fontDecoration = FontDecoration.NORMAL; 

    public void writeConfiguredGlyphToSpriteBatch(
        SpriteBatch spriteBatch,
        Vector2in position,
        SpriteBatchHeight height
    ) {
        this.glyph.writeGlyphToSpriteBatch(
            spriteBatch,
            position,
            height,
            this.fontDecoration,
            this.color,
            this.highlightColor
        );
    }
    
}
