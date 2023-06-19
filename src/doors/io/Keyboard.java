package doors.io;

import org.lwjgl.glfw.GLFW;

import doors.Game;

public class Keyboard {

    public static Keyboard KEYBOARD = new Keyboard();

    private long[] pressedKeys = new long[500];
    private long[] releasedKeys = new long[500];

    private void onKeyEvent(long window, int key, int scancode, int action, int mode) {
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            this.pressedKeys[key] = Game.GAME.currentTick;
        } else if (action == GLFW.GLFW_RELEASE) {
            this.releasedKeys[key] = Game.GAME.currentTick;
        }
    }

    public boolean isKeyDown(int key) {
        var pressCount = this.pressedKeys[key];
        var releaseCount = this.releasedKeys[key];
        return pressCount > releaseCount;
    }

    public boolean isKeyPressed(int key) {
        return this.pressedKeys[key] == Game.GAME.currentTick;
    }

    public void setup() {
        GLFW.glfwSetKeyCallback(Window.WINDOW.windowID, this::onKeyEvent);
    }
}
