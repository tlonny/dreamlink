package doors.core.graphics.texture;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL42;

import doors.Config;
import doors.core.utility.vector.Vector2in;

public class EmptyTexture extends Texture {

    public EmptyTexture(TextureChannel textureChannel, Vector2in dimensions) {
        super(textureChannel, dimensions);
    }

    public void setup() {
        super.setup();
        GL42.glTexImage2D(
            GL42.GL_TEXTURE_2D, 
            0, 
            GL42.GL_RGBA, 
            Config.RESOLUTION.x, 
            Config.RESOLUTION.y, 
            0, 
            GL42.GL_RGBA, 
            GL42.GL_UNSIGNED_BYTE, 
            (ByteBuffer)null
        );
    }
}
