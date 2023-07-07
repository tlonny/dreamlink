package doors.core.graphics.sprite;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;

public class FontMeshBufferWriter {

    private SpriteMeshBufferWriter spriteWriter;

    public FontMeshBufferWriter(MeshBuffer meshBuffer) {
        this.spriteWriter = new SpriteMeshBufferWriter(meshBuffer);
    }

    public void writeText(Font font, String text, Vector2in position, FontDecoration fontDecoration, Vector3fl color) {
        var cursor = new Vector2in(position);
        for(var ix = 0; ix < text.length(); ix += 1) {
            var character = text.charAt(ix);
            var fontCharacter = font.getFontCharacter(character);
            if (fontCharacter != null) {
                var textureSample = fontCharacter.getTextureSample(fontDecoration);
                this.spriteWriter.writeSprite(
                    textureSample,
                    cursor,
                    textureSample.dimensions,
                    color
                );
            }
            cursor.x += font.getCharacterDimensions().x;
        }
    }

}
