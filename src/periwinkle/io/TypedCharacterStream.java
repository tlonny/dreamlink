package periwinkle.io;

import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.glfw.GLFW;

import periwinkle.Game;

public class TypedCharacterStream {

    public Queue<Character> characterQueue = new LinkedList<>(); 

    public void onCharEvent(long window, int codePoint) {
        if(codePoint > 127) {
            return;
        }
        this.characterQueue.add((char)codePoint);
    }

    public void setup() {
        GLFW.glfwSetCharCallback(Game.DISPLAY.windowID, this::onCharEvent);
    }

    public void refresh() {
        this.characterQueue.clear();
    }

}
