package doors.graphics;

import org.joml.Vector2i;

public interface IRenderTarget {

    public static Vector2i RESOLUTION = new Vector2i(1280, 720);

    void bind();
}
