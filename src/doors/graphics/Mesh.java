package doors.graphics;

import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    private static int POSITION_LOCATION = 0;
    private static int NORMAL_LOCATION = 1;
    private static int TEXTURE_LOCATION = 2;

    private int vertexArrayID;
    private int indexVertexBufferID;
    private int positionVertexBufferID;
    private int normalVertexBufferID;
    private int textureVertexBufferID;
    private int numIndices;

    public void setup() {
        this.vertexArrayID = GL30.glGenVertexArrays();
        this.bind();
        this.indexVertexBufferID = GL30.glGenBuffers();
        this.positionVertexBufferID = GL30.glGenBuffers();
        this.normalVertexBufferID = GL30.glGenBuffers();
        this.textureVertexBufferID = GL30.glGenBuffers();
    }

    private void bind() {
        GL30.glBindVertexArray(this.vertexArrayID);
    }

    private void pushIndexData(IntBuffer buffer) {
        GL30.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.indexVertexBufferID);
        GL30.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
    }

    private void pushFloatData(int location, int size, int vertexBufferID, FloatBuffer buffer) {
        GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
        GL30.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
        GL30.glVertexAttribPointer(location, size, GL11.GL_FLOAT, false, 0, 0);
        GL30.glEnableVertexAttribArray(location);
    }

    public void loadFromMeshBuffer(MeshBuffer buffer) {
        this.bind();
        this.pushIndexData(buffer.indexBuffer);
        this.pushFloatData(POSITION_LOCATION, 3, this.positionVertexBufferID, buffer.positionBuffer);
        this.pushFloatData(NORMAL_LOCATION, 3, this.normalVertexBufferID, buffer.normalBuffer);
        this.pushFloatData(TEXTURE_LOCATION, 2, this.textureVertexBufferID, buffer.textureOffsetBuffer);
        this.numIndices = buffer.indexCount;
    }

    public void tearDown() {
        GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glDeleteBuffers(this.indexVertexBufferID);
        GL30.glDeleteBuffers(this.positionVertexBufferID);
        GL30.glDeleteBuffers(this.textureVertexBufferID);
        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays(this.vertexArrayID);
    }

    public void render() {
        if(this.numIndices == 0)
            return;
        this.bind();
        GL30.glDrawElements(GL11.GL_TRIANGLES, this.numIndices, GL11.GL_UNSIGNED_INT, 0);
    }

}

