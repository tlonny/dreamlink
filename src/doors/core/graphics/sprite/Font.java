package doors.core.graphics.sprite;

import java.util.HashMap;
import java.util.Map;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.Texture;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;

public class Font {

    private Map<Character, FontCharacter> fontCharacterLookup;
    public Vector2in characterDimensions;
    private Texture fontTexture;

    public Font(Texture fontTexture, Vector2in characterDimensions) {
        this.fontCharacterLookup = new HashMap<>();
        this.fontTexture = fontTexture;
        this.characterDimensions = characterDimensions;
    }

    protected void registerCharacter(
        char character, 
        Vector2in normalPosition,
        Vector2in underlinePosition
    ) {
        var fontCharacter = new FontCharacter(
            this.fontTexture.createTextureSample(normalPosition, Vector2in.ONE, this.characterDimensions),
            this.fontTexture.createTextureSample(normalPosition, Vector2in.ONE, this.characterDimensions),
            this.fontTexture.createTextureSample(normalPosition, Vector2in.ONE, this.characterDimensions),
            this.fontTexture.createTextureSample(underlinePosition, Vector2in.ONE, this.characterDimensions)
        );
        this.fontCharacterLookup.put(character, fontCharacter);
    }

    public Vector2in getCharacterDimensions() {
        return this.characterDimensions;
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
                meshBuffer.writeQuad(
                    textureSample,
                    cursor,
                    textureSample.dimensions,
                    color
                );
            }
            cursor.x += this.getCharacterDimensions().x;
        }
    }
}
