package doors;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL15;

import doors.entity.EntityTransitionManager;
import doors.graphics.Shader;
import doors.io.Display;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacterStream;
import doors.utility.Maths;
import doors.utility.Timer;
import doors.overlay.DebugConsole;
import doors.overlay.OverlayAtlas;
import doors.overlay.PerformanceTracker;
import doors.overlay.Reticule;

public class Game {

    public static OverlayAtlas OVERLAY_TEXTURE = new OverlayAtlas();

    public static Display DISPLAY = new Display();
    public static Keyboard KEYBOARD = new Keyboard();
    public static TypedCharacterStream TYPED_CHARACTER_STREAM = new TypedCharacterStream(); 
    public static Mouse MOUSE = new Mouse();

    public static EntityTransitionManager ENTITY_TRANSITION_MANAGER = new EntityTransitionManager();
    public static Shader SHADER = new Shader();
    public static Reticule RETICULE = new Reticule();
    public static Player PLAYER = new Player();
    public static Camera CAMERA = new Camera();
    public static DebugConsole DEBUG_CONSOLE = new DebugConsole();
    public static PerformanceTracker PERFORMANCE_TRACKER = new PerformanceTracker();

    private static int WORLD_SIM_MS = 50;

    private static void init() {
        DISPLAY.setup();
        KEYBOARD.setup();
        MOUSE.setup();
        TYPED_CHARACTER_STREAM.setup();
        SHADER.setup();
        RETICULE.setup();
        DEBUG_CONSOLE.setup();
        OVERLAY_TEXTURE.setup();
        PERFORMANCE_TRACKER.setup();
    }

    private static void simulateWorld() {
        PLAYER.simulate();
        WORLD.simulate();

    }

    private static void simulateOverlay() {
        DEBUG_CONSOLE.simulate();
        PERFORMANCE_TRACKER.simulate();
    }

    private static void renderWorld(float simFactor) {
        GL15.glEnable(GL15.GL_DEPTH_TEST);
        CAMERA.prepare(simFactor);
        SHADER.setViewRotationMatrix(CAMERA.viewRotationMatrix);
        SHADER.setViewTranslationMatrix(CAMERA.viewTranslationMatrix);
        SHADER.setProjectionMatrix(CAMERA.projectionMatrix);

        WORLD.render();
    }

    private static void renderOverlay() {
        GL15.glDisable(GL15.GL_DEPTH_TEST);
        SHADER.setViewRotationMatrix(Maths.IDENTITY);
        SHADER.setViewTranslationMatrix(Maths.IDENTITY);
        SHADER.setProjectionMatrix(Maths.IDENTITY);

        RETICULE.render();
        DEBUG_CONSOLE.render();
        PERFORMANCE_TRACKER.render();
    }

    private static void scratch() {
        PLAYER.spatialComponent.position.set(
            Level.WORLD_BLOCK_DIMENSIONS.x / 2,
            Level.WORLD_BLOCK_DIMENSIONS.y + 20,
            Level.WORLD_BLOCK_DIMENSIONS.z / 2
        );
    }

    private static void run() {
        var simTimer = new Timer();
        while(!DISPLAY.shouldClose()) {
            TYPED_CHARACTER_STREAM.refresh();
            DISPLAY.refresh();
            MOUSE.loadMousePosition();
            MOUSE.resetMousePosition();

            if(KEYBOARD.isKeyDown(GLFW.GLFW_KEY_ESCAPE))
                DISPLAY.setShouldClose();

            while(simTimer.millisElapsed() > WORLD_SIM_MS) {
                ENTITY_TRANSITION_MANAGER.transition();
                simulateWorld();
                simTimer.incrementStartTime(WORLD_SIM_MS);
            }

            var simFactor = (float)simTimer.millisElapsed()/WORLD_SIM_MS;
            renderWorld(simFactor);
            simulateOverlay();
            renderOverlay();
        }
    }

    public static void main(String[] args) {
        init();
        scratch();
        run();
    }

}
