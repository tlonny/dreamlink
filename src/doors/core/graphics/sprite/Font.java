package doors.core.graphics.sprite;

import java.util.HashMap;
import java.util.Map;

import doors.core.graphics.texture.TextureChannel;
import doors.core.graphics.texture.TextureSampler;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.Vector2in;

public class Font {

    private Map<Character, FontCharacter> fontCharacterLookup;
    private Vector2in characterDimensions;
    private TextureSampler textureSampler;

    public Font(Vector2in textureDimensions, Vector2in characterDimensions) {
        this.fontCharacterLookup = new HashMap<>();
        this.characterDimensions = characterDimensions;
        this.textureSampler = new TextureSampler(textureDimensions, characterDimensions);
    }

    protected void registerCharacter(
        char character, 
        Vector2in normalPosition,
        Vector2in underlinePosition
    ) {
        var fontCharacter = new FontCharacter(
            this.textureSampler.createTextureSample(normalPosition),
            this.textureSampler.createTextureSample(normalPosition),
            this.textureSampler.createTextureSample(normalPosition),
            this.textureSampler.createTextureSample(underlinePosition)
        );
        this.fontCharacterLookup.put(character, fontCharacter);
    }

    public Vector2in getCharacterDimensions() {
        return this.characterDimensions;
    }

    public FontCharacter getFontCharacter(char character) {
        return this.fontCharacterLookup.getOrDefault(character, null);
    }

    public void writeText(SpriteBatch spriteBatch, TextureChannel textureChannel, String text, Vector2in position, FontDecoration fontDecoration, Vector3fl color) {
        var cursor = new Vector2in(position);
        for(var ix = 0; ix < text.length(); ix += 1) {
            var character = text.charAt(ix);
            var fontCharacter = this.getFontCharacter(character);
            if (fontCharacter != null) {
                var textureSample = fontCharacter.getTextureSample(fontDecoration);
                spriteBatch.writeSprite(
                    textureChannel,
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
