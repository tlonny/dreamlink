package doors.graphics.texture.channel;

import doors.graphics.texture.AbstractTextureChannel;

public class FontTextureChannel extends AbstractTextureChannel {

    public static FontTextureChannel FONT_TEXTURE_CHANNEL = new FontTextureChannel();

    @Override
    public int getTextureChannelID() {
        return 0;
    }
    
}
