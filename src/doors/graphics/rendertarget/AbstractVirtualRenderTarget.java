package doors.graphics.rendertarget;

import org.lwjgl.opengl.GL42;

import doors.Config;
import doors.graphics.texture.AbstractTextureChannel;
import doors.graphics.texture.EmptyTexture;
import doors.utility.vector.Vector2in;

public abstract class AbstractVirtualRenderTarget extends AbstractRenderTarget {

    private int frameBufferID;
    private int depthBufferID;

    public EmptyTexture texture;

    public AbstractVirtualRenderTarget() {
         this.texture = new EmptyTexture(this.getTextureChannel(), Config.RESOLUTION);
    }

    public void setup() {        
        this.texture.setup();

        this.frameBufferID = GL42.glGenFramebuffers();
        this.useRenderTarget();

        GL42.glFramebufferTexture(GL42.GL_FRAMEBUFFER, GL42.GL_COLOR_ATTACHMENT0, this.texture.textureID, 0);

        this.depthBufferID = GL42.glGenRenderbuffers();
        GL42.glBindRenderbuffer(GL42.GL_RENDERBUFFER, this.depthBufferID);
        GL42.glRenderbufferStorage(GL42.GL_RENDERBUFFER, GL42.GL_DEPTH_COMPONENT, Config.RESOLUTION.x, Config.RESOLUTION.y);
        GL42.glFramebufferRenderbuffer(GL42.GL_FRAMEBUFFER, GL42.GL_DEPTH_ATTACHMENT, GL42.GL_RENDERBUFFER, this.depthBufferID);
    }

    public abstract AbstractTextureChannel getTextureChannel();

    @Override
    protected int getFramebufferID() {
        return this.frameBufferID;
    }

    @Override
    protected Vector2in getViewport() {
        return Config.RESOLUTION;
    }
}
