package doors.overlay;

import org.joml.Vector2i;

import doors.graphics.TextureSample;
import doors.graphics.TextureData;

public class OverlayTexture extends TextureData {

    private static String OVERLAY_SRC = "data/texture/overlay.png";
    public static Vector2i DIMENSIONS = new Vector2i(512, 512);
    public static Vector2i TILE_8_16 = new Vector2i(8, 16);
    public static Vector2i TILE_8_8 = new Vector2i(8,8);

    public static OverlayTexture OVERLAY_TEXTURE = new OverlayTexture();

    public static TextureSample SPACE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(0, 0), TILE_8_16);
    public static TextureSample BANG = OVERLAY_TEXTURE.createTextureSample(new Vector2i(8, 0), TILE_8_16);
    public static TextureSample DOUBLE_QUOTES = OVERLAY_TEXTURE.createTextureSample(new Vector2i(16, 0), TILE_8_16);
    public static TextureSample HASH = OVERLAY_TEXTURE.createTextureSample(new Vector2i(24, 0), TILE_8_16);
    public static TextureSample DOLLAR = OVERLAY_TEXTURE.createTextureSample(new Vector2i(32, 0), TILE_8_16);
    public static TextureSample PERCENT = OVERLAY_TEXTURE.createTextureSample(new Vector2i(40, 0), TILE_8_16);
    public static TextureSample AMPERSAND = OVERLAY_TEXTURE.createTextureSample(new Vector2i(48, 0), TILE_8_16);
    public static TextureSample QUOTE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(56, 0), TILE_8_16);
    public static TextureSample LEFT_PARENS = OVERLAY_TEXTURE.createTextureSample(new Vector2i(64, 0), TILE_8_16);
    public static TextureSample RIGHT_PARENS = OVERLAY_TEXTURE.createTextureSample(new Vector2i(72, 0), TILE_8_16);
    public static TextureSample ASTERISK = OVERLAY_TEXTURE.createTextureSample(new Vector2i(80, 0), TILE_8_16);
    public static TextureSample PLUS = OVERLAY_TEXTURE.createTextureSample(new Vector2i(88, 0), TILE_8_16);
    public static TextureSample COMMA = OVERLAY_TEXTURE.createTextureSample(new Vector2i(96, 0), TILE_8_16);
    public static TextureSample DASH = OVERLAY_TEXTURE.createTextureSample(new Vector2i(104, 0), TILE_8_16);
    public static TextureSample PERIOD = OVERLAY_TEXTURE.createTextureSample(new Vector2i(112, 0), TILE_8_16);
    public static TextureSample SLASH = OVERLAY_TEXTURE.createTextureSample(new Vector2i(120, 0), TILE_8_16);

    public static TextureSample UNDERLINE_SPACE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(128, 0), TILE_8_16);
    public static TextureSample UNDERLINE_BANG = OVERLAY_TEXTURE.createTextureSample(new Vector2i(136, 0), TILE_8_16);
    public static TextureSample UNDERLINE_DOUBLEQUOTES = OVERLAY_TEXTURE.createTextureSample(new Vector2i(144, 0), TILE_8_16);
    public static TextureSample UNDERLINE_HASH = OVERLAY_TEXTURE.createTextureSample(new Vector2i(152, 0), TILE_8_16);
    public static TextureSample UNDERLINE_DOLLAR = OVERLAY_TEXTURE.createTextureSample(new Vector2i(160, 0), TILE_8_16);
    public static TextureSample UNDERLINE_PERCENT = OVERLAY_TEXTURE.createTextureSample(new Vector2i(168, 0), TILE_8_16);
    public static TextureSample UNDERLINE_AMPERSAND = OVERLAY_TEXTURE.createTextureSample(new Vector2i(176, 0), TILE_8_16);
    public static TextureSample UNDERLINE_QUOTE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(184, 0), TILE_8_16);
    public static TextureSample UNDERLINE_LEFT_PARENS = OVERLAY_TEXTURE.createTextureSample(new Vector2i(192, 0), TILE_8_16);
    public static TextureSample UNDERLINE_RIGHT_PARENS = OVERLAY_TEXTURE.createTextureSample(new Vector2i(200, 0), TILE_8_16);
    public static TextureSample UNDERLINE_ASTERISK = OVERLAY_TEXTURE.createTextureSample(new Vector2i(208, 0), TILE_8_16);
    public static TextureSample UNDERLINE_PLUS = OVERLAY_TEXTURE.createTextureSample(new Vector2i(216, 0), TILE_8_16);
    public static TextureSample UNDERLINE_COMMA = OVERLAY_TEXTURE.createTextureSample(new Vector2i(224, 0), TILE_8_16);
    public static TextureSample UNDERLINE_DASH = OVERLAY_TEXTURE.createTextureSample(new Vector2i(232, 0), TILE_8_16);
    public static TextureSample UNDERLINE_PERIOD = OVERLAY_TEXTURE.createTextureSample(new Vector2i(240, 0), TILE_8_16);
    public static TextureSample UNDERLINE_SLASH = OVERLAY_TEXTURE.createTextureSample(new Vector2i(248, 0), TILE_8_16);

    public static TextureSample ZERO = OVERLAY_TEXTURE.createTextureSample(new Vector2i(0, 16), TILE_8_16);
    public static TextureSample ONE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(8, 16), TILE_8_16);
    public static TextureSample TWO = OVERLAY_TEXTURE.createTextureSample(new Vector2i(16, 16), TILE_8_16);
    public static TextureSample THREE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(24, 16), TILE_8_16);
    public static TextureSample FOUR = OVERLAY_TEXTURE.createTextureSample(new Vector2i(32, 16), TILE_8_16);
    public static TextureSample FIVE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(40, 16), TILE_8_16);
    public static TextureSample SIX = OVERLAY_TEXTURE.createTextureSample(new Vector2i(48, 16), TILE_8_16);
    public static TextureSample SEVEN = OVERLAY_TEXTURE.createTextureSample(new Vector2i(56, 16), TILE_8_16);
    public static TextureSample EIGHT = OVERLAY_TEXTURE.createTextureSample(new Vector2i(64, 16), TILE_8_16);
    public static TextureSample NINE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(72, 16), TILE_8_16);
    public static TextureSample COLON = OVERLAY_TEXTURE.createTextureSample(new Vector2i(80, 16), TILE_8_16);
    public static TextureSample SEMICOLON = OVERLAY_TEXTURE.createTextureSample(new Vector2i(88, 16), TILE_8_16);
    public static TextureSample LESS_THAN = OVERLAY_TEXTURE.createTextureSample(new Vector2i(96, 16), TILE_8_16);
    public static TextureSample EQUALS = OVERLAY_TEXTURE.createTextureSample(new Vector2i(104, 16), TILE_8_16);
    public static TextureSample GREATER_THAN = OVERLAY_TEXTURE.createTextureSample(new Vector2i(112, 16), TILE_8_16);
    public static TextureSample QUESTION = OVERLAY_TEXTURE.createTextureSample(new Vector2i(120, 16), TILE_8_16);

    public static TextureSample UNDERLINE_ZERO = OVERLAY_TEXTURE.createTextureSample(new Vector2i(128, 16), TILE_8_16);
    public static TextureSample UNDERLINE_ONE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(136, 16), TILE_8_16);
    public static TextureSample UNDERLINE_TWO = OVERLAY_TEXTURE.createTextureSample(new Vector2i(144, 16), TILE_8_16);
    public static TextureSample UNDERLINE_THREE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(152, 16), TILE_8_16);
    public static TextureSample UNDERLINE_FOUR = OVERLAY_TEXTURE.createTextureSample(new Vector2i(160, 16), TILE_8_16);
    public static TextureSample UNDERLINE_FIVE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(168, 16), TILE_8_16);
    public static TextureSample UNDERLINE_SIX = OVERLAY_TEXTURE.createTextureSample(new Vector2i(176, 16), TILE_8_16);
    public static TextureSample UNDERLINE_SEVEN = OVERLAY_TEXTURE.createTextureSample(new Vector2i(184, 16), TILE_8_16);
    public static TextureSample UNDERLINE_EIGHT = OVERLAY_TEXTURE.createTextureSample(new Vector2i(192, 16), TILE_8_16);
    public static TextureSample UNDERLINE_NINE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(200, 16), TILE_8_16);
    public static TextureSample UNDERLINE_COLON = OVERLAY_TEXTURE.createTextureSample(new Vector2i(208, 16), TILE_8_16);
    public static TextureSample UNDERLINE_SEMICOLON = OVERLAY_TEXTURE.createTextureSample(new Vector2i(216, 16), TILE_8_16);
    public static TextureSample UNDERLINE_LESS_THAN = OVERLAY_TEXTURE.createTextureSample(new Vector2i(224, 16), TILE_8_16);
    public static TextureSample UNDERLINE_EQUALS = OVERLAY_TEXTURE.createTextureSample(new Vector2i(232, 16), TILE_8_16);
    public static TextureSample UNDERLINE_GREATER_THAN = OVERLAY_TEXTURE.createTextureSample(new Vector2i(240, 16), TILE_8_16);
    public static TextureSample UNDERLINE_QUESTION = OVERLAY_TEXTURE.createTextureSample(new Vector2i(248, 16), TILE_8_16);

    public static TextureSample AT = OVERLAY_TEXTURE.createTextureSample(new Vector2i(0, 32), TILE_8_16);
    public static TextureSample UPPER_A = OVERLAY_TEXTURE.createTextureSample(new Vector2i(8, 32), TILE_8_16);
    public static TextureSample UPPER_B = OVERLAY_TEXTURE.createTextureSample(new Vector2i(16, 32), TILE_8_16);
    public static TextureSample UPPER_C = OVERLAY_TEXTURE.createTextureSample(new Vector2i(24, 32), TILE_8_16);
    public static TextureSample UPPER_D = OVERLAY_TEXTURE.createTextureSample(new Vector2i(32, 32), TILE_8_16);
    public static TextureSample UPPER_E = OVERLAY_TEXTURE.createTextureSample(new Vector2i(40, 32), TILE_8_16);
    public static TextureSample UPPER_F = OVERLAY_TEXTURE.createTextureSample(new Vector2i(48, 32), TILE_8_16);
    public static TextureSample UPPER_G = OVERLAY_TEXTURE.createTextureSample(new Vector2i(56, 32), TILE_8_16);
    public static TextureSample UPPER_H = OVERLAY_TEXTURE.createTextureSample(new Vector2i(64, 32), TILE_8_16);
    public static TextureSample UPPER_I = OVERLAY_TEXTURE.createTextureSample(new Vector2i(72, 32), TILE_8_16);
    public static TextureSample UPPER_J = OVERLAY_TEXTURE.createTextureSample(new Vector2i(80, 32), TILE_8_16);
    public static TextureSample UPPER_K = OVERLAY_TEXTURE.createTextureSample(new Vector2i(88, 32), TILE_8_16);
    public static TextureSample UPPER_L = OVERLAY_TEXTURE.createTextureSample(new Vector2i(96, 32), TILE_8_16);
    public static TextureSample UPPER_M = OVERLAY_TEXTURE.createTextureSample(new Vector2i(104, 32), TILE_8_16);
    public static TextureSample UPPER_N = OVERLAY_TEXTURE.createTextureSample(new Vector2i(112, 32), TILE_8_16);
    public static TextureSample UPPER_O = OVERLAY_TEXTURE.createTextureSample(new Vector2i(120, 32), TILE_8_16);

    public static TextureSample UNDERLINE_AT = OVERLAY_TEXTURE.createTextureSample(new Vector2i(128, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_A = OVERLAY_TEXTURE.createTextureSample(new Vector2i(136, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_B = OVERLAY_TEXTURE.createTextureSample(new Vector2i(144, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_C = OVERLAY_TEXTURE.createTextureSample(new Vector2i(152, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_D = OVERLAY_TEXTURE.createTextureSample(new Vector2i(160, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_E = OVERLAY_TEXTURE.createTextureSample(new Vector2i(168, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_F = OVERLAY_TEXTURE.createTextureSample(new Vector2i(176, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_G = OVERLAY_TEXTURE.createTextureSample(new Vector2i(184, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_H = OVERLAY_TEXTURE.createTextureSample(new Vector2i(192, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_I = OVERLAY_TEXTURE.createTextureSample(new Vector2i(200, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_J = OVERLAY_TEXTURE.createTextureSample(new Vector2i(208, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_K = OVERLAY_TEXTURE.createTextureSample(new Vector2i(216, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_L = OVERLAY_TEXTURE.createTextureSample(new Vector2i(224, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_M = OVERLAY_TEXTURE.createTextureSample(new Vector2i(232, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_N = OVERLAY_TEXTURE.createTextureSample(new Vector2i(240, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_O = OVERLAY_TEXTURE.createTextureSample(new Vector2i(248, 32), TILE_8_16);

    public static TextureSample UPPER_P = OVERLAY_TEXTURE.createTextureSample(new Vector2i(0, 48), TILE_8_16);
    public static TextureSample UPPER_Q = OVERLAY_TEXTURE.createTextureSample(new Vector2i(8, 48), TILE_8_16);
    public static TextureSample UPPER_R = OVERLAY_TEXTURE.createTextureSample(new Vector2i(16, 48), TILE_8_16);
    public static TextureSample UPPER_S = OVERLAY_TEXTURE.createTextureSample(new Vector2i(24, 48), TILE_8_16);
    public static TextureSample UPPER_T = OVERLAY_TEXTURE.createTextureSample(new Vector2i(32, 48), TILE_8_16);
    public static TextureSample UPPER_U = OVERLAY_TEXTURE.createTextureSample(new Vector2i(40, 48), TILE_8_16);
    public static TextureSample UPPER_V = OVERLAY_TEXTURE.createTextureSample(new Vector2i(48, 48), TILE_8_16);
    public static TextureSample UPPER_W = OVERLAY_TEXTURE.createTextureSample(new Vector2i(56, 48), TILE_8_16);
    public static TextureSample UPPER_X = OVERLAY_TEXTURE.createTextureSample(new Vector2i(64, 48), TILE_8_16);
    public static TextureSample UPPER_Y = OVERLAY_TEXTURE.createTextureSample(new Vector2i(72, 48), TILE_8_16);
    public static TextureSample UPPER_Z = OVERLAY_TEXTURE.createTextureSample(new Vector2i(80, 48), TILE_8_16);
    public static TextureSample LEFT_BRACKET = OVERLAY_TEXTURE.createTextureSample(new Vector2i(88, 48), TILE_8_16);
    public static TextureSample BACKSLASH = OVERLAY_TEXTURE.createTextureSample(new Vector2i(96, 48), TILE_8_16);
    public static TextureSample RIGHT_BRACKET = OVERLAY_TEXTURE.createTextureSample(new Vector2i(104, 48), TILE_8_16);
    public static TextureSample CARET = OVERLAY_TEXTURE.createTextureSample(new Vector2i(112, 48), TILE_8_16);
    public static TextureSample UNDERSCORE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(120, 48), TILE_8_16);

    public static TextureSample UNDERLINE_UPPER_P = OVERLAY_TEXTURE.createTextureSample(new Vector2i(128, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_Q = OVERLAY_TEXTURE.createTextureSample(new Vector2i(136, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_R = OVERLAY_TEXTURE.createTextureSample(new Vector2i(144, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_S = OVERLAY_TEXTURE.createTextureSample(new Vector2i(152, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_T = OVERLAY_TEXTURE.createTextureSample(new Vector2i(160, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_U = OVERLAY_TEXTURE.createTextureSample(new Vector2i(168, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_V = OVERLAY_TEXTURE.createTextureSample(new Vector2i(176, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_W = OVERLAY_TEXTURE.createTextureSample(new Vector2i(184, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_X = OVERLAY_TEXTURE.createTextureSample(new Vector2i(192, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_Y = OVERLAY_TEXTURE.createTextureSample(new Vector2i(200, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_Z = OVERLAY_TEXTURE.createTextureSample(new Vector2i(208, 48), TILE_8_16);
    public static TextureSample UNDERLINE_LEFT_BRACKET = OVERLAY_TEXTURE.createTextureSample(new Vector2i(216, 48), TILE_8_16);
    public static TextureSample UNDERLINE_BACKSLASH = OVERLAY_TEXTURE.createTextureSample(new Vector2i(224, 48), TILE_8_16);
    public static TextureSample UNDERLINE_RIGHT_BRACKET = OVERLAY_TEXTURE.createTextureSample(new Vector2i(232, 48), TILE_8_16);
    public static TextureSample UNDERLINE_CARET = OVERLAY_TEXTURE.createTextureSample(new Vector2i(240, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UNDERSCORE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(248, 48), TILE_8_16);

    public static TextureSample BACKTICK = OVERLAY_TEXTURE.createTextureSample(new Vector2i(0, 64), TILE_8_16);
    public static TextureSample LOWER_A = OVERLAY_TEXTURE.createTextureSample(new Vector2i(8, 64), TILE_8_16);
    public static TextureSample LOWER_B = OVERLAY_TEXTURE.createTextureSample(new Vector2i(16, 64), TILE_8_16);
    public static TextureSample LOWER_C = OVERLAY_TEXTURE.createTextureSample(new Vector2i(24, 64), TILE_8_16);
    public static TextureSample LOWER_D = OVERLAY_TEXTURE.createTextureSample(new Vector2i(32, 64), TILE_8_16);
    public static TextureSample LOWER_E = OVERLAY_TEXTURE.createTextureSample(new Vector2i(40, 64), TILE_8_16);
    public static TextureSample LOWER_F = OVERLAY_TEXTURE.createTextureSample(new Vector2i(48, 64), TILE_8_16);
    public static TextureSample LOWER_G = OVERLAY_TEXTURE.createTextureSample(new Vector2i(56, 64), TILE_8_16);
    public static TextureSample LOWER_H = OVERLAY_TEXTURE.createTextureSample(new Vector2i(64, 64), TILE_8_16);
    public static TextureSample LOWER_I = OVERLAY_TEXTURE.createTextureSample(new Vector2i(72, 64), TILE_8_16);
    public static TextureSample LOWER_J = OVERLAY_TEXTURE.createTextureSample(new Vector2i(80, 64), TILE_8_16);
    public static TextureSample LOWER_K = OVERLAY_TEXTURE.createTextureSample(new Vector2i(88, 64), TILE_8_16);
    public static TextureSample LOWER_L = OVERLAY_TEXTURE.createTextureSample(new Vector2i(96, 64), TILE_8_16);
    public static TextureSample LOWER_M = OVERLAY_TEXTURE.createTextureSample(new Vector2i(104, 64), TILE_8_16);
    public static TextureSample LOWER_N = OVERLAY_TEXTURE.createTextureSample(new Vector2i(112, 64), TILE_8_16);
    public static TextureSample LOWER_O = OVERLAY_TEXTURE.createTextureSample(new Vector2i(120, 64), TILE_8_16);

    public static TextureSample UNDERLINE_BACKTICK = OVERLAY_TEXTURE.createTextureSample(new Vector2i(128, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_A = OVERLAY_TEXTURE.createTextureSample(new Vector2i(136, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_B = OVERLAY_TEXTURE.createTextureSample(new Vector2i(144, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_C = OVERLAY_TEXTURE.createTextureSample(new Vector2i(152, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_D = OVERLAY_TEXTURE.createTextureSample(new Vector2i(160, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_E = OVERLAY_TEXTURE.createTextureSample(new Vector2i(168, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_F = OVERLAY_TEXTURE.createTextureSample(new Vector2i(176, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_G = OVERLAY_TEXTURE.createTextureSample(new Vector2i(184, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_H = OVERLAY_TEXTURE.createTextureSample(new Vector2i(192, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_I = OVERLAY_TEXTURE.createTextureSample(new Vector2i(200, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_J = OVERLAY_TEXTURE.createTextureSample(new Vector2i(208, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_K = OVERLAY_TEXTURE.createTextureSample(new Vector2i(216, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_L = OVERLAY_TEXTURE.createTextureSample(new Vector2i(224, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_M = OVERLAY_TEXTURE.createTextureSample(new Vector2i(232, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_N = OVERLAY_TEXTURE.createTextureSample(new Vector2i(240, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_O = OVERLAY_TEXTURE.createTextureSample(new Vector2i(248, 64), TILE_8_16);

    public static TextureSample LOWER_P = OVERLAY_TEXTURE.createTextureSample(new Vector2i(0, 80), TILE_8_16);
    public static TextureSample LOWER_Q = OVERLAY_TEXTURE.createTextureSample(new Vector2i(8, 80), TILE_8_16);
    public static TextureSample LOWER_R = OVERLAY_TEXTURE.createTextureSample(new Vector2i(16, 80), TILE_8_16);
    public static TextureSample LOWER_S = OVERLAY_TEXTURE.createTextureSample(new Vector2i(24, 80), TILE_8_16);
    public static TextureSample LOWER_T = OVERLAY_TEXTURE.createTextureSample(new Vector2i(32, 80), TILE_8_16);
    public static TextureSample LOWER_U = OVERLAY_TEXTURE.createTextureSample(new Vector2i(40, 80), TILE_8_16);
    public static TextureSample LOWER_V = OVERLAY_TEXTURE.createTextureSample(new Vector2i(48, 80), TILE_8_16);
    public static TextureSample LOWER_W = OVERLAY_TEXTURE.createTextureSample(new Vector2i(56, 80), TILE_8_16);
    public static TextureSample LOWER_X = OVERLAY_TEXTURE.createTextureSample(new Vector2i(64, 80), TILE_8_16);
    public static TextureSample LOWER_Y = OVERLAY_TEXTURE.createTextureSample(new Vector2i(72, 80), TILE_8_16);
    public static TextureSample LOWER_Z = OVERLAY_TEXTURE.createTextureSample(new Vector2i(80, 80), TILE_8_16);
    public static TextureSample LEFT_BRACE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(88, 80), TILE_8_16);
    public static TextureSample PIPE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(96, 80), TILE_8_16);
    public static TextureSample RIGHT_BRACE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(104, 80), TILE_8_16);
    public static TextureSample TILDE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(112, 80), TILE_8_16);

    public static TextureSample UNDERLINE_LOWER_P = OVERLAY_TEXTURE.createTextureSample(new Vector2i(128, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_Q = OVERLAY_TEXTURE.createTextureSample(new Vector2i(136, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_R = OVERLAY_TEXTURE.createTextureSample(new Vector2i(144, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_S = OVERLAY_TEXTURE.createTextureSample(new Vector2i(152, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_T = OVERLAY_TEXTURE.createTextureSample(new Vector2i(160, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_U = OVERLAY_TEXTURE.createTextureSample(new Vector2i(168, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_V = OVERLAY_TEXTURE.createTextureSample(new Vector2i(176, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_W = OVERLAY_TEXTURE.createTextureSample(new Vector2i(184, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_X = OVERLAY_TEXTURE.createTextureSample(new Vector2i(192, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_Y = OVERLAY_TEXTURE.createTextureSample(new Vector2i(200, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_Z = OVERLAY_TEXTURE.createTextureSample(new Vector2i(208, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LEFT_BRACE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(216, 80), TILE_8_16);
    public static TextureSample UNDERLINE_PIPE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(224, 80), TILE_8_16);
    public static TextureSample UNDERLINE_RIGHT_BRACE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(232, 80), TILE_8_16);
    public static TextureSample UNDERLINE_TILDE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(240, 80), TILE_8_16);

    public static TextureSample SOLID = OVERLAY_TEXTURE.createTextureSample(new Vector2i(8, 96), TILE_8_8);
    public static TextureSample RETICULE = OVERLAY_TEXTURE.createTextureSample(new Vector2i(16, 96), TILE_8_8);

    public OverlayTexture() {
        super(DIMENSIONS);
    }

    public void setup() {
        this.setup(OVERLAY_SRC);
    }
}
