package doors.graphics;

import org.lwjgl.opengl.GL42;

public interface IRenderTarget {

    void use();

    public static void clear() {
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
    }
}
