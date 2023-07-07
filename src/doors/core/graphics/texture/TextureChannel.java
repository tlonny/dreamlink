package doors.core.graphics.texture;

public class TextureChannel {

    public static TextureChannel TEXTURE_CHANNEL_0 = new TextureChannel(0);
    public static TextureChannel TEXTURE_CHANNEL_1 = new TextureChannel(1);
    public static TextureChannel TEXTURE_CHANNEL_2 = new TextureChannel(2);
    public static TextureChannel TEXTURE_CHANNEL_3 = new TextureChannel(3);
    public static TextureChannel TEXTURE_CHANNEL_4 = new TextureChannel(4);
    public static TextureChannel TEXTURE_CHANNEL_5 = new TextureChannel(5);
    public static TextureChannel TEXTURE_CHANNEL_6 = new TextureChannel(6);
    public static TextureChannel TEXTURE_CHANNEL_7 = new TextureChannel(7);
    public static TextureChannel TEXTURE_CHANNEL_8 = new TextureChannel(8);
    public static TextureChannel TEXTURE_CHANNEL_9 = new TextureChannel(9);

    public static TextureChannel[] TEXTURE_CHANNELS = new TextureChannel[] {
        TEXTURE_CHANNEL_0,
        TEXTURE_CHANNEL_1,
        TEXTURE_CHANNEL_2,
        TEXTURE_CHANNEL_3,
        TEXTURE_CHANNEL_4,
        TEXTURE_CHANNEL_5,
        TEXTURE_CHANNEL_6,
        TEXTURE_CHANNEL_7,
        TEXTURE_CHANNEL_8,
        TEXTURE_CHANNEL_9
    };

    public int textureChannelID;
    public Texture registeredTexture;

    public TextureChannel(int textureChannelID) {
        this.textureChannelID = textureChannelID;
        this.registeredTexture = null;
    }

    public int getTextureUnitID() {
        // We reserve GL_TEXTURE0 as a working area to setup new, incoming textures.
        return this.textureChannelID + 1;
    }
}
