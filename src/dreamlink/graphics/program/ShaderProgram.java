package dreamlink.graphics.program;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL42;

import dreamlink.graphics.program.shader.ProgramShaderModule;
import dreamlink.graphics.program.shader.ShaderType;
import dreamlink.graphics.program.uniform.UniformVariable;

public class ShaderProgram {

    public final ProgramShaderModule vertexShaderModule;
    public final ProgramShaderModule fragmentShaderModule;

    private final List<UniformVariable<?>> uniforms = new ArrayList<>();

    private int programID;

    public ShaderProgram(String vertexShaderPath, String fragmentShaderPath) {
        this.vertexShaderModule = new ProgramShaderModule(this, vertexShaderPath, ShaderType.vertex);
        this.fragmentShaderModule = new ProgramShaderModule(this, fragmentShaderPath, ShaderType.fragment);
    }

    public int getProgramID() {
        return this.programID;
    }

    public void addUniform(UniformVariable<?> uniform) {
        this.uniforms.add(uniform);
    }

    public void setup() {
        this.programID = GL42.glCreateProgram();

        this.vertexShaderModule.loadShaderCode();
        this.vertexShaderModule.setup();

        this.fragmentShaderModule.loadShaderCode();
        this.fragmentShaderModule.setup();

        GL42.glLinkProgram(this.programID);
        GL42.glValidateProgram(this.programID);

        for(var uniform : this.uniforms) {
            uniform.setup();
        }
    }

    public void destroy() {
        GL42.glDeleteProgram(this.programID);
    }
    
}
