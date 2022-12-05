package dreamlink.graphics;

import java.nio.ByteBuffer;

import org.joml.Vector2i;
import org.lwjgl.opengl.GL42;

import dreamlink.graphics.glconfig.FrameBufferConfig;
import dreamlink.graphics.glconfig.TextureConfig;
import dreamlink.graphics.texture.ITexture;
import dreamlink.graphics.texture.Texture;
import dreamlink.graphics.texture.TextureUnit;
import dreamlink.window.Window;

public class FrameBuffer {

    public static final FrameBuffer portal = new FrameBuffer();

    private final Texture texture = new Texture();
    private int frameBufferID;
    private int renderBufferID;

    public ITexture getTexture() {
        return this.texture;
    }

    public int getFrameBufferID() {
        return this.frameBufferID;
    }

    public void setup() {
        var resolution = Window.instance.getResolution(new Vector2i());

        this.frameBufferID = GL42.glGenFramebuffers();

        this.texture.setup();

        try(var textureConfig = new TextureConfig(TextureUnit.working)) {
            textureConfig.setState(this.texture);
            GL42.glTexImage2D(
                GL42.GL_TEXTURE_2D, 
                0, 
                GL42.GL_RGBA, 
                resolution.x,
                resolution.y,
                0, 
                GL42.GL_RGBA, 
                GL42.GL_UNSIGNED_BYTE, 
                (ByteBuffer)null
            );
        }

        this.renderBufferID = GL42.glGenRenderbuffers();
        GL42.glBindRenderbuffer(GL42.GL_RENDERBUFFER, this.renderBufferID);
        GL42.glRenderbufferStorage(
            GL42.GL_RENDERBUFFER, 
            GL42.GL_DEPTH_COMPONENT24, 
            resolution.x, 
            resolution.y
        );

        try(var frameBufferConfig = new FrameBufferConfig()) {
            frameBufferConfig.setState(this);

            GL42.glFramebufferTexture2D(
                GL42.GL_FRAMEBUFFER, 
                GL42.GL_COLOR_ATTACHMENT0,
                GL42.GL_TEXTURE_2D,
                this.texture.getTextureID(),
                0
            );

            GL42.glFramebufferRenderbuffer(
                GL42.GL_FRAMEBUFFER, 
                GL42.GL_DEPTH_ATTACHMENT,
                GL42.GL_RENDERBUFFER,
                this.renderBufferID
            );
        } 
    }

    public void destroy() {
        GL42.glDeleteFramebuffers(this.frameBufferID);
        GL42.glDeleteRenderbuffers(this.renderBufferID);
    }

}
