package doors.graphics;

import org.joml.Vector2f;

public class TextureSample {

    public Vector2f[] textureOffsets;

    public TextureSample() {
        this.textureOffsets = new Vector2f[] {
            new Vector2f(0,0),
            new Vector2f(0,1),
            new Vector2f(1,1),
            new Vector2f(1,0),
        };
    }

    public TextureSample(Vector2f vertex0, Vector2f vertex1, Vector2f vertex2, Vector2f vertex3) {
        this.textureOffsets = new Vector2f[] {
            vertex0,
            vertex1,
            vertex2,
            vertex3
        };
    }
}
