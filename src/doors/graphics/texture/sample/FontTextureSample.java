package doors.graphics.texture.sample;

import doors.graphics.texture.channel.FontTextureChannel;
import doors.utility.vector.Vector2in;

public class FontTextureSample extends TextureSample {

    private static Vector2in CHARACTER_DIMENSIONS = new Vector2in(8, 16);

    public static FontTextureSample SYM_SPACE = new FontTextureSample(new Vector2in(0, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_SPACE_UNDERLINE = new FontTextureSample(new Vector2in(16, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_BANG = new FontTextureSample(new Vector2in(1, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_BANG_UNDERLINE = new FontTextureSample(new Vector2in(17, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_DOUBLE_QUOTES = new FontTextureSample(new Vector2in(2, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_DOUBLE_QUOTES_UNDERLINE = new FontTextureSample(new Vector2in(18, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_HASH = new FontTextureSample(new Vector2in(3, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_HASH_UNDERLINE = new FontTextureSample(new Vector2in(19, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_DOLLAR = new FontTextureSample(new Vector2in(4, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_DOLLAR_UNDERLINE = new FontTextureSample(new Vector2in(20, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_PERCENT = new FontTextureSample(new Vector2in(5, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_PERCENT_UNDERLINE = new FontTextureSample(new Vector2in(21, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_AMPERSAND = new FontTextureSample(new Vector2in(6, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_AMPERSAND_UNDERLINE = new FontTextureSample(new Vector2in(22, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_SINGLE_QUOTE = new FontTextureSample(new Vector2in(7, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_SINGLE_QUOTE_UNDERLINE = new FontTextureSample(new Vector2in(23, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_OPEN_PAREN = new FontTextureSample(new Vector2in(8, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_OPEN_PAREN_UNDERLINE = new FontTextureSample(new Vector2in(24, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_CLOSE_PAREN = new FontTextureSample(new Vector2in(9, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_CLOSE_PAREN_UNDERLINE = new FontTextureSample(new Vector2in(25, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_STAR = new FontTextureSample(new Vector2in(10, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_STAR_UNDERLINE = new FontTextureSample(new Vector2in(26, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_PLUS = new FontTextureSample(new Vector2in(11, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_PLUS_UNDERLINE = new FontTextureSample(new Vector2in(27, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_COMMA = new FontTextureSample(new Vector2in(12, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_COMMA_UNDERLINE = new FontTextureSample(new Vector2in(28, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_MINUS = new FontTextureSample(new Vector2in(13, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_MINUS_UNDERLINE = new FontTextureSample(new Vector2in(29, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_PERIOD = new FontTextureSample(new Vector2in(14, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_PERIOD_UNDERLINE = new FontTextureSample(new Vector2in(30, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_SLASH = new FontTextureSample(new Vector2in(15, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_SLASH_UNDERLINE = new FontTextureSample(new Vector2in(31, 0).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample DIGIT_0 = new FontTextureSample(new Vector2in(0, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample DIGIT_0_UNDERLINE = new FontTextureSample(new Vector2in(16, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample DIGIT_1 = new FontTextureSample(new Vector2in(1, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample DIGIT_1_UNDERLINE = new FontTextureSample(new Vector2in(17, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample DIGIT_2 = new FontTextureSample(new Vector2in(2, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample DIGIT_2_UNDERLINE = new FontTextureSample(new Vector2in(18, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample DIGIT_3 = new FontTextureSample(new Vector2in(3, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample DIGIT_3_UNDERLINE = new FontTextureSample(new Vector2in(19, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample DIGIT_4 = new FontTextureSample(new Vector2in(4, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample DIGIT_4_UNDERLINE = new FontTextureSample(new Vector2in(20, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample DIGIT_5 = new FontTextureSample(new Vector2in(5, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample DIGIT_5_UNDERLINE = new FontTextureSample(new Vector2in(21, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample DIGIT_6 = new FontTextureSample(new Vector2in(6, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample DIGIT_6_UNDERLINE = new FontTextureSample(new Vector2in(22, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample DIGIT_7 = new FontTextureSample(new Vector2in(7, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample DIGIT_7_UNDERLINE = new FontTextureSample(new Vector2in(23, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample DIGIT_8 = new FontTextureSample(new Vector2in(8, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample DIGIT_8_UNDERLINE = new FontTextureSample(new Vector2in(24, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample DIGIT_9 = new FontTextureSample(new Vector2in(9, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample DIGIT_9_UNDERLINE = new FontTextureSample(new Vector2in(25, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_COLON = new FontTextureSample(new Vector2in(10, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_COLON_UNDERLINE = new FontTextureSample(new Vector2in(26, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_SEMICOLON = new FontTextureSample(new Vector2in(11, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_SEMICOLON_UNDERLINE = new FontTextureSample(new Vector2in(27, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_LESS_THAN = new FontTextureSample(new Vector2in(12, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_LESS_THAN_UNDERLINE = new FontTextureSample(new Vector2in(28, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_EQUALS = new FontTextureSample(new Vector2in(13, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_EQUALS_UNDERLINE = new FontTextureSample(new Vector2in(29, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_GREATER_THAN = new FontTextureSample(new Vector2in(14, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_GREATER_THAN_UNDERLINE = new FontTextureSample(new Vector2in(30, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_QUESTION_MARK = new FontTextureSample(new Vector2in(15, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_QUESTION_MARK_UNDERLINE = new FontTextureSample(new Vector2in(31, 1).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_AT = new FontTextureSample(new Vector2in(0, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_AT_UNDERLINE = new FontTextureSample(new Vector2in(16, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_A = new FontTextureSample(new Vector2in(1, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_A_UNDERLINE = new FontTextureSample(new Vector2in(17, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_B = new FontTextureSample(new Vector2in(2, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_B_UNDERLINE = new FontTextureSample(new Vector2in(18, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_C = new FontTextureSample(new Vector2in(3, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_C_UNDERLINE = new FontTextureSample(new Vector2in(19, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_D = new FontTextureSample(new Vector2in(4, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_D_UNDERLINE = new FontTextureSample(new Vector2in(20, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_E = new FontTextureSample(new Vector2in(5, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_E_UNDERLINE = new FontTextureSample(new Vector2in(21, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_F = new FontTextureSample(new Vector2in(6, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_F_UNDERLINE = new FontTextureSample(new Vector2in(22, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_G = new FontTextureSample(new Vector2in(7, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_G_UNDERLINE = new FontTextureSample(new Vector2in(23, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_H = new FontTextureSample(new Vector2in(8, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_H_UNDERLINE = new FontTextureSample(new Vector2in(24, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_I = new FontTextureSample(new Vector2in(9, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_I_UNDERLINE = new FontTextureSample(new Vector2in(25, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_J = new FontTextureSample(new Vector2in(10, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_J_UNDERLINE = new FontTextureSample(new Vector2in(26, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_K = new FontTextureSample(new Vector2in(11, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_K_UNDERLINE = new FontTextureSample(new Vector2in(27, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_L = new FontTextureSample(new Vector2in(12, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_L_UNDERLINE = new FontTextureSample(new Vector2in(28, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_M = new FontTextureSample(new Vector2in(13, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_M_UNDERLINE = new FontTextureSample(new Vector2in(29, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_N = new FontTextureSample(new Vector2in(14, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_N_UNDERLINE = new FontTextureSample(new Vector2in(30, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_O = new FontTextureSample(new Vector2in(15, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_O_UNDERLINE = new FontTextureSample(new Vector2in(31, 2).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_P = new FontTextureSample(new Vector2in(0, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_P_UNDERLINE = new FontTextureSample(new Vector2in(16, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_Q = new FontTextureSample(new Vector2in(1, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_Q_UNDERLINE = new FontTextureSample(new Vector2in(17, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_R = new FontTextureSample(new Vector2in(2, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_R_UNDERLINE = new FontTextureSample(new Vector2in(18, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_S = new FontTextureSample(new Vector2in(3, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_S_UNDERLINE = new FontTextureSample(new Vector2in(19, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_T = new FontTextureSample(new Vector2in(4, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_T_UNDERLINE = new FontTextureSample(new Vector2in(20, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_U = new FontTextureSample(new Vector2in(5, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_U_UNDERLINE = new FontTextureSample(new Vector2in(21, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    
    public static FontTextureSample LETTER_UPPERCASE_V = new FontTextureSample(new Vector2in(6, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_V_UNDERLINE = new FontTextureSample(new Vector2in(22, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_W = new FontTextureSample(new Vector2in(7, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_W_UNDERLINE = new FontTextureSample(new Vector2in(23, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_X = new FontTextureSample(new Vector2in(8, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_X_UNDERLINE = new FontTextureSample(new Vector2in(24, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_Y = new FontTextureSample(new Vector2in(9, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_Y_UNDERLINE = new FontTextureSample(new Vector2in(25, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_UPPERCASE_Z = new FontTextureSample(new Vector2in(10, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_UPPERCASE_Z_UNDERLINE = new FontTextureSample(new Vector2in(26, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_OPEN_BRACKET = new FontTextureSample(new Vector2in(11, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_OPEN_BRACKET_UNDERLINE = new FontTextureSample(new Vector2in(27, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_BACKSLASH = new FontTextureSample(new Vector2in(12, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_BACKSLASH_UNDERLINE = new FontTextureSample(new Vector2in(28, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_CLOSE_BRACKET = new FontTextureSample(new Vector2in(13, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_CLOSE_BRACKET_UNDERLINE = new FontTextureSample(new Vector2in(29, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_CARET = new FontTextureSample(new Vector2in(14, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_CARET_UNDERLINE = new FontTextureSample(new Vector2in(30, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_UNDERSCORE = new FontTextureSample(new Vector2in(15, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_UNDERSCORE_UNDERLINE = new FontTextureSample(new Vector2in(31, 3).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_GRAVE = new FontTextureSample(new Vector2in(0, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_GRAVE_UNDERLINE = new FontTextureSample(new Vector2in(16, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_A = new FontTextureSample(new Vector2in(1, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_A_UNDERLINE = new FontTextureSample(new Vector2in(17, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_B = new FontTextureSample(new Vector2in(2, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_B_UNDERLINE = new FontTextureSample(new Vector2in(18, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_C = new FontTextureSample(new Vector2in(3, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_C_UNDERLINE = new FontTextureSample(new Vector2in(19, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_D = new FontTextureSample(new Vector2in(4, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_D_UNDERLINE = new FontTextureSample(new Vector2in(20, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_E = new FontTextureSample(new Vector2in(5, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_E_UNDERLINE = new FontTextureSample(new Vector2in(21, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_F = new FontTextureSample(new Vector2in(6, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_F_UNDERLINE = new FontTextureSample(new Vector2in(22, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_G = new FontTextureSample(new Vector2in(7, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_G_UNDERLINE = new FontTextureSample(new Vector2in(23, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_H = new FontTextureSample(new Vector2in(8, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_H_UNDERLINE = new FontTextureSample(new Vector2in(24, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_I = new FontTextureSample(new Vector2in(9, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_I_UNDERLINE = new FontTextureSample(new Vector2in(25, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_J = new FontTextureSample(new Vector2in(10, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_J_UNDERLINE = new FontTextureSample(new Vector2in(26, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_K = new FontTextureSample(new Vector2in(11, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_K_UNDERLINE = new FontTextureSample(new Vector2in(27, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_L = new FontTextureSample(new Vector2in(12, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_L_UNDERLINE = new FontTextureSample(new Vector2in(28, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_M = new FontTextureSample(new Vector2in(13, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_M_UNDERLINE = new FontTextureSample(new Vector2in(29, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_N = new FontTextureSample(new Vector2in(14, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_N_UNDERLINE = new FontTextureSample(new Vector2in(30, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_O = new FontTextureSample(new Vector2in(15, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_O_UNDERLINE = new FontTextureSample(new Vector2in(31, 4).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_P = new FontTextureSample(new Vector2in(0, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_P_UNDERLINE = new FontTextureSample(new Vector2in(16, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_Q = new FontTextureSample(new Vector2in(1, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_Q_UNDERLINE = new FontTextureSample(new Vector2in(17, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_R = new FontTextureSample(new Vector2in(2, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_R_UNDERLINE = new FontTextureSample(new Vector2in(18, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_S = new FontTextureSample(new Vector2in(3, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_S_UNDERLINE = new FontTextureSample(new Vector2in(19, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_T = new FontTextureSample(new Vector2in(4, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_T_UNDERLINE = new FontTextureSample(new Vector2in(20, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_U = new FontTextureSample(new Vector2in(5, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_U_UNDERLINE = new FontTextureSample(new Vector2in(21, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_V = new FontTextureSample(new Vector2in(6, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_V_UNDERLINE = new FontTextureSample(new Vector2in(22, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_W = new FontTextureSample(new Vector2in(7, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_W_UNDERLINE = new FontTextureSample(new Vector2in(23, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_X = new FontTextureSample(new Vector2in(8, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_X_UNDERLINE = new FontTextureSample(new Vector2in(24, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_Y = new FontTextureSample(new Vector2in(9, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_Y_UNDERLINE = new FontTextureSample(new Vector2in(25, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample LETTER_LOWERCASE_Z = new FontTextureSample(new Vector2in(10, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample LETTER_LOWERCASE_Z_UNDERLINE = new FontTextureSample(new Vector2in(26, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_OPEN_BRACE = new FontTextureSample(new Vector2in(11, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_OPEN_BRACE_UNDERLINE = new FontTextureSample(new Vector2in(27, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_PIPE = new FontTextureSample(new Vector2in(12, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_PIPE_UNDERLINE = new FontTextureSample(new Vector2in(28, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_CLOSE_BRACE = new FontTextureSample(new Vector2in(13, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_CLOSE_BRACE_UNDERLINE = new FontTextureSample(new Vector2in(29, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public static FontTextureSample SYM_TILDE = new FontTextureSample(new Vector2in(14, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));
    public static FontTextureSample SYM_TILDE_UNDERLINE = new FontTextureSample(new Vector2in(30, 5).mul(CHARACTER_DIMENSIONS), new Vector2in(Vector2in.ONE).mul(CHARACTER_DIMENSIONS));

    public FontTextureSample(Vector2in position, Vector2in dimensions) {
        super(FontTextureChannel.FONT_TEXTURE_CHANNEL, position, dimensions);
    }
    
}
