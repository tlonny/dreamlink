package doors.overlay;

import doors.graphics.TextureSample;

import java.util.HashMap;
import java.util.Map;

public class Glyph {

    public static Map<Character,Glyph> GLYPH_CHARACTER_LOOKUP = new HashMap<>();

    public static Glyph SPACE = new Glyph(' ', OverlayTextureAtlas.SPACE, OverlayTextureAtlas.UNDERLINE_SPACE);
    public static Glyph SYM_BANG = new Glyph('!', OverlayTextureAtlas.BANG, OverlayTextureAtlas.UNDERLINE_BANG);
    public static Glyph SYM_DOUBLE_QUOTES = new Glyph('"', OverlayTextureAtlas.DOUBLE_QUOTES, OverlayTextureAtlas.UNDERLINE_DOUBLEQUOTES);
    public static Glyph SYM_HASH = new Glyph('#', OverlayTextureAtlas.HASH, OverlayTextureAtlas.UNDERLINE_HASH);
    public static Glyph SYM_DOLLAR = new Glyph('$', OverlayTextureAtlas.DOLLAR, OverlayTextureAtlas.UNDERLINE_DOLLAR);
    public static Glyph SYM_PERCENT = new Glyph('%', OverlayTextureAtlas.PERCENT, OverlayTextureAtlas.UNDERLINE_PERCENT);
    public static Glyph SYM_AMPERSAND = new Glyph('&', OverlayTextureAtlas.AMPERSAND, OverlayTextureAtlas.UNDERLINE_AMPERSAND);
    public static Glyph SYM_APOSTROPHE = new Glyph('\'', OverlayTextureAtlas.BACKSLASH, OverlayTextureAtlas.UNDERLINE_BACKSLASH);
    public static Glyph SYM_LEFT_PARENS = new Glyph('(', OverlayTextureAtlas.LEFT_PARENS, OverlayTextureAtlas.UNDERLINE_LEFT_PARENS);
    public static Glyph SYM_RIGHT_PARENS = new Glyph(')', OverlayTextureAtlas.RIGHT_PARENS, OverlayTextureAtlas.UNDERLINE_RIGHT_PARENS);
    public static Glyph SYM_ASTERISK = new Glyph('*', OverlayTextureAtlas.ASTERISK, OverlayTextureAtlas.UNDERLINE_ASTERISK);
    public static Glyph SYM_PLUS = new Glyph('+', OverlayTextureAtlas.PLUS, OverlayTextureAtlas.UNDERLINE_PLUS);
    public static Glyph SYM_COMMA = new Glyph(',', OverlayTextureAtlas.COMMA, OverlayTextureAtlas.UNDERLINE_COMMA);
    public static Glyph SYM_MINUS = new Glyph('-', OverlayTextureAtlas.DASH, OverlayTextureAtlas.UNDERLINE_DASH);
    public static Glyph SYM_PERIOD = new Glyph('.', OverlayTextureAtlas.PERIOD, OverlayTextureAtlas.UNDERLINE_PERIOD);
    public static Glyph SYM_SLASH = new Glyph('/', OverlayTextureAtlas.SLASH, OverlayTextureAtlas.UNDERLINE_SLASH);

    public static Glyph NUM_0 = new Glyph('0', OverlayTextureAtlas.ZERO, OverlayTextureAtlas.UNDERLINE_ZERO);
    public static Glyph NUM_1 = new Glyph('1', OverlayTextureAtlas.ONE, OverlayTextureAtlas.UNDERLINE_ONE);
    public static Glyph NUM_2 = new Glyph('2', OverlayTextureAtlas.TWO, OverlayTextureAtlas.UNDERLINE_TWO);
    public static Glyph NUM_3 = new Glyph('3', OverlayTextureAtlas.THREE, OverlayTextureAtlas.UNDERLINE_THREE);
    public static Glyph NUM_4 = new Glyph('4', OverlayTextureAtlas.FOUR, OverlayTextureAtlas.UNDERLINE_FOUR);
    public static Glyph NUM_5 = new Glyph('5', OverlayTextureAtlas.FIVE, OverlayTextureAtlas.UNDERLINE_FIVE);
    public static Glyph NUM_6 = new Glyph('6', OverlayTextureAtlas.SIX, OverlayTextureAtlas.UNDERLINE_SIX);
    public static Glyph NUM_7 = new Glyph('7', OverlayTextureAtlas.SEVEN, OverlayTextureAtlas.UNDERLINE_SEVEN);
    public static Glyph NUM_8 = new Glyph('8', OverlayTextureAtlas.EIGHT, OverlayTextureAtlas.UNDERLINE_EIGHT);
    public static Glyph NUM_9 = new Glyph('9', OverlayTextureAtlas.NINE, OverlayTextureAtlas.UNDERLINE_NINE);

    public static Glyph SYM_COLON = new Glyph(':', OverlayTextureAtlas.COLON, OverlayTextureAtlas.UNDERLINE_COLON);
    public static Glyph SYM_SEMICOLON = new Glyph(';', OverlayTextureAtlas.SEMICOLON, OverlayTextureAtlas.UNDERLINE_SEMICOLON);
    public static Glyph SYM_LESS_THAN = new Glyph('<', OverlayTextureAtlas.LESS_THAN, OverlayTextureAtlas.UNDERLINE_LESS_THAN);
    public static Glyph SYM_EQUAL = new Glyph('=', OverlayTextureAtlas.EQUALS, OverlayTextureAtlas.UNDERLINE_EQUALS);
    public static Glyph SYM_GREATER_THAN = new Glyph('>', OverlayTextureAtlas.GREATER_THAN, OverlayTextureAtlas.UNDERLINE_GREATER_THAN);
    public static Glyph SYM_QUESTION = new Glyph('?', OverlayTextureAtlas.QUESTION, OverlayTextureAtlas.UNDERLINE_QUESTION);
    public static Glyph SYM_AT = new Glyph('@', OverlayTextureAtlas.AT, OverlayTextureAtlas.UNDERLINE_AT);

    public static Glyph CHAR_UPPER_A = new Glyph('A', OverlayTextureAtlas.UPPER_A, OverlayTextureAtlas.UNDERLINE_UPPER_A);
    public static Glyph CHAR_UPPER_B = new Glyph('B', OverlayTextureAtlas.UPPER_B, OverlayTextureAtlas.UNDERLINE_UPPER_B);
    public static Glyph CHAR_UPPER_C = new Glyph('C', OverlayTextureAtlas.UPPER_C, OverlayTextureAtlas.UNDERLINE_UPPER_C);
    public static Glyph CHAR_UPPER_D = new Glyph('D', OverlayTextureAtlas.UPPER_D, OverlayTextureAtlas.UNDERLINE_UPPER_D);
    public static Glyph CHAR_UPPER_E = new Glyph('E', OverlayTextureAtlas.UPPER_E, OverlayTextureAtlas.UNDERLINE_UPPER_E);
    public static Glyph CHAR_UPPER_F = new Glyph('F', OverlayTextureAtlas.UPPER_F, OverlayTextureAtlas.UNDERLINE_UPPER_F);
    public static Glyph CHAR_UPPER_G = new Glyph('G', OverlayTextureAtlas.UPPER_G, OverlayTextureAtlas.UNDERLINE_UPPER_G);
    public static Glyph CHAR_UPPER_H = new Glyph('H', OverlayTextureAtlas.UPPER_H, OverlayTextureAtlas.UNDERLINE_UPPER_H);
    public static Glyph CHAR_UPPER_I = new Glyph('I', OverlayTextureAtlas.UPPER_I, OverlayTextureAtlas.UNDERLINE_UPPER_I);
    public static Glyph CHAR_UPPER_J = new Glyph('J', OverlayTextureAtlas.UPPER_J, OverlayTextureAtlas.UNDERLINE_UPPER_J);
    public static Glyph CHAR_UPPER_K = new Glyph('K', OverlayTextureAtlas.UPPER_K, OverlayTextureAtlas.UNDERLINE_UPPER_K);
    public static Glyph CHAR_UPPER_L = new Glyph('L', OverlayTextureAtlas.UPPER_L, OverlayTextureAtlas.UNDERLINE_UPPER_L);
    public static Glyph CHAR_UPPER_M = new Glyph('M', OverlayTextureAtlas.UPPER_M, OverlayTextureAtlas.UNDERLINE_UPPER_M);
    public static Glyph CHAR_UPPER_N = new Glyph('N', OverlayTextureAtlas.UPPER_N, OverlayTextureAtlas.UNDERLINE_UPPER_N);
    public static Glyph CHAR_UPPER_O = new Glyph('O', OverlayTextureAtlas.UPPER_O, OverlayTextureAtlas.UNDERLINE_UPPER_O);
    public static Glyph CHAR_UPPER_P = new Glyph('P', OverlayTextureAtlas.UPPER_P, OverlayTextureAtlas.UNDERLINE_UPPER_P);
    public static Glyph CHAR_UPPER_Q = new Glyph('Q', OverlayTextureAtlas.UPPER_Q, OverlayTextureAtlas.UNDERLINE_UPPER_Q);
    public static Glyph CHAR_UPPER_R = new Glyph('R', OverlayTextureAtlas.UPPER_R, OverlayTextureAtlas.UNDERLINE_UPPER_R);
    public static Glyph CHAR_UPPER_S = new Glyph('S', OverlayTextureAtlas.UPPER_S, OverlayTextureAtlas.UNDERLINE_UPPER_S);
    public static Glyph CHAR_UPPER_T = new Glyph('T', OverlayTextureAtlas.UPPER_T, OverlayTextureAtlas.UNDERLINE_UPPER_T);
    public static Glyph CHAR_UPPER_U = new Glyph('U', OverlayTextureAtlas.UPPER_U, OverlayTextureAtlas.UNDERLINE_UPPER_U);
    public static Glyph CHAR_UPPER_V = new Glyph('V', OverlayTextureAtlas.UPPER_V, OverlayTextureAtlas.UNDERLINE_UPPER_V);
    public static Glyph CHAR_UPPER_W = new Glyph('W', OverlayTextureAtlas.UPPER_W, OverlayTextureAtlas.UNDERLINE_UPPER_W);
    public static Glyph CHAR_UPPER_X = new Glyph('X', OverlayTextureAtlas.UPPER_X, OverlayTextureAtlas.UNDERLINE_UPPER_X);
    public static Glyph CHAR_UPPER_Y = new Glyph('Y', OverlayTextureAtlas.UPPER_Y, OverlayTextureAtlas.UNDERLINE_UPPER_Y);
    public static Glyph CHAR_UPPER_Z = new Glyph('Z', OverlayTextureAtlas.UPPER_Z, OverlayTextureAtlas.UNDERLINE_UPPER_Z);

    public static Glyph SYM_LEFT_SQUARE_BRACKET = new Glyph('[', OverlayTextureAtlas.LEFT_BRACKET, OverlayTextureAtlas.UNDERLINE_LEFT_BRACKET);
    public static Glyph SYM_BACKSLASH = new Glyph('\\',OverlayTextureAtlas.BACKSLASH, OverlayTextureAtlas.UNDERLINE_BACKSLASH);
    public static Glyph SYM_RIGHT_SQUARE_BRACKET = new Glyph(']', OverlayTextureAtlas.RIGHT_BRACKET, OverlayTextureAtlas.UNDERLINE_RIGHT_BRACKET);
    public static Glyph SYM_CARET = new Glyph('^', OverlayTextureAtlas.CARET, OverlayTextureAtlas.UNDERLINE_CARET);
    public static Glyph SYM_UNDERSCORE = new Glyph('_', OverlayTextureAtlas.UNDERSCORE, OverlayTextureAtlas.UNDERLINE_UNDERSCORE);
    public static Glyph SYM_BACKTICK = new Glyph('`', OverlayTextureAtlas.BACKTICK, OverlayTextureAtlas.UNDERLINE_BACKTICK);

    public static Glyph CHAR_LOWER_A = new Glyph('a', OverlayTextureAtlas.LOWER_A, OverlayTextureAtlas.UNDERLINE_LOWER_A);
    public static Glyph CHAR_LOWER_B = new Glyph('b', OverlayTextureAtlas.LOWER_B, OverlayTextureAtlas.UNDERLINE_LOWER_B);
    public static Glyph CHAR_LOWER_C = new Glyph('c', OverlayTextureAtlas.LOWER_C, OverlayTextureAtlas.UNDERLINE_LOWER_C);
    public static Glyph CHAR_LOWER_D = new Glyph('d', OverlayTextureAtlas.LOWER_D, OverlayTextureAtlas.UNDERLINE_LOWER_D);
    public static Glyph CHAR_LOWER_E = new Glyph('e', OverlayTextureAtlas.LOWER_E, OverlayTextureAtlas.UNDERLINE_LOWER_E);
    public static Glyph CHAR_LOWER_F = new Glyph('f', OverlayTextureAtlas.LOWER_F, OverlayTextureAtlas.UNDERLINE_LOWER_F);
    public static Glyph CHAR_LOWER_G = new Glyph('g', OverlayTextureAtlas.LOWER_G, OverlayTextureAtlas.UNDERLINE_LOWER_G);
    public static Glyph CHAR_LOWER_H = new Glyph('h', OverlayTextureAtlas.LOWER_H, OverlayTextureAtlas.UNDERLINE_LOWER_H);
    public static Glyph CHAR_LOWER_I = new Glyph('i', OverlayTextureAtlas.LOWER_I, OverlayTextureAtlas.UNDERLINE_LOWER_I);
    public static Glyph CHAR_LOWER_J = new Glyph('j', OverlayTextureAtlas.LOWER_J, OverlayTextureAtlas.UNDERLINE_LOWER_J);
    public static Glyph CHAR_LOWER_K = new Glyph('k', OverlayTextureAtlas.LOWER_K, OverlayTextureAtlas.UNDERLINE_LOWER_K);
    public static Glyph CHAR_LOWER_L = new Glyph('l', OverlayTextureAtlas.LOWER_L, OverlayTextureAtlas.UNDERLINE_LOWER_L);
    public static Glyph CHAR_LOWER_M = new Glyph('m', OverlayTextureAtlas.LOWER_M, OverlayTextureAtlas.UNDERLINE_LOWER_M);
    public static Glyph CHAR_LOWER_N = new Glyph('n', OverlayTextureAtlas.LOWER_N, OverlayTextureAtlas.UNDERLINE_LOWER_N);
    public static Glyph CHAR_LOWER_O = new Glyph('o', OverlayTextureAtlas.LOWER_O, OverlayTextureAtlas.UNDERLINE_LOWER_O);
    public static Glyph CHAR_LOWER_P = new Glyph('p', OverlayTextureAtlas.LOWER_P, OverlayTextureAtlas.UNDERLINE_LOWER_P);
    public static Glyph CHAR_LOWER_Q = new Glyph('q', OverlayTextureAtlas.LOWER_Q, OverlayTextureAtlas.UNDERLINE_LOWER_Q);
    public static Glyph CHAR_LOWER_R = new Glyph('r', OverlayTextureAtlas.LOWER_R, OverlayTextureAtlas.UNDERLINE_LOWER_R);
    public static Glyph CHAR_LOWER_S = new Glyph('s', OverlayTextureAtlas.LOWER_S, OverlayTextureAtlas.UNDERLINE_LOWER_S);
    public static Glyph CHAR_LOWER_T = new Glyph('t', OverlayTextureAtlas.LOWER_T, OverlayTextureAtlas.UNDERLINE_LOWER_T);
    public static Glyph CHAR_LOWER_U = new Glyph('u', OverlayTextureAtlas.LOWER_U, OverlayTextureAtlas.UNDERLINE_LOWER_U);
    public static Glyph CHAR_LOWER_V = new Glyph('v', OverlayTextureAtlas.LOWER_V, OverlayTextureAtlas.UNDERLINE_LOWER_V);
    public static Glyph CHAR_LOWER_W = new Glyph('w', OverlayTextureAtlas.LOWER_W, OverlayTextureAtlas.UNDERLINE_LOWER_W);
    public static Glyph CHAR_LOWER_X = new Glyph('x', OverlayTextureAtlas.LOWER_X, OverlayTextureAtlas.UNDERLINE_LOWER_X);
    public static Glyph CHAR_LOWER_Y = new Glyph('y', OverlayTextureAtlas.LOWER_Y, OverlayTextureAtlas.UNDERLINE_LOWER_Y);
    public static Glyph CHAR_LOWER_Z = new Glyph('z', OverlayTextureAtlas.LOWER_Z, OverlayTextureAtlas.UNDERLINE_LOWER_Z);

    public static Glyph SYM_LEFT_BRACE = new Glyph('{', OverlayTextureAtlas.LEFT_BRACE, OverlayTextureAtlas.UNDERLINE_LEFT_BRACE);
    public static Glyph SYM_PIPE = new Glyph('|', OverlayTextureAtlas.PIPE, OverlayTextureAtlas.UNDERLINE_PIPE);
    public static Glyph SYM_RIGHT_BRACE = new Glyph('}', OverlayTextureAtlas.RIGHT_BRACE, OverlayTextureAtlas.UNDERLINE_RIGHT_BRACE);
    public static Glyph SYM_TILDE = new Glyph('~', OverlayTextureAtlas.TILDE, OverlayTextureAtlas.UNDERLINE_TILDE);

    public TextureSample textureSample;
    public TextureSample underlineTextureSample;
    public char character;

    public Glyph(char character, TextureSample textureSample, TextureSample underlineTextureSample) {
        this.character = character;;
        this.textureSample = textureSample;
        this.underlineTextureSample = underlineTextureSample;
        GLYPH_CHARACTER_LOOKUP.put(this.character, this);
    }
}
