package doors.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import doors.utility.FileIO;

import java.nio.FloatBuffer;
import java.nio.file.Paths;

public class ShaderProgram {

    public static ShaderProgram BOUND_SHADER_PROGRAM = null;
    private static FloatBuffer MATRIX_BUFFER = MemoryUtil.memAllocFloat(16);
    private static Matrix4f MATRIX = new Matrix4f();

    private int programID;

    private int viewRotationMatrixUniformID;
    private int viewTranslationMatrixUniformID;
    private int viewProjectionMatrixUniformID;
    private int modelMatrixUniformID;
    private int colorUniformID;
    private int textureSamplerUniformID;

    public void setup(String shaderDirectory) {
        this.programID = GL20.glCreateProgram();

        var vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        var vertexShaderPath = Paths.get(shaderDirectory, "vertex.glsl").toString();
        this.attachShader(vertexID, FileIO.loadText(vertexShaderPath));

        var fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        var fragmentShaderPath = Paths.get(shaderDirectory, "fragment.glsl").toString();
        this.attachShader(fragmentID, FileIO.loadText(fragmentShaderPath));

        GL20.glLinkProgram(this.programID);
        GL20.glValidateProgram(this.programID);

        this.viewProjectionMatrixUniformID = this.createUniform("projection_matrix");
        this.viewRotationMatrixUniformID = this.createUniform("view_rotation_matrix");
        this.viewTranslationMatrixUniformID = this.createUniform("view_translation_matrix");
        this.modelMatrixUniformID = this.createUniform("model_matrix");
        this.colorUniformID = this.createUniform("color");
        this.textureSamplerUniformID = this.createUniform("texture_sampler");
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

    public void bind() {
        if(BOUND_SHADER_PROGRAM != this) {
            GL20.glUseProgram(this.programID);
            BOUND_SHADER_PROGRAM = this;
        }
    }

    public static void setModel(Vector3f position, Vector3f color) {
        MATRIX.identity().translate(position);
        setUniform(BOUND_SHADER_PROGRAM.modelMatrixUniformID, MATRIX);
        setUniform(BOUND_SHADER_PROGRAM.colorUniformID, color);
    }

    public static void setView(IView view) {
        setUniform(BOUND_SHADER_PROGRAM.textureSamplerUniformID, 0);

        view.writeViewProjectionMatrix(MATRIX);
        setUniform(BOUND_SHADER_PROGRAM.viewProjectionMatrixUniformID, MATRIX);

        view.writeViewRotationMatrix(MATRIX);
        setUniform(BOUND_SHADER_PROGRAM.viewRotationMatrixUniformID, MATRIX);

        view.writeViewTranslationMatrix(MATRIX);
        setUniform(BOUND_SHADER_PROGRAM.viewTranslationMatrixUniformID, MATRIX);
    }

    private int createUniform(String uniformName) {
        return GL20.glGetUniformLocation(this.programID, uniformName);
    }

    private static void setUniform(int uniformID, int value) {
        GL20.glUniform1i(uniformID, value);
    }

    private static void setUniform(int uniformID, Vector3f value) {
        GL20.glUniform3f(uniformID, value.x, value.y, value.z);
    }

    private static void setUniform(int uniformID, Matrix4f value) {
        MATRIX_BUFFER.clear();
        value.get(MATRIX_BUFFER);
        GL20.glUniformMatrix4fv(uniformID, false, MATRIX_BUFFER);
    }
}

