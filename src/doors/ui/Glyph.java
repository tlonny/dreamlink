package doors.ui;

import doors.graphics.TextureSample;
import doors.overlay.SystemTextureAtlas;

import java.util.HashMap;
import java.util.Map;

public class Glyph {

    public static Map<Character,Glyph> GLYPH_CHARACTER_LOOKUP = new HashMap<>();

    public static Glyph SPACE = new Glyph(' ', SystemTextureAtlas.SPACE, SystemTextureAtlas.UNDERLINE_SPACE);
    public static Glyph SYM_BANG = new Glyph('!', SystemTextureAtlas.BANG, SystemTextureAtlas.UNDERLINE_BANG);
    public static Glyph SYM_DOUBLE_QUOTES = new Glyph('"', SystemTextureAtlas.DOUBLE_QUOTES, SystemTextureAtlas.UNDERLINE_DOUBLEQUOTES);
    public static Glyph SYM_HASH = new Glyph('#', SystemTextureAtlas.HASH, SystemTextureAtlas.UNDERLINE_HASH);
    public static Glyph SYM_DOLLAR = new Glyph('$', SystemTextureAtlas.DOLLAR, SystemTextureAtlas.UNDERLINE_DOLLAR);
    public static Glyph SYM_PERCENT = new Glyph('%', SystemTextureAtlas.PERCENT, SystemTextureAtlas.UNDERLINE_PERCENT);
    public static Glyph SYM_AMPERSAND = new Glyph('&', SystemTextureAtlas.AMPERSAND, SystemTextureAtlas.UNDERLINE_AMPERSAND);
    public static Glyph SYM_APOSTROPHE = new Glyph('\'', SystemTextureAtlas.BACKSLASH, SystemTextureAtlas.UNDERLINE_BACKSLASH);
    public static Glyph SYM_LEFT_PARENS = new Glyph('(', SystemTextureAtlas.LEFT_PARENS, SystemTextureAtlas.UNDERLINE_LEFT_PARENS);
    public static Glyph SYM_RIGHT_PARENS = new Glyph(')', SystemTextureAtlas.RIGHT_PARENS, SystemTextureAtlas.UNDERLINE_RIGHT_PARENS);
    public static Glyph SYM_ASTERISK = new Glyph('*', SystemTextureAtlas.ASTERISK, SystemTextureAtlas.UNDERLINE_ASTERISK);
    public static Glyph SYM_PLUS = new Glyph('+', SystemTextureAtlas.PLUS, SystemTextureAtlas.UNDERLINE_PLUS);
    public static Glyph SYM_COMMA = new Glyph(',', SystemTextureAtlas.COMMA, SystemTextureAtlas.UNDERLINE_COMMA);
    public static Glyph SYM_MINUS = new Glyph('-', SystemTextureAtlas.DASH, SystemTextureAtlas.UNDERLINE_DASH);
    public static Glyph SYM_PERIOD = new Glyph('.', SystemTextureAtlas.PERIOD, SystemTextureAtlas.UNDERLINE_PERIOD);
    public static Glyph SYM_SLASH = new Glyph('/', SystemTextureAtlas.SLASH, SystemTextureAtlas.UNDERLINE_SLASH);

    public static Glyph NUM_0 = new Glyph('0', SystemTextureAtlas.ZERO, SystemTextureAtlas.UNDERLINE_ZERO);
    public static Glyph NUM_1 = new Glyph('1', SystemTextureAtlas.ONE, SystemTextureAtlas.UNDERLINE_ONE);
    public static Glyph NUM_2 = new Glyph('2', SystemTextureAtlas.TWO, SystemTextureAtlas.UNDERLINE_TWO);
    public static Glyph NUM_3 = new Glyph('3', SystemTextureAtlas.THREE, SystemTextureAtlas.UNDERLINE_THREE);
    public static Glyph NUM_4 = new Glyph('4', SystemTextureAtlas.FOUR, SystemTextureAtlas.UNDERLINE_FOUR);
    public static Glyph NUM_5 = new Glyph('5', SystemTextureAtlas.FIVE, SystemTextureAtlas.UNDERLINE_FIVE);
    public static Glyph NUM_6 = new Glyph('6', SystemTextureAtlas.SIX, SystemTextureAtlas.UNDERLINE_SIX);
    public static Glyph NUM_7 = new Glyph('7', SystemTextureAtlas.SEVEN, SystemTextureAtlas.UNDERLINE_SEVEN);
    public static Glyph NUM_8 = new Glyph('8', SystemTextureAtlas.EIGHT, SystemTextureAtlas.UNDERLINE_EIGHT);
    public static Glyph NUM_9 = new Glyph('9', SystemTextureAtlas.NINE, SystemTextureAtlas.UNDERLINE_NINE);

    public static Glyph SYM_COLON = new Glyph(':', SystemTextureAtlas.COLON, SystemTextureAtlas.UNDERLINE_COLON);
    public static Glyph SYM_SEMICOLON = new Glyph(';', SystemTextureAtlas.SEMICOLON, SystemTextureAtlas.UNDERLINE_SEMICOLON);
    public static Glyph SYM_LESS_THAN = new Glyph('<', SystemTextureAtlas.LESS_THAN, SystemTextureAtlas.UNDERLINE_LESS_THAN);
    public static Glyph SYM_EQUAL = new Glyph('=', SystemTextureAtlas.EQUALS, SystemTextureAtlas.UNDERLINE_EQUALS);
    public static Glyph SYM_GREATER_THAN = new Glyph('>', SystemTextureAtlas.GREATER_THAN, SystemTextureAtlas.UNDERLINE_GREATER_THAN);
    public static Glyph SYM_QUESTION = new Glyph('?', SystemTextureAtlas.QUESTION, SystemTextureAtlas.UNDERLINE_QUESTION);
    public static Glyph SYM_AT = new Glyph('@', SystemTextureAtlas.AT, SystemTextureAtlas.UNDERLINE_AT);

    public static Glyph CHAR_UPPER_A = new Glyph('A', SystemTextureAtlas.UPPER_A, SystemTextureAtlas.UNDERLINE_UPPER_A);
    public static Glyph CHAR_UPPER_B = new Glyph('B', SystemTextureAtlas.UPPER_B, SystemTextureAtlas.UNDERLINE_UPPER_B);
    public static Glyph CHAR_UPPER_C = new Glyph('C', SystemTextureAtlas.UPPER_C, SystemTextureAtlas.UNDERLINE_UPPER_C);
    public static Glyph CHAR_UPPER_D = new Glyph('D', SystemTextureAtlas.UPPER_D, SystemTextureAtlas.UNDERLINE_UPPER_D);
    public static Glyph CHAR_UPPER_E = new Glyph('E', SystemTextureAtlas.UPPER_E, SystemTextureAtlas.UNDERLINE_UPPER_E);
    public static Glyph CHAR_UPPER_F = new Glyph('F', SystemTextureAtlas.UPPER_F, SystemTextureAtlas.UNDERLINE_UPPER_F);
    public static Glyph CHAR_UPPER_G = new Glyph('G', SystemTextureAtlas.UPPER_G, SystemTextureAtlas.UNDERLINE_UPPER_G);
    public static Glyph CHAR_UPPER_H = new Glyph('H', SystemTextureAtlas.UPPER_H, SystemTextureAtlas.UNDERLINE_UPPER_H);
    public static Glyph CHAR_UPPER_I = new Glyph('I', SystemTextureAtlas.UPPER_I, SystemTextureAtlas.UNDERLINE_UPPER_I);
    public static Glyph CHAR_UPPER_J = new Glyph('J', SystemTextureAtlas.UPPER_J, SystemTextureAtlas.UNDERLINE_UPPER_J);
    public static Glyph CHAR_UPPER_K = new Glyph('K', SystemTextureAtlas.UPPER_K, SystemTextureAtlas.UNDERLINE_UPPER_K);
    public static Glyph CHAR_UPPER_L = new Glyph('L', SystemTextureAtlas.UPPER_L, SystemTextureAtlas.UNDERLINE_UPPER_L);
    public static Glyph CHAR_UPPER_M = new Glyph('M', SystemTextureAtlas.UPPER_M, SystemTextureAtlas.UNDERLINE_UPPER_M);
    public static Glyph CHAR_UPPER_N = new Glyph('N', SystemTextureAtlas.UPPER_N, SystemTextureAtlas.UNDERLINE_UPPER_N);
    public static Glyph CHAR_UPPER_O = new Glyph('O', SystemTextureAtlas.UPPER_O, SystemTextureAtlas.UNDERLINE_UPPER_O);
    public static Glyph CHAR_UPPER_P = new Glyph('P', SystemTextureAtlas.UPPER_P, SystemTextureAtlas.UNDERLINE_UPPER_P);
    public static Glyph CHAR_UPPER_Q = new Glyph('Q', SystemTextureAtlas.UPPER_Q, SystemTextureAtlas.UNDERLINE_UPPER_Q);
    public static Glyph CHAR_UPPER_R = new Glyph('R', SystemTextureAtlas.UPPER_R, SystemTextureAtlas.UNDERLINE_UPPER_R);
    public static Glyph CHAR_UPPER_S = new Glyph('S', SystemTextureAtlas.UPPER_S, SystemTextureAtlas.UNDERLINE_UPPER_S);
    public static Glyph CHAR_UPPER_T = new Glyph('T', SystemTextureAtlas.UPPER_T, SystemTextureAtlas.UNDERLINE_UPPER_T);
    public static Glyph CHAR_UPPER_U = new Glyph('U', SystemTextureAtlas.UPPER_U, SystemTextureAtlas.UNDERLINE_UPPER_U);
    public static Glyph CHAR_UPPER_V = new Glyph('V', SystemTextureAtlas.UPPER_V, SystemTextureAtlas.UNDERLINE_UPPER_V);
    public static Glyph CHAR_UPPER_W = new Glyph('W', SystemTextureAtlas.UPPER_W, SystemTextureAtlas.UNDERLINE_UPPER_W);
    public static Glyph CHAR_UPPER_X = new Glyph('X', SystemTextureAtlas.UPPER_X, SystemTextureAtlas.UNDERLINE_UPPER_X);
    public static Glyph CHAR_UPPER_Y = new Glyph('Y', SystemTextureAtlas.UPPER_Y, SystemTextureAtlas.UNDERLINE_UPPER_Y);
    public static Glyph CHAR_UPPER_Z = new Glyph('Z', SystemTextureAtlas.UPPER_Z, SystemTextureAtlas.UNDERLINE_UPPER_Z);

    public static Glyph SYM_LEFT_SQUARE_BRACKET = new Glyph('[', SystemTextureAtlas.LEFT_BRACKET, SystemTextureAtlas.UNDERLINE_LEFT_BRACKET);
    public static Glyph SYM_BACKSLASH = new Glyph('\\',SystemTextureAtlas.BACKSLASH, SystemTextureAtlas.UNDERLINE_BACKSLASH);
    public static Glyph SYM_RIGHT_SQUARE_BRACKET = new Glyph(']', SystemTextureAtlas.RIGHT_BRACKET, SystemTextureAtlas.UNDERLINE_RIGHT_BRACKET);
    public static Glyph SYM_CARET = new Glyph('^', SystemTextureAtlas.CARET, SystemTextureAtlas.UNDERLINE_CARET);
    public static Glyph SYM_UNDERSCORE = new Glyph('_', SystemTextureAtlas.UNDERSCORE, SystemTextureAtlas.UNDERLINE_UNDERSCORE);
    public static Glyph SYM_BACKTICK = new Glyph('`', SystemTextureAtlas.BACKTICK, SystemTextureAtlas.UNDERLINE_BACKTICK);

    public static Glyph CHAR_LOWER_A = new Glyph('a', SystemTextureAtlas.LOWER_A, SystemTextureAtlas.UNDERLINE_LOWER_A);
    public static Glyph CHAR_LOWER_B = new Glyph('b', SystemTextureAtlas.LOWER_B, SystemTextureAtlas.UNDERLINE_LOWER_B);
    public static Glyph CHAR_LOWER_C = new Glyph('c', SystemTextureAtlas.LOWER_C, SystemTextureAtlas.UNDERLINE_LOWER_C);
    public static Glyph CHAR_LOWER_D = new Glyph('d', SystemTextureAtlas.LOWER_D, SystemTextureAtlas.UNDERLINE_LOWER_D);
    public static Glyph CHAR_LOWER_E = new Glyph('e', SystemTextureAtlas.LOWER_E, SystemTextureAtlas.UNDERLINE_LOWER_E);
    public static Glyph CHAR_LOWER_F = new Glyph('f', SystemTextureAtlas.LOWER_F, SystemTextureAtlas.UNDERLINE_LOWER_F);
    public static Glyph CHAR_LOWER_G = new Glyph('g', SystemTextureAtlas.LOWER_G, SystemTextureAtlas.UNDERLINE_LOWER_G);
    public static Glyph CHAR_LOWER_H = new Glyph('h', SystemTextureAtlas.LOWER_H, SystemTextureAtlas.UNDERLINE_LOWER_H);
    public static Glyph CHAR_LOWER_I = new Glyph('i', SystemTextureAtlas.LOWER_I, SystemTextureAtlas.UNDERLINE_LOWER_I);
    public static Glyph CHAR_LOWER_J = new Glyph('j', SystemTextureAtlas.LOWER_J, SystemTextureAtlas.UNDERLINE_LOWER_J);
    public static Glyph CHAR_LOWER_K = new Glyph('k', SystemTextureAtlas.LOWER_K, SystemTextureAtlas.UNDERLINE_LOWER_K);
    public static Glyph CHAR_LOWER_L = new Glyph('l', SystemTextureAtlas.LOWER_L, SystemTextureAtlas.UNDERLINE_LOWER_L);
    public static Glyph CHAR_LOWER_M = new Glyph('m', SystemTextureAtlas.LOWER_M, SystemTextureAtlas.UNDERLINE_LOWER_M);
    public static Glyph CHAR_LOWER_N = new Glyph('n', SystemTextureAtlas.LOWER_N, SystemTextureAtlas.UNDERLINE_LOWER_N);
    public static Glyph CHAR_LOWER_O = new Glyph('o', SystemTextureAtlas.LOWER_O, SystemTextureAtlas.UNDERLINE_LOWER_O);
    public static Glyph CHAR_LOWER_P = new Glyph('p', SystemTextureAtlas.LOWER_P, SystemTextureAtlas.UNDERLINE_LOWER_P);
    public static Glyph CHAR_LOWER_Q = new Glyph('q', SystemTextureAtlas.LOWER_Q, SystemTextureAtlas.UNDERLINE_LOWER_Q);
    public static Glyph CHAR_LOWER_R = new Glyph('r', SystemTextureAtlas.LOWER_R, SystemTextureAtlas.UNDERLINE_LOWER_R);
    public static Glyph CHAR_LOWER_S = new Glyph('s', SystemTextureAtlas.LOWER_S, SystemTextureAtlas.UNDERLINE_LOWER_S);
    public static Glyph CHAR_LOWER_T = new Glyph('t', SystemTextureAtlas.LOWER_T, SystemTextureAtlas.UNDERLINE_LOWER_T);
    public static Glyph CHAR_LOWER_U = new Glyph('u', SystemTextureAtlas.LOWER_U, SystemTextureAtlas.UNDERLINE_LOWER_U);
    public static Glyph CHAR_LOWER_V = new Glyph('v', SystemTextureAtlas.LOWER_V, SystemTextureAtlas.UNDERLINE_LOWER_V);
    public static Glyph CHAR_LOWER_W = new Glyph('w', SystemTextureAtlas.LOWER_W, SystemTextureAtlas.UNDERLINE_LOWER_W);
    public static Glyph CHAR_LOWER_X = new Glyph('x', SystemTextureAtlas.LOWER_X, SystemTextureAtlas.UNDERLINE_LOWER_X);
    public static Glyph CHAR_LOWER_Y = new Glyph('y', SystemTextureAtlas.LOWER_Y, SystemTextureAtlas.UNDERLINE_LOWER_Y);
    public static Glyph CHAR_LOWER_Z = new Glyph('z', SystemTextureAtlas.LOWER_Z, SystemTextureAtlas.UNDERLINE_LOWER_Z);

    public static Glyph SYM_LEFT_BRACE = new Glyph('{', SystemTextureAtlas.LEFT_BRACE, SystemTextureAtlas.UNDERLINE_LEFT_BRACE);
    public static Glyph SYM_PIPE = new Glyph('|', SystemTextureAtlas.PIPE, SystemTextureAtlas.UNDERLINE_PIPE);
    public static Glyph SYM_RIGHT_BRACE = new Glyph('}', SystemTextureAtlas.RIGHT_BRACE, SystemTextureAtlas.UNDERLINE_RIGHT_BRACE);
    public static Glyph SYM_TILDE = new Glyph('~', SystemTextureAtlas.TILDE, SystemTextureAtlas.UNDERLINE_TILDE);

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
