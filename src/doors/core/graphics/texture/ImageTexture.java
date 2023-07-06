package doors.core.graphics.texture;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL42;

import doors.core.utility.FileIO;
import doors.core.utility.vector.Vector2in;

public class ImageTexture implements ITexture {

    private static Vector2in MAX_DIMENSIONS = new Vector2in(1024, 1024);
    private static ByteBuffer BYTE_BUFFER = ByteBuffer.allocateDirect(MAX_DIMENSIONS.getIntArea() * 4);

    private String path;
    private int textureID; 

    public ImageTexture(String path) {
        this.path = path;
    }

    @Override
    public int getTextureID() {
        return this.textureID;
    }

    public void setup() {
        this.textureID = GL42.glGenTextures();
        BYTE_BUFFER.clear();
        var dimensions = FileIO.writeImageToBuffer(path, BYTE_BUFFER, true);
        BYTE_BUFFER.flip();
        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MIN_FILTER, GL42.GL_NEAREST);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MAG_FILTER, GL42.GL_NEAREST);
        GL42.glTexImage2D(GL42.GL_TEXTURE_2D, 0, GL42.GL_RGBA, dimensions.x, dimensions.y, 0, GL42.GL_RGBA, GL42.GL_UNSIGNED_BYTE, BYTE_BUFFER);
    }
}
