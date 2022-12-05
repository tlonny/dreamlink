package dreamlink.graphics.program;

import java.util.ArrayList;

import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.program.uniform.UniformVariable;
import dreamlink.graphics.program.uniform.strategy.IntegerUniformStrategy;

public class SpriteShaderProgram extends ShaderProgram {

    private static final String vertexPath = "glsl/sprite/Vertex.glsl";
    private static final String fragmentPath = "glsl/sprite/Fragment.glsl";
    private static final String uniformAnimationFrame = "animation_frame";

    public static final SpriteShaderProgram instance = new SpriteShaderProgram();

    private final UniformVariable<Integer> animationFrame = new UniformVariable<>(
        this, new IntegerUniformStrategy(), SpriteShaderProgram.uniformAnimationFrame
    );

    private final ArrayList<UniformVariable<Integer>> samplers = new ArrayList<>();

    public SpriteShaderProgram() {
        super(SpriteShaderProgram.vertexPath, SpriteShaderProgram.fragmentPath);

        for(var ix = 0; ix < ShaderProgramSampler.size(); ix += 1) {
            var uniform = new UniformVariable<>(
                this, 
                new IntegerUniformStrategy(), 
                ShaderProgramSampler.get(ix).samplerName
            );
            this.samplers.add(uniform);
        }
    }

    public void setAnimationFrame(int frame) {
        this.animationFrame.setValue(frame);
    }

    @Override
    public void setup() {
        super.setup();
        
        try(var shaderProgramConfig = new ShaderProgramConfig()) {
            shaderProgramConfig.setState(this);
            for(var ix = 0; ix < ShaderProgramSampler.size(); ix += 1) {
                var sampler = ShaderProgramSampler.get(ix);
                var uniform = this.samplers.get(ix);
                uniform.setValue(sampler.textureUnit.getIndex());
            }
        } 
    }
    
}
