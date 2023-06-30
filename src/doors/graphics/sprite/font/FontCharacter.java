package doors.graphics.sprite.font;

import java.util.HashMap;
import java.util.Map;

import doors.graphics.texture.TextureSample;

public class FontCharacter {

    public static Map<Character,FontCharacter> FONT_CHARACTER_LOOKUP = new HashMap<>();

    public static FontCharacter SPACE = new FontCharacter(
        ' ',
        FontTextureAtlas.SPACE,
        FontTextureAtlas.UNDERLINE_SPACE
    );

    public static FontCharacter SYM_EXCLAMATION = new FontCharacter(
        '!',
        FontTextureAtlas.SYM_BANG,
        FontTextureAtlas.UNDERLINE_SYM_BANG
    );

    public static FontCharacter SYM_DOUBLE_QUOTES = new FontCharacter(
        '"',
        FontTextureAtlas.SYM_DOUBLE_QUOTES,
        FontTextureAtlas.UNDERLINE_SYM_DOUBLE_QUOTES
    );

    public static FontCharacter SYM_HASH = new FontCharacter(
        '#',
        FontTextureAtlas.SYM_HASH,
        FontTextureAtlas.UNDERLINE_SYM_HASH
    );

    public static FontCharacter SYM_DOLLAR = new FontCharacter(
        '$',
        FontTextureAtlas.SYM_DOLLAR,
        FontTextureAtlas.UNDERLINE_SYM_DOLLAR
    );

    public static FontCharacter SYM_PERCENT = new FontCharacter(
        '%',
        FontTextureAtlas.SYM_PERCENT,
        FontTextureAtlas.UNDERLINE_SYM_PERCENT
    );

    public static FontCharacter SYM_AMPERSAND = new FontCharacter(
        '&',
        FontTextureAtlas.SYM_AMPERSAND,
        FontTextureAtlas.UNDERLINE_SYM_AMPERSAND
    );

    public static FontCharacter SYM_APOSTROPHE = new FontCharacter(
        '\'',
        FontTextureAtlas.SYM_APOSTROPHE,
        FontTextureAtlas.UNDERLINE_SYM_APOSTROPHE
    );

    public static FontCharacter SYM_LEFT_PARENS = new FontCharacter(
        '(',
        FontTextureAtlas.SYM_LEFT_PARENS,
        FontTextureAtlas.UNDERLINE_SYM_LEFT_PARENS
    );

    public static FontCharacter SYM_RIGHT_PARENS = new FontCharacter(
        ')',
        FontTextureAtlas.SYM_RIGHT_PARENS,
        FontTextureAtlas.UNDERLINE_SYM_RIGHT_PARENS
    );

    public static FontCharacter SYM_ASTERISK = new FontCharacter(
        '*',
        FontTextureAtlas.SYM_ASTERISK,
        FontTextureAtlas.UNDERLINE_SYM_ASTERISK
    );

    public static FontCharacter SYM_PLUS = new FontCharacter(
        '+',
        FontTextureAtlas.SYM_PLUS,
        FontTextureAtlas.UNDERLINE_SYM_PLUS
    );

    public static FontCharacter SYM_COMMA = new FontCharacter(
        ',',
        FontTextureAtlas.SYM_COMMA,
        FontTextureAtlas.UNDERLINE_SYM_COMMA
    );

    public static FontCharacter SYM_MINUS = new FontCharacter(
        '-',
        FontTextureAtlas.SYM_MINUS,
        FontTextureAtlas.UNDERLINE_SYM_MINUS
    );

    public static FontCharacter SYM_PERIOD = new FontCharacter(
        '.',
        FontTextureAtlas.SYM_PERIOD,
        FontTextureAtlas.UNDERLINE_SYM_PERIOD
    );

    public static FontCharacter SYM_SLASH = new FontCharacter(
        '/',
        FontTextureAtlas.SYM_SLASH,
        FontTextureAtlas.UNDERLINE_SYM_SLASH
    );

    public static FontCharacter NUM_0 = new FontCharacter(
        '0',
        FontTextureAtlas.NUM_0,
        FontTextureAtlas.UNDERLINE_NUM_0
    );

    public static FontCharacter NUM_1 = new FontCharacter(
        '1',
        FontTextureAtlas.NUM_1,
        FontTextureAtlas.UNDERLINE_NUM_1
    );

    public static FontCharacter NUM_2 = new FontCharacter(
        '2',
        FontTextureAtlas.NUM_2,
        FontTextureAtlas.UNDERLINE_NUM_2
    );

    public static FontCharacter NUM_3 = new FontCharacter(
        '3',
        FontTextureAtlas.NUM_3,
        FontTextureAtlas.UNDERLINE_NUM_3
    );

    public static FontCharacter NUM_4 = new FontCharacter(
        '4',
        FontTextureAtlas.NUM_4,
        FontTextureAtlas.UNDERLINE_NUM_4
    );

    public static FontCharacter NUM_5 = new FontCharacter(
        '5',
        FontTextureAtlas.NUM_5,
        FontTextureAtlas.UNDERLINE_NUM_5
    );

    public static FontCharacter NUM_6 = new FontCharacter(
        '6',
        FontTextureAtlas.NUM_6,
        FontTextureAtlas.UNDERLINE_NUM_6
    );

    public static FontCharacter NUM_7 = new FontCharacter(
        '7',
        FontTextureAtlas.NUM_7,
        FontTextureAtlas.UNDERLINE_NUM_7
    );

    public static FontCharacter NUM_8 = new FontCharacter(
        '8',
        FontTextureAtlas.NUM_8,
        FontTextureAtlas.UNDERLINE_NUM_8
    );

    public static FontCharacter NUM_9 = new FontCharacter(
        '9',
        FontTextureAtlas.NUM_9,
        FontTextureAtlas.UNDERLINE_NUM_9
    );

    public static FontCharacter SYM_COLON = new FontCharacter(
        ':',
        FontTextureAtlas.SYM_COLON,
        FontTextureAtlas.UNDERLINE_SYM_COLON
    );

    public static FontCharacter SYM_SEMICOLON = new FontCharacter(
        ';',
        FontTextureAtlas.SYM_SEMICOLON,
        FontTextureAtlas.UNDERLINE_SYM_SEMICOLON
    );

    public static FontCharacter SYM_LESS_THAN = new FontCharacter(
        '<',
        FontTextureAtlas.SYM_LESS_THAN,
        FontTextureAtlas.UNDERLINE_SYM_LESS_THAN
    );

    public static FontCharacter SYM_EQUAL = new FontCharacter(
        '=',
        FontTextureAtlas.SYM_EQUAL,
        FontTextureAtlas.UNDERLINE_SYM_EQUAL
    );

    public static FontCharacter SYM_GREATER_THAN = new FontCharacter(
        '>',
        FontTextureAtlas.SYM_GREATER_THAN,
        FontTextureAtlas.UNDERLINE_SYM_GREATER_THAN
    );

    public static FontCharacter SYM_QUESTION = new FontCharacter(
        '?',
        FontTextureAtlas.SYM_QUESTION,
        FontTextureAtlas.UNDERLINE_SYM_QUESTION
    );

    public static FontCharacter SYM_AT = new FontCharacter(
        '@',
        FontTextureAtlas.SYM_AT,
        FontTextureAtlas.UNDERLINE_SYM_AT
    );

    public static FontCharacter CHAR_UPPER_A = new FontCharacter(
        'A',
        FontTextureAtlas.CHAR_UPPER_A,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_A
    );

    public static FontCharacter CHAR_UPPER_B = new FontCharacter(
        'B',
        FontTextureAtlas.CHAR_UPPER_B,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_B
    );

    public static FontCharacter CHAR_UPPER_C = new FontCharacter(
        'C',
        FontTextureAtlas.CHAR_UPPER_C,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_C
    );

    public static FontCharacter CHAR_UPPER_D = new FontCharacter(
        'D',
        FontTextureAtlas.CHAR_UPPER_D,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_D
    );

    public static FontCharacter CHAR_UPPER_E = new FontCharacter(
        'E',
        FontTextureAtlas.CHAR_UPPER_E,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_E
    );
    
    public static FontCharacter CHAR_UPPER_F = new FontCharacter(
        'F',
        FontTextureAtlas.CHAR_UPPER_F,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_F
    );

    public static FontCharacter CHAR_UPPER_G = new FontCharacter(
        'G',
        FontTextureAtlas.CHAR_UPPER_G,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_G
    );

    public static FontCharacter CHAR_UPPER_H = new FontCharacter(
        'H',
        FontTextureAtlas.CHAR_UPPER_H,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_H
    );

    public static FontCharacter CHAR_UPPER_I = new FontCharacter(
        'I',
        FontTextureAtlas.CHAR_UPPER_I,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_I
    );

    public static FontCharacter CHAR_UPPER_J = new FontCharacter(
        'J',
        FontTextureAtlas.CHAR_UPPER_J,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_J
    );

    public static FontCharacter CHAR_UPPER_K = new FontCharacter(
        'K',
        FontTextureAtlas.CHAR_UPPER_K,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_K
    );

    public static FontCharacter CHAR_UPPER_L = new FontCharacter(
        'L',
        FontTextureAtlas.CHAR_UPPER_L,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_L
    );

    public static FontCharacter CHAR_UPPER_M = new FontCharacter(
        'M',
        FontTextureAtlas.CHAR_UPPER_M,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_M
    );

    public static FontCharacter CHAR_UPPER_N = new FontCharacter(
        'N',
        FontTextureAtlas.CHAR_UPPER_N,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_N
    );

    public static FontCharacter CHAR_UPPER_O = new FontCharacter(
        'O',
        FontTextureAtlas.CHAR_UPPER_O,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_O
    );

    public static FontCharacter CHAR_UPPER_P = new FontCharacter(
        'P',
        FontTextureAtlas.CHAR_UPPER_P,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_P
    );

    public static FontCharacter CHAR_UPPER_Q = new FontCharacter(
        'Q',
        FontTextureAtlas.CHAR_UPPER_Q,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_Q
    );

    public static FontCharacter CHAR_UPPER_R = new FontCharacter(
        'R',
        FontTextureAtlas.CHAR_UPPER_R,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_R
    );

    public static FontCharacter CHAR_UPPER_S = new FontCharacter(
        'S',
        FontTextureAtlas.CHAR_UPPER_S,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_S
    );

    public static FontCharacter CHAR_UPPER_T = new FontCharacter(
        'T',
        FontTextureAtlas.CHAR_UPPER_T,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_T
    );

    public static FontCharacter CHAR_UPPER_U = new FontCharacter(
        'U',
        FontTextureAtlas.CHAR_UPPER_U,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_U
    );

    public static FontCharacter CHAR_UPPER_V = new FontCharacter(
        'V',
        FontTextureAtlas.CHAR_UPPER_V,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_V
    );

    public static FontCharacter CHAR_UPPER_W = new FontCharacter(
        'W',
        FontTextureAtlas.CHAR_UPPER_W,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_W
    );

    public static FontCharacter CHAR_UPPER_X = new FontCharacter(
        'X',
        FontTextureAtlas.CHAR_UPPER_X,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_X
    );

    public static FontCharacter CHAR_UPPER_Y = new FontCharacter(
        'Y',
        FontTextureAtlas.CHAR_UPPER_Y,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_Y
    );

    public static FontCharacter CHAR_UPPER_Z = new FontCharacter(
        'Z',
        FontTextureAtlas.CHAR_UPPER_Z,
        FontTextureAtlas.UNDERLINE_CHAR_UPPER_Z
    );

    public static FontCharacter SYM_LEFT_SQUARE_BRACKET = new FontCharacter(
        '[',
        FontTextureAtlas.SYM_LEFT_SQUARE_BRACKET,
        FontTextureAtlas.UNDERLINE_SYM_LEFT_SQUARE_BRACKET
    );

    public static FontCharacter SYM_BACKSLASH = new FontCharacter(
        '\\',
        FontTextureAtlas.SYM_BACKSLASH,
        FontTextureAtlas.UNDERLINE_SYM_BACKSLASH
    );

    public static FontCharacter SYM_RIGHT_SQUARE_BRACKET = new FontCharacter(
        ']',
        FontTextureAtlas.SYM_RIGHT_SQUARE_BRACKET,
        FontTextureAtlas.UNDERLINE_SYM_RIGHT_SQUARE_BRACKET
    );

    public static FontCharacter SYM_CARET = new FontCharacter(
        '^',
        FontTextureAtlas.SYM_CARET,
        FontTextureAtlas.UNDERLINE_SYM_CARET
    );

    public static FontCharacter SYM_UNDERSCORE = new FontCharacter(
        '_',
        FontTextureAtlas.SYM_UNDERSCORE,
        FontTextureAtlas.UNDERLINE_SYM_UNDERSCORE
    );


    public static FontCharacter SYM_BACKTICK = new FontCharacter(
        '`',
        FontTextureAtlas.SYM_BACKTICK,
        FontTextureAtlas.UNDERLINE_SYM_BACKTICK
    );

    public static FontCharacter CHAR_LOWER_A = new FontCharacter(
        'a',
        FontTextureAtlas.CHAR_LOWER_A,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_A
    );

    public static FontCharacter CHAR_LOWER_B = new FontCharacter(
        'b',
        FontTextureAtlas.CHAR_LOWER_B,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_B
    ); 

    public static FontCharacter CHAR_LOWER_C = new FontCharacter(
        'c',
        FontTextureAtlas.CHAR_LOWER_C,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_C
    );

    public static FontCharacter CHAR_LOWER_D = new FontCharacter(
        'd',
        FontTextureAtlas.CHAR_LOWER_D,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_D
    );

    public static FontCharacter CHAR_LOWER_E = new FontCharacter(
        'e',
        FontTextureAtlas.CHAR_LOWER_E,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_E
    );

    public static FontCharacter CHAR_LOWER_F = new FontCharacter(
        'f',
        FontTextureAtlas.CHAR_LOWER_F,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_F
    );

    public static FontCharacter CHAR_LOWER_G = new FontCharacter(
        'g',
        FontTextureAtlas.CHAR_LOWER_G,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_G
    );

    public static FontCharacter CHAR_LOWER_H = new FontCharacter(
        'h',
        FontTextureAtlas.CHAR_LOWER_H,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_H
    );

    public static FontCharacter CHAR_LOWER_I = new FontCharacter(
        'i',
        FontTextureAtlas.CHAR_LOWER_I,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_I
    );

    public static FontCharacter CHAR_LOWER_J = new FontCharacter(
        'j',
        FontTextureAtlas.CHAR_LOWER_J,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_J
    );

    public static FontCharacter CHAR_LOWER_K = new FontCharacter(
        'k',
        FontTextureAtlas.CHAR_LOWER_K,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_K
    );

    public static FontCharacter CHAR_LOWER_L = new FontCharacter(
        'l',
        FontTextureAtlas.CHAR_LOWER_L,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_L
    );

    public static FontCharacter CHAR_LOWER_M = new FontCharacter(
        'm',
        FontTextureAtlas.CHAR_LOWER_M,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_M
    );

    public static FontCharacter CHAR_LOWER_N = new FontCharacter(
        'n',
        FontTextureAtlas.CHAR_LOWER_N,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_N
    );

    public static FontCharacter CHAR_LOWER_O = new FontCharacter(
        'o',
        FontTextureAtlas.CHAR_LOWER_O,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_O
    );

    public static FontCharacter CHAR_LOWER_P = new FontCharacter(
        'p',
        FontTextureAtlas.CHAR_LOWER_P,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_P
    );
    
    public static FontCharacter CHAR_LOWER_Q = new FontCharacter(
        'q',
        FontTextureAtlas.CHAR_LOWER_Q,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_Q
    );

    public static FontCharacter CHAR_LOWER_R = new FontCharacter(
        'r',
        FontTextureAtlas.CHAR_LOWER_R,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_R
    );

    public static FontCharacter CHAR_LOWER_S = new FontCharacter(
        's',
        FontTextureAtlas.CHAR_LOWER_S,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_S
    );

    public static FontCharacter CHAR_LOWER_T = new FontCharacter(
        't',
        FontTextureAtlas.CHAR_LOWER_T,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_T
    );

    public static FontCharacter CHAR_LOWER_U = new FontCharacter(
        'u',
        FontTextureAtlas.CHAR_LOWER_U,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_U
    );

    public static FontCharacter CHAR_LOWER_V = new FontCharacter(
        'v',
        FontTextureAtlas.CHAR_LOWER_V,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_V
    );

    public static FontCharacter CHAR_LOWER_W = new FontCharacter(
        'w',
        FontTextureAtlas.CHAR_LOWER_W,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_W
    );

    public static FontCharacter CHAR_LOWER_X = new FontCharacter(
        'x',
        FontTextureAtlas.CHAR_LOWER_X,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_X
    );

    public static FontCharacter CHAR_LOWER_Y = new FontCharacter(
        'y',
        FontTextureAtlas.CHAR_LOWER_Y,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_Y
    );

    public static FontCharacter CHAR_LOWER_Z = new FontCharacter(
        'z',
        FontTextureAtlas.CHAR_LOWER_Z,
        FontTextureAtlas.UNDERLINE_CHAR_LOWER_Z
    );

    public static FontCharacter SYM_LEFT_BRACE = new FontCharacter(
        '{',
        FontTextureAtlas.SYM_LEFT_BRACE,
        FontTextureAtlas.UNDERLINE_SYM_LEFT_BRACE
    );

    public static FontCharacter SYM_PIPE = new FontCharacter(
        '|',
        FontTextureAtlas.SYM_PIPE,
        FontTextureAtlas.UNDERLINE_SYM_PIPE
    );

    public static FontCharacter SYM_RIGHT_BRACE = new FontCharacter(
        '}',
        FontTextureAtlas.SYM_RIGHT_BRACE,
        FontTextureAtlas.UNDERLINE_SYM_RIGHT_BRACE
    );

    public static FontCharacter SYM_TILDE = new FontCharacter(
        '~',
        FontTextureAtlas.SYM_TILDE,
        FontTextureAtlas.UNDERLINE_SYM_TILDE
    );
    
    public TextureSample textureSample;
    public TextureSample underlineTextureSample;
    public char character;

    public FontCharacter(char character, TextureSample textureSample, TextureSample underlineTextureSample) {
        this.character = character;
        this.textureSample = textureSample;
        this.underlineTextureSample = underlineTextureSample;
        FONT_CHARACTER_LOOKUP.put(character, this);
    }
}

