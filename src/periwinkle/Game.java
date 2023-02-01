package periwinkle;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL15;

import periwinkle.entity.EntityTransitionManager;
import periwinkle.environment.ParticleAtlas;
import periwinkle.environment.RainEmitter;
import periwinkle.environment.SkyBox;
import periwinkle.environment.SkyType;
import periwinkle.graphics.Shader;
import periwinkle.io.Display;
import periwinkle.io.Keyboard;
import periwinkle.io.Mouse;
import periwinkle.io.TypedCharacterStream;
import periwinkle.terrain.TerrainAtlas;
import periwinkle.terrain.World;
import periwinkle.utility.Maths;
import periwinkle.utility.Timer;
import periwinkle.terrain.Generator;
import periwinkle.overlay.DebugConsole;
import periwinkle.overlay.OverlayAtlas;
import periwinkle.overlay.PerformanceTracker;
import periwinkle.overlay.Reticule;

public class Game {

    public static OverlayAtlas OVERLAY_ATLAS = new OverlayAtlas();
    public static TerrainAtlas TERRAIN_ATLAS = new TerrainAtlas();
    public static ParticleAtlas PARTICLE_ATLAS = new ParticleAtlas();

    public static Display DISPLAY = new Display();
    public static Keyboard KEYBOARD = new Keyboard();
    public static TypedCharacterStream TYPED_CHARACTER_STREAM = new TypedCharacterStream(); 
    public static Mouse MOUSE = new Mouse();

    public static EntityTransitionManager ENTITY_TRANSITION_MANAGER = new EntityTransitionManager();
    public static World WORLD = new World();
    public static Shader SHADER = new Shader();
    public static SkyBox SKY_BOX = new SkyBox();
    public static Reticule RETICULE = new Reticule();
    public static Player PLAYER = new Player();
    public static Camera CAMERA = new Camera();
    public static RainEmitter RAIN_EMITTER = new RainEmitter();
    public static DebugConsole DEBUG_CONSOLE = new DebugConsole();
    public static PerformanceTracker PERFORMANCE_TRACKER = new PerformanceTracker();
    public static Generator GENERATOR = new Generator();

    public static SkyType SKY_TYPE = SkyType.CLEAR_DAY;

    private static int WORLD_SIM_MS = 50;

    private static void init() {
        DISPLAY.setup();
        KEYBOARD.setup();
        MOUSE.setup();
        TYPED_CHARACTER_STREAM.setup();
        WORLD.setup();
        SHADER.setup();
        SKY_BOX.setup();
        RETICULE.setup();
        DEBUG_CONSOLE.setup();
        OVERLAY_ATLAS.setup();
        TERRAIN_ATLAS.setup();
        PARTICLE_ATLAS.setup();
        PERFORMANCE_TRACKER.setup();
        RAIN_EMITTER.setup();
    }

    private static void simulateWorld() {
        PLAYER.simulate();
        WORLD.simulate();
        RAIN_EMITTER.simulate();

    }

    private static void simulateOverlay() {
        DEBUG_CONSOLE.simulate();
        PERFORMANCE_TRACKER.simulate();
    }

    private static void renderWorld(float simFactor) {
        GL15.glEnable(GL15.GL_DEPTH_TEST);
        CAMERA.prepare(simFactor);
        SHADER.setGlobalColor(SKY_TYPE.skyColor);
        SHADER.setViewRotationMatrix(CAMERA.viewRotationMatrix);
        SHADER.setViewTranslationMatrix(CAMERA.viewTranslationMatrix);
        SHADER.setProjectionMatrix(CAMERA.projectionMatrix);

        WORLD.render();
        RAIN_EMITTER.render(simFactor);
        SKY_BOX.render();
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
            World.WORLD_BLOCK_DIMENSIONS.x / 2,
            World.WORLD_BLOCK_DIMENSIONS.y + 20,
            World.WORLD_BLOCK_DIMENSIONS.z / 2
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
