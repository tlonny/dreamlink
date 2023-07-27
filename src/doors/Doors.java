package doors;

import doors.graphics.rendertarget.PortalVirtualRenderTarget;
import doors.graphics.rendertarget.ScreenVirtualRenderTarget;
import doors.graphics.texture.EntityTexture;
import doors.graphics.texture.FontTexture;
import doors.graphics.texture.MenuTexture;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacters;
import doors.io.Window;
import doors.level.cache.LevelCache;
import doors.queue.IncrementalWorkQueue;
import doors.queue.ThreadPoolWorkQueue;
import doors.debug.ExitListener;
import doors.state.AbstractGameState;
import doors.state.MainMenuGameState;

public class Doors {

    private void setup() {
        Window.WINDOW.setup();
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

        MainMenuGameState.MAIN_MENU_GAME_STATE.use();
    }

    private void update() {
        TypedCharacters.TYPED_CHARACTERS.update();
        Keyboard.KEYBOARD.update();
        Window.WINDOW.update();
        Mouse.MOUSE.update();
        IncrementalWorkQueue.INCREMENTAL_WORK_QUEUE.update();
        ExitListener.EXIT_LISTENER.update();
        AbstractGameState.USED_GAME_STATE.update();
        LevelCache.LEVEL_CACHE.update();
    }

    private void tearDown() {
        ThreadPoolWorkQueue.THREAD_POOL_WORK_QUEUE.tearDown();
    }

    private void run() {
        try {
            this.setup();
            while(!Window.WINDOW.shouldClose()) {
                this.update();
            }
        } finally {
            this.tearDown();
        }
    }

    public static void main(String[] args) {
        new Doors().run();
    }

}
