package doors.core.io;

import org.lwjgl.glfw.GLFW;

import doors.Config;
import doors.utility.vector.Vector2in;

public class Mouse {

    private static Vector2in LOCK_POSITION = new Vector2in(Config.RESOLUTION).div(2);

    public static Mouse MOUSE = new Mouse();

    private long leftPressed;
    private long leftReleased;
    private long rightPressed;
    private long rightReleased;

    public Vector2in position = new Vector2in();
    private boolean isMouseLocked = true;
    private long currentTick;

    private void writeMousePosition(Vector2in position) {
        GLFW.glfwSetCursorPos(
            Window.WINDOW.windowID,
            (float) position.x / Config.RESOLUTION.x * Window.WINDOW.dimensions.x,
            (float) position.y / Config.RESOLUTION.y * Window.WINDOW.dimensions.y
        );
    }

    private Vector2in readMousePosition() {
        return new Vector2in(
            (int)(Window.WINDOW.cursorPosition.x * Config.RESOLUTION.x),
            (int)(Window.WINDOW.cursorPosition.y * Config.RESOLUTION.y)
        );
    }

    public void lockMouse() {
        if(this.isMouseLocked) {
            return;
        }
        this.writeMousePosition(LOCK_POSITION);
        this.position.set(LOCK_POSITION);
        this.isMouseLocked = true;
    }

    public void unlockMouse() {
        if(!this.isMouseLocked) {
            return;
        }
        this.isMouseLocked = false;
    }

    private void onMouseButtonEvent(long window, int button, int action, int mode) {
        if (action == GLFW.GLFW_PRESS) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
                this.leftPressed = this.currentTick;
            } else if(button == GLFW.GLFW_MOUSE_BUTTON_2) {
                this.rightPressed = this.currentTick;
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
                this.leftReleased = this.currentTick;
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_2) {
                this.rightReleased = this.currentTick;
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
        return this.leftReleased == this.currentTick;
    }

    public boolean isRightButtonReleased() {
        return this.rightReleased == this.currentTick;
    }

    public boolean isLeftMouseButtonPressed() {
        return this.leftPressed == this.currentTick;
    }

    public boolean isRightMouseButtonPressed() {
        return this.rightPressed == this.currentTick;
    }

    public void update() {
        this.currentTick = System.currentTimeMillis();
        this.position.set(this.readMousePosition());
        if(this.isMouseLocked) {
            this.writeMousePosition(LOCK_POSITION);
        }
    }

    public void setup() {
        Window.WINDOW.setMouseButtonCallback(this::onMouseButtonEvent);
    }
}
