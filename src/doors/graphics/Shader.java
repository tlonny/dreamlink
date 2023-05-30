package doors.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import doors.utility.IO;

import java.nio.FloatBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Shader {

    private static Path VERTEX_SHADER_SRC = Paths.get("src/glsl/vertex.glsl");
    private static Path FRAGMENT_SHADER_SRC = Paths.get("src/glsl/fragment.glsl");

    private final FloatBuffer matrixBuffer = MemoryUtil.memAllocFloat(16);

    private int programID;
    private int viewRotationMatrixUniformID;
    private int viewTranslationMatrixUniformID;
    private int projectionMatrixUniformID;
    private int modelMatrixUniformID;
    private int textureSamplerUniformID;
    private int ambientLightUniformID;

    public Matrix4f viewRotationMatrix = new Matrix4f();
    public Matrix4f viewTranslationMatrix = new Matrix4f();
    public Matrix4f projectionMatrix = new Matrix4f();
    public Matrix4f modelMatrix = new Matrix4f();
    public Vector3f ambientLight = new Vector3f();

    public void setup() {
        this.programID = GL20.glCreateProgram();

        var vertexID = this.createShader(GL20.GL_VERTEX_SHADER);
        this.attachShader(vertexID, IO.loadText(VERTEX_SHADER_SRC));

        var fragmentID = this.createShader(GL20.GL_FRAGMENT_SHADER);
        this.attachShader(fragmentID, IO.loadText(FRAGMENT_SHADER_SRC));

        GL20.glLinkProgram(this.programID);
        GL20.glValidateProgram(this.programID);

        this.projectionMatrixUniformID = this.createUniform("projection_matrix");
        this.viewRotationMatrixUniformID = this.createUniform("view_rotation_matrix");
        this.viewTranslationMatrixUniformID = this.createUniform("view_translation_matrix");
        this.modelMatrixUniformID = this.createUniform("model_matrix");
        this.ambientLightUniformID = this.createUniform("ambient_light");
        this.textureSamplerUniformID = this.createUniform("texture_sampler");

        this.bind();

        this.setUniform(this.textureSamplerUniformID, 0);
    }

    public void bind() {
        GL20.glUseProgram(this.programID);
    }

    private int createShader(int shaderType) {
        return GL20.glCreateShader(shaderType);
    }

    private void attachShader(int shaderID, String shaderCode) {
        GL20.glShaderSource(shaderID, shaderCode);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0) {
            System.err.println(GL20.glGetShaderInfoLog(shaderID));
            throw new RuntimeException("Shader compilation failed");
        }
        GL20.glAttachShader(this.programID, shaderID);
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

    private void setUniform(int uniformID, Matrix4f value) {
        this.matrixBuffer.clear();
        value.get(this.matrixBuffer);
        GL20.glUniformMatrix4fv(uniformID, false, this.matrixBuffer);
    }

    public void writeAmbientLight() {
        this.setUniform(this.ambientLightUniformID, this.ambientLight);
    }

    public void writeProjectionMatrix() {
        this.setUniform(this.projectionMatrixUniformID, this.projectionMatrix);
    }

    public void writeViewRotationMatrix() {
        this.setUniform(this.viewRotationMatrixUniformID, this.viewRotationMatrix);
    }

    public void writeViewTranslationMatrix() {
        this.setUniform(this.viewTranslationMatrixUniformID, this.viewTranslationMatrix);
    }

    public void writeModelMatrix() {
        this.setUniform(this.modelMatrixUniformID, this.modelMatrix);
    }

}

