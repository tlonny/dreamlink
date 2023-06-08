package doors;

import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.glfw.GLFW;

import doors.graphics.CoreShader;
import doors.graphics.PhysicalRenderTarget;
import doors.utility.GLFns;
import doors.graphics.VirtualRenderTarget;
import doors.job.IWorkUnit;
import doors.io.Window;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacterStream;
import doors.utility.Timer;
import doors.overlay.OverlayTexture;
import doors.perspective.FlatPerspective;
import doors.perspective.WorldPerspective;
import doors.overlay.HUD;
import doors.terrain.Terrain;

public class Game {

    public static Game GAME = new Game();

    public Terrain level1 = new Terrain();

    private static int WORLD_SIM_MS = 50;
    private static int JOB_LIMIT_MS = 10;

    public Queue<IWorkUnit> workQueue;
    private Timer timer;

    public Game() {
        this.timer = new Timer();
        this.workQueue = new LinkedList<>();
    }

    private void initialize() {

        Window.WINDOW.setup();
        Keyboard.KEYBOARD.setup();
        Mouse.MOUSE.setup();
        TypedCharacterStream.TYPED_CHARACTER_STREAM.setup();

        CoreShader.CORE_SHADER.setup();
        HUD.HUD.setup();

        OverlayTexture.OVERLAY_TEXTURE.setup();
        VirtualRenderTarget.WORLD_RENDER_TARGET.setup();

        OverlayTexture.OVERLAY_TEXTURE.bindTexture();
        VirtualRenderTarget.WORLD_RENDER_TARGET.bindTexture();

        level1.setup("scratch/world_1");
    }
    
    private void refresh() {
        Window.WINDOW.refresh();
        TypedCharacterStream.TYPED_CHARACTER_STREAM.refresh();
        Mouse.MOUSE.refresh();
    }

    private void simulate() {
        Player.PLAYER.simulate();
    }

    private void renderWorld() {
        VirtualRenderTarget.WORLD_RENDER_TARGET.bindRenderTarget();

        CoreShader.CORE_SHADER.bindShaderProgram();
        CoreShader.setTextureChannels();
        CoreShader.setPerspective(WorldPerspective.WORLD_PERSPECTIVE);

        GLFns.enableDepthTest();
        GLFns.clear();

        this.level1.render();
    }

    private void renderOverlay() {
        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.bindRenderTarget();

        CoreShader.CORE_SHADER.bindShaderProgram();
        CoreShader.setTextureChannels();
        CoreShader.setPerspective(FlatPerspective.FLAT_PERSPECTIVE);

        GLFns.disableDepthTest();
        GLFns.clear();

        HUD.HUD.render();
    }

    private void run() {
        this.initialize();
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
            this.renderWorld();
            this.renderOverlay();
        }
    }

    public static void main(String[] args) {
        GAME.run();
    }

}
