package doors.graphics;

import org.joml.Vector2i;
import org.joml.Vector3f;
import org.json.JSONObject;
import org.lwjgl.opengl.GL42;

import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.Maths;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    private static Mesh BOUND_MESH = null;
    private static int MAX_QUADS = 1_000;
    private static MeshBuffer MESH_BUFFER = new MeshBuffer(MAX_QUADS);

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

    public void loadFromFile(String path) {
        var modelString = FileIO.loadText(path);
        var fragments = new JSONObject(modelString).getJSONArray("fragments");

        var positionBuffer = new Vector3f[4];
        for(var ix = 0; ix < positionBuffer.length; ix += 1) {
            positionBuffer[ix] = new Vector3f();
        }

        for(var fragIX = 0; fragIX < fragments.length(); fragIX += 1) {
            var fragment = fragments.getJSONObject(fragIX);

            var textureStr = fragment.getString("texture");
            var texture = Texture.TEXTURE_DATA_LOOKUP.get(textureStr);

            var dimensionsArray = fragment.getJSONArray("dimensions");

            var dimensions = new Vector3f(
                dimensionsArray.getFloat(0),
                dimensionsArray.getFloat(1),
                dimensionsArray.getFloat(2)
            );

            var originArray = fragment.getJSONArray("origin");
            var origin = new Vector3f(
                originArray.getFloat(0),
                originArray.getFloat(1),
                originArray.getFloat(2)
            );

            var allTextureSamples = fragment.getJSONObject("texture_samples");

            for(var cubeFace : CubeFace.CUBE_FACES) {
                var textureSampleOffsets = allTextureSamples.getJSONArray(cubeFace.name);
                for(var ix = 0; ix < cubeFace.vertices.length; ix += 1) {
                    positionBuffer[ix] = cubeFace.vertices[ix].mul(dimensions).sub(origin);
                }
                var textureSample = texture.createTextureSample(
                    new Vector2i(textureSampleOffsets.getInt(0), textureSampleOffsets.getInt(1)),
                    new Vector2i(textureSampleOffsets.getInt(2), textureSampleOffsets.getInt(3))
                );
                MESH_BUFFER.pushQuad(positionBuffer, cubeFace, textureSample, Maths.VEC3F_ONE);
            }

        }
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

