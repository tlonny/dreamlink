package doors;

import org.lwjgl.opengl.GL42;

import doors.core.WorkQueue;
import doors.graphics.Shader;
import doors.graphics.mesh.DoorMesh;
import doors.graphics.mesh.PortalMesh;
import doors.graphics.mesh.SpriteBatch;
import doors.graphics.rendertarget.PhysicalRenderTarget;
import doors.graphics.rendertarget.VirtualRenderTarget;
import doors.graphics.texture.EntityTextureAtlas;
import doors.graphics.texture.FontTextureAtlas;
import doors.graphics.texture.MenuTextureAtlas;
import doors.io.ExitListener;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.Tick;
import doors.io.TypedCharacters;
import doors.io.Window;
import doors.state.EditorGameState;
import doors.state.ExploreGameState;
import doors.state.GameState;
import doors.state.MainMenuGameState;

public class Doors {

    private void setup() {
        Window.WINDOW.setup();
        Mouse.MOUSE.setup();
        Keyboard.KEYBOARD.setup();
        TypedCharacters.TYPED_CHARACTERS.setup();
        Shader.SHADER.setup();
        SpriteBatch.SPRITE_BATCH.setup();
        FontTextureAtlas.FONT_TEXTURE_ATLAS.setup();
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.setup();
        EntityTextureAtlas.ENTITY_TEXTURE_ATLAS.setup();
        VirtualRenderTarget.RENDER_TARGET_CURRENT.setup();
        VirtualRenderTarget.RENDER_TARGET_PORTAL.setup();
        DoorMesh.DOOR_MESH.setup();
        PortalMesh.PORTAL_MESH.setup();

        FontTextureAtlas.FONT_TEXTURE_ATLAS.useTexture();
        EntityTextureAtlas.ENTITY_TEXTURE_ATLAS.useTexture();
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.useTexture();
        VirtualRenderTarget.RENDER_TARGET_CURRENT.texture.useTexture();
        VirtualRenderTarget.RENDER_TARGET_PORTAL.texture.useTexture();

        MainMenuGameState.MAIN_MENU_GAME_STATE.use();
        //ExploreGameState.EXPLORE_GAME_STATE.use("sphere_2");
        //EditorGameState.EDITOR_GAME_STATE.use("sphere_2");
    }

    private void update() {
        Tick.TICK.update();
        TypedCharacters.TYPED_CHARACTERS.update();
        Keyboard.KEYBOARD.update();
        Window.WINDOW.update();
        Mouse.MOUSE.update();
        WorkQueue.WORK_QUEUE.update();
        ExitListener.EXIT_LISTENER.update();
        GameState.USED_GAME_STATE.update();
    }

    private void render() {
        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        Shader.SHADER.setFlatViewMatrices();
        SpriteBatch.SPRITE_BATCH.flush();
        SpriteBatch.SPRITE_BATCH.render();
    }

    private void run() {
        this.setup();
        while(!Window.WINDOW.shouldClose()) {
            this.update();
            this.render();
        }
    }

    public static void main(String[] args) {
        new Doors().run();
    }

}
