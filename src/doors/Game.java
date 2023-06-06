package doors;

import java.util.LinkedList;
import java.util.Queue;

import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import doors.entity.EntityTransitionManager;
import doors.graphics.CoreShader;
import doors.graphics.GLConfig;
import doors.graphics.PortalShader;
import doors.graphics.RenderTarget;
import doors.graphics.ShaderProgram;
import doors.graphics.Texture;
import doors.job.IWorkUnit;
import doors.io.Window;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacterStream;
import doors.utility.Timer;
import doors.overlay.OverlayTexture;
import doors.overlay.PerformanceTracker;
import doors.overlay.Reticule;
import doors.overlay.VirtualScreen;
import doors.terrain.Terrain;

public class Game {

    public static OverlayTexture OVERLAY_TEXTURE = new OverlayTexture();

    public static Game GAME = new Game();
    public static Window WINDOW = new Window();
    public static Keyboard KEYBOARD = new Keyboard();
    public static TypedCharacterStream TYPED_CHARACTER_STREAM = new TypedCharacterStream(); 
    public static Mouse MOUSE = new Mouse();

    public static EntityTransitionManager ENTITY_TRANSITION_MANAGER = new EntityTransitionManager();
    public static CoreShader CORE_SHADER = new CoreShader();
    public static PortalShader PORTAL_SHADER = new PortalShader();
    public static Reticule RETICULE = new Reticule();
    public static Player PLAYER = new Player();
    public static WorldCamera WORLD_CAMERA = new WorldCamera();
    public static FlatCamera FLAT_CAMERA = new FlatCamera();
    public static PerformanceTracker PERFORMANCE_TRACKER = new PerformanceTracker();
    public static Terrain LEVEL_1 = new Terrain();
    public static Terrain LEVEL_2 = new Terrain();
    public static VirtualScreen SCREEN = new VirtualScreen();
    public static VirtualScreen PORTAL_SCREEN = new VirtualScreen();
    public static Texture SNOW_TEXTURE = new Texture(VirtualScreen.RESOLUTION);

    private static int WORLD_SIM_MS = 50;
    private static int JOB_LIMIT_MS = 10;

    public Queue<IWorkUnit> workQueue;
    private Timer timer;
    public Texture snow = new Texture(new Vector2i(1280,720));

    public Game() {
        this.timer = new Timer();
        this.workQueue = new LinkedList<>();
    }

    private void initialize() {
        WINDOW.setup();
        KEYBOARD.setup();
        MOUSE.setup();
        TYPED_CHARACTER_STREAM.setup();
        CORE_SHADER.setup();
        PORTAL_SHADER.setup();
        RETICULE.setup();
        OVERLAY_TEXTURE.setup();
        PERFORMANCE_TRACKER.setup();
        LEVEL_1.setup("scratch/world_1");
        LEVEL_2.setup("scratch/world_2");
        SCREEN.setup();
        PORTAL_SCREEN.setup();
        SNOW_TEXTURE.setup();
        SNOW_TEXTURE.bind();
        Texture.loadFromFile("scratch/snow.png");
    }
    
    private void refresh() {
        WINDOW.refresh();
        TYPED_CHARACTER_STREAM.refresh();
        MOUSE.refresh();
    }

    private void simulate() {
        ENTITY_TRANSITION_MANAGER.transition();
        PLAYER.simulate();
    }

    private void renderPortal() {
        GLConfig.WORLD_CONFIG.apply();

        PORTAL_SCREEN.bind();

        CORE_SHADER.bind();
        ShaderProgram.setView(WORLD_CAMERA);

        LEVEL_2.render();
    }

    private void renderWorld() {
        GLConfig.WORLD_CONFIG.apply();

        SCREEN.bind();

        CORE_SHADER.bind();
        ShaderProgram.setView(WORLD_CAMERA);

        LEVEL_1.render();

        PORTAL_SHADER.bind();
        ShaderProgram.setView(WORLD_CAMERA);

        LEVEL_1.renderDoors(PORTAL_SCREEN.texture);
    }

    private void renderOverlay() {
        GLConfig.OVERLAY_CONFIG.apply();

        SCREEN.bind();

        CORE_SHADER.bind();
        ShaderProgram.setView(FLAT_CAMERA);

        
        RETICULE.render();
        PERFORMANCE_TRACKER.render();
    }

    private void renderScreen() {
        CORE_SHADER.bind();
        RenderTarget.unbind();
        ShaderProgram.setView(FLAT_CAMERA);
        GLConfig.OVERLAY_CONFIG.apply();

        SCREEN.render();
    }

    private void clear() {
        SCREEN.bind();
        GL11.glClearColor(0f, 0f, 0f, 0f);
        RenderTarget.clear();

        PORTAL_SCREEN.bind();
        GL11.glClearColor(1f, 1f, 0f, 0f);
        RenderTarget.clear();

        RenderTarget.unbind();
        GL11.glClearColor(1f, 0f, 0f, 0f);
        RenderTarget.clear();
    }


    private void run() {
        this.initialize();
        this.timer.resetStartTime();

        while(!WINDOW.shouldClose()) {
            this.refresh();

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

            var simFactor = (float)this.timer.millisElapsed()/WORLD_SIM_MS;
            WORLD_CAMERA.update(simFactor);

            this.clear();
            this.renderPortal();
            this.renderWorld();
            this.renderOverlay();
            this.renderScreen();
        }
    }

    public static void main(String[] args) {
        GAME.run();
    }

}
