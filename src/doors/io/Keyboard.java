package doors.io;

import org.lwjgl.glfw.GLFW;

import doors.Game;

public class Keyboard {

    private long[] pressedKeys = new long[500];
    private long[] releasedKeys = new long[500];

    private void onKeyEvent(long window, int key, int scancode, int action, int mode) {
        var currentTime = System.currentTimeMillis();
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            this.pressedKeys[key] = currentTime;
        } else if (action == GLFW.GLFW_RELEASE) {
            this.releasedKeys[key] = currentTime;
        }
    }

    public boolean isKeyDown(int key) {
        var pressCount = this.pressedKeys[key];
        var releaseCount = this.releasedKeys[key];
        return pressCount > releaseCount;
    }

    public boolean isKeyPressed(int key, ButtonLatch latch) {
        var pressCount = this.pressedKeys[key];
        var result = latch.lastPressedTime < pressCount;
        latch.lastPressedTime = System.currentTimeMillis();
        return result;
    }

    public long getKeyPressTime(int key) {
        return this.pressedKeys[key];
    }

    public void setup() {
        GLFW.glfwSetKeyCallback(Game.DISPLAY.windowID, this::onKeyEvent);
    }
}
