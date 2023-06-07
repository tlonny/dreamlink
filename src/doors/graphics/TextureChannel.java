package doors.graphics;

import org.lwjgl.opengl.GL42;

public class TextureChannel {

    public static TextureChannel WORLD_RENDER_CHANNEL = 
        new TextureChannel(GL42.GL_TEXTURE1);

    public static TextureChannel OVERLAY_TEXTURE_CHANNEL = 
        new TextureChannel(GL42.GL_TEXTURE2);

    public static TextureChannel TERRAIN_TEXTURE_CHANNEL = 
        new TextureChannel(GL42.GL_TEXTURE3);

    public int textureUnitID;
    public TextureData texture;

    public int getNormalizedTextureUnitID() {
        return this.textureUnitID - GL42.GL_TEXTURE0;
    }

    public TextureChannel(int textureUnitID) {
        this.textureUnitID = textureUnitID;
    }

    public void setTexture(TextureData texture) {
        System.out.println("setting texture " + texture.textureID + " to texture channel " + this.textureUnitID);
        GL42.glActiveTexture(this.textureUnitID);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, texture.textureID);
    }


}
