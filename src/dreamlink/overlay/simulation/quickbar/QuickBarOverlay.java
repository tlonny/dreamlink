package dreamlink.overlay.simulation.quickbar;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;

import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.TextureConfig;
import dreamlink.graphics.glconfig.blend.BlendMode;
import dreamlink.graphics.glconfig.blend.BlendModeConfig;
import dreamlink.graphics.program.SpriteShaderProgram;
import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.texture.TextureUnit;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.state.SimulationState;
import dreamlink.state.scene.SceneManager;
import dreamlink.state.scene.SimulationScene;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.window.Window;
import dreamlink.world.World;

public class QuickBarOverlay {

    private static final int numQuickBarSlots = 10;
    private static final int spacing = 5;

    public final static QuickBarOverlay instance = new QuickBarOverlay();

    private final QuickBarRootComponent component = new QuickBarRootComponent();
    private final List<QuickBarSlotComponent> slots = new ArrayList<>();
    private final SpriteBatch spriteBatch = new SpriteBatch();

    private QuickBarOverlay() {
        var layoutComponent = new HorizontalSpanComponent(
            Alignment.start, 
            QuickBarOverlay.spacing
        );

        for(var ix = 0; ix < QuickBarOverlay.numQuickBarSlots; ix += 1) {
            var slot = new QuickBarSlotComponent(ix);
            this.slots.add(slot);
            layoutComponent.addComponent(slot);
        }
    }

    public void setup() {
        this.spriteBatch.setup();
        var resolution = Window.instance.getResolution(new Vector2i());
        this.component.computeInitialDimensions();
        this.component.computeDimensions(resolution);
        this.component.computePosition(Vector2iMaths.zero, SpriteHeight.menu);
    }

    public void destroy() {
        this.spriteBatch.destroy();
    }

    public void update() {
        if(SceneManager.instance.getScene() != SimulationScene.instance) {
            return;
        }

        var room = World.instance.getRoom();
        for(var ix = 0; ix < QuickBarSlot.getSize(); ix += 1) {
            var slot = QuickBarSlot.get(ix);
            if(Window.instance.isButtonPressed(slot.button)) {
                room.setSelectedPaletteStampSlotIndex(ix);
            }
        }
    }

    private final ShaderProgramConfig renderShaderProgramConfig = new ShaderProgramConfig();
    private final BlendModeConfig renderBlendModeConfig = new BlendModeConfig();
    private final TextureConfig renderTextureConfig = new TextureConfig(TextureUnit.room);

    public void render() {
        if(!SimulationState.instance.getAllowEdit()) {
            return;
        }

        this.spriteBatch.clear();
        this.component.writeToSpriteBatch(this.spriteBatch);
        this.spriteBatch.buffer();

        try(
            var shaderProgramConfig = this.renderShaderProgramConfig.checkpoint();
            var blendModeConfig = this.renderBlendModeConfig.checkpoint();
            var textureConfig = this.renderTextureConfig.checkpoint();
        ) {
            blendModeConfig.setState(BlendMode.alphaBlend);
            shaderProgramConfig.setState(SpriteShaderProgram.instance);
            textureConfig.setState(World.instance.getRoom().getTexture());
            this.spriteBatch.render();
        }
    }
}
