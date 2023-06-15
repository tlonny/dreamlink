package doors;

import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.glfw.GLFW;

import doors.graphics.Shader;
import doors.graphics.ImageTexture;
import doors.graphics.ModelMesh;
import doors.graphics.PhysicalRenderTarget;
import doors.graphics.TextureChannel;
import doors.utility.GLFns;
import doors.graphics.RenderTargetTexture;
import doors.job.IWorkUnit;
import doors.io.Window;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacterStream;
import doors.utility.Timer;
import doors.perspective.FlatPerspective;
import doors.perspective.WorldPerspective;
import doors.overlay.HUD;
import doors.terrain.Terrain;

public class Game {

    public static Game GAME = new Game();


    private static int WORLD_SIM_MS = 50;
    private static int JOB_LIMIT_MS = 10;

    public Queue<IWorkUnit> workQueue;
    private Timer timer;

    private Terrain currentTerrain = new Terrain("scratch/empty");
    private Terrain portalTerrain = new Terrain("scratch/sphere");

    public Game() {
        this.timer = new Timer();
        this.workQueue = new LinkedList<>();
    }

    private void setup() {

        Window.WINDOW.setup();
        Keyboard.KEYBOARD.setup();
        Mouse.MOUSE.setup();
        TypedCharacterStream.TYPED_CHARACTER_STREAM.setup();

        Shader.CORE_SHADER.setup();
        Shader.PORTAL_SHADER.setup();
        HUD.HUD.setup();

        ImageTexture.OVERLAY_TEXTURE.setup();
        ImageTexture.ENTITY_TEXTURE.setup();

        RenderTargetTexture.WORLD_RENDER_TARGET_TEXTURE.setup();
        RenderTargetTexture.PORTAL_RENDER_TARGET_TEXTURE.setup();

        TextureChannel.OVERLAY_TEXTURE_CHANNEL.bindTextureToTextureChannel(ImageTexture.OVERLAY_TEXTURE);
        TextureChannel.ENTITY_TEXTURE_CHANNEL.bindTextureToTextureChannel(ImageTexture.ENTITY_TEXTURE);
        TextureChannel.PORTAL_RENDER_TEXTURE_CHANNEL.bindTextureToTextureChannel(RenderTargetTexture.PORTAL_RENDER_TARGET_TEXTURE);
        TextureChannel.WORLD_RENDER_TEXTURE_CHANNEL.bindTextureToTextureChannel(RenderTargetTexture.WORLD_RENDER_TARGET_TEXTURE);

        this.currentTerrain.setup();
        this.portalTerrain.setup();

        ModelMesh.DOOR.setup();
        ModelMesh.PORTAL.setup();
    }
    
    private void refresh() {
        Window.WINDOW.refresh();
        TypedCharacterStream.TYPED_CHARACTER_STREAM.refresh();
        Mouse.MOUSE.refresh();
    }

    private void simulate() {
        Player.PLAYER.simulate();
    }
    
    private void renderPortal() {
        RenderTargetTexture.PORTAL_RENDER_TARGET_TEXTURE.bindRenderTarget();

        Shader.CORE_SHADER.bindShader();
        Shader.setTextureChannels();
        Shader.setPerspective(WorldPerspective.WORLD_PERSPECTIVE);

        GLFns.enableDepthTest();
        GLFns.clear();
        this.portalTerrain.render();
    }

    private void renderWorld() {
        RenderTargetTexture.WORLD_RENDER_TARGET_TEXTURE.bindRenderTarget();

        Shader.CORE_SHADER.bindShader();
        Shader.setTextureChannels();
        Shader.setPerspective(WorldPerspective.WORLD_PERSPECTIVE);

        GLFns.enableDepthTest();
        GLFns.clear();
        GLFns.enableFaceCulling();

        this.currentTerrain.render();

        GLFns.disableFaceCulling();
        Shader.PORTAL_SHADER.bindShader();
        Shader.setTextureChannels();
        Shader.setPerspective(WorldPerspective.WORLD_PERSPECTIVE);

        this.currentTerrain.renderOpenDoor();
    }

    private void renderOverlay() {
        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.bindRenderTarget();

        Shader.CORE_SHADER.bindShader();
        Shader.setTextureChannels();
        Shader.setPerspective(FlatPerspective.FLAT_PERSPECTIVE);

        GLFns.disableDepthTest();
        GLFns.clear();

        HUD.HUD.render();
    }

    private void run() {
        this.timer.resetStartTime();

        while(!Window.WINDOW.shouldClose()) {
            this.refresh();

            if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
                Window.WINDOW.setShouldClose();
            }

            while(timer.millisElapsed() > WORLD_SIM_MS) {
                this.timer.incrementStartTime(WORLD_SIM_MS);
                this.simulate();
            }

            var jobLimit = timer.millisElapsed() + JOB_LIMIT_MS;
            while(timer.millisElapsed() <= jobLimit && !this.workQueue.isEmpty()) {
                this.workQueue.remove().execute();
            }

            var simFactor = (float)this.timer.millisElapsed()/WORLD_SIM_MS;
            WorldPerspective.WORLD_PERSPECTIVE.update(simFactor);
            this.renderPortal();
            this.renderWorld();
            this.renderOverlay();
        }
    }

    public static void main(String[] args) {
        GAME.setup();
        GAME.run();
    }

}
