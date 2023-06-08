package doors.graphics;

import org.lwjgl.opengl.GL42;

import doors.Config;

public class VirtualRenderTarget extends Texture implements IRenderTarget {

    public static VirtualRenderTarget WORLD_RENDER_TARGET = 
        new VirtualRenderTarget(TextureChannel.WORLD_RENDER_CHANNEL);

    private int frameBufferID;
    private int depthBufferID;

    public VirtualRenderTarget(TextureChannel textureChannel) {
        super(textureChannel, Config.RESOLUTION);
    }

    public void setup() {        
        super.setup();

        this.frameBufferID = GL42.glGenFramebuffers();
        this.bindRenderTarget();

        GL42.glFramebufferTexture(GL42.GL_FRAMEBUFFER, GL42.GL_COLOR_ATTACHMENT0, this.textureID, 0);

        this.depthBufferID = GL42.glGenRenderbuffers();
        GL42.glBindRenderbuffer(GL42.GL_RENDERBUFFER, this.depthBufferID);
        GL42.glRenderbufferStorage(GL42.GL_RENDERBUFFER, GL42.GL_DEPTH_COMPONENT, this.dimensions.x, this.dimensions.y);
        GL42.glFramebufferRenderbuffer(GL42.GL_FRAMEBUFFER, GL42.GL_DEPTH_ATTACHMENT, GL42.GL_RENDERBUFFER, this.depthBufferID);
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
