package doors.state;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL42;

import doors.Config;
import doors.debug.DebugInformation;
import doors.graphics.mesh.Mesh;
import doors.graphics.mesh.MeshBuffer;
import doors.graphics.rendertarget.PhysicalRenderTarget;
import doors.graphics.rendertarget.ScreenVirtualRenderTarget;
import doors.graphics.shader.Shader;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.sample.EntityTextureSample;
import doors.graphics.texture.sample.ScreenRenderTextureSample;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.level.ILevelProvider;
import doors.level.Level;
import doors.level.cache.LevelCache;
import doors.level.camera.Camera;
import doors.level.camera.PlayerMovementSystem;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class ExploreGameState extends AbstractGameState implements ILevelProvider {

    private static int SPRITE_BATCH_QUADS = 1_000;

    private static Vector2in RETICULE_POSITION = new Vector2in()
        .set(Config.CONFIG.getResolution())
        .sub(EntityTextureSample.RETICULE.dimensions)
        .div(2);

    public static ExploreGameState EXPLORE_GAME_STATE = new ExploreGameState();

    private Level currentLevel;
    private Camera camera;
    private MeshBuffer meshBuffer = new MeshBuffer(SPRITE_BATCH_QUADS);
    private SpriteBatch spriteBatch = new SpriteBatch();
    private UIRoot exploreMenu = new UIRoot();
    private Mesh mesh = new Mesh();
    private boolean showMenu;

    private Vector3fl previousCameraPosition = new Vector3fl();

    public ExploreGameState() {
        this.camera = new Camera(new PlayerMovementSystem(this));
    }

    public void use(String levelName) {
        super.use();
        this.currentLevel = LevelCache.LEVEL_CACHE.getLevelCacheEntry(levelName).level;
        Mouse.MOUSE.setLocked(true);
    }

    private void renderCurrent() {
        ScreenVirtualRenderTarget.SCREEN_VIRTUAL_RENDER_TARGET.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        Shader.SHADER.setPerspectiveViewMatrices(
            this.camera.position, 
            this.camera.rotation
        );

        this.currentLevel.terrainData.render();
    }

    private void updateWorld() {
        this.previousCameraPosition.set(this.camera.position);
        this.camera.update();

        DebugInformation.DEBUG_INFORMATION.update(this.camera);
        DebugInformation.DEBUG_INFORMATION.writeDebugInformationToSpriteBatch(this.spriteBatch);

        this.spriteBatch.pushSprite(
            EntityTextureSample.RETICULE,
            RETICULE_POSITION,
            EntityTextureSample.RETICULE.dimensions,
            SpriteBatchHeight.HUD,
            Vector3fl.WHITE
        );

    }

    private void updateMenu() {
        this.exploreMenu.update();
        this.exploreMenu.writeUIRoot(this.spriteBatch);
        this.exploreMenu.selectedCursor.writeCursor(this.spriteBatch);
    }

    @Override
    public void update() {
        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
            this.showMenu = !this.showMenu;
            Mouse.MOUSE.setLocked(!this.showMenu);
        }

        this.meshBuffer.clear();
        this.spriteBatch.clear();

        if(this.showMenu) {
            this.updateMenu();
        } else {
            this.updateWorld();
        }

        this.renderCurrent();

        this.spriteBatch.pushSprite(
            ScreenRenderTextureSample.SCREEN_RENDER,
            Vector2in.ZERO,
            Config.CONFIG.getResolution(),
            SpriteBatchHeight.SCREEN,
            Vector3fl.WHITE
        );

        this.spriteBatch.writeSpriteBatchToMeshBuffer(this.meshBuffer);
        this.meshBuffer.writeMeshBufferToMesh(this.mesh);

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        Shader.SHADER.setFlatViewMatrices();
        this.mesh.render();
    }

    @Override
    public Level getLevel() {
        return this.currentLevel;
    }

}