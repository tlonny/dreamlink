package doors.core;

import doors.core.graphics.Shader;
import doors.core.io.Keyboard;
import doors.core.io.Mouse;
import doors.core.io.TypedCharacters;
import doors.core.io.Window;

public class Game {

    protected void setup() {
        Window.WINDOW.setup();
        Mouse.MOUSE.setup();
        Keyboard.KEYBOARD.setup();
        TypedCharacters.TYPED_CHARACTERS.setup();
        Shader.SHADER.setup();
    }

    protected void teardown() {
    }

    protected void update() {
        Tick.TICK.update();
        TypedCharacters.TYPED_CHARACTERS.update();
        Keyboard.KEYBOARD.update();
        Window.WINDOW.update();
        Mouse.MOUSE.update();
        WorkQueue.WORK_QUEUE.update();
    }

    public void run() {
        this.setup();
        while(!Window.WINDOW.shouldClose()) {
            this.update();
        }
        this.teardown();
    }

}
