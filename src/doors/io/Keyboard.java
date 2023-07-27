package doors.io;

import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.glfw.GLFW;

public class Keyboard {

    public static Keyboard KEYBOARD = new Keyboard();

    private long[] pressedKeyTimes = new long[500];
    private long[] releasedKeyTimes = new long[500];

    public Collection<Integer> pressedKeys = new ArrayList<>();

    public Keyboard() {
        Window.WINDOW.setKeyCallback(this::onKeyEvent);
    }

    private void onKeyEvent(long window, int key, int scancode, int action, int mode) {
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            this.pressedKeyTimes[key] = Window.WINDOW.currentTick;
            this.pressedKeys.add(key);
        } else if (action == GLFW.GLFW_RELEASE) {
            this.releasedKeyTimes[key] = Window.WINDOW.currentTick;
        }
    }

    public boolean isKeyDown(int key) {
        var pressCount = this.pressedKeyTimes[key];
        var releaseCount = this.releasedKeyTimes[key];
        return pressCount > releaseCount;
    }

    public boolean isKeyPressed(int key) {
        return this.pressedKeyTimes[key] == Window.WINDOW.currentTick;
    }

    public boolean isKeyReleased(int key) {
        return this.releasedKeyTimes[key] == Window.WINDOW.currentTick;
    }

    public void update() {
        this.pressedKeys.clear();
    }
}
