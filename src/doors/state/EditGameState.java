package doors.state;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL42;

import doors.Config;
import doors.debug.DebugInformation;
import doors.graphics.mesh.DoorMesh;
import doors.graphics.mesh.Mesh;
import doors.graphics.mesh.MeshBuffer;
import doors.graphics.rendertarget.PhysicalRenderTarget;
import doors.graphics.rendertarget.ScreenVirtualRenderTarget;
import doors.graphics.shader.Shader;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.EntityTexture;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.level.Level;
import doors.level.LevelCache;
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
        .set(Config.RESOLUTION)
        .sub(EntityTexture.ENTITY_TEXTURE.reticule.dimensions)
        .div(2);

    private Level currentLevel;

    private VoxelRayCaster voxelRayCaster = new VoxelRayCaster();
    private boolean showMenu = false;
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

        if(this.editQuickBarComponent.selectedSlot == null) {
            return;
        }


        if(this.editQuickBarComponent.selectedSlot.value == null) {
            return;
        }

        this.voxelRayCaster.cast(this.camera.position, this.camera.direction);
        for(var ix = 0; ix < MAX_RAY_ITERATIONS; ix += 1) {
            // Can accidentally trap yourself...
            this.voxelRayCaster.advance();

            var block = this.currentLevel.terrain.getBlock(this.voxelRayCaster.rayCursor);
            if(block != null) {
                break;
            }
        }

        this.currentLevel.terrain.setBlock(
            this.voxelRayCaster.previousRayCursor,
            this.editQuickBarComponent.selectedSlot.value.block
        );
    }

    private void tryRemoveBlock() {
        if(!Mouse.MOUSE.isRightMouseButtonPressed()) {
            return;
        }

        this.voxelRayCaster.cast(this.camera.position, this.camera.direction);
        for(var ix = 0; ix < MAX_RAY_ITERATIONS; ix += 1) {
            var block = this.currentLevel.terrain.getBlock(this.voxelRayCaster.rayCursor);
            if(block != null) {
                break;
            }
            this.voxelRayCaster.advance();
        }

        this.currentLevel.terrain.setBlock(this.voxelRayCaster.rayCursor, null);
    }

    public void setup() {
        this.mesh.setup();
    }

    public void use(String level) {
        super.use();

        Mouse.MOUSE.setLocked(true);
        this.currentLevel = LevelCache.LEVEL_CACHE.getLevel(level);
        this.editMenuComponent.setLevel(this.currentLevel);
        var mainDoor = this.currentLevel.doors.get("main");

        this.camera.position.set(mainDoor.orientation.normal).mul(2f).add(mainDoor.position).add(0f, 1f, 0f);
        this.camera.rotation.set(mainDoor.orientation.rotation);
        this.camera.position.y = 6;

        //this.camera.position.add(0.3f);
    }

    private void renderCurrent() {
        ScreenVirtualRenderTarget.SCREEN_VIRTUAL_RENDER_TARGET.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        Shader.SHADER.setPerspectiveViewMatrices(
            this.camera.position, 
            this.camera.rotation
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
        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
            this.showMenu = !this.showMenu;
            Mouse.MOUSE.setLocked(!this.showMenu);
        }

        this.spriteBatch.clear();
        this.meshBuffer.clear();
        
        DebugInformation.DEBUG_INFORMATION.update(this.camera);

        this.spriteBatch.writeSprite(
            ScreenVirtualRenderTarget.SCREEN_VIRTUAL_RENDER_TARGET.texture.createTextureSample(),
            Vector2in.ZERO,
            Config.RESOLUTION,
            SpriteBatchHeight.SCREEN,
            Vector3fl.WHITE
        );

        this.spriteBatch.writeSprite(
            EntityTexture.ENTITY_TEXTURE.reticule,
            RETICULE_POSITION,
            EntityTexture.ENTITY_TEXTURE.reticule.dimensions,
            SpriteBatchHeight.SCREEN,
            Vector3fl.WHITE
        );

        DebugInformation.DEBUG_INFORMATION.writeDebugInformation(this.spriteBatch);

        this.editMenu.update();
        this.editMenu.writeUIRoot(this.spriteBatch);

        if(this.showMenu) {
            this.hiddenEditMenuComponent.isHidden = false;
            this.editMenu.selectedCursor.writeCursor(this.spriteBatch);
        } else {
            this.hiddenEditMenuComponent.isHidden = true;
            this.camera.update();
            this.tryAddBlock();
            this.tryRemoveBlock();
        }

        this.spriteBatch.writeToMeshBuffer(this.meshBuffer);
        this.mesh.loadDataFromMeshBuffer(this.meshBuffer);

        this.renderCurrent();

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        Shader.SHADER.setFlatViewMatrices();
        this.mesh.render();
    }

}