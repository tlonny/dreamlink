package doors.overlay;

import org.joml.Vector2i;
import doors.graphics.MeshBuffer;

public class TextMeshBufferWriter {

    public TextMeshBufferWriter(MeshBuffer meshBuffer) {
        this.spriteMeshBuffer = new SpriteMeshBufferWriter(meshBuffer);
    }

    public String text;
    public Vector2i position = new Vector2i();
    public boolean isUnderlined;
    private SpriteMeshBufferWriter spriteMeshBuffer;

    public void pushText(Vector2i position, Vector2i characterDimensions, String text, boolean isUnderlined) {
        var cursor = new Vector2i(position);
        for(var ix = 0; ix < text.length(); ix +=1 ) {
            var character = text.charAt(ix);
            var glyph = Glyph.GLYPH_CHARACTER_LOOKUP.get(character);
            this.spriteMeshBuffer.pushSprite(
                cursor,
                characterDimensions,
                isUnderlined ? glyph.underlineTextureSample : glyph.textureSample
            );
            cursor.x += characterDimensions.x;
        }
    }

}
