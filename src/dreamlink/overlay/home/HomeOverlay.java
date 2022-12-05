package dreamlink.overlay.home;

import org.joml.Vector2i;

import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.blend.BlendMode;
import dreamlink.graphics.glconfig.blend.BlendModeConfig;
import dreamlink.graphics.program.SpriteShaderProgram;
import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.choice.ChoiceComponent;
import dreamlink.overlay.eventspan.EventSpanManager;
import dreamlink.overlay.home.credits.HomeCreditsComponent;
import dreamlink.overlay.home.edit.HomeEditComponent;
import dreamlink.overlay.home.explore.HomeExploreComponent;
import dreamlink.overlay.home.settings.HomeSettingsComponent;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.window.Window;

public class HomeOverlay extends WrapperComponent {

    private class InternalHomeChildComponentProvider implements IHomeChildComponentProvider {

        @Override
        public void gotoRoot() {
            HomeOverlay.this.selectedChoiceKey = HomeOverlay.rootComponentKey;
        }

        @Override
        public void gotoExplore() {
            HomeOverlay.this.exploreComponent.clear();
            HomeOverlay.this.selectedChoiceKey = HomeOverlay.exploreComponentKey;
        }

        @Override
        public void gotoEdit() {
            HomeOverlay.this.selectedChoiceKey = HomeOverlay.editComponentKey;
        }

        @Override
        public void gotoSettings() {
            HomeOverlay.this.settingsComponent.clear();
            HomeOverlay.this.selectedChoiceKey = HomeOverlay.settingsComponentKey;
        }

        @Override
        public void gotoCredits() {
            HomeOverlay.this.selectedChoiceKey = HomeOverlay.creditsComponentKey;
        }



    }

    private static final int rootComponentKey = 0;
    private static final int exploreComponentKey = 1;
    private static final int editComponentKey = 2;
    private static final int settingsComponentKey = 3;
    private static final int creditsComponentKey = 4;

    public static final HomeOverlay instance = new HomeOverlay();

    private final IComponent component;
    private final HomeRootComponent rootComponent;
    private final HomeCreditsComponent aboutComponent;
    private final HomeExploreComponent exploreComponent;
    private final HomeEditComponent editComponent;
    private final HomeSettingsComponent settingsComponent;

    private final EventSpanManager eventSpanManager = new EventSpanManager();
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private int selectedChoiceKey;

    private HomeOverlay() {
        var provider = new InternalHomeChildComponentProvider();
        this.rootComponent = new HomeRootComponent(provider);
        this.exploreComponent = new HomeExploreComponent(provider);
        this.editComponent = new HomeEditComponent(provider);
        this.aboutComponent = new HomeCreditsComponent(provider);
        this.settingsComponent = new HomeSettingsComponent(provider);

        this.component = new ChoiceComponent<>(this::getChoiceKey)
            .addComponent(
                HomeOverlay.rootComponentKey, 
                this.rootComponent
            )
            .addComponent(
                HomeOverlay.exploreComponentKey, 
                this.exploreComponent
            )
            .addComponent(
                HomeOverlay.editComponentKey, 
                this.editComponent
            )
            .addComponent(
                HomeOverlay.creditsComponentKey, 
                this.aboutComponent
            )
            .addComponent(
                HomeOverlay.settingsComponentKey, 
                this.settingsComponent
            );
    }

    public void setup() {
        this.spriteBatch.setup();
        var resolution = Window.instance.getResolution(new Vector2i());
        this.component.computeInitialDimensions();
        this.component.computeDimensions(resolution);
        this.component.computePosition(Vector2iMaths.zero, SpriteHeight.menu);
    }

    public void update() {
        this.eventSpanManager.clear();
        this.registerEventSpans(this.eventSpanManager);
        this.eventSpanManager.update();
    }

    public void destroy() {
        this.spriteBatch.destroy();
    }

    public void reset() {
        this.selectedChoiceKey = HomeOverlay.rootComponentKey;
    }

    private int getChoiceKey() {
        return this.selectedChoiceKey;
    }

    private final Vector2i renderResolution = new Vector2i();
    private final ShaderProgramConfig renderShaderProgramConfig = new ShaderProgramConfig();
    private final BlendModeConfig renderBlendModeConfig = new BlendModeConfig();

    public void render() {
        this.spriteBatch.clear();
        MenuTextureSample.background.writeToSpriteBatch(
            this.spriteBatch,
            Vector2iMaths.zero,
            Window.instance.getResolution(this.renderResolution),
            SpriteHeight.background
        );

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

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
