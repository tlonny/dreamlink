package doors.graphics.rendertarget;

import doors.core.graphics.rendertarget.AbstractVirtualRenderTarget;
import doors.core.graphics.texture.AbstractTextureChannel;
import doors.graphics.texture.channel.PortalRenderTextureChannel;

public class PortalVirtualRenderTarget extends AbstractVirtualRenderTarget {

    public static PortalVirtualRenderTarget PORTAL_VIRTUAL_RENDER_TARGET = new PortalVirtualRenderTarget();

    @Override
    public AbstractTextureChannel getTextureChannel() {
        return PortalRenderTextureChannel.PORTAL_RENDER_TEXTURE_CHANNEL;
    }
    
}
