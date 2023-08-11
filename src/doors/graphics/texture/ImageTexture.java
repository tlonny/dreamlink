package doors.graphics.texture;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL42;

import doors.graphics.texture.channel.AbstractTextureChannel;
import doors.utility.io.Image;
import doors.utility.vector.Vector2in;
import doors.work.WorkUnit;

public class ImageTexture extends AbstractTexture {

    private static Vector2in MAX_TEXTURE_DIMENSIONS = new Vector2in(4096, 4096);
    private static ByteBuffer BYTE_BUFFER = ByteBuffer.allocateDirect(MAX_TEXTURE_DIMENSIONS.getIntArea() * 4);

    private Vector2in dimensions = new Vector2in();
    private Image image;

    public WorkUnit setupWorkUnit = new WorkUnit();

    public ImageTexture(AbstractTextureChannel textureChannel, String path) {
        super(textureChannel);
        this.image = new Image(path, true);
        this.setupWorkUnit = new WorkUnit(this::loadToGPU);
        this.setupWorkUnit.registerDependency(this.image.setupWorkUnit);
        this.setupWorkUnit.submit();
    }

    private void loadToGPU() {
        BYTE_BUFFER.clear();
        BYTE_BUFFER.put(this.image.getPixelData());
        BYTE_BUFFER.flip();
        this.dimensions.set(this.image.getDimensions());

        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        GL42.glTexImage2D(
            GL42.GL_TEXTURE_2D, 
            0, 
            GL42.GL_RGBA, 
            this.dimensions.x, 
            this.dimensions.y, 
            0, 
            GL42.GL_RGBA, 
            GL42.GL_UNSIGNED_BYTE, 
            BYTE_BUFFER
        );
    }

    @Override
    public String toString() {
        return String.format("ImageTexture(%s)", this.image.path);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }
}
