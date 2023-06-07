package doors.graphics;

import org.lwjgl.opengl.GL42;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    private static Mesh BOUND_MESH = null;

    private static int POSITION_LOCATION = 0;
    private static int NORMAL_LOCATION = 1;
    private static int TEXTURE_OFFSET_LOCATION = 2;
    private static int PACKED_COLOR_TEXTURE_UNIT_LOCATION = 3;

    private int vertexArrayID;
    private int indexVertexBufferID;
    private int positionVertexBufferID;
    private int normalVertexBufferID;
    private int textureOffsetVertexBufferID;
    private int packedColorTextureUnitBufferID;

    public int numIndices;

    public void setup() {
        this.vertexArrayID = GL42.glGenVertexArrays();
        this.bind();

        this.indexVertexBufferID = GL42.glGenBuffers();
        this.positionVertexBufferID = GL42.glGenBuffers();
        this.normalVertexBufferID = GL42.glGenBuffers();
        this.textureOffsetVertexBufferID = GL42.glGenBuffers();
        this.packedColorTextureUnitBufferID = GL42.glGenBuffers();
    }

    private void bind() {
        if(this != BOUND_MESH) {
            GL42.glBindVertexArray(this.vertexArrayID);
            BOUND_MESH = this;
        }
    }

    private static void loadIndexData(int vertexBufferID, IntBuffer buffer) {
        GL42.glBindBuffer(GL42.GL_ELEMENT_ARRAY_BUFFER, vertexBufferID);
        GL42.glBufferData(GL42.GL_ELEMENT_ARRAY_BUFFER, buffer, GL42.GL_DYNAMIC_DRAW);
    }

    private static void loadIntData(int location, int size, int vertexBufferID, IntBuffer buffer) {
        GL42.glBindBuffer(GL42.GL_ARRAY_BUFFER, vertexBufferID);
        GL42.glBufferData(GL42.GL_ARRAY_BUFFER, buffer, GL42.GL_DYNAMIC_DRAW);
        GL42.glVertexAttribIPointer(location, size, GL42.GL_INT, 0, 0);
        GL42.glEnableVertexAttribArray(location);
    }

    private static void loadFloatData(int location, int size, int vertexBufferID, FloatBuffer buffer) {
        GL42.glBindBuffer(GL42.GL_ARRAY_BUFFER, vertexBufferID);
        GL42.glBufferData(GL42.GL_ARRAY_BUFFER, buffer, GL42.GL_DYNAMIC_DRAW);
        GL42.glVertexAttribPointer(location, size, GL42.GL_FLOAT, false, 0, 0);
        GL42.glEnableVertexAttribArray(location);
    }

    public void render() {
        this.bind();

        if(this.numIndices == 0) {
            return;
        }

        GL42.glDrawElements(GL42.GL_TRIANGLES, this.numIndices, GL42.GL_UNSIGNED_INT, 0);
    }

    public void loadFromMeshBuffer(MeshBuffer buffer) {
        this.bind();
        loadIndexData(this.indexVertexBufferID, buffer.indexBuffer);
        loadFloatData(POSITION_LOCATION, 3, this.positionVertexBufferID, buffer.positionBuffer);
        loadFloatData(NORMAL_LOCATION, 3, this.normalVertexBufferID, buffer.normalBuffer);
        loadFloatData(TEXTURE_OFFSET_LOCATION, 2, this.textureOffsetVertexBufferID, buffer.textureOffsetBuffer);
        loadIntData(PACKED_COLOR_TEXTURE_UNIT_LOCATION, 1, this.packedColorTextureUnitBufferID, buffer.packedColorTextureUnitBuffer);

        this.numIndices = buffer.indexCount;
    }

}

