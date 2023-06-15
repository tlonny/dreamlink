package doors;

import java.util.LinkedList;
import java.util.Queue;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import doors.graphics.Shader;
import doors.graphics.ImageTexture;
import doors.graphics.ModelMesh;
import doors.graphics.PhysicalRenderTarget;
import doors.graphics.TextureChannel;
import doors.graphics.perspective.FlatPerspective;
import doors.graphics.perspective.WorldPerspective;
import doors.graphics.RenderTargetTexture;
import doors.job.IWorkUnit;
import doors.io.Window;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacterStream;
import doors.utility.GLFns;
import doors.utility.Maths;
import doors.utility.Timer;
import doors.overlay.HUD;
import doors.terrain.Terrain;

public class Game {

    public static Game GAME = new Game();


    private static int JOB_LIMIT_MS = 10;
    public boolean renderRecurse;

    public Queue<IWorkUnit> workQueue;
    private Timer timer;
    public Terrain currentTerrain;

    public Game() {
        this.timer = new Timer();
        this.workQueue = new LinkedList<>();
    }

    private void setup() {

        Window.WINDOW.setup();
        Keyboard.KEYBOARD.setup();
        Mouse.MOUSE.setup();
        TypedCharacterStream.TYPED_CHARACTER_STREAM.setup();

        Shader.SHADER.setup();
        HUD.HUD.setup();

        ImageTexture.OVERLAY_TEXTURE.setup();
        ImageTexture.ENTITY_TEXTURE.setup();

        RenderTargetTexture.CURRENT_WORLD_RENDER_TARGET_TEXTURE.setup();
        RenderTargetTexture.TARGET_WORLD_RENDER_TARGET_TEXTURE.setup();

        TextureChannel.OVERLAY_TEXTURE_CHANNEL.useTexture(ImageTexture.OVERLAY_TEXTURE);
        TextureChannel.ENTITY_TEXTURE_CHANNEL.useTexture(ImageTexture.ENTITY_TEXTURE);

        ModelMesh.DOOR.setup();
        ModelMesh.PORTAL.setup();
        ModelMesh.UNIT.setup();

        new Terrain("scratch/sphere").setup();
        new Terrain("scratch/empty").setup();
        Camera.CAMERA.position.set(10, 2, 8);
        this.currentTerrain = Terrain.TERRAIN_LOOKUP.get("scratch/empty");

    }
    
    private void refresh() {
        Window.WINDOW.refresh();
        TypedCharacterStream.TYPED_CHARACTER_STREAM.refresh();
        Mouse.MOUSE.refresh();
    }
    
    private void renderWorld() {
        RenderTargetTexture.TARGET_WORLD_RENDER_TARGET_TEXTURE.use();
        GLFns.clear();
        GLFns.setDepthTest(true);
        GLFns.setFaceCulling(true);

        var openDoor = this.currentTerrain.openDoor;
        var targetTerrain = Terrain.TERRAIN_LOOKUP.get(openDoor.targetTerrain);

        var perspective = new WorldPerspective();
        perspective.alignToCamera();
        openDoor.mutate(perspective);
        perspective.apply();

        targetTerrain.render();

        RenderTargetTexture.CURRENT_WORLD_RENDER_TARGET_TEXTURE.use();
        GLFns.clear();

        perspective.alignToCamera();
        perspective.apply();

        this.currentTerrain.render();

        GLFns.setFaceCulling(false);

        TextureChannel.PORTAL_TEXTURE_CHANNEL.useTexture(RenderTargetTexture.TARGET_WORLD_RENDER_TARGET_TEXTURE);

        this.currentTerrain.renderPortal();
    }

    private void renderOverlay() {
        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.use();
        TextureChannel.PORTAL_TEXTURE_CHANNEL.useTexture(RenderTargetTexture.CURRENT_WORLD_RENDER_TARGET_TEXTURE);
        GLFns.clear();

        var perspective = new FlatPerspective();
        perspective.apply();

        GLFns.setDepthTest(false);
        GLFns.setFaceCulling(true);

        HUD.HUD.render();
    }

    private void run() {
        this.timer.resetStartTime();

        while(!Window.WINDOW.shouldClose()) {
            this.refresh();

            if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
                Window.WINDOW.setShouldClose();
            }

            this.timer.resetStartTime();

            while(timer.millisElapsed() <= JOB_LIMIT_MS && !this.workQueue.isEmpty()) {
                this.workQueue.remove().execute();
            }

            Camera.CAMERA.update();
            this.currentTerrain.simulate();

            this.renderWorld();
            this.renderOverlay();
        }
    }

    public static void main(String[] args) {
        GAME.setup();
        GAME.run();
    }

}
