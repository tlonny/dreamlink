package doors.graphics;

import org.joml.Vector2i;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

public class RenderTarget {

    private Vector2i dimensions;
    public Texture texture;
    private int frameBufferID;
    private int depthBufferID;

    public RenderTarget(Vector2i dimensions) {
        this.dimensions = dimensions;
        this.texture = new Texture(dimensions);
    }

    public void setup() {        

        this.texture.setup();

        this.frameBufferID = GL30.glGenFramebuffers();
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.frameBufferID);
        GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, this.texture.textureID, 0);

        this.depthBufferID = GL30.glGenRenderbuffers();
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, this.depthBufferID);
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL20.GL_DEPTH_COMPONENT, this.dimensions.x, this.dimensions.y);
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL30.GL_RENDERBUFFER, this.depthBufferID);
    }

    public void bind() {
        System.out.println(this.frameBufferID);
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.frameBufferID);
        GL20.glViewport(0, 0, this.dimensions.x, this.dimensions.y);
        GL15.glClear(GL15.GL_COLOR_BUFFER_BIT | GL15.GL_DEPTH_BUFFER_BIT );
    }

}
