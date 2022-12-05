package dreamlink.graphics.program.uniform;

import org.lwjgl.opengl.GL42;

import dreamlink.graphics.program.ShaderProgram;
import dreamlink.graphics.program.uniform.strategy.IUniformStrategy;

public class UniformVariable<T> {

    private final String uniformName;
    private final ShaderProgram program;
    private final IUniformStrategy<T> strategy;
    private int uniformID;

    public UniformVariable(
        ShaderProgram program, 
        IUniformStrategy<T> strategy, 
        String uniformName
    ) {
        this.program = program;
        this.strategy = strategy;
        this.uniformName = uniformName;
        program.addUniform(this);
    }

    public UniformVariable(
        ShaderProgram program, 
        IUniformStrategy<T> strategy, 
        String uniformName, 
        int index
    ) {
        this(program, strategy, String.format("%s[%d]", uniformName, index));
    }

    public void setup() {
        this.uniformID = GL42.glGetUniformLocation(
            this.program.getProgramID(),
            this.uniformName
        );
    }

    public void setValue(T value) {
        this.strategy.setValue(this.uniformID, value);
    }

}
