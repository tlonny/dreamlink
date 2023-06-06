package doors.graphics;

import org.lwjgl.opengl.GL11;

public class GLConfig {

    public boolean depthTest;

    public GLConfig(boolean depthTest) {
        this.depthTest = depthTest;
    }

    private void applyDepthTest() {
        if(this.depthTest) {
                GL11.glEnable(GL11.GL_DEPTH_TEST);
        } else {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        }
    }

    public void apply() {
        this.applyDepthTest();
    }

    public static GLConfig WORLD_CONFIG = new GLConfig(true);
    public static GLConfig OVERLAY_CONFIG = new GLConfig(false);

}
