package doors.graphics.texture;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL42;

import doors.utility.FileIO;
import doors.utility.geometry.Vector2in;

public class ImageTexture implements ITexture {

    private static Vector2in MAX_DIMENSIONS = new Vector2in(1024, 1024);
    private static ByteBuffer BYTE_BUFFER = ByteBuffer.allocateDirect(MAX_DIMENSIONS.x * MAX_DIMENSIONS.y * 4);

    public static ImageTexture STANDARD_FONT_TEXTURE = new ImageTexture("data/texture/font/standard.png");
    public static ImageTexture UI_TEXTURE = new ImageTexture("data/texture/ui.png");
    public static ImageTexture ENTITY_TEXTURE = new ImageTexture("data/texture/entity.png");

    private String path;

    public ImageTexture(String path) {
        this.path = path;
    }

    private int textureID; 

    public int getTextureID() {
        return this.textureID;
    }

    public void setup() {
        this.textureID = GL42.glGenTextures();
        BYTE_BUFFER.clear();
        var dimensions = FileIO.writeImageToBuffer(path, BYTE_BUFFER);
        BYTE_BUFFER.flip();
        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MIN_FILTER, GL42.GL_NEAREST);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MAG_FILTER, GL42.GL_NEAREST);
        GL42.glTexImage2D(GL42.GL_TEXTURE_2D, 0, GL42.GL_RGBA, dimensions.x, dimensions.y, 0, GL42.GL_RGBA, GL42.GL_UNSIGNED_BYTE, BYTE_BUFFER);
    }
}
