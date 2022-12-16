package periwinkle.entry;

import periwinkle.component.Entity;
import periwinkle.Camera;
import periwinkle.Input;
import periwinkle.Player;
import periwinkle.Reticule;
import periwinkle.environment.ParticleType;
import periwinkle.environment.Sky;
import periwinkle.environment.SkyType;
import periwinkle.graphics.Atlas;
import periwinkle.graphics.Shader;
import periwinkle.graphics.Display;
import periwinkle.ui.Command;
import periwinkle.ui.DebugConsole;
import periwinkle.ui.Glyph;
import periwinkle.ui.UI;
import periwinkle.terrain.BlockType;
import periwinkle.terrain.Generator;
import periwinkle.terrain.World;
import periwinkle.utility.Timer;
import org.joml.Matrix4f;
import org.joml.Vector2i;
import org.joml.Vector3i;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL15;

public class Game {

    private static final int STEP_MS = 50;

    public void run() {
        this.stepTimer.resetStartTime();
        while(!Display.DISPLAY.shouldClose()) {
            if(Input.INPUT.isKeyDown(GLFW.GLFW_KEY_ESCAPE))
                Display.DISPLAY.setShouldClose();

            while(this.stepTimer.millisElapsed() > STEP_MS) {
                this.update(this.stepCount);
                this.stepTimer.incrementStartTime(STEP_MS);
                this.stepCount += 1;
            }

            var stepFactor = (float)this.stepTimer.millisElapsed()/STEP_MS;
            this.render(stepFactor);
        }
    }

    private final Timer stepTimer = new Timer();
    private long stepCount = 0;

    private void update(long stepCount) {
        Entity.transition();
        DebugConsole.DEBUG_CONSOLE.update(stepCount);
        Player.PLAYER.update();
        World.WORLD.update();
        Sky.SKY.update();
        Input.INPUT.update();
    }

    private void renderWorld(float stepFactor) {
        GL15.glEnable(GL15.GL_DEPTH_TEST);
        Shader.SHADER.setGlobalColor(Sky.SKY.skyType.skyColor);
        Camera.CAMERA.reposition(stepFactor);
        Shader.SHADER.setViewRotationMatrix(Camera.CAMERA.getViewRotationMatrix());
        Shader.SHADER.setViewTranslationMatrix(Camera.CAMERA.getViewTranslationMatrix());
        Shader.SHADER.setProjectionMatrix(Camera.CAMERA.getProjectionMatrix());

        World.WORLD.render();
        Sky.SKY.render(stepFactor);
    }

    private void renderGUI() {
        GL15.glDisable(GL15.GL_DEPTH_TEST);
        var identity = new Matrix4f();
        Shader.SHADER.setViewRotationMatrix(identity);
        Shader.SHADER.setViewTranslationMatrix(identity);
        Shader.SHADER.setProjectionMatrix(identity);
        Reticule.RETICULE.render();
        DebugConsole.DEBUG_CONSOLE.render();
        UI.UI.drawText(new Vector2i(20,20), "periwinkle V0.1");
        UI.UI.render();
        Input.INPUT.render();
    }

    private void render(float stepFactor) {
        Shader.SHADER.useProgram();
        Shader.SHADER.setTextureSampler();
        this.renderWorld(stepFactor);

        this.renderGUI();
        Display.DISPLAY.refresh();
    }

    public static void main(String[] args) {
        Display.init();
        Atlas.init();
        Input.init();
        Command.init();
        BlockType.init();
        Glyph.init();
        World.init();
        Shader.init();
        Sky.init();
        SkyType.init();
        UI.init();
        ParticleType.init();
        Reticule.init();

        Player.PLAYER.position.set(10, 400, 10);
        for(var x = 0; x < World.WORLD_BLOCK_DIMENSIONS.x; x += 1) {
            for(var z = 0; z < World.WORLD_BLOCK_DIMENSIONS.z; z += 1) {
                World.WORLD.setBlock(new Vector3i(x, 0, z), BlockType.GRASS);
                World.WORLD.setBlock(new Vector3i(x, 1, z), BlockType.RED_FLOWERS);
            }
        }
        Generator.GENERATOR.generate();
        World.WORLD.enableLighting();
        World.WORLD.enableHeightMap();

        new Game().run();
    }

}