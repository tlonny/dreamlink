package doors.core.graphics;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL42;
import org.lwjgl.system.MemoryUtil;

import doors.core.graphics.texture.TextureChannel;
import doors.core.utility.FileIO;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.IVector3fl;

import java.nio.FloatBuffer;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Shader {
    
    private static String SHADER_DIRECTORY = "data/shader";

    public static Shader SHADER = new Shader();

    private FloatBuffer matrixBuffer = MemoryUtil.memAllocFloat(16);
    private Matrix4f workingMatrix = new Matrix4f();

    private int programID;
    private int viewRotationMatrixUniformID;
    private int viewTranslationMatrixUniformID;
    private int viewProjectionMatrixUniformID;
    private int modelMatrixUniformID;
    private int colorUniformID;

    private Map<String,Integer> samplerUniformIDMap;

    public Shader() {
        this.samplerUniformIDMap = new HashMap<>();
    }

    public void setup() {
        this.programID = GL42.glCreateProgram();

        var vertexID = GL42.glCreateShader(GL42.GL_VERTEX_SHADER);
        var vertexShaderPath = Paths.get(SHADER_DIRECTORY, "vertex.glsl").toString();
        this.attachShader(vertexID, FileIO.loadText(vertexShaderPath));

        var fragmentID = GL42.glCreateShader(GL42.GL_FRAGMENT_SHADER);
        var fragmentShaderPath = Paths.get(SHADER_DIRECTORY, "fragment.glsl").toString();
        this.attachShader(fragmentID, FileIO.loadText(fragmentShaderPath));

        GL42.glLinkProgram(this.programID);
        GL42.glValidateProgram(this.programID);

        this.viewProjectionMatrixUniformID = this.createUniform("projection_matrix");
        this.viewRotationMatrixUniformID = this.createUniform("view_rotation_matrix");
        this.viewTranslationMatrixUniformID = this.createUniform("view_translation_matrix");
        this.modelMatrixUniformID = this.createUniform("model_matrix");
        this.colorUniformID = this.createUniform("color");

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

    public void setModelMatrix(IVector3fl position, IVector3fl rotation, IVector3fl scale) {
        workingMatrix
            .identity()
            .translate(position.getFloatX(), position.getFloatY(), position.getFloatZ())
            .rotateY(rotation.getFloatY())
            .rotateX(rotation.getFloatX())
            .rotateZ(rotation.getFloatZ())
            .scale(scale.getFloatX(), scale.getFloatY(), scale.getFloatZ());

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

    public void setColor(Vector3fl color) {
        this.setUniform(this.colorUniformID, color);
    }

    protected void setUniform(int uniformID, int value) {
        GL42.glUniform1i(uniformID, value);
    }

    protected void setUniform(int uniformID, Vector3fl value) {
        GL42.glUniform3f(uniformID, value.x, value.y, value.z);
    }

    protected void setUniform(int uniformID, IVector3fl value) {
        GL42.glUniform3f(uniformID, value.getFloatX(), value.getFloatY(), value.getFloatZ());
    }

    protected void setUniform(int uniformID, Matrix4f value) {
        matrixBuffer.clear();
        value.get(matrixBuffer);
        GL42.glUniformMatrix4fv(uniformID, false, matrixBuffer);
    }
}

