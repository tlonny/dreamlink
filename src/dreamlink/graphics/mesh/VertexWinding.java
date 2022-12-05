package dreamlink.graphics.mesh;

import org.joml.Vector2f;
import org.joml.Vector2fc;

public class VertexWinding {

    private static final VertexWinding[] windings = new VertexWinding[] {
        new VertexWinding(new Vector2f(0f, 1f)),
        new VertexWinding(new Vector2f(0f, 0f)),
        new VertexWinding(new Vector2f(1f, 0f)),
        new VertexWinding(new Vector2f(1f, 1f)),
    };

    public static int getSize() {
        return windings.length;
    }

    public static VertexWinding get(int index) {
        return windings[index];
    }

    public final Vector2fc winding;

    public VertexWinding(Vector2fc winding) {
        this.winding = winding;
    }
    
}
