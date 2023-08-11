package doors.graphics.texture.channel;

public class FontTextureChannel extends AbstractTextureChannel {

    public static FontTextureChannel FONT_TEXTURE_CHANNEL = new FontTextureChannel();

    @Override
    public int getTextureUnitID() {
        return 1;
    }
    
}
