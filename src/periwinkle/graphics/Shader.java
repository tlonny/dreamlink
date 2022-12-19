package periwinkle.graphics;

import periwinkle.utility.File;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Shader {

    private static String VERTEX_SRC = "src/glsl/vertex.glsl";
    private static String FRAGMENT_SRC = "src/glsl/fragment.glsl";

    private int programID;
    private int viewRotationMatrixUniformID;
    private int viewTranslationMatrixUniformID;
    private int projectionMatrixUniformID;
    private int modelMatrixUniformID;
    private int textureSamplerUniformID;
    private int globalColorUniformID;
    private int globalLightDirectionFactorUniformID;

    public void setup() {
        this.programID = GL20.glCreateProgram();
        this.createShader(File.FILE.readStringFromFile(VERTEX_SRC), GL20.GL_VERTEX_SHADER);
        this.createShader(File.FILE.readStringFromFile(FRAGMENT_SRC), GL20.GL_FRAGMENT_SHADER);
        GL20.glLinkProgram(this.programID);
        GL20.glValidateProgram(this.programID);

        this.projectionMatrixUniformID = this.createUniform("projection_matrix");
        this.viewRotationMatrixUniformID = this.createUniform("view_rotation_matrix");
        this.viewTranslationMatrixUniformID = this.createUniform("view_translation_matrix");
        this.modelMatrixUniformID = this.createUniform("model_matrix");
        this.textureSamplerUniformID = this.createUniform("texture_sampler");
        this.globalColorUniformID = this.createUniform("global_color");
        this.globalLightDirectionFactorUniformID = this.createUniform("global_light_direction_factor");

        GL20.glUseProgram(this.programID);
        this.setUniform(this.textureSamplerUniformID, 0);
    }

    private int createUniform(String uniformName) {
        return GL20.glGetUniformLocation(this.programID, uniformName);
    }

    private void setUniform(int uniformID, int value) {
        GL20.glUniform1i(uniformID, value);
    }

    private void setUniform(int uniformID, Vector3f value) {
        GL20.glUniform3f(uniformID, value.x, value.y, value.z);
    }

    private final FloatBuffer setUniformMatrixBuffer = MemoryUtil.memAllocFloat(16);
    private void setUniform(int uniformID, Matrix4f value) {
        this.setUniformMatrixBuffer.clear();
        value.get(this.setUniformMatrixBuffer);
        GL20.glUniformMatrix4fv(uniformID, false, this.setUniformMatrixBuffer);
    }

    private void setUniform(int uniformID, float value) {
        GL20.glUniform1f(uniformID, value);
    }

    private void createShader(String shaderCode, int shaderType) {
        var shaderID = GL20.glCreateShader(shaderType);
        GL20.glShaderSource(shaderID, shaderCode);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0) {
            System.err.println(GL20.glGetShaderInfoLog(shaderID));
            throw new RuntimeException("Shader compilation failed");
        }
        GL20.glAttachShader(this.programID, shaderID);
    }

    public void setGlobalColor(Vector3f colors) {
        this.setUniform(this.globalColorUniformID, colors);
    }

    public void setGlobalLightDirectionFactor(float factor) {
        this.setUniform(this.globalLightDirectionFactorUniformID, factor);
    }

    public void setProjectionMatrix(Matrix4f matrix) {
        this.setUniform(this.projectionMatrixUniformID, matrix);
    }

    public void setViewRotationMatrix(Matrix4f matrix) {
        this.setUniform(this.viewRotationMatrixUniformID, matrix);
    }

    public void setViewTranslationMatrix(Matrix4f matrix) {
        this.setUniform(this.viewTranslationMatrixUniformID, matrix);
    }

    public void setModelMatrix(Matrix4f matrix) {
        this.setUniform(this.modelMatrixUniformID, matrix);
    }
}
