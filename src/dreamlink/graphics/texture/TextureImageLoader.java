package dreamlink.graphics.texture;

import java.nio.ByteBuffer;

import org.joml.Vector2ic;
import org.lwjgl.opengl.GL42;
import org.lwjgl.system.MemoryUtil;

import dreamlink.graphics.glconfig.TextureConfig;

public class TextureImageLoader {

    private final int maxBufferSize = 4096 * 4096 * 4;
    private final ByteBuffer inputBuffer = MemoryUtil.memAlloc(maxBufferSize);

    public static final TextureImageLoader instance = new TextureImageLoader();

    public void buffer(Texture texture, byte[] data, Vector2ic dimensions) {
        this.inputBuffer.clear();
        this.inputBuffer.put(data);
        this.inputBuffer.flip();

        try(var textureConfig = new TextureConfig(TextureUnit.working)) {
            textureConfig.setState(texture);
            GL42.glTexImage2D(
                GL42.GL_TEXTURE_2D, 
                0, 
                GL42.GL_RGBA, 
                dimensions.x(),
                dimensions.y(),
                0, 
                GL42.GL_RGBA, 
                GL42.GL_UNSIGNED_BYTE, 
                this.inputBuffer
            );
        }
    }

}
