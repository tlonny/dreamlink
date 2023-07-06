package doors.core.graphics.texture;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL42;

public class TextureChannel {

    public static Map<String, TextureChannel> TEXTURE_CHANNEL_LOOKUP = new HashMap<>();

    private int textureUnitID;
    private ITexture usedTexture;
    private String name;

    public TextureChannel(String name, int textureUnitID) {
        super();
        this.name = name;
        this.textureUnitID = textureUnitID;
        TEXTURE_CHANNEL_LOOKUP.put(name, this);
    }

    public int getSamplerUniformValue() {
        return this.textureUnitID - GL42.GL_TEXTURE0;
    }

    public String getSamplerUniformName() {
        return String.format("%s_sampler", this.name);
    }

    public void useTexture(ITexture texture) {
        if (this.usedTexture == texture) {
            return;
        }
        GL42.glActiveTexture(this.textureUnitID);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, texture.getTextureID());
        this.usedTexture = texture;
    }
}
