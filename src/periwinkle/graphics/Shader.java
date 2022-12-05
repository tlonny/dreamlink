package periwinkle.graphics;

import periwinkle.utility.File;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Shader {

    public static Shader SHADER = new Shader(
        "src/glsl/vertex.glsl",
        "src/glsl/fragment.glsl"
    );

    public static void init() {
        SHADER.setup();
    }

    private int programID;
    private int viewRotationMatrixUniformID;
    private int viewTranslationMatrixUniformID;
    private int projectionMatrixUniformID;
    private int modelMatrixUniformID;
    private int textureSamplerUniformID;
    private int globalColorUniformID;
    private int globalLightDirectionFactorUniformID;

    private final String vertexSrc;
    private final String fragmentSrc;

    public Shader(String vertexSrc, String fragmentSrc) {
        this.vertexSrc = vertexSrc;
        this.fragmentSrc = fragmentSrc;
    }

    public void setup() {
        this.programID = GL20.glCreateProgram();
        this.createShader(File.FILE.readStringFromFile(this.vertexSrc), GL20.GL_VERTEX_SHADER);
        this.createShader(File.FILE.readStringFromFile(this.fragmentSrc), GL20.GL_FRAGMENT_SHADER);
        GL20.glLinkProgram(this.programID);
        GL20.glValidateProgram(this.programID);

        this.projectionMatrixUniformID = this.createUniform("projection_Matrix");
        this.viewRotationMatrixUniformID = this.createUniform("view_Rotation_Matrix");
        this.viewTranslationMatrixUniformID = this.createUniform("view_Translation_Matrix");
        this.modelMatrixUniformID = this.createUniform("model_Matrix");
        this.textureSamplerUniformID = this.createUniform("texture_Sampler");
        this.globalColorUniformID = this.createUniform("global_Color");
        this.globalLightDirectionFactorUniformID = this.createUniform("global_Light_Direction_Factor");
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

    public void setTextureSampler() {
        // We are sampling TEX0.
        this.setUniform(this.textureSamplerUniformID, 0);
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

    public void useProgram() {
        GL20.glUseProgram(this.programID);
    }

}
