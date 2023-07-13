package doors.graphics.texture;

public class TextureChannel {

    public static TextureChannel TEXTURE_CHANNEL_FONT = new TextureChannel(0);
    public static TextureChannel TEXTURE_CHANNEL_MENU = new TextureChannel(1);
    public static TextureChannel TEXTURE_CHANNEL_ENTITY = new TextureChannel(2);
    public static TextureChannel TEXTURE_CHANNEL_BLOCK = new TextureChannel(3);
    public static TextureChannel TEXTURE_CHANNEL_CURRENT_LEVEL = new TextureChannel(4);
    public static TextureChannel TEXTURE_CHANNEL_PORTAL_LEVEL = new TextureChannel(5);

    public static TextureChannel[] TEXTURE_CHANNELS = new TextureChannel[] {
        TEXTURE_CHANNEL_FONT,
        TEXTURE_CHANNEL_MENU,
        TEXTURE_CHANNEL_ENTITY,
        TEXTURE_CHANNEL_BLOCK,
        TEXTURE_CHANNEL_CURRENT_LEVEL,
        TEXTURE_CHANNEL_PORTAL_LEVEL
    };

    public int textureChannelID;
    public Texture registeredTexture = null;

    public TextureChannel(int textureChannelID) {
        this.textureChannelID = textureChannelID;
    }

    public int getTextureUnitID() {
        // We reserve GL_TEXTURE0 as a working area to setup new, incoming textures.
        return this.textureChannelID + 1;
    }
}
