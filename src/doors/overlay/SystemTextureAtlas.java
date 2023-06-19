package doors.overlay;

import org.joml.Vector2i;

import doors.graphics.TextureChannel;
import doors.graphics.TextureSample;
import doors.graphics.TextureSampler;

public class SystemTextureAtlas {

    private static TextureSampler SAMPLER = new TextureSampler(
        TextureChannel.UI_TEXTURE_CHANNEL, 
        new Vector2i(512, 512)
    );

    public static Vector2i TILE_8_16 = new Vector2i(8, 16);
    public static Vector2i TILE_8_8 = new Vector2i(8,8);

    public static TextureSample SPACE = SAMPLER.createTextureSample(new Vector2i(0, 0), TILE_8_16);
    public static TextureSample BANG = SAMPLER.createTextureSample(new Vector2i(8, 0), TILE_8_16);
    public static TextureSample DOUBLE_QUOTES = SAMPLER.createTextureSample(new Vector2i(16, 0), TILE_8_16);
    public static TextureSample HASH = SAMPLER.createTextureSample(new Vector2i(24, 0), TILE_8_16);
    public static TextureSample DOLLAR = SAMPLER.createTextureSample(new Vector2i(32, 0), TILE_8_16);
    public static TextureSample PERCENT = SAMPLER.createTextureSample(new Vector2i(40, 0), TILE_8_16);
    public static TextureSample AMPERSAND = SAMPLER.createTextureSample(new Vector2i(48, 0), TILE_8_16);
    public static TextureSample QUOTE = SAMPLER.createTextureSample(new Vector2i(56, 0), TILE_8_16);
    public static TextureSample LEFT_PARENS = SAMPLER.createTextureSample(new Vector2i(64, 0), TILE_8_16);
    public static TextureSample RIGHT_PARENS = SAMPLER.createTextureSample(new Vector2i(72, 0), TILE_8_16);
    public static TextureSample ASTERISK = SAMPLER.createTextureSample(new Vector2i(80, 0), TILE_8_16);
    public static TextureSample PLUS = SAMPLER.createTextureSample(new Vector2i(88, 0), TILE_8_16);
    public static TextureSample COMMA = SAMPLER.createTextureSample(new Vector2i(96, 0), TILE_8_16);
    public static TextureSample DASH = SAMPLER.createTextureSample(new Vector2i(104, 0), TILE_8_16);
    public static TextureSample PERIOD = SAMPLER.createTextureSample(new Vector2i(112, 0), TILE_8_16);
    public static TextureSample SLASH = SAMPLER.createTextureSample(new Vector2i(120, 0), TILE_8_16);

    public static TextureSample UNDERLINE_SPACE = SAMPLER.createTextureSample(new Vector2i(128, 0), TILE_8_16);
    public static TextureSample UNDERLINE_BANG = SAMPLER.createTextureSample(new Vector2i(136, 0), TILE_8_16);
    public static TextureSample UNDERLINE_DOUBLEQUOTES = SAMPLER.createTextureSample(new Vector2i(144, 0), TILE_8_16);
    public static TextureSample UNDERLINE_HASH = SAMPLER.createTextureSample(new Vector2i(152, 0), TILE_8_16);
    public static TextureSample UNDERLINE_DOLLAR = SAMPLER.createTextureSample(new Vector2i(160, 0), TILE_8_16);
    public static TextureSample UNDERLINE_PERCENT = SAMPLER.createTextureSample(new Vector2i(168, 0), TILE_8_16);
    public static TextureSample UNDERLINE_AMPERSAND = SAMPLER.createTextureSample(new Vector2i(176, 0), TILE_8_16);
    public static TextureSample UNDERLINE_QUOTE = SAMPLER.createTextureSample(new Vector2i(184, 0), TILE_8_16);
    public static TextureSample UNDERLINE_LEFT_PARENS = SAMPLER.createTextureSample(new Vector2i(192, 0), TILE_8_16);
    public static TextureSample UNDERLINE_RIGHT_PARENS = SAMPLER.createTextureSample(new Vector2i(200, 0), TILE_8_16);
    public static TextureSample UNDERLINE_ASTERISK = SAMPLER.createTextureSample(new Vector2i(208, 0), TILE_8_16);
    public static TextureSample UNDERLINE_PLUS = SAMPLER.createTextureSample(new Vector2i(216, 0), TILE_8_16);
    public static TextureSample UNDERLINE_COMMA = SAMPLER.createTextureSample(new Vector2i(224, 0), TILE_8_16);
    public static TextureSample UNDERLINE_DASH = SAMPLER.createTextureSample(new Vector2i(232, 0), TILE_8_16);
    public static TextureSample UNDERLINE_PERIOD = SAMPLER.createTextureSample(new Vector2i(240, 0), TILE_8_16);
    public static TextureSample UNDERLINE_SLASH = SAMPLER.createTextureSample(new Vector2i(248, 0), TILE_8_16);

    public static TextureSample ZERO = SAMPLER.createTextureSample(new Vector2i(0, 16), TILE_8_16);
    public static TextureSample ONE = SAMPLER.createTextureSample(new Vector2i(8, 16), TILE_8_16);
    public static TextureSample TWO = SAMPLER.createTextureSample(new Vector2i(16, 16), TILE_8_16);
    public static TextureSample THREE = SAMPLER.createTextureSample(new Vector2i(24, 16), TILE_8_16);
    public static TextureSample FOUR = SAMPLER.createTextureSample(new Vector2i(32, 16), TILE_8_16);
    public static TextureSample FIVE = SAMPLER.createTextureSample(new Vector2i(40, 16), TILE_8_16);
    public static TextureSample SIX = SAMPLER.createTextureSample(new Vector2i(48, 16), TILE_8_16);
    public static TextureSample SEVEN = SAMPLER.createTextureSample(new Vector2i(56, 16), TILE_8_16);
    public static TextureSample EIGHT = SAMPLER.createTextureSample(new Vector2i(64, 16), TILE_8_16);
    public static TextureSample NINE = SAMPLER.createTextureSample(new Vector2i(72, 16), TILE_8_16);
    public static TextureSample COLON = SAMPLER.createTextureSample(new Vector2i(80, 16), TILE_8_16);
    public static TextureSample SEMICOLON = SAMPLER.createTextureSample(new Vector2i(88, 16), TILE_8_16);
    public static TextureSample LESS_THAN = SAMPLER.createTextureSample(new Vector2i(96, 16), TILE_8_16);
    public static TextureSample EQUALS = SAMPLER.createTextureSample(new Vector2i(104, 16), TILE_8_16);
    public static TextureSample GREATER_THAN = SAMPLER.createTextureSample(new Vector2i(112, 16), TILE_8_16);
    public static TextureSample QUESTION = SAMPLER.createTextureSample(new Vector2i(120, 16), TILE_8_16);

    public static TextureSample UNDERLINE_ZERO = SAMPLER.createTextureSample(new Vector2i(128, 16), TILE_8_16);
    public static TextureSample UNDERLINE_ONE = SAMPLER.createTextureSample(new Vector2i(136, 16), TILE_8_16);
    public static TextureSample UNDERLINE_TWO = SAMPLER.createTextureSample(new Vector2i(144, 16), TILE_8_16);
    public static TextureSample UNDERLINE_THREE = SAMPLER.createTextureSample(new Vector2i(152, 16), TILE_8_16);
    public static TextureSample UNDERLINE_FOUR = SAMPLER.createTextureSample(new Vector2i(160, 16), TILE_8_16);
    public static TextureSample UNDERLINE_FIVE = SAMPLER.createTextureSample(new Vector2i(168, 16), TILE_8_16);
    public static TextureSample UNDERLINE_SIX = SAMPLER.createTextureSample(new Vector2i(176, 16), TILE_8_16);
    public static TextureSample UNDERLINE_SEVEN = SAMPLER.createTextureSample(new Vector2i(184, 16), TILE_8_16);
    public static TextureSample UNDERLINE_EIGHT = SAMPLER.createTextureSample(new Vector2i(192, 16), TILE_8_16);
    public static TextureSample UNDERLINE_NINE = SAMPLER.createTextureSample(new Vector2i(200, 16), TILE_8_16);
    public static TextureSample UNDERLINE_COLON = SAMPLER.createTextureSample(new Vector2i(208, 16), TILE_8_16);
    public static TextureSample UNDERLINE_SEMICOLON = SAMPLER.createTextureSample(new Vector2i(216, 16), TILE_8_16);
    public static TextureSample UNDERLINE_LESS_THAN = SAMPLER.createTextureSample(new Vector2i(224, 16), TILE_8_16);
    public static TextureSample UNDERLINE_EQUALS = SAMPLER.createTextureSample(new Vector2i(232, 16), TILE_8_16);
    public static TextureSample UNDERLINE_GREATER_THAN = SAMPLER.createTextureSample(new Vector2i(240, 16), TILE_8_16);
    public static TextureSample UNDERLINE_QUESTION = SAMPLER.createTextureSample(new Vector2i(248, 16), TILE_8_16);

    public static TextureSample AT = SAMPLER.createTextureSample(new Vector2i(0, 32), TILE_8_16);
    public static TextureSample UPPER_A = SAMPLER.createTextureSample(new Vector2i(8, 32), TILE_8_16);
    public static TextureSample UPPER_B = SAMPLER.createTextureSample(new Vector2i(16, 32), TILE_8_16);
    public static TextureSample UPPER_C = SAMPLER.createTextureSample(new Vector2i(24, 32), TILE_8_16);
    public static TextureSample UPPER_D = SAMPLER.createTextureSample(new Vector2i(32, 32), TILE_8_16);
    public static TextureSample UPPER_E = SAMPLER.createTextureSample(new Vector2i(40, 32), TILE_8_16);
    public static TextureSample UPPER_F = SAMPLER.createTextureSample(new Vector2i(48, 32), TILE_8_16);
    public static TextureSample UPPER_G = SAMPLER.createTextureSample(new Vector2i(56, 32), TILE_8_16);
    public static TextureSample UPPER_H = SAMPLER.createTextureSample(new Vector2i(64, 32), TILE_8_16);
    public static TextureSample UPPER_I = SAMPLER.createTextureSample(new Vector2i(72, 32), TILE_8_16);
    public static TextureSample UPPER_J = SAMPLER.createTextureSample(new Vector2i(80, 32), TILE_8_16);
    public static TextureSample UPPER_K = SAMPLER.createTextureSample(new Vector2i(88, 32), TILE_8_16);
    public static TextureSample UPPER_L = SAMPLER.createTextureSample(new Vector2i(96, 32), TILE_8_16);
    public static TextureSample UPPER_M = SAMPLER.createTextureSample(new Vector2i(104, 32), TILE_8_16);
    public static TextureSample UPPER_N = SAMPLER.createTextureSample(new Vector2i(112, 32), TILE_8_16);
    public static TextureSample UPPER_O = SAMPLER.createTextureSample(new Vector2i(120, 32), TILE_8_16);

    public static TextureSample UNDERLINE_AT = SAMPLER.createTextureSample(new Vector2i(128, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_A = SAMPLER.createTextureSample(new Vector2i(136, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_B = SAMPLER.createTextureSample(new Vector2i(144, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_C = SAMPLER.createTextureSample(new Vector2i(152, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_D = SAMPLER.createTextureSample(new Vector2i(160, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_E = SAMPLER.createTextureSample(new Vector2i(168, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_F = SAMPLER.createTextureSample(new Vector2i(176, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_G = SAMPLER.createTextureSample(new Vector2i(184, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_H = SAMPLER.createTextureSample(new Vector2i(192, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_I = SAMPLER.createTextureSample(new Vector2i(200, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_J = SAMPLER.createTextureSample(new Vector2i(208, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_K = SAMPLER.createTextureSample(new Vector2i(216, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_L = SAMPLER.createTextureSample(new Vector2i(224, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_M = SAMPLER.createTextureSample(new Vector2i(232, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_N = SAMPLER.createTextureSample(new Vector2i(240, 32), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_O = SAMPLER.createTextureSample(new Vector2i(248, 32), TILE_8_16);

    public static TextureSample UPPER_P = SAMPLER.createTextureSample(new Vector2i(0, 48), TILE_8_16);
    public static TextureSample UPPER_Q = SAMPLER.createTextureSample(new Vector2i(8, 48), TILE_8_16);
    public static TextureSample UPPER_R = SAMPLER.createTextureSample(new Vector2i(16, 48), TILE_8_16);
    public static TextureSample UPPER_S = SAMPLER.createTextureSample(new Vector2i(24, 48), TILE_8_16);
    public static TextureSample UPPER_T = SAMPLER.createTextureSample(new Vector2i(32, 48), TILE_8_16);
    public static TextureSample UPPER_U = SAMPLER.createTextureSample(new Vector2i(40, 48), TILE_8_16);
    public static TextureSample UPPER_V = SAMPLER.createTextureSample(new Vector2i(48, 48), TILE_8_16);
    public static TextureSample UPPER_W = SAMPLER.createTextureSample(new Vector2i(56, 48), TILE_8_16);
    public static TextureSample UPPER_X = SAMPLER.createTextureSample(new Vector2i(64, 48), TILE_8_16);
    public static TextureSample UPPER_Y = SAMPLER.createTextureSample(new Vector2i(72, 48), TILE_8_16);
    public static TextureSample UPPER_Z = SAMPLER.createTextureSample(new Vector2i(80, 48), TILE_8_16);
    public static TextureSample LEFT_BRACKET = SAMPLER.createTextureSample(new Vector2i(88, 48), TILE_8_16);
    public static TextureSample BACKSLASH = SAMPLER.createTextureSample(new Vector2i(96, 48), TILE_8_16);
    public static TextureSample RIGHT_BRACKET = SAMPLER.createTextureSample(new Vector2i(104, 48), TILE_8_16);
    public static TextureSample CARET = SAMPLER.createTextureSample(new Vector2i(112, 48), TILE_8_16);
    public static TextureSample UNDERSCORE = SAMPLER.createTextureSample(new Vector2i(120, 48), TILE_8_16);

    public static TextureSample UNDERLINE_UPPER_P = SAMPLER.createTextureSample(new Vector2i(128, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_Q = SAMPLER.createTextureSample(new Vector2i(136, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_R = SAMPLER.createTextureSample(new Vector2i(144, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_S = SAMPLER.createTextureSample(new Vector2i(152, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_T = SAMPLER.createTextureSample(new Vector2i(160, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_U = SAMPLER.createTextureSample(new Vector2i(168, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_V = SAMPLER.createTextureSample(new Vector2i(176, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_W = SAMPLER.createTextureSample(new Vector2i(184, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_X = SAMPLER.createTextureSample(new Vector2i(192, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_Y = SAMPLER.createTextureSample(new Vector2i(200, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UPPER_Z = SAMPLER.createTextureSample(new Vector2i(208, 48), TILE_8_16);
    public static TextureSample UNDERLINE_LEFT_BRACKET = SAMPLER.createTextureSample(new Vector2i(216, 48), TILE_8_16);
    public static TextureSample UNDERLINE_BACKSLASH = SAMPLER.createTextureSample(new Vector2i(224, 48), TILE_8_16);
    public static TextureSample UNDERLINE_RIGHT_BRACKET = SAMPLER.createTextureSample(new Vector2i(232, 48), TILE_8_16);
    public static TextureSample UNDERLINE_CARET = SAMPLER.createTextureSample(new Vector2i(240, 48), TILE_8_16);
    public static TextureSample UNDERLINE_UNDERSCORE = SAMPLER.createTextureSample(new Vector2i(248, 48), TILE_8_16);

    public static TextureSample BACKTICK = SAMPLER.createTextureSample(new Vector2i(0, 64), TILE_8_16);
    public static TextureSample LOWER_A = SAMPLER.createTextureSample(new Vector2i(8, 64), TILE_8_16);
    public static TextureSample LOWER_B = SAMPLER.createTextureSample(new Vector2i(16, 64), TILE_8_16);
    public static TextureSample LOWER_C = SAMPLER.createTextureSample(new Vector2i(24, 64), TILE_8_16);
    public static TextureSample LOWER_D = SAMPLER.createTextureSample(new Vector2i(32, 64), TILE_8_16);
    public static TextureSample LOWER_E = SAMPLER.createTextureSample(new Vector2i(40, 64), TILE_8_16);
    public static TextureSample LOWER_F = SAMPLER.createTextureSample(new Vector2i(48, 64), TILE_8_16);
    public static TextureSample LOWER_G = SAMPLER.createTextureSample(new Vector2i(56, 64), TILE_8_16);
    public static TextureSample LOWER_H = SAMPLER.createTextureSample(new Vector2i(64, 64), TILE_8_16);
    public static TextureSample LOWER_I = SAMPLER.createTextureSample(new Vector2i(72, 64), TILE_8_16);
    public static TextureSample LOWER_J = SAMPLER.createTextureSample(new Vector2i(80, 64), TILE_8_16);
    public static TextureSample LOWER_K = SAMPLER.createTextureSample(new Vector2i(88, 64), TILE_8_16);
    public static TextureSample LOWER_L = SAMPLER.createTextureSample(new Vector2i(96, 64), TILE_8_16);
    public static TextureSample LOWER_M = SAMPLER.createTextureSample(new Vector2i(104, 64), TILE_8_16);
    public static TextureSample LOWER_N = SAMPLER.createTextureSample(new Vector2i(112, 64), TILE_8_16);
    public static TextureSample LOWER_O = SAMPLER.createTextureSample(new Vector2i(120, 64), TILE_8_16);

    public static TextureSample UNDERLINE_BACKTICK = SAMPLER.createTextureSample(new Vector2i(128, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_A = SAMPLER.createTextureSample(new Vector2i(136, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_B = SAMPLER.createTextureSample(new Vector2i(144, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_C = SAMPLER.createTextureSample(new Vector2i(152, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_D = SAMPLER.createTextureSample(new Vector2i(160, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_E = SAMPLER.createTextureSample(new Vector2i(168, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_F = SAMPLER.createTextureSample(new Vector2i(176, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_G = SAMPLER.createTextureSample(new Vector2i(184, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_H = SAMPLER.createTextureSample(new Vector2i(192, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_I = SAMPLER.createTextureSample(new Vector2i(200, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_J = SAMPLER.createTextureSample(new Vector2i(208, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_K = SAMPLER.createTextureSample(new Vector2i(216, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_L = SAMPLER.createTextureSample(new Vector2i(224, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_M = SAMPLER.createTextureSample(new Vector2i(232, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_N = SAMPLER.createTextureSample(new Vector2i(240, 64), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_O = SAMPLER.createTextureSample(new Vector2i(248, 64), TILE_8_16);

    public static TextureSample LOWER_P = SAMPLER.createTextureSample(new Vector2i(0, 80), TILE_8_16);
    public static TextureSample LOWER_Q = SAMPLER.createTextureSample(new Vector2i(8, 80), TILE_8_16);
    public static TextureSample LOWER_R = SAMPLER.createTextureSample(new Vector2i(16, 80), TILE_8_16);
    public static TextureSample LOWER_S = SAMPLER.createTextureSample(new Vector2i(24, 80), TILE_8_16);
    public static TextureSample LOWER_T = SAMPLER.createTextureSample(new Vector2i(32, 80), TILE_8_16);
    public static TextureSample LOWER_U = SAMPLER.createTextureSample(new Vector2i(40, 80), TILE_8_16);
    public static TextureSample LOWER_V = SAMPLER.createTextureSample(new Vector2i(48, 80), TILE_8_16);
    public static TextureSample LOWER_W = SAMPLER.createTextureSample(new Vector2i(56, 80), TILE_8_16);
    public static TextureSample LOWER_X = SAMPLER.createTextureSample(new Vector2i(64, 80), TILE_8_16);
    public static TextureSample LOWER_Y = SAMPLER.createTextureSample(new Vector2i(72, 80), TILE_8_16);
    public static TextureSample LOWER_Z = SAMPLER.createTextureSample(new Vector2i(80, 80), TILE_8_16);
    public static TextureSample LEFT_BRACE = SAMPLER.createTextureSample(new Vector2i(88, 80), TILE_8_16);
    public static TextureSample PIPE = SAMPLER.createTextureSample(new Vector2i(96, 80), TILE_8_16);
    public static TextureSample RIGHT_BRACE = SAMPLER.createTextureSample(new Vector2i(104, 80), TILE_8_16);
    public static TextureSample TILDE = SAMPLER.createTextureSample(new Vector2i(112, 80), TILE_8_16);

    public static TextureSample UNDERLINE_LOWER_P = SAMPLER.createTextureSample(new Vector2i(128, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_Q = SAMPLER.createTextureSample(new Vector2i(136, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_R = SAMPLER.createTextureSample(new Vector2i(144, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_S = SAMPLER.createTextureSample(new Vector2i(152, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_T = SAMPLER.createTextureSample(new Vector2i(160, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_U = SAMPLER.createTextureSample(new Vector2i(168, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_V = SAMPLER.createTextureSample(new Vector2i(176, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_W = SAMPLER.createTextureSample(new Vector2i(184, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_X = SAMPLER.createTextureSample(new Vector2i(192, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_Y = SAMPLER.createTextureSample(new Vector2i(200, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LOWER_Z = SAMPLER.createTextureSample(new Vector2i(208, 80), TILE_8_16);
    public static TextureSample UNDERLINE_LEFT_BRACE = SAMPLER.createTextureSample(new Vector2i(216, 80), TILE_8_16);
    public static TextureSample UNDERLINE_PIPE = SAMPLER.createTextureSample(new Vector2i(224, 80), TILE_8_16);
    public static TextureSample UNDERLINE_RIGHT_BRACE = SAMPLER.createTextureSample(new Vector2i(232, 80), TILE_8_16);
    public static TextureSample UNDERLINE_TILDE = SAMPLER.createTextureSample(new Vector2i(240, 80), TILE_8_16);

    public static TextureSample SOLID = SAMPLER.createTextureSample(new Vector2i(8, 96), TILE_8_8);
    public static TextureSample RETICULE = SAMPLER.createTextureSample(new Vector2i(16, 96), TILE_8_8);
}
