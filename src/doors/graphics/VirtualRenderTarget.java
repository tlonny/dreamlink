package doors.graphics;

import org.lwjgl.opengl.GL42;

public class VirtualRenderTarget extends TextureData implements IRenderTarget {

    public static VirtualRenderTarget WORLD_RENDER_TARGET = new VirtualRenderTarget();

    private int frameBufferID;
    private int depthBufferID;

    public VirtualRenderTarget() {
        super(IRenderTarget.RESOLUTION);
    }

    public void setup() {        
        super.setup(null);

        this.frameBufferID = GL42.glGenFramebuffers();
        this.bind();

        GL42.glFramebufferTexture(GL42.GL_FRAMEBUFFER, GL42.GL_COLOR_ATTACHMENT0, this.textureID, 0);

        this.depthBufferID = GL42.glGenRenderbuffers();
        GL42.glBindRenderbuffer(GL42.GL_RENDERBUFFER, this.depthBufferID);
        GL42.glRenderbufferStorage(GL42.GL_RENDERBUFFER, GL42.GL_DEPTH_COMPONENT, this.dimensions.x, this.dimensions.y);
        GL42.glFramebufferRenderbuffer(GL42.GL_FRAMEBUFFER, GL42.GL_DEPTH_ATTACHMENT, GL42.GL_RENDERBUFFER, this.depthBufferID);
    }

    @Override
    public void bind() {
        if(PhysicalRenderTarget.BOUND_RENDER_TARGET != this) {
            GL42.glBindFramebuffer(GL42.GL_FRAMEBUFFER, this.frameBufferID);
            GL42.glViewport(0, 0, this.dimensions.x, this.dimensions.y);
            PhysicalRenderTarget.BOUND_RENDER_TARGET = this;
        }
    }
}
