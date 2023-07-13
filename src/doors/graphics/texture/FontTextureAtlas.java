package doors.graphics.texture;

import java.util.HashMap;
import java.util.Map;

import doors.graphics.mesh.SpriteBatch;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class FontTextureAtlas extends ImageTexture {

    private static Vector2in TEXTURE_DIMENSIONS = new Vector2in(256, 96);
    public static Vector2in CHARACTER_DIMENSIONS = new Vector2in(8, 16);

    public static FontTextureAtlas FONT_TEXTURE_ATLAS = new FontTextureAtlas();

    private Map<Character, FontCharacter> fontCharacterLookup = new HashMap<>();

    public FontTextureAtlas() {
        super(
            TextureChannel.TEXTURE_CHANNEL_FONT,
            TEXTURE_DIMENSIONS,
            "data/texture/font.png"
        );

        this.registerCharacter(' ', new Vector2in(0, 0), new Vector2in(16, 0));
        this.registerCharacter('!', new Vector2in(1, 0), new Vector2in(17, 0));
        this.registerCharacter('"', new Vector2in(2, 0), new Vector2in(18, 0));
        this.registerCharacter('#', new Vector2in(3, 0), new Vector2in(19, 0));
        this.registerCharacter('$', new Vector2in(4, 0), new Vector2in(20, 0));
        this.registerCharacter('%', new Vector2in(5, 0), new Vector2in(21, 0));
        this.registerCharacter('&', new Vector2in(6, 0), new Vector2in(22, 0));
        this.registerCharacter('\'', new Vector2in(7, 0), new Vector2in(23, 0));
        this.registerCharacter('(', new Vector2in(8, 0), new Vector2in(24, 0));
        this.registerCharacter(')', new Vector2in(9, 0), new Vector2in(25, 0));
        this.registerCharacter('*', new Vector2in(10, 0), new Vector2in(26, 0));
        this.registerCharacter('+', new Vector2in(11, 0), new Vector2in(27, 0));
        this.registerCharacter(',', new Vector2in(12, 0), new Vector2in(28, 0));
        this.registerCharacter('-', new Vector2in(13, 0), new Vector2in(29, 0));
        this.registerCharacter('.', new Vector2in(14, 0), new Vector2in(30, 0));
        this.registerCharacter('/', new Vector2in(15, 0), new Vector2in(31, 0));

        this.registerCharacter('0', new Vector2in(0, 1), new Vector2in(16, 1));
        this.registerCharacter('1', new Vector2in(1, 1), new Vector2in(17, 1));
        this.registerCharacter('2', new Vector2in(2, 1), new Vector2in(18, 1));
        this.registerCharacter('3', new Vector2in(3, 1), new Vector2in(19, 1));
        this.registerCharacter('4', new Vector2in(4, 1), new Vector2in(20, 1));
        this.registerCharacter('5', new Vector2in(5, 1), new Vector2in(21, 1));
        this.registerCharacter('6', new Vector2in(6, 1), new Vector2in(22, 1));
        this.registerCharacter('7', new Vector2in(7, 1), new Vector2in(23, 1));
        this.registerCharacter('8', new Vector2in(8, 1), new Vector2in(24, 1));
        this.registerCharacter('9', new Vector2in(9, 1), new Vector2in(25, 1));
        this.registerCharacter(':', new Vector2in(10, 1), new Vector2in(26, 1));
        this.registerCharacter(';', new Vector2in(11, 1), new Vector2in(27, 1));
        this.registerCharacter('<', new Vector2in(12, 1), new Vector2in(28, 1));
        this.registerCharacter('=', new Vector2in(13, 1), new Vector2in(29, 1));
        this.registerCharacter('>', new Vector2in(14, 1), new Vector2in(30, 1));
        this.registerCharacter('?', new Vector2in(15, 1), new Vector2in(31, 1));

        this.registerCharacter('@', new Vector2in(0, 2), new Vector2in(16, 2));
        this.registerCharacter('A', new Vector2in(1, 2), new Vector2in(17, 2));
        this.registerCharacter('B', new Vector2in(2, 2), new Vector2in(18, 2));
        this.registerCharacter('C', new Vector2in(3, 2), new Vector2in(19, 2));
        this.registerCharacter('D', new Vector2in(4, 2), new Vector2in(20, 2));
        this.registerCharacter('E', new Vector2in(5, 2), new Vector2in(21, 2));
        this.registerCharacter('F', new Vector2in(6, 2), new Vector2in(22, 2));
        this.registerCharacter('G', new Vector2in(7, 2), new Vector2in(23, 2));
        this.registerCharacter('H', new Vector2in(8, 2), new Vector2in(24, 2));
        this.registerCharacter('I', new Vector2in(9, 2), new Vector2in(25, 2));
        this.registerCharacter('J', new Vector2in(10, 2), new Vector2in(26, 2));
        this.registerCharacter('K', new Vector2in(11, 2), new Vector2in(27, 2));
        this.registerCharacter('L', new Vector2in(12, 2), new Vector2in(28, 2));
        this.registerCharacter('M', new Vector2in(13, 2), new Vector2in(29, 2));
        this.registerCharacter('N', new Vector2in(14, 2), new Vector2in(30, 2));
        this.registerCharacter('O', new Vector2in(15, 2), new Vector2in(31, 2));

        this.registerCharacter('P', new Vector2in(0, 3), new Vector2in(16, 3));
        this.registerCharacter('Q', new Vector2in(1, 3), new Vector2in(17, 3));
        this.registerCharacter('R', new Vector2in(2, 3), new Vector2in(18, 3));
        this.registerCharacter('S', new Vector2in(3, 3), new Vector2in(19, 3));
        this.registerCharacter('T', new Vector2in(4, 3), new Vector2in(20, 3));
        this.registerCharacter('U', new Vector2in(5, 3), new Vector2in(21, 3));
        this.registerCharacter('V', new Vector2in(6, 3), new Vector2in(22, 3));
        this.registerCharacter('W', new Vector2in(7, 3), new Vector2in(23, 3));
        this.registerCharacter('X', new Vector2in(8, 3), new Vector2in(24, 3));
        this.registerCharacter('Y', new Vector2in(9, 3), new Vector2in(25, 3));
        this.registerCharacter('Z', new Vector2in(10, 3), new Vector2in(26, 3));
        this.registerCharacter('[', new Vector2in(11, 3), new Vector2in(27, 3));
        this.registerCharacter('\\', new Vector2in(12, 3), new Vector2in(28, 3));
        this.registerCharacter(']', new Vector2in(13, 3), new Vector2in(29, 3));
        this.registerCharacter('^', new Vector2in(14, 3), new Vector2in(30, 3));
        this.registerCharacter('_', new Vector2in(15, 3), new Vector2in(31, 3));

        this.registerCharacter('`', new Vector2in(0, 4), new Vector2in(16, 4));
        this.registerCharacter('a', new Vector2in(1, 4), new Vector2in(17, 4));
        this.registerCharacter('b', new Vector2in(2, 4), new Vector2in(18, 4));
        this.registerCharacter('c', new Vector2in(3, 4), new Vector2in(19, 4));
        this.registerCharacter('d', new Vector2in(4, 4), new Vector2in(20, 4));
        this.registerCharacter('e', new Vector2in(5, 4), new Vector2in(21, 4));
        this.registerCharacter('f', new Vector2in(6, 4), new Vector2in(22, 4));
        this.registerCharacter('g', new Vector2in(7, 4), new Vector2in(23, 4));
        this.registerCharacter('h', new Vector2in(8, 4), new Vector2in(24, 4));
        this.registerCharacter('i', new Vector2in(9, 4), new Vector2in(25, 4));
        this.registerCharacter('j', new Vector2in(10, 4), new Vector2in(26, 4));
        this.registerCharacter('k', new Vector2in(11, 4), new Vector2in(27, 4));
        this.registerCharacter('l', new Vector2in(12, 4), new Vector2in(28, 4));
        this.registerCharacter('m', new Vector2in(13, 4), new Vector2in(29, 4));
        this.registerCharacter('n', new Vector2in(14, 4), new Vector2in(30, 4));
        this.registerCharacter('o', new Vector2in(15, 4), new Vector2in(31, 4));

        this.registerCharacter('p', new Vector2in(0, 5), new Vector2in(16, 5));
        this.registerCharacter('q', new Vector2in(1, 5), new Vector2in(17, 5));
        this.registerCharacter('r', new Vector2in(2, 5), new Vector2in(18, 5));
        this.registerCharacter('s', new Vector2in(3, 5), new Vector2in(19, 5));
        this.registerCharacter('t', new Vector2in(4, 5), new Vector2in(20, 5));
        this.registerCharacter('u', new Vector2in(5, 5), new Vector2in(21, 5));
        this.registerCharacter('v', new Vector2in(6, 5), new Vector2in(22, 5));
        this.registerCharacter('w', new Vector2in(7, 5), new Vector2in(23, 5));
        this.registerCharacter('x', new Vector2in(8, 5), new Vector2in(24, 5));
        this.registerCharacter('y', new Vector2in(9, 5), new Vector2in(25, 5));
        this.registerCharacter('z', new Vector2in(10, 5), new Vector2in(26, 5));
        this.registerCharacter('{', new Vector2in(11, 5), new Vector2in(27, 5));
        this.registerCharacter('|', new Vector2in(12, 5), new Vector2in(28, 5));
        this.registerCharacter('}', new Vector2in(13, 5), new Vector2in(29, 5));
        this.registerCharacter('~', new Vector2in(14, 5), new Vector2in(30, 5));
    }

    private void registerCharacter(
        char character, 
        Vector2in normalPosition,
        Vector2in underlinePosition
    ) {
        var fontCharacter = new FontCharacter(
            this.createTextureSample(normalPosition, Vector2in.ONE, CHARACTER_DIMENSIONS),
            this.createTextureSample(normalPosition, Vector2in.ONE, CHARACTER_DIMENSIONS),
            this.createTextureSample(normalPosition, Vector2in.ONE, CHARACTER_DIMENSIONS),
            this.createTextureSample(underlinePosition, Vector2in.ONE, CHARACTER_DIMENSIONS)
        );
        this.fontCharacterLookup.put(character, fontCharacter);
    }

    public FontCharacter getFontCharacter(char character) {
        return this.fontCharacterLookup.getOrDefault(character, null);
    }

    public void writeText(String text, Vector2in position, FontDecoration fontDecoration, Vector3fl color) {
        var cursor = new Vector2in(position);
        for(var ix = 0; ix < text.length(); ix += 1) {
            var character = text.charAt(ix);
            var fontCharacter = this.getFontCharacter(character);
            if (fontCharacter != null) {
                var textureSample = fontCharacter.getTextureSample(fontDecoration);
                SpriteBatch.SPRITE_BATCH.writeSprite(
                    textureSample,
                    cursor,
                    textureSample.dimensions,
                    color
                );
            }
            cursor.x += CHARACTER_DIMENSIONS.x;
        }
    }
}
