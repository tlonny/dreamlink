package periwinkle.overlay;

import periwinkle.Game;
import periwinkle.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Glyph {

    public static Map<Character,Glyph> GLYPH_CHARACTER_LOOKUP = new HashMap<>();

    public static Glyph SPACE = new Glyph(' ', Game.OVERLAY_ATLAS.space, Game.OVERLAY_ATLAS.underlineSpace);

    public static Glyph SYM_BANG = new Glyph('!', Game.OVERLAY_ATLAS.bang, Game.OVERLAY_ATLAS.underlineBang);

    public static Glyph SYM_DOUBLE_QUOTES = new Glyph('"', Game.OVERLAY_ATLAS.doubleQuotes, Game.OVERLAY_ATLAS.underlineDoubleQuotes);

    public static Glyph SYM_HASH = new Glyph('#', Game.OVERLAY_ATLAS.hash, Game.OVERLAY_ATLAS.underlineHash);

    public static Glyph SYM_DOLLAR = new Glyph('$', Game.OVERLAY_ATLAS.dollar, Game.OVERLAY_ATLAS.underlineDollar);

    public static Glyph SYM_PERCENT = new Glyph('%', Game.OVERLAY_ATLAS.percent, Game.OVERLAY_ATLAS.underlinePercent);

    public static Glyph SYM_AMPERSAND = new Glyph('&', Game.OVERLAY_ATLAS.ampersand, Game.OVERLAY_ATLAS.underlineAmpersand);

    public static Glyph SYM_APOSTROPHE = new Glyph('\'', Game.OVERLAY_ATLAS.backSlash, Game.OVERLAY_ATLAS.underlineBackSlash);

    public static Glyph SYM_LEFT_PARENS = new Glyph('(', Game.OVERLAY_ATLAS.leftParens, Game.OVERLAY_ATLAS.underlineLeftParens);

    public static Glyph SYM_RIGHT_PARENS = new Glyph(')', Game.OVERLAY_ATLAS.rightParens, Game.OVERLAY_ATLAS.underlineRightParens);

    public static Glyph SYM_ASTERISK = new Glyph('*', Game.OVERLAY_ATLAS.asterisk, Game.OVERLAY_ATLAS.underlineAsterisk);

    public static Glyph SYM_PLUS = new Glyph('+', Game.OVERLAY_ATLAS.plus, Game.OVERLAY_ATLAS.underlinePlus);

    public static Glyph SYM_COMMA = new Glyph(',', Game.OVERLAY_ATLAS.comma, Game.OVERLAY_ATLAS.underlineComma);

    public static Glyph SYM_MINUS = new Glyph('-', Game.OVERLAY_ATLAS.dash, Game.OVERLAY_ATLAS.underlineDash);

    public static Glyph SYM_PERIOD = new Glyph('.', Game.OVERLAY_ATLAS.period, Game.OVERLAY_ATLAS.underlinePeriod);

    public static Glyph SYM_SLASH = new Glyph('/', Game.OVERLAY_ATLAS.slash, Game.OVERLAY_ATLAS.underlineSlash);

    public static Glyph NUM_0 = new Glyph('0', Game.OVERLAY_ATLAS.zero, Game.OVERLAY_ATLAS.underlineZero);

    public static Glyph NUM_1 = new Glyph('1', Game.OVERLAY_ATLAS.one, Game.OVERLAY_ATLAS.underlineOne);

    public static Glyph NUM_2 = new Glyph('2', Game.OVERLAY_ATLAS.two, Game.OVERLAY_ATLAS.underlineTwo);

    public static Glyph NUM_3 = new Glyph('3', Game.OVERLAY_ATLAS.three, Game.OVERLAY_ATLAS.underlineThree);

    public static Glyph NUM_4 = new Glyph('4', Game.OVERLAY_ATLAS.four, Game.OVERLAY_ATLAS.underlineFour);

    public static Glyph NUM_5 = new Glyph('5', Game.OVERLAY_ATLAS.five, Game.OVERLAY_ATLAS.underlineFive);

    public static Glyph NUM_6 = new Glyph('6', Game.OVERLAY_ATLAS.six, Game.OVERLAY_ATLAS.underlineSix);

    public static Glyph NUM_7 = new Glyph('7', Game.OVERLAY_ATLAS.seven, Game.OVERLAY_ATLAS.underlineSeven);

    public static Glyph NUM_8 = new Glyph('8', Game.OVERLAY_ATLAS.eight, Game.OVERLAY_ATLAS.underlineEight);

    public static Glyph NUM_9 = new Glyph('9', Game.OVERLAY_ATLAS.nine, Game.OVERLAY_ATLAS.underlineNine);

    public static Glyph SYM_COLON = new Glyph(':', Game.OVERLAY_ATLAS.colon, Game.OVERLAY_ATLAS.underlineColon);

    public static Glyph SYM_SEMICOLON = new Glyph(';', Game.OVERLAY_ATLAS.semiColon, Game.OVERLAY_ATLAS.underlineSemiColon);

    public static Glyph SYM_LESS_THAN = new Glyph('<', Game.OVERLAY_ATLAS.lessThan, Game.OVERLAY_ATLAS.underlineLessThan);

    public static Glyph SYM_EQUAL = new Glyph('=', Game.OVERLAY_ATLAS.equals, Game.OVERLAY_ATLAS.underlineEquals);

    public static Glyph SYM_GREATER_THAN = new Glyph('>', Game.OVERLAY_ATLAS.greaterThan, Game.OVERLAY_ATLAS.underlineGreaterThan);

    public static Glyph SYM_QUESTION = new Glyph('?', Game.OVERLAY_ATLAS.question, Game.OVERLAY_ATLAS.underlineQuestion);

    public static Glyph SYM_AT = new Glyph('@', Game.OVERLAY_ATLAS.at, Game.OVERLAY_ATLAS.underlineAt);

    public static Glyph CHAR_UPPER_A = new Glyph('A', Game.OVERLAY_ATLAS.upperA, Game.OVERLAY_ATLAS.underlineUpperA);

    public static Glyph CHAR_UPPER_B = new Glyph('B', Game.OVERLAY_ATLAS.upperB, Game.OVERLAY_ATLAS.underlineUpperB);

    public static Glyph CHAR_UPPER_C = new Glyph('C', Game.OVERLAY_ATLAS.upperC, Game.OVERLAY_ATLAS.underlineUpperC);

    public static Glyph CHAR_UPPER_D = new Glyph('D', Game.OVERLAY_ATLAS.upperD, Game.OVERLAY_ATLAS.underlineUpperD);

    public static Glyph CHAR_UPPER_E = new Glyph('E', Game.OVERLAY_ATLAS.upperE, Game.OVERLAY_ATLAS.underlineUpperE);

    public static Glyph CHAR_UPPER_F = new Glyph('F', Game.OVERLAY_ATLAS.upperF, Game.OVERLAY_ATLAS.underlineUpperF);

    public static Glyph CHAR_UPPER_G = new Glyph('G', Game.OVERLAY_ATLAS.upperG, Game.OVERLAY_ATLAS.underlineUpperG);

    public static Glyph CHAR_UPPER_H = new Glyph('H', Game.OVERLAY_ATLAS.upperH, Game.OVERLAY_ATLAS.underlineUpperH);

    public static Glyph CHAR_UPPER_I = new Glyph('I', Game.OVERLAY_ATLAS.upperI, Game.OVERLAY_ATLAS.underlineUpperI);

    public static Glyph CHAR_UPPER_J = new Glyph('J', Game.OVERLAY_ATLAS.upperJ, Game.OVERLAY_ATLAS.underlineUpperJ);

    public static Glyph CHAR_UPPER_K = new Glyph('K', Game.OVERLAY_ATLAS.upperK, Game.OVERLAY_ATLAS.underlineUpperK);

    public static Glyph CHAR_UPPER_L = new Glyph('L', Game.OVERLAY_ATLAS.upperL, Game.OVERLAY_ATLAS.underlineUpperL);

    public static Glyph CHAR_UPPER_M = new Glyph('M', Game.OVERLAY_ATLAS.upperM, Game.OVERLAY_ATLAS.underlineUpperM);

    public static Glyph CHAR_UPPER_N = new Glyph('N', Game.OVERLAY_ATLAS.upperN, Game.OVERLAY_ATLAS.underlineUpperN);

    public static Glyph CHAR_UPPER_O = new Glyph('O', Game.OVERLAY_ATLAS.upperO, Game.OVERLAY_ATLAS.underlineUpperO);

    public static Glyph CHAR_UPPER_P = new Glyph('P', Game.OVERLAY_ATLAS.upperP, Game.OVERLAY_ATLAS.underlineUpperP);

    public static Glyph CHAR_UPPER_Q = new Glyph('Q', Game.OVERLAY_ATLAS.upperQ, Game.OVERLAY_ATLAS.underlineUpperQ);

    public static Glyph CHAR_UPPER_R = new Glyph('R', Game.OVERLAY_ATLAS.upperR, Game.OVERLAY_ATLAS.underlineUpperR);

    public static Glyph CHAR_UPPER_S = new Glyph('S', Game.OVERLAY_ATLAS.upperS, Game.OVERLAY_ATLAS.underlineUpperS);

    public static Glyph CHAR_UPPER_T = new Glyph('T', Game.OVERLAY_ATLAS.upperT, Game.OVERLAY_ATLAS.underlineUpperT);

    public static Glyph CHAR_UPPER_U = new Glyph('U', Game.OVERLAY_ATLAS.upperU, Game.OVERLAY_ATLAS.underlineUpperU);

    public static Glyph CHAR_UPPER_V = new Glyph('V', Game.OVERLAY_ATLAS.upperV, Game.OVERLAY_ATLAS.underlineUpperV);

    public static Glyph CHAR_UPPER_W = new Glyph('W', Game.OVERLAY_ATLAS.upperW, Game.OVERLAY_ATLAS.underlineUpperW);

    public static Glyph CHAR_UPPER_X = new Glyph('X', Game.OVERLAY_ATLAS.upperX, Game.OVERLAY_ATLAS.underlineUpperX);

    public static Glyph CHAR_UPPER_Y = new Glyph('Y', Game.OVERLAY_ATLAS.upperY, Game.OVERLAY_ATLAS.underlineUpperY);

    public static Glyph CHAR_UPPER_Z = new Glyph('Z', Game.OVERLAY_ATLAS.upperZ, Game.OVERLAY_ATLAS.underlineUpperZ);

    public static Glyph SYM_LEFT_SQUARE_BRACKET = new Glyph('[', Game.OVERLAY_ATLAS.leftBracket, Game.OVERLAY_ATLAS.underlineLeftBracket);

    public static Glyph SYM_BACKSLASH = new Glyph('\\',Game.OVERLAY_ATLAS.backSlash, Game.OVERLAY_ATLAS.underlineBackSlash);

    public static Glyph SYM_RIGHT_SQUARE_BRACKET = new Glyph(']', Game.OVERLAY_ATLAS.rightBracket, Game.OVERLAY_ATLAS.underlineRightBracket);

    public static Glyph SYM_CARET = new Glyph('^', Game.OVERLAY_ATLAS.caret, Game.OVERLAY_ATLAS.underlineCaret);

    public static Glyph SYM_UNDERSCORE = new Glyph('_', Game.OVERLAY_ATLAS.underscore, Game.OVERLAY_ATLAS.underlineUnderscore);

    public static Glyph SYM_BACKTICK = new Glyph('`', Game.OVERLAY_ATLAS.backTick, Game.OVERLAY_ATLAS.underlineBackTick);

    public static Glyph CHAR_LOWER_A = new Glyph('a', Game.OVERLAY_ATLAS.lowerA, Game.OVERLAY_ATLAS.underlineLowerA);

    public static Glyph CHAR_LOWER_B = new Glyph('b', Game.OVERLAY_ATLAS.lowerB, Game.OVERLAY_ATLAS.underlineLowerB);

    public static Glyph CHAR_LOWER_C = new Glyph('c', Game.OVERLAY_ATLAS.lowerC, Game.OVERLAY_ATLAS.underlineLowerC);

    public static Glyph CHAR_LOWER_D = new Glyph('d', Game.OVERLAY_ATLAS.lowerD, Game.OVERLAY_ATLAS.underlineLowerD);

    public static Glyph CHAR_LOWER_E = new Glyph('e', Game.OVERLAY_ATLAS.lowerE, Game.OVERLAY_ATLAS.underlineLowerE);

    public static Glyph CHAR_LOWER_F = new Glyph('f', Game.OVERLAY_ATLAS.lowerF, Game.OVERLAY_ATLAS.underlineLowerF);

    public static Glyph CHAR_LOWER_G = new Glyph('g', Game.OVERLAY_ATLAS.lowerG, Game.OVERLAY_ATLAS.underlineLowerG);

    public static Glyph CHAR_LOWER_H = new Glyph('h', Game.OVERLAY_ATLAS.lowerH, Game.OVERLAY_ATLAS.underlineLowerH);

    public static Glyph CHAR_LOWER_I = new Glyph('i', Game.OVERLAY_ATLAS.lowerI, Game.OVERLAY_ATLAS.underlineLowerI);

    public static Glyph CHAR_LOWER_J = new Glyph('j', Game.OVERLAY_ATLAS.lowerJ, Game.OVERLAY_ATLAS.underlineLowerJ);

    public static Glyph CHAR_LOWER_K = new Glyph('k', Game.OVERLAY_ATLAS.lowerK, Game.OVERLAY_ATLAS.underlineLowerK);

    public static Glyph CHAR_LOWER_L = new Glyph('l', Game.OVERLAY_ATLAS.lowerL, Game.OVERLAY_ATLAS.underlineLowerL);

    public static Glyph CHAR_LOWER_M = new Glyph('m', Game.OVERLAY_ATLAS.lowerM, Game.OVERLAY_ATLAS.underlineLowerM);

    public static Glyph CHAR_LOWER_N = new Glyph('n', Game.OVERLAY_ATLAS.lowerN, Game.OVERLAY_ATLAS.underlineLowerN);

    public static Glyph CHAR_LOWER_O = new Glyph('o', Game.OVERLAY_ATLAS.lowerO, Game.OVERLAY_ATLAS.underlineLowerO);

    public static Glyph CHAR_LOWER_P = new Glyph('p', Game.OVERLAY_ATLAS.lowerP, Game.OVERLAY_ATLAS.underlineLowerP);

    public static Glyph CHAR_LOWER_Q = new Glyph('q', Game.OVERLAY_ATLAS.lowerQ, Game.OVERLAY_ATLAS.underlineLowerQ);

    public static Glyph CHAR_LOWER_R = new Glyph('r', Game.OVERLAY_ATLAS.lowerR, Game.OVERLAY_ATLAS.underlineLowerR);

    public static Glyph CHAR_LOWER_S = new Glyph('s', Game.OVERLAY_ATLAS.lowerS, Game.OVERLAY_ATLAS.underlineLowerS);

    public static Glyph CHAR_LOWER_T = new Glyph('t', Game.OVERLAY_ATLAS.lowerT, Game.OVERLAY_ATLAS.underlineLowerT);

    public static Glyph CHAR_LOWER_U = new Glyph('u', Game.OVERLAY_ATLAS.lowerU, Game.OVERLAY_ATLAS.underlineLowerU);

    public static Glyph CHAR_LOWER_V = new Glyph('v', Game.OVERLAY_ATLAS.lowerV, Game.OVERLAY_ATLAS.underlineLowerV);

    public static Glyph CHAR_LOWER_W = new Glyph('w', Game.OVERLAY_ATLAS.lowerW, Game.OVERLAY_ATLAS.underlineLowerW);

    public static Glyph CHAR_LOWER_X = new Glyph('x', Game.OVERLAY_ATLAS.lowerX, Game.OVERLAY_ATLAS.underlineLowerX);

    public static Glyph CHAR_LOWER_Y = new Glyph('y', Game.OVERLAY_ATLAS.lowerY, Game.OVERLAY_ATLAS.underlineLowerY);

    public static Glyph CHAR_LOWER_Z = new Glyph('z', Game.OVERLAY_ATLAS.lowerZ, Game.OVERLAY_ATLAS.underlineLowerZ);

    public static Glyph SYM_LEFT_BRACE = new Glyph('{', Game.OVERLAY_ATLAS.leftBrace, Game.OVERLAY_ATLAS.underlineLeftBrace);

    public static Glyph SYM_PIPE = new Glyph('|', Game.OVERLAY_ATLAS.pipe, Game.OVERLAY_ATLAS.underlinePipe);

    public static Glyph SYM_RIGHT_BRACE = new Glyph('}', Game.OVERLAY_ATLAS.rightBrace, Game.OVERLAY_ATLAS.underlineRightBrace);

    public static Glyph SYM_TILDE = new Glyph('~', Game.OVERLAY_ATLAS.tilde, Game.OVERLAY_ATLAS.underlineTilde);

    public Texture texture;
    public Texture underlineTexture;
    public char character;

    public Glyph(char character, Texture texture, Texture underlineSprite) {
        this.character = character;;
        this.texture = texture;
        this.underlineTexture = underlineSprite;
        GLYPH_CHARACTER_LOOKUP.put(this.character, this);
    }
}
