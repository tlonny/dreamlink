package doors.core.io;

import java.util.Collection;
import java.util.LinkedList;

import org.lwjgl.glfw.GLFW;

public class Keyboard {

    public static Keyboard KEYBOARD = new Keyboard();

    private long[] pressedKeyTimes = new long[500];
    private long[] releasedKeyTimes = new long[500];
    private long currentTick;

    public Collection<Integer> pressedKeys = new LinkedList<>();
    public Collection<Integer> releasedKeys = new LinkedList<>();

    private void onKeyEvent(long window, int key, int scancode, int action, int mode) {
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            this.pressedKeyTimes[key] = this.currentTick;
            this.pressedKeys.add(key);
        } else if (action == GLFW.GLFW_RELEASE) {
            this.releasedKeyTimes[key] = this.currentTick;
            this.releasedKeys.add(key);
        }
    }

    public void update() {
        this.currentTick = System.currentTimeMillis();
        this.pressedKeys.clear();
        this.releasedKeys.clear();
    }

    public boolean isKeyDown(int key) {
        var pressCount = this.pressedKeyTimes[key];
        var releaseCount = this.releasedKeyTimes[key];
        return pressCount > releaseCount;
    }

    public boolean isKeyPressed(int key) {
        return this.pressedKeyTimes[key] == this.currentTick;
    }

    public boolean isKeyReleased(int key) {
        return this.releasedKeyTimes[key] == this.currentTick;
    }

    public void setup() {
        Window.WINDOW.setKeyCallback(this::onKeyEvent);

    }
}
