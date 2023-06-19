package doors.io;

import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;

import doors.Config;
import doors.Game;

public class Mouse {

    public static Mouse MOUSE = new Mouse();

    private long leftPressed;
    private long leftReleased;
    private long rightPressed;
    private long rightReleased;

    public boolean centerLock = true;

    private double[] xBuffer = new double[1];
    private double[] yBuffer = new double[1];

    public Vector2i position = new Vector2i();

    public void setCenterLock(boolean state) {
        this.centerLock = state;
        
        // Required to stop the mouse jerking due to the delta between
        // where the current mouse is and the center of the screen
        // when we go from false to true :-)
        GLFW.glfwSetCursorPos(
            Window.WINDOW.windowID, 
            Window.WINDOW.dimensions.x / 2f, 
            Window.WINDOW.dimensions.y / 2f
        );
    }

    private void onMouseButtonEvent(long window, int button, int action, int mode) {
        if (action == GLFW.GLFW_PRESS) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
                this.leftPressed = Game.GAME.currentTick;
            } else if(button == GLFW.GLFW_MOUSE_BUTTON_2) {
                this.rightPressed = Game.GAME.currentTick;
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
                this.leftReleased = Game.GAME.currentTick;
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_2) {
                this.rightReleased = Game.GAME.currentTick;
            }
        }
    }

    public boolean isLeftMouseButtonDown() {
        return this.leftPressed > leftReleased;
    }

    public boolean isRightMouseButtonDown() {
        return this.rightPressed > rightReleased;
    }

    public boolean isLeftMouseButtonPressed() {
        return this.leftPressed == Game.GAME.currentTick;
    }

    public boolean isRightMouseButtonPressed() {
        return this.rightPressed == Game.GAME.currentTick;
    }

    public void refresh() {
        var cursorFlag = this.centerLock ? GLFW.GLFW_CURSOR_HIDDEN : GLFW.GLFW_CURSOR_NORMAL;
        GLFW.glfwSetInputMode(Window.WINDOW.windowID, GLFW.GLFW_CURSOR, cursorFlag);

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
