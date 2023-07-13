package doors.graphics.rendertarget;

import doors.io.Window;
import doors.utility.vector.Vector2in;

public class PhysicalRenderTarget extends RenderTarget {

    public static PhysicalRenderTarget PHYSICAL_RENDER_TARGET = new PhysicalRenderTarget();

    @Override
    protected int getFramebufferID() {
        return 0;
    }

    @Override
    protected Vector2in getViewport() {
        return Window.WINDOW.dimensions;
    }

}
