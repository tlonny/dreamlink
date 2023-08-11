package doors.graphics.rendertarget;

import doors.graphics.texture.PortalRenderTexture;

public class PortalVirtualRenderTarget extends AbstractVirtualRenderTarget {

    public static PortalVirtualRenderTarget PORTAL_VIRTUAL_RENDER_TARGET = new PortalVirtualRenderTarget();

    public PortalVirtualRenderTarget() {
        super(PortalRenderTexture.PORTAL_RENDER_TEXTURE);
    }
}
