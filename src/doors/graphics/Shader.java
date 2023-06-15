package doors.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL42;
import org.lwjgl.system.MemoryUtil;

import doors.utility.FileIO;

import java.nio.FloatBuffer;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Shader {

    public static Shader SHADER = new Shader("data/shader/core");

    private FloatBuffer matrixBuffer = MemoryUtil.memAllocFloat(16);
    private Matrix4f workingMatrix = new Matrix4f();

    private int programID;
    private String shaderDirectory;

    private int viewRotationMatrixUniformID;
    private int viewTranslationMatrixUniformID;
    private int viewProjectionMatrixUniformID;
    private int modelMatrixUniformID;
    private int colorUniformID;

    private Map<String,Integer> samplerUniformIDMap;

    public Shader(String shaderDirectory) {
        this.shaderDirectory = shaderDirectory;
    }

    public void setup() {
        this.programID = GL42.glCreateProgram();

        var vertexID = GL42.glCreateShader(GL42.GL_VERTEX_SHADER);
        var vertexShaderPath = Paths.get(this.shaderDirectory, "vertex.glsl").toString();
        this.attachShader(vertexID, FileIO.loadText(vertexShaderPath));

        var fragmentID = GL42.glCreateShader(GL42.GL_FRAGMENT_SHADER);
        var fragmentShaderPath = Paths.get(this.shaderDirectory, "fragment.glsl").toString();
        this.attachShader(fragmentID, FileIO.loadText(fragmentShaderPath));

        GL42.glLinkProgram(this.programID);
        GL42.glValidateProgram(this.programID);

        this.viewProjectionMatrixUniformID = this.createUniform("projection_matrix");
        this.viewRotationMatrixUniformID = this.createUniform("view_rotation_matrix");
        this.viewTranslationMatrixUniformID = this.createUniform("view_translation_matrix");
        this.modelMatrixUniformID = this.createUniform("model_matrix");
        this.colorUniformID = this.createUniform("color");

        this.samplerUniformIDMap = new HashMap<>();
        for(var entry : TextureChannel.TEXTURE_CHANNEL_LOOKUP.entrySet()) {
            var channelName = entry.getKey();
            var uniformName = entry.getValue().getSamplerUniformName();
            this.samplerUniformIDMap.put(channelName, this.createUniform(uniformName));
        }

        GL42.glUseProgram(this.programID);
        setTextureChannels();
    }

    private int createUniform(String uniformName) {
        var uniformID =  GL42.glGetUniformLocation(this.programID, uniformName);
        return uniformID;
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

    private void setTextureChannels() {
        for(var entry : TextureChannel.TEXTURE_CHANNEL_LOOKUP.entrySet()) {
            var channelName = entry.getKey();
            var uniformID = this.samplerUniformIDMap.get(channelName);
            var uniformValue = entry.getValue().getSamplerUniformValue();
            this.setUniform(uniformID, uniformValue);
        }
    }

    public void setModelMatrix(Vector3f position, Vector3f rotation, Vector3f scale) {
        workingMatrix
            .identity()
            .translate(position)
            .rotateY(rotation.y)
            .rotateX(rotation.x)
            .rotateZ(rotation.z)
            .scale(scale);

        this.setUniform(this.modelMatrixUniformID, workingMatrix);
    }

    public void setViewTranslationMatrix(Matrix4f matrix) {
        this.setUniform(this.viewTranslationMatrixUniformID, matrix);
    }

    public void setViewRotationMatrix(Matrix4f matrix) {
        this.setUniform(this.viewRotationMatrixUniformID, matrix);
    }

    public void setViewProjectionMatrix(Matrix4f matrix) {
        this.setUniform(this.viewProjectionMatrixUniformID, matrix);
    }

    public void setColor(Vector3f color) {
        this.setUniform(this.colorUniformID, color);
    }

    private void setUniform(int uniformID, int value) {
        GL42.glUniform1i(uniformID, value);
    }

    private void setUniform(int uniformID, Vector3f value) {
        GL42.glUniform3f(uniformID, value.x, value.y, value.z);
    }

    private void setUniform(int uniformID, Matrix4f value) {
        matrixBuffer.clear();
        value.get(matrixBuffer);
        GL42.glUniformMatrix4fv(uniformID, false, matrixBuffer);
    }
}

