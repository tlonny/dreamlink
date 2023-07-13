package doors.graphics.rendertarget;

import org.lwjgl.opengl.GL42;

import doors.core.Config;
import doors.graphics.texture.EmptyTexture;
import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSample;
import doors.utility.vector.Vector2in;

public class VirtualRenderTarget extends RenderTarget {

    public static VirtualRenderTarget RENDER_TARGET_CURRENT = new VirtualRenderTarget(TextureChannel.TEXTURE_CHANNEL_CURRENT_LEVEL);
    public static VirtualRenderTarget RENDER_TARGET_PORTAL = new VirtualRenderTarget(TextureChannel.TEXTURE_CHANNEL_PORTAL_LEVEL);

    private int frameBufferID;
    private int depthBufferID;

    public EmptyTexture texture;
    public TextureSample screenSample;

    public VirtualRenderTarget(TextureChannel textureChannel) {
         this.texture = new EmptyTexture(textureChannel, Config.RESOLUTION);
         this.screenSample = this.texture.createTextureSample(Vector2in.ZERO, Config.RESOLUTION);
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

    @Override
    protected int getFramebufferID() {
        return this.frameBufferID;
    }

    @Override
    protected Vector2in getViewport() {
        return Config.RESOLUTION;
    }
}
