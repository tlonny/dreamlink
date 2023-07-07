package doors.core.graphics.sprite;

import java.util.HashMap;
import java.util.Map;

import doors.core.graphics.texture.Texture;
import doors.core.utility.vector.Vector2in;

public class Font {

    private Map<Character, FontCharacter> fontCharacterLookup;
    private Vector2in characterDimensions;
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
}
