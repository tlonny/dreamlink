package doors.overlay;

import doors.Game;
import doors.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Glyph {

    public static Map<Character,Glyph> GLYPH_CHARACTER_LOOKUP = new HashMap<>();

    public static Glyph SPACE = new Glyph(' ', Game.OVERLAY_TEXTURE.space, Game.OVERLAY_TEXTURE.underlineSpace);

    public static Glyph SYM_BANG = new Glyph('!', Game.OVERLAY_TEXTURE.bang, Game.OVERLAY_TEXTURE.underlineBang);

    public static Glyph SYM_DOUBLE_QUOTES = new Glyph('"', Game.OVERLAY_TEXTURE.doubleQuotes, Game.OVERLAY_TEXTURE.underlineDoubleQuotes);

    public static Glyph SYM_HASH = new Glyph('#', Game.OVERLAY_TEXTURE.hash, Game.OVERLAY_TEXTURE.underlineHash);

    public static Glyph SYM_DOLLAR = new Glyph('$', Game.OVERLAY_TEXTURE.dollar, Game.OVERLAY_TEXTURE.underlineDollar);

    public static Glyph SYM_PERCENT = new Glyph('%', Game.OVERLAY_TEXTURE.percent, Game.OVERLAY_TEXTURE.underlinePercent);

    public static Glyph SYM_AMPERSAND = new Glyph('&', Game.OVERLAY_TEXTURE.ampersand, Game.OVERLAY_TEXTURE.underlineAmpersand);

    public static Glyph SYM_APOSTROPHE = new Glyph('\'', Game.OVERLAY_TEXTURE.backSlash, Game.OVERLAY_TEXTURE.underlineBackSlash);

    public static Glyph SYM_LEFT_PARENS = new Glyph('(', Game.OVERLAY_TEXTURE.leftParens, Game.OVERLAY_TEXTURE.underlineLeftParens);

    public static Glyph SYM_RIGHT_PARENS = new Glyph(')', Game.OVERLAY_TEXTURE.rightParens, Game.OVERLAY_TEXTURE.underlineRightParens);

    public static Glyph SYM_ASTERISK = new Glyph('*', Game.OVERLAY_TEXTURE.asterisk, Game.OVERLAY_TEXTURE.underlineAsterisk);

    public static Glyph SYM_PLUS = new Glyph('+', Game.OVERLAY_TEXTURE.plus, Game.OVERLAY_TEXTURE.underlinePlus);

    public static Glyph SYM_COMMA = new Glyph(',', Game.OVERLAY_TEXTURE.comma, Game.OVERLAY_TEXTURE.underlineComma);

    public static Glyph SYM_MINUS = new Glyph('-', Game.OVERLAY_TEXTURE.dash, Game.OVERLAY_TEXTURE.underlineDash);

    public static Glyph SYM_PERIOD = new Glyph('.', Game.OVERLAY_TEXTURE.period, Game.OVERLAY_TEXTURE.underlinePeriod);

    public static Glyph SYM_SLASH = new Glyph('/', Game.OVERLAY_TEXTURE.slash, Game.OVERLAY_TEXTURE.underlineSlash);

    public static Glyph NUM_0 = new Glyph('0', Game.OVERLAY_TEXTURE.zero, Game.OVERLAY_TEXTURE.underlineZero);

    public static Glyph NUM_1 = new Glyph('1', Game.OVERLAY_TEXTURE.one, Game.OVERLAY_TEXTURE.underlineOne);

    public static Glyph NUM_2 = new Glyph('2', Game.OVERLAY_TEXTURE.two, Game.OVERLAY_TEXTURE.underlineTwo);

    public static Glyph NUM_3 = new Glyph('3', Game.OVERLAY_TEXTURE.three, Game.OVERLAY_TEXTURE.underlineThree);

    public static Glyph NUM_4 = new Glyph('4', Game.OVERLAY_TEXTURE.four, Game.OVERLAY_TEXTURE.underlineFour);

    public static Glyph NUM_5 = new Glyph('5', Game.OVERLAY_TEXTURE.five, Game.OVERLAY_TEXTURE.underlineFive);

    public static Glyph NUM_6 = new Glyph('6', Game.OVERLAY_TEXTURE.six, Game.OVERLAY_TEXTURE.underlineSix);

    public static Glyph NUM_7 = new Glyph('7', Game.OVERLAY_TEXTURE.seven, Game.OVERLAY_TEXTURE.underlineSeven);

    public static Glyph NUM_8 = new Glyph('8', Game.OVERLAY_TEXTURE.eight, Game.OVERLAY_TEXTURE.underlineEight);

    public static Glyph NUM_9 = new Glyph('9', Game.OVERLAY_TEXTURE.nine, Game.OVERLAY_TEXTURE.underlineNine);

    public static Glyph SYM_COLON = new Glyph(':', Game.OVERLAY_TEXTURE.colon, Game.OVERLAY_TEXTURE.underlineColon);

    public static Glyph SYM_SEMICOLON = new Glyph(';', Game.OVERLAY_TEXTURE.semiColon, Game.OVERLAY_TEXTURE.underlineSemiColon);

    public static Glyph SYM_LESS_THAN = new Glyph('<', Game.OVERLAY_TEXTURE.lessThan, Game.OVERLAY_TEXTURE.underlineLessThan);

    public static Glyph SYM_EQUAL = new Glyph('=', Game.OVERLAY_TEXTURE.equals, Game.OVERLAY_TEXTURE.underlineEquals);

    public static Glyph SYM_GREATER_THAN = new Glyph('>', Game.OVERLAY_TEXTURE.greaterThan, Game.OVERLAY_TEXTURE.underlineGreaterThan);

    public static Glyph SYM_QUESTION = new Glyph('?', Game.OVERLAY_TEXTURE.question, Game.OVERLAY_TEXTURE.underlineQuestion);

    public static Glyph SYM_AT = new Glyph('@', Game.OVERLAY_TEXTURE.at, Game.OVERLAY_TEXTURE.underlineAt);

    public static Glyph CHAR_UPPER_A = new Glyph('A', Game.OVERLAY_TEXTURE.upperA, Game.OVERLAY_TEXTURE.underlineUpperA);

    public static Glyph CHAR_UPPER_B = new Glyph('B', Game.OVERLAY_TEXTURE.upperB, Game.OVERLAY_TEXTURE.underlineUpperB);

    public static Glyph CHAR_UPPER_C = new Glyph('C', Game.OVERLAY_TEXTURE.upperC, Game.OVERLAY_TEXTURE.underlineUpperC);

    public static Glyph CHAR_UPPER_D = new Glyph('D', Game.OVERLAY_TEXTURE.upperD, Game.OVERLAY_TEXTURE.underlineUpperD);

    public static Glyph CHAR_UPPER_E = new Glyph('E', Game.OVERLAY_TEXTURE.upperE, Game.OVERLAY_TEXTURE.underlineUpperE);

    public static Glyph CHAR_UPPER_F = new Glyph('F', Game.OVERLAY_TEXTURE.upperF, Game.OVERLAY_TEXTURE.underlineUpperF);

    public static Glyph CHAR_UPPER_G = new Glyph('G', Game.OVERLAY_TEXTURE.upperG, Game.OVERLAY_TEXTURE.underlineUpperG);

    public static Glyph CHAR_UPPER_H = new Glyph('H', Game.OVERLAY_TEXTURE.upperH, Game.OVERLAY_TEXTURE.underlineUpperH);

    public static Glyph CHAR_UPPER_I = new Glyph('I', Game.OVERLAY_TEXTURE.upperI, Game.OVERLAY_TEXTURE.underlineUpperI);

    public static Glyph CHAR_UPPER_J = new Glyph('J', Game.OVERLAY_TEXTURE.upperJ, Game.OVERLAY_TEXTURE.underlineUpperJ);

    public static Glyph CHAR_UPPER_K = new Glyph('K', Game.OVERLAY_TEXTURE.upperK, Game.OVERLAY_TEXTURE.underlineUpperK);

    public static Glyph CHAR_UPPER_L = new Glyph('L', Game.OVERLAY_TEXTURE.upperL, Game.OVERLAY_TEXTURE.underlineUpperL);

    public static Glyph CHAR_UPPER_M = new Glyph('M', Game.OVERLAY_TEXTURE.upperM, Game.OVERLAY_TEXTURE.underlineUpperM);

    public static Glyph CHAR_UPPER_N = new Glyph('N', Game.OVERLAY_TEXTURE.upperN, Game.OVERLAY_TEXTURE.underlineUpperN);

    public static Glyph CHAR_UPPER_O = new Glyph('O', Game.OVERLAY_TEXTURE.upperO, Game.OVERLAY_TEXTURE.underlineUpperO);

    public static Glyph CHAR_UPPER_P = new Glyph('P', Game.OVERLAY_TEXTURE.upperP, Game.OVERLAY_TEXTURE.underlineUpperP);

    public static Glyph CHAR_UPPER_Q = new Glyph('Q', Game.OVERLAY_TEXTURE.upperQ, Game.OVERLAY_TEXTURE.underlineUpperQ);

    public static Glyph CHAR_UPPER_R = new Glyph('R', Game.OVERLAY_TEXTURE.upperR, Game.OVERLAY_TEXTURE.underlineUpperR);

    public static Glyph CHAR_UPPER_S = new Glyph('S', Game.OVERLAY_TEXTURE.upperS, Game.OVERLAY_TEXTURE.underlineUpperS);

    public static Glyph CHAR_UPPER_T = new Glyph('T', Game.OVERLAY_TEXTURE.upperT, Game.OVERLAY_TEXTURE.underlineUpperT);

    public static Glyph CHAR_UPPER_U = new Glyph('U', Game.OVERLAY_TEXTURE.upperU, Game.OVERLAY_TEXTURE.underlineUpperU);

    public static Glyph CHAR_UPPER_V = new Glyph('V', Game.OVERLAY_TEXTURE.upperV, Game.OVERLAY_TEXTURE.underlineUpperV);

    public static Glyph CHAR_UPPER_W = new Glyph('W', Game.OVERLAY_TEXTURE.upperW, Game.OVERLAY_TEXTURE.underlineUpperW);

    public static Glyph CHAR_UPPER_X = new Glyph('X', Game.OVERLAY_TEXTURE.upperX, Game.OVERLAY_TEXTURE.underlineUpperX);

    public static Glyph CHAR_UPPER_Y = new Glyph('Y', Game.OVERLAY_TEXTURE.upperY, Game.OVERLAY_TEXTURE.underlineUpperY);

    public static Glyph CHAR_UPPER_Z = new Glyph('Z', Game.OVERLAY_TEXTURE.upperZ, Game.OVERLAY_TEXTURE.underlineUpperZ);

    public static Glyph SYM_LEFT_SQUARE_BRACKET = new Glyph('[', Game.OVERLAY_TEXTURE.leftBracket, Game.OVERLAY_TEXTURE.underlineLeftBracket);

    public static Glyph SYM_BACKSLASH = new Glyph('\\',Game.OVERLAY_TEXTURE.backSlash, Game.OVERLAY_TEXTURE.underlineBackSlash);

    public static Glyph SYM_RIGHT_SQUARE_BRACKET = new Glyph(']', Game.OVERLAY_TEXTURE.rightBracket, Game.OVERLAY_TEXTURE.underlineRightBracket);

    public static Glyph SYM_CARET = new Glyph('^', Game.OVERLAY_TEXTURE.caret, Game.OVERLAY_TEXTURE.underlineCaret);

    public static Glyph SYM_UNDERSCORE = new Glyph('_', Game.OVERLAY_TEXTURE.underscore, Game.OVERLAY_TEXTURE.underlineUnderscore);

    public static Glyph SYM_BACKTICK = new Glyph('`', Game.OVERLAY_TEXTURE.backTick, Game.OVERLAY_TEXTURE.underlineBackTick);

    public static Glyph CHAR_LOWER_A = new Glyph('a', Game.OVERLAY_TEXTURE.lowerA, Game.OVERLAY_TEXTURE.underlineLowerA);

    public static Glyph CHAR_LOWER_B = new Glyph('b', Game.OVERLAY_TEXTURE.lowerB, Game.OVERLAY_TEXTURE.underlineLowerB);

    public static Glyph CHAR_LOWER_C = new Glyph('c', Game.OVERLAY_TEXTURE.lowerC, Game.OVERLAY_TEXTURE.underlineLowerC);

    public static Glyph CHAR_LOWER_D = new Glyph('d', Game.OVERLAY_TEXTURE.lowerD, Game.OVERLAY_TEXTURE.underlineLowerD);

    public static Glyph CHAR_LOWER_E = new Glyph('e', Game.OVERLAY_TEXTURE.lowerE, Game.OVERLAY_TEXTURE.underlineLowerE);

    public static Glyph CHAR_LOWER_F = new Glyph('f', Game.OVERLAY_TEXTURE.lowerF, Game.OVERLAY_TEXTURE.underlineLowerF);

    public static Glyph CHAR_LOWER_G = new Glyph('g', Game.OVERLAY_TEXTURE.lowerG, Game.OVERLAY_TEXTURE.underlineLowerG);

    public static Glyph CHAR_LOWER_H = new Glyph('h', Game.OVERLAY_TEXTURE.lowerH, Game.OVERLAY_TEXTURE.underlineLowerH);

    public static Glyph CHAR_LOWER_I = new Glyph('i', Game.OVERLAY_TEXTURE.lowerI, Game.OVERLAY_TEXTURE.underlineLowerI);

    public static Glyph CHAR_LOWER_J = new Glyph('j', Game.OVERLAY_TEXTURE.lowerJ, Game.OVERLAY_TEXTURE.underlineLowerJ);

    public static Glyph CHAR_LOWER_K = new Glyph('k', Game.OVERLAY_TEXTURE.lowerK, Game.OVERLAY_TEXTURE.underlineLowerK);

    public static Glyph CHAR_LOWER_L = new Glyph('l', Game.OVERLAY_TEXTURE.lowerL, Game.OVERLAY_TEXTURE.underlineLowerL);

    public static Glyph CHAR_LOWER_M = new Glyph('m', Game.OVERLAY_TEXTURE.lowerM, Game.OVERLAY_TEXTURE.underlineLowerM);

    public static Glyph CHAR_LOWER_N = new Glyph('n', Game.OVERLAY_TEXTURE.lowerN, Game.OVERLAY_TEXTURE.underlineLowerN);

    public static Glyph CHAR_LOWER_O = new Glyph('o', Game.OVERLAY_TEXTURE.lowerO, Game.OVERLAY_TEXTURE.underlineLowerO);

    public static Glyph CHAR_LOWER_P = new Glyph('p', Game.OVERLAY_TEXTURE.lowerP, Game.OVERLAY_TEXTURE.underlineLowerP);

    public static Glyph CHAR_LOWER_Q = new Glyph('q', Game.OVERLAY_TEXTURE.lowerQ, Game.OVERLAY_TEXTURE.underlineLowerQ);

    public static Glyph CHAR_LOWER_R = new Glyph('r', Game.OVERLAY_TEXTURE.lowerR, Game.OVERLAY_TEXTURE.underlineLowerR);

    public static Glyph CHAR_LOWER_S = new Glyph('s', Game.OVERLAY_TEXTURE.lowerS, Game.OVERLAY_TEXTURE.underlineLowerS);

    public static Glyph CHAR_LOWER_T = new Glyph('t', Game.OVERLAY_TEXTURE.lowerT, Game.OVERLAY_TEXTURE.underlineLowerT);

    public static Glyph CHAR_LOWER_U = new Glyph('u', Game.OVERLAY_TEXTURE.lowerU, Game.OVERLAY_TEXTURE.underlineLowerU);

    public static Glyph CHAR_LOWER_V = new Glyph('v', Game.OVERLAY_TEXTURE.lowerV, Game.OVERLAY_TEXTURE.underlineLowerV);

    public static Glyph CHAR_LOWER_W = new Glyph('w', Game.OVERLAY_TEXTURE.lowerW, Game.OVERLAY_TEXTURE.underlineLowerW);

    public static Glyph CHAR_LOWER_X = new Glyph('x', Game.OVERLAY_TEXTURE.lowerX, Game.OVERLAY_TEXTURE.underlineLowerX);

    public static Glyph CHAR_LOWER_Y = new Glyph('y', Game.OVERLAY_TEXTURE.lowerY, Game.OVERLAY_TEXTURE.underlineLowerY);

    public static Glyph CHAR_LOWER_Z = new Glyph('z', Game.OVERLAY_TEXTURE.lowerZ, Game.OVERLAY_TEXTURE.underlineLowerZ);

    public static Glyph SYM_LEFT_BRACE = new Glyph('{', Game.OVERLAY_TEXTURE.leftBrace, Game.OVERLAY_TEXTURE.underlineLeftBrace);

    public static Glyph SYM_PIPE = new Glyph('|', Game.OVERLAY_TEXTURE.pipe, Game.OVERLAY_TEXTURE.underlinePipe);

    public static Glyph SYM_RIGHT_BRACE = new Glyph('}', Game.OVERLAY_TEXTURE.rightBrace, Game.OVERLAY_TEXTURE.underlineRightBrace);

    public static Glyph SYM_TILDE = new Glyph('~', Game.OVERLAY_TEXTURE.tilde, Game.OVERLAY_TEXTURE.underlineTilde);

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
