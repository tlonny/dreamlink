package doors;

import doors.queue.WorkQueue;
import doors.graphics.mesh.DoorMesh;
import doors.graphics.mesh.PortalMesh;
import doors.graphics.rendertarget.PortalVirtualRenderTarget;
import doors.graphics.rendertarget.ScreenVirtualRenderTarget;
import doors.graphics.shader.Shader;
import doors.graphics.texture.EntityTexture;
import doors.graphics.texture.FontTexture;
import doors.graphics.texture.MenuTexture;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacters;
import doors.io.Window;
import doors.debug.ExitListener;
import doors.state.AbstractGameState;
import doors.state.EditGameState;
import doors.state.ExploreGameState;
import doors.state.MainMenuGameState;

public class Doors {

    private void setup() {
        Window.WINDOW.setup();
        Mouse.MOUSE.setup();
        Keyboard.KEYBOARD.setup();
        TypedCharacters.TYPED_CHARACTERS.setup();
        Shader.SHADER.setup();

        MenuTexture.MENU_TEXTURE.setup();
        EntityTexture.ENTITY_TEXTURE.setup();
        FontTexture.FONT_TEXTURE.setup();
        PortalVirtualRenderTarget.PORTAL_VIRTUAL_RENDER_TARGET.setup();
        ScreenVirtualRenderTarget.SCREEN_VIRTUAL_RENDER_TARGET.setup();

        FontTexture.FONT_TEXTURE.useTexture();
        EntityTexture.ENTITY_TEXTURE.useTexture();
        MenuTexture.MENU_TEXTURE.useTexture();
        PortalVirtualRenderTarget.PORTAL_VIRTUAL_RENDER_TARGET.texture.useTexture();
        ScreenVirtualRenderTarget.SCREEN_VIRTUAL_RENDER_TARGET.texture.useTexture();

        DoorMesh.DOOR_MESH.setup();
        PortalMesh.PORTAL_MESH.setup();

        MainMenuGameState.MAIN_MENU_GAME_STATE.setup();
        ExploreGameState.EXPLORE_GAME_STATE.setup();
        EditGameState.EDIT_GAME_STATE.setup();
        MainMenuGameState.MAIN_MENU_GAME_STATE.use();
    }

    private void update() {
        TypedCharacters.TYPED_CHARACTERS.update();
        Keyboard.KEYBOARD.update();
        Window.WINDOW.update();
        Mouse.MOUSE.update();
        WorkQueue.WORK_QUEUE.update();
        ExitListener.EXIT_LISTENER.update();
        AbstractGameState.USED_GAME_STATE.update();
    }

    private void run() {
        this.setup();
        while(!Window.WINDOW.shouldClose()) {
            this.update();
        }
    }

    public static void main(String[] args) {
        new Doors().run();
    }

}
