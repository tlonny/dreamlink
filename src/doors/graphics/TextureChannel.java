package doors.graphics;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL42;

public class TextureChannel {

    public static Map<String, TextureChannel> TEXTURE_CHANNEL_LOOKUP = new HashMap<>();

    public static TextureChannel OVERLAY_TEXTURE_CHANNEL = 
        new TextureChannel("overlay", GL42.GL_TEXTURE1);

    public static TextureChannel ENTITY_TEXTURE_CHANNEL = 
        new TextureChannel("entity", GL42.GL_TEXTURE2);

    public static TextureChannel TERRAIN_TEXTURE_CHANNEL = 
        new TextureChannel("terrain", GL42.GL_TEXTURE3);

    public static TextureChannel PORTAL_TEXTURE_CHANNEL = 
        new TextureChannel("portal", GL42.GL_TEXTURE4);

    public int textureUnitID;
    public ITexture usedTexture;
    public String name;

    public int getSamplerUniformValue() {
        return this.textureUnitID - GL42.GL_TEXTURE0;
    }

    public String getSamplerUniformName() {
        return String.format("%s_sampler", this.name);
    }

    public TextureChannel(String name, int textureUnitID) {
        this.name = name;
        this.textureUnitID = textureUnitID;
        TEXTURE_CHANNEL_LOOKUP.put(name, this);
    }

    public void useTexture(ITexture texture) {
        if(this.usedTexture == texture) {
            return;
        }

        GL42.glActiveTexture(this.textureUnitID);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, texture.getTextureID());
        this.usedTexture = texture;
    }


}
