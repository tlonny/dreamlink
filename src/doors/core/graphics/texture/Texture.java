package doors.core.graphics.texture;

import org.lwjgl.opengl.GL42;

import doors.core.utility.vector.Vector2fl;
import doors.core.utility.vector.Vector2in;

public class Texture {

    public static int NUM_TEXTURE_UNITS = 10;
    public static Texture[] USED_TEXTURES = new Texture[NUM_TEXTURE_UNITS];

    private Vector2in dimensions;
    private TextureChannel textureChannel;
    public int textureID;

    public Texture(TextureChannel textureChannel, Vector2in dimensions) {
        this.textureChannel = textureChannel;
        this.dimensions = dimensions;
    }

    public void setup() {
        this.textureID = GL42.glGenTextures();
        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MIN_FILTER, GL42.GL_NEAREST);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MAG_FILTER, GL42.GL_NEAREST);
    }

    public void useTexture() {
        if(this.textureChannel.registeredTexture != this) {
            this.textureChannel.registeredTexture = this;
            GL42.glActiveTexture(GL42.GL_TEXTURE0 + this.textureChannel.getTextureUnitID());
            GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
            System.out.println("Texture " + this.textureID + " bound to texture unit " + this.textureChannel.getTextureUnitID());
        }
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
