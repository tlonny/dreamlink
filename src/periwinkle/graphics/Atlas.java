package periwinkle.graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import de.matthiasmann.twl.utils.PNGDecoder;

public class Atlas {

    private static int MAX_TEXTURE_SIZE = 1024 * 1024 * 4;
    private static ByteBuffer BYTE_BUFFER = ByteBuffer.allocateDirect(MAX_TEXTURE_SIZE);

    private int atlasID;
    private final String textureSrc;
    public Vector2i dimensions;

    public Atlas(String textureSrc, Vector2i dimensions) {
        this.textureSrc = textureSrc;
        this.dimensions = dimensions;
    }

    public void setup() {
        try {
            var stream = new FileInputStream(this.textureSrc);
            var decoder = new PNGDecoder(stream);
            decoder.decode(BYTE_BUFFER, this.dimensions.x * 4, PNGDecoder.Format.RGBA);

        } catch (FileNotFoundException e) {
            var msg = String.format("Unable to find PNG: %s", this.textureSrc);
            throw new RuntimeException(msg);
        } catch (IOException e) {
            var msg = String.format("Unable to load PNG: %s", this.textureSrc);
            throw new RuntimeException(msg);
        }

        BYTE_BUFFER.flip();
        this.atlasID = GL20.glGenTextures();
        GL20.glBindTexture(GL11.GL_TEXTURE_2D, this.atlasID);
        GL20.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
        GL20.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.dimensions.x, this.dimensions.y, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, BYTE_BUFFER);
        GL20.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL20.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        BYTE_BUFFER.clear();
    }

    public void bind() {
        GL20.glActiveTexture(GL13.GL_TEXTURE0);
        GL20.glBindTexture(GL11.GL_TEXTURE_2D, this.atlasID);
    }

    public Texture createTexture() {
        return this.createTexture(new Vector2i(0,0), this.dimensions);
    }

    public Texture createTexture(Vector2i tilePosition, Vector2i dimensions) {
        var position = new Vector2i(tilePosition).mul(dimensions);
        var vertices = new Vector2f[] {
            new Vector2f(
                (float)position.x/this.dimensions.x,
                (float)position.y/this.dimensions.y
            ),
            new Vector2f(
                (float)position.x/this.dimensions.x,
                (float)(position.y + dimensions.y)/this.dimensions.y
            ),
            new Vector2f(
                (float)(position.x + dimensions.x)/this.dimensions.x,
                (float)(position.y + dimensions.y)/this.dimensions.y
            ),
            new Vector2f(
                (float)(position.x + dimensions.x)/this.dimensions.x,
                (float)position.y/this.dimensions.y
            )
        };
        return new Texture(vertices, dimensions);
    }

}
