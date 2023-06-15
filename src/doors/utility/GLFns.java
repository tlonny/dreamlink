package doors.utility;

import org.lwjgl.opengl.GL42;

public class GLFns {

    public static void clear() {
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
    }

    public static void setDepthTest(boolean enabled) {
        if(enabled) {
            GL42.glEnable(GL42.GL_DEPTH_TEST);
        } else {
            GL42.glDisable(GL42.GL_DEPTH_TEST);
        }
    }

    public static void setFaceCulling(boolean enabled) {
        if(enabled) {
            GL42.glEnable(GL42.GL_CULL_FACE);
        } else {
            GL42.glDisable(GL42.GL_CULL_FACE);
        }
    }

}
