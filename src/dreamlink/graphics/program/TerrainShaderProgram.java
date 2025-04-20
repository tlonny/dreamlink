package dreamlink.graphics.program;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector3fc;
import org.joml.Vector4fc;

import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.program.strategy.camera.CameraStrategy;
import dreamlink.graphics.program.strategy.camera.ICameraStrategyProvider;
import dreamlink.graphics.program.strategy.clip.ClipStrategy;
import dreamlink.graphics.program.strategy.clip.IClipStrategyProvider;
import dreamlink.graphics.program.strategy.model.IModelStrategyProvider;
import dreamlink.graphics.program.strategy.model.ModelStrategy;
import dreamlink.graphics.program.strategy.transformer.ITransformerStrategyProvider;
import dreamlink.graphics.program.strategy.transformer.TransformerStrategy;
import dreamlink.graphics.program.uniform.AnimationFrameUniformVariable;
import dreamlink.graphics.program.uniform.UniformVariable;
import dreamlink.graphics.program.uniform.strategy.BooleanUniformStrategy;
import dreamlink.graphics.program.uniform.strategy.FloatUniformStrategy;
import dreamlink.graphics.program.uniform.strategy.IntegerUniformStrategy;
import dreamlink.graphics.program.uniform.strategy.Matrix4fUniformStrategy;
import dreamlink.graphics.program.uniform.strategy.Vector3fUniformStrategy;
import dreamlink.graphics.program.uniform.strategy.Vector4fUniformStrategy;
import dreamlink.utility.maths.Vector3fMaths;

public class TerrainShaderProgram extends ShaderProgram {

    private class InternalCameraStrategyProvider implements ICameraStrategyProvider {

        @Override
        public void setViewProjectionMatrixUniform(Matrix4f matrix) {
            TerrainShaderProgram.this.viewProjectionMatrix.setValue(matrix);
        }

        @Override
        public void setViewRotationMatrixUniform(Matrix4f matrix) {
            TerrainShaderProgram.this.viewRotationMatrix.setValue(matrix);
        }

        @Override
        public void setViewTranslationMatrixUniform(Matrix4f matrix) {
            TerrainShaderProgram.this.viewTranslationMatrix.setValue(matrix);
        }

    }

    private class InternalModelStrategyProvider implements IModelStrategyProvider {

        @Override
        public void setModelMatrixUniform(Matrix4f matrix) {
            TerrainShaderProgram.this.modelMatrix.setValue(matrix);
        }
    }

    private class InternalTransformerStrategyProvider implements ITransformerStrategyProvider {

        @Override
        public void setTransformerMatrixUniform(int index, Matrix4f matrix) {
            TerrainShaderProgram.this.transformMatrices.get(index).setValue(matrix);
        }

    }

    private class InternalClipStrategyProvider implements IClipStrategyProvider {

        @Override
        public void setClipUniform(Vector4fc clip) {
            TerrainShaderProgram.this.clip.setValue(clip);
        }
    }

    private static final String vertexPath = "glsl/terrain/Vertex.glsl";
    private static final String fragmentPath = "glsl/terrain/Fragment.glsl";
    private static final int numTransformers = 4;

    private static final String uniformTransformerMatrix = "transformer_matrix";
    private static final String uniformViewProjectionMatrix = "view_projection_matrix";
    private static final String uniformViewRotationMatrix = "view_rotation_matrix";
    private static final String uniformViewTranslationMatrix = "view_translation_matrix";
    private static final String uniformModelMatrix = "model_matrix";
    private static final String uniformClipPlane = "clip";
    private static final String uniformCanSeeHiddenBlocks = "can_see_hidden_blocks";
    private static final String uniformIsLightingEnabled = "is_lighting_enabled";
    private static final String uniformIsFogEnabled = "is_fog_enabled";
    private static final String uniformFogStartDistance = "fog_start_distance";
    private static final String uniformFogRange = "fog_range";

    public static final TerrainShaderProgram instance = new TerrainShaderProgram();

    private final UniformVariable<Matrix4f> viewProjectionMatrix = new UniformVariable<>(
        this, 
        new Matrix4fUniformStrategy(), 
        TerrainShaderProgram.uniformViewProjectionMatrix
    );

    private final UniformVariable<Matrix4f> viewRotationMatrix = new UniformVariable<>(
        this, 
        new Matrix4fUniformStrategy(), 
        TerrainShaderProgram.uniformViewRotationMatrix
    );

    private final UniformVariable<Matrix4f> viewTranslationMatrix = new UniformVariable<>(
        this, 
        new Matrix4fUniformStrategy(), 
        TerrainShaderProgram.uniformViewTranslationMatrix
    );

    private final UniformVariable<Matrix4f> modelMatrix = new UniformVariable<>(
        this, 
        new Matrix4fUniformStrategy(), 
        TerrainShaderProgram.uniformModelMatrix
    );

    private final UniformVariable<Vector4fc> clip = new UniformVariable<>(
        this, 
        new Vector4fUniformStrategy(), 
        TerrainShaderProgram.uniformClipPlane
    );

    private final UniformVariable<Boolean> isLightingEnabled = new UniformVariable<>(
        this, 
        new BooleanUniformStrategy(), 
        TerrainShaderProgram.uniformIsLightingEnabled
    );

    private final UniformVariable<Boolean> isFogEnabled = new UniformVariable<>(
        this, 
        new BooleanUniformStrategy(), 
        TerrainShaderProgram.uniformIsFogEnabled
    );

    private final UniformVariable<Boolean> canSeeHiddenBlocks = new UniformVariable<>(
        this, 
        new BooleanUniformStrategy(), 
        TerrainShaderProgram.uniformCanSeeHiddenBlocks
    );

    private final UniformVariable<Float> fogStartDistance = new UniformVariable<>(
        this, 
        new FloatUniformStrategy(), 
        TerrainShaderProgram.uniformFogStartDistance
    );

    private final UniformVariable<Float> fogRange = new UniformVariable<>(
        this, 
        new FloatUniformStrategy(), 
        TerrainShaderProgram.uniformFogRange
    );

    private final AnimationFrameUniformVariable animationFrame = new AnimationFrameUniformVariable(this);
    private final ArrayList<UniformVariable<Matrix4f>> transformMatrices = new ArrayList<>();
    private final ArrayList<UniformVariable<Vector3fc>> shaderProgramColors = new ArrayList<>();
    private final ArrayList<UniformVariable<Integer>> samplers = new ArrayList<>();

    private final TransformerStrategy transformerStrategy = new TransformerStrategy(
        new InternalTransformerStrategyProvider()
    );

    private final CameraStrategy cameraStrategy = new CameraStrategy(
        new InternalCameraStrategyProvider()
    );

    private final ModelStrategy modelStrategy = new ModelStrategy(
        new InternalModelStrategyProvider()
    );

    private final ClipStrategy clipStrategy = new ClipStrategy(
        new InternalClipStrategyProvider()
    );

    public TerrainShaderProgram() {
        super(TerrainShaderProgram.vertexPath, TerrainShaderProgram.fragmentPath);


        for(var ix = 0; ix < TerrainShaderProgram.numTransformers; ix += 1) {
            this.transformMatrices.add(new UniformVariable<>(this, new Matrix4fUniformStrategy(), TerrainShaderProgram.uniformTransformerMatrix, ix));
        }

        for(var ix = 0; ix < ShaderProgramColor.size(); ix += 1) {
            var shaderLightColor = ShaderProgramColor.get(ix);
            this.shaderProgramColors.add(
                new UniformVariable<>(
                    this,
                    new Vector3fUniformStrategy(),
                    shaderLightColor.name
                )
            );
        }

        for(var ix = 0; ix < ShaderProgramSampler.size(); ix += 1) {
            var sampler = ShaderProgramSampler.get(ix);
            this.samplers.add(
                new UniformVariable<>(
                    this, 
                    new IntegerUniformStrategy(), 
                    sampler.samplerName
                )
            );
        }
    }

    public void setColor(ShaderProgramColor channel, Vector3fc lightColor) {
        this.shaderProgramColors.get(channel.getIndex()).setValue(lightColor);
    }

    public void setAnimationFrame(int frame) {
        this.animationFrame.setValue(frame);
    }

    public void setCamera(Vector3fc position, Vector3fc rotation) {
        this.cameraStrategy.setCamera(position, rotation);
    }

    public void setModel(Vector3fc position, float yaw) {
        this.modelStrategy.setModel(position, yaw);
    }

    public void setClip(Vector3fc position, Vector3fc normal) {
        this.clipStrategy.setClip(position, normal);
    }

    public void setTransformer(int index, Vector3fc position, float yaw) {
        this.transformerStrategy.setTransformer(index, position, yaw);
    }

    public void setEnableLighting(boolean isLightingEnabled) {
        this.isLightingEnabled.setValue(isLightingEnabled);
    }

    public void setShowHiddenBlocks(boolean canSeeHiddenBlocks) {
        this.canSeeHiddenBlocks.setValue(canSeeHiddenBlocks);
    }

    public void setEnableFog(boolean isFogEnabled) {
        this.isFogEnabled.setValue(isFogEnabled);
    }

    public void setFog(float startDistance, float range) {
        this.fogStartDistance.setValue(startDistance);
        this.fogRange.setValue(range);
    }

    @Override
    public void setup() {
        super.setup();

        try(var shaderProgramState = new ShaderProgramConfig()) {
            shaderProgramState.setState(this);
            this.setTransformer(0, Vector3fMaths.zero, 0f);
            for(var ix = 0; ix < ShaderProgramSampler.size(); ix += 1) {
                var sampler = ShaderProgramSampler.get(ix);
                var uniform = this.samplers.get(ix);
                uniform.setValue(sampler.textureUnit.getIndex());
            }
        }
    }
    
}
