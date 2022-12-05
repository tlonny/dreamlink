package periwinkle.overlay;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.ITextureLookup;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.util.HashMap;
import java.util.Map;

public class Glyph implements ITextureLookup {

    public static Map<Character,Glyph> GLYPH_CHARACTER_LOOKUP = new HashMap<>();
    private static Vector2i GLYPH_DIMENSIONS = new Vector2i(8, 18);

    public static Glyph SYM_SPACE = new Glyph(
        ' ', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(0,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_BANG = new Glyph(
        '!', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(1,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_DOUBLE_QUOTES = new Glyph(
        '"', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(2,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_HASH = new Glyph(
        '#', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(3,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_DOLLAR = new Glyph(
        '$', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(4,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_PERCENT = new Glyph(
        '%', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(5,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_AMPERSAND = new Glyph(
        '&', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(6,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_APOSTROPHE = new Glyph(
        '\'', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(7,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_LEFT_PARENS = new Glyph(
        '(', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(8,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_RIGHT_PARENS = new Glyph(
        ')', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(9,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_ASTERISK = new Glyph(
        '*', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(10,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_PLUS = new Glyph(
        '+', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(11,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_COMMA = new Glyph(
        ',', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(12,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_MINUS = new Glyph(
        '-', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(13,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_DOT = new Glyph(
        '.', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(14,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_SLASH = new Glyph(
        '/', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(15,0).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph NUM_0 = new Glyph(
        '0', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(0,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph NUM_1 = new Glyph(
        '1', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(1,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph NUM_2 = new Glyph(
        '2', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(2,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph NUM_3 = new Glyph(
        '3', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(3,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph NUM_4 = new Glyph(
        '4', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(4,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph NUM_5 = new Glyph(
        '5', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(5,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph NUM_6 = new Glyph(
        '6', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(6,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph NUM_7 = new Glyph(
        '7', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(7,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph NUM_8 = new Glyph(
        '8', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(8,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph NUM_9 = new Glyph(
        '9', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(9,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_COLON = new Glyph(
            ':', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(10,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_SEMICOLON = new Glyph(
            ';', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(11,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_LESS_THAN = new Glyph(
            '<', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(12,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_EQUAL = new Glyph(
            '=', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(13,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_GREATER_THAN = new Glyph(
            '>', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(14,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_QUESTION = new Glyph(
            '?', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(15,1).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_AT = new Glyph(
            '@', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(0,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_A = new Glyph(
        'A', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(1,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_B = new Glyph(
        'B', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(2,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_C = new Glyph(
        'C', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(3,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_D = new Glyph(
        'D', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(4,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_E = new Glyph(
        'E', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(5,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_F = new Glyph(
        'F', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(6,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_G = new Glyph(
        'G', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(7,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_H = new Glyph(
        'H', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(8,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_I = new Glyph(
        'I', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(9,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_J = new Glyph(
        'J', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(10,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_K = new Glyph(
        'K', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(11,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_L = new Glyph(
        'L', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(12,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_M = new Glyph(
        'M', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(13,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_N = new Glyph(
        'N', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(14,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_O = new Glyph(
        'O', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(15,2).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_P = new Glyph(
        'P', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(0,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_Q = new Glyph(
        'Q', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(1,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_R = new Glyph(
        'R', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(2,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_S = new Glyph(
        'S', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(3,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_T = new Glyph(
        'T', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(4,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_U = new Glyph(
        'U', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(5,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_V = new Glyph(
        'V', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(6,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_W = new Glyph(
        'W', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(7,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_X = new Glyph(
        'X', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(8,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_Y = new Glyph(
        'Y', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(9,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph UPPER_Z = new Glyph(
        'Z', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(10,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_LEFT_SQUARE_BRACKET = new Glyph(
        '[', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(11,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_BACKSLASH = new Glyph(
        '\\', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(12,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_RIGHT_SQUARE_BRACKET = new Glyph(
        ']', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(13,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_CARET = new Glyph(
            '^', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(14,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_UNDERSCORE = new Glyph(
        '_', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(15,3).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_BACKTICK = new Glyph(
        '`', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(0,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_A = new Glyph(
            'a', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(1,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_B = new Glyph(
            'b', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(2,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_C = new Glyph(
            'c', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(3,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_D = new Glyph(
            'd', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(4,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_E = new Glyph(
            'e', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(5,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_F = new Glyph(
            'f', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(6,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_G = new Glyph(
            'g', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(7,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_H = new Glyph(
            'h', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(8,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_I = new Glyph(
            'i', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(9,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_J = new Glyph(
            'j', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(10,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_K = new Glyph(
            'k', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(11,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_L = new Glyph(
            'l', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(12,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_M = new Glyph(
            'm', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(13,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_N = new Glyph(
            'n', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(14,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_O = new Glyph(
            'o', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(15,4).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_P = new Glyph(
            'p', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(0,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_Q = new Glyph(
            'q', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(1,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_R = new Glyph(
            'r', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(2,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_S = new Glyph(
            's', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(3,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_T = new Glyph(
            't', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(4,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_U = new Glyph(
            'u', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(5,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_V = new Glyph(
            'v', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(6,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_W = new Glyph(
            'w', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(7,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_X = new Glyph(
            'x', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(8,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_Y = new Glyph(
            'y', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(9,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph LOWER_Z = new Glyph(
            'z', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(10,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_LEFT_BRACE = new Glyph(
            '{', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(11,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_PIPE = new Glyph(
        '|', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(12,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_RIGHT_BRACE = new Glyph(
        '}', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(13,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph SYM_TILDE = new Glyph(
            '~', Atlas.GLYPH_ATLAS.buildTextureOffsets(new Vector2i(14,5).mul(GLYPH_DIMENSIONS), GLYPH_DIMENSIONS)
    );

    public static Glyph[] GLYPHS = new Glyph[] {
        SYM_SPACE,
        SYM_BANG,
        SYM_DOUBLE_QUOTES,
        SYM_HASH,
        SYM_DOLLAR,
        SYM_PERCENT,
        SYM_AMPERSAND,
        SYM_APOSTROPHE,
        SYM_LEFT_PARENS,
        SYM_RIGHT_PARENS,
        SYM_ASTERISK,
        SYM_PLUS,
        SYM_COMMA,
        SYM_MINUS,
        SYM_DOT,
        SYM_SLASH,
        NUM_0,
        NUM_1,
        NUM_2,
        NUM_3,
        NUM_4,
        NUM_5,
        NUM_6,
        NUM_7,
        NUM_8,
        NUM_9,
        SYM_COLON,
        SYM_SEMICOLON,
        SYM_LESS_THAN,
        SYM_EQUAL,
        SYM_GREATER_THAN,
        SYM_QUESTION,
        SYM_AT,
        UPPER_A,
        UPPER_B,
        UPPER_C,
        UPPER_D,
        UPPER_E,
        UPPER_F,
        UPPER_G,
        UPPER_H,
        UPPER_I,
        UPPER_J,
        UPPER_K,
        UPPER_L,
        UPPER_M,
        UPPER_N,
        UPPER_O,
        UPPER_P,
        UPPER_Q,
        UPPER_R,
        UPPER_S,
        UPPER_T,
        UPPER_U,
        UPPER_V,
        UPPER_W,
        UPPER_X,
        UPPER_Y,
        UPPER_Z,
        SYM_LEFT_SQUARE_BRACKET,
        SYM_BACKSLASH,
        SYM_RIGHT_SQUARE_BRACKET,
        SYM_CARET,
        SYM_UNDERSCORE,
        LOWER_A,
        LOWER_B,
        LOWER_C,
        LOWER_D,
        LOWER_E,
        LOWER_F,
        LOWER_G,
        LOWER_H,
        LOWER_I,
        LOWER_J,
        LOWER_K,
        LOWER_L,
        LOWER_M,
        LOWER_N,
        LOWER_O,
        LOWER_P,
        LOWER_Q,
        LOWER_R,
        LOWER_S,
        LOWER_T,
        LOWER_U,
        LOWER_V,
        LOWER_W,
        LOWER_X,
        LOWER_Y,
        LOWER_Z,
        SYM_LEFT_BRACE,
        SYM_PIPE,
        SYM_RIGHT_BRACE,
        SYM_TILDE
    };

    public static void init() {
        for(var glyph : GLYPHS)
            GLYPH_CHARACTER_LOOKUP.put(glyph.character, glyph);
    }

    private final Vector2f[] textureOffsets;
    public final char character;

    public Glyph(char character, Vector2f[] textureOffsets) {
        this.textureOffsets = textureOffsets;
        this.character = character;
    }

    @Override
    public Vector2f[] getTextureOffsets() {
        return this.textureOffsets;
    }
}
