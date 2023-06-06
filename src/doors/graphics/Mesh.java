package doors.graphics;

import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    public static Mesh BOUND_MESH = null;

    private static int POSITION_LOCATION = 0;
    private static int NORMAL_LOCATION = 1;
    private static int TEXTURE_OFFSET_LOCATION = 2;

    private int vertexArrayID;
    private int indexVertexBufferID;
    private int positionVertexBufferID;
    private int normalVertexBufferID;
    private int textureOffsetVertexBufferID;

    public int numIndices;

    public void setup() {
        this.vertexArrayID = GL30.glGenVertexArrays();
        this.bind();

        this.indexVertexBufferID = GL30.glGenBuffers();
        this.positionVertexBufferID = GL30.glGenBuffers();
        this.normalVertexBufferID = GL30.glGenBuffers();
        this.textureOffsetVertexBufferID = GL30.glGenBuffers();
    }

    public void bind() {
        if(this != BOUND_MESH) {
            GL30.glBindVertexArray(this.vertexArrayID);
            BOUND_MESH = this;
        }
    }

    public static void loadIndexData(int vertexBufferID, IntBuffer buffer) {
        GL30.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vertexBufferID);
        GL30.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
    }

    private static void loadFloatData(int location, int size, int vertexBufferID, FloatBuffer buffer) {
        GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
        GL30.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
        GL30.glVertexAttribPointer(location, size, GL11.GL_FLOAT, false, 0, 0);
        GL30.glEnableVertexAttribArray(location);
    }

    public static void render() {
        if(BOUND_MESH.numIndices == 0) {
            return;
        }

        GL30.glDrawElements(GL11.GL_TRIANGLES, BOUND_MESH.numIndices, GL11.GL_UNSIGNED_INT, 0);
    }

    public static void loadFromMeshBuffer(IMeshBuffer buffer) {
        loadIndexData(BOUND_MESH.indexVertexBufferID, buffer.getIndexBuffer());
        loadFloatData(POSITION_LOCATION, 3, BOUND_MESH.positionVertexBufferID, buffer.getPositionBuffer());
        loadFloatData(NORMAL_LOCATION, 3, BOUND_MESH.normalVertexBufferID, buffer.getNormalBuffer());
        loadFloatData(TEXTURE_OFFSET_LOCATION, 2, BOUND_MESH.textureOffsetVertexBufferID, buffer.getTextureOffsetBuffer());
        BOUND_MESH.numIndices = buffer.getIndexCount();
    }

}

