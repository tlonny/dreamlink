package doors.graphics;

import org.lwjgl.opengl.GL42;

import doors.io.Window;

public class PhysicalRenderTarget implements IRenderTarget {

    public static PhysicalRenderTarget PHYSICAL_RENDER_TARGET = new PhysicalRenderTarget();
    public static IRenderTarget BOUND_RENDER_TARGET = PhysicalRenderTarget.PHYSICAL_RENDER_TARGET;

    @Override
    public void bindRenderTarget() {
        if(PhysicalRenderTarget.BOUND_RENDER_TARGET != this) {
            GL42.glBindFramebuffer(GL42.GL_FRAMEBUFFER, 0);
            GL42.glViewport(0, 0, Window.WINDOW.dimensions.x, Window.WINDOW.dimensions.y);
            PhysicalRenderTarget.BOUND_RENDER_TARGET = this;
        }
    }
}
