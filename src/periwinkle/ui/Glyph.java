package periwinkle.ui;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.Sprite;

import java.util.HashMap;
import java.util.Map;

public class Glyph {

    public static Map<Character,Glyph> GLYPH_CHARACTER_LOOKUP = new HashMap<>();

    public static Glyph SYM_SPACE = new Glyph(' ', "SYM_SPACE");

    public static Glyph SYM_BANG = new Glyph('!', "SYM_BANG");

    public static Glyph SYM_DOUBLE_QUOTES = new Glyph('"', "SYM_DOUBLE_QUOTES");

    public static Glyph SYM_HASH = new Glyph('#', "SYM_HASH");

    public static Glyph SYM_DOLLAR = new Glyph('$', "SYM_DOLLAR");

    public static Glyph SYM_PERCENT = new Glyph('%', "SYM_PERCENT");

    public static Glyph SYM_AMPERSAND = new Glyph('&', "SYM_AMPERSAND");

    public static Glyph SYM_APOSTROPHE = new Glyph('\'', "SYM_APOSTROPHE");

    public static Glyph SYM_LEFT_PARENS = new Glyph('(', "SYM_LEFT_PARENS");

    public static Glyph SYM_RIGHT_PARENS = new Glyph(',', "SYM_RIGHT_PARENS");

    public static Glyph SYM_ASTERISK = new Glyph('*', "SYM_ASTERISK");

    public static Glyph SYM_PLUS = new Glyph('+', "SYM_PLUS");

    public static Glyph SYM_COMMA = new Glyph(',', "SYM_COMMA");

    public static Glyph SYM_MINUS = new Glyph('-', "SYM_MINUS");

    public static Glyph SYM_PERIOD = new Glyph('.', "SYM_PERIOD");

    public static Glyph SYM_SLASH = new Glyph('/', "SYM_SLASH");

    public static Glyph NUM_0 = new Glyph('0', "NUM_0");

    public static Glyph NUM_1 = new Glyph('1', "NUM_1");

    public static Glyph NUM_2 = new Glyph('2', "NUM_2");

    public static Glyph NUM_3 = new Glyph('3', "NUM_3");

    public static Glyph NUM_4 = new Glyph('4', "NUM_4");

    public static Glyph NUM_5 = new Glyph('5', "NUM_5");

    public static Glyph NUM_6 = new Glyph('6', "NUM_6");

    public static Glyph NUM_7 = new Glyph('7', "NUM_7");

    public static Glyph NUM_8 = new Glyph('8', "NUM_8");

    public static Glyph NUM_9 = new Glyph('9', "NUM_9");

    public static Glyph SYM_COLON = new Glyph(':', "SYM_COLON");

    public static Glyph SYM_SEMICOLON = new Glyph(';', "SYM_SEMICOLON");

    public static Glyph SYM_LESS_THAN = new Glyph('<', "SYM_LESS_THAN");

    public static Glyph SYM_EQUAL = new Glyph('=', "SYM_EQUAL");

    public static Glyph SYM_GREATER_THAN = new Glyph('>', "SYM_GREATER_THAN");

    public static Glyph SYM_QUESTION = new Glyph('?', "SYM_QUESTION");

    public static Glyph SYM_AT = new Glyph('@', "SYM_AT");

    public static Glyph CHAR_UPPER_A = new Glyph('A', "CHAR_UPPER_A");

    public static Glyph CHAR_UPPER_B = new Glyph('B', "CHAR_UPPER_B");

    public static Glyph CHAR_UPPER_C = new Glyph('C', "CHAR_UPPER_C");

    public static Glyph CHAR_UPPER_D = new Glyph('D', "CHAR_UPPER_D");

    public static Glyph CHAR_UPPER_E = new Glyph('E', "CHAR_UPPER_E");

    public static Glyph CHAR_UPPER_F = new Glyph('F', "CHAR_UPPER_F");

    public static Glyph CHAR_UPPER_G = new Glyph('G', "CHAR_UPPER_G");

    public static Glyph CHAR_UPPER_H = new Glyph('H', "CHAR_UPPER_H");

    public static Glyph CHAR_UPPER_I = new Glyph('I', "CHAR_UPPER_I");

    public static Glyph CHAR_UPPER_J = new Glyph('J', "CHAR_UPPER_J");

    public static Glyph CHAR_UPPER_K = new Glyph('K', "CHAR_UPPER_K");

    public static Glyph CHAR_UPPER_L = new Glyph('L', "CHAR_UPPER_L");

    public static Glyph CHAR_UPPER_M = new Glyph('M', "CHAR_UPPER_M");

    public static Glyph CHAR_UPPER_N = new Glyph('N', "CHAR_UPPER_N");

    public static Glyph CHAR_UPPER_O = new Glyph('O', "CHAR_UPPER_O");

    public static Glyph CHAR_UPPER_P = new Glyph('P', "CHAR_UPPER_P");

    public static Glyph CHAR_UPPER_Q = new Glyph('Q', "CHAR_UPPER_Q");

    public static Glyph CHAR_UPPER_R = new Glyph('R', "CHAR_UPPER_R");

    public static Glyph CHAR_UPPER_S = new Glyph('S', "CHAR_UPPER_S");

    public static Glyph CHAR_UPPER_T = new Glyph('T', "CHAR_UPPER_T");

    public static Glyph CHAR_UPPER_U = new Glyph('U', "CHAR_UPPER_U");

    public static Glyph CHAR_UPPER_V = new Glyph('V', "CHAR_UPPER_V");

    public static Glyph CHAR_UPPER_W = new Glyph('W', "CHAR_UPPER_W");

    public static Glyph CHAR_UPPER_X = new Glyph('X', "CHAR_UPPER_X");

    public static Glyph CHAR_UPPER_Y = new Glyph('Y', "CHAR_UPPER_Y");

    public static Glyph CHAR_UPPER_Z = new Glyph('Z', "CHAR_UPPER_Z");

    public static Glyph SYM_LEFT_SQUARE_BRACKET = new Glyph('[', "SYM_LEFT_SQUARE_BRACKET");

    public static Glyph SYM_BACKSLASH = new Glyph('\\', "SYM_BACKSLASH");

    public static Glyph SYM_RIGHT_SQUARE_BRACKET = new Glyph(']', "SYM_RIGHT_SQUARE_BRACKET");

    public static Glyph SYM_CARET = new Glyph('^', "SYM_CARET");

    public static Glyph SYM_UNDERSCORE = new Glyph('_', "SYM_UNDERSCORE");

    public static Glyph SYM_BACKTICK = new Glyph('`', "SYM_BACKTICK");

    public static Glyph CHAR_LOWER_A = new Glyph('a', "CHAR_LOWER_A");

    public static Glyph CHAR_LOWER_B = new Glyph('b', "CHAR_LOWER_B");

    public static Glyph CHAR_LOWER_C = new Glyph('c', "CHAR_LOWER_C");

    public static Glyph CHAR_LOWER_D = new Glyph('d', "CHAR_LOWER_D");

    public static Glyph CHAR_LOWER_E = new Glyph('e', "CHAR_LOWER_E");

    public static Glyph CHAR_LOWER_F = new Glyph('f', "CHAR_LOWER_F");

    public static Glyph CHAR_LOWER_G = new Glyph('g', "CHAR_LOWER_G");

    public static Glyph CHAR_LOWER_H = new Glyph('h', "CHAR_LOWER_H");

    public static Glyph CHAR_LOWER_I = new Glyph('i', "CHAR_LOWER_I");

    public static Glyph CHAR_LOWER_J = new Glyph('j', "CHAR_LOWER_J");

    public static Glyph CHAR_LOWER_K = new Glyph('k', "CHAR_LOWER_K");

    public static Glyph CHAR_LOWER_L = new Glyph('l', "CHAR_LOWER_L");

    public static Glyph CHAR_LOWER_M = new Glyph('m', "CHAR_LOWER_M");

    public static Glyph CHAR_LOWER_N = new Glyph('n', "CHAR_LOWER_N");

    public static Glyph CHAR_LOWER_O = new Glyph('o', "CHAR_LOWER_O");

    public static Glyph CHAR_LOWER_P = new Glyph('p', "CHAR_LOWER_P");

    public static Glyph CHAR_LOWER_Q = new Glyph('q', "CHAR_LOWER_Q");

    public static Glyph CHAR_LOWER_R = new Glyph('r', "CHAR_LOWER_R");

    public static Glyph CHAR_LOWER_S = new Glyph('s', "CHAR_LOWER_S");

    public static Glyph CHAR_LOWER_T = new Glyph('t', "CHAR_LOWER_T");

    public static Glyph CHAR_LOWER_U = new Glyph('u', "CHAR_LOWER_U");

    public static Glyph CHAR_LOWER_V = new Glyph('v', "CHAR_LOWER_V");

    public static Glyph CHAR_LOWER_W = new Glyph('w', "CHAR_LOWER_W");

    public static Glyph CHAR_LOWER_X = new Glyph('x', "CHAR_LOWER_X");

    public static Glyph CHAR_LOWER_Y = new Glyph('y', "CHAR_LOWER_Y");

    public static Glyph CHAR_LOWER_Z = new Glyph('z', "CHAR_LOWER_Z");

    public static Glyph SYM_LEFT_BRACE = new Glyph('{', "SYM_LEFT_BRACE");

    public static Glyph SYM_PIPE = new Glyph('|', "SYM_PIPE");

    public static Glyph SYM_RIGHT_BRACE = new Glyph('}', "SYM_RIGHT_BRACE");

    public static Glyph SYM_TILDE = new Glyph('~', "SYM_TILDE");

    public static void init() {
        SYM_SPACE.setup();
        SYM_BANG.setup();
        SYM_DOUBLE_QUOTES.setup();
        SYM_HASH.setup();
        SYM_DOLLAR.setup();
        SYM_PERCENT.setup();
        SYM_AMPERSAND.setup();
        SYM_APOSTROPHE.setup();
        SYM_LEFT_PARENS.setup();
        SYM_RIGHT_PARENS.setup();
        SYM_ASTERISK.setup();
        SYM_PLUS.setup();
        SYM_COMMA.setup();
        SYM_MINUS.setup();
        SYM_PERIOD.setup();
        SYM_SLASH.setup();
        NUM_0.setup();
        NUM_1.setup();
        NUM_2.setup();
        NUM_3.setup();
        NUM_4.setup();
        NUM_5.setup();
        NUM_6.setup();
        NUM_7.setup();
        NUM_8.setup();
        NUM_9.setup();
        SYM_COLON.setup();
        SYM_SEMICOLON.setup();
        SYM_LESS_THAN.setup();
        SYM_EQUAL.setup();
        SYM_GREATER_THAN.setup();
        SYM_QUESTION.setup();
        SYM_AT.setup();
        CHAR_UPPER_A.setup();
        CHAR_UPPER_B.setup();
        CHAR_UPPER_C.setup();
        CHAR_UPPER_D.setup();
        CHAR_UPPER_E.setup();
        CHAR_UPPER_F.setup();
        CHAR_UPPER_G.setup();
        CHAR_UPPER_H.setup();
        CHAR_UPPER_I.setup();
        CHAR_UPPER_J.setup();
        CHAR_UPPER_K.setup();
        CHAR_UPPER_L.setup();
        CHAR_UPPER_M.setup();
        CHAR_UPPER_N.setup();
        CHAR_UPPER_O.setup();
        CHAR_UPPER_P.setup();
        CHAR_UPPER_Q.setup();
        CHAR_UPPER_R.setup();
        CHAR_UPPER_S.setup();
        CHAR_UPPER_T.setup();
        CHAR_UPPER_U.setup();
        CHAR_UPPER_V.setup();
        CHAR_UPPER_W.setup();
        CHAR_UPPER_X.setup();
        CHAR_UPPER_Y.setup();
        CHAR_UPPER_Z.setup();
        SYM_LEFT_SQUARE_BRACKET.setup();
        SYM_BACKSLASH.setup();
        SYM_RIGHT_SQUARE_BRACKET.setup();
        SYM_CARET.setup();
        SYM_UNDERSCORE.setup();
        CHAR_LOWER_A.setup();
        CHAR_LOWER_B.setup();
        CHAR_LOWER_C.setup();
        CHAR_LOWER_D.setup();
        CHAR_LOWER_E.setup();
        CHAR_LOWER_F.setup();
        CHAR_LOWER_G.setup();
        CHAR_LOWER_H.setup();
        CHAR_LOWER_I.setup();
        CHAR_LOWER_J.setup();
        CHAR_LOWER_K.setup();
        CHAR_LOWER_L.setup();
        CHAR_LOWER_M.setup();
        CHAR_LOWER_N.setup();
        CHAR_LOWER_O.setup();
        CHAR_LOWER_P.setup();
        CHAR_LOWER_Q.setup();
        CHAR_LOWER_R.setup();
        CHAR_LOWER_S.setup();
        CHAR_LOWER_T.setup();
        CHAR_LOWER_U.setup();
        CHAR_LOWER_V.setup();
        CHAR_LOWER_W.setup();
        CHAR_LOWER_X.setup();
        CHAR_LOWER_Y.setup();
        CHAR_LOWER_Z.setup();
        SYM_LEFT_BRACE.setup();
        SYM_PIPE.setup();
        SYM_RIGHT_BRACE.setup();
        SYM_TILDE.setup();
    }

    public final Sprite sprite;
    public final char character;

    public void setup() {
        GLYPH_CHARACTER_LOOKUP.put(this.character, this);
    }

    public Glyph(char character, String spriteKey) {
        this.sprite = Atlas.UI_ATLAS.getSprite(spriteKey);
        this.character = character;
    }
}
