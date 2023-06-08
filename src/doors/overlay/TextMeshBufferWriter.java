package doors.overlay;

import org.joml.Vector2i;
import org.joml.Vector3f;

import doors.graphics.MeshBuffer;
import doors.utility.Maths;

public class TextMeshBufferWriter {

    public String text;

    private SpriteMeshBufferWriter spriteMeshBuffer;

    public TextMeshBufferWriter(MeshBuffer meshBuffer) {
        this.spriteMeshBuffer = new SpriteMeshBufferWriter(meshBuffer);
    }

    public void pushText(Vector2i position, String text, Vector3f color, boolean isUnderlined) {
        var cursor = new Vector2i(position);
        for(var ix = 0; ix < text.length(); ix +=1 ) {
            var character = text.charAt(ix);
            var glyph = Glyph.GLYPH_CHARACTER_LOOKUP.get(character);

            this.spriteMeshBuffer.pushSprite(
                cursor,
                OverlayTextureAtlas.TILE_8_16,
                isUnderlined ? glyph.underlineTextureSample : glyph.textureSample,
                Maths.VEC3F_ONE
            );

            cursor.x += OverlayTextureAtlas.TILE_8_16.x;
        }
    }

}
