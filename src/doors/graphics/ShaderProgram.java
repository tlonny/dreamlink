package doors.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL42;
import org.lwjgl.system.MemoryUtil;

import doors.perspective.IView;
import doors.utility.FileIO;

import java.nio.FloatBuffer;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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

    private Map<String,Integer> samplerUniformIDMap;

    public void setup(String shaderDirectory) {
        this.programID = GL42.glCreateProgram();

        var vertexID = GL42.glCreateShader(GL42.GL_VERTEX_SHADER);
        var vertexShaderPath = Paths.get(shaderDirectory, "vertex.glsl").toString();
        this.attachShader(vertexID, FileIO.loadText(vertexShaderPath));

        var fragmentID = GL42.glCreateShader(GL42.GL_FRAGMENT_SHADER);
        var fragmentShaderPath = Paths.get(shaderDirectory, "fragment.glsl").toString();
        this.attachShader(fragmentID, FileIO.loadText(fragmentShaderPath));

        GL42.glLinkProgram(this.programID);
        GL42.glValidateProgram(this.programID);

        this.viewProjectionMatrixUniformID = this.createUniform("projection_matrix");
        this.viewRotationMatrixUniformID = this.createUniform("view_rotation_matrix");
        this.viewTranslationMatrixUniformID = this.createUniform("view_translation_matrix");
        this.modelMatrixUniformID = this.createUniform("model_matrix");
        this.colorUniformID = this.createUniform("color");

        this.samplerUniformIDMap = new HashMap<>();
        for(var channel : TextureChannel.TEXTURE_CHANNELS) {
            var uniformName = channel.getSamplerUniformName();
            this.samplerUniformIDMap.put(channel.name, this.createUniform(uniformName));
        }
    }

    private void attachShader(int shaderID, String shaderCode) {
        GL42.glShaderSource(shaderID, shaderCode);
        GL42.glCompileShader(shaderID);
        if(GL42.glGetShaderi(shaderID, GL42.GL_COMPILE_STATUS) == 0) {
            System.err.println(GL42.glGetShaderInfoLog(shaderID));
            throw new RuntimeException("Shader compilation failed");
        }
        GL42.glAttachShader(this.programID, shaderID);
    }

    public void bindShaderProgram() {
        if(BOUND_SHADER_PROGRAM != this) {
            GL42.glUseProgram(this.programID);
            BOUND_SHADER_PROGRAM = this;
        }
    }

    public static void setPerspective(IView view) {
        view.writeViewProjectionMatrix(MATRIX);
        setUniform(BOUND_SHADER_PROGRAM.viewProjectionMatrixUniformID, MATRIX);

        view.writeViewRotationMatrix(MATRIX);
        setUniform(BOUND_SHADER_PROGRAM.viewRotationMatrixUniformID, MATRIX);

        view.writeViewTranslationMatrix(MATRIX);
        setUniform(BOUND_SHADER_PROGRAM.viewTranslationMatrixUniformID, MATRIX);
    }

    public static void setTextureChannels() {
        for(var channel : TextureChannel.TEXTURE_CHANNELS) {
            var uniformID = BOUND_SHADER_PROGRAM.samplerUniformIDMap.get(channel.name);
            setUniform(uniformID, channel.getSamplerUniformValue());
        }
    }

    public static void setModel(Vector3f position, Vector3f color) {
        MATRIX.identity().translate(position);
        setUniform(BOUND_SHADER_PROGRAM.modelMatrixUniformID, MATRIX);
        setUniform(BOUND_SHADER_PROGRAM.colorUniformID, color);
    }

    private int createUniform(String uniformName) {
        return GL42.glGetUniformLocation(this.programID, uniformName);
    }

    private static void setUniform(int uniformID, int value) {
        GL42.glUniform1i(uniformID, value);
    }

    private static void setUniform(int uniformID, Vector3f value) {
        GL42.glUniform3f(uniformID, value.x, value.y, value.z);
    }

    private static void setUniform(int uniformID, Matrix4f value) {
        MATRIX_BUFFER.clear();
        value.get(MATRIX_BUFFER);
        GL42.glUniformMatrix4fv(uniformID, false, MATRIX_BUFFER);
    }
}

