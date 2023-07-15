package doors.core.graphics.font;

import java.util.HashMap;
import java.util.Map;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.AbstractTexture;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public abstract class AbstractFont {

    private Map<Character, FontCharacter> fontCharacterLookup = new HashMap<>();
    private AbstractTexture texture;
    public Vector2in characterDimensions;

    public AbstractFont(AbstractTexture texture, Vector2in characterDimensions) {
        this.texture = texture;
        this.characterDimensions = characterDimensions;
    }

    protected void registerCharacter(
        char character, 
        Vector2in normalPosition,
        Vector2in underlinePosition
    ) {
        var normalTextureSample = this.texture.createTextureSample(normalPosition, Vector2in.ONE, this.characterDimensions);
        var underlineTextureSample = this.texture.createTextureSample(underlinePosition, Vector2in.ONE, this.characterDimensions);
        var fontCharacter = new FontCharacter(
            normalTextureSample,
            normalTextureSample,
            normalTextureSample,
            underlineTextureSample
        );
        this.fontCharacterLookup.put(character, fontCharacter);
    }

    public FontCharacter getFontCharacter(char character) {
        return this.fontCharacterLookup.getOrDefault(character, null);
    }

    public void writeText(MeshBuffer meshBuffer, String text, Vector2in position, FontDecoration fontDecoration, Vector3fl color) {
        var cursor = new Vector2in(position);
        for(var ix = 0; ix < text.length(); ix += 1) {
            var character = text.charAt(ix);
            var fontCharacter = this.getFontCharacter(character);
            if (fontCharacter != null) {
                var textureSample = fontCharacter.getTextureSample(fontDecoration);
                meshBuffer.writeQuad(textureSample, cursor, this.characterDimensions, color);
            }
            cursor.x += this.characterDimensions.x;
        }
    }
}
