package doors.graphics.rendertarget;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL42;

import doors.Config;
import doors.graphics.texture.ITexture;
import doors.graphics.texture.TextureSample;

public class RenderTargetTexture implements ITexture, IRenderTarget {

    public static RenderTargetTexture CURRENT_WORLD_RENDER_TARGET_TEXTURE = new RenderTargetTexture();
    public static RenderTargetTexture PORTAL_WORLD_RENDER_TARGET_TEXTURE = new RenderTargetTexture();

    private int frameBufferID;
    private int depthBufferID;

    private int textureID;
    public TextureSample textureSample;

    public void setup() {        
        this.textureID = GL42.glGenTextures();
        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MIN_FILTER, GL42.GL_NEAREST);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MAG_FILTER, GL42.GL_NEAREST);

        GL42.glTexImage2D(GL42.GL_TEXTURE_2D, 0, GL42.GL_RGBA, Config.RESOLUTION.x, Config.RESOLUTION.y, 0, GL42.GL_RGBA, GL42.GL_UNSIGNED_BYTE, (ByteBuffer)null);
        this.frameBufferID = GL42.glGenFramebuffers();
        this.useRenderTarget();

        GL42.glFramebufferTexture(GL42.GL_FRAMEBUFFER, GL42.GL_COLOR_ATTACHMENT0, this.textureID, 0);

        this.depthBufferID = GL42.glGenRenderbuffers();
        GL42.glBindRenderbuffer(GL42.GL_RENDERBUFFER, this.depthBufferID);
        GL42.glRenderbufferStorage(GL42.GL_RENDERBUFFER, GL42.GL_DEPTH_COMPONENT, Config.RESOLUTION.x, Config.RESOLUTION.y);
        GL42.glFramebufferRenderbuffer(GL42.GL_FRAMEBUFFER, GL42.GL_DEPTH_ATTACHMENT, GL42.GL_RENDERBUFFER, this.depthBufferID);
    }

    @Override
    public void useRenderTarget() {
        if(PhysicalRenderTarget.USED_RENDER_TARGET == this || this.frameBufferID == 0) {
            return;
        }
        GL42.glBindFramebuffer(GL42.GL_FRAMEBUFFER, this.frameBufferID);
        GL42.glViewport(0, 0, Config.RESOLUTION.x, Config.RESOLUTION.y);
        PhysicalRenderTarget.USED_RENDER_TARGET = this;
    }

    public int getTextureID() {
        return this.textureID;
    }
}
