package doors.state;

import org.lwjgl.opengl.GL42;

import doors.Doors;
import doors.core.graphics.Shader;
import doors.core.io.Mouse;
import doors.level.Level;
import doors.level.LevelCache;
import doors.entity.Billboard;
import doors.entity.Camera;
import doors.entity.DebugInformation;
import doors.entity.DoorMesh;

public class EditorGameState extends GameState {

    public static EditorGameState EDITOR_GAME_STATE = new EditorGameState();

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
        Doors.RENDER_TARGET_CURRENT.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        Shader.SHADER.setPerspectiveViewMatrices(
            Camera.CAMERA.position, 
            Camera.CAMERA.rotation
        );

        this.currentLevel.render();

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
        Billboard.CURRENT_BILLBOARD.update();
        DebugInformation.DEBUG_INFORMATION.update();
        this.renderCurrent();
    }

}
