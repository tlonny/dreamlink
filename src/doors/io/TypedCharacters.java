package doors.io;

import java.util.Collection;
import java.util.LinkedList;

public class TypedCharacters {

    public static TypedCharacters TYPED_CHARACTERS = new TypedCharacters();

    public Collection<Character> characters = new LinkedList<>(); 

    public void onCharEvent(long window, int codePoint) {
        if(codePoint > 127) {
            return;
        }
        this.characters.add((char)codePoint);
    }

    public void setup() {
        Window.WINDOW.setCharCallback(this::onCharEvent);
    }

    public void update() {
        this.characters.clear();
    }

}
