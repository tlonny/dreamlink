package doors.graphics.texture.channel;

public class PortalRenderTextureChannel extends AbstractTextureChannel {

    public static PortalRenderTextureChannel PORTAL_RENDER_TEXTURE_CHANNEL = new PortalRenderTextureChannel();

    @Override
    public int getTextureUnitID() {
        return 5;
    }
    
}
