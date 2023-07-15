package doors.core.graphics.rendertarget;

import org.lwjgl.opengl.GL42;

import doors.utility.vector.Vector2in;

public abstract class AbstractRenderTarget {

    private static AbstractRenderTarget USED_RENDER_TARGET;

    protected abstract int getFramebufferID();

    protected abstract Vector2in getViewport();

    public void useRenderTarget() {
        if(USED_RENDER_TARGET == this) {
            return;
        }

        var viewport = this.getViewport();
        GL42.glBindFramebuffer(GL42.GL_FRAMEBUFFER, this.getFramebufferID());
        GL42.glViewport(0, 0, viewport.x, viewport.y);
        USED_RENDER_TARGET = this;
    }

}
