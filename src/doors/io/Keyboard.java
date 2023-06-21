package doors.io;

import org.lwjgl.glfw.GLFW;

public class Keyboard {

    public static Keyboard KEYBOARD = new Keyboard();

    private long[] pressedKeys = new long[500];
    private long[] releasedKeys = new long[500];

    private void onKeyEvent(long window, int key, int scancode, int action, int mode) {
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            this.pressedKeys[key] = Window.WINDOW.currentTick;
        } else if (action == GLFW.GLFW_RELEASE) {
            this.releasedKeys[key] = Window.WINDOW.currentTick;
        }
    }

    public boolean isKeyDown(int key) {
        var pressCount = this.pressedKeys[key];
        var releaseCount = this.releasedKeys[key];
        return pressCount > releaseCount;
    }

    public boolean isKeyPressed(int key) {
        return this.pressedKeys[key] == Window.WINDOW.currentTick;
    }

    public void setup() {
        GLFW.glfwSetKeyCallback(Window.WINDOW.windowID, this::onKeyEvent);

    }
}
