package doors.ui;

import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;

import doors.Config;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.Window;

public class Menu {

    private static Vector2i MENU_SIZE = new Vector2i(100, 40);
    private static Vector2i POSITION = new Vector2i(
        Config.RESOLUTION.x / 2 - MENU_SIZE.x / 2,
        Config.RESOLUTION.y / 2 - MENU_SIZE.y / 2
    );

    public static Menu MENU = new Menu();

    private Box root;
    public boolean active;

    public Menu() {
        this.root = new Box(MENU_SIZE);
        this.root.center = true;
        this.root.children.add(new Button("quit", () -> Window.WINDOW.setShouldClose()));
    }

    public void paint() {
        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
            this.active = !this.active;
            Mouse.MOUSE.setCenterLock(!this.active);
        }

        if(!active) {
            return;
        }

        this.root.paint(POSITION);
    }

}
