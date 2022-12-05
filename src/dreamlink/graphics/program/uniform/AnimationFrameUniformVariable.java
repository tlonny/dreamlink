package dreamlink.graphics.program.uniform;

import dreamlink.graphics.program.ShaderProgram;
import dreamlink.graphics.program.uniform.strategy.IntegerUniformStrategy;

public class AnimationFrameUniformVariable extends UniformVariable<Integer> {

    private static final String uniformName = "animation_frame";

    public AnimationFrameUniformVariable(ShaderProgram program) {
        super(program, new IntegerUniformStrategy(), AnimationFrameUniformVariable.uniformName);
    }
    
}
