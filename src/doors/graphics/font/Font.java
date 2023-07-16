package doors.graphics.font;

import java.util.HashMap;
import java.util.Map;

import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.FontTexture;
import doors.graphics.texture.TextureSample;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class Font {

    public static Vector2in CHARACTER_DIMENSIONS = new Vector2in(8, 16);

    public static Font FONT = new Font();

    private Map<Character, FontCharacter> fontCharacterLookup = new HashMap<>();

    public Font() {
        this.registerCharacter(' ', FontTexture.FONT_TEXTURE.symSpace, FontTexture.FONT_TEXTURE.symSpaceUnderline);
        this.registerCharacter('!', FontTexture.FONT_TEXTURE.symBang, FontTexture.FONT_TEXTURE.symBangUnderline);
        this.registerCharacter('"', FontTexture.FONT_TEXTURE.symDoubleQuotes, FontTexture.FONT_TEXTURE.symDoubleQuotesUnderline);
        this.registerCharacter('#', FontTexture.FONT_TEXTURE.symHash, FontTexture.FONT_TEXTURE.symHashUnderline);
        this.registerCharacter('$', FontTexture.FONT_TEXTURE.symDollar, FontTexture.FONT_TEXTURE.symDollarUnderline);
        this.registerCharacter('%', FontTexture.FONT_TEXTURE.symPercent, FontTexture.FONT_TEXTURE.symPercentUnderline);
        this.registerCharacter('&', FontTexture.FONT_TEXTURE.symAmpersand, FontTexture.FONT_TEXTURE.symAmpersandUnderline);
        this.registerCharacter('\'', FontTexture.FONT_TEXTURE.symSingleQuote, FontTexture.FONT_TEXTURE.symSingleQuoteUnderline);
        this.registerCharacter('(', FontTexture.FONT_TEXTURE.symOpenParen, FontTexture.FONT_TEXTURE.symOpenParenUnderline);
        this.registerCharacter(')', FontTexture.FONT_TEXTURE.symCloseParen, FontTexture.FONT_TEXTURE.symCloseParenUnderline);
        this.registerCharacter('*', FontTexture.FONT_TEXTURE.symStar, FontTexture.FONT_TEXTURE.symStarUnderline);
        this.registerCharacter('+', FontTexture.FONT_TEXTURE.symPlus, FontTexture.FONT_TEXTURE.symPlusUnderline);
        this.registerCharacter(',', FontTexture.FONT_TEXTURE.symComma, FontTexture.FONT_TEXTURE.symCommaUnderline);
        this.registerCharacter('-', FontTexture.FONT_TEXTURE.symMinus, FontTexture.FONT_TEXTURE.symMinusUnderline);
        this.registerCharacter('.', FontTexture.FONT_TEXTURE.symPeriod, FontTexture.FONT_TEXTURE.symPeriodUnderline);
        this.registerCharacter('/', FontTexture.FONT_TEXTURE.symSlash, FontTexture.FONT_TEXTURE.symSlashUnderline);

        this.registerCharacter('0', FontTexture.FONT_TEXTURE.digit0, FontTexture.FONT_TEXTURE.digit0Underline);
        this.registerCharacter('1', FontTexture.FONT_TEXTURE.digit1, FontTexture.FONT_TEXTURE.digit1Underline);
        this.registerCharacter('2', FontTexture.FONT_TEXTURE.digit2, FontTexture.FONT_TEXTURE.digit2Underline);
        this.registerCharacter('3', FontTexture.FONT_TEXTURE.digit3, FontTexture.FONT_TEXTURE.digit3Underline);
        this.registerCharacter('4', FontTexture.FONT_TEXTURE.digit4, FontTexture.FONT_TEXTURE.digit4Underline);
        this.registerCharacter('5', FontTexture.FONT_TEXTURE.digit5, FontTexture.FONT_TEXTURE.digit5Underline);
        this.registerCharacter('6', FontTexture.FONT_TEXTURE.digit6, FontTexture.FONT_TEXTURE.digit6Underline);
        this.registerCharacter('7', FontTexture.FONT_TEXTURE.digit7, FontTexture.FONT_TEXTURE.digit7Underline);
        this.registerCharacter('8', FontTexture.FONT_TEXTURE.digit8, FontTexture.FONT_TEXTURE.digit8Underline);
        this.registerCharacter('9', FontTexture.FONT_TEXTURE.digit9, FontTexture.FONT_TEXTURE.digit9Underline);
        this.registerCharacter(':', FontTexture.FONT_TEXTURE.symColon, FontTexture.FONT_TEXTURE.symColonUnderline);
        this.registerCharacter(';', FontTexture.FONT_TEXTURE.symSemicolon, FontTexture.FONT_TEXTURE.symSemicolonUnderline);
        this.registerCharacter('<', FontTexture.FONT_TEXTURE.symLessThan, FontTexture.FONT_TEXTURE.symLessThanUnderline);
        this.registerCharacter('=', FontTexture.FONT_TEXTURE.symEquals, FontTexture.FONT_TEXTURE.symEqualsUnderline);
        this.registerCharacter('>', FontTexture.FONT_TEXTURE.symGreaterThan, FontTexture.FONT_TEXTURE.symGreaterThanUnderline);
        this.registerCharacter('?', FontTexture.FONT_TEXTURE.symQuestionMark, FontTexture.FONT_TEXTURE.symQuestionMarkUnderline);

        this.registerCharacter('@', FontTexture.FONT_TEXTURE.symAt, FontTexture.FONT_TEXTURE.symAtUnderline);
        this.registerCharacter('A', FontTexture.FONT_TEXTURE.letterUppercaseA, FontTexture.FONT_TEXTURE.letterUppercaseAUnderline);
        this.registerCharacter('B', FontTexture.FONT_TEXTURE.letterUppercaseB, FontTexture.FONT_TEXTURE.letterUppercaseBUnderline);
        this.registerCharacter('C', FontTexture.FONT_TEXTURE.letterUppercaseC, FontTexture.FONT_TEXTURE.letterUppercaseCUnderline);
        this.registerCharacter('D', FontTexture.FONT_TEXTURE.letterUppercaseD, FontTexture.FONT_TEXTURE.letterUppercaseDUnderline);
        this.registerCharacter('E', FontTexture.FONT_TEXTURE.letterUppercaseE, FontTexture.FONT_TEXTURE.letterUppercaseEUnderline);
        this.registerCharacter('F', FontTexture.FONT_TEXTURE.letterUppercaseF, FontTexture.FONT_TEXTURE.letterUppercaseFUnderline);
        this.registerCharacter('G', FontTexture.FONT_TEXTURE.letterUppercaseG, FontTexture.FONT_TEXTURE.letterUppercaseGUnderline);
        this.registerCharacter('H', FontTexture.FONT_TEXTURE.letterUppercaseH, FontTexture.FONT_TEXTURE.letterUppercaseHUnderline);
        this.registerCharacter('I', FontTexture.FONT_TEXTURE.letterUppercaseI, FontTexture.FONT_TEXTURE.letterUppercaseIUnderline);
        this.registerCharacter('J', FontTexture.FONT_TEXTURE.letterUppercaseJ, FontTexture.FONT_TEXTURE.letterUppercaseJUnderline);
        this.registerCharacter('K', FontTexture.FONT_TEXTURE.letterUppercaseK, FontTexture.FONT_TEXTURE.letterUppercaseKUnderline);
        this.registerCharacter('L', FontTexture.FONT_TEXTURE.letterUppercaseL, FontTexture.FONT_TEXTURE.letterUppercaseLUnderline);
        this.registerCharacter('M', FontTexture.FONT_TEXTURE.letterUppercaseM, FontTexture.FONT_TEXTURE.letterUppercaseMUnderline);
        this.registerCharacter('N', FontTexture.FONT_TEXTURE.letterUppercaseN, FontTexture.FONT_TEXTURE.letterUppercaseNUnderline);
        this.registerCharacter('O', FontTexture.FONT_TEXTURE.letterUppercaseO, FontTexture.FONT_TEXTURE.letterUppercaseOUnderline);

        this.registerCharacter('P', FontTexture.FONT_TEXTURE.letterUppercaseP, FontTexture.FONT_TEXTURE.letterUppercasePUnderline);
        this.registerCharacter('Q', FontTexture.FONT_TEXTURE.letterUppercaseQ, FontTexture.FONT_TEXTURE.letterUppercaseQUnderline);
        this.registerCharacter('R', FontTexture.FONT_TEXTURE.letterUppercaseR, FontTexture.FONT_TEXTURE.letterUppercaseRUnderline);
        this.registerCharacter('S', FontTexture.FONT_TEXTURE.letterUppercaseS, FontTexture.FONT_TEXTURE.letterUppercaseSUnderline);
        this.registerCharacter('T', FontTexture.FONT_TEXTURE.letterUppercaseT, FontTexture.FONT_TEXTURE.letterUppercaseTUnderline);
        this.registerCharacter('U', FontTexture.FONT_TEXTURE.letterUppercaseU, FontTexture.FONT_TEXTURE.letterUppercaseUUnderline);
        this.registerCharacter('V', FontTexture.FONT_TEXTURE.letterUppercaseV, FontTexture.FONT_TEXTURE.letterUppercaseVUnderline);
        this.registerCharacter('W', FontTexture.FONT_TEXTURE.letterUppercaseW, FontTexture.FONT_TEXTURE.letterUppercaseWUnderline);
        this.registerCharacter('X', FontTexture.FONT_TEXTURE.letterUppercaseX, FontTexture.FONT_TEXTURE.letterUppercaseXUnderline);
        this.registerCharacter('Y', FontTexture.FONT_TEXTURE.letterUppercaseY, FontTexture.FONT_TEXTURE.letterUppercaseYUnderline);
        this.registerCharacter('Z', FontTexture.FONT_TEXTURE.letterUppercaseZ, FontTexture.FONT_TEXTURE.letterUppercaseZUnderline); 
        this.registerCharacter('[', FontTexture.FONT_TEXTURE.symOpenBracket, FontTexture.FONT_TEXTURE.symOpenBracketUnderline);
        this.registerCharacter('\\', FontTexture.FONT_TEXTURE.symBackslash, FontTexture.FONT_TEXTURE.symBackslashUnderline);
        this.registerCharacter(']', FontTexture.FONT_TEXTURE.symCloseBracket, FontTexture.FONT_TEXTURE.symCloseBracketUnderline);
        this.registerCharacter('^', FontTexture.FONT_TEXTURE.symCaret, FontTexture.FONT_TEXTURE.symCaretUnderline);
        this.registerCharacter('_', FontTexture.FONT_TEXTURE.symUnderscore, FontTexture.FONT_TEXTURE.symUnderscoreUnderline);

        this.registerCharacter('`', FontTexture.FONT_TEXTURE.symGrave, FontTexture.FONT_TEXTURE.symGraveUnderline);
        this.registerCharacter('a', FontTexture.FONT_TEXTURE.letterLowercaseA, FontTexture.FONT_TEXTURE.letterLowercaseAUnderline);
        this.registerCharacter('b', FontTexture.FONT_TEXTURE.letterLowercaseB, FontTexture.FONT_TEXTURE.letterLowercaseBUnderline);
        this.registerCharacter('c', FontTexture.FONT_TEXTURE.letterLowercaseC, FontTexture.FONT_TEXTURE.letterLowercaseCUnderline);
        this.registerCharacter('d', FontTexture.FONT_TEXTURE.letterLowercaseD, FontTexture.FONT_TEXTURE.letterLowercaseDUnderline);
        this.registerCharacter('e', FontTexture.FONT_TEXTURE.letterLowercaseE, FontTexture.FONT_TEXTURE.letterLowercaseEUnderline);
        this.registerCharacter('f', FontTexture.FONT_TEXTURE.letterLowercaseF, FontTexture.FONT_TEXTURE.letterLowercaseFUnderline);
        this.registerCharacter('g', FontTexture.FONT_TEXTURE.letterLowercaseG, FontTexture.FONT_TEXTURE.letterLowercaseGUnderline);
        this.registerCharacter('h', FontTexture.FONT_TEXTURE.letterLowercaseH, FontTexture.FONT_TEXTURE.letterLowercaseHUnderline);
        this.registerCharacter('i', FontTexture.FONT_TEXTURE.letterLowercaseI, FontTexture.FONT_TEXTURE.letterLowercaseIUnderline);
        this.registerCharacter('j', FontTexture.FONT_TEXTURE.letterLowercaseJ, FontTexture.FONT_TEXTURE.letterLowercaseJUnderline);
        this.registerCharacter('k', FontTexture.FONT_TEXTURE.letterLowercaseK, FontTexture.FONT_TEXTURE.letterLowercaseKUnderline);
        this.registerCharacter('l', FontTexture.FONT_TEXTURE.letterLowercaseL, FontTexture.FONT_TEXTURE.letterLowercaseLUnderline);
        this.registerCharacter('m', FontTexture.FONT_TEXTURE.letterLowercaseM, FontTexture.FONT_TEXTURE.letterLowercaseMUnderline);
        this.registerCharacter('n', FontTexture.FONT_TEXTURE.letterLowercaseN, FontTexture.FONT_TEXTURE.letterLowercaseNUnderline);
        this.registerCharacter('o', FontTexture.FONT_TEXTURE.letterLowercaseO, FontTexture.FONT_TEXTURE.letterLowercaseOUnderline);

        this.registerCharacter('p', FontTexture.FONT_TEXTURE.letterLowercaseP, FontTexture.FONT_TEXTURE.letterLowercasePUnderline);
        this.registerCharacter('q', FontTexture.FONT_TEXTURE.letterLowercaseQ, FontTexture.FONT_TEXTURE.letterLowercaseQUnderline);
        this.registerCharacter('r', FontTexture.FONT_TEXTURE.letterLowercaseR, FontTexture.FONT_TEXTURE.letterLowercaseRUnderline);
        this.registerCharacter('s', FontTexture.FONT_TEXTURE.letterLowercaseS, FontTexture.FONT_TEXTURE.letterLowercaseSUnderline);
        this.registerCharacter('t', FontTexture.FONT_TEXTURE.letterLowercaseT, FontTexture.FONT_TEXTURE.letterLowercaseTUnderline);
        this.registerCharacter('u', FontTexture.FONT_TEXTURE.letterLowercaseU, FontTexture.FONT_TEXTURE.letterLowercaseUUnderline);
        this.registerCharacter('v', FontTexture.FONT_TEXTURE.letterLowercaseV, FontTexture.FONT_TEXTURE.letterLowercaseVUnderline);
        this.registerCharacter('w', FontTexture.FONT_TEXTURE.letterLowercaseW, FontTexture.FONT_TEXTURE.letterLowercaseWUnderline);
        this.registerCharacter('x', FontTexture.FONT_TEXTURE.letterLowercaseX, FontTexture.FONT_TEXTURE.letterLowercaseXUnderline);
        this.registerCharacter('y', FontTexture.FONT_TEXTURE.letterLowercaseY, FontTexture.FONT_TEXTURE.letterLowercaseYUnderline);
        this.registerCharacter('z', FontTexture.FONT_TEXTURE.letterLowercaseZ, FontTexture.FONT_TEXTURE.letterLowercaseZUnderline);
        this.registerCharacter('{', FontTexture.FONT_TEXTURE.symOpenBrace, FontTexture.FONT_TEXTURE.symCloseBrace);
        this.registerCharacter('|', FontTexture.FONT_TEXTURE.symPipe, FontTexture.FONT_TEXTURE.symPipeUnderline);
        this.registerCharacter('}', FontTexture.FONT_TEXTURE.symCloseBrace, FontTexture.FONT_TEXTURE.symCloseBraceUnderline);
        this.registerCharacter('~', FontTexture.FONT_TEXTURE.symTilde, FontTexture.FONT_TEXTURE.symTildeUnderline);
    }

    private void registerCharacter(
        char character, 
        TextureSample normalTextureSample,
        TextureSample underlineTextureSample
    ) {
        var fontcharacter = new FontCharacter(
            normalTextureSample,
            normalTextureSample,
            normalTextureSample,
            underlineTextureSample
        );

        this.fontCharacterLookup.put(character, fontcharacter);
    }

    public FontCharacter getFontCharacter(char character) {
        return this.fontCharacterLookup.getOrDefault(character, null);
    }

    public void writeText(SpriteBatch spriteBatch, String text, Vector2in position, SpriteBatchHeight height, FontDecoration fontDecoration, Vector3fl color) {
        var cursor = new Vector2in(position);
        for(var ix = 0; ix < text.length(); ix += 1) {
            var character = text.charAt(ix);
            var fontCharacter = this.getFontCharacter(character);
            if (fontCharacter != null) {
                var textureSample = fontCharacter.getTextureSample(fontDecoration);
                spriteBatch.writeSprite(
                    textureSample,
                    cursor,
                    Font.CHARACTER_DIMENSIONS,
                    height,
                    color
                );
            }
            cursor.x += CHARACTER_DIMENSIONS.x;
        }
    }
    
}
