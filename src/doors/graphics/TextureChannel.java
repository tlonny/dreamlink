package doors.graphics;

import org.lwjgl.opengl.GL42;

public class TextureChannel {

    public static TextureChannel WORLD_RENDER_CHANNEL = 
        new TextureChannel("world_render", GL42.GL_TEXTURE1);

    public static TextureChannel OVERLAY_TEXTURE_CHANNEL = 
        new TextureChannel("overlay", GL42.GL_TEXTURE2);

    public static TextureChannel TERRAIN_TEXTURE_CHANNEL = 
        new TextureChannel("terrain", GL42.GL_TEXTURE3);

    public static TextureChannel ENTITY_TEXTURE_CHANNEL = 
        new TextureChannel("entity", GL42.GL_TEXTURE4);

    public static TextureChannel[] TEXTURE_CHANNELS = new TextureChannel[] {
        WORLD_RENDER_CHANNEL,
        OVERLAY_TEXTURE_CHANNEL,
        TERRAIN_TEXTURE_CHANNEL,
        ENTITY_TEXTURE_CHANNEL
    };

    public int textureUnitID;
    public int boundTextureID;
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
    }

    public void bindTextureID(int textureID) {
        if(this.boundTextureID != textureID) {
            GL42.glActiveTexture(this.textureUnitID);
            GL42.glBindTexture(GL42.GL_TEXTURE_2D, textureID);
            this.boundTextureID = textureID;
        }
    }


}
