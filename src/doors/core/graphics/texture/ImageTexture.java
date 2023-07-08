package doors.core.graphics.texture;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL42;

import doors.core.config.Config;
import doors.core.utility.FileIO;
import doors.core.utility.vector.Vector2in;

public class ImageTexture extends Texture {

    private static int BYTE_BUFFER_BYTES = Config.CONFIG.getMaxImageTextureDimensions().getIntArea() * 4;
    private static ByteBuffer BYTE_BUFFER = ByteBuffer.allocateDirect(BYTE_BUFFER_BYTES);

    private String path;

    public ImageTexture(TextureChannel textureChannel, Vector2in dimensions, String path) {
        super(textureChannel, dimensions);
        this.path = path;
    }

    public void setup() {
        super.setup();
        BYTE_BUFFER.clear();
        var dimensions = FileIO.writeImageToBuffer(path, BYTE_BUFFER, true);
        BYTE_BUFFER.flip();
        GL42.glTexImage2D(GL42.GL_TEXTURE_2D, 0, GL42.GL_RGBA, dimensions.x, dimensions.y, 0, GL42.GL_RGBA, GL42.GL_UNSIGNED_BYTE, BYTE_BUFFER);
    }
}
