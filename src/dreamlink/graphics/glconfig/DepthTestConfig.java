package dreamlink.graphics.glconfig;

import org.lwjgl.opengl.GL42;

import dreamlink.graphics.glconfig.state.GLState;

public class DepthTestConfig extends GLConfig<Boolean> {

    private static void setDepthTest(Boolean isEnabled) {
        if(isEnabled) {
            GL42.glEnable(GL42.GL_DEPTH_TEST);
        } else {
            GL42.glDisable(GL42.GL_DEPTH_TEST);
        }
    }

    private static final GLState<Boolean> state = new GLState<>(
        DepthTestConfig::setDepthTest
    );

    public static void setup() {
        DepthTestConfig.state.setState(false);
    }

    public DepthTestConfig() {
        super(DepthTestConfig.state);
    }
    

}
