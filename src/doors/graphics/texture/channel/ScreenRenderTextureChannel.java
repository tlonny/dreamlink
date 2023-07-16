package doors.graphics.texture.channel;

import doors.graphics.texture.AbstractTextureChannel;

public class ScreenRenderTextureChannel extends AbstractTextureChannel {

    public static ScreenRenderTextureChannel SCREEN_RENDER_TEXTURE_CHANNEL = new ScreenRenderTextureChannel();

    @Override
    public int getTextureChannelID() {
        return 5;
    }
    
}
