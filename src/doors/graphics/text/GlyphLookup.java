package doors.graphics.text;

import java.util.HashMap;
import java.util.Map;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.FontTexture;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class GlyphLookup {

    public static GlyphLookup GLYPH_LOOKUP = new GlyphLookup();

    public Map<Character, Glyph> glyphLookup = new HashMap<>();

    private Vector2in positionCursor = new Vector2in();

    public GlyphLookup() {
        this.glyphLookup.put(' ', new Glyph(FontTexture.FONT_TEXTURE.symSpace, FontTexture.FONT_TEXTURE.symSpaceUnderline));
        this.glyphLookup.put('!', new Glyph(FontTexture.FONT_TEXTURE.symBang, FontTexture.FONT_TEXTURE.symBangUnderline));
        this.glyphLookup.put('"', new Glyph(FontTexture.FONT_TEXTURE.symDoubleQuotes, FontTexture.FONT_TEXTURE.symDoubleQuotesUnderline));
        this.glyphLookup.put('#', new Glyph(FontTexture.FONT_TEXTURE.symHash, FontTexture.FONT_TEXTURE.symHashUnderline));
        this.glyphLookup.put('$', new Glyph(FontTexture.FONT_TEXTURE.symDollar, FontTexture.FONT_TEXTURE.symDollarUnderline));
        this.glyphLookup.put('%', new Glyph(FontTexture.FONT_TEXTURE.symPercent, FontTexture.FONT_TEXTURE.symPercentUnderline));
        this.glyphLookup.put('&', new Glyph(FontTexture.FONT_TEXTURE.symAmpersand, FontTexture.FONT_TEXTURE.symAmpersandUnderline));
        this.glyphLookup.put('\'', new Glyph(FontTexture.FONT_TEXTURE.symSingleQuote, FontTexture.FONT_TEXTURE.symSingleQuoteUnderline));
        this.glyphLookup.put('(', new Glyph(FontTexture.FONT_TEXTURE.symOpenParen, FontTexture.FONT_TEXTURE.symOpenParenUnderline));
        this.glyphLookup.put(')', new Glyph(FontTexture.FONT_TEXTURE.symCloseParen, FontTexture.FONT_TEXTURE.symCloseParenUnderline));
        this.glyphLookup.put('*', new Glyph(FontTexture.FONT_TEXTURE.symStar, FontTexture.FONT_TEXTURE.symStarUnderline));
        this.glyphLookup.put('+', new Glyph(FontTexture.FONT_TEXTURE.symPlus, FontTexture.FONT_TEXTURE.symPlusUnderline));
        this.glyphLookup.put(',', new Glyph(FontTexture.FONT_TEXTURE.symComma, FontTexture.FONT_TEXTURE.symCommaUnderline));
        this.glyphLookup.put('-', new Glyph(FontTexture.FONT_TEXTURE.symMinus, FontTexture.FONT_TEXTURE.symMinusUnderline));
        this.glyphLookup.put('.', new Glyph(FontTexture.FONT_TEXTURE.symPeriod, FontTexture.FONT_TEXTURE.symPeriodUnderline));
        this.glyphLookup.put('/', new Glyph(FontTexture.FONT_TEXTURE.symSlash, FontTexture.FONT_TEXTURE.symSlashUnderline));

        this.glyphLookup.put('0', new Glyph(FontTexture.FONT_TEXTURE.digit0, FontTexture.FONT_TEXTURE.digit0Underline));
        this.glyphLookup.put('1', new Glyph(FontTexture.FONT_TEXTURE.digit1, FontTexture.FONT_TEXTURE.digit1Underline));
        this.glyphLookup.put('2', new Glyph(FontTexture.FONT_TEXTURE.digit2, FontTexture.FONT_TEXTURE.digit2Underline));
        this.glyphLookup.put('3', new Glyph(FontTexture.FONT_TEXTURE.digit3, FontTexture.FONT_TEXTURE.digit3Underline));
        this.glyphLookup.put('4', new Glyph(FontTexture.FONT_TEXTURE.digit4, FontTexture.FONT_TEXTURE.digit4Underline));
        this.glyphLookup.put('5', new Glyph(FontTexture.FONT_TEXTURE.digit5, FontTexture.FONT_TEXTURE.digit5Underline));
        this.glyphLookup.put('6', new Glyph(FontTexture.FONT_TEXTURE.digit6, FontTexture.FONT_TEXTURE.digit6Underline));
        this.glyphLookup.put('7', new Glyph(FontTexture.FONT_TEXTURE.digit7, FontTexture.FONT_TEXTURE.digit7Underline));
        this.glyphLookup.put('8', new Glyph(FontTexture.FONT_TEXTURE.digit8, FontTexture.FONT_TEXTURE.digit8Underline));
        this.glyphLookup.put('9', new Glyph(FontTexture.FONT_TEXTURE.digit9, FontTexture.FONT_TEXTURE.digit9Underline));
        this.glyphLookup.put(':', new Glyph(FontTexture.FONT_TEXTURE.symColon, FontTexture.FONT_TEXTURE.symColonUnderline));
        this.glyphLookup.put(';', new Glyph(FontTexture.FONT_TEXTURE.symSemicolon, FontTexture.FONT_TEXTURE.symSemicolonUnderline));
        this.glyphLookup.put('<', new Glyph(FontTexture.FONT_TEXTURE.symLessThan, FontTexture.FONT_TEXTURE.symLessThanUnderline));
        this.glyphLookup.put('=', new Glyph(FontTexture.FONT_TEXTURE.symEquals, FontTexture.FONT_TEXTURE.symEqualsUnderline));
        this.glyphLookup.put('>', new Glyph(FontTexture.FONT_TEXTURE.symGreaterThan, FontTexture.FONT_TEXTURE.symGreaterThanUnderline));
        this.glyphLookup.put('?', new Glyph(FontTexture.FONT_TEXTURE.symQuestionMark, FontTexture.FONT_TEXTURE.symQuestionMarkUnderline));

        this.glyphLookup.put('@', new Glyph(FontTexture.FONT_TEXTURE.symAt, FontTexture.FONT_TEXTURE.symAtUnderline));
        this.glyphLookup.put('A', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseA, FontTexture.FONT_TEXTURE.letterUppercaseAUnderline));
        this.glyphLookup.put('B', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseB, FontTexture.FONT_TEXTURE.letterUppercaseBUnderline));
        this.glyphLookup.put('C', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseC, FontTexture.FONT_TEXTURE.letterUppercaseCUnderline));
        this.glyphLookup.put('D', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseD, FontTexture.FONT_TEXTURE.letterUppercaseDUnderline));
        this.glyphLookup.put('E', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseE, FontTexture.FONT_TEXTURE.letterUppercaseEUnderline));
        this.glyphLookup.put('F', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseF, FontTexture.FONT_TEXTURE.letterUppercaseFUnderline));
        this.glyphLookup.put('G', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseG, FontTexture.FONT_TEXTURE.letterUppercaseGUnderline));
        this.glyphLookup.put('H', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseH, FontTexture.FONT_TEXTURE.letterUppercaseHUnderline));
        this.glyphLookup.put('I', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseI, FontTexture.FONT_TEXTURE.letterUppercaseIUnderline));
        this.glyphLookup.put('J', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseJ, FontTexture.FONT_TEXTURE.letterUppercaseJUnderline));
        this.glyphLookup.put('K', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseK, FontTexture.FONT_TEXTURE.letterUppercaseKUnderline));
        this.glyphLookup.put('L', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseL, FontTexture.FONT_TEXTURE.letterUppercaseLUnderline));
        this.glyphLookup.put('M', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseM, FontTexture.FONT_TEXTURE.letterUppercaseMUnderline));
        this.glyphLookup.put('N', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseN, FontTexture.FONT_TEXTURE.letterUppercaseNUnderline));
        this.glyphLookup.put('O', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseO, FontTexture.FONT_TEXTURE.letterUppercaseOUnderline));

        this.glyphLookup.put('P', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseP, FontTexture.FONT_TEXTURE.letterUppercasePUnderline));
        this.glyphLookup.put('Q', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseQ, FontTexture.FONT_TEXTURE.letterUppercaseQUnderline));
        this.glyphLookup.put('R', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseR, FontTexture.FONT_TEXTURE.letterUppercaseRUnderline));
        this.glyphLookup.put('S', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseS, FontTexture.FONT_TEXTURE.letterUppercaseSUnderline));
        this.glyphLookup.put('T', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseT, FontTexture.FONT_TEXTURE.letterUppercaseTUnderline));
        this.glyphLookup.put('U', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseU, FontTexture.FONT_TEXTURE.letterUppercaseUUnderline));
        this.glyphLookup.put('V', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseV, FontTexture.FONT_TEXTURE.letterUppercaseVUnderline));
        this.glyphLookup.put('W', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseW, FontTexture.FONT_TEXTURE.letterUppercaseWUnderline));
        this.glyphLookup.put('X', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseX, FontTexture.FONT_TEXTURE.letterUppercaseXUnderline));
        this.glyphLookup.put('Y', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseY, FontTexture.FONT_TEXTURE.letterUppercaseYUnderline));
        this.glyphLookup.put('Z', new Glyph(FontTexture.FONT_TEXTURE.letterUppercaseZ, FontTexture.FONT_TEXTURE.letterUppercaseZUnderline)); 
        this.glyphLookup.put('[', new Glyph(FontTexture.FONT_TEXTURE.symOpenBracket, FontTexture.FONT_TEXTURE.symOpenBracketUnderline));
        this.glyphLookup.put('\\', new Glyph(FontTexture.FONT_TEXTURE.symBackslash, FontTexture.FONT_TEXTURE.symBackslashUnderline));
        this.glyphLookup.put(']', new Glyph(FontTexture.FONT_TEXTURE.symCloseBracket, FontTexture.FONT_TEXTURE.symCloseBracketUnderline));
        this.glyphLookup.put('^', new Glyph(FontTexture.FONT_TEXTURE.symCaret, FontTexture.FONT_TEXTURE.symCaretUnderline));
        this.glyphLookup.put('_', new Glyph(FontTexture.FONT_TEXTURE.symUnderscore, FontTexture.FONT_TEXTURE.symUnderscoreUnderline));

        this.glyphLookup.put('`', new Glyph(FontTexture.FONT_TEXTURE.symGrave, FontTexture.FONT_TEXTURE.symGraveUnderline));
        this.glyphLookup.put('a', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseA, FontTexture.FONT_TEXTURE.letterLowercaseAUnderline));
        this.glyphLookup.put('b', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseB, FontTexture.FONT_TEXTURE.letterLowercaseBUnderline));
        this.glyphLookup.put('c', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseC, FontTexture.FONT_TEXTURE.letterLowercaseCUnderline));
        this.glyphLookup.put('d', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseD, FontTexture.FONT_TEXTURE.letterLowercaseDUnderline));
        this.glyphLookup.put('e', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseE, FontTexture.FONT_TEXTURE.letterLowercaseEUnderline));
        this.glyphLookup.put('f', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseF, FontTexture.FONT_TEXTURE.letterLowercaseFUnderline));
        this.glyphLookup.put('g', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseG, FontTexture.FONT_TEXTURE.letterLowercaseGUnderline));
        this.glyphLookup.put('h', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseH, FontTexture.FONT_TEXTURE.letterLowercaseHUnderline));
        this.glyphLookup.put('i', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseI, FontTexture.FONT_TEXTURE.letterLowercaseIUnderline));
        this.glyphLookup.put('j', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseJ, FontTexture.FONT_TEXTURE.letterLowercaseJUnderline));
        this.glyphLookup.put('k', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseK, FontTexture.FONT_TEXTURE.letterLowercaseKUnderline));
        this.glyphLookup.put('l', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseL, FontTexture.FONT_TEXTURE.letterLowercaseLUnderline));
        this.glyphLookup.put('m', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseM, FontTexture.FONT_TEXTURE.letterLowercaseMUnderline));
        this.glyphLookup.put('n', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseN, FontTexture.FONT_TEXTURE.letterLowercaseNUnderline));
        this.glyphLookup.put('o', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseO, FontTexture.FONT_TEXTURE.letterLowercaseOUnderline));

        this.glyphLookup.put('p', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseP, FontTexture.FONT_TEXTURE.letterLowercasePUnderline));
        this.glyphLookup.put('q', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseQ, FontTexture.FONT_TEXTURE.letterLowercaseQUnderline));
        this.glyphLookup.put('r', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseR, FontTexture.FONT_TEXTURE.letterLowercaseRUnderline));
        this.glyphLookup.put('s', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseS, FontTexture.FONT_TEXTURE.letterLowercaseSUnderline));
        this.glyphLookup.put('t', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseT, FontTexture.FONT_TEXTURE.letterLowercaseTUnderline));
        this.glyphLookup.put('u', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseU, FontTexture.FONT_TEXTURE.letterLowercaseUUnderline));
        this.glyphLookup.put('v', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseV, FontTexture.FONT_TEXTURE.letterLowercaseVUnderline));
        this.glyphLookup.put('w', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseW, FontTexture.FONT_TEXTURE.letterLowercaseWUnderline));
        this.glyphLookup.put('x', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseX, FontTexture.FONT_TEXTURE.letterLowercaseXUnderline));
        this.glyphLookup.put('y', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseY, FontTexture.FONT_TEXTURE.letterLowercaseYUnderline));
        this.glyphLookup.put('z', new Glyph(FontTexture.FONT_TEXTURE.letterLowercaseZ, FontTexture.FONT_TEXTURE.letterLowercaseZUnderline));
        this.glyphLookup.put('{', new Glyph(FontTexture.FONT_TEXTURE.symOpenBrace, FontTexture.FONT_TEXTURE.symCloseBrace));
        this.glyphLookup.put('|', new Glyph(FontTexture.FONT_TEXTURE.symPipe, FontTexture.FONT_TEXTURE.symPipeUnderline));
        this.glyphLookup.put('}', new Glyph(FontTexture.FONT_TEXTURE.symCloseBrace, FontTexture.FONT_TEXTURE.symCloseBraceUnderline));
        this.glyphLookup.put('~', new Glyph(FontTexture.FONT_TEXTURE.symTilde, FontTexture.FONT_TEXTURE.symTildeUnderline));
    }

    public void writeTextToSpriteBatch(
        SpriteBatch spriteBatch,
        String text,
        Vector2in position,
        SpriteBatchHeight height,
        FontDecoration fontDecoration,
        Vector3fl color
    ) {
        this.positionCursor.set(position);
        for(var ix = 0; ix < text.length(); ix += 1) {
            var character = text.charAt(ix);
            this.glyphLookup.get(character).writeGlyphToSpriteBatch(
                spriteBatch,
                this.positionCursor,
                height,
                fontDecoration,
                color
            );
            this.positionCursor.x += Glyph.GLYPH_DIMENSIONS.x;
        }
    }
    
}
