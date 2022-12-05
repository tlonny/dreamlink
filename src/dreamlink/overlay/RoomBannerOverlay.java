package dreamlink.overlay;

import org.joml.Vector2i;

import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.blend.BlendMode;
import dreamlink.graphics.glconfig.blend.BlendModeConfig;
import dreamlink.graphics.program.SpriteShaderProgram;
import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.window.Window;
import dreamlink.world.World;

public class RoomBannerOverlay extends WrapperComponent {

    private class InternalTextLineComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public int getCharacterSize() {
            return RoomBannerOverlay.this.roomName.length();
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public void setCharacterState(int characterIndex, CharacterData characterState) {
            characterState.set(
                RoomBannerOverlay.this.roomName.charAt(characterIndex),
                FontDecoration.normal,
                Vector4fMaths.white,
                Vector4fMaths.black
            );
            characterState.color.w = RoomBannerOverlay.this.alpha;
            characterState.highlightColor.w = RoomBannerOverlay.this.alpha;
        }
    }

    private static final float alphaDelta = 0.05f;

    public static final RoomBannerOverlay instance = new RoomBannerOverlay();

    private final SpriteBatch spriteBatch = new SpriteBatch();
    private float alpha;
    private String roomName = "";
    private final IComponent component;

    private RoomBannerOverlay() {
        this.component = new BoxComponent(
            new TextLineViewComponent(
                new InternalTextLineComponentProvider(),
                Alignment.center
            ),
            BoxDimension.grow(),
            BoxDimension.grow(Alignment.start)
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
        this.roomName = "";
        this.alpha = 0f;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
    public void update() {
        var currentRoom = World.instance.getRoom();
        if(!currentRoom.name.equals(this.roomName)) {
            this.alpha = Math.max(this.alpha - alphaDelta, 0f);

            if(this.alpha == 0f) {
                this.roomName = currentRoom.name;
            }
        } else {
            this.alpha = Math.min(this.alpha + alphaDelta, 1f);
        }
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
    
}
