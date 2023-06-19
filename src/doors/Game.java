package doors;

import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.opengl.GL42;

import doors.graphics.Shader;
import doors.ui.Menu;
import doors.overlay.Overlay;
import doors.overlay.Reticule;
import doors.overlay.WorldScreen;
import doors.graphics.ImageTexture;
import doors.graphics.ModelMesh;
import doors.graphics.PhysicalRenderTarget;
import doors.graphics.TextureChannel;
import doors.perspective.FlatPerspective;
import doors.perspective.WorldPerspective;
import doors.graphics.RenderTargetTexture;
import doors.job.IWorkUnit;
import doors.io.Window;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacterStream;
import doors.utility.Timer;
import doors.terrain.Terrain;
import doors.terrain.TerrainCache;

public class Game {

    public static Game GAME = new Game();

    private static int JOB_LIMIT_MS = 10;

    public Queue<IWorkUnit> workQueue;
    public long currentTick;
    public long previousTick;

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

        ImageTexture.OVERLAY_TEXTURE.setup();
        ImageTexture.ENTITY_TEXTURE.setup();

        RenderTargetTexture.CURRENT_WORLD_RENDER_TARGET_TEXTURE.setup();
        RenderTargetTexture.PORTAL_WORLD_RENDER_TARGET_TEXTURE.setup();

        TextureChannel.UI_TEXTURE_CHANNEL.useTexture(ImageTexture.OVERLAY_TEXTURE);
        TextureChannel.ENTITY_TEXTURE_CHANNEL.useTexture(ImageTexture.ENTITY_TEXTURE);
        TextureChannel.PORTAL_TEXTURE_CHANNEL.useTexture(RenderTargetTexture.PORTAL_WORLD_RENDER_TARGET_TEXTURE);
        TextureChannel.WORLD_TEXTURE_CHANNEL.useTexture(RenderTargetTexture.CURRENT_WORLD_RENDER_TARGET_TEXTURE);

        Overlay.OVERLAY.setup();

        ModelMesh.DOOR.setup();
        ModelMesh.PORTAL.setup();
        ModelMesh.UNIT.setup();
        ModelMesh.ARROW.setup();

        Camera.CAMERA.position.set(10, 2, 8);
        this.currentTerrain = TerrainCache.TERRAIN_CACHE.getTerrain("scratch/sphere_2");
    }
    
    private void refresh() {
        Window.WINDOW.refresh();
        TypedCharacterStream.TYPED_CHARACTER_STREAM.refresh();
        Mouse.MOUSE.refresh();
    }

    private void update() {
        Camera.CAMERA.update();
        this.currentTerrain.simulate();
    }

    private void renderPortalWorld() {
        RenderTargetTexture.PORTAL_WORLD_RENDER_TARGET_TEXTURE.use();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        if(this.currentTerrain.openPortal == null) {
            return;
        }

        var perspective = new WorldPerspective();
        perspective.alignToCamera();
        this.currentTerrain.openPortal.mutate(perspective);
        perspective.apply();

        this.currentTerrain.openPortal.targetPortal.door.terrain.render();
    }

    private void renderCurrentWorld() {
        RenderTargetTexture.CURRENT_WORLD_RENDER_TARGET_TEXTURE.use();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        var perspective = new WorldPerspective();
        perspective.alignToCamera();
        perspective.apply();

        this.currentTerrain.render();
    }

    private void render() {
        this.renderPortalWorld();
        this.renderCurrentWorld();

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.use();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        var perspective = new FlatPerspective();
        perspective.apply();

        WorldScreen.WORLD_SCREEN.paint();
        Reticule.RETICULE.paint();
        Menu.MENU.paint();

        Overlay.OVERLAY.render();
    }

    private void run() {
        this.timer.resetStartTime();

        while(!Window.WINDOW.shouldClose()) {
            this.timer.resetStartTime();
            while(timer.millisElapsed() <= JOB_LIMIT_MS && !this.workQueue.isEmpty()) {
                this.workQueue.remove().execute();
            }

            this.previousTick = this.currentTick;
            this.currentTick = System.currentTimeMillis();
            this.refresh();
            this.update();
            this.render();
        }
    }

    public static void main(String[] args) {
        GAME.setup();
        GAME.run();
    }

}
