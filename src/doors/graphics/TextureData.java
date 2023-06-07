package doors.graphics;

import java.nio.ByteBuffer;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.opengl.GL42;

import doors.utility.FileIO;
import doors.utility.Maths;

public class TextureData {

    private static Vector2i MAX_DIMENSIONS = new Vector2i(1024, 1024);
    private static ByteBuffer BYTE_BUFFER = ByteBuffer.allocateDirect(MAX_DIMENSIONS.x * MAX_DIMENSIONS.y * 4);

    public Vector2i dimensions;
    public TextureSample billboardTextureSample;
    public int textureID;

    public TextureData(Vector2i dimensions) {
        this.dimensions = dimensions;
        this.billboardTextureSample = this.createTextureSample(Maths.VEC2I_ZERO, dimensions);
    }

    public void setup(String path) {
        this.textureID = GL42.glGenTextures();

        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MIN_FILTER, GL42.GL_NEAREST);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MAG_FILTER, GL42.GL_NEAREST);
        System.out.println("loaded in texture with path " + path + " and texture id " + this.textureID + "with unit id " + GL42.GL_TEXTURE0);
        if(path == null) {
            GL42.glTexImage2D(GL42.GL_TEXTURE_2D, 0, GL42.GL_RGBA, this.dimensions.x, this.dimensions.y, 0, GL42.GL_RGBA, GL42.GL_UNSIGNED_BYTE, (ByteBuffer)null);
        } else {
            BYTE_BUFFER.clear();
            FileIO.writeImageToBuffer(path, BYTE_BUFFER);
            BYTE_BUFFER.flip();
            GL42.glTexImage2D(GL42.GL_TEXTURE_2D, 0, GL42.GL_RGBA, this.dimensions.x, this.dimensions.y, 0, GL42.GL_RGBA, GL42.GL_UNSIGNED_BYTE, BYTE_BUFFER);
        }
    }

    public TextureSample createTextureSample(Vector2i position, Vector2i dimensions) {
        return new TextureSample(
            Maths.div(new Vector2f(position), this.dimensions),
            Maths.div(new Vector2f(position).add(0, dimensions.y), this.dimensions),
            Maths.div(new Vector2f(position).add(dimensions.x, dimensions.y), this.dimensions),
            Maths.div(new Vector2f(position).add(dimensions.x, 0), this.dimensions)
        );
    }

}
