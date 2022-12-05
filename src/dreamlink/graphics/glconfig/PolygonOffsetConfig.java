package dreamlink.graphics.glconfig;

import org.lwjgl.opengl.GL42;

import dreamlink.graphics.glconfig.state.GLState;

public class PolygonOffsetConfig extends GLConfig<Boolean> {

    public static void setPolygonOffset(boolean isEnabled) {
        if(isEnabled) {
            GL42.glEnable(GL42.GL_POLYGON_OFFSET_FILL);
        } else {
            GL42.glDisable(GL42.GL_POLYGON_OFFSET_FILL);
        }
    }

    private static final GLState<Boolean> state = new GLState<>(
        PolygonOffsetConfig::setPolygonOffset
    );
    
    public static void setup() {
        PolygonOffsetConfig.state.setState(false);
    }

    public PolygonOffsetConfig() {
        super(PolygonOffsetConfig.state);
    }

}
