package doors.graphics.texture;

import doors.graphics.texture.channel.FontTextureChannel;
import doors.utility.vector.Vector2in;

public class FontTexture extends ImageTexture {

    private static Vector2in TEXTURE_DIMENSIONS = new Vector2in(256, 96);
    private static Vector2in CHARACTER_DIMENSIONS = new Vector2in(8, 16);

    public static FontTexture FONT_TEXTURE = new FontTexture();

    public FontTexture() {
        super(
            FontTextureChannel.FONT_TEXTURE_CHANNEL,
            TEXTURE_DIMENSIONS,
            "data/texture/font.png"
        );
    }

    public TextureSample symSpace = this.createTextureSample(new Vector2in(0, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symSpaceUnderline = this.createTextureSample(new Vector2in(16, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symBang = this.createTextureSample(new Vector2in(1, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symBangUnderline = this.createTextureSample(new Vector2in(17, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symDoubleQuotes = this.createTextureSample(new Vector2in(2, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symDoubleQuotesUnderline = this.createTextureSample(new Vector2in(18, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symHash = this.createTextureSample(new Vector2in(3, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symHashUnderline = this.createTextureSample(new Vector2in(19, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symDollar = this.createTextureSample(new Vector2in(4, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symDollarUnderline = this.createTextureSample(new Vector2in(20, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symPercent = this.createTextureSample(new Vector2in(5, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symPercentUnderline = this.createTextureSample(new Vector2in(21, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symAmpersand = this.createTextureSample(new Vector2in(6, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symAmpersandUnderline = this.createTextureSample(new Vector2in(22, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symSingleQuote = this.createTextureSample(new Vector2in(7, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symSingleQuoteUnderline = this.createTextureSample(new Vector2in(23, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symOpenParen = this.createTextureSample(new Vector2in(8, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symOpenParenUnderline = this.createTextureSample(new Vector2in(24, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symCloseParen = this.createTextureSample(new Vector2in(9, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symCloseParenUnderline = this.createTextureSample(new Vector2in(25, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symStar = this.createTextureSample(new Vector2in(10, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symStarUnderline = this.createTextureSample(new Vector2in(26, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symPlus = this.createTextureSample(new Vector2in(11, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symPlusUnderline = this.createTextureSample(new Vector2in(27, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symComma = this.createTextureSample(new Vector2in(12, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symCommaUnderline = this.createTextureSample(new Vector2in(28, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symMinus = this.createTextureSample(new Vector2in(13, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symMinusUnderline = this.createTextureSample(new Vector2in(29, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symPeriod = this.createTextureSample(new Vector2in(14, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symPeriodUnderline = this.createTextureSample(new Vector2in(30, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symSlash = this.createTextureSample(new Vector2in(15, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symSlashUnderline = this.createTextureSample(new Vector2in(31, 0), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample digit0 = this.createTextureSample(new Vector2in(0, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample digit0Underline = this.createTextureSample(new Vector2in(16, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample digit1 = this.createTextureSample(new Vector2in(1, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample digit1Underline = this.createTextureSample(new Vector2in(17, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample digit2 = this.createTextureSample(new Vector2in(2, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample digit2Underline = this.createTextureSample(new Vector2in(18, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample digit3 = this.createTextureSample(new Vector2in(3, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample digit3Underline = this.createTextureSample(new Vector2in(19, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample digit4 = this.createTextureSample(new Vector2in(4, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample digit4Underline = this.createTextureSample(new Vector2in(20, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample digit5 = this.createTextureSample(new Vector2in(5, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample digit5Underline = this.createTextureSample(new Vector2in(21, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample digit6 = this.createTextureSample(new Vector2in(6, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample digit6Underline = this.createTextureSample(new Vector2in(22, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample digit7 = this.createTextureSample(new Vector2in(7, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample digit7Underline = this.createTextureSample(new Vector2in(23, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample digit8 = this.createTextureSample(new Vector2in(8, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample digit8Underline = this.createTextureSample(new Vector2in(24, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample digit9 = this.createTextureSample(new Vector2in(9, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample digit9Underline = this.createTextureSample(new Vector2in(25, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symColon = this.createTextureSample(new Vector2in(10, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symColonUnderline = this.createTextureSample(new Vector2in(26, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symSemicolon = this.createTextureSample(new Vector2in(11, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symSemicolonUnderline = this.createTextureSample(new Vector2in(27, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symLessThan = this.createTextureSample(new Vector2in(12, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symLessThanUnderline = this.createTextureSample(new Vector2in(28, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symEquals = this.createTextureSample(new Vector2in(13, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symEqualsUnderline = this.createTextureSample(new Vector2in(29, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symGreaterThan = this.createTextureSample(new Vector2in(14, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symGreaterThanUnderline = this.createTextureSample(new Vector2in(30, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symQuestionMark = this.createTextureSample(new Vector2in(15, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symQuestionMarkUnderline = this.createTextureSample(new Vector2in(31, 1), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symAt = this.createTextureSample(new Vector2in(0, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symAtUnderline = this.createTextureSample(new Vector2in(16, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseA = this.createTextureSample(new Vector2in(1, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseAUnderline = this.createTextureSample(new Vector2in(17, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseB = this.createTextureSample(new Vector2in(2, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseBUnderline = this.createTextureSample(new Vector2in(18, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseC = this.createTextureSample(new Vector2in(3, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseCUnderline = this.createTextureSample(new Vector2in(19, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseD = this.createTextureSample(new Vector2in(4, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseDUnderline = this.createTextureSample(new Vector2in(20, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseE = this.createTextureSample(new Vector2in(5, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseEUnderline = this.createTextureSample(new Vector2in(21, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseF = this.createTextureSample(new Vector2in(6, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseFUnderline = this.createTextureSample(new Vector2in(22, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseG = this.createTextureSample(new Vector2in(7, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseGUnderline = this.createTextureSample(new Vector2in(23, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseH = this.createTextureSample(new Vector2in(8, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseHUnderline = this.createTextureSample(new Vector2in(24, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseI = this.createTextureSample(new Vector2in(9, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseIUnderline = this.createTextureSample(new Vector2in(25, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseJ = this.createTextureSample(new Vector2in(10, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseJUnderline = this.createTextureSample(new Vector2in(26, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseK = this.createTextureSample(new Vector2in(11, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseKUnderline = this.createTextureSample(new Vector2in(27, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseL = this.createTextureSample(new Vector2in(12, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseLUnderline = this.createTextureSample(new Vector2in(28, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseM = this.createTextureSample(new Vector2in(13, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseMUnderline = this.createTextureSample(new Vector2in(29, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseN = this.createTextureSample(new Vector2in(14, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseNUnderline = this.createTextureSample(new Vector2in(30, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseO = this.createTextureSample(new Vector2in(15, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseOUnderline = this.createTextureSample(new Vector2in(31, 2), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseP = this.createTextureSample(new Vector2in(0, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercasePUnderline = this.createTextureSample(new Vector2in(16, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseQ = this.createTextureSample(new Vector2in(1, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseQUnderline = this.createTextureSample(new Vector2in(17, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseR = this.createTextureSample(new Vector2in(2, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseRUnderline = this.createTextureSample(new Vector2in(18, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseS = this.createTextureSample(new Vector2in(3, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseSUnderline = this.createTextureSample(new Vector2in(19, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseT = this.createTextureSample(new Vector2in(4, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseTUnderline = this.createTextureSample(new Vector2in(20, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseU = this.createTextureSample(new Vector2in(5, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseUUnderline = this.createTextureSample(new Vector2in(21, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    
    public TextureSample letterUppercaseV = this.createTextureSample(new Vector2in(6, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseVUnderline = this.createTextureSample(new Vector2in(22, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseW = this.createTextureSample(new Vector2in(7, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseWUnderline = this.createTextureSample(new Vector2in(23, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseX = this.createTextureSample(new Vector2in(8, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseXUnderline = this.createTextureSample(new Vector2in(24, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseY = this.createTextureSample(new Vector2in(9, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseYUnderline = this.createTextureSample(new Vector2in(25, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterUppercaseZ = this.createTextureSample(new Vector2in(10, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterUppercaseZUnderline = this.createTextureSample(new Vector2in(26, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symOpenBracket = this.createTextureSample(new Vector2in(11, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symOpenBracketUnderline = this.createTextureSample(new Vector2in(27, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symBackslash = this.createTextureSample(new Vector2in(12, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symBackslashUnderline = this.createTextureSample(new Vector2in(28, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symCloseBracket = this.createTextureSample(new Vector2in(13, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symCloseBracketUnderline = this.createTextureSample(new Vector2in(29, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symCaret = this.createTextureSample(new Vector2in(14, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symCaretUnderline = this.createTextureSample(new Vector2in(30, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symUnderscore = this.createTextureSample(new Vector2in(15, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symUnderscoreUnderline = this.createTextureSample(new Vector2in(31, 3), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symGrave = this.createTextureSample(new Vector2in(0, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symGraveUnderline = this.createTextureSample(new Vector2in(16, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseA = this.createTextureSample(new Vector2in(1, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseAUnderline = this.createTextureSample(new Vector2in(17, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseB = this.createTextureSample(new Vector2in(2, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseBUnderline = this.createTextureSample(new Vector2in(18, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseC = this.createTextureSample(new Vector2in(3, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseCUnderline = this.createTextureSample(new Vector2in(19, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseD = this.createTextureSample(new Vector2in(4, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseDUnderline = this.createTextureSample(new Vector2in(20, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseE = this.createTextureSample(new Vector2in(5, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseEUnderline = this.createTextureSample(new Vector2in(21, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseF = this.createTextureSample(new Vector2in(6, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseFUnderline = this.createTextureSample(new Vector2in(22, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseG = this.createTextureSample(new Vector2in(7, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseGUnderline = this.createTextureSample(new Vector2in(23, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseH = this.createTextureSample(new Vector2in(8, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseHUnderline = this.createTextureSample(new Vector2in(24, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseI = this.createTextureSample(new Vector2in(9, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseIUnderline = this.createTextureSample(new Vector2in(25, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseJ = this.createTextureSample(new Vector2in(10, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseJUnderline = this.createTextureSample(new Vector2in(26, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseK = this.createTextureSample(new Vector2in(11, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseKUnderline = this.createTextureSample(new Vector2in(27, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseL = this.createTextureSample(new Vector2in(12, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseLUnderline = this.createTextureSample(new Vector2in(28, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseM = this.createTextureSample(new Vector2in(13, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseMUnderline = this.createTextureSample(new Vector2in(29, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseN = this.createTextureSample(new Vector2in(14, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseNUnderline = this.createTextureSample(new Vector2in(30, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseO = this.createTextureSample(new Vector2in(15, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseOUnderline = this.createTextureSample(new Vector2in(31, 4), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseP = this.createTextureSample(new Vector2in(0, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercasePUnderline = this.createTextureSample(new Vector2in(16, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseQ = this.createTextureSample(new Vector2in(1, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseQUnderline = this.createTextureSample(new Vector2in(17, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseR = this.createTextureSample(new Vector2in(2, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseRUnderline = this.createTextureSample(new Vector2in(18, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseS = this.createTextureSample(new Vector2in(3, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseSUnderline = this.createTextureSample(new Vector2in(19, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseT = this.createTextureSample(new Vector2in(4, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseTUnderline = this.createTextureSample(new Vector2in(20, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseU = this.createTextureSample(new Vector2in(5, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseUUnderline = this.createTextureSample(new Vector2in(21, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseV = this.createTextureSample(new Vector2in(6, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseVUnderline = this.createTextureSample(new Vector2in(22, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseW = this.createTextureSample(new Vector2in(7, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseWUnderline = this.createTextureSample(new Vector2in(23, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseX = this.createTextureSample(new Vector2in(8, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseXUnderline = this.createTextureSample(new Vector2in(24, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseY = this.createTextureSample(new Vector2in(9, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseYUnderline = this.createTextureSample(new Vector2in(25, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample letterLowercaseZ = this.createTextureSample(new Vector2in(10, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample letterLowercaseZUnderline = this.createTextureSample(new Vector2in(26, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symOpenBrace = this.createTextureSample(new Vector2in(11, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symOpenBraceUnderline = this.createTextureSample(new Vector2in(27, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symPipe = this.createTextureSample(new Vector2in(12, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symPipeUnderline = this.createTextureSample(new Vector2in(28, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symCloseBrace = this.createTextureSample(new Vector2in(13, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symCloseBraceUnderline = this.createTextureSample(new Vector2in(29, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);

    public TextureSample symTilde = this.createTextureSample(new Vector2in(14, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
    public TextureSample symTildeUnderline = this.createTextureSample(new Vector2in(30, 5), Vector2in.ONE, CHARACTER_DIMENSIONS);
}
