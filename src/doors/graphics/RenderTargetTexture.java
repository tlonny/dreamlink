package doors.graphics;

import java.nio.ByteBuffer;

import org.joml.Vector2i;
import org.lwjgl.opengl.GL42;

import doors.Config;

public class RenderTargetTexture implements IRenderTarget, ITexture {

    public static RenderTargetTexture WORLD_RENDER_TARGET_TEXTURE = 
        new RenderTargetTexture(Config.RESOLUTION);

    private int frameBufferID;
    private int depthBufferID;

    private Vector2i dimensions;
    private int textureID;

    public RenderTargetTexture(Vector2i dimensions) {
        this.dimensions = dimensions;
    }

    public void setup() {        
        this.textureID = GL42.glGenTextures();
        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MIN_FILTER, GL42.GL_NEAREST);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MAG_FILTER, GL42.GL_NEAREST);

        GL42.glTexImage2D(GL42.GL_TEXTURE_2D, 0, GL42.GL_RGBA, this.dimensions.x, this.dimensions.y, 0, GL42.GL_RGBA, GL42.GL_UNSIGNED_BYTE, (ByteBuffer)null);
        this.frameBufferID = GL42.glGenFramebuffers();
        this.bindRenderTarget();

        GL42.glFramebufferTexture(GL42.GL_FRAMEBUFFER, GL42.GL_COLOR_ATTACHMENT0, this.textureID, 0);

        this.depthBufferID = GL42.glGenRenderbuffers();
        GL42.glBindRenderbuffer(GL42.GL_RENDERBUFFER, this.depthBufferID);
        GL42.glRenderbufferStorage(GL42.GL_RENDERBUFFER, GL42.GL_DEPTH_COMPONENT, this.dimensions.x, this.dimensions.y);
        GL42.glFramebufferRenderbuffer(GL42.GL_FRAMEBUFFER, GL42.GL_DEPTH_ATTACHMENT, GL42.GL_RENDERBUFFER, this.depthBufferID);
    }

    @Override
    public int getTextureID() {
        return this.textureID;
    }

    @Override
    public void bindRenderTarget() {
        if(PhysicalRenderTarget.BOUND_RENDER_TARGET != this) {
            GL42.glBindFramebuffer(GL42.GL_FRAMEBUFFER, this.frameBufferID);
            GL42.glViewport(0, 0, this.dimensions.x, this.dimensions.y);
            PhysicalRenderTarget.BOUND_RENDER_TARGET = this;
        }
    }
}
