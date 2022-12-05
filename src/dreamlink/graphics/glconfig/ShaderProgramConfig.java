package dreamlink.graphics.glconfig;

import org.lwjgl.opengl.GL42;

import dreamlink.graphics.glconfig.state.GLState;
import dreamlink.graphics.program.ShaderProgram;

public class ShaderProgramConfig extends GLConfig<ShaderProgram> {

    private static void setShaderProgram(ShaderProgram program) {
        GL42.glUseProgram(program == null ? 0 : program.getProgramID());
    }

    private static final GLState<ShaderProgram> state = new GLState<>(
        ShaderProgramConfig::setShaderProgram
    );

    public static void setup() {
        ShaderProgramConfig.state.setState(null);
    }

    public ShaderProgramConfig() {
        super(ShaderProgramConfig.state);
    }
    
}
