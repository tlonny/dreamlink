package dreamlink.graphics.glconfig.blend;

import org.lwjgl.opengl.GL42;

import dreamlink.graphics.glconfig.GLConfig;
import dreamlink.graphics.glconfig.state.GLState;

public class BlendModeConfig extends GLConfig<BlendMode> {

    private static void setBlendMode(BlendMode blendMode) {
        GL42.glBlendFunc(blendMode.srcFactor, blendMode.dstFactor);
    }

    private static final GLState<BlendMode> state = new GLState<>(
        BlendModeConfig::setBlendMode
    );

    public static void setup() {
        BlendModeConfig.state.setState(BlendMode.noBlend);
    } 

    public BlendModeConfig() {
        super(BlendModeConfig.state);
    }

    
}
