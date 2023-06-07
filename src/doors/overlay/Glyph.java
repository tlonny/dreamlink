package doors.overlay;

import doors.graphics.TextureSample;

import java.util.HashMap;
import java.util.Map;

public class Glyph {

    public static Map<Character,Glyph> GLYPH_CHARACTER_LOOKUP = new HashMap<>();

    public static Glyph SPACE = new Glyph(' ', OverlayTexture.SPACE, OverlayTexture.UNDERLINE_SPACE);
    public static Glyph SYM_BANG = new Glyph('!', OverlayTexture.BANG, OverlayTexture.UNDERLINE_BANG);
    public static Glyph SYM_DOUBLE_QUOTES = new Glyph('"', OverlayTexture.DOUBLE_QUOTES, OverlayTexture.UNDERLINE_DOUBLEQUOTES);
    public static Glyph SYM_HASH = new Glyph('#', OverlayTexture.HASH, OverlayTexture.UNDERLINE_HASH);
    public static Glyph SYM_DOLLAR = new Glyph('$', OverlayTexture.DOLLAR, OverlayTexture.UNDERLINE_DOLLAR);
    public static Glyph SYM_PERCENT = new Glyph('%', OverlayTexture.PERCENT, OverlayTexture.UNDERLINE_PERCENT);
    public static Glyph SYM_AMPERSAND = new Glyph('&', OverlayTexture.AMPERSAND, OverlayTexture.UNDERLINE_AMPERSAND);
    public static Glyph SYM_APOSTROPHE = new Glyph('\'', OverlayTexture.BACKSLASH, OverlayTexture.UNDERLINE_BACKSLASH);
    public static Glyph SYM_LEFT_PARENS = new Glyph('(', OverlayTexture.LEFT_PARENS, OverlayTexture.UNDERLINE_LEFT_PARENS);
    public static Glyph SYM_RIGHT_PARENS = new Glyph(')', OverlayTexture.RIGHT_PARENS, OverlayTexture.UNDERLINE_RIGHT_PARENS);
    public static Glyph SYM_ASTERISK = new Glyph('*', OverlayTexture.ASTERISK, OverlayTexture.UNDERLINE_ASTERISK);
    public static Glyph SYM_PLUS = new Glyph('+', OverlayTexture.PLUS, OverlayTexture.UNDERLINE_PLUS);
    public static Glyph SYM_COMMA = new Glyph(',', OverlayTexture.COMMA, OverlayTexture.UNDERLINE_COMMA);
    public static Glyph SYM_MINUS = new Glyph('-', OverlayTexture.DASH, OverlayTexture.UNDERLINE_DASH);
    public static Glyph SYM_PERIOD = new Glyph('.', OverlayTexture.PERIOD, OverlayTexture.UNDERLINE_PERIOD);
    public static Glyph SYM_SLASH = new Glyph('/', OverlayTexture.SLASH, OverlayTexture.UNDERLINE_SLASH);

    public static Glyph NUM_0 = new Glyph('0', OverlayTexture.ZERO, OverlayTexture.UNDERLINE_ZERO);
    public static Glyph NUM_1 = new Glyph('1', OverlayTexture.ONE, OverlayTexture.UNDERLINE_ONE);
    public static Glyph NUM_2 = new Glyph('2', OverlayTexture.TWO, OverlayTexture.UNDERLINE_TWO);
    public static Glyph NUM_3 = new Glyph('3', OverlayTexture.THREE, OverlayTexture.UNDERLINE_THREE);
    public static Glyph NUM_4 = new Glyph('4', OverlayTexture.FOUR, OverlayTexture.UNDERLINE_FOUR);
    public static Glyph NUM_5 = new Glyph('5', OverlayTexture.FIVE, OverlayTexture.UNDERLINE_FIVE);
    public static Glyph NUM_6 = new Glyph('6', OverlayTexture.SIX, OverlayTexture.UNDERLINE_SIX);
    public static Glyph NUM_7 = new Glyph('7', OverlayTexture.SEVEN, OverlayTexture.UNDERLINE_SEVEN);
    public static Glyph NUM_8 = new Glyph('8', OverlayTexture.EIGHT, OverlayTexture.UNDERLINE_EIGHT);
    public static Glyph NUM_9 = new Glyph('9', OverlayTexture.NINE, OverlayTexture.UNDERLINE_NINE);

    public static Glyph SYM_COLON = new Glyph(':', OverlayTexture.COLON, OverlayTexture.UNDERLINE_COLON);
    public static Glyph SYM_SEMICOLON = new Glyph(';', OverlayTexture.SEMICOLON, OverlayTexture.UNDERLINE_SEMICOLON);
    public static Glyph SYM_LESS_THAN = new Glyph('<', OverlayTexture.LESS_THAN, OverlayTexture.UNDERLINE_LESS_THAN);
    public static Glyph SYM_EQUAL = new Glyph('=', OverlayTexture.EQUALS, OverlayTexture.UNDERLINE_EQUALS);
    public static Glyph SYM_GREATER_THAN = new Glyph('>', OverlayTexture.GREATER_THAN, OverlayTexture.UNDERLINE_GREATER_THAN);
    public static Glyph SYM_QUESTION = new Glyph('?', OverlayTexture.QUESTION, OverlayTexture.UNDERLINE_QUESTION);
    public static Glyph SYM_AT = new Glyph('@', OverlayTexture.AT, OverlayTexture.UNDERLINE_AT);

    public static Glyph CHAR_UPPER_A = new Glyph('A', OverlayTexture.UPPER_A, OverlayTexture.UNDERLINE_UPPER_A);
    public static Glyph CHAR_UPPER_B = new Glyph('B', OverlayTexture.UPPER_B, OverlayTexture.UNDERLINE_UPPER_B);
    public static Glyph CHAR_UPPER_C = new Glyph('C', OverlayTexture.UPPER_C, OverlayTexture.UNDERLINE_UPPER_C);
    public static Glyph CHAR_UPPER_D = new Glyph('D', OverlayTexture.UPPER_D, OverlayTexture.UNDERLINE_UPPER_D);
    public static Glyph CHAR_UPPER_E = new Glyph('E', OverlayTexture.UPPER_E, OverlayTexture.UNDERLINE_UPPER_E);
    public static Glyph CHAR_UPPER_F = new Glyph('F', OverlayTexture.UPPER_F, OverlayTexture.UNDERLINE_UPPER_F);
    public static Glyph CHAR_UPPER_G = new Glyph('G', OverlayTexture.UPPER_G, OverlayTexture.UNDERLINE_UPPER_G);
    public static Glyph CHAR_UPPER_H = new Glyph('H', OverlayTexture.UPPER_H, OverlayTexture.UNDERLINE_UPPER_H);
    public static Glyph CHAR_UPPER_I = new Glyph('I', OverlayTexture.UPPER_I, OverlayTexture.UNDERLINE_UPPER_I);
    public static Glyph CHAR_UPPER_J = new Glyph('J', OverlayTexture.UPPER_J, OverlayTexture.UNDERLINE_UPPER_J);
    public static Glyph CHAR_UPPER_K = new Glyph('K', OverlayTexture.UPPER_K, OverlayTexture.UNDERLINE_UPPER_K);
    public static Glyph CHAR_UPPER_L = new Glyph('L', OverlayTexture.UPPER_L, OverlayTexture.UNDERLINE_UPPER_L);
    public static Glyph CHAR_UPPER_M = new Glyph('M', OverlayTexture.UPPER_M, OverlayTexture.UNDERLINE_UPPER_M);
    public static Glyph CHAR_UPPER_N = new Glyph('N', OverlayTexture.UPPER_N, OverlayTexture.UNDERLINE_UPPER_N);
    public static Glyph CHAR_UPPER_O = new Glyph('O', OverlayTexture.UPPER_O, OverlayTexture.UNDERLINE_UPPER_O);
    public static Glyph CHAR_UPPER_P = new Glyph('P', OverlayTexture.UPPER_P, OverlayTexture.UNDERLINE_UPPER_P);
    public static Glyph CHAR_UPPER_Q = new Glyph('Q', OverlayTexture.UPPER_Q, OverlayTexture.UNDERLINE_UPPER_Q);
    public static Glyph CHAR_UPPER_R = new Glyph('R', OverlayTexture.UPPER_R, OverlayTexture.UNDERLINE_UPPER_R);
    public static Glyph CHAR_UPPER_S = new Glyph('S', OverlayTexture.UPPER_S, OverlayTexture.UNDERLINE_UPPER_S);
    public static Glyph CHAR_UPPER_T = new Glyph('T', OverlayTexture.UPPER_T, OverlayTexture.UNDERLINE_UPPER_T);
    public static Glyph CHAR_UPPER_U = new Glyph('U', OverlayTexture.UPPER_U, OverlayTexture.UNDERLINE_UPPER_U);
    public static Glyph CHAR_UPPER_V = new Glyph('V', OverlayTexture.UPPER_V, OverlayTexture.UNDERLINE_UPPER_V);
    public static Glyph CHAR_UPPER_W = new Glyph('W', OverlayTexture.UPPER_W, OverlayTexture.UNDERLINE_UPPER_W);
    public static Glyph CHAR_UPPER_X = new Glyph('X', OverlayTexture.UPPER_X, OverlayTexture.UNDERLINE_UPPER_X);
    public static Glyph CHAR_UPPER_Y = new Glyph('Y', OverlayTexture.UPPER_Y, OverlayTexture.UNDERLINE_UPPER_Y);
    public static Glyph CHAR_UPPER_Z = new Glyph('Z', OverlayTexture.UPPER_Z, OverlayTexture.UNDERLINE_UPPER_Z);

    public static Glyph SYM_LEFT_SQUARE_BRACKET = new Glyph('[', OverlayTexture.LEFT_BRACKET, OverlayTexture.UNDERLINE_LEFT_BRACKET);
    public static Glyph SYM_BACKSLASH = new Glyph('\\',OverlayTexture.BACKSLASH, OverlayTexture.UNDERLINE_BACKSLASH);
    public static Glyph SYM_RIGHT_SQUARE_BRACKET = new Glyph(']', OverlayTexture.RIGHT_BRACKET, OverlayTexture.UNDERLINE_RIGHT_BRACKET);
    public static Glyph SYM_CARET = new Glyph('^', OverlayTexture.CARET, OverlayTexture.UNDERLINE_CARET);
    public static Glyph SYM_UNDERSCORE = new Glyph('_', OverlayTexture.UNDERSCORE, OverlayTexture.UNDERLINE_UNDERSCORE);
    public static Glyph SYM_BACKTICK = new Glyph('`', OverlayTexture.BACKTICK, OverlayTexture.UNDERLINE_BACKTICK);

    public static Glyph CHAR_LOWER_A = new Glyph('a', OverlayTexture.LOWER_A, OverlayTexture.UNDERLINE_LOWER_A);
    public static Glyph CHAR_LOWER_B = new Glyph('b', OverlayTexture.LOWER_B, OverlayTexture.UNDERLINE_LOWER_B);
    public static Glyph CHAR_LOWER_C = new Glyph('c', OverlayTexture.LOWER_C, OverlayTexture.UNDERLINE_LOWER_C);
    public static Glyph CHAR_LOWER_D = new Glyph('d', OverlayTexture.LOWER_D, OverlayTexture.UNDERLINE_LOWER_D);
    public static Glyph CHAR_LOWER_E = new Glyph('e', OverlayTexture.LOWER_E, OverlayTexture.UNDERLINE_LOWER_E);
    public static Glyph CHAR_LOWER_F = new Glyph('f', OverlayTexture.LOWER_F, OverlayTexture.UNDERLINE_LOWER_F);
    public static Glyph CHAR_LOWER_G = new Glyph('g', OverlayTexture.LOWER_G, OverlayTexture.UNDERLINE_LOWER_G);
    public static Glyph CHAR_LOWER_H = new Glyph('h', OverlayTexture.LOWER_H, OverlayTexture.UNDERLINE_LOWER_H);
    public static Glyph CHAR_LOWER_I = new Glyph('i', OverlayTexture.LOWER_I, OverlayTexture.UNDERLINE_LOWER_I);
    public static Glyph CHAR_LOWER_J = new Glyph('j', OverlayTexture.LOWER_J, OverlayTexture.UNDERLINE_LOWER_J);
    public static Glyph CHAR_LOWER_K = new Glyph('k', OverlayTexture.LOWER_K, OverlayTexture.UNDERLINE_LOWER_K);
    public static Glyph CHAR_LOWER_L = new Glyph('l', OverlayTexture.LOWER_L, OverlayTexture.UNDERLINE_LOWER_L);
    public static Glyph CHAR_LOWER_M = new Glyph('m', OverlayTexture.LOWER_M, OverlayTexture.UNDERLINE_LOWER_M);
    public static Glyph CHAR_LOWER_N = new Glyph('n', OverlayTexture.LOWER_N, OverlayTexture.UNDERLINE_LOWER_N);
    public static Glyph CHAR_LOWER_O = new Glyph('o', OverlayTexture.LOWER_O, OverlayTexture.UNDERLINE_LOWER_O);
    public static Glyph CHAR_LOWER_P = new Glyph('p', OverlayTexture.LOWER_P, OverlayTexture.UNDERLINE_LOWER_P);
    public static Glyph CHAR_LOWER_Q = new Glyph('q', OverlayTexture.LOWER_Q, OverlayTexture.UNDERLINE_LOWER_Q);
    public static Glyph CHAR_LOWER_R = new Glyph('r', OverlayTexture.LOWER_R, OverlayTexture.UNDERLINE_LOWER_R);
    public static Glyph CHAR_LOWER_S = new Glyph('s', OverlayTexture.LOWER_S, OverlayTexture.UNDERLINE_LOWER_S);
    public static Glyph CHAR_LOWER_T = new Glyph('t', OverlayTexture.LOWER_T, OverlayTexture.UNDERLINE_LOWER_T);
    public static Glyph CHAR_LOWER_U = new Glyph('u', OverlayTexture.LOWER_U, OverlayTexture.UNDERLINE_LOWER_U);
    public static Glyph CHAR_LOWER_V = new Glyph('v', OverlayTexture.LOWER_V, OverlayTexture.UNDERLINE_LOWER_V);
    public static Glyph CHAR_LOWER_W = new Glyph('w', OverlayTexture.LOWER_W, OverlayTexture.UNDERLINE_LOWER_W);
    public static Glyph CHAR_LOWER_X = new Glyph('x', OverlayTexture.LOWER_X, OverlayTexture.UNDERLINE_LOWER_X);
    public static Glyph CHAR_LOWER_Y = new Glyph('y', OverlayTexture.LOWER_Y, OverlayTexture.UNDERLINE_LOWER_Y);
    public static Glyph CHAR_LOWER_Z = new Glyph('z', OverlayTexture.LOWER_Z, OverlayTexture.UNDERLINE_LOWER_Z);

    public static Glyph SYM_LEFT_BRACE = new Glyph('{', OverlayTexture.LEFT_BRACE, OverlayTexture.UNDERLINE_LEFT_BRACE);
    public static Glyph SYM_PIPE = new Glyph('|', OverlayTexture.PIPE, OverlayTexture.UNDERLINE_PIPE);
    public static Glyph SYM_RIGHT_BRACE = new Glyph('}', OverlayTexture.RIGHT_BRACE, OverlayTexture.UNDERLINE_RIGHT_BRACE);
    public static Glyph SYM_TILDE = new Glyph('~', OverlayTexture.TILDE, OverlayTexture.UNDERLINE_TILDE);

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
