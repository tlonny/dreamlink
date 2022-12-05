package dreamlink.overlay;

import org.joml.Vector2i;

import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.blend.BlendMode;
import dreamlink.graphics.glconfig.blend.BlendModeConfig;
import dreamlink.graphics.program.SpriteShaderProgram;
import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.sprite.template.SolidSpriteTemplate;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.background.StaticBackgroundComponentProvider;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;
import dreamlink.overlay.eventspan.EventSpanManager;
import dreamlink.state.scene.HomeScene;
import dreamlink.state.scene.SimulationScene;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.window.Window;
import dreamlink.window.button.Button;
import dreamlink.world.World;

public class RoomLoadOverlay extends WrapperComponent {

    private class InternalErrorTextComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public int getCharacterSize() {
            var room = World.instance.getRoom();
            return RoomLoadOverlay.this.showFailedMessage
                ? room.getLoadError().length()
                : 0;
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public void setCharacterState(int characterIndex, CharacterData characterState) {
            var room = World.instance.getRoom();
            characterState.set(
                room.getLoadError().charAt(characterIndex),
                FontDecoration.normal,
                Vector4fMaths.red
            );
            characterState.color.w = RoomLoadOverlay.this.alpha;
        }

    }

    private class InternalStatusTextComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public int getCharacterSize() {
            return RoomLoadOverlay.this.showFailedMessage
                ? RoomLoadOverlay.loadFailedText.length()
                : RoomLoadOverlay.loadingText.length();
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public void setCharacterState(int characterIndex, CharacterData characterState) {
            if(RoomLoadOverlay.this.showFailedMessage) {
                characterState.set(
                    RoomLoadOverlay.loadFailedText.charAt(characterIndex),
                    FontDecoration.normal,
                    Vector4fMaths.red
                );
                characterState.color.w = RoomLoadOverlay.this.alpha;
                return;
            }

            characterState.set(
                RoomLoadOverlay.loadingText.charAt(characterIndex),
                FontDecoration.normal,
                Vector4fMaths.white
            );
            var modTime = System.currentTimeMillis() % RoomLoadOverlay.alphaFlashPeriodMs;
            var period = (float)modTime / RoomLoadOverlay.alphaFlashPeriodMs * 2f * Math.PI;
            var alphaMod = (float)Math.sin(period) * 0.5f + 0.5f;
            alphaMod *= (1f - RoomLoadOverlay.minFlashAlpha) ;
            alphaMod += RoomLoadOverlay.minFlashAlpha;
            characterState.color.w = RoomLoadOverlay.this.alpha * alphaMod;
        }

    }

    public static final RoomLoadOverlay instance = new RoomLoadOverlay();

    private static final float deltaAlpha = 0.01f;
    private static final float minFlashAlpha = 0.4f;
    private static final long alphaFlashPeriodMs = 500;
    private static final String loadingText = "Loading...";
    private static final String loadFailedText = "Load Failed (Press 'Esc' to go back)";
    private float alpha;
    private boolean showFailedMessage;

    private final IComponent component;
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final EventSpanManager eventSpanManager = new EventSpanManager();

    private RoomLoadOverlay() {
        this.component = new BackgroundComponent(
            new StaticBackgroundComponentProvider(SolidSpriteTemplate.black),
            new BoxComponent(
                new VerticalSpanComponent(Alignment.center, 0)
                    .addComponent(
                        new TextLineViewComponent(
                            new InternalStatusTextComponentProvider(),
                            Alignment.center
                        )
                    )
                    .addComponent(
                        new TextLineViewComponent(
                            new InternalErrorTextComponentProvider(),
                            Alignment.center
                        )
                    ),
                BoxDimension.grow(),
                BoxDimension.grow()
            )
        );
    }

    public void setup() {
        this.spriteBatch.setup();
        var resolution = Window.instance.getResolution(new Vector2i());
        this.computeInitialDimensions();
        this.computeDimensions(resolution);
        this.computePosition(Vector2iMaths.zero, SpriteHeight.menu);
    }

    public void destroy() {
        this.spriteBatch.destroy();
    }

    public void reset() {
        this.alpha = 1f;
        this.showFailedMessage = false;
    }

    public void update() {
        var room = World.instance.getRoom();
        if(room.isFinalized()) {
            SimulationScene.instance.bind();
        } else if(room.getLoadError() != null) {
            this.alpha = this.showFailedMessage
                ? Math.min(1f, this.alpha + RoomLoadOverlay.deltaAlpha)
                : Math.max(0f, this.alpha - RoomLoadOverlay.deltaAlpha);
            if(this.alpha == 0f) {
                this.showFailedMessage = true;
            }

            if(Window.instance.isButtonPressed(Button.keyEscape)) {
                HomeScene.instance.bind();
            }
        }

        this.eventSpanManager.clear();
        super.registerEventSpans(this.eventSpanManager);
        this.eventSpanManager.update();
    }

    private final ShaderProgramConfig renderShaderProgramConfig = new ShaderProgramConfig();
    private final BlendModeConfig renderBlendModeConfig = new BlendModeConfig();

    public void render() {
        this.spriteBatch.clear();
        this.writeToSpriteBatch(this.spriteBatch);
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
