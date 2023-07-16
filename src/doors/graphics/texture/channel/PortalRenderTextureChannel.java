package doors.graphics.texture.channel;

import doors.graphics.texture.AbstractTextureChannel;

public class PortalRenderTextureChannel extends AbstractTextureChannel {

    public static PortalRenderTextureChannel PORTAL_RENDER_TEXTURE_CHANNEL = new PortalRenderTextureChannel();

    @Override
    public int getTextureChannelID() {
        return 4;
    }
    
}
