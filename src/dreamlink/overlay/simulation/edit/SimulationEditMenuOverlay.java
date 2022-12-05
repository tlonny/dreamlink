package dreamlink.overlay.simulation.edit;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.TextureConfig;
import dreamlink.graphics.glconfig.blend.BlendMode;
import dreamlink.graphics.glconfig.blend.BlendModeConfig;
import dreamlink.graphics.program.SpriteShaderProgram;
import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.TextureUnit;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.icon.IconComponent;
import dreamlink.overlay.component.icon.StaticIconComponentProvider;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.tab.TabViewComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.window.WindowComponent;
import dreamlink.overlay.eventspan.EventSpanManager;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.overlay.simulation.edit.debug.SettingsComponent;
import dreamlink.overlay.simulation.edit.door.DoorComponent;
import dreamlink.overlay.simulation.quickbar.QuickBarRootComponent;
import dreamlink.state.SimulationState;
import dreamlink.state.scene.HomeScene;
import dreamlink.state.scene.SaveScene;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.window.Window;
import dreamlink.world.World;

public class SimulationEditMenuOverlay implements IComponent {

    private static final int itemSpacing = 5;
    private static final int iconSpacing = 5;
    private static final String saveText = "Save";
    private static final String quitText = "Quit";
    private static final String windowTitle = "Edit Menu";
    private static final String stampsTabLabel = "Stamps";
    private static final String doorsTabLabel = "Doors";
    private static final String settingsTabLabel = "Settings";
    private static final Vector2ic iconDimensions = new Vector2i(16, 16);
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);
    private static final Vector2ic windowSize = new Vector2i(480, 440);

    public static final SimulationEditMenuOverlay instance = new SimulationEditMenuOverlay();

    private final IComponent component;
    private final QuickBarRootComponent quickBarRootComponent = new QuickBarRootComponent();
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final EventSpanManager eventSpanManager = new EventSpanManager();

    private final StampListComponent stampListComponent = new StampListComponent();
    private final DoorComponent doorComponent = new DoorComponent();
    private final SettingsComponent settingsComponent = new SettingsComponent();

    private SimulationEditMenuOverlay() {
        this.component = new WindowComponent(
            MenuTextureSample.iconFolder, 
            SimulationEditMenuOverlay.windowTitle,
            new BoxComponent(
                new PaddingComponent(
                    new VerticalSpanComponent(
                        Alignment.center, 
                        SimulationEditMenuOverlay.itemSpacing
                    )
                        .addComponent(
                            new TabViewComponent()
                                .addTab(
                                    new HorizontalSpanComponent(
                                        Alignment.center, 
                                        SimulationEditMenuOverlay.iconSpacing
                                    )
                                        .addComponent(
                                            new IconComponent(
                                                new StaticIconComponentProvider(MenuTextureSample.iconResourcePack),
                                                SimulationEditMenuOverlay.iconDimensions
                                            )
                                        )
                                        .addComponent(
                                            new TextLineLabelComponent(
                                                SimulationEditMenuOverlay.stampsTabLabel,
                                                FontDecoration.normal,
                                                Vector4fMaths.black
                                            )
                                        ),
                                    this.stampListComponent
                                )
                                .addTab(
                                    new HorizontalSpanComponent(
                                        Alignment.center, 
                                        SimulationEditMenuOverlay.iconSpacing
                                    )
                                        .addComponent(
                                            new IconComponent(
                                                new StaticIconComponentProvider(MenuTextureSample.iconGlobe),
                                                SimulationEditMenuOverlay.iconDimensions
                                            )
                                        )
                                        .addComponent(
                                            new TextLineLabelComponent(
                                                SimulationEditMenuOverlay.doorsTabLabel,
                                                FontDecoration.normal,
                                                Vector4fMaths.black
                                            )
                                        ),
                                    this.doorComponent
                                )
                                .addTab(
                                    new HorizontalSpanComponent(
                                        Alignment.center, 
                                        SimulationEditMenuOverlay.iconSpacing
                                    )
                                        .addComponent(
                                            new IconComponent(
                                                new StaticIconComponentProvider(MenuTextureSample.iconSettings),
                                                SimulationEditMenuOverlay.iconDimensions
                                            )
                                        )
                                        .addComponent(
                                            new TextLineLabelComponent(
                                                SimulationEditMenuOverlay.settingsTabLabel,
                                                FontDecoration.normal,
                                                Vector4fMaths.black
                                            )
                                        ),
                                    this.settingsComponent
                                )
                        ).addComponent(
                            new HorizontalSpanComponent(
                                Alignment.center,
                                SimulationEditMenuOverlay.itemSpacing
                            )
                                .addComponent(
                                    new ButtonComponent(
                                        new StaticButtonComponentProvider(this::save),
                                        new BoxComponent(
                                            new TextLineLabelComponent(
                                                SimulationEditMenuOverlay.saveText,
                                                FontDecoration.normal,
                                                Vector4fMaths.black
                                            ),
                                            BoxDimension.fixed(SimulationEditMenuOverlay.buttonDimensions.x()),
                                            BoxDimension.fixed(SimulationEditMenuOverlay.buttonDimensions.y())
                                        )
                                    )
                                )
                                .addComponent(
                                    new ButtonComponent(
                                        new StaticButtonComponentProvider(this::quit),
                                        new BoxComponent(
                                            new TextLineLabelComponent(
                                                SimulationEditMenuOverlay.quitText,
                                                FontDecoration.normal,
                                                Vector4fMaths.black
                                            ),
                                            BoxDimension.fixed(SimulationEditMenuOverlay.buttonDimensions.x()),
                                            BoxDimension.fixed(SimulationEditMenuOverlay.buttonDimensions.y())
                                        )
                                    )
                                )
                        ),
                    SimulationEditMenuOverlay.itemSpacing
                ),
                BoxDimension.fixed(SimulationEditMenuOverlay.windowSize.x()),
                BoxDimension.fixed(SimulationEditMenuOverlay.windowSize.y())
            )
        );
    }

    public void setup() {
        var resolution = Window.instance.getResolution(new Vector2i());
        this.computeInitialDimensions();
        this.computeDimensions(resolution);
        this.computePosition(Vector2iMaths.zero, SpriteHeight.menu);
        this.spriteBatch.setup();
    }

    public void destroy() {
        this.spriteBatch.destroy();
    }

    private void quit() {
        HomeScene.instance.bind();
    }

    private void save() {
        SaveScene.instance.bind();
    }

    public void clear() {
        this.doorComponent.clear();
    }

    public void update() {
        if(!SimulationState.instance.getAllowEdit()) {
            return;
        }

        this.eventSpanManager.clear();
        this.registerEventSpans(this.eventSpanManager);
        this.eventSpanManager.update();
    }

    private final ShaderProgramConfig renderShaderProgramConfig = new ShaderProgramConfig();
    private final BlendModeConfig renderBlendModeConfig = new BlendModeConfig();
    private final TextureConfig renderTextureConfig = new TextureConfig(TextureUnit.room);

    public void render() {
        if(!SimulationState.instance.getAllowEdit()) {
            return;
        }

        this.spriteBatch.clear();
        this.writeToSpriteBatch(this.spriteBatch);
        this.eventSpanManager.getCursor().writeToSpriteBatch(this.spriteBatch);
        this.spriteBatch.buffer();

        try(
            var shaderProgramConfig = this.renderShaderProgramConfig.checkpoint();
            var blendModeConfig = this.renderBlendModeConfig.checkpoint();
            var textureConfig = this.renderTextureConfig.checkpoint();
        ) {
            textureConfig.setState(World.instance.getRoom().getTexture());
            blendModeConfig.setState(BlendMode.alphaBlend);
            shaderProgramConfig.setState(SpriteShaderProgram.instance);
            this.spriteBatch.render();
        }
    }

    @Override
    public Vector2i getInitialDimensions(Vector2i target) {
        return this.component.getInitialDimensions(target);
    }

    @Override
    public Vector2i getDimensions(Vector2i target) {
        return this.component.getDimensions(target);
    }

    @Override
    public Vector2i getPosition(Vector2i target) {
        return this.component.getPosition(target);
    }

    @Override
    public SpriteHeight getSpriteHeight() {
        return this.component.getSpriteHeight();
    }

    @Override
    public void computeInitialDimensions() {
        this.component.computeInitialDimensions();
        this.quickBarRootComponent.computeInitialDimensions();
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        this.component.computeDimensions(availableSpace);
        this.quickBarRootComponent.computeDimensions(availableSpace);
    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight spriteHeight) {
        this.component.computePosition(position, spriteHeight);
        this.quickBarRootComponent.computePosition(position, spriteHeight);
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        this.component.registerEventSpans(registry);
        this.quickBarRootComponent.registerEventSpans(registry);
    }

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        this.component.writeToSpriteBatch(spriteBatch);
        this.quickBarRootComponent.writeToSpriteBatch(spriteBatch);
    }

}
