package dreamlink.graphics.glconfig.cullface;

import org.lwjgl.opengl.GL42;

import dreamlink.graphics.glconfig.GLConfig;
import dreamlink.graphics.glconfig.state.GLState;

public class CullFaceConfig extends GLConfig<CullFace> {

    private static void setCullFace(CullFace face) {
        GL42.glCullFace(face.face);
    }

    private static final GLState<CullFace> state = new GLState<>(
        CullFaceConfig::setCullFace
    );

    public static void setup() {
        CullFaceConfig.state.setState(CullFace.back);
    }

    public CullFaceConfig() {
        super(CullFaceConfig.state);
    }
    

}
