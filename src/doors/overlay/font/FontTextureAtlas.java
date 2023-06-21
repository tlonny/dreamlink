package doors.overlay.font;

import doors.graphics.texture.TextureSample;
import doors.graphics.texture.TextureSampler;
import doors.utility.geometry.Vector2in;

public class FontTextureAtlas {

    public static Vector2in CHARACTER_DIMENSIONS = new Vector2in(8, 16);
    private static Vector2in TEXTURE_DIMENSIONS = new Vector2in(256, 96);
    private static TextureSampler SAMPLER = new TextureSampler(TEXTURE_DIMENSIONS, CHARACTER_DIMENSIONS);

    public static TextureSample SPACE = SAMPLER.createTextureSample(new Vector2in(0, 0));
    public static TextureSample SYM_BANG = SAMPLER.createTextureSample(new Vector2in(1, 0));
    public static TextureSample SYM_DOUBLE_QUOTES = SAMPLER.createTextureSample(new Vector2in(2, 0));
    public static TextureSample SYM_HASH = SAMPLER.createTextureSample(new Vector2in(3, 0));
    public static TextureSample SYM_DOLLAR = SAMPLER.createTextureSample(new Vector2in(4, 0));
    public static TextureSample SYM_PERCENT = SAMPLER.createTextureSample(new Vector2in(5, 0));
    public static TextureSample SYM_AMPERSAND = SAMPLER.createTextureSample(new Vector2in(6, 0));
    public static TextureSample SYM_APOSTROPHE = SAMPLER.createTextureSample(new Vector2in(7, 0));
    public static TextureSample SYM_LEFT_PARENS = SAMPLER.createTextureSample(new Vector2in(8, 0));
    public static TextureSample SYM_RIGHT_PARENS = SAMPLER.createTextureSample(new Vector2in(9, 0));
    public static TextureSample SYM_ASTERISK = SAMPLER.createTextureSample(new Vector2in(10, 0));
    public static TextureSample SYM_PLUS = SAMPLER.createTextureSample(new Vector2in(11, 0));
    public static TextureSample SYM_COMMA = SAMPLER.createTextureSample(new Vector2in(12, 0));
    public static TextureSample SYM_MINUS = SAMPLER.createTextureSample(new Vector2in(13, 0));
    public static TextureSample SYM_PERIOD = SAMPLER.createTextureSample(new Vector2in(14, 0));
    public static TextureSample SYM_SLASH = SAMPLER.createTextureSample(new Vector2in(15, 0));

    public static TextureSample UNDERLINE_SPACE = SAMPLER.createTextureSample(new Vector2in(16, 0));
    public static TextureSample UNDERLINE_SYM_BANG = SAMPLER.createTextureSample(new Vector2in(17, 0));
    public static TextureSample UNDERLINE_SYM_DOUBLE_QUOTES = SAMPLER.createTextureSample(new Vector2in(18, 0));
    public static TextureSample UNDERLINE_SYM_HASH = SAMPLER.createTextureSample(new Vector2in(19, 0));
    public static TextureSample UNDERLINE_SYM_DOLLAR = SAMPLER.createTextureSample(new Vector2in(20, 0));
    public static TextureSample UNDERLINE_SYM_PERCENT = SAMPLER.createTextureSample(new Vector2in(21, 0));
    public static TextureSample UNDERLINE_SYM_AMPERSAND = SAMPLER.createTextureSample(new Vector2in(22, 0));
    public static TextureSample UNDERLINE_SYM_APOSTROPHE = SAMPLER.createTextureSample(new Vector2in(23, 0));
    public static TextureSample UNDERLINE_SYM_LEFT_PARENS = SAMPLER.createTextureSample(new Vector2in(24, 0));
    public static TextureSample UNDERLINE_SYM_RIGHT_PARENS = SAMPLER.createTextureSample(new Vector2in(25, 0));
    public static TextureSample UNDERLINE_SYM_ASTERISK = SAMPLER.createTextureSample(new Vector2in(26, 0));
    public static TextureSample UNDERLINE_SYM_PLUS = SAMPLER.createTextureSample(new Vector2in(27, 0));
    public static TextureSample UNDERLINE_SYM_COMMA = SAMPLER.createTextureSample(new Vector2in(28, 0));
    public static TextureSample UNDERLINE_SYM_MINUS = SAMPLER.createTextureSample(new Vector2in(29, 0));
    public static TextureSample UNDERLINE_SYM_PERIOD = SAMPLER.createTextureSample(new Vector2in(30, 0));
    public static TextureSample UNDERLINE_SYM_SLASH = SAMPLER.createTextureSample(new Vector2in(31, 0));

    public static TextureSample NUM_0 = SAMPLER.createTextureSample(new Vector2in(0, 1));
    public static TextureSample NUM_1 = SAMPLER.createTextureSample(new Vector2in(1, 1));
    public static TextureSample NUM_2 = SAMPLER.createTextureSample(new Vector2in(2, 1));
    public static TextureSample NUM_3 = SAMPLER.createTextureSample(new Vector2in(3, 1));
    public static TextureSample NUM_4 = SAMPLER.createTextureSample(new Vector2in(4, 1));
    public static TextureSample NUM_5 = SAMPLER.createTextureSample(new Vector2in(5, 1));
    public static TextureSample NUM_6 = SAMPLER.createTextureSample(new Vector2in(6, 1));
    public static TextureSample NUM_7 = SAMPLER.createTextureSample(new Vector2in(7, 1));
    public static TextureSample NUM_8 = SAMPLER.createTextureSample(new Vector2in(8, 1));
    public static TextureSample NUM_9 = SAMPLER.createTextureSample(new Vector2in(9, 1));
    public static TextureSample SYM_COLON = SAMPLER.createTextureSample(new Vector2in(10, 1));
    public static TextureSample SYM_SEMICOLON = SAMPLER.createTextureSample(new Vector2in(11, 1));
    public static TextureSample SYM_LESS_THAN = SAMPLER.createTextureSample(new Vector2in(12 ,1));
    public static TextureSample SYM_EQUAL = SAMPLER.createTextureSample(new Vector2in(13, 1));
    public static TextureSample SYM_GREATER_THAN = SAMPLER.createTextureSample(new Vector2in(14, 1));
    public static TextureSample SYM_QUESTION = SAMPLER.createTextureSample(new Vector2in(15, 1));

    public static TextureSample UNDERLINE_NUM_0 = SAMPLER.createTextureSample(new Vector2in(16, 1));
    public static TextureSample UNDERLINE_NUM_1 = SAMPLER.createTextureSample(new Vector2in(17, 1));
    public static TextureSample UNDERLINE_NUM_2 = SAMPLER.createTextureSample(new Vector2in(18, 1));
    public static TextureSample UNDERLINE_NUM_3 = SAMPLER.createTextureSample(new Vector2in(19, 1));
    public static TextureSample UNDERLINE_NUM_4 = SAMPLER.createTextureSample(new Vector2in(20, 1));
    public static TextureSample UNDERLINE_NUM_5 = SAMPLER.createTextureSample(new Vector2in(21, 1));
    public static TextureSample UNDERLINE_NUM_6 = SAMPLER.createTextureSample(new Vector2in(22, 1));
    public static TextureSample UNDERLINE_NUM_7 = SAMPLER.createTextureSample(new Vector2in(23, 1));
    public static TextureSample UNDERLINE_NUM_8 = SAMPLER.createTextureSample(new Vector2in(24, 1));
    public static TextureSample UNDERLINE_NUM_9 = SAMPLER.createTextureSample(new Vector2in(25, 1));
    public static TextureSample UNDERLINE_SYM_COLON = SAMPLER.createTextureSample(new Vector2in(26, 1));
    public static TextureSample UNDERLINE_SYM_SEMICOLON = SAMPLER.createTextureSample(new Vector2in(27, 1));
    public static TextureSample UNDERLINE_SYM_LESS_THAN = SAMPLER.createTextureSample(new Vector2in(28 ,1));
    public static TextureSample UNDERLINE_SYM_EQUAL = SAMPLER.createTextureSample(new Vector2in(29, 1));
    public static TextureSample UNDERLINE_SYM_GREATER_THAN = SAMPLER.createTextureSample(new Vector2in(30, 1));
    public static TextureSample UNDERLINE_SYM_QUESTION = SAMPLER.createTextureSample(new Vector2in(31, 1));

    public static TextureSample SYM_AT = SAMPLER.createTextureSample(new Vector2in(0, 2));
    public static TextureSample CHAR_UPPER_A = SAMPLER.createTextureSample(new Vector2in(1, 2));
    public static TextureSample CHAR_UPPER_B = SAMPLER.createTextureSample(new Vector2in(2, 2));
    public static TextureSample CHAR_UPPER_C = SAMPLER.createTextureSample(new Vector2in(3, 2));
    public static TextureSample CHAR_UPPER_D = SAMPLER.createTextureSample(new Vector2in(4, 2));
    public static TextureSample CHAR_UPPER_E = SAMPLER.createTextureSample(new Vector2in(5, 2));
    public static TextureSample CHAR_UPPER_F = SAMPLER.createTextureSample(new Vector2in(6, 2));
    public static TextureSample CHAR_UPPER_G = SAMPLER.createTextureSample(new Vector2in(7, 2));
    public static TextureSample CHAR_UPPER_H = SAMPLER.createTextureSample(new Vector2in(8, 2));
    public static TextureSample CHAR_UPPER_I = SAMPLER.createTextureSample(new Vector2in(9, 2));
    public static TextureSample CHAR_UPPER_J = SAMPLER.createTextureSample(new Vector2in(10, 2));
    public static TextureSample CHAR_UPPER_K = SAMPLER.createTextureSample(new Vector2in(11, 2));
    public static TextureSample CHAR_UPPER_L = SAMPLER.createTextureSample(new Vector2in(12, 2));
    public static TextureSample CHAR_UPPER_M = SAMPLER.createTextureSample(new Vector2in(13, 2));
    public static TextureSample CHAR_UPPER_N = SAMPLER.createTextureSample(new Vector2in(14, 2));
    public static TextureSample CHAR_UPPER_O = SAMPLER.createTextureSample(new Vector2in(15, 2));

    public static TextureSample UNDERLINE_SYM_AT = SAMPLER.createTextureSample(new Vector2in(16, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_A = SAMPLER.createTextureSample(new Vector2in(17, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_B = SAMPLER.createTextureSample(new Vector2in(18, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_C = SAMPLER.createTextureSample(new Vector2in(19, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_D = SAMPLER.createTextureSample(new Vector2in(20, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_E = SAMPLER.createTextureSample(new Vector2in(21, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_F = SAMPLER.createTextureSample(new Vector2in(22, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_G = SAMPLER.createTextureSample(new Vector2in(23, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_H = SAMPLER.createTextureSample(new Vector2in(24, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_I = SAMPLER.createTextureSample(new Vector2in(25, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_J = SAMPLER.createTextureSample(new Vector2in(26, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_K = SAMPLER.createTextureSample(new Vector2in(27, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_L = SAMPLER.createTextureSample(new Vector2in(28, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_M = SAMPLER.createTextureSample(new Vector2in(29, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_N = SAMPLER.createTextureSample(new Vector2in(30, 2));
    public static TextureSample UNDERLINE_CHAR_UPPER_O = SAMPLER.createTextureSample(new Vector2in(31, 2));

    public static TextureSample CHAR_UPPER_P = SAMPLER.createTextureSample(new Vector2in(0, 3));
    public static TextureSample CHAR_UPPER_Q = SAMPLER.createTextureSample(new Vector2in(1, 3));
    public static TextureSample CHAR_UPPER_R = SAMPLER.createTextureSample(new Vector2in(2, 3));
    public static TextureSample CHAR_UPPER_S = SAMPLER.createTextureSample(new Vector2in(3, 3));
    public static TextureSample CHAR_UPPER_T = SAMPLER.createTextureSample(new Vector2in(4, 3));
    public static TextureSample CHAR_UPPER_U = SAMPLER.createTextureSample(new Vector2in(5, 3));
    public static TextureSample CHAR_UPPER_V = SAMPLER.createTextureSample(new Vector2in(6, 3));
    public static TextureSample CHAR_UPPER_W = SAMPLER.createTextureSample(new Vector2in(7, 3));
    public static TextureSample CHAR_UPPER_X = SAMPLER.createTextureSample(new Vector2in(8, 3));
    public static TextureSample CHAR_UPPER_Y = SAMPLER.createTextureSample(new Vector2in(9, 3));
    public static TextureSample CHAR_UPPER_Z = SAMPLER.createTextureSample(new Vector2in(10, 3));
    public static TextureSample SYM_LEFT_SQUARE_BRACKET = SAMPLER.createTextureSample(new Vector2in(11, 3));
    public static TextureSample SYM_BACKSLASH = SAMPLER.createTextureSample(new Vector2in(12, 3));
    public static TextureSample SYM_RIGHT_SQUARE_BRACKET = SAMPLER.createTextureSample(new Vector2in(13, 3));
    public static TextureSample SYM_CARET = SAMPLER.createTextureSample(new Vector2in(14, 3));
    public static TextureSample SYM_UNDERSCORE = SAMPLER.createTextureSample(new Vector2in(15, 3));
    
    public static TextureSample UNDERLINE_CHAR_UPPER_P = SAMPLER.createTextureSample(new Vector2in(16, 3));
    public static TextureSample UNDERLINE_CHAR_UPPER_Q = SAMPLER.createTextureSample(new Vector2in(17, 3));
    public static TextureSample UNDERLINE_CHAR_UPPER_R = SAMPLER.createTextureSample(new Vector2in(18, 3));
    public static TextureSample UNDERLINE_CHAR_UPPER_S = SAMPLER.createTextureSample(new Vector2in(19, 3));
    public static TextureSample UNDERLINE_CHAR_UPPER_T = SAMPLER.createTextureSample(new Vector2in(20, 3));
    public static TextureSample UNDERLINE_CHAR_UPPER_U = SAMPLER.createTextureSample(new Vector2in(21, 3));
    public static TextureSample UNDERLINE_CHAR_UPPER_V = SAMPLER.createTextureSample(new Vector2in(21, 3));
    public static TextureSample UNDERLINE_CHAR_UPPER_W = SAMPLER.createTextureSample(new Vector2in(22, 3));
    public static TextureSample UNDERLINE_CHAR_UPPER_X = SAMPLER.createTextureSample(new Vector2in(23, 3));
    public static TextureSample UNDERLINE_CHAR_UPPER_Y = SAMPLER.createTextureSample(new Vector2in(24, 3));
    public static TextureSample UNDERLINE_CHAR_UPPER_Z = SAMPLER.createTextureSample(new Vector2in(25, 3));
    public static TextureSample UNDERLINE_SYM_LEFT_SQUARE_BRACKET = SAMPLER.createTextureSample(new Vector2in(26, 3));
    public static TextureSample UNDERLINE_SYM_BACKSLASH = SAMPLER.createTextureSample(new Vector2in(27, 3));
    public static TextureSample UNDERLINE_SYM_RIGHT_SQUARE_BRACKET = SAMPLER.createTextureSample(new Vector2in(28, 3));
    public static TextureSample UNDERLINE_SYM_CARET = SAMPLER.createTextureSample(new Vector2in(29, 3));
    public static TextureSample UNDERLINE_SYM_UNDERSCORE = SAMPLER.createTextureSample(new Vector2in(30, 3));

    public static TextureSample SYM_BACKTICK = SAMPLER.createTextureSample(new Vector2in(0, 4));
    public static TextureSample CHAR_LOWER_A = SAMPLER.createTextureSample(new Vector2in(1, 4));
    public static TextureSample CHAR_LOWER_B = SAMPLER.createTextureSample(new Vector2in(2, 4));
    public static TextureSample CHAR_LOWER_C = SAMPLER.createTextureSample(new Vector2in(3, 4));
    public static TextureSample CHAR_LOWER_D = SAMPLER.createTextureSample(new Vector2in(4, 4));
    public static TextureSample CHAR_LOWER_E = SAMPLER.createTextureSample(new Vector2in(5, 4));
    public static TextureSample CHAR_LOWER_F = SAMPLER.createTextureSample(new Vector2in(6, 4));
    public static TextureSample CHAR_LOWER_G = SAMPLER.createTextureSample(new Vector2in(7, 4));
    public static TextureSample CHAR_LOWER_H = SAMPLER.createTextureSample(new Vector2in(8, 4));
    public static TextureSample CHAR_LOWER_I = SAMPLER.createTextureSample(new Vector2in(9, 4));
    public static TextureSample CHAR_LOWER_J = SAMPLER.createTextureSample(new Vector2in(10, 4));
    public static TextureSample CHAR_LOWER_K = SAMPLER.createTextureSample(new Vector2in(11, 4));
    public static TextureSample CHAR_LOWER_L = SAMPLER.createTextureSample(new Vector2in(12, 4));
    public static TextureSample CHAR_LOWER_M = SAMPLER.createTextureSample(new Vector2in(13, 4));
    public static TextureSample CHAR_LOWER_N = SAMPLER.createTextureSample(new Vector2in(14, 4));
    public static TextureSample CHAR_LOWER_O = SAMPLER.createTextureSample(new Vector2in(15, 4));

    public static TextureSample UNDERLINE_SYM_BACKTICK = SAMPLER.createTextureSample(new Vector2in(16, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_A = SAMPLER.createTextureSample(new Vector2in(17, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_B = SAMPLER.createTextureSample(new Vector2in(18, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_C = SAMPLER.createTextureSample(new Vector2in(19, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_D = SAMPLER.createTextureSample(new Vector2in(20, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_E = SAMPLER.createTextureSample(new Vector2in(21, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_F = SAMPLER.createTextureSample(new Vector2in(22, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_G = SAMPLER.createTextureSample(new Vector2in(23, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_H = SAMPLER.createTextureSample(new Vector2in(24, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_I = SAMPLER.createTextureSample(new Vector2in(25, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_J = SAMPLER.createTextureSample(new Vector2in(26, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_K = SAMPLER.createTextureSample(new Vector2in(27, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_L = SAMPLER.createTextureSample(new Vector2in(28, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_M = SAMPLER.createTextureSample(new Vector2in(29, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_N = SAMPLER.createTextureSample(new Vector2in(30, 4));
    public static TextureSample UNDERLINE_CHAR_LOWER_O = SAMPLER.createTextureSample(new Vector2in(31, 4));

    public static TextureSample CHAR_LOWER_P = SAMPLER.createTextureSample(new Vector2in(0, 5));
    public static TextureSample CHAR_LOWER_Q = SAMPLER.createTextureSample(new Vector2in(1, 5));
    public static TextureSample CHAR_LOWER_R = SAMPLER.createTextureSample(new Vector2in(2, 5));
    public static TextureSample CHAR_LOWER_S = SAMPLER.createTextureSample(new Vector2in(3, 5));
    public static TextureSample CHAR_LOWER_T = SAMPLER.createTextureSample(new Vector2in(4, 5));
    public static TextureSample CHAR_LOWER_U = SAMPLER.createTextureSample(new Vector2in(5, 5));
    public static TextureSample CHAR_LOWER_V = SAMPLER.createTextureSample(new Vector2in(6, 5));
    public static TextureSample CHAR_LOWER_W = SAMPLER.createTextureSample(new Vector2in(7, 5));
    public static TextureSample CHAR_LOWER_X = SAMPLER.createTextureSample(new Vector2in(8, 5));
    public static TextureSample CHAR_LOWER_Y = SAMPLER.createTextureSample(new Vector2in(9, 5));
    public static TextureSample CHAR_LOWER_Z = SAMPLER.createTextureSample(new Vector2in(10, 5));
    public static TextureSample SYM_LEFT_BRACE = SAMPLER.createTextureSample(new Vector2in(11, 5));
    public static TextureSample SYM_PIPE = SAMPLER.createTextureSample(new Vector2in(12, 5));
    public static TextureSample SYM_RIGHT_BRACE = SAMPLER.createTextureSample(new Vector2in(13, 5));
    public static TextureSample SYM_TILDE = SAMPLER.createTextureSample(new Vector2in(14, 5));

    public static TextureSample UNDERLINE_CHAR_LOWER_P = SAMPLER.createTextureSample(new Vector2in(16, 5));
    public static TextureSample UNDERLINE_CHAR_LOWER_Q = SAMPLER.createTextureSample(new Vector2in(17, 5));
    public static TextureSample UNDERLINE_CHAR_LOWER_R = SAMPLER.createTextureSample(new Vector2in(18, 5));
    public static TextureSample UNDERLINE_CHAR_LOWER_S = SAMPLER.createTextureSample(new Vector2in(19, 5));
    public static TextureSample UNDERLINE_CHAR_LOWER_T = SAMPLER.createTextureSample(new Vector2in(20, 5));
    public static TextureSample UNDERLINE_CHAR_LOWER_U = SAMPLER.createTextureSample(new Vector2in(21, 5));
    public static TextureSample UNDERLINE_CHAR_LOWER_V = SAMPLER.createTextureSample(new Vector2in(22, 5));
    public static TextureSample UNDERLINE_CHAR_LOWER_W = SAMPLER.createTextureSample(new Vector2in(23, 5));
    public static TextureSample UNDERLINE_CHAR_LOWER_X = SAMPLER.createTextureSample(new Vector2in(24, 5));
    public static TextureSample UNDERLINE_CHAR_LOWER_Y = SAMPLER.createTextureSample(new Vector2in(25, 5));
    public static TextureSample UNDERLINE_CHAR_LOWER_Z = SAMPLER.createTextureSample(new Vector2in(26, 5));
    public static TextureSample UNDERLINE_SYM_LEFT_BRACE = SAMPLER.createTextureSample(new Vector2in(27, 5));
    public static TextureSample UNDERLINE_SYM_PIPE = SAMPLER.createTextureSample(new Vector2in(28, 5));
    public static TextureSample UNDERLINE_SYM_RIGHT_BRACE = SAMPLER.createTextureSample(new Vector2in(29, 5));
    public static TextureSample UNDERLINE_SYM_TILDE = SAMPLER.createTextureSample(new Vector2in(30, 5));
}

