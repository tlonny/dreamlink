package dreamlink.overlay;

import org.joml.Vector2i;
import org.joml.Vector4f;

import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.blend.BlendMode;
import dreamlink.graphics.glconfig.blend.BlendModeConfig;
import dreamlink.graphics.program.SpriteShaderProgram;
import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.sprite.template.SolidSpriteTemplate;
import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.state.scene.HomeScene;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.window.Window;
import dreamlink.window.button.Button;

public class LogoOverlay {

    private static final float rampInStartFactor = 0.2f;
    private static final float rampInEndFactor = 0.3f;
    private static final float rampOutStartFactor = 0.8f;
    private static final float rampOutEndFactor = 0.9f;
    private static final long holdTime = 2_500;
    
    public static final LogoOverlay instance = new LogoOverlay();

    private long startTime;
    private float alpha;
    private final SpriteBatch spriteBatch = new SpriteBatch();

    public void setup() {
        this.spriteBatch.setup();
    }
    
    public void update() {
        var timeNow = System.currentTimeMillis();
        var timeElapsed = timeNow - this.startTime;
        var progress = Math.min(1, timeElapsed / (float) LogoOverlay.holdTime);

        if(progress < rampInStartFactor) {
            this.alpha = 0f;
        } else if(progress < rampInEndFactor) {
            this.alpha = (progress - rampInStartFactor) / (rampInEndFactor - rampInStartFactor);
        } else if(progress < rampOutStartFactor) {
            this.alpha = 1f;
        } else if(progress < rampOutEndFactor) {
            this.alpha = 1f - (progress - rampOutStartFactor) / (rampOutEndFactor - rampOutStartFactor);
        } else {
            this.alpha = 0f;
        }

        var elapsedTime = System.currentTimeMillis() - this.startTime;
        if(elapsedTime > LogoOverlay.holdTime || Window.instance.isButtonPressed(Button.keyEscape)) {
            HomeScene.instance.bind();
        }
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
        this.alpha = 0f;
    }

    public void destroy() {
        this.spriteBatch.clear();
    }

    private final Vector2i writeToSpriteBatchPosition = new Vector2i();
    private final Vector2i writeToSpriteBatchResolution = new Vector2i();
    private final Vector2i writeToSpriteBatchDimensions = new Vector2i();
    private final Vector4f writeToSpriteBatchColor = new Vector4f();

    private final ShaderProgramConfig renderShaderProgramConfig = new ShaderProgramConfig();
    private final BlendModeConfig renderBlendModeConfig = new BlendModeConfig();

    public void render() {
        this.spriteBatch.clear();

        SolidSpriteTemplate.white.writeToSpriteBatch(
            this.spriteBatch,
            Vector2iMaths.zero,
            Window.instance.getResolution(this.writeToSpriteBatchDimensions),
            SpriteHeight.background
        );

        var resolution = Window.instance.getResolution(this.writeToSpriteBatchResolution);
        this.spriteBatch.writeTextureSample(
            Vector2iMaths.center(
                this.writeToSpriteBatchPosition,
                resolution,
                EntityTextureSample.logo.dimensions
            ),
            EntityTextureSample.logo.dimensions,
            SpriteHeight.background,
            EntityTextureSample.logo,
            Vector4fMaths.fromAlpha(this.writeToSpriteBatchColor, this.alpha)
        );

        this.spriteBatch.buffer();

        try(
            var shaderProgramConfig = this.renderShaderProgramConfig.checkpoint();
            var blendModeConfig = this.renderBlendModeConfig.checkpoint();
        ) {
            blendModeConfig.setState(BlendMode.alphaBlend);
            shaderProgramConfig.setState(SpriteShaderProgram.instance);
            this.spriteBatch.render();
        }
    }
    
}
