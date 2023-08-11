package doors.graphics.texture.sample;

import doors.graphics.texture.channel.AbstractTextureChannel;
import doors.utility.vector.Vector2fl;
import doors.utility.vector.Vector2in;

public class TextureSample {

    public AbstractTextureChannel textureChannel;
    public Vector2fl[] textureOffsets = new Vector2fl[4];
    public Vector2in dimensions;

    public TextureSample(AbstractTextureChannel textureChannel, Vector2in position, Vector2in dimensions) {
        this.textureChannel = textureChannel;
        this.textureOffsets[0] = new Vector2fl(position).add(0, 0);
        this.textureOffsets[1] = new Vector2fl(position).add(0, dimensions.y);
        this.textureOffsets[2] = new Vector2fl(position).add(dimensions.x, dimensions.y);
        this.textureOffsets[3] = new Vector2fl(position).add(dimensions.x, 0);
        this.dimensions = dimensions;
    }

}

