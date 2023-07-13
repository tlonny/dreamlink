package doors.state;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL42;

import doors.core.Config;
import doors.core.GameState;
import doors.graphics.Shader;
import doors.graphics.mesh.DoorMesh;
import doors.graphics.mesh.PortalMesh;
import doors.graphics.mesh.SpriteBatch;
import doors.graphics.rendertarget.VirtualRenderTarget;
import doors.graphics.texture.EntityTextureAtlas;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.level.Camera;
import doors.level.DebugInformation;
import doors.level.Door;
import doors.level.Level;
import doors.level.LevelCache;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class ExploreGameState extends GameState {

    private static Vector3fl COLLIDER_DIMENSIONS = new Vector3fl(2f,2f,2f);

    private static Vector2in RETICULE_POSITION = new Vector2in()
        .set(Config.RESOLUTION)
        .sub(EntityTextureAtlas.ENTITY_TEXTURE_ATLAS.reticule.dimensions)
        .div(2);

    public static ExploreGameState EXPLORE_GAME_STATE = new ExploreGameState();

    private Level currentLevel;
    private Door openDoor;
    private Door shutDoor;

    private float openFactor;

    private Level portalLevel;
    private Door portalDoor;
    private float portalRotation;

    private Vector3fl previousCameraPosition = new Vector3fl();

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
        if(this.openDoor == target || !targetLevel.terrain.isReady) {
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
        VirtualRenderTarget.RENDER_TARGET_PORTAL.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        var cameraPosition = new Vector3fl(Camera.CAMERA.position)
            .sub(this.openDoor.position)
            .rotateY(this.portalRotation)
            .add(this.portalDoor.position);

        var cameraRotation = new Vector3fl(Camera.CAMERA.rotation)
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
        VirtualRenderTarget.RENDER_TARGET_CURRENT.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        Shader.SHADER.setPerspectiveViewMatrices(
            Camera.CAMERA.position, 
            Camera.CAMERA.rotation
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
