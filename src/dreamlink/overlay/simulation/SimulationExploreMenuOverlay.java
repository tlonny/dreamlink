package dreamlink.overlay.simulation;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.blend.BlendMode;
import dreamlink.graphics.glconfig.blend.BlendModeConfig;
import dreamlink.graphics.program.SpriteShaderProgram;
import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.window.WindowComponent;
import dreamlink.overlay.eventspan.EventSpanManager;
import dreamlink.state.SimulationState;
import dreamlink.state.scene.HomeScene;
import dreamlink.state.scene.SimulationScene;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.window.Window;

public class SimulationExploreMenuOverlay extends WrapperComponent {

    private static final String windowTitle = "Explore Menu";
    private static final int itemSpacing = 5;
    private static final String quitButtonText = "Quit";
    private static final String backButtonText = "Back";
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);

    public static final SimulationExploreMenuOverlay instance = new SimulationExploreMenuOverlay();

    private final IComponent component;
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final EventSpanManager eventSpanManager = new EventSpanManager();

    private SimulationExploreMenuOverlay() {
        this.component = new WindowComponent(
            MenuTextureSample.iconFolder,
            SimulationExploreMenuOverlay.windowTitle,
            new PaddingComponent(
                new VerticalSpanComponent(Alignment.center, SimulationExploreMenuOverlay.itemSpacing)
                    .addComponent(
                        new ButtonComponent(
                            new StaticButtonComponentProvider(this::back),
                            new BoxComponent(
                                new TextLineLabelComponent(
                                    SimulationExploreMenuOverlay.backButtonText,
                                    FontDecoration.normal,
                                    Vector4fMaths.black
                                ),
                                BoxDimension.fixed(SimulationExploreMenuOverlay.buttonDimensions.x()),
                                BoxDimension.fixed(SimulationExploreMenuOverlay.buttonDimensions.y())
                            )
                        )
                    )
                    .addComponent(
                        new ButtonComponent(
                            new StaticButtonComponentProvider(this::quit),
                            new BoxComponent(
                                new TextLineLabelComponent(
                                    SimulationExploreMenuOverlay.quitButtonText,
                                    FontDecoration.normal,
                                    Vector4fMaths.black
                                ),
                                BoxDimension.fixed(SimulationExploreMenuOverlay.buttonDimensions.x()),
                                BoxDimension.fixed(SimulationExploreMenuOverlay.buttonDimensions.y())
                            )
                        )
                    ),
                SimulationExploreMenuOverlay.itemSpacing
            )
        );
    }

    public void setup() {
        var resolution = Window.instance.getResolution(new Vector2i());
        this.component.computeInitialDimensions();
        this.component.computeDimensions(resolution);
        this.component.computePosition(Vector2iMaths.zero, SpriteHeight.menu);
        this.spriteBatch.setup();
    }

    public void destroy() {
        this.spriteBatch.destroy();
    }

    private void quit() {
        HomeScene.instance.bind();
    }

    private void back() {
        SimulationScene.instance.bind();
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    public void update() {
        if(SimulationState.instance.getAllowEdit()) {
            return;
        }

        this.eventSpanManager.clear();
        this.registerEventSpans(this.eventSpanManager);
        this.eventSpanManager.update();
    }

    private final ShaderProgramConfig renderShaderProgramConfig = new ShaderProgramConfig();
    private final BlendModeConfig renderBlendModeConfig = new BlendModeConfig();

    public void render() {
        if(SimulationState.instance.getAllowEdit()) {
            return;
        }

        this.spriteBatch.clear();
        this.writeToSpriteBatch(this.spriteBatch);
        this.eventSpanManager.getCursor().writeToSpriteBatch(this.spriteBatch);
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
