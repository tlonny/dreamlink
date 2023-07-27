package doors.graphics.texture;

import org.lwjgl.opengl.GL42;

import doors.utility.vector.Vector2fl;
import doors.utility.vector.Vector2in;

public abstract class AbstractTexture {

    public static int NUM_TEXTURE_UNITS = 10;
    public static AbstractTexture[] USED_TEXTURES = new AbstractTexture[NUM_TEXTURE_UNITS];

    protected Vector2in dimensions;
    private AbstractTextureChannel textureChannel;
    public int textureID;

    public AbstractTexture(AbstractTextureChannel textureChannel, Vector2in dimensions) {
        this.textureChannel = textureChannel;
        this.dimensions = dimensions;
    }

    protected abstract void loadTextureData();

    public void setup() {
        this.textureID = GL42.glGenTextures();
        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MIN_FILTER, GL42.GL_NEAREST);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MAG_FILTER, GL42.GL_NEAREST);
        this.loadTextureData();
    }

    public void useTexture() {
        // Careful here - if we bind the texture to the texture channel before running setup
        // we will effectively bind texture ID 0 to the channel. The "caching" logic prevents
        // this from being corrected later - so we need to catch it earlier...
        if(this.textureID == 0) {
            return;
        }

        if(this.textureChannel.registeredTexture != this) {
            this.textureChannel.registeredTexture = this;
            GL42.glActiveTexture(GL42.GL_TEXTURE0 + this.textureChannel.getTextureUnitID());
            GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        }
    }

    public TextureSample createTextureSample() {
        return this.createTextureSample(Vector2in.ZERO, this.dimensions);
    }

    public TextureSample createTextureSample(Vector2in position, Vector2in dimensions) {
        var textureOffsets = new Vector2fl[] {
            new Vector2fl(position).add(0, 0).div(this.dimensions),
            new Vector2fl(position).add(0, dimensions.y).div(this.dimensions),
            new Vector2fl(position).add(dimensions.x, dimensions.y).div(this.dimensions),
            new Vector2fl(position).add(dimensions.x, 0).div(this.dimensions)
        };
        return new TextureSample(this.textureChannel, textureOffsets, new Vector2in(dimensions));
    }

    public TextureSample createTextureSample(Vector2in position, Vector2in dimensions, Vector2in scale) {
        return this.createTextureSample(
            new Vector2in(position).mul(scale),
            new Vector2in(dimensions).mul(scale)
        );
    }

}
