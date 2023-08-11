package doors.graphics.texture;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL42;

import doors.Config;
import doors.graphics.texture.channel.AbstractTextureChannel;
import doors.utility.vector.Vector2in;

public class FrameBufferTexture extends AbstractTexture {

    public FrameBufferTexture(AbstractTextureChannel textureChannel) {
        super(textureChannel);
        var resolution = Config.CONFIG.getResolution();
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

    @Override
    public Vector2in getDimensions() {
        return Config.CONFIG.getResolution();
    }
}
