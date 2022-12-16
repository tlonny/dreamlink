package periwinkle;

import periwinkle.graphics.Display;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class Input {

    public static Input INPUT = new Input();
    public static void init() {
        INPUT.setup();
    }

    private final long[] pressedKeys = new long[500];
    private final long[] releasedKeys = new long[500];
    private final long[] repeatedKeys = new long[500];

    private long leftPressed;
    private long rightPressed;

    public final List<Character> inputCapture = new ArrayList<>();
    private long captureCount = 1;

    private void onMouseButtonEvent(long window, int button, int action, int mode) {
        if(action == GLFW.GLFW_PRESS) {
            if(button == GLFW.GLFW_MOUSE_BUTTON_1)
                this.leftPressed = this.captureCount;
            else if(button == GLFW.GLFW_MOUSE_BUTTON_2)
                this.rightPressed = this.captureCount;
        }
    }

    private void onKeyEvent(long window, int key, int scancode, int action, int mode) {
        if(action == GLFW.GLFW_PRESS)
            this.pressedKeys[key] = this.captureCount;
        else if(action == GLFW.GLFW_RELEASE)
            this.releasedKeys[key] = this.captureCount;
        else if(action == GLFW.GLFW_REPEAT)
            this.repeatedKeys[key] = this.captureCount;
    }

    private void onCharEvent(long window, int codePoint) {
        if(codePoint > 127)
            return;
        this.inputCapture.add((char)codePoint);
    }

    public boolean isKeyDown(int key) {
        var pressCount = this.pressedKeys[key];
        var releaseCount = this.releasedKeys[key];
        return pressCount > releaseCount;
    }

    public boolean isKeyRepeated(int key) {
        if(this.isKeyPressed(key))
            return true;
        var repeatCount  = this.repeatedKeys[key];
        var releaseCount  = this.releasedKeys[key];
        return repeatCount > releaseCount;
    }

    public boolean isKeyPressed(int key) {
        var pressCount = this.pressedKeys[key];
        return pressCount == this.captureCount;
    }

    public boolean isLeftMousePressed() {
        return this.leftPressed == this.captureCount;
    }

    public boolean isRightMousePressed() {
        return this.rightPressed == this.captureCount;
    }

    public void update() {
        this.captureCount += 1;
    }

    public void render() {
        this.inputCapture.clear();
    }

    public void setup() {
        Display.DISPLAY.setKeyCallback(this::onKeyEvent);
        Display.DISPLAY.setCharCallback(this::onCharEvent);
        Display.DISPLAY.setMouseButtonCallback(this::onMouseButtonEvent);
    }
}
