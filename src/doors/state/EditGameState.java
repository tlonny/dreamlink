package doors.state;

import java.nio.file.Paths;

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
import doors.level.Door;
import doors.level.Level;
import doors.level.camera.Camera;
import doors.level.camera.NoClipMovementSystem;
import doors.ui.component.HiddenComponent;
import doors.ui.component.edit.EditDoorMenuComponent;
import doors.ui.component.edit.EditMenuComponent;
import doors.ui.component.edit.EditMenuState;
import doors.ui.component.edit.EditQuickBarComponent;
import doors.ui.root.UIRoot;
import doors.utility.VoxelRayCaster;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class EditGameState extends AbstractGameState {

    private static int MAX_QUADS = 1000;
    private static int MAX_RAY_ITERATIONS = 50;
    private static float MAX_DOOR_DISTANCE = 10f;

    public static EditGameState EDIT_GAME_STATE = new EditGameState();

    private static Vector2in RETICULE_POSITION = new Vector2in()
        .set(Config.RESOLUTION)
        .sub(EntityTexture.ENTITY_TEXTURE.reticule.dimensions)
        .div(2);

    private Level currentLevel;

    private VoxelRayCaster voxelRayCaster = new VoxelRayCaster();
    public EditMenuState menuState = EditMenuState.HIDDEN;
    private MeshBuffer meshBuffer = new MeshBuffer(MAX_QUADS);
    private SpriteBatch spriteBatch = new SpriteBatch();
    private Mesh mesh = new Mesh();
    private Door targetDoor;

    private EditMenuComponent editMenuComponent = new EditMenuComponent();
    private EditDoorMenuComponent editDoorMenuComponent = new EditDoorMenuComponent();
    private EditQuickBarComponent editQuickBarComponent = new EditQuickBarComponent();

    private HiddenComponent hiddenEditMenuComponent = new HiddenComponent(this.editMenuComponent, true);
    private HiddenComponent hiddenDoorMenuComponent = new HiddenComponent(this.editDoorMenuComponent, true);
    private UIRoot editMenu = new UIRoot();

    private Camera camera = new Camera(new NoClipMovementSystem());

    public EditGameState() {
        this.editMenu.rootComponents.add(this.hiddenEditMenuComponent);
        this.editMenu.rootComponents.add(this.editQuickBarComponent);
        this.editMenu.rootComponents.add(this.hiddenDoorMenuComponent);
    }

    private void tryEditDoor() {
        this.targetDoor = null;
        float distanceToTarget = MAX_DOOR_DISTANCE;

        for(var door : this.currentLevel.doors.values()) {
            var distance = door.position.getDistance(this.camera.position);
            if(distance < distanceToTarget) {
                this.targetDoor = door;
                distanceToTarget = distance;
            }
        }

        if(this.targetDoor == null) {
            return;
        }

       this.editDoorMenuComponent.onSubmit = this.targetDoor::setName;
       this.menuState = EditMenuState.DOOR_CONFIG;
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

            var block = this.currentLevel.terrain.getBlock(this.voxelRayCaster.rayCursor);
            if(block != null) {
                break;
            }
        }

        this.currentLevel.terrain.setBlock(
            this.voxelRayCaster.previousRayCursor,
            selectedBlock
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

    public void use(String levelName) {
        super.use();

        // TODO: Dispose of old level
        var levelDirectory = Paths.get(Config.WORKSPACE_PATH, levelName).toString();
        this.currentLevel = new Level(levelDirectory);

        Mouse.MOUSE.setLocked(true);
        this.editMenuComponent.setLevel(this.currentLevel);
        var mainDoor = this.currentLevel.doors.get("main");

        this.camera.position.set(mainDoor.orientation.normal).mul(2f).add(mainDoor.position).add(0f, 1f, 0f);
        this.camera.rotation.set(mainDoor.orientation.rotation);
        this.camera.position.y = 6;
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
            this.menuState = this.menuState == EditMenuState.HIDDEN ? EditMenuState.MENU : EditMenuState.HIDDEN;
        }

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_E)) {
            this.tryEditDoor();
        }

        var menuState = this.menuState;
        Mouse.MOUSE.setLocked(menuState == EditMenuState.HIDDEN);

        this.spriteBatch.clear();
        this.meshBuffer.clear();
        
        DebugInformation.DEBUG_INFORMATION.update(this.camera);

        this.spriteBatch.pushSprite(
            ScreenVirtualRenderTarget.SCREEN_VIRTUAL_RENDER_TARGET.texture.createTextureSample(),
            Vector2in.ZERO,
            Config.RESOLUTION,
            SpriteBatchHeight.SCREEN,
            Vector3fl.WHITE
        );

        this.spriteBatch.pushSprite(
            EntityTexture.ENTITY_TEXTURE.reticule,
            RETICULE_POSITION,
            EntityTexture.ENTITY_TEXTURE.reticule.dimensions,
            SpriteBatchHeight.SCREEN,
            Vector3fl.WHITE
        );

        DebugInformation.DEBUG_INFORMATION.writeDebugInformationToSpriteBatch(this.spriteBatch);

        this.hiddenEditMenuComponent.isHidden = menuState != EditMenuState.MENU;
        this.hiddenDoorMenuComponent.isHidden = menuState != EditMenuState.DOOR_CONFIG;

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
        this.meshBuffer.writeMeshTo(this.mesh);

        this.renderLevel();

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        Shader.SHADER.setFlatViewMatrices();
        this.mesh.render();
    }

}