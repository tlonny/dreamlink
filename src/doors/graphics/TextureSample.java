package doors.graphics;

import org.joml.Vector2f;

public class TextureSample {

    public TextureChannel textureChannel;
    public Vector2f[] textureOffsets;

    public TextureSample(TextureChannel textureChannel, Vector2f[] textureOffsets) {
        this.textureChannel = textureChannel;
        this.textureOffsets = textureOffsets;
    }
}

