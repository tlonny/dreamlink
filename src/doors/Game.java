package doors;

import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL15;

import doors.entity.EntityTransitionManager;
import doors.graphics.Shader;
import doors.job.IWorkUnit;
import doors.io.Window;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacterStream;
import doors.utility.Timer;
import doors.overlay.OverlayTexture;
import doors.overlay.PerformanceTracker;
import doors.overlay.Reticule;
import doors.terrain.Terrain;

public class Game {

    public static OverlayTexture OVERLAY_TEXTURE = new OverlayTexture();

    public static Game GAME = new Game();
    public static Window WINDOW = new Window();
    public static Keyboard KEYBOARD = new Keyboard();
    public static TypedCharacterStream TYPED_CHARACTER_STREAM = new TypedCharacterStream(); 
    public static Mouse MOUSE = new Mouse();

    public static EntityTransitionManager ENTITY_TRANSITION_MANAGER = new EntityTransitionManager();
    public static Shader SHADER = new Shader();
    public static Reticule RETICULE = new Reticule();
    public static Player PLAYER = new Player();
    public static Camera CAMERA = new Camera();
    public static PerformanceTracker PERFORMANCE_TRACKER = new PerformanceTracker();
    public static Terrain TERRAIN = new Terrain();
    public static Screen SCREEN = new Screen();

    private static int WORLD_SIM_MS = 50;
    private static int JOB_LIMIT_MS = 10;

    public Queue<IWorkUnit> workQueue;
    private Timer timer;

    public Game() {
        this.timer = new Timer();
        this.workQueue = new LinkedList<>();
    }

    private void initialize() {
        WINDOW.setup();
        KEYBOARD.setup();
        MOUSE.setup();
        TYPED_CHARACTER_STREAM.setup();
        SHADER.setup();
        RETICULE.setup();
        OVERLAY_TEXTURE.setup();
        PERFORMANCE_TRACKER.setup();
        TERRAIN.setup("scratch/test");
        SCREEN.setup();
    }

    private void refresh() {
        TYPED_CHARACTER_STREAM.refresh();
        WINDOW.refresh();
        MOUSE.refresh();
    }

    private void simulate() {
        ENTITY_TRANSITION_MANAGER.transition();
        PLAYER.simulate();
    }

    private void renderWorld() {
        var simFactor = (float)this.timer.millisElapsed()/WORLD_SIM_MS;
        CAMERA.prepare(simFactor);
        GL15.glEnable(GL15.GL_DEPTH_TEST);
        SHADER.bind();
        SHADER.writeAmbientLight();
        SHADER.ambientLight.set(1f, 1f, 1f);
        SHADER.viewRotationMatrix.set(CAMERA.viewRotationMatrix);
        SHADER.writeViewRotationMatrix();
        SHADER.viewTranslationMatrix.set(CAMERA.viewTranslationMatrix);
        SHADER.writeViewTranslationMatrix();
        SHADER.projectionMatrix.set(CAMERA.projectionMatrix);
        SHADER.writeProjectionMatrix();

        TERRAIN.render();
    }

    private void renderOverlay() {
        GL15.glDisable(GL15.GL_DEPTH_TEST);
        SHADER.bind();
        SHADER.viewRotationMatrix.identity();
        SHADER.writeViewRotationMatrix();
        SHADER.viewTranslationMatrix.identity();
        SHADER.writeViewTranslationMatrix();
        SHADER.projectionMatrix.identity();
        SHADER.writeProjectionMatrix();
        SHADER.modelMatrix.identity();
        SHADER.writeModelMatrix();


        RETICULE.render();
        PERFORMANCE_TRACKER.render();
    }

    private void run() {
        this.initialize();

        this.timer.resetStartTime();
        while(!WINDOW.shouldClose()) {
            refresh();

            if(KEYBOARD.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
                WINDOW.setShouldClose();
            }

            while(timer.millisElapsed() > WORLD_SIM_MS) {
                this.timer.incrementStartTime(WORLD_SIM_MS);
                this.simulate();
            }

            var jobLimit = timer.millisElapsed() + JOB_LIMIT_MS;
            while(timer.millisElapsed() <= jobLimit && !this.workQueue.isEmpty()) {
                this.workQueue.remove().execute();
            }

            this.renderWorld();
            this.renderOverlay();

            SCREEN.flush();
        }
    }

    public static void main(String[] args) {
        GAME.run();
    }

}
