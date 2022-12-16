package periwinkle.graphics;

import org.joml.Vector2f;

public class Sprite {

    public final Vector2f[] vertices;

    public Sprite(Vector2f v0, Vector2f v1, Vector2f v2, Vector2f v3) {
        this.vertices = new Vector2f[] { v0, v1, v2, v3 };
    }

}

