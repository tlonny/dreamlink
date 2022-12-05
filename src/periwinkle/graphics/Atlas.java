package periwinkle.graphics;

import periwinkle.utility.File;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class Atlas {

    public static Atlas TERRAIN_ATLAS = new Atlas(
        "src/texture/terrain.png", 256
    );

    public static Atlas GLYPH_ATLAS = new Atlas(
        "src/texture/glyph.png", 128
    );

    public static Atlas SKY_ATLAS = new Atlas(
        "src/texture/skybox.png", 512
    );

    public static Atlas PARTICLE_ATLAS = new Atlas(
        "src/texture/particle.png", 512
    );

    public static void init() {
        TERRAIN_ATLAS.setup();
        GLYPH_ATLAS.setup();
        SKY_ATLAS.setup();
        PARTICLE_ATLAS.setup();
    }

    private static final int MAX_TEXTURE_WIDTH = 1024;
    private static final int MAX_TEXTURE_BYTES = 4 * MAX_TEXTURE_WIDTH * MAX_TEXTURE_WIDTH;

    private int atlasID;
    private final String textureSrc;
    private final int textureLength;
    private final ByteBuffer setupByteBuffer = MemoryUtil.memAlloc(MAX_TEXTURE_BYTES);

    public Atlas(String textureSrc, int textureLength) {
        this.textureSrc = textureSrc;
        this.textureLength = textureLength;
    }

    public void setup() {
        this.atlasID = GL20.glGenTextures();
        var buffer = File.FILE.loadPNGFromFile(this.textureSrc, this.setupByteBuffer.clear());
        buffer.flip();
        GL20.glBindTexture(GL11.GL_TEXTURE_2D, this.atlasID);
        GL20.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
        GL20.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.textureLength, this.textureLength, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
        GL20.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL20.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
    }

    public Vector2f[] buildTextureOffsets(Vector2i position, Vector2i dimensions) {
        return new Vector2f[] {
            new Vector2f(position.x, position.y).mul(1f / this.textureLength),
            new Vector2f(position.x, position.y + dimensions.y).mul(1f / this.textureLength),
            new Vector2f(position.x + dimensions.x, position.y + dimensions.y).mul(1f / this.textureLength),
            new Vector2f(position.x + dimensions.x, position.y).mul(1f / this.textureLength),
        };
    }

    public void bind() {
        GL20.glActiveTexture(GL13.GL_TEXTURE0);
        GL20.glBindTexture(GL11.GL_TEXTURE_2D, this.atlasID);
    }




}
