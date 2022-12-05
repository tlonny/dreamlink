package dreamlink.window.button;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import dreamlink.window.IWindowModuleProvider;

public class WindowButtonModule {

    private final IWindowModuleProvider provider;
    private final long[] pressedKeys = new long[1024];
    private final long[] releasedKeys = new long[1024];
    private List<Button> pressedButtons = new ArrayList<>();
    private long tick;

    public WindowButtonModule(IWindowModuleProvider provider) {
        this.provider = provider;
    }

    public void setup() {
        var GLFWModule = this.provider.getGLFWModule();
        GLFWModule.setKeyCallback(this::keyCallBack);
        GLFWModule.setMouseButtonCallback(this::mouseButtonCallBack);
    }

    private void keyCallBack(long window, int code, int scancode, int action, int mode) {
        var uniformButtonID = code + ButtonType.keyboard.offset;
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            this.pressedKeys[uniformButtonID] = this.tick;
            var button = Button.getButton(uniformButtonID);
            if(button != null) {
                this.pressedButtons.add(button);
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            this.releasedKeys[uniformButtonID] = this.tick;
        }
    }

    private void mouseButtonCallBack(long window, int code, int action, int mode) {
        var uniformButtonID = code + ButtonType.mouse.offset;
        if (action == GLFW.GLFW_PRESS) {
            this.pressedKeys[uniformButtonID] = this.tick;
            var button = Button.getButton(uniformButtonID);
            if(button != null) {
                this.pressedButtons.add(button);
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            this.releasedKeys[uniformButtonID] = this.tick;
        }
    }

    public void advance() {
        this.tick += 1;
        this.pressedButtons.clear();
    }

    public Iterable<Button> getPressedButtons() {
        return this.pressedButtons;
    }

    public boolean isButtonDown(Button button) {
        var pressCount = this.pressedKeys[button.uniformButtonID];
        var releaseCount = this.releasedKeys[button.uniformButtonID];
        return pressCount > releaseCount;
    }

    public boolean isButtonPressed(Button button) {
        return this.pressedKeys[button.uniformButtonID] == this.tick;
    }

    public boolean isButtonReleased(Button button) {
        return this.releasedKeys[button.uniformButtonID] == this.tick;
    }
}
