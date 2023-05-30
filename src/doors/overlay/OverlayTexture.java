package doors.overlay;

import org.joml.Vector2i;

import doors.graphics.TextureSample;
import doors.graphics.Texture;

public class OverlayTexture extends Texture {

    private static Vector2i TILE_8_16 = new Vector2i(8, 16);
    private static Vector2i TILE_8_8 = new Vector2i(8,8);
    private static Vector2i TILE_2_2 = new Vector2i(2,2);

    public TextureSample space = this.createTextureSample(new Vector2i(0, 0), TILE_8_16);
    public TextureSample bang = this.createTextureSample(new Vector2i(8, 0), TILE_8_16);
    public TextureSample doubleQuotes = this.createTextureSample(new Vector2i(16, 0), TILE_8_16);
    public TextureSample hash = this.createTextureSample(new Vector2i(24, 0), TILE_8_16);
    public TextureSample dollar = this.createTextureSample(new Vector2i(32, 0), TILE_8_16);
    public TextureSample percent = this.createTextureSample(new Vector2i(40, 0), TILE_8_16);
    public TextureSample ampersand = this.createTextureSample(new Vector2i(48, 0), TILE_8_16);
    public TextureSample quote = this.createTextureSample(new Vector2i(56, 0), TILE_8_16);
    public TextureSample leftParens = this.createTextureSample(new Vector2i(64, 0), TILE_8_16);
    public TextureSample rightParens = this.createTextureSample(new Vector2i(72, 0), TILE_8_16);
    public TextureSample asterisk = this.createTextureSample(new Vector2i(80, 0), TILE_8_16);
    public TextureSample plus = this.createTextureSample(new Vector2i(88, 0), TILE_8_16);
    public TextureSample comma = this.createTextureSample(new Vector2i(96, 0), TILE_8_16);
    public TextureSample dash = this.createTextureSample(new Vector2i(104, 0), TILE_8_16);
    public TextureSample period = this.createTextureSample(new Vector2i(112, 0), TILE_8_16);
    public TextureSample slash = this.createTextureSample(new Vector2i(120, 0), TILE_8_16);

    public TextureSample underlineSpace = this.createTextureSample(new Vector2i(128, 0), TILE_8_16);
    public TextureSample underlineBang = this.createTextureSample(new Vector2i(136, 0), TILE_8_16);
    public TextureSample underlineDoubleQuotes = this.createTextureSample(new Vector2i(144, 0), TILE_8_16);
    public TextureSample underlineHash = this.createTextureSample(new Vector2i(152, 0), TILE_8_16);
    public TextureSample underlineDollar = this.createTextureSample(new Vector2i(160, 0), TILE_8_16);
    public TextureSample underlinePercent = this.createTextureSample(new Vector2i(168, 0), TILE_8_16);
    public TextureSample underlineAmpersand = this.createTextureSample(new Vector2i(176, 0), TILE_8_16);
    public TextureSample underlineQuote = this.createTextureSample(new Vector2i(184, 0), TILE_8_16);
    public TextureSample underlineLeftParens = this.createTextureSample(new Vector2i(192, 0), TILE_8_16);
    public TextureSample underlineRightParens = this.createTextureSample(new Vector2i(200, 0), TILE_8_16);
    public TextureSample underlineAsterisk = this.createTextureSample(new Vector2i(208, 0), TILE_8_16);
    public TextureSample underlinePlus = this.createTextureSample(new Vector2i(216, 0), TILE_8_16);
    public TextureSample underlineComma = this.createTextureSample(new Vector2i(224, 0), TILE_8_16);
    public TextureSample underlineDash = this.createTextureSample(new Vector2i(232, 0), TILE_8_16);
    public TextureSample underlinePeriod = this.createTextureSample(new Vector2i(240, 0), TILE_8_16);
    public TextureSample underlineSlash = this.createTextureSample(new Vector2i(248, 0), TILE_8_16);

    public TextureSample zero = this.createTextureSample(new Vector2i(0, 16), TILE_8_16);
    public TextureSample one = this.createTextureSample(new Vector2i(8, 16), TILE_8_16);
    public TextureSample two = this.createTextureSample(new Vector2i(16, 16), TILE_8_16);
    public TextureSample three = this.createTextureSample(new Vector2i(24, 16), TILE_8_16);
    public TextureSample four = this.createTextureSample(new Vector2i(32, 16), TILE_8_16);
    public TextureSample five = this.createTextureSample(new Vector2i(40, 16), TILE_8_16);
    public TextureSample six = this.createTextureSample(new Vector2i(48, 16), TILE_8_16);
    public TextureSample seven = this.createTextureSample(new Vector2i(56, 16), TILE_8_16);
    public TextureSample eight = this.createTextureSample(new Vector2i(64, 16), TILE_8_16);
    public TextureSample nine = this.createTextureSample(new Vector2i(72, 16), TILE_8_16);
    public TextureSample colon = this.createTextureSample(new Vector2i(80, 16), TILE_8_16);
    public TextureSample semiColon = this.createTextureSample(new Vector2i(88, 16), TILE_8_16);
    public TextureSample lessThan = this.createTextureSample(new Vector2i(96, 16), TILE_8_16);
    public TextureSample equals = this.createTextureSample(new Vector2i(104, 16), TILE_8_16);
    public TextureSample greaterThan = this.createTextureSample(new Vector2i(112, 16), TILE_8_16);
    public TextureSample question = this.createTextureSample(new Vector2i(120, 16), TILE_8_16);

    public TextureSample underlineZero = this.createTextureSample(new Vector2i(128, 16), TILE_8_16);
    public TextureSample underlineOne = this.createTextureSample(new Vector2i(136, 16), TILE_8_16);
    public TextureSample underlineTwo = this.createTextureSample(new Vector2i(144, 16), TILE_8_16);
    public TextureSample underlineThree = this.createTextureSample(new Vector2i(152, 16), TILE_8_16);
    public TextureSample underlineFour = this.createTextureSample(new Vector2i(160, 16), TILE_8_16);
    public TextureSample underlineFive = this.createTextureSample(new Vector2i(168, 16), TILE_8_16);
    public TextureSample underlineSix = this.createTextureSample(new Vector2i(176, 16), TILE_8_16);
    public TextureSample underlineSeven = this.createTextureSample(new Vector2i(184, 16), TILE_8_16);
    public TextureSample underlineEight = this.createTextureSample(new Vector2i(192, 16), TILE_8_16);
    public TextureSample underlineNine = this.createTextureSample(new Vector2i(200, 16), TILE_8_16);
    public TextureSample underlineColon = this.createTextureSample(new Vector2i(208, 16), TILE_8_16);
    public TextureSample underlineSemiColon = this.createTextureSample(new Vector2i(216, 16), TILE_8_16);
    public TextureSample underlineLessThan = this.createTextureSample(new Vector2i(224, 16), TILE_8_16);
    public TextureSample underlineEquals = this.createTextureSample(new Vector2i(232, 16), TILE_8_16);
    public TextureSample underlineGreaterThan = this.createTextureSample(new Vector2i(240, 16), TILE_8_16);
    public TextureSample underlineQuestion = this.createTextureSample(new Vector2i(248, 16), TILE_8_16);

    public TextureSample at = this.createTextureSample(new Vector2i(0, 32), TILE_8_16);
    public TextureSample upperA = this.createTextureSample(new Vector2i(8, 32), TILE_8_16);
    public TextureSample upperB = this.createTextureSample(new Vector2i(16, 32), TILE_8_16);
    public TextureSample upperC = this.createTextureSample(new Vector2i(24, 32), TILE_8_16);
    public TextureSample upperD = this.createTextureSample(new Vector2i(32, 32), TILE_8_16);
    public TextureSample upperE = this.createTextureSample(new Vector2i(40, 32), TILE_8_16);
    public TextureSample upperF = this.createTextureSample(new Vector2i(48, 32), TILE_8_16);
    public TextureSample upperG = this.createTextureSample(new Vector2i(56, 32), TILE_8_16);
    public TextureSample upperH = this.createTextureSample(new Vector2i(64, 32), TILE_8_16);
    public TextureSample upperI = this.createTextureSample(new Vector2i(72, 32), TILE_8_16);
    public TextureSample upperJ = this.createTextureSample(new Vector2i(80, 32), TILE_8_16);
    public TextureSample upperK = this.createTextureSample(new Vector2i(88, 32), TILE_8_16);
    public TextureSample upperL = this.createTextureSample(new Vector2i(96, 32), TILE_8_16);
    public TextureSample upperM = this.createTextureSample(new Vector2i(104, 32), TILE_8_16);
    public TextureSample upperN = this.createTextureSample(new Vector2i(112, 32), TILE_8_16);
    public TextureSample upperO = this.createTextureSample(new Vector2i(120, 32), TILE_8_16);

    public TextureSample underlineAt = this.createTextureSample(new Vector2i(128, 32), TILE_8_16);
    public TextureSample underlineUpperA = this.createTextureSample(new Vector2i(136, 32), TILE_8_16);
    public TextureSample underlineUpperB = this.createTextureSample(new Vector2i(144, 32), TILE_8_16);
    public TextureSample underlineUpperC = this.createTextureSample(new Vector2i(152, 32), TILE_8_16);
    public TextureSample underlineUpperD = this.createTextureSample(new Vector2i(160, 32), TILE_8_16);
    public TextureSample underlineUpperE = this.createTextureSample(new Vector2i(168, 32), TILE_8_16);
    public TextureSample underlineUpperF = this.createTextureSample(new Vector2i(176, 32), TILE_8_16);
    public TextureSample underlineUpperG = this.createTextureSample(new Vector2i(184, 32), TILE_8_16);
    public TextureSample underlineUpperH = this.createTextureSample(new Vector2i(192, 32), TILE_8_16);
    public TextureSample underlineUpperI = this.createTextureSample(new Vector2i(200, 32), TILE_8_16);
    public TextureSample underlineUpperJ = this.createTextureSample(new Vector2i(208, 32), TILE_8_16);
    public TextureSample underlineUpperK = this.createTextureSample(new Vector2i(216, 32), TILE_8_16);
    public TextureSample underlineUpperL = this.createTextureSample(new Vector2i(224, 32), TILE_8_16);
    public TextureSample underlineUpperM = this.createTextureSample(new Vector2i(232, 32), TILE_8_16);
    public TextureSample underlineUpperN = this.createTextureSample(new Vector2i(240, 32), TILE_8_16);
    public TextureSample underlineUpperO = this.createTextureSample(new Vector2i(248, 32), TILE_8_16);

    public TextureSample upperP = this.createTextureSample(new Vector2i(0, 48), TILE_8_16);
    public TextureSample upperQ = this.createTextureSample(new Vector2i(8, 48), TILE_8_16);
    public TextureSample upperR = this.createTextureSample(new Vector2i(16, 48), TILE_8_16);
    public TextureSample upperS = this.createTextureSample(new Vector2i(24, 48), TILE_8_16);
    public TextureSample upperT = this.createTextureSample(new Vector2i(32, 48), TILE_8_16);
    public TextureSample upperU = this.createTextureSample(new Vector2i(40, 48), TILE_8_16);
    public TextureSample upperV = this.createTextureSample(new Vector2i(48, 48), TILE_8_16);
    public TextureSample upperW = this.createTextureSample(new Vector2i(56, 48), TILE_8_16);
    public TextureSample upperX = this.createTextureSample(new Vector2i(64, 48), TILE_8_16);
    public TextureSample upperY = this.createTextureSample(new Vector2i(72, 48), TILE_8_16);
    public TextureSample upperZ = this.createTextureSample(new Vector2i(80, 48), TILE_8_16);
    public TextureSample leftBracket = this.createTextureSample(new Vector2i(88, 48), TILE_8_16);
    public TextureSample backSlash = this.createTextureSample(new Vector2i(96, 48), TILE_8_16);
    public TextureSample rightBracket = this.createTextureSample(new Vector2i(104, 48), TILE_8_16);
    public TextureSample caret = this.createTextureSample(new Vector2i(112, 48), TILE_8_16);
    public TextureSample underscore = this.createTextureSample(new Vector2i(120, 48), TILE_8_16);

    public TextureSample underlineUpperP = this.createTextureSample(new Vector2i(128, 48), TILE_8_16);
    public TextureSample underlineUpperQ = this.createTextureSample(new Vector2i(136, 48), TILE_8_16);
    public TextureSample underlineUpperR = this.createTextureSample(new Vector2i(144, 48), TILE_8_16);
    public TextureSample underlineUpperS = this.createTextureSample(new Vector2i(152, 48), TILE_8_16);
    public TextureSample underlineUpperT = this.createTextureSample(new Vector2i(160, 48), TILE_8_16);
    public TextureSample underlineUpperU = this.createTextureSample(new Vector2i(168, 48), TILE_8_16);
    public TextureSample underlineUpperV = this.createTextureSample(new Vector2i(176, 48), TILE_8_16);
    public TextureSample underlineUpperW = this.createTextureSample(new Vector2i(184, 48), TILE_8_16);
    public TextureSample underlineUpperX = this.createTextureSample(new Vector2i(192, 48), TILE_8_16);
    public TextureSample underlineUpperY = this.createTextureSample(new Vector2i(200, 48), TILE_8_16);
    public TextureSample underlineUpperZ = this.createTextureSample(new Vector2i(208, 48), TILE_8_16);
    public TextureSample underlineLeftBracket = this.createTextureSample(new Vector2i(216, 48), TILE_8_16);
    public TextureSample underlineBackSlash = this.createTextureSample(new Vector2i(224, 48), TILE_8_16);
    public TextureSample underlineRightBracket = this.createTextureSample(new Vector2i(232, 48), TILE_8_16);
    public TextureSample underlineCaret = this.createTextureSample(new Vector2i(240, 48), TILE_8_16);
    public TextureSample underlineUnderscore = this.createTextureSample(new Vector2i(248, 48), TILE_8_16);

    public TextureSample backTick = this.createTextureSample(new Vector2i(0, 64), TILE_8_16);
    public TextureSample lowerA = this.createTextureSample(new Vector2i(8, 64), TILE_8_16);
    public TextureSample lowerB = this.createTextureSample(new Vector2i(16, 64), TILE_8_16);
    public TextureSample lowerC = this.createTextureSample(new Vector2i(24, 64), TILE_8_16);
    public TextureSample lowerD = this.createTextureSample(new Vector2i(32, 64), TILE_8_16);
    public TextureSample lowerE = this.createTextureSample(new Vector2i(40, 64), TILE_8_16);
    public TextureSample lowerF = this.createTextureSample(new Vector2i(48, 64), TILE_8_16);
    public TextureSample lowerG = this.createTextureSample(new Vector2i(56, 64), TILE_8_16);
    public TextureSample lowerH = this.createTextureSample(new Vector2i(64, 64), TILE_8_16);
    public TextureSample lowerI = this.createTextureSample(new Vector2i(72, 64), TILE_8_16);
    public TextureSample lowerJ = this.createTextureSample(new Vector2i(80, 64), TILE_8_16);
    public TextureSample lowerK = this.createTextureSample(new Vector2i(88, 64), TILE_8_16);
    public TextureSample lowerL = this.createTextureSample(new Vector2i(96, 64), TILE_8_16);
    public TextureSample lowerM = this.createTextureSample(new Vector2i(104, 64), TILE_8_16);
    public TextureSample lowerN = this.createTextureSample(new Vector2i(112, 64), TILE_8_16);
    public TextureSample lowerO = this.createTextureSample(new Vector2i(120, 64), TILE_8_16);

    public TextureSample underlineBackTick = this.createTextureSample(new Vector2i(128, 64), TILE_8_16);
    public TextureSample underlineLowerA = this.createTextureSample(new Vector2i(136, 64), TILE_8_16);
    public TextureSample underlineLowerB = this.createTextureSample(new Vector2i(144, 64), TILE_8_16);
    public TextureSample underlineLowerC = this.createTextureSample(new Vector2i(152, 64), TILE_8_16);
    public TextureSample underlineLowerD = this.createTextureSample(new Vector2i(160, 64), TILE_8_16);
    public TextureSample underlineLowerE = this.createTextureSample(new Vector2i(168, 64), TILE_8_16);
    public TextureSample underlineLowerF = this.createTextureSample(new Vector2i(176, 64), TILE_8_16);
    public TextureSample underlineLowerG = this.createTextureSample(new Vector2i(184, 64), TILE_8_16);
    public TextureSample underlineLowerH = this.createTextureSample(new Vector2i(192, 64), TILE_8_16);
    public TextureSample underlineLowerI = this.createTextureSample(new Vector2i(200, 64), TILE_8_16);
    public TextureSample underlineLowerJ = this.createTextureSample(new Vector2i(208, 64), TILE_8_16);
    public TextureSample underlineLowerK = this.createTextureSample(new Vector2i(216, 64), TILE_8_16);
    public TextureSample underlineLowerL = this.createTextureSample(new Vector2i(224, 64), TILE_8_16);
    public TextureSample underlineLowerM = this.createTextureSample(new Vector2i(232, 64), TILE_8_16);
    public TextureSample underlineLowerN = this.createTextureSample(new Vector2i(240, 64), TILE_8_16);
    public TextureSample underlineLowerO = this.createTextureSample(new Vector2i(248, 64), TILE_8_16);

    public TextureSample lowerP = this.createTextureSample(new Vector2i(0, 80), TILE_8_16);
    public TextureSample lowerQ = this.createTextureSample(new Vector2i(8, 80), TILE_8_16);
    public TextureSample lowerR = this.createTextureSample(new Vector2i(16, 80), TILE_8_16);
    public TextureSample lowerS = this.createTextureSample(new Vector2i(24, 80), TILE_8_16);
    public TextureSample lowerT = this.createTextureSample(new Vector2i(32, 80), TILE_8_16);
    public TextureSample lowerU = this.createTextureSample(new Vector2i(40, 80), TILE_8_16);
    public TextureSample lowerV = this.createTextureSample(new Vector2i(48, 80), TILE_8_16);
    public TextureSample lowerW = this.createTextureSample(new Vector2i(56, 80), TILE_8_16);
    public TextureSample lowerX = this.createTextureSample(new Vector2i(64, 80), TILE_8_16);
    public TextureSample lowerY = this.createTextureSample(new Vector2i(72, 80), TILE_8_16);
    public TextureSample lowerZ = this.createTextureSample(new Vector2i(80, 80), TILE_8_16);
    public TextureSample leftBrace = this.createTextureSample(new Vector2i(88, 80), TILE_8_16);
    public TextureSample pipe = this.createTextureSample(new Vector2i(96, 80), TILE_8_16);
    public TextureSample rightBrace = this.createTextureSample(new Vector2i(104, 80), TILE_8_16);
    public TextureSample tilde = this.createTextureSample(new Vector2i(112, 80), TILE_8_16);

    public TextureSample underlineLowerP = this.createTextureSample(new Vector2i(128, 80), TILE_8_16);
    public TextureSample underlineLowerQ = this.createTextureSample(new Vector2i(136, 80), TILE_8_16);
    public TextureSample underlineLowerR = this.createTextureSample(new Vector2i(144, 80), TILE_8_16);
    public TextureSample underlineLowerS = this.createTextureSample(new Vector2i(152, 80), TILE_8_16);
    public TextureSample underlineLowerT = this.createTextureSample(new Vector2i(160, 80), TILE_8_16);
    public TextureSample underlineLowerU = this.createTextureSample(new Vector2i(168, 80), TILE_8_16);
    public TextureSample underlineLowerV = this.createTextureSample(new Vector2i(176, 80), TILE_8_16);
    public TextureSample underlineLowerW = this.createTextureSample(new Vector2i(184, 80), TILE_8_16);
    public TextureSample underlineLowerX = this.createTextureSample(new Vector2i(192, 80), TILE_8_16);
    public TextureSample underlineLowerY = this.createTextureSample(new Vector2i(200, 80), TILE_8_16);
    public TextureSample underlineLowerZ = this.createTextureSample(new Vector2i(208, 80), TILE_8_16);
    public TextureSample underlineLeftBrace = this.createTextureSample(new Vector2i(216, 80), TILE_8_16);
    public TextureSample underlinePipe = this.createTextureSample(new Vector2i(224, 80), TILE_8_16);
    public TextureSample underlineRightBrace = this.createTextureSample(new Vector2i(232, 80), TILE_8_16);
    public TextureSample underlineTilde = this.createTextureSample(new Vector2i(240, 80), TILE_8_16);

    public TextureSample solid = this.createTextureSample(new Vector2i(8, 96), TILE_8_8);
    public TextureSample reticule = this.createTextureSample(new Vector2i(16, 96), TILE_8_8);

    public TextureSample borderTopLeft = this.createTextureSample(new Vector2i(0, 96), TILE_2_2);
    public TextureSample borderTop = this.createTextureSample(new Vector2i(2, 96), TILE_2_2);
    public TextureSample borderTopRight = this.createTextureSample(new Vector2i(6, 96), TILE_2_2);
    public TextureSample borderLeft = this.createTextureSample(new Vector2i(0, 98), TILE_2_2);
    public TextureSample borderRight = this.createTextureSample(new Vector2i(6, 98), TILE_2_2);
    public TextureSample borderBottomLeft = this.createTextureSample(new Vector2i(0, 102), TILE_2_2);
    public TextureSample borderBottom = this.createTextureSample(new Vector2i(2, 102), TILE_2_2);
    public TextureSample borderBottomRight = this.createTextureSample(new Vector2i(6, 102), TILE_2_2);

    public OverlayTexture() {
        super(new Vector2i(512, 512), "src/texture/overlay.png");
    }

}
