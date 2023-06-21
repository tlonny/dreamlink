package doors.io;

import org.lwjgl.glfw.GLFW;
import doors.Config;
import doors.utility.geometry.Vector2in;

public class Mouse {

    public static Mouse MOUSE = new Mouse();

    private long leftPressed;
    private long leftReleased;
    private long rightPressed;
    private long rightReleased;

    private boolean centerLock;
    private double[] xBuffer = new double[1];
    private double[] yBuffer = new double[1];

    public Vector2in position = new Vector2in();

    public void setCenterLock(boolean centerLock) {
        this.centerLock = centerLock;
    }

    public void setCursorVisibility(boolean visible) {
        var cursorFlag = visible ? GLFW.GLFW_CURSOR_NORMAL : GLFW.GLFW_CURSOR_HIDDEN;
        GLFW.glfwSetInputMode(Window.WINDOW.windowID, GLFW.GLFW_CURSOR, cursorFlag);
    }

    private void onMouseButtonEvent(long window, int button, int action, int mode) {
        if (action == GLFW.GLFW_PRESS) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
                this.leftPressed = Window.WINDOW.currentTick;
            } else if(button == GLFW.GLFW_MOUSE_BUTTON_2) {
                this.rightPressed = Window.WINDOW.currentTick;
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
                this.leftReleased = Window.WINDOW.currentTick;
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_2) {
                this.rightReleased = Window.WINDOW.currentTick;
            }
        }
    }

    public boolean isLeftMouseButtonDown() {
        return this.leftPressed > leftReleased;
    }

    public boolean isRightMouseButtonDown() {
        return this.rightPressed > rightReleased;
    }

    public boolean isLeftMouseButtonReleased() {
        return this.leftReleased == Window.WINDOW.currentTick;
    }

    public boolean isRightButtonReleased() {
        return this.rightReleased == Window.WINDOW.currentTick;
    }

    public boolean isLeftMouseButtonPressed() {
        return this.leftPressed == Window.WINDOW.currentTick;
    }

    public boolean isRightMouseButtonPressed() {
        return this.rightPressed == Window.WINDOW.currentTick;
    }

    public void update() {
        GLFW.glfwGetCursorPos(Window.WINDOW.windowID, this.xBuffer, this.yBuffer);
        this.position.set(
            (int)(this.xBuffer[0] / Window.WINDOW.dimensions.x * Config.RESOLUTION.x),
            (int)(this.yBuffer[0] / Window.WINDOW.dimensions.y * Config.RESOLUTION.y)
        );

        if(this.centerLock) {
            GLFW.glfwSetCursorPos(
                Window.WINDOW.windowID, 
                Window.WINDOW.dimensions.x / 2f, 
                Window.WINDOW.dimensions.y / 2f
            );
        }
    }

    public void setup() {
        GLFW.glfwSetMouseButtonCallback(Window.WINDOW.windowID, this::onMouseButtonEvent);
    }
}
