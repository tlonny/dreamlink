package doors.core.io;

import java.util.Collection;
import java.util.LinkedList;

import org.lwjgl.glfw.GLFW;

import doors.core.Tick;

public class Keyboard {

    public static Keyboard KEYBOARD = new Keyboard();

    private long[] pressedKeyTimes = new long[500];
    private long[] releasedKeyTimes = new long[500];

    public Collection<Integer> pressedKeys;
    public Collection<Integer> releasedKeys;

    public Keyboard() {
        this.pressedKeyTimes = new long[500];
        this.releasedKeyTimes = new long[500];
        this.pressedKeys = new LinkedList<>();
        this.releasedKeys = new LinkedList<>();
    }

    private void onKeyEvent(long window, int key, int scancode, int action, int mode) {
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            this.pressedKeyTimes[key] = Tick.TICK.getCurrentTick();
            this.pressedKeys.add(key);
        } else if (action == GLFW.GLFW_RELEASE) {
            this.releasedKeyTimes[key] = Tick.TICK.getCurrentTick();
            this.releasedKeys.add(key);
        }
    }

    public void update() {
        this.pressedKeys.clear();
        this.releasedKeys.clear();
    }

    public boolean isKeyDown(int key) {
        var pressCount = this.pressedKeyTimes[key];
        var releaseCount = this.releasedKeyTimes[key];
        return pressCount > releaseCount;
    }

    public boolean isKeyPressed(int key) {
        return this.pressedKeyTimes[key] == Tick.TICK.getCurrentTick();
    }

    public boolean isKeyReleased(int key) {
        return this.releasedKeyTimes[key] == Tick.TICK.getCurrentTick();
    }

    public void setup() {
        Window.WINDOW.setKeyCallback(this::onKeyEvent);

    }
}
