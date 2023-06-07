package doors.utility;

import org.lwjgl.opengl.GL42;

public class GLFns {

    public static void clear() {
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
    }

    public static void enableDepthTest() {
        GL42.glEnable(GL42.GL_DEPTH_TEST);
    }

    public static void disableDepthTest() {
        GL42.glDisable(GL42.GL_DEPTH_TEST);
    }

}
