package doors.graphics;

import org.joml.Vector2f;
import org.joml.Vector2i;

import doors.utility.Maths;

public class TextureSample {

    public Vector2f[] textureOffsets;
    public TextureChannel textureChannel;

    public TextureSample(TextureChannel textureChannel, Vector2i position, Vector2i dimensions, Vector2i textureDimensions) {
        this.textureChannel = textureChannel;
        this.textureOffsets = new Vector2f[] {
            Maths.div(new Vector2f(position), textureDimensions),
            Maths.div(new Vector2f(position).add(0, dimensions.y), textureDimensions),
            Maths.div(new Vector2f(position).add(dimensions.x, dimensions.y), textureDimensions),
            Maths.div(new Vector2f(position).add(dimensions.x, 0), textureDimensions)
        };
    }
}

