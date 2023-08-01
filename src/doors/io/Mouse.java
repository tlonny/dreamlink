package doors.io;

import org.lwjgl.glfw.GLFW;

import doors.Config;
import doors.utility.vector.Vector2in;

public class Mouse {

    public static Mouse MOUSE = new Mouse();

    private double[] cursorXBuffer = new double[1];
    private double[] cursorYBuffer = new double[1];

    public long leftPressed;
    public long leftReleased;
    public long rightPressed;
    public long rightReleased;

    public Vector2in position = new Vector2in();

    private boolean isLocked = false;

    public Mouse() {
        Window.WINDOW.setMouseButtonCallback(this::onMouseButtonEvent);
    }

    public void setLocked(boolean isLocked) {
        if(isLocked == this.isLocked) {
            return;
        }

        this.isLocked = isLocked;
        // When we lock the camera, we want to make sure the huge delta that
        // occurs due to the previously free-moving mouse is removed to prevent
        // a nasty camera "jolt".

        // thus - first, we must zero the actual cursor position such that the
        // delta is not picked up in the next update loop. 
        GLFW.glfwSetCursorPos(
            Window.WINDOW.windowID,
            Window.WINDOW.dimensions.x / 2,
            Window.WINDOW.dimensions.y / 2
        );

        // we should also zero the buffered cursor position as if the camera does
        // a read before the next update loop, it will pick up the "unlocked" mouse
        // position.
        this.position.set(Config.CONFIG.getResolution()).div(2);
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
        GLFW.glfwGetCursorPos(Window.WINDOW.windowID, this.cursorXBuffer, this.cursorYBuffer);

        this.position.set(
            (int)(this.cursorXBuffer[0] / Window.WINDOW.dimensions.x * Config.CONFIG.getResolution().x),
            (int)(this.cursorYBuffer[0] / Window.WINDOW.dimensions.y * Config.CONFIG.getResolution().y)
        );

        if(this.isLocked) {
            GLFW.glfwSetCursorPos(
                Window.WINDOW.windowID,
                Window.WINDOW.dimensions.x / 2,
                Window.WINDOW.dimensions.y / 2
            );
        }
    }
}
