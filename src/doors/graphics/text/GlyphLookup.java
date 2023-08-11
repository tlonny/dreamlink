package doors.graphics.text;

import java.util.HashMap;
import java.util.Map;

import doors.graphics.texture.sample.FontTextureSample;

public class GlyphLookup {

    public static GlyphLookup GLYPH_LOOKUP = new GlyphLookup();

    public Map<Character, Glyph> glyphLookup = new HashMap<>();

    public GlyphLookup() {
        this.glyphLookup.put(' ', new Glyph(FontTextureSample.SYM_SPACE, FontTextureSample.SYM_SPACE_UNDERLINE));
        this.glyphLookup.put('!', new Glyph(FontTextureSample.SYM_BANG, FontTextureSample.SYM_BANG_UNDERLINE));
        this.glyphLookup.put('"', new Glyph(FontTextureSample.SYM_DOUBLE_QUOTES, FontTextureSample.SYM_DOUBLE_QUOTES_UNDERLINE));
        this.glyphLookup.put('#', new Glyph(FontTextureSample.SYM_HASH, FontTextureSample.SYM_HASH_UNDERLINE));
        this.glyphLookup.put('$', new Glyph(FontTextureSample.SYM_DOLLAR, FontTextureSample.SYM_DOLLAR_UNDERLINE));
        this.glyphLookup.put('%', new Glyph(FontTextureSample.SYM_PERCENT, FontTextureSample.SYM_PERCENT_UNDERLINE));
        this.glyphLookup.put('&', new Glyph(FontTextureSample.SYM_AMPERSAND, FontTextureSample.SYM_AMPERSAND_UNDERLINE));
        this.glyphLookup.put('\'', new Glyph(FontTextureSample.SYM_SINGLE_QUOTE, FontTextureSample.SYM_SINGLE_QUOTE_UNDERLINE));
        this.glyphLookup.put('(', new Glyph(FontTextureSample.SYM_OPEN_PAREN, FontTextureSample.SYM_OPEN_PAREN_UNDERLINE));
        this.glyphLookup.put(')', new Glyph(FontTextureSample.SYM_CLOSE_PAREN, FontTextureSample.SYM_CLOSE_PAREN_UNDERLINE));
        this.glyphLookup.put('*', new Glyph(FontTextureSample.SYM_STAR, FontTextureSample.SYM_STAR_UNDERLINE));
        this.glyphLookup.put('+', new Glyph(FontTextureSample.SYM_PLUS, FontTextureSample.SYM_PLUS_UNDERLINE));
        this.glyphLookup.put(',', new Glyph(FontTextureSample.SYM_COMMA, FontTextureSample.SYM_COMMA_UNDERLINE));
        this.glyphLookup.put('-', new Glyph(FontTextureSample.SYM_MINUS, FontTextureSample.SYM_MINUS_UNDERLINE));
        this.glyphLookup.put('.', new Glyph(FontTextureSample.SYM_PERIOD, FontTextureSample.SYM_PERIOD_UNDERLINE));
        this.glyphLookup.put('/', new Glyph(FontTextureSample.SYM_SLASH, FontTextureSample.SYM_SLASH_UNDERLINE));

        this.glyphLookup.put('0', new Glyph(FontTextureSample.DIGIT_0, FontTextureSample.DIGIT_0_UNDERLINE));
        this.glyphLookup.put('1', new Glyph(FontTextureSample.DIGIT_1, FontTextureSample.DIGIT_1_UNDERLINE));
        this.glyphLookup.put('2', new Glyph(FontTextureSample.DIGIT_2, FontTextureSample.DIGIT_2_UNDERLINE));
        this.glyphLookup.put('3', new Glyph(FontTextureSample.DIGIT_3, FontTextureSample.DIGIT_3_UNDERLINE));
        this.glyphLookup.put('4', new Glyph(FontTextureSample.DIGIT_4, FontTextureSample.DIGIT_4_UNDERLINE));
        this.glyphLookup.put('5', new Glyph(FontTextureSample.DIGIT_5, FontTextureSample.DIGIT_5_UNDERLINE));
        this.glyphLookup.put('6', new Glyph(FontTextureSample.DIGIT_6, FontTextureSample.DIGIT_6_UNDERLINE));
        this.glyphLookup.put('7', new Glyph(FontTextureSample.DIGIT_7, FontTextureSample.DIGIT_7_UNDERLINE));
        this.glyphLookup.put('8', new Glyph(FontTextureSample.DIGIT_8, FontTextureSample.DIGIT_8_UNDERLINE));
        this.glyphLookup.put('9', new Glyph(FontTextureSample.DIGIT_9, FontTextureSample.DIGIT_9_UNDERLINE));
        this.glyphLookup.put(':', new Glyph(FontTextureSample.SYM_COLON, FontTextureSample.SYM_COLON_UNDERLINE));
        this.glyphLookup.put(';', new Glyph(FontTextureSample.SYM_SEMICOLON, FontTextureSample.SYM_SEMICOLON_UNDERLINE));
        this.glyphLookup.put('<', new Glyph(FontTextureSample.SYM_LESS_THAN, FontTextureSample.SYM_LESS_THAN_UNDERLINE));
        this.glyphLookup.put('=', new Glyph(FontTextureSample.SYM_EQUALS, FontTextureSample.SYM_EQUALS_UNDERLINE));
        this.glyphLookup.put('>', new Glyph(FontTextureSample.SYM_GREATER_THAN, FontTextureSample.SYM_GREATER_THAN_UNDERLINE));
        this.glyphLookup.put('?', new Glyph(FontTextureSample.SYM_QUESTION_MARK, FontTextureSample.SYM_QUESTION_MARK_UNDERLINE));

        this.glyphLookup.put('@', new Glyph(FontTextureSample.SYM_AT, FontTextureSample.SYM_AT_UNDERLINE));
        this.glyphLookup.put('A', new Glyph(FontTextureSample.LETTER_UPPERCASE_A, FontTextureSample.LETTER_UPPERCASE_A_UNDERLINE));
        this.glyphLookup.put('B', new Glyph(FontTextureSample.LETTER_UPPERCASE_B, FontTextureSample.LETTER_UPPERCASE_B_UNDERLINE));
        this.glyphLookup.put('C', new Glyph(FontTextureSample.LETTER_UPPERCASE_C, FontTextureSample.LETTER_UPPERCASE_C_UNDERLINE));
        this.glyphLookup.put('D', new Glyph(FontTextureSample.LETTER_UPPERCASE_D, FontTextureSample.LETTER_UPPERCASE_D_UNDERLINE));
        this.glyphLookup.put('E', new Glyph(FontTextureSample.LETTER_UPPERCASE_E, FontTextureSample.LETTER_UPPERCASE_E_UNDERLINE));
        this.glyphLookup.put('F', new Glyph(FontTextureSample.LETTER_UPPERCASE_F, FontTextureSample.LETTER_UPPERCASE_F_UNDERLINE));
        this.glyphLookup.put('G', new Glyph(FontTextureSample.LETTER_UPPERCASE_G, FontTextureSample.LETTER_UPPERCASE_G_UNDERLINE));
        this.glyphLookup.put('H', new Glyph(FontTextureSample.LETTER_UPPERCASE_H, FontTextureSample.LETTER_UPPERCASE_H_UNDERLINE));
        this.glyphLookup.put('I', new Glyph(FontTextureSample.LETTER_UPPERCASE_I, FontTextureSample.LETTER_UPPERCASE_I_UNDERLINE));
        this.glyphLookup.put('J', new Glyph(FontTextureSample.LETTER_UPPERCASE_J, FontTextureSample.LETTER_UPPERCASE_J_UNDERLINE));
        this.glyphLookup.put('K', new Glyph(FontTextureSample.LETTER_UPPERCASE_K, FontTextureSample.LETTER_UPPERCASE_K_UNDERLINE));
        this.glyphLookup.put('L', new Glyph(FontTextureSample.LETTER_UPPERCASE_L, FontTextureSample.LETTER_UPPERCASE_L_UNDERLINE));
        this.glyphLookup.put('M', new Glyph(FontTextureSample.LETTER_UPPERCASE_M, FontTextureSample.LETTER_UPPERCASE_M_UNDERLINE));
        this.glyphLookup.put('N', new Glyph(FontTextureSample.LETTER_UPPERCASE_N, FontTextureSample.LETTER_UPPERCASE_N_UNDERLINE));
        this.glyphLookup.put('O', new Glyph(FontTextureSample.LETTER_UPPERCASE_O, FontTextureSample.LETTER_UPPERCASE_O_UNDERLINE));

        this.glyphLookup.put('P', new Glyph(FontTextureSample.LETTER_UPPERCASE_P, FontTextureSample.LETTER_UPPERCASE_P_UNDERLINE));
        this.glyphLookup.put('Q', new Glyph(FontTextureSample.LETTER_UPPERCASE_Q, FontTextureSample.LETTER_UPPERCASE_Q_UNDERLINE));
        this.glyphLookup.put('R', new Glyph(FontTextureSample.LETTER_UPPERCASE_R, FontTextureSample.LETTER_UPPERCASE_R_UNDERLINE));
        this.glyphLookup.put('S', new Glyph(FontTextureSample.LETTER_UPPERCASE_S, FontTextureSample.LETTER_UPPERCASE_S_UNDERLINE));
        this.glyphLookup.put('T', new Glyph(FontTextureSample.LETTER_UPPERCASE_T, FontTextureSample.LETTER_UPPERCASE_T_UNDERLINE));
        this.glyphLookup.put('U', new Glyph(FontTextureSample.LETTER_UPPERCASE_U, FontTextureSample.LETTER_UPPERCASE_U_UNDERLINE));
        this.glyphLookup.put('V', new Glyph(FontTextureSample.LETTER_UPPERCASE_V, FontTextureSample.LETTER_UPPERCASE_V_UNDERLINE));
        this.glyphLookup.put('W', new Glyph(FontTextureSample.LETTER_UPPERCASE_W, FontTextureSample.LETTER_UPPERCASE_W_UNDERLINE));
        this.glyphLookup.put('X', new Glyph(FontTextureSample.LETTER_UPPERCASE_X, FontTextureSample.LETTER_UPPERCASE_X_UNDERLINE));
        this.glyphLookup.put('Y', new Glyph(FontTextureSample.LETTER_UPPERCASE_Y, FontTextureSample.LETTER_UPPERCASE_Y_UNDERLINE));
        this.glyphLookup.put('Z', new Glyph(FontTextureSample.LETTER_UPPERCASE_Z, FontTextureSample.LETTER_UPPERCASE_Z_UNDERLINE)); 
        this.glyphLookup.put('[', new Glyph(FontTextureSample.SYM_OPEN_BRACKET, FontTextureSample.SYM_OPEN_BRACKET_UNDERLINE));
        this.glyphLookup.put('\\', new Glyph(FontTextureSample.SYM_BACKSLASH, FontTextureSample.SYM_BACKSLASH_UNDERLINE));
        this.glyphLookup.put(']', new Glyph(FontTextureSample.SYM_CLOSE_BRACKET, FontTextureSample.SYM_CLOSE_BRACKET_UNDERLINE));
        this.glyphLookup.put('^', new Glyph(FontTextureSample.SYM_CARET, FontTextureSample.SYM_CARET_UNDERLINE));
        this.glyphLookup.put('_', new Glyph(FontTextureSample.SYM_UNDERSCORE, FontTextureSample.SYM_UNDERSCORE_UNDERLINE));

        this.glyphLookup.put('`', new Glyph(FontTextureSample.SYM_GRAVE, FontTextureSample.SYM_GRAVE_UNDERLINE));
        this.glyphLookup.put('a', new Glyph(FontTextureSample.LETTER_LOWERCASE_A, FontTextureSample.LETTER_LOWERCASE_A_UNDERLINE));
        this.glyphLookup.put('b', new Glyph(FontTextureSample.LETTER_LOWERCASE_B, FontTextureSample.LETTER_LOWERCASE_B_UNDERLINE));
        this.glyphLookup.put('c', new Glyph(FontTextureSample.LETTER_LOWERCASE_C, FontTextureSample.LETTER_LOWERCASE_C_UNDERLINE));
        this.glyphLookup.put('d', new Glyph(FontTextureSample.LETTER_LOWERCASE_D, FontTextureSample.LETTER_LOWERCASE_D_UNDERLINE));
        this.glyphLookup.put('e', new Glyph(FontTextureSample.LETTER_LOWERCASE_E, FontTextureSample.LETTER_LOWERCASE_E_UNDERLINE));
        this.glyphLookup.put('f', new Glyph(FontTextureSample.LETTER_LOWERCASE_F, FontTextureSample.LETTER_LOWERCASE_F_UNDERLINE));
        this.glyphLookup.put('g', new Glyph(FontTextureSample.LETTER_LOWERCASE_G, FontTextureSample.LETTER_LOWERCASE_G_UNDERLINE));
        this.glyphLookup.put('h', new Glyph(FontTextureSample.LETTER_LOWERCASE_H, FontTextureSample.LETTER_LOWERCASE_H_UNDERLINE));
        this.glyphLookup.put('i', new Glyph(FontTextureSample.LETTER_LOWERCASE_I, FontTextureSample.LETTER_LOWERCASE_I_UNDERLINE));
        this.glyphLookup.put('j', new Glyph(FontTextureSample.LETTER_LOWERCASE_J, FontTextureSample.LETTER_LOWERCASE_J_UNDERLINE));
        this.glyphLookup.put('k', new Glyph(FontTextureSample.LETTER_LOWERCASE_K, FontTextureSample.LETTER_LOWERCASE_K_UNDERLINE));
        this.glyphLookup.put('l', new Glyph(FontTextureSample.LETTER_LOWERCASE_L, FontTextureSample.LETTER_LOWERCASE_L_UNDERLINE));
        this.glyphLookup.put('m', new Glyph(FontTextureSample.LETTER_LOWERCASE_M, FontTextureSample.LETTER_LOWERCASE_M_UNDERLINE));
        this.glyphLookup.put('n', new Glyph(FontTextureSample.LETTER_LOWERCASE_N, FontTextureSample.LETTER_LOWERCASE_N_UNDERLINE));
        this.glyphLookup.put('o', new Glyph(FontTextureSample.LETTER_LOWERCASE_O, FontTextureSample.LETTER_LOWERCASE_O_UNDERLINE));

        this.glyphLookup.put('p', new Glyph(FontTextureSample.LETTER_LOWERCASE_P, FontTextureSample.LETTER_LOWERCASE_P_UNDERLINE));
        this.glyphLookup.put('q', new Glyph(FontTextureSample.LETTER_LOWERCASE_Q, FontTextureSample.LETTER_LOWERCASE_Q_UNDERLINE));
        this.glyphLookup.put('r', new Glyph(FontTextureSample.LETTER_LOWERCASE_R, FontTextureSample.LETTER_LOWERCASE_R_UNDERLINE));
        this.glyphLookup.put('s', new Glyph(FontTextureSample.LETTER_LOWERCASE_S, FontTextureSample.LETTER_LOWERCASE_S_UNDERLINE));
        this.glyphLookup.put('t', new Glyph(FontTextureSample.LETTER_LOWERCASE_T, FontTextureSample.LETTER_LOWERCASE_T_UNDERLINE));
        this.glyphLookup.put('u', new Glyph(FontTextureSample.LETTER_LOWERCASE_U, FontTextureSample.LETTER_LOWERCASE_U_UNDERLINE));
        this.glyphLookup.put('v', new Glyph(FontTextureSample.LETTER_LOWERCASE_V, FontTextureSample.LETTER_LOWERCASE_V_UNDERLINE));
        this.glyphLookup.put('w', new Glyph(FontTextureSample.LETTER_LOWERCASE_W, FontTextureSample.LETTER_LOWERCASE_W_UNDERLINE));
        this.glyphLookup.put('x', new Glyph(FontTextureSample.LETTER_LOWERCASE_X, FontTextureSample.LETTER_LOWERCASE_X_UNDERLINE));
        this.glyphLookup.put('y', new Glyph(FontTextureSample.LETTER_LOWERCASE_Y, FontTextureSample.LETTER_LOWERCASE_Y_UNDERLINE));
        this.glyphLookup.put('z', new Glyph(FontTextureSample.LETTER_LOWERCASE_Z, FontTextureSample.LETTER_LOWERCASE_Z_UNDERLINE));
        this.glyphLookup.put('{', new Glyph(FontTextureSample.SYM_OPEN_BRACE, FontTextureSample.SYM_CLOSE_BRACE));
        this.glyphLookup.put('|', new Glyph(FontTextureSample.SYM_PIPE, FontTextureSample.SYM_PIPE_UNDERLINE));
        this.glyphLookup.put('}', new Glyph(FontTextureSample.SYM_CLOSE_BRACE, FontTextureSample.SYM_CLOSE_BRACE_UNDERLINE));
        this.glyphLookup.put('~', new Glyph(FontTextureSample.SYM_TILDE, FontTextureSample.SYM_TILDE_UNDERLINE));
    }

    public Glyph getGlyph(char character) {
        return this.glyphLookup.get(character);
    }
    
}
