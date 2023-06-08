package doors.graphics;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2i;
import org.lwjgl.opengl.GL42;

import doors.utility.FileIO;
import doors.utility.Maths;

public class Texture {

    private static Vector2i MAX_DIMENSIONS = new Vector2i(1024, 1024);
    private static ByteBuffer BYTE_BUFFER = ByteBuffer.allocateDirect(MAX_DIMENSIONS.x * MAX_DIMENSIONS.y * 4);
    public static Map<String, Texture> TEXTURE_DATA_LOOKUP = new HashMap<>();

    public TextureChannel textureChannel;
    public Vector2i dimensions;
    public String name;
    public TextureSample billboardTextureSample;
    public int textureID;

    public Texture(TextureChannel textureChannel, Vector2i dimensions) {
        this(null, textureChannel, dimensions);
    }

    public Texture(String name, TextureChannel textureChannel, Vector2i dimensions) {
        this.name = name;
        this.textureChannel = textureChannel;
        this.dimensions = dimensions;
        this.billboardTextureSample = this.createTextureSample(Maths.VEC2I_ZERO, dimensions);

        if(this.name != null) {
            TEXTURE_DATA_LOOKUP.put(this.name, this);
        }
    }

    public void bindTexture() {
        this.textureChannel.bindTextureID(this.textureID);
    }

    public void setup() {
        this.textureID = GL42.glGenTextures();
        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MIN_FILTER, GL42.GL_NEAREST);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MAG_FILTER, GL42.GL_NEAREST);
        GL42.glTexImage2D(GL42.GL_TEXTURE_2D, 0, GL42.GL_RGBA, this.dimensions.x, this.dimensions.y, 0, GL42.GL_RGBA, GL42.GL_UNSIGNED_BYTE, (ByteBuffer)null);
    }

    public void loadFromFile(String path) {
        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        BYTE_BUFFER.clear();
        FileIO.writeImageToBuffer(path, BYTE_BUFFER);
        BYTE_BUFFER.flip();
        GL42.glTexImage2D(GL42.GL_TEXTURE_2D, 0, GL42.GL_RGBA, this.dimensions.x, this.dimensions.y, 0, GL42.GL_RGBA, GL42.GL_UNSIGNED_BYTE, BYTE_BUFFER);
    }

    public TextureSample createTextureSample(Vector2i position, Vector2i dimensions) {
        return new TextureSample(this.textureChannel, position, dimensions, this.dimensions);
    }

}
