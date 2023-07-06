package doors;

import org.lwjgl.opengl.GL42;
import doors.core.graphics.mesh.ModelMesh;
import doors.core.graphics.rendertarget.PhysicalRenderTarget;
import doors.core.graphics.rendertarget.RenderTargetTexture;
import doors.core.graphics.sprite.SpriteBatch;
import doors.core.graphics.texture.ImageTexture;
import doors.core.graphics.texture.TextureChannel;
import doors.state.explore.Camera;
import doors.state.explore.ExploreGameState;
import doors.state.MainMenuExploreGameState;
import doors.state.MainMenuRootGameState;
import doors.core.graphics.camera.FlatCamera;
import doors.core.Game;
import doors.state.GameState;

public class Doors extends Game {

    public static ModelMesh MESH_UNIT = new ModelMesh("data/model/unit.json");
    public static ModelMesh MESH_DOOR = new ModelMesh("data/model/door.json");
    public static ModelMesh MESH_PORTAL = new ModelMesh("data/model/portal.json");
    public static ModelMesh MESH_ARROW = new ModelMesh("data/model/arrow.json");

    public static ImageTexture TEXTURE_FONT = new ImageTexture("data/texture/font/standard.png");
    public static ImageTexture TEXTURE_UI = new ImageTexture("data/texture/ui.png");
    public static ImageTexture TEXTURE_ENTITY = new ImageTexture("data/texture/entity.png");

    public static TextureChannel TEXTURE_CHANNEL_FONT = new TextureChannel("font", GL42.GL_TEXTURE1);
    public static TextureChannel TEXTURE_CHANNEL_UI = new TextureChannel("ui", GL42.GL_TEXTURE2);
    public static TextureChannel TEXTURE_CHANNEL_ENTITY = new TextureChannel("entity", GL42.GL_TEXTURE3);
    public static TextureChannel BLOCK_TEXTURE_CHANNEL = new TextureChannel("block", GL42.GL_TEXTURE4);
    public static TextureChannel TEXTURE_CHANNEL_PORTAL = new TextureChannel("portal", GL42.GL_TEXTURE5);
    public static TextureChannel TEXTURE_CHANNEL_CURRENT = new TextureChannel("current", GL42.GL_TEXTURE6);

    public static RenderTargetTexture RENDER_TARGET_CURRENT = new RenderTargetTexture();
    public static RenderTargetTexture RENDER_TARGET_PORTAL = new RenderTargetTexture();

    public static SpriteBatch SPRITE_BATCH = new SpriteBatch(5_000);

    @Override
    protected void setup() {
        super.setup();

        MESH_UNIT.setup();
        MESH_DOOR.setup();
        MESH_PORTAL.setup();
        MESH_ARROW.setup();

        SPRITE_BATCH.setup();

        TEXTURE_FONT.setup();
        TEXTURE_UI.setup();
        TEXTURE_ENTITY.setup();

        RENDER_TARGET_CURRENT.setup();
        RENDER_TARGET_PORTAL.setup();

        TEXTURE_CHANNEL_UI.useTexture(TEXTURE_UI);
        TEXTURE_CHANNEL_FONT.useTexture(TEXTURE_FONT);
        TEXTURE_CHANNEL_ENTITY.useTexture(TEXTURE_ENTITY);
        TEXTURE_CHANNEL_PORTAL.useTexture(RENDER_TARGET_PORTAL);
        TEXTURE_CHANNEL_CURRENT.useTexture(RENDER_TARGET_CURRENT);

        MainMenuRootGameState.MAIN_MENU_ROOT_GAME_STATE.setup();
        MainMenuExploreGameState.MAIN_MENU_EXPLORE_GAME_STATE.setup();

        Camera.CAMERA.position.set(10,2,12);
        MainMenuRootGameState.MAIN_MENU_ROOT_GAME_STATE.use();
    }

    @Override
    protected void update() {
        super.update();

        SPRITE_BATCH.startBatch();
        GameState.USED_GAME_STATE.update();
        SPRITE_BATCH.endBatch();

        ExitListener.EXIT_LISTENER.update();

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        FlatCamera.FLAT_CAMERA.apply();
        SPRITE_BATCH.render();
    }

    public static void main(String[] args) {
        new Doors().run();
    }

}
