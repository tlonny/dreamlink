package doors.core.graphics.rendertarget;

import org.lwjgl.opengl.GL42;

import doors.core.config.Config;
import doors.core.graphics.texture.EmptyTexture;
import doors.core.graphics.texture.TextureChannel;
import doors.core.graphics.texture.TextureSample;
import doors.core.utility.vector.Vector2in;

public class VirtualRenderTarget extends RenderTarget {

    private int frameBufferID;
    private int depthBufferID;

    public EmptyTexture texture;
    public TextureSample screenSample;

    public VirtualRenderTarget(TextureChannel textureChannel) {
         this.texture = new EmptyTexture(textureChannel, Config.CONFIG.getResolution());
         this.screenSample = this.texture.createTextureSample(Vector2in.ZERO, Config.CONFIG.getResolution());
    }

    public void setup() {        
        this.texture.setup();

        this.frameBufferID = GL42.glGenFramebuffers();
        this.useRenderTarget();

        GL42.glFramebufferTexture(GL42.GL_FRAMEBUFFER, GL42.GL_COLOR_ATTACHMENT0, this.texture.textureID, 0);

        this.depthBufferID = GL42.glGenRenderbuffers();
        GL42.glBindRenderbuffer(GL42.GL_RENDERBUFFER, this.depthBufferID);
        GL42.glRenderbufferStorage(GL42.GL_RENDERBUFFER, GL42.GL_DEPTH_COMPONENT, Config.CONFIG.getResolution().x, Config.CONFIG.getResolution().y);
        GL42.glFramebufferRenderbuffer(GL42.GL_FRAMEBUFFER, GL42.GL_DEPTH_ATTACHMENT, GL42.GL_RENDERBUFFER, this.depthBufferID);
    }

    @Override
    protected int getFramebufferID() {
        return this.frameBufferID;
    }

    @Override
    protected Vector2in getViewport() {
        return Config.CONFIG.getResolution();
    }
}
