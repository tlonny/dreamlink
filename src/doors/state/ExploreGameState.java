package doors.state;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL42;

import doors.Config;
import doors.debug.DebugInformation;
import doors.graphics.mesh.DoorMesh;
import doors.graphics.mesh.Mesh;
import doors.graphics.mesh.MeshBuffer;
import doors.graphics.mesh.PortalMesh;
import doors.graphics.rendertarget.PhysicalRenderTarget;
import doors.graphics.rendertarget.PortalVirtualRenderTarget;
import doors.graphics.rendertarget.ScreenVirtualRenderTarget;
import doors.graphics.shader.Shader;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.EntityTexture;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.level.Door;
import doors.level.Level;
import doors.level.LevelCache;
import doors.level.camera.Camera;
import doors.level.camera.PlayerMovementSystem;
import doors.level.terrain.IHasTerrain;
import doors.level.terrain.Terrain;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class ExploreGameState extends AbstractGameState implements IHasTerrain {

    private static int SPRITE_BATCH_QUADS = 1_000;
    private static Vector3fl COLLIDER_DIMENSIONS = new Vector3fl(2f,2f,2f);

    private static Vector2in RETICULE_POSITION = new Vector2in()
        .set(Config.RESOLUTION)
        .sub(EntityTexture.ENTITY_TEXTURE.reticule.dimensions)
        .div(2);

    public static ExploreGameState EXPLORE_GAME_STATE = new ExploreGameState();

    private Level currentLevel;
    private Door openDoor;
    private Door shutDoor;
    private Camera camera;

    private float doorOpenFactor;

    private Level portalLevel;
    private Door portalDoor;
    private float portalRotation;

    private MeshBuffer meshBuffer = new MeshBuffer(SPRITE_BATCH_QUADS);
    private SpriteBatch spriteBatch = new SpriteBatch();
    private Mesh mesh = new Mesh();

    private Vector3fl previousCameraPosition = new Vector3fl();

    public ExploreGameState() {
        this.camera = new Camera(new PlayerMovementSystem(this));
    }

    public void setup() {
        this.mesh.setup();
    }

    public void use(String level) {
        super.use();
        this.currentLevel = LevelCache.LEVEL_CACHE.getLevel(level);
        Mouse.MOUSE.setLocked(true);

        var mainDoor = this.currentLevel.doors.get("main");
        this.camera.position.set(mainDoor.orientation.normal).mul(2f).add(mainDoor.position).add(0f, 2f, 0f);
        this.camera.rotation.set(mainDoor.orientation.rotation);
    }

    private void tryOpenDoor() {
        Door target = null;
        float distanceToTarget = Float.MAX_VALUE;

        for(var door : this.currentLevel.doors.values()) {
            var distance = door.position.getDistance(this.camera.position);
            if(distance < distanceToTarget) {
                target = door;
                distanceToTarget = distance;
            }
        }

        if(target == null)
            return;

        var targetLevel = LevelCache.LEVEL_CACHE.getLevel(target.targetLevel);
        if(this.openDoor == target || !targetLevel.terrain.isReady) {
            return;
        }

        this.shutDoor = this.openDoor;
        this.openDoor = target;
        this.doorOpenFactor = 0f;
    }

    private float getPortalRotation() {
        return (float)Math.PI - this.openDoor.orientation.rotation.y + this.portalDoor.orientation.rotation.y;
    }

    private void teleport() {
        var currentDot = new Vector3fl(this.camera.position)
            .sub(this.openDoor.position)
            .getDot(this.openDoor.orientation.normal);

        var previousDot = new Vector3fl(this.previousCameraPosition)
            .sub(this.openDoor.position)
            .getDot(this.openDoor.orientation.normal);

        if(Math.signum(currentDot) == Math.signum(previousDot)) {
            return;
        }

        var adjustedCollider = new Vector3fl(this.openDoor.position).sub(1f, 0f, 1f);
        if(!this.camera.position.isWithinBounds(adjustedCollider, COLLIDER_DIMENSIONS)) {
            return;
        }

        this.camera.position.sub(this.openDoor.position);
        this.camera.position.rotateY(this.portalRotation);
        this.camera.position.add(this.portalDoor.position);
        this.camera.rotation.y += this.portalRotation;
        this.camera.velocity.rotateY(this.portalRotation);

        var currentLevel = this.currentLevel;
        this.currentLevel = this.portalLevel;
        this.portalLevel = currentLevel;

        var currentDoor = this.openDoor;
        this.openDoor = this.portalDoor;
        this.portalDoor = currentDoor;
    }

    private void renderPortal() {
        PortalVirtualRenderTarget.PORTAL_VIRTUAL_RENDER_TARGET.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        var cameraPosition = new Vector3fl(this.camera.position)
            .sub(this.openDoor.position)
            .rotateY(this.portalRotation)
            .add(this.portalDoor.position);

        var cameraRotation = new Vector3fl(this.camera.rotation)
            .add(0f, this.portalRotation, 0f);

        Shader.SHADER.setPerspectiveViewMatrices(cameraPosition, cameraRotation);

        this.portalLevel.terrain.render();

        for(var door : this.portalLevel.doors.values()) {
            var level = LevelCache.LEVEL_CACHE.getLevel(door.targetLevel);
            if(!level.terrain.isReady) {
                DoorMesh.DOOR_MESH.renderLocked(
                    door.position,
                    door.orientation.rotation
                );
                continue;
            }

            DoorMesh.DOOR_MESH.render(
                door.position,
                door.orientation.rotation,
                door == this.portalDoor ? 1f : 0f
            );
        }
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

        if(this.openDoor != null) {
            PortalMesh.PORTAL_MESH.render(
                this.openDoor.position,
                this.openDoor.orientation.rotation,
                Vector3fl.ONE,
                Vector3fl.WHITE
            );
        }

        for(var door : this.currentLevel.doors.values()) {
            var level = LevelCache.LEVEL_CACHE.getLevel(door.targetLevel);
            if(!level.terrain.isReady) {
                DoorMesh.DOOR_MESH.renderLocked(
                    door.position,
                    door.orientation.rotation
                );
                continue;
            }

            if(door == this.openDoor) {
                DoorMesh.DOOR_MESH.render(
                    door.position,
                    door.orientation.rotation,
                    this.doorOpenFactor
                );
                continue;
            } 

            if (door == this.shutDoor) {
                DoorMesh.DOOR_MESH.render(
                    door.position,
                    door.orientation.rotation,
                    1f - this.doorOpenFactor
                );
                continue;
            }

            DoorMesh.DOOR_MESH.render(
                door.position,
                door.orientation.rotation,
                0f
            );
        }
    }

    @Override
    public void update() {
        this.meshBuffer.clear();
        this.spriteBatch.clear();

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_E)) {
            this.tryOpenDoor();
        }

        this.camera.update();
        DebugInformation.DEBUG_INFORMATION.update(this.camera);

        this.doorOpenFactor += 0.05f;
        this.doorOpenFactor = Math.min(this.doorOpenFactor, 1f);

        if(this.openDoor != null) {
            this.portalLevel = LevelCache.LEVEL_CACHE.getLevel(this.openDoor.targetLevel);
            this.portalDoor = portalLevel.doors.get(this.openDoor.targetDoor);
            this.portalRotation = this.getPortalRotation();

            this.teleport();
            this.renderPortal();
            this.previousCameraPosition.set(this.camera.position);
        }

        this.renderCurrent();

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
            SpriteBatchHeight.HUD,
            Vector3fl.WHITE
        );

        DebugInformation.DEBUG_INFORMATION.writeDebugInformation(this.spriteBatch);
        this.spriteBatch.writeToMeshBuffer(this.meshBuffer);
        this.mesh.loadDataFromMeshBuffer(this.meshBuffer);

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        Shader.SHADER.setFlatViewMatrices();
        this.mesh.render();
    }

    @Override
    public Terrain getTerrain() {
        return this.currentLevel.terrain;
    }

}