package doors.graphics;

import java.nio.ByteBuffer;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

import doors.utility.Image;
import doors.utility.VectorExtension;

public class Texture {

    private static int MAX_TEXTURE_SIZE = 1024 * 1024 * 4;
    private static ByteBuffer BYTE_BUFFER = ByteBuffer.allocateDirect(MAX_TEXTURE_SIZE);

    public Vector2i dimensions;
    private String filename;
    private int textureID;

    public Texture(Vector2i dimensions, String filename) {
        this.dimensions = dimensions;
        this.filename = filename;
    }

    public void setup() {
        Image.loadToBuffer(BYTE_BUFFER, this.filename);

        this.textureID = GL20.glGenTextures();
        GL20.glBindTexture(GL20.GL_TEXTURE_2D, this.textureID);
        GL20.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
        GL20.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.dimensions.x, this.dimensions.y, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, BYTE_BUFFER);
        GL20.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL20.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
    }

    public TextureSample createTextureSample(Vector2i position, Vector2i dimensions) {
        return new TextureSample(
            VectorExtension.div(new Vector2f(position), this.dimensions),
            VectorExtension.div(new Vector2f(position).add(0, dimensions.y), this.dimensions),
            VectorExtension.div(new Vector2f(position).add(dimensions.x, dimensions.y), this.dimensions),
            VectorExtension.div(new Vector2f(position).add(dimensions.x, 0), this.dimensions)
        );
    }

    public void bind() {
        GL20.glActiveTexture(GL13.GL_TEXTURE0);
        GL20.glBindTexture(GL11.GL_TEXTURE_2D, this.textureID);
    }

}
