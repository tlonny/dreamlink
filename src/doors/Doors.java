package doors;

import doors.graphics.rendertarget.PhysicalRenderTarget;
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

import org.lwjgl.opengl.GL42;

import doors.debug.ExitListener;
import doors.debug.SimpleMesh;
import doors.state.AbstractGameState;
import doors.state.MainMenuGameState;
import doors.work.WorkQueue;
import doors.work.WorkUnit;

public class Doors {

    private void setup() {
        PortalVirtualRenderTarget.PORTAL_VIRTUAL_RENDER_TARGET.texture.useTexture();
        ScreenVirtualRenderTarget.SCREEN_VIRTUAL_RENDER_TARGET.texture.useTexture();

        var ewu = new WorkUnit(EntityTexture.ENTITY_TEXTURE::useTexture);
        ewu.registerDependency(EntityTexture.ENTITY_TEXTURE.setupWorkUnit);

        var mwu = new WorkUnit(MenuTexture.MENU_TEXTURE::useTexture);
        mwu.registerDependency(MenuTexture.MENU_TEXTURE.setupWorkUnit);

        var fwu = new WorkUnit(FontTexture.FONT_TEXTURE::useTexture);
        fwu.registerDependency(FontTexture.FONT_TEXTURE.setupWorkUnit);

        ewu.submit();
        mwu.submit();
        fwu.submit();

        MainMenuGameState.MAIN_MENU_GAME_STATE.use();
    }

    private SimpleMesh sm = new SimpleMesh();

    private void update() {
        TypedCharacters.TYPED_CHARACTERS.update();
        Keyboard.KEYBOARD.update();
        Window.WINDOW.update();
        Mouse.MOUSE.update();
        WorkQueue.WORK_QUEUE.update();
        ExitListener.EXIT_LISTENER.update();
        AbstractGameState.USED_GAME_STATE.update();
    }

    private void tearDown() {
        WorkQueue.WORK_QUEUE.tearDown();
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
        Window.WINDOW.noop();
        new Doors().run();
    }

}
