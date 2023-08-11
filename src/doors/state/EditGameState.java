package doors.state;

import java.nio.file.Paths;

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
import doors.level.Level;
import doors.level.block.BlockData;
import doors.level.camera.Camera;
import doors.level.camera.NoClipMovementSystem;
import doors.ui.component.HiddenComponent;
import doors.ui.component.edit.EditMenuComponent;
import doors.ui.component.edit.EditQuickBarComponent;
import doors.ui.root.UIRoot;
import doors.utility.VoxelRayCaster;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class EditGameState extends AbstractGameState {

    private static int MAX_QUADS = 1000;
    private static int MAX_RAY_ITERATIONS = 50;

    public static EditGameState EDIT_GAME_STATE = new EditGameState();

    private static Vector2in RETICULE_POSITION = new Vector2in()
        .set(Config.CONFIG.getResolution())
        .sub(EntityTextureSample.RETICULE.dimensions)
        .div(2);

    private Level currentLevel;

    public EditMenuState menuState = EditMenuState.HIDDEN;

    private BlockData blockDataBuffer = new BlockData();
    private VoxelRayCaster voxelRayCaster = new VoxelRayCaster();
    private MeshBuffer meshBuffer = new MeshBuffer(MAX_QUADS);
    private SpriteBatch spriteBatch = new SpriteBatch();
    private Mesh mesh = new Mesh();

    private EditMenuComponent editMenuComponent = new EditMenuComponent();
    private EditQuickBarComponent editQuickBarComponent = new EditQuickBarComponent();

    private HiddenComponent hiddenEditMenuComponent = new HiddenComponent(this.editMenuComponent, true);
    private UIRoot editMenu = new UIRoot();

    private Camera camera = new Camera(new NoClipMovementSystem());

    public EditGameState() {
        this.editMenu.rootComponents.add(this.hiddenEditMenuComponent);
        this.editMenu.rootComponents.add(this.editQuickBarComponent);
    }

    private void tryAddBlock() {
        if(!Mouse.MOUSE.isLeftMouseButtonPressed()) {
            return;
        }

        var selectedBlock = this.editQuickBarComponent.getSelectedBlock();
        if(selectedBlock == null) {
            return;
        }

        this.voxelRayCaster.cast(this.camera.position, this.camera.direction);
        for(var ix = 0; ix < MAX_RAY_ITERATIONS; ix += 1) {
            // Can accidentally trap yourself...
            this.voxelRayCaster.advance();

            this.currentLevel.terrainData.getBlockData(this.voxelRayCaster.rayCursor, this.blockDataBuffer);
            if(this.blockDataBuffer.block != null) {
                break;
            }
        }

        this.currentLevel.terrainData.setBlock(
            this.voxelRayCaster.previousRayCursor,
            selectedBlock,
            this.camera.getOrientation().getOpposite()
        );
    }

    private void tryRemoveBlock() {
        if(!Mouse.MOUSE.isRightMouseButtonPressed()) {
            return;
        }

        this.voxelRayCaster.cast(this.camera.position, this.camera.direction);
        for(var ix = 0; ix < MAX_RAY_ITERATIONS; ix += 1) {
            this.currentLevel.terrainData.getBlockData(this.voxelRayCaster.rayCursor, this.blockDataBuffer);
            if(this.blockDataBuffer.block != null) {
                break;
            }
            this.voxelRayCaster.advance();
        }

        this.currentLevel.terrainData.setBlock(
            this.voxelRayCaster.rayCursor, 
            null,
            this.camera.getOrientation()
        );
    }

    public void use(String levelName) {
        super.use();

        // TODO: Dispose of old level
        var levelDirectory = Paths.get(Config.CONFIG.getWorkspacePath(), levelName).toString();
        this.currentLevel = new Level(levelDirectory);

        Mouse.MOUSE.setLocked(true);
        this.editMenuComponent.setLevel(this.currentLevel);
        this.menuState = EditMenuState.HIDDEN;

    }

    private void renderLevel() {
        ScreenVirtualRenderTarget.SCREEN_VIRTUAL_RENDER_TARGET.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        Shader.SHADER.setPerspectiveViewMatrices(
            this.camera.position, 
            this.camera.rotation
        );

        this.currentLevel.terrainData.render();
    }

    @Override
    public void update() {
        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
            this.menuState = this.menuState == EditMenuState.HIDDEN ? EditMenuState.MENU : EditMenuState.HIDDEN;
        }

        var menuState = this.menuState;
        Mouse.MOUSE.setLocked(menuState == EditMenuState.HIDDEN);

        this.spriteBatch.clear();
        this.meshBuffer.clear();
        
        DebugInformation.DEBUG_INFORMATION.update(this.camera);

        this.spriteBatch.pushSprite(
            ScreenRenderTextureSample.SCREEN_RENDER,
            Vector2in.ZERO,
            Config.CONFIG.getResolution(),
            SpriteBatchHeight.SCREEN,
            Vector3fl.WHITE
        );

        this.spriteBatch.pushSprite(
            EntityTextureSample.RETICULE,
            RETICULE_POSITION,
            EntityTextureSample.RETICULE.dimensions,
            SpriteBatchHeight.SCREEN,
            Vector3fl.WHITE
        );

        DebugInformation.DEBUG_INFORMATION.writeDebugInformationToSpriteBatch(this.spriteBatch);

        this.hiddenEditMenuComponent.isHidden = menuState != EditMenuState.MENU;

        this.editMenu.update();
        this.editMenu.writeUIRoot(this.spriteBatch);

        if(menuState == EditMenuState.HIDDEN) {
            this.camera.update();
            this.tryAddBlock();
            this.tryRemoveBlock();
        } else {
            this.editMenu.selectedCursor.writeCursor(this.spriteBatch);
        }

        this.spriteBatch.writeSpriteBatchToMeshBuffer(this.meshBuffer);
        this.meshBuffer.writeMeshBufferToMesh(this.mesh);

        this.renderLevel();

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        Shader.SHADER.setFlatViewMatrices();
        this.mesh.render();
    }

}