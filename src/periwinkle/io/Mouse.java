package periwinkle.io;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import periwinkle.Game;

public class Mouse {

    private long leftPressed;
    private long leftReleased;
    private long rightPressed;
    private long rightReleased;

    private double[] xBuffer = new double[1];
    private double[] yBuffer = new double[1];

    public Vector2f position = new Vector2f();

    private void onMouseButtonEvent(long window, int button, int action, int mode) {
        var currentTime = System.currentTimeMillis();
        if (action == GLFW.GLFW_PRESS) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
                this.leftPressed = currentTime;
            } else if(button == GLFW.GLFW_MOUSE_BUTTON_2) {
                this.rightPressed = currentTime;
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
                this.leftReleased = currentTime;
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_2) {
                this.rightReleased = currentTime;
            }
        }
    }

    public boolean isLeftMouseButtonDown() {
        return this.leftPressed > leftReleased;
    }

    public boolean isRightMouseButtonDown() {
        return this.rightPressed > rightReleased;
    }

    public boolean isLeftMouseButtonPressed(ButtonLatch latch) {
        var result = latch.lastPressedTime < this.leftPressed;
        latch.lastPressedTime = System.currentTimeMillis();
        return result;
    }

    public boolean isRightMouseButtonPressed(ButtonLatch latch) {
        var result = latch.lastPressedTime < this.rightPressed;
        latch.lastPressedTime = System.currentTimeMillis();
        return result;
    }

    public void loadMousePosition() {
        GLFW.glfwGetCursorPos(Game.DISPLAY.windowID, this.xBuffer, this.yBuffer);

        this.position.set(
            (float)this.xBuffer[0],
            (float)this.yBuffer[0]
        );
    }

    public void resetMousePosition() {
        GLFW.glfwSetCursorPos(
            Game.DISPLAY.windowID, 
            Game.DISPLAY.dimensions.x / 2f, 
            Game.DISPLAY.dimensions.y / 2f
        );
    }

    public void setup() {
        GLFW.glfwSetMouseButtonCallback(Game.DISPLAY.windowID, this::onMouseButtonEvent);
    }
}
