package periwinkle.overlay;

import org.joml.Vector2i;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.Texture;

public class OverlayAtlas extends Atlas {

    private static Vector2i TILE_8_16 = new Vector2i(8, 16);
    private static Vector2i TILE_8_8 = new Vector2i(8,8);
    private static Vector2i TILE_2_2 = new Vector2i(2,2);

    public Texture space = this.createTexture(new Vector2i(0,0), TILE_8_16);
    public Texture bang = this.createTexture(new Vector2i(1,0), TILE_8_16);
    public Texture doubleQuotes = this.createTexture(new Vector2i(2,0), TILE_8_16);
    public Texture hash = this.createTexture(new Vector2i(3,0), TILE_8_16);
    public Texture dollar = this.createTexture(new Vector2i(4,0), TILE_8_16);
    public Texture percent = this.createTexture(new Vector2i(5,0), TILE_8_16);
    public Texture ampersand = this.createTexture(new Vector2i(6,0), TILE_8_16);
    public Texture quote = this.createTexture(new Vector2i(7,0), TILE_8_16);
    public Texture leftParens = this.createTexture(new Vector2i(8,0), TILE_8_16);
    public Texture rightParens = this.createTexture(new Vector2i(9,0), TILE_8_16);
    public Texture asterisk = this.createTexture(new Vector2i(10,0), TILE_8_16);
    public Texture plus = this.createTexture(new Vector2i(11,0), TILE_8_16);
    public Texture comma = this.createTexture(new Vector2i(12,0), TILE_8_16);
    public Texture dash = this.createTexture(new Vector2i(13,0), TILE_8_16);
    public Texture period = this.createTexture(new Vector2i(14,0), TILE_8_16);
    public Texture slash = this.createTexture(new Vector2i(15,0), TILE_8_16);

    public Texture underlineSpace = this.createTexture(new Vector2i(16,0), TILE_8_16);
    public Texture underlineBang = this.createTexture(new Vector2i(17,0), TILE_8_16);
    public Texture underlineDoubleQuotes = this.createTexture(new Vector2i(18,0), TILE_8_16);
    public Texture underlineHash = this.createTexture(new Vector2i(19,0), TILE_8_16);
    public Texture underlineDollar = this.createTexture(new Vector2i(20,0), TILE_8_16);
    public Texture underlinePercent = this.createTexture(new Vector2i(21,0), TILE_8_16);
    public Texture underlineAmpersand = this.createTexture(new Vector2i(22,0), TILE_8_16);
    public Texture underlineQuote = this.createTexture(new Vector2i(23,0), TILE_8_16);
    public Texture underlineLeftParens = this.createTexture(new Vector2i(24,0), TILE_8_16);
    public Texture underlineRightParens = this.createTexture(new Vector2i(25,0), TILE_8_16);
    public Texture underlineAsterisk = this.createTexture(new Vector2i(26,0), TILE_8_16);
    public Texture underlinePlus = this.createTexture(new Vector2i(27,0), TILE_8_16);
    public Texture underlineComma = this.createTexture(new Vector2i(28,0), TILE_8_16);
    public Texture underlineDash = this.createTexture(new Vector2i(29,0), TILE_8_16);
    public Texture underlinePeriod = this.createTexture(new Vector2i(30,0), TILE_8_16);
    public Texture underlineSlash = this.createTexture(new Vector2i(31,0), TILE_8_16);

    public Texture zero = this.createTexture(new Vector2i(0,1), TILE_8_16);
    public Texture one = this.createTexture(new Vector2i(1,1), TILE_8_16);
    public Texture two = this.createTexture(new Vector2i(2,1), TILE_8_16);
    public Texture three = this.createTexture(new Vector2i(3,1), TILE_8_16);
    public Texture four = this.createTexture(new Vector2i(4,1), TILE_8_16);
    public Texture five = this.createTexture(new Vector2i(5,1), TILE_8_16);
    public Texture six = this.createTexture(new Vector2i(6,1), TILE_8_16);
    public Texture seven = this.createTexture(new Vector2i(7,1), TILE_8_16);
    public Texture eight = this.createTexture(new Vector2i(8,1), TILE_8_16);
    public Texture nine = this.createTexture(new Vector2i(9,1), TILE_8_16);
    public Texture colon = this.createTexture(new Vector2i(10,1), TILE_8_16);
    public Texture semiColon = this.createTexture(new Vector2i(11,1), TILE_8_16);
    public Texture lessThan = this.createTexture(new Vector2i(12,1), TILE_8_16);
    public Texture equals = this.createTexture(new Vector2i(13,1), TILE_8_16);
    public Texture greaterThan = this.createTexture(new Vector2i(14,1), TILE_8_16);
    public Texture question = this.createTexture(new Vector2i(15,1), TILE_8_16);

    public Texture underlineZero = this.createTexture(new Vector2i(16,1), TILE_8_16);
    public Texture underlineOne = this.createTexture(new Vector2i(17,1), TILE_8_16);
    public Texture underlineTwo = this.createTexture(new Vector2i(18,1), TILE_8_16);
    public Texture underlineThree = this.createTexture(new Vector2i(19,1), TILE_8_16);
    public Texture underlineFour = this.createTexture(new Vector2i(20,1), TILE_8_16);
    public Texture underlineFive = this.createTexture(new Vector2i(21,1), TILE_8_16);
    public Texture underlineSix = this.createTexture(new Vector2i(22,1), TILE_8_16);
    public Texture underlineSeven = this.createTexture(new Vector2i(23,1), TILE_8_16);
    public Texture underlineEight = this.createTexture(new Vector2i(24,1), TILE_8_16);
    public Texture underlineNine = this.createTexture(new Vector2i(25,1), TILE_8_16);
    public Texture underlineColon = this.createTexture(new Vector2i(26,1), TILE_8_16);
    public Texture underlineSemiColon = this.createTexture(new Vector2i(27,1), TILE_8_16);
    public Texture underlineLessThan = this.createTexture(new Vector2i(28,1), TILE_8_16);
    public Texture underlineEquals = this.createTexture(new Vector2i(29,1), TILE_8_16);
    public Texture underlineGreaterThan = this.createTexture(new Vector2i(30,1), TILE_8_16);
    public Texture underlineQuestion = this.createTexture(new Vector2i(31,1), TILE_8_16);

    public Texture at = this.createTexture(new Vector2i(0,2), TILE_8_16);
    public Texture upperA = this.createTexture(new Vector2i(1,2), TILE_8_16);
    public Texture upperB = this.createTexture(new Vector2i(2,2), TILE_8_16);
    public Texture upperC = this.createTexture(new Vector2i(3,2), TILE_8_16);
    public Texture upperD = this.createTexture(new Vector2i(4,2), TILE_8_16);
    public Texture upperE = this.createTexture(new Vector2i(5,2), TILE_8_16);
    public Texture upperF = this.createTexture(new Vector2i(6,2), TILE_8_16);
    public Texture upperG = this.createTexture(new Vector2i(7,2), TILE_8_16);
    public Texture upperH = this.createTexture(new Vector2i(8,2), TILE_8_16);
    public Texture upperI = this.createTexture(new Vector2i(9,2), TILE_8_16);
    public Texture upperJ = this.createTexture(new Vector2i(10,2), TILE_8_16);
    public Texture upperK = this.createTexture(new Vector2i(11,2), TILE_8_16);
    public Texture upperL = this.createTexture(new Vector2i(12,2), TILE_8_16);
    public Texture upperM = this.createTexture(new Vector2i(13,2), TILE_8_16);
    public Texture upperN = this.createTexture(new Vector2i(14,2), TILE_8_16);
    public Texture upperO = this.createTexture(new Vector2i(15,2), TILE_8_16);

    public Texture underlineAt = this.createTexture(new Vector2i(16,2), TILE_8_16);
    public Texture underlineUpperA = this.createTexture(new Vector2i(17,2), TILE_8_16);
    public Texture underlineUpperB = this.createTexture(new Vector2i(18,2), TILE_8_16);
    public Texture underlineUpperC = this.createTexture(new Vector2i(19,2), TILE_8_16);
    public Texture underlineUpperD = this.createTexture(new Vector2i(20,2), TILE_8_16);
    public Texture underlineUpperE = this.createTexture(new Vector2i(21,2), TILE_8_16);
    public Texture underlineUpperF = this.createTexture(new Vector2i(22,2), TILE_8_16);
    public Texture underlineUpperG = this.createTexture(new Vector2i(23,2), TILE_8_16);
    public Texture underlineUpperH = this.createTexture(new Vector2i(24,2), TILE_8_16);
    public Texture underlineUpperI = this.createTexture(new Vector2i(25,2), TILE_8_16);
    public Texture underlineUpperJ = this.createTexture(new Vector2i(26,2), TILE_8_16);
    public Texture underlineUpperK = this.createTexture(new Vector2i(27,2), TILE_8_16);
    public Texture underlineUpperL = this.createTexture(new Vector2i(28,2), TILE_8_16);
    public Texture underlineUpperM = this.createTexture(new Vector2i(29,2), TILE_8_16);
    public Texture underlineUpperN = this.createTexture(new Vector2i(30,2), TILE_8_16);
    public Texture underlineUpperO = this.createTexture(new Vector2i(31,2), TILE_8_16);

    public Texture upperP = this.createTexture(new Vector2i(0,3), TILE_8_16);
    public Texture upperQ = this.createTexture(new Vector2i(1,3), TILE_8_16);
    public Texture upperR = this.createTexture(new Vector2i(2,3), TILE_8_16);
    public Texture upperS = this.createTexture(new Vector2i(3,3), TILE_8_16);
    public Texture upperT = this.createTexture(new Vector2i(4,3), TILE_8_16);
    public Texture upperU = this.createTexture(new Vector2i(5,3), TILE_8_16);
    public Texture upperV = this.createTexture(new Vector2i(6,3), TILE_8_16);
    public Texture upperW = this.createTexture(new Vector2i(7,3), TILE_8_16);
    public Texture upperX = this.createTexture(new Vector2i(8,3), TILE_8_16);
    public Texture upperY = this.createTexture(new Vector2i(9,3), TILE_8_16);
    public Texture upperZ = this.createTexture(new Vector2i(10,3), TILE_8_16);
    public Texture leftBracket = this.createTexture(new Vector2i(11,3), TILE_8_16);
    public Texture backSlash = this.createTexture(new Vector2i(12,3), TILE_8_16);
    public Texture rightBracket = this.createTexture(new Vector2i(13,3), TILE_8_16);
    public Texture caret = this.createTexture(new Vector2i(14,3), TILE_8_16);
    public Texture underscore = this.createTexture(new Vector2i(15,3), TILE_8_16);

    public Texture underlineUpperP = this.createTexture(new Vector2i(16,3), TILE_8_16);
    public Texture underlineUpperQ = this.createTexture(new Vector2i(17,3), TILE_8_16);
    public Texture underlineUpperR = this.createTexture(new Vector2i(18,3), TILE_8_16);
    public Texture underlineUpperS = this.createTexture(new Vector2i(19,3), TILE_8_16);
    public Texture underlineUpperT = this.createTexture(new Vector2i(20,3), TILE_8_16);
    public Texture underlineUpperU = this.createTexture(new Vector2i(21,3), TILE_8_16);
    public Texture underlineUpperV = this.createTexture(new Vector2i(22,3), TILE_8_16);
    public Texture underlineUpperW = this.createTexture(new Vector2i(23,3), TILE_8_16);
    public Texture underlineUpperX = this.createTexture(new Vector2i(24,3), TILE_8_16);
    public Texture underlineUpperY = this.createTexture(new Vector2i(25,3), TILE_8_16);
    public Texture underlineUpperZ = this.createTexture(new Vector2i(26,3), TILE_8_16);
    public Texture underlineLeftBracket = this.createTexture(new Vector2i(27,3), TILE_8_16);
    public Texture underlineBackSlash = this.createTexture(new Vector2i(28,3), TILE_8_16);
    public Texture underlineRightBracket = this.createTexture(new Vector2i(29,3), TILE_8_16);
    public Texture underlineCaret = this.createTexture(new Vector2i(30,3), TILE_8_16);
    public Texture underlineUnderscore = this.createTexture(new Vector2i(31,3), TILE_8_16);

    public Texture backTick = this.createTexture(new Vector2i(0,4), TILE_8_16);
    public Texture lowerA = this.createTexture(new Vector2i(1,4), TILE_8_16);
    public Texture lowerB = this.createTexture(new Vector2i(2,4), TILE_8_16);
    public Texture lowerC = this.createTexture(new Vector2i(3,4), TILE_8_16);
    public Texture lowerD = this.createTexture(new Vector2i(4,4), TILE_8_16);
    public Texture lowerE = this.createTexture(new Vector2i(5,4), TILE_8_16);
    public Texture lowerF = this.createTexture(new Vector2i(6,4), TILE_8_16);
    public Texture lowerG = this.createTexture(new Vector2i(7,4), TILE_8_16);
    public Texture lowerH = this.createTexture(new Vector2i(8,4), TILE_8_16);
    public Texture lowerI = this.createTexture(new Vector2i(9,4), TILE_8_16);
    public Texture lowerJ = this.createTexture(new Vector2i(10,4), TILE_8_16);
    public Texture lowerK = this.createTexture(new Vector2i(11,4), TILE_8_16);
    public Texture lowerL = this.createTexture(new Vector2i(12,4), TILE_8_16);
    public Texture lowerM = this.createTexture(new Vector2i(13,4), TILE_8_16);
    public Texture lowerN = this.createTexture(new Vector2i(14,4), TILE_8_16);
    public Texture lowerO = this.createTexture(new Vector2i(15,4), TILE_8_16);

    public Texture underlineBackTick = this.createTexture(new Vector2i(16,4), TILE_8_16);
    public Texture underlineLowerA = this.createTexture(new Vector2i(17,4), TILE_8_16);
    public Texture underlineLowerB = this.createTexture(new Vector2i(18,4), TILE_8_16);
    public Texture underlineLowerC = this.createTexture(new Vector2i(19,4), TILE_8_16);
    public Texture underlineLowerD = this.createTexture(new Vector2i(20,4), TILE_8_16);
    public Texture underlineLowerE = this.createTexture(new Vector2i(21,4), TILE_8_16);
    public Texture underlineLowerF = this.createTexture(new Vector2i(22,4), TILE_8_16);
    public Texture underlineLowerG = this.createTexture(new Vector2i(23,4), TILE_8_16);
    public Texture underlineLowerH = this.createTexture(new Vector2i(24,4), TILE_8_16);
    public Texture underlineLowerI = this.createTexture(new Vector2i(25,4), TILE_8_16);
    public Texture underlineLowerJ = this.createTexture(new Vector2i(26,4), TILE_8_16);
    public Texture underlineLowerK = this.createTexture(new Vector2i(27,4), TILE_8_16);
    public Texture underlineLowerL = this.createTexture(new Vector2i(28,4), TILE_8_16);
    public Texture underlineLowerM = this.createTexture(new Vector2i(29,4), TILE_8_16);
    public Texture underlineLowerN = this.createTexture(new Vector2i(30,4), TILE_8_16);
    public Texture underlineLowerO = this.createTexture(new Vector2i(31,4), TILE_8_16);

    public Texture lowerP = this.createTexture(new Vector2i(0,5), TILE_8_16);
    public Texture lowerQ = this.createTexture(new Vector2i(1,5), TILE_8_16);
    public Texture lowerR = this.createTexture(new Vector2i(2,5), TILE_8_16);
    public Texture lowerS = this.createTexture(new Vector2i(3,5), TILE_8_16);
    public Texture lowerT = this.createTexture(new Vector2i(4,5), TILE_8_16);
    public Texture lowerU = this.createTexture(new Vector2i(5,5), TILE_8_16);
    public Texture lowerV = this.createTexture(new Vector2i(6,5), TILE_8_16);
    public Texture lowerW = this.createTexture(new Vector2i(7,5), TILE_8_16);
    public Texture lowerX = this.createTexture(new Vector2i(8,5), TILE_8_16);
    public Texture lowerY = this.createTexture(new Vector2i(9,5), TILE_8_16);
    public Texture lowerZ = this.createTexture(new Vector2i(10,5), TILE_8_16);
    public Texture leftBrace = this.createTexture(new Vector2i(11,5), TILE_8_16);
    public Texture pipe = this.createTexture(new Vector2i(12,5), TILE_8_16);
    public Texture rightBrace = this.createTexture(new Vector2i(13,5), TILE_8_16);
    public Texture tilde = this.createTexture(new Vector2i(14,5), TILE_8_16);

    public Texture underlineLowerP = this.createTexture(new Vector2i(16,5), TILE_8_16);
    public Texture underlineLowerQ = this.createTexture(new Vector2i(17,5), TILE_8_16);
    public Texture underlineLowerR = this.createTexture(new Vector2i(18,5), TILE_8_16);
    public Texture underlineLowerS = this.createTexture(new Vector2i(19,5), TILE_8_16);
    public Texture underlineLowerT = this.createTexture(new Vector2i(20,5), TILE_8_16);
    public Texture underlineLowerU = this.createTexture(new Vector2i(21,5), TILE_8_16);
    public Texture underlineLowerV = this.createTexture(new Vector2i(22,5), TILE_8_16);
    public Texture underlineLowerW = this.createTexture(new Vector2i(23,5), TILE_8_16);
    public Texture underlineLowerX = this.createTexture(new Vector2i(24,5), TILE_8_16);
    public Texture underlineLowerY = this.createTexture(new Vector2i(25,5), TILE_8_16);
    public Texture underlineLowerZ = this.createTexture(new Vector2i(26,5), TILE_8_16);
    public Texture underlineLeftBrace = this.createTexture(new Vector2i(27,5), TILE_8_16);
    public Texture underlinePipe = this.createTexture(new Vector2i(28,5), TILE_8_16);
    public Texture underlineRightBrace = this.createTexture(new Vector2i(29,5), TILE_8_16);
    public Texture underlineTilde = this.createTexture(new Vector2i(30,5), TILE_8_16);

    public Texture solid = this.createTexture(new Vector2i(1,12), TILE_8_8);
    public Texture reticule = this.createTexture(new Vector2i(2,12), TILE_8_8);

    public Texture borderTopLeft = this.createTexture(new Vector2i(0,48), TILE_2_2);
    public Texture borderTop = this.createTexture(new Vector2i(1,48), TILE_2_2);
    public Texture borderTopRight = this.createTexture(new Vector2i(3,48), TILE_2_2);
    public Texture borderLeft = this.createTexture(new Vector2i(0,49), TILE_2_2);
    public Texture borderRight = this.createTexture(new Vector2i(3,49), TILE_2_2);
    public Texture borderBottomLeft = this.createTexture(new Vector2i(0,51), TILE_2_2);
    public Texture borderBottom = this.createTexture(new Vector2i(1,51), TILE_2_2);
    public Texture borderBottomRight = this.createTexture(new Vector2i(3,51), TILE_2_2);

    public OverlayAtlas() {
        super("src/texture/overlay.png", new Vector2i(512, 512));
    }





}
