package periwinkle.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    private final static int POSITION_LOCATION = 0;
    private final static int NORMAL_LOCATION = 1;
    private final static int LOCAL_LIGHT_LOCATION = 2;
    private final static int GLOBAL_LIGHT_LOCATION = 3;
    private final static int TEXTURE_LOCATION = 4;

    private int vertexArrayID;
    private int indexVertexBufferID;
    private int positionVertexBufferID;
    private int normalVertexBufferID;
    private int localLightVertexBufferID;
    private int globalLightVertexBufferID;
    private int textureVertexBufferID;
    private int numIndices;

    private final Atlas atlas;


    public Mesh(Atlas atlas) {
        this.atlas = atlas;
    }

    public void setup() {
        this.vertexArrayID = GL30.glGenVertexArrays();
        this.bindVAO();
        this.indexVertexBufferID = GL30.glGenBuffers();
        this.localLightVertexBufferID = GL30.glGenBuffers();
        this.globalLightVertexBufferID = GL30.glGenBuffers();
        this.positionVertexBufferID = GL30.glGenBuffers();
        this.normalVertexBufferID = GL30.glGenBuffers();
        this.textureVertexBufferID = GL30.glGenBuffers();
    }

    private void bindVAO() {
        GL30.glBindVertexArray(this.vertexArrayID);
    }

    private void pushIndexData(IntBuffer buffer) {
        GL30.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.indexVertexBufferID);
        GL30.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
    }

    private void pushFloatData(int location, int size, int vertexBufferID, FloatBuffer buffer) {
        GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
        GL30.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
        GL30.glVertexAttribPointer(location, size, GL11.GL_FLOAT, false, 0, 0);;
        GL30.glEnableVertexAttribArray(location);
    }

    public void loadMesh(MeshBuffer buffer) {
        this.bindVAO();
        this.pushIndexData(buffer.indexBuffer);
        this.pushFloatData(POSITION_LOCATION, 3, this.positionVertexBufferID, buffer.positionBuffer);
        this.pushFloatData(NORMAL_LOCATION, 3, this.normalVertexBufferID, buffer.normalBuffer);
        this.pushFloatData(LOCAL_LIGHT_LOCATION, 1, this.localLightVertexBufferID, buffer.localLightBuffer);
        this.pushFloatData(GLOBAL_LIGHT_LOCATION, 1, this.globalLightVertexBufferID, buffer.globalLightBuffer);
        this.pushFloatData(TEXTURE_LOCATION, 2, this.textureVertexBufferID, buffer.textureBuffer);
        this.numIndices = buffer.indexCount;
    }

    public void tearDown() {
        GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glDeleteBuffers(this.indexVertexBufferID);
        GL30.glDeleteBuffers(this.positionVertexBufferID);
        GL30.glDeleteBuffers(this.textureVertexBufferID);
        GL30.glDeleteBuffers(this.localLightVertexBufferID);
        GL30.glDeleteBuffers(this.globalLightVertexBufferID);
        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays(this.vertexArrayID);
    }

    private Matrix4f modelMatrix = new Matrix4f();
    public void render(Vector3f position, Vector3f color, float globalLightDirectionFactor) {
        if(this.numIndices == 0)
            return;
        this.bindVAO();
        this.atlas.bind();
        this.modelMatrix.identity().translate(position);
        Shader.SHADER.setModelMatrix(this.modelMatrix);
        Shader.SHADER.setGlobalColor(color);
        Shader.SHADER.setGlobalLightDirectionFactor(globalLightDirectionFactor);
        GL30.glDrawElements(GL11.GL_TRIANGLES, this.numIndices, GL11.GL_UNSIGNED_INT, 0);
    }

}
