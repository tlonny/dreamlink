package doors.io;

import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.glfw.GLFW;

public class TypedCharacterStream {

    public static TypedCharacterStream TYPED_CHARACTER_STREAM = new TypedCharacterStream();

    public Queue<Character> characterQueue = new LinkedList<>(); 

    public void onCharEvent(long window, int codePoint) {
        if(codePoint > 127) {
            return;
        }
        this.characterQueue.add((char)codePoint);
    }

    public void setup() {
        GLFW.glfwSetCharCallback(Window.WINDOW.windowID, this::onCharEvent);
    }

    public void refresh() {
        this.characterQueue.clear();
    }

}
