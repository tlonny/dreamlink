package doors.utility;

import org.lwjgl.opengl.GL42;

public class GLFns {

    public static void enableDepthTest() {
        GL42.glEnable(GL42.GL_DEPTH_TEST);
    }

    public static void disableDepthTest() {
        GL42.glDisable(GL42.GL_DEPTH_TEST);
    }

}
