package doors.graphics;

import org.joml.Vector2f;
import org.joml.Vector2i;

import doors.utility.Maths;

public class TextureSampler {

    private Vector2i dimensions;
    private TextureChannel textureChannel;

    public TextureSampler(TextureChannel textureChannel, Vector2i dimensions) {
        this.dimensions = dimensions;
        this.textureChannel = textureChannel;
    }

    public TextureSample createTextureSample(Vector2i position, Vector2i dimensions) {
        var textureOffsets = new Vector2f[] {
            Maths.div(new Vector2f(position), this.dimensions),
            Maths.div(new Vector2f(position).add(0, dimensions.y), this.dimensions),
            Maths.div(new Vector2f(position).add(dimensions.x, dimensions.y), this.dimensions),
            Maths.div(new Vector2f(position).add(dimensions.x, 0), this.dimensions)
        };

        return new TextureSample(textureChannel, textureOffsets);
    }

    public TextureSample createTextureSample() {
        return this.createTextureSample(Maths.VEC2I_ZERO, this.dimensions);
    }

}
