package dreamlink.graphics.text;

import java.util.HashMap;
import java.util.Map;

import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.graphics.texture.sample.TextureSample;

public class CharacterTextureSampleLookup {

    public static final CharacterTextureSampleLookup instance = new CharacterTextureSampleLookup();

    private final Map<Character, CharacterTextureConfig> glyphLookup;

    public CharacterTextureSampleLookup() {
        this.glyphLookup = new HashMap<>();
        this.addCharacterEntry(' ', MenuTextureSample.symSpace, MenuTextureSample.symSpaceUnderline);
        this.addCharacterEntry('!', MenuTextureSample.symExclamation, MenuTextureSample.symExclamationUnderline);
        this.addCharacterEntry('"', MenuTextureSample.symDoubleQuotes, MenuTextureSample.symDoubleQuotesUnderline);
        this.addCharacterEntry('#', MenuTextureSample.symHash, MenuTextureSample.symHashUnderline);
        this.addCharacterEntry('$', MenuTextureSample.symDollar, MenuTextureSample.symDollarUnderline);
        this.addCharacterEntry('%', MenuTextureSample.symPercent, MenuTextureSample.symPercentUnderline);
        this.addCharacterEntry('&', MenuTextureSample.symAmpersand, MenuTextureSample.symAmpersandUnderline);
        this.addCharacterEntry('\'', MenuTextureSample.symSingleQuote, MenuTextureSample.symSingleQuoteUnderline);
        this.addCharacterEntry('(', MenuTextureSample.symOpenParenthesis, MenuTextureSample.symOpenParenthesisUnderline);
        this.addCharacterEntry(')', MenuTextureSample.symCloseParenthesis, MenuTextureSample.symCloseParenthesisUnderline);
        this.addCharacterEntry('*', MenuTextureSample.symAsterisk, MenuTextureSample.symAsteriskUnderline);
        this.addCharacterEntry('+', MenuTextureSample.symPlus, MenuTextureSample.symPlusUnderline);
        this.addCharacterEntry(',', MenuTextureSample.symComma, MenuTextureSample.symCommaUnderline);
        this.addCharacterEntry('-', MenuTextureSample.symMinus, MenuTextureSample.symMinusUnderline);
        this.addCharacterEntry('.', MenuTextureSample.symPeriod, MenuTextureSample.symPeriodUnderline);
        this.addCharacterEntry('/', MenuTextureSample.symSlash, MenuTextureSample.symSlashUnderline);

        this.addCharacterEntry('0', MenuTextureSample.digit0, MenuTextureSample.digit0Underline);
        this.addCharacterEntry('1', MenuTextureSample.digit1, MenuTextureSample.digit1Underline);
        this.addCharacterEntry('2', MenuTextureSample.digit2, MenuTextureSample.digit2Underline);
        this.addCharacterEntry('3', MenuTextureSample.digit3, MenuTextureSample.digit3Underline);
        this.addCharacterEntry('4', MenuTextureSample.digit4, MenuTextureSample.digit4Underline);
        this.addCharacterEntry('5', MenuTextureSample.digit5, MenuTextureSample.digit5Underline);
        this.addCharacterEntry('6', MenuTextureSample.digit6, MenuTextureSample.digit6Underline);
        this.addCharacterEntry('7', MenuTextureSample.digit7, MenuTextureSample.digit7Underline);
        this.addCharacterEntry('8', MenuTextureSample.digit8, MenuTextureSample.digit8Underline);
        this.addCharacterEntry('9', MenuTextureSample.digit9, MenuTextureSample.digit9Underline);
        this.addCharacterEntry(':', MenuTextureSample.symColon, MenuTextureSample.symColonUnderline);
        this.addCharacterEntry(';', MenuTextureSample.symSemicolon, MenuTextureSample.symSemicolonUnderline);
        this.addCharacterEntry('<', MenuTextureSample.symLessThan, MenuTextureSample.symLessThanUnderline);
        this.addCharacterEntry('=', MenuTextureSample.symEquals, MenuTextureSample.symEqualsUnderline);
        this.addCharacterEntry('>', MenuTextureSample.symGreaterThan, MenuTextureSample.symGreaterThanUnderline);
        this.addCharacterEntry('?', MenuTextureSample.symQuestion, MenuTextureSample.symQuestionUnderline);

        this.addCharacterEntry('@', MenuTextureSample.symAt, MenuTextureSample.symAtUnderline);
        this.addCharacterEntry('A', MenuTextureSample.letterUpperCaseA, MenuTextureSample.letterUpperCaseAUnderline);
        this.addCharacterEntry('B', MenuTextureSample.letterUpperCaseB, MenuTextureSample.letterUpperCaseBUnderline);
        this.addCharacterEntry('C', MenuTextureSample.letterUpperCaseC, MenuTextureSample.letterUpperCaseCUnderline);
        this.addCharacterEntry('D', MenuTextureSample.letterUpperCaseD, MenuTextureSample.letterUpperCaseDUnderline);
        this.addCharacterEntry('E', MenuTextureSample.letterUpperCaseE, MenuTextureSample.letterUpperCaseEUnderline);
        this.addCharacterEntry('F', MenuTextureSample.letterUpperCaseF, MenuTextureSample.letterUpperCaseFUnderline);
        this.addCharacterEntry('G', MenuTextureSample.letterUpperCaseG, MenuTextureSample.letterUpperCaseGUnderline);
        this.addCharacterEntry('H', MenuTextureSample.letterUpperCaseH, MenuTextureSample.letterUpperCaseHUnderline);
        this.addCharacterEntry('I', MenuTextureSample.letterUpperCaseI, MenuTextureSample.letterUpperCaseIUnderline);
        this.addCharacterEntry('J', MenuTextureSample.letterUpperCaseJ, MenuTextureSample.letterUpperCaseJUnderline);
        this.addCharacterEntry('K', MenuTextureSample.letterUpperCaseK, MenuTextureSample.letterUpperCaseKUnderline);
        this.addCharacterEntry('L', MenuTextureSample.letterUpperCaseL, MenuTextureSample.letterUpperCaseLUnderline);
        this.addCharacterEntry('M', MenuTextureSample.letterUpperCaseM, MenuTextureSample.letterUpperCaseMUnderline);
        this.addCharacterEntry('N', MenuTextureSample.letterUpperCaseN, MenuTextureSample.letterUpperCaseNUnderline);
        this.addCharacterEntry('O', MenuTextureSample.letterUpperCaseO, MenuTextureSample.letterUpperCaseOUnderline);

        this.addCharacterEntry('P', MenuTextureSample.letterUpperCaseP, MenuTextureSample.letterUpperCasePUnderline);
        this.addCharacterEntry('Q', MenuTextureSample.letterUpperCaseQ, MenuTextureSample.letterUpperCaseQUnderline);
        this.addCharacterEntry('R', MenuTextureSample.letterUpperCaseR, MenuTextureSample.letterUpperCaseRUnderline);
        this.addCharacterEntry('S', MenuTextureSample.letterUpperCaseS, MenuTextureSample.letterUpperCaseSUnderline);
        this.addCharacterEntry('T', MenuTextureSample.letterUpperCaseT, MenuTextureSample.letterUpperCaseTUnderline);
        this.addCharacterEntry('U', MenuTextureSample.letterUpperCaseU, MenuTextureSample.letterUpperCaseUUnderline);
        this.addCharacterEntry('V', MenuTextureSample.letterUpperCaseV, MenuTextureSample.letterUpperCaseVUnderline);
        this.addCharacterEntry('W', MenuTextureSample.letterUpperCaseW, MenuTextureSample.letterUpperCaseWUnderline);
        this.addCharacterEntry('X', MenuTextureSample.letterUpperCaseX, MenuTextureSample.letterUpperCaseXUnderline);
        this.addCharacterEntry('Y', MenuTextureSample.letterUpperCaseY, MenuTextureSample.letterUpperCaseYUnderline);
        this.addCharacterEntry('Z', MenuTextureSample.letterUpperCaseZ, MenuTextureSample.letterUpperCaseZUnderline); 
        this.addCharacterEntry('[', MenuTextureSample.symOpenBracket, MenuTextureSample.symOpenBracketUnderline);
        this.addCharacterEntry('\\', MenuTextureSample.symBackslash, MenuTextureSample.symBackslashUnderline);
        this.addCharacterEntry(']', MenuTextureSample.symCloseBracket, MenuTextureSample.symCloseBracketUnderline);
        this.addCharacterEntry('^', MenuTextureSample.symCaret, MenuTextureSample.symCaretUnderline);
        this.addCharacterEntry('_', MenuTextureSample.symUnderscore, MenuTextureSample.symUnderscoreUnderline);

        this.addCharacterEntry('`', MenuTextureSample.symBacktick, MenuTextureSample.symBacktickUnderline);
        this.addCharacterEntry('a', MenuTextureSample.letterLowerCaseA, MenuTextureSample.letterLowerCaseAUnderline);
        this.addCharacterEntry('b', MenuTextureSample.letterLowerCaseB, MenuTextureSample.letterLowerCaseBUnderline);
        this.addCharacterEntry('c', MenuTextureSample.letterLowerCaseC, MenuTextureSample.letterLowerCaseCUnderline);
        this.addCharacterEntry('d', MenuTextureSample.letterLowerCaseD, MenuTextureSample.letterLowerCaseDUnderline);
        this.addCharacterEntry('e', MenuTextureSample.letterLowerCaseE, MenuTextureSample.letterLowerCaseEUnderline);
        this.addCharacterEntry('f', MenuTextureSample.letterLowerCaseF, MenuTextureSample.letterLowerCaseFUnderline);
        this.addCharacterEntry('g', MenuTextureSample.letterLowerCaseG, MenuTextureSample.letterLowerCaseGUnderline);
        this.addCharacterEntry('h', MenuTextureSample.letterLowerCaseH, MenuTextureSample.letterLowerCaseHUnderline);
        this.addCharacterEntry('i', MenuTextureSample.letterLowerCaseI, MenuTextureSample.letterLowerCaseIUnderline);
        this.addCharacterEntry('j', MenuTextureSample.letterLowerCaseJ, MenuTextureSample.letterLowerCaseJUnderline);
        this.addCharacterEntry('k', MenuTextureSample.letterLowerCaseK, MenuTextureSample.letterLowerCaseKUnderline);
        this.addCharacterEntry('l', MenuTextureSample.letterLowerCaseL, MenuTextureSample.letterLowerCaseLUnderline);
        this.addCharacterEntry('m', MenuTextureSample.letterLowerCaseM, MenuTextureSample.letterLowerCaseMUnderline);
        this.addCharacterEntry('n', MenuTextureSample.letterLowerCaseN, MenuTextureSample.letterLowerCaseNUnderline);
        this.addCharacterEntry('o', MenuTextureSample.letterLowerCaseO, MenuTextureSample.letterLowerCaseOUnderline);

        this.addCharacterEntry('p', MenuTextureSample.letterLowerCaseP, MenuTextureSample.letterLowerCasePUnderline);
        this.addCharacterEntry('q', MenuTextureSample.letterLowerCaseQ, MenuTextureSample.letterLowerCaseQUnderline);
        this.addCharacterEntry('r', MenuTextureSample.letterLowerCaseR, MenuTextureSample.letterLowerCaseRUnderline);
        this.addCharacterEntry('s', MenuTextureSample.letterLowerCaseS, MenuTextureSample.letterLowerCaseSUnderline);
        this.addCharacterEntry('t', MenuTextureSample.letterLowerCaseT, MenuTextureSample.letterLowerCaseTUnderline);
        this.addCharacterEntry('u', MenuTextureSample.letterLowerCaseU, MenuTextureSample.letterLowerCaseUUnderline);
        this.addCharacterEntry('v', MenuTextureSample.letterLowerCaseV, MenuTextureSample.letterLowerCaseVUnderline);
        this.addCharacterEntry('w', MenuTextureSample.letterLowerCaseW, MenuTextureSample.letterLowerCaseWUnderline);
        this.addCharacterEntry('x', MenuTextureSample.letterLowerCaseX, MenuTextureSample.letterLowerCaseXUnderline);
        this.addCharacterEntry('y', MenuTextureSample.letterLowerCaseY, MenuTextureSample.letterLowerCaseYUnderline);
        this.addCharacterEntry('z', MenuTextureSample.letterLowerCaseZ, MenuTextureSample.letterLowerCaseZUnderline);
        this.addCharacterEntry('{', MenuTextureSample.symOpenBrace, MenuTextureSample.symCloseBrace);
        this.addCharacterEntry('|', MenuTextureSample.symPipe, MenuTextureSample.symPipeUnderline);
        this.addCharacterEntry('}', MenuTextureSample.symCloseBrace, MenuTextureSample.symCloseBraceUnderline);
        this.addCharacterEntry('~', MenuTextureSample.symTilde, MenuTextureSample.symTildeUnderline);
    }

    private void addCharacterEntry(
        char character, 
        TextureSample textureSample, 
        TextureSample underlineTextureSample
    ) {
        this.glyphLookup.put(character, new CharacterTextureConfig(textureSample, underlineTextureSample));
    }

    public TextureSample getTextureSample(char character, FontDecoration fontDecoration) {
        var config = this.glyphLookup.get(character);
        if(config == null) {
            return null;
        }

        if(fontDecoration == FontDecoration.underline) {
            return config.underlineTextureSample;
        }

        return config.normalTextureSample;
    }
    
}
