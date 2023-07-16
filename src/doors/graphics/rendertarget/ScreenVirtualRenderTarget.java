package doors.graphics.rendertarget;

import doors.graphics.texture.AbstractTextureChannel;
import doors.graphics.texture.channel.ScreenRenderTextureChannel;

public class ScreenVirtualRenderTarget extends AbstractVirtualRenderTarget {

    public static ScreenVirtualRenderTarget SCREEN_VIRTUAL_RENDER_TARGET = new ScreenVirtualRenderTarget();

    @Override
    public AbstractTextureChannel getTextureChannel() {
        return ScreenRenderTextureChannel.SCREEN_RENDER_TEXTURE_CHANNEL;
    }
    
}
