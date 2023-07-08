package doors.state.explore;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL42;

import doors.core.config.Config;
import doors.Doors;
import doors.Screen;
import doors.state.GameState;
import doors.core.graphics.Shader;
import doors.core.graphics.sprite.SpriteMeshBufferWriter;
import doors.core.io.Keyboard;
import doors.core.io.Mouse;
import doors.core.io.Window;
import doors.level.Door;
import doors.level.Level;
import doors.level.LevelCache;
import doors.core.utility.vector.Vector3fl;
import doors.entity.Camera;
import doors.graphics.entity.DoorMesh;
import doors.graphics.entity.PortalMesh;
import doors.core.utility.vector.Vector2in;

public class ExploreGameState extends GameState {

    private static Vector3fl COLLIDER_DIMENSIONS = new Vector3fl(2f,2f,2f);

    public static ExploreGameState EXPLORE_GAME_STATE = new ExploreGameState();

    public Level currentLevel;
    public Door openDoor;
    public Door shutDoor;

    private float openFactor;

    private Level portalLevel;
    private Door portalDoor;
    private float portalRotation;
    private SpriteMeshBufferWriter spriteWriter;

    private Vector3fl previousCameraPosition;

    public ExploreGameState() {
        this.previousCameraPosition = new Vector3fl();
        this.spriteWriter = new SpriteMeshBufferWriter(Screen.SCREEN.meshBuffer);
    }

    public void tryOpenDoor() {
        Door target = null;
        float distanceToTarget = Float.MAX_VALUE;

        for(var door : this.currentLevel.doors.values()) {
            var distance = door.position.getDistance(Camera.CAMERA.position);
            if(distance < distanceToTarget) {
                target = door;
                distanceToTarget = distance;
            }
        }

        if(target == null)
            return;

        var targetLevel = LevelCache.LEVEL_CACHE.getLevel(target.targetLevel);
        if(this.openDoor == target || !targetLevel.isReady) {
            return;
        }

        this.shutDoor = this.openDoor;
        this.openDoor = target;
        this.openFactor = 0f;
    }

    public void use(String level) {
        super.use();
        this.currentLevel = LevelCache.LEVEL_CACHE.getLevel(level);
        Mouse.MOUSE.centerLock = true;
        Window.WINDOW.setCursorVisibility(false);

        var mainDoor = this.currentLevel.doors.get("main");
        Camera.CAMERA.position.set(mainDoor.orientation.normal).mul(2f).add(mainDoor.position).add(0f, 1f, 0f);
        Camera.CAMERA.rotation.set(mainDoor.orientation.rotation);
    }

    private float getPortalRotation() {
        return (float)Math.PI - this.openDoor.orientation.rotation.y + this.portalDoor.orientation.rotation.y;
    }

    private void teleport() {
        var currentDot = new Vector3fl(Camera.CAMERA.position)
            .sub(this.openDoor.position)
            .getDot(this.openDoor.orientation.normal);

        var previousDot = new Vector3fl(this.previousCameraPosition)
            .sub(this.openDoor.position)
            .getDot(this.openDoor.orientation.normal);

        if(Math.signum(currentDot) == Math.signum(previousDot)) {
            return;
        }

        var adjustedCollider = new Vector3fl(this.openDoor.position).sub(1f, 0f, 1f);
        if(!Camera.CAMERA.position.isWithinBounds(adjustedCollider, COLLIDER_DIMENSIONS)) {
            return;
        }

        Camera.CAMERA.position.sub(this.openDoor.position);
        Camera.CAMERA.position.rotateY(this.portalRotation);
        Camera.CAMERA.position.add(this.portalDoor.position);
        Camera.CAMERA.rotation.y += this.portalRotation;
        Camera.CAMERA.velocity.rotateY(this.portalRotation);

        var currentLevel = this.currentLevel;
        this.currentLevel = this.portalLevel;
        this.portalLevel = currentLevel;

        var currentDoor = this.openDoor;
        this.openDoor = this.portalDoor;
        this.portalDoor = currentDoor;
    }

    private void renderPortal() {
        Doors.RENDER_TARGET_PORTAL.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        var cameraPosition = new Vector3fl(Camera.CAMERA.position)
            .sub(this.openDoor.position)
            .rotateY(this.portalRotation)
            .add(this.portalDoor.position);

        var cameraRotation = new Vector3fl(Camera.CAMERA.rotation)
            .add(0f, this.portalRotation, 0f);

        Shader.SHADER.setPerspectiveViewMatrices(cameraPosition, cameraRotation);

        this.portalLevel.render();

        for(var door : this.portalLevel.doors.values()) {
            var level = LevelCache.LEVEL_CACHE.getLevel(door.targetLevel);
            if(!level.isReady) {
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
        Doors.RENDER_TARGET_CURRENT.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        Shader.SHADER.setPerspectiveViewMatrices(
            Camera.CAMERA.position, 
            Camera.CAMERA.rotation
        );

        this.currentLevel.render();

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
            if(!level.isReady) {
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
                    this.openFactor
                );
                continue;
            } 

            if (door == this.shutDoor) {
                DoorMesh.DOOR_MESH.render(
                    door.position,
                    door.orientation.rotation,
                    1f - this.openFactor
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
        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_E)) {
            this.tryOpenDoor();
        }

        Camera.CAMERA.update();

        this.openFactor += 0.05f;
        this.openFactor = Math.min(this.openFactor, 1f);

        if(this.openDoor != null) {
            this.portalLevel = LevelCache.LEVEL_CACHE.getLevel(this.openDoor.targetLevel);
            this.portalDoor = portalLevel.doors.get(this.openDoor.targetDoor);
            this.portalRotation = this.getPortalRotation();

            this.teleport();
            this.renderPortal();
            this.previousCameraPosition.set(Camera.CAMERA.position);
        }

        this.renderCurrent();

        this.spriteWriter.writeSprite(
            Doors.RENDER_TARGET_CURRENT.screenSample,
            Vector2in.ZERO,
            Config.CONFIG.getResolution(),
            Vector3fl.WHITE
        );

        DebugInformation.DEBUG_INFORMATION.update();
    }

}
