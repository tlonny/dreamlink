package dreamlink.graphics.glconfig;

import org.lwjgl.opengl.GL42;

import dreamlink.graphics.glconfig.state.GLState;
import dreamlink.graphics.glconfig.state.IGLStateStrategy;
import dreamlink.graphics.texture.ITexture;
import dreamlink.graphics.texture.TextureUnit;

public class TextureConfig extends GLConfig<ITexture>{

    private static class InternalTextureStateStrategy implements IGLStateStrategy<ITexture> {

        private final TextureUnit textureUnit;

        public InternalTextureStateStrategy(TextureUnit textureUnit) {
            this.textureUnit = textureUnit;
        }

        @Override
        public void setState(ITexture texture) {
            GL42.glActiveTexture(GL42.GL_TEXTURE0 + this.textureUnit.getIndex());
            GL42.glBindTexture(GL42.GL_TEXTURE_2D, texture == null ? 0 : texture.getTextureID());
        }
    }

    private static class InternalTextureState extends GLState<ITexture> {
        public InternalTextureState(TextureUnit textureUnit) {
            super(new InternalTextureStateStrategy(textureUnit));
        }
    }

    private static final InternalTextureState[] states = new InternalTextureState[TextureUnit.getSize()];

    public static void setState(TextureUnit textureUnit, ITexture texture) {
        TextureConfig.states[textureUnit.getIndex()].setState(texture);
    }


    public static void setup() {
        for(var ix = 0; ix < TextureUnit.getSize(); ix += 1) {
            var textureUnit = TextureUnit.get(ix);
            var state = new InternalTextureState(textureUnit);
            state.setState(null);
            TextureConfig.states[ix] = state;
        }
    }

    public TextureConfig(TextureUnit textureUnit) {
        super(TextureConfig.states[textureUnit.getIndex()]);
    }
    
}
