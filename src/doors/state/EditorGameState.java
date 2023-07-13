package doors.state;

import org.lwjgl.opengl.GL42;

import doors.core.Config;
import doors.core.GameState;
import doors.graphics.Shader;
import doors.graphics.mesh.DoorMesh;
import doors.graphics.mesh.SpriteBatch;
import doors.graphics.rendertarget.VirtualRenderTarget;
import doors.graphics.texture.EntityTextureAtlas;
import doors.io.Mouse;
import doors.level.Camera;
import doors.level.DebugInformation;
import doors.level.Level;
import doors.level.LevelCache;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class EditorGameState extends GameState {

    public static EditorGameState EDITOR_GAME_STATE = new EditorGameState();

    private static Vector2in RETICULE_POSITION = new Vector2in()
        .set(Config.RESOLUTION)
        .sub(EntityTextureAtlas.ENTITY_TEXTURE_ATLAS.reticule.dimensions)
        .div(2);

    private Level currentLevel;

    public void use(String level) {
        super.use();
        this.currentLevel = LevelCache.LEVEL_CACHE.getLevel(level);
        Mouse.MOUSE.centerLock = true;

        var mainDoor = this.currentLevel.doors.get("main");
        Camera.CAMERA.position.set(mainDoor.orientation.normal).mul(2f).add(mainDoor.position).add(0f, 1f, 0f);
        Camera.CAMERA.rotation.set(mainDoor.orientation.rotation);
    }

    private void renderCurrent() {
        VirtualRenderTarget.RENDER_TARGET_CURRENT.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        Shader.SHADER.setPerspectiveViewMatrices(
            Camera.CAMERA.position, 
            Camera.CAMERA.rotation
        );

        this.currentLevel.terrain.render();

        for(var door : this.currentLevel.doors.values()) {
            DoorMesh.DOOR_MESH.render(
                door.position,
                door.orientation.rotation,
                0f
            );
        }
    }

    @Override
    public void update() {
        Camera.CAMERA.update();

        SpriteBatch.SPRITE_BATCH.writeSprite(
            VirtualRenderTarget.RENDER_TARGET_CURRENT.screenSample,
            Vector2in.ZERO,
            Config.RESOLUTION,
            Vector3fl.WHITE
        );

        SpriteBatch.SPRITE_BATCH.writeSprite(
            EntityTextureAtlas.ENTITY_TEXTURE_ATLAS.reticule,
            RETICULE_POSITION,
            EntityTextureAtlas.ENTITY_TEXTURE_ATLAS.reticule.dimensions,
            Vector3fl.WHITE
        );

        DebugInformation.DEBUG_INFORMATION.update();

        this.renderCurrent();
    }

}
