package doors.core.graphics.texture;

import doors.utility.vector.Vector2fl;
import doors.utility.vector.Vector2in;

public class TextureSample {

    public AbstractTextureChannel textureChannel;
    public Vector2fl[] textureOffsets;
    public Vector2in dimensions;

    public TextureSample(AbstractTextureChannel textureChannel, Vector2fl[] textureOffsets, Vector2in dimensions) {
        this.textureChannel = textureChannel;
        this.textureOffsets = textureOffsets;
        this.dimensions = dimensions;
    }

}

