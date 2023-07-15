package doors.debug;

import doors.core.io.TypedCharacters;
import doors.core.io.Window;

public class ExitListener {

    private static String ESCAPE_SEQUENCE = ":q";

    public static ExitListener EXIT_LISTENER = new ExitListener(ESCAPE_SEQUENCE);

    private String escapeSequence;
    private char[] characterBuffer = new char[ESCAPE_SEQUENCE.length()];
    public boolean isEnabled = true;

    public ExitListener(String escapeSequence) {
        this.escapeSequence = escapeSequence;
    }

    private void shiftBuffer(char newChar) {
        var length = this.characterBuffer.length;
        for(var ix = 0; ix < length - 1; ix += 1) {
            this.characterBuffer[ix] = this.characterBuffer[ix + 1];
        }
        this.characterBuffer[length - 1] = newChar;
    }

    private boolean isSequenceMatch() {
        for(var ix = 0; ix < this.characterBuffer.length; ix += 1) {
            var bufferChar = this.characterBuffer[ix];
            var escapeChar = this.escapeSequence.charAt(ix);
            if(bufferChar != escapeChar) {
                return false;
            }
        }
        return true;
    }

    public void update() {
        for(var typedCharacter : TypedCharacters.TYPED_CHARACTERS.characters) {
            this.shiftBuffer(typedCharacter);
            if(this.isSequenceMatch() && this.isEnabled) {
                Window.WINDOW.setShouldClose();
            }
        }
    }

}
