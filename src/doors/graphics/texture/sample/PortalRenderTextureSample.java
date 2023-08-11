package doors.graphics.texture.sample;

import doors.Config;
import doors.graphics.texture.channel.PortalRenderTextureChannel;
import doors.utility.vector.Vector2in;

public class PortalRenderTextureSample extends TextureSample {

    public static PortalRenderTextureSample PORTAL_RENDER = new PortalRenderTextureSample();

    public PortalRenderTextureSample() {
        super(PortalRenderTextureChannel.PORTAL_RENDER_TEXTURE_CHANNEL, Vector2in.ZERO, Config.CONFIG.getResolution());
    }
    
}
