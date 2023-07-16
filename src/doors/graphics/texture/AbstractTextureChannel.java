package doors.graphics.texture;

public abstract class AbstractTextureChannel {

    public int textureChannelID;
    public AbstractTexture registeredTexture = null;

    public abstract int getTextureChannelID();

    public int getTextureUnitID() {
        // We reserve GL_TEXTURE0 as a working area to setup new, incoming textures.
        return this.getTextureChannelID() + 1;
    }
}
