package doors.state;

import org.lwjgl.opengl.GL42;

import doors.graphics.mesh.Mesh;
import doors.graphics.mesh.MeshBuffer;
import doors.graphics.rendertarget.PhysicalRenderTarget;
import doors.graphics.shader.Shader;
import doors.graphics.spritebatch.SpriteBatch;
import doors.io.Mouse;
import doors.level.cache.LevelCache;
import doors.level.cache.LevelCacheEntryState;
import doors.ui.component.mainmenu.MainMenuEditComponent;
import doors.ui.component.mainmenu.MainMenuExploreComponent;
import doors.ui.component.mainmenu.MainMenuRootComponent;
import doors.ui.root.UIRoot;

public class MainMenuGameState extends AbstractGameState {

    private static int SPRITE_BATCH_QUADS = 1_000;

    public static MainMenuGameState MAIN_MENU_GAME_STATE = new MainMenuGameState();

    private MainMenuRootComponent rootMenuComponent = new MainMenuRootComponent();
    private MainMenuEditComponent editMenuComponent = new MainMenuEditComponent();
    private MainMenuExploreComponent exploreMenuComponent = new MainMenuExploreComponent();

    private UIRoot rootMenu = new UIRoot();
    private UIRoot editMenu = new UIRoot();
    private UIRoot exploreMenu = new UIRoot();
    private UIRoot usedRoot = this.editMenu;

    private String levelToExplore;

    private SpriteBatch spriteBatch = new SpriteBatch();
    private MeshBuffer meshBuffer = new MeshBuffer(SPRITE_BATCH_QUADS);
    private Mesh mesh = new Mesh();

    public MainMenuGameState() {
        this.rootMenu.rootComponents.add(this.rootMenuComponent);

        this.editMenu.rootComponents.add(this.editMenuComponent);

        this.exploreMenu.rootComponents.add(this.exploreMenuComponent);
    }

    @Override
    public void use() {
        super.use();
        Mouse.MOUSE.setLocked(false);
        this.editMenuComponent.readLevels();
        this.levelToExplore = null;
    }

    public void gotoExploreMenu() {
        this.usedRoot = this.exploreMenu;
    }

    public void gotoExplore(String levelName) {
        this.levelToExplore = levelName;
        LevelCache.LEVEL_CACHE.requestLevel(levelName);
    }

    public void gotoEditMenu() {
        this.usedRoot = this.editMenu;
    }

    public void gotoRootMenu() {
        this.usedRoot = this.rootMenu;
    }

    public LevelCacheEntryState getLevelCacheEntryState() {
        if(this.levelToExplore == null) {
            return null;
        }

        return LevelCache.LEVEL_CACHE.getLevelCacheEntryState(this.levelToExplore);
    }

    @Override
    public void update() {
        this.meshBuffer.clear();
        this.spriteBatch.clear();

        if(this.levelToExplore != null && LevelCache.LEVEL_CACHE.isLevelReady(this.levelToExplore)) {
            ExploreGameState.EXPLORE_GAME_STATE.use(this.levelToExplore);
        }

        var root = this.usedRoot;
        root.update();
        root.writeUIRoot(this.spriteBatch);
        root.selectedCursor.writeCursor(this.spriteBatch);

        this.spriteBatch.writeSpriteBatchToMeshBuffer(this.meshBuffer);
        this.meshBuffer.writeMeshTo(this.mesh);

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        Shader.SHADER.setFlatViewMatrices();

        this.mesh.render();
    }
}
