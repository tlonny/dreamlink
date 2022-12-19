package periwinkle.graphics;

import org.joml.Vector2f;
import org.joml.Vector2i;

public class Texture {

    public Vector2f[] vertices;
    public Vector2i rawDimensions;

    public Texture(Vector2f[] vertices, Vector2i rawDimensions) {
        this.vertices = vertices;
        this.rawDimensions = rawDimensions;
    }


}

