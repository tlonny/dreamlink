package doors.graphics.texture;

import doors.graphics.texture.channel.PortalRenderTextureChannel;

public class PortalRenderTexture extends FrameBufferTexture {

    public static PortalRenderTexture PORTAL_RENDER_TEXTURE = new PortalRenderTexture();

    public PortalRenderTexture() {
        super(PortalRenderTextureChannel.PORTAL_RENDER_TEXTURE_CHANNEL);
    }
    
}
