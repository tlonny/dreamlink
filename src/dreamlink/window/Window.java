package dreamlink.window;

import dreamlink.window.button.Button;
import dreamlink.window.button.WindowButtonModule;

import org.joml.Vector2f;
import org.joml.Vector2i;

public class Window {

    private class InternalProvider implements IWindowModuleProvider {

        @Override
        public WindowGLFWModule getGLFWModule() {
            return Window.instance.GLFWModule;
        }

    }

    private final WindowButtonModule buttonModule;
    private final WindowMouseModule mouseModule;
    private final WindowTypedCharacterModule typedCharacterModule;
    private final WindowGLCapabilitiesModule GLCapabilitiesModule;
    private final WindowALCapabilitiesModule ALCapabilitiesModule;
    private final WindowGLFWModule GLFWModule;

    public static final Window instance = new Window();

    public Window() {
        var provider = new InternalProvider();
        this.buttonModule = new WindowButtonModule(provider);
        this.mouseModule = new WindowMouseModule(provider);
        this.typedCharacterModule = new WindowTypedCharacterModule(provider);
        this.GLCapabilitiesModule = new WindowGLCapabilitiesModule();
        this.ALCapabilitiesModule = new WindowALCapabilitiesModule();
        this.GLFWModule = new WindowGLFWModule();
    }

    public void setup() {
        this.GLFWModule.setup();
        this.buttonModule.setup();
        this.typedCharacterModule.setup();
        this.GLCapabilitiesModule.setup();
        this.ALCapabilitiesModule.setup();
    }

    public Vector2i getResolution(Vector2i target) {
        return this.GLFWModule.getResolution(target);
    }

    public Vector2f getResolution(Vector2f target) {
        return this.GLFWModule.getResolution(target);
    }

    public long getWindowID() {
        return this.GLFWModule.getWindowID();
    }

    public void setShouldClose() {
        this.GLFWModule.setShouldClose();
    }

    public boolean shouldClose() {
        return this.GLFWModule.shouldClose();
    }

    public boolean isButtonDown(Button button) {
        return this.buttonModule.isButtonDown(button);
    }

    public boolean isButtonPressed(Button button) {
        return this.buttonModule.isButtonPressed(button);
    }

    public boolean isButtonReleased(Button button) {
        return this.buttonModule.isButtonReleased(button);
    }

    public Iterable<Button> getPressedButtons() {
        return this.buttonModule.getPressedButtons();
    }

    public Iterable<Character> getCharacters() {
        return this.typedCharacterModule.getTypedCharacters();
    }

    public Vector2f getDeltaMousePosition(Vector2f target) {
        return this.mouseModule.getDeltaPosition(target);
    }

    public Vector2i getMousePosition(Vector2i target) {
        return this.mouseModule.getMousePosition(target);
    }

    public void handleUserInputs() {
        this.buttonModule.advance();
        this.typedCharacterModule.advance();
        this.GLFWModule.pollEvents();
        this.mouseModule.fetch();
    }

    public void swapBuffers() {
        this.GLFWModule.swapBuffers();
    }

    public void destroy() {
        this.ALCapabilitiesModule.destroy();
        this.GLFWModule.destroy();
    }

}
