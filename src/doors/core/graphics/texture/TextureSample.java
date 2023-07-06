package doors.core.graphics.texture;

import doors.Config;
import doors.core.utility.vector.Vector2fl;
import doors.core.utility.vector.Vector2in;

public class TextureSample {

    private static TextureSampler SCREEN_SAMPLER = new TextureSampler(Config.RESOLUTION, Config.RESOLUTION);
    public static TextureSample SCREEN_SAMPLE = SCREEN_SAMPLER.createTextureSample(Vector2in.ZERO);

    public Vector2fl[] textureOffsets;
    public Vector2in dimensions;

    public TextureSample(Vector2fl[] textureOffsets, Vector2in dimensions) {
        this.textureOffsets = textureOffsets;
        this.dimensions = dimensions;
    }

}

