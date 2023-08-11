package doors.graphics.rendertarget;

import doors.graphics.texture.ScreenRenderTexture;

public class ScreenVirtualRenderTarget extends AbstractVirtualRenderTarget {

    public static ScreenVirtualRenderTarget SCREEN_VIRTUAL_RENDER_TARGET = new ScreenVirtualRenderTarget();

    public ScreenVirtualRenderTarget() {
        super(ScreenRenderTexture.SCREEN_RENDER_TEXTURE);
    }

}
