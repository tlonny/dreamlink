package doors.core.io;

import org.lwjgl.glfw.GLFW;
import doors.Config;
import doors.core.Tick;
import doors.core.utility.vector.Vector2in;

public class Mouse {

    public static Mouse MOUSE = new Mouse();

    public boolean centerLock;

    private long leftPressed;
    private long leftReleased;
    private long rightPressed;
    private long rightReleased;
    public Vector2in position;

    public Mouse() {
        this.position = new Vector2in();
    }

    private void onMouseButtonEvent(long window, int button, int action, int mode) {
        if (action == GLFW.GLFW_PRESS) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
                this.leftPressed = Tick.TICK.getCurrentTick();
            } else if(button == GLFW.GLFW_MOUSE_BUTTON_2) {
                this.rightPressed = Tick.TICK.getCurrentTick();
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
                this.leftReleased = Tick.TICK.getCurrentTick();
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_2) {
                this.rightReleased = Tick.TICK.getCurrentTick();
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
        return this.leftReleased == Tick.TICK.getCurrentTick();
    }

    public boolean isRightButtonReleased() {
        return this.rightReleased == Tick.TICK.getCurrentTick();
    }

    public boolean isLeftMouseButtonPressed() {
        return this.leftPressed == Tick.TICK.getCurrentTick();
    }

    public boolean isRightMouseButtonPressed() {
        return this.rightPressed == Tick.TICK.getCurrentTick();
    }

    public void update() {
        this.position.set(
            (int)(Window.WINDOW.getCursorPosition().getFloatX() * Config.RESOLUTION.x),
            (int)(Window.WINDOW.getCursorPosition().getFloatY() * Config.RESOLUTION.y)
        );

        if(this.centerLock) {
            GLFW.glfwSetCursorPos(
                Window.WINDOW.windowID,
                Window.WINDOW.dimensions.x / 2,
                Window.WINDOW.dimensions.y / 2
            );
        }
    }

    public void setup() {
        Window.WINDOW.setMouseButtonCallback(this::onMouseButtonEvent);
    }
}
