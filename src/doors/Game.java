package doors;

import doors.graphics.Shader;
import doors.graphics.mesh.ModelMesh;
import doors.graphics.rendertarget.RenderTargetTexture;
import doors.graphics.texture.ImageTexture;
import doors.graphics.texture.TextureChannel;
import doors.state.GameState;
import doors.state.MainMenuExploreGameState;
import doors.state.MainMenuRootGameState;
import doors.io.Window;
import doors.overlay.SpriteBatch;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacters;

public class Game {

    private static void setup() {
        Window.WINDOW.setup();
        Mouse.MOUSE.setup();
        Keyboard.KEYBOARD.setup();
        TypedCharacters.TYPED_CHARACTERS.setup();
        Shader.SHADER.setup();

        ModelMesh.UNIT.setup();
        ModelMesh.DOOR.setup();
        ModelMesh.PORTAL.setup();
        ModelMesh.ARROW.setup();

        SpriteBatch.SPRITE_BATCH.setup();

        Camera.CAMERA.setup();

        ImageTexture.STANDARD_FONT_TEXTURE.setup();
        ImageTexture.UI_TEXTURE.setup();
        ImageTexture.ENTITY_TEXTURE.setup();

        RenderTargetTexture.CURRENT_WORLD_RENDER_TARGET_TEXTURE.setup();
        RenderTargetTexture.PORTAL_WORLD_RENDER_TARGET_TEXTURE.setup();

        TextureChannel.UI_TEXTURE_CHANNEL.useTexture(ImageTexture.UI_TEXTURE);
        TextureChannel.FONT_TEXTURE_CHANNEL.useTexture(ImageTexture.STANDARD_FONT_TEXTURE);
        TextureChannel.ENTITY_TEXTURE_CHANNEL.useTexture(ImageTexture.ENTITY_TEXTURE);
        TextureChannel.PORTAL_TEXTURE_CHANNEL.useTexture(RenderTargetTexture.PORTAL_WORLD_RENDER_TARGET_TEXTURE);
        TextureChannel.WORLD_TEXTURE_CHANNEL.useTexture(RenderTargetTexture.CURRENT_WORLD_RENDER_TARGET_TEXTURE);

        MainMenuRootGameState.MAIN_MENU_ROOT_GAME_STATE.setup();
        MainMenuExploreGameState.MAIN_MENU_EXPLORE_GAME_STATE.setup();

        Camera.CAMERA.position.set(20,20,20);
        MainMenuRootGameState.MAIN_MENU_ROOT_GAME_STATE.use();
    }

    private static void run() {
        while(!Window.WINDOW.shouldClose()) {
            TypedCharacters.TYPED_CHARACTERS.update();
            Window.WINDOW.update();
            Mouse.MOUSE.update();
            WorkQueue.WORK_QUEUE.update();
            GameState.USED_GAME_STATE.update();
            ExitListener.EXIT_LISTENER.update();
        }
    }

    public static void main(String[] args) {
        setup();
        run();
    }

}
