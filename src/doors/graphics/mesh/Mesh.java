package doors.graphics.mesh;

import org.lwjgl.opengl.GL42;

import doors.graphics.Shader;
import doors.utility.geometry.Vector3fl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    private static Mesh USED_MESH = null;

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
    public boolean cullFaces;

    public Mesh() {
        this.cullFaces = true;
    }

    public void setup() {
        this.vertexArrayID = GL42.glGenVertexArrays();
        this.use();

        this.indexVertexBufferID = GL42.glGenBuffers();
        this.positionVertexBufferID = GL42.glGenBuffers();
        this.normalVertexBufferID = GL42.glGenBuffers();
        this.textureOffsetVertexBufferID = GL42.glGenBuffers();
        this.packedColorTextureUnitBufferID = GL42.glGenBuffers();
    }


    private void use() {
        if(this == USED_MESH || this.vertexArrayID == 0) {
            return;
        }

        if(this.cullFaces) {
            GL42.glEnable(GL42.GL_CULL_FACE);
        } else {
            GL42.glDisable(GL42.GL_CULL_FACE);
        }

        GL42.glBindVertexArray(this.vertexArrayID);

        USED_MESH = this;
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

    public void loadFromMeshBuffer(MeshBuffer buffer) {
        this.use();
        loadIndexData(this.indexVertexBufferID, buffer.indexBuffer);
        loadFloatData(POSITION_LOCATION, 3, this.positionVertexBufferID, buffer.positionBuffer);
        loadFloatData(NORMAL_LOCATION, 3, this.normalVertexBufferID, buffer.normalBuffer);
        loadFloatData(TEXTURE_OFFSET_LOCATION, 2, this.textureOffsetVertexBufferID, buffer.textureOffsetBuffer);
        loadIntData(PACKED_COLOR_TEXTURE_UNIT_LOCATION, 1, this.packedColorTextureUnitBufferID, buffer.packedColorTextureUnitBuffer);
        this.numIndices = buffer.indexCount;
    }

    public void render(Vector3fl position, Vector3fl rotation, Vector3fl scale, Vector3fl color) {
        this.use();
        Shader.SHADER.setModelMatrix(position, rotation, scale);
        Shader.SHADER.setColor(color);
        GL42.glDrawElements(GL42.GL_TRIANGLES, this.numIndices, GL42.GL_UNSIGNED_INT, 0);
    }

}

