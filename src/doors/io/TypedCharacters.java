package doors.io;

import java.util.Collection;
import java.util.LinkedList;

import org.lwjgl.glfw.GLFW;

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
        GLFW.glfwSetCharCallback(Window.WINDOW.windowID, this::onCharEvent);
    }

    public void update() {
        this.characters.clear();
    }

}
