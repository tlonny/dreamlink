package doors.graphics.mesh;

import org.lwjgl.opengl.GL42;

import doors.graphics.shader.Shader;
import doors.utility.vector.IVector3fl;
import doors.utility.vector.Vector3fl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    private static Mesh USED_MESH = null;

    private int POSITION_LOCATION = 0;
    private int NORMAL_LOCATION = 1;
    private int TEXTURE_OFFSET_LOCATION = 2;
    private int PACKED_COLOR_LOCATION = 3;
    private int PACKED_LOOKUP_IDS_LOCATION = 4;

    private int vertexArrayID;
    private int indexVertexBufferID;
    private int positionVertexBufferID;
    private int normalVertexBufferID;
    private int textureOffsetVertexBufferID;
    private int packedColorBufferID;
    private int packedLookupIDsBufferID;
    private int numIndices;

    public boolean cullFaces = true;
    public TextureSampleMode textureSampleMode = TextureSampleMode.NORMAL;

    public void setup() {
        this.vertexArrayID = GL42.glGenVertexArrays();
        this.useMesh();

        this.indexVertexBufferID = GL42.glGenBuffers();
        this.positionVertexBufferID = GL42.glGenBuffers();
        this.normalVertexBufferID = GL42.glGenBuffers();
        this.textureOffsetVertexBufferID = GL42.glGenBuffers();
        this.packedColorBufferID = GL42.glGenBuffers();
        this.packedLookupIDsBufferID = GL42.glGenBuffers();
    }


    private void useMesh() {
        if(this == USED_MESH) {
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

    private void loadIndexData(int vertexBufferID, IntBuffer buffer) {
        buffer.flip();
        GL42.glBindBuffer(GL42.GL_ELEMENT_ARRAY_BUFFER, vertexBufferID);
        GL42.glBufferData(GL42.GL_ELEMENT_ARRAY_BUFFER, buffer, GL42.GL_DYNAMIC_DRAW);
    }

    private void loadIntData(int location, int size, int vertexBufferID, IntBuffer buffer) {
        buffer.flip();
        GL42.glBindBuffer(GL42.GL_ARRAY_BUFFER, vertexBufferID);
        GL42.glBufferData(GL42.GL_ARRAY_BUFFER, buffer, GL42.GL_DYNAMIC_DRAW);
        GL42.glVertexAttribIPointer(location, size, GL42.GL_INT, 0, 0);
        GL42.glEnableVertexAttribArray(location);
    }

    private void loadFloatData(int location, int size, int vertexBufferID, FloatBuffer buffer) {
        buffer.flip();
        GL42.glBindBuffer(GL42.GL_ARRAY_BUFFER, vertexBufferID);
        GL42.glBufferData(GL42.GL_ARRAY_BUFFER, buffer, GL42.GL_DYNAMIC_DRAW);
        GL42.glVertexAttribPointer(location, size, GL42.GL_FLOAT, false, 0, 0);
        GL42.glEnableVertexAttribArray(location);
    }

    public void loadDataFromMeshBuffer(MeshBuffer meshBuffer) {
        this.useMesh();
        this.numIndices = meshBuffer.getNumIndices();
        this.loadIndexData(this.indexVertexBufferID, meshBuffer.indexBuffer);
        this.loadFloatData(POSITION_LOCATION, 3, this.positionVertexBufferID, meshBuffer.positionBuffer);
        this.loadFloatData(NORMAL_LOCATION, 3, this.normalVertexBufferID, meshBuffer.normalBuffer);
        this.loadFloatData(TEXTURE_OFFSET_LOCATION, 2, this.textureOffsetVertexBufferID, meshBuffer.textureOffsetBuffer);
        this.loadIntData(PACKED_COLOR_LOCATION, 1, this.packedColorBufferID, meshBuffer.packedColorBuffer);
        this.loadIntData(PACKED_LOOKUP_IDS_LOCATION, 1, this.packedLookupIDsBufferID, meshBuffer.packedLookupIDsBuffer);
    }

    public void render(IVector3fl position, Vector3fl rotation, Vector3fl scale, Vector3fl color) {
        this.useMesh();
        Shader.SHADER.setTextureSampleMode(this.textureSampleMode);
        Shader.SHADER.setModelMatrix(position, rotation, scale);
        Shader.SHADER.setColor(color);
        GL42.glDrawElements(GL42.GL_TRIANGLES, this.numIndices, GL42.GL_UNSIGNED_INT, 0);
    }

    public void render(IVector3fl position, Vector3fl rotation, Vector3fl scale) {
        this.render(position, rotation, scale, Vector3fl.WHITE);
    }

    public void render(IVector3fl position, Vector3fl rotation) {
        this.render(position, rotation, Vector3fl.ONE);
    }

    public void render() {
        this.render(
            Vector3fl.ZERO,
            Vector3fl.ZERO,
            Vector3fl.ONE,
            Vector3fl.WHITE
        );
    }
}

