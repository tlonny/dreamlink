package dreamlink.overlay;

import org.joml.Vector2i;

import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.blend.BlendMode;
import dreamlink.graphics.glconfig.blend.BlendModeConfig;
import dreamlink.graphics.program.SpriteShaderProgram;
import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.player.Player;
import dreamlink.window.Window;

public class ReticuleOverlay {

    private final static int reticuleScaler = 3;

    public static final ReticuleOverlay instance = new ReticuleOverlay();

    private final Vector2i writeToSpriteBatchResolution = new Vector2i();
    private final Vector2i writeToSpriteBatchPosition = new Vector2i();
    private final SpriteBatch spriteBatch = new SpriteBatch();

    public void setup() {
        this.spriteBatch.setup();
    }

    public void destroy() {
        this.spriteBatch.clear();
    }

    private final BlendModeConfig renderBlendModeConfig = new BlendModeConfig();
    private final ShaderProgramConfig renderShaderProgramConfig = new ShaderProgramConfig();
    private final Vector2i renderDimensions = new Vector2i();

    public void render() {
        this.spriteBatch.clear();

        var reticuleSprite = Player.instance.canInteract()
            ? MenuTextureSample.reticuleInteraction
            : MenuTextureSample.reticule;

        var dimensions = this.renderDimensions
            .set(reticuleSprite.dimensions)
            .mul(ReticuleOverlay.reticuleScaler);

        var resolution = Window.instance.getResolution(this.writeToSpriteBatchResolution);
        var position = this.writeToSpriteBatchPosition.set(
            (resolution.x - dimensions.x()) / 2,
            (resolution.y - dimensions.y()) / 2
        );

        reticuleSprite.writeToSpriteBatch(
            this.spriteBatch,
            position,
            dimensions,
            SpriteHeight.hud
        );

        this.spriteBatch.buffer();

        try(
            var blendModeConfig = this.renderBlendModeConfig.checkpoint();
            var shaderProgramConfig = this.renderShaderProgramConfig.checkpoint()
        ) {
            blendModeConfig.setState(BlendMode.invert);
            shaderProgramConfig.setState(SpriteShaderProgram.instance);
            this.spriteBatch.render();
        }

    }
    
}
