package doors.graphics.mesh;

import org.lwjgl.system.MemoryUtil;

import doors.graphics.texture.sample.TextureSample;
import doors.utility.CubeFace;
import doors.utility.vector.Vector3fl;
import doors.utility.vector.IVector2fl;
import doors.utility.vector.IVector3fl;
import doors.utility.vector.Vector2fl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MeshBuffer {

    private static int[] QUAD_INDICES = new int[] { 0, 1, 3, 3, 1, 2 };
    private static int DEFAULT_MESH_BUFFER_MAX_QUADS = 50_000;

    public static MeshBuffer DEFAULT_MESH_BUFFER = new MeshBuffer(DEFAULT_MESH_BUFFER_MAX_QUADS);

    private static IVector2fl[] WINDINGS = new IVector2fl[] {
        new Vector2fl(0f, 1f),
        new Vector2fl(0f, 0f),
        new Vector2fl(1f, 0f),
        new Vector2fl(1f, 1f),
    };

    public IntBuffer indexBuffer;
    public FloatBuffer positionBuffer;
    public FloatBuffer normalBuffer;
    public FloatBuffer textureOffsetBuffer;
    public IntBuffer packedColorBuffer;
    public IntBuffer packedLookupIDsBuffer;

    private Vector3fl vertexPositionCursor = new Vector3fl();
    private Vector3fl originPositionCursor = new Vector3fl();

    private int numQuads;

    public MeshBuffer(int quadCapacity) {
        this.indexBuffer = MemoryUtil.memAllocInt(quadCapacity * 6);
        this.positionBuffer = MemoryUtil.memAllocFloat(quadCapacity * 12);
        this.normalBuffer = MemoryUtil.memAllocFloat(quadCapacity * 12);
        this.textureOffsetBuffer = MemoryUtil.memAllocFloat(quadCapacity * 8);
        this.packedColorBuffer = MemoryUtil.memAllocInt(quadCapacity * 4); 
        this.packedLookupIDsBuffer = MemoryUtil.memAllocInt(quadCapacity * 4); 
    }

    public int getNumIndices() {
        return this.numQuads * 6;
    }

    public void tearDown() {
        MemoryUtil.memFree(this.indexBuffer);
        MemoryUtil.memFree(this.positionBuffer);
        MemoryUtil.memFree(this.normalBuffer);
        MemoryUtil.memFree(this.textureOffsetBuffer);
        MemoryUtil.memFree(this.packedColorBuffer);
        MemoryUtil.memFree(this.packedLookupIDsBuffer);
    }

    private int packLookupIDs(int normalisedTextureUnitID, int transformerID) {
        var packed = 0;
        packed += normalisedTextureUnitID;
        packed *= 0x100;
        packed += transformerID;
        return packed;
    }

    private int packColor(Vector3fl color) {
        var packed = 0;
        packed += (int)(color.x * 0xFF);
        packed *= 0x100;
        packed += (int)(color.y * 0xFF);
        packed *= 0x100;
        packed += (int)(color.z * 0xFF);
        return packed;
    }

    public void pushQuad(TextureSample textureSample, int transformerID, IVector3fl position, float rotation, IVector3fl dimensions, CubeFace cubeFace, Vector3fl color) {
        var packedColor = this.packColor(color);
        var packedLookupIDs = this.packLookupIDs(textureSample.textureChannel.getTextureUnitID(), transformerID);

        for(var ix = 0; ix < 4; ix += 1) {
            var winding = WINDINGS[ix];
            if (cubeFace == CubeFace.FRONT) {
                this.vertexPositionCursor.set(
                    position.getFloatX() + dimensions.getFloatX() * winding.getFloatX(),
                    position.getFloatY() + dimensions.getFloatY() * winding.getFloatY(),
                    position.getFloatZ() + dimensions.getFloatZ()
                );
            } else if (cubeFace == CubeFace.BACK) {
                this.vertexPositionCursor.set(
                    position.getFloatX() + dimensions.getFloatX() * (1f - winding.getFloatX()),
                    position.getFloatY() + dimensions.getFloatY() * winding.getFloatY(),
                    position.getFloatZ()
                );
            } else if (cubeFace == CubeFace.RIGHT) {
                this.vertexPositionCursor.set(
                    position.getFloatX(),
                    position.getFloatY() + dimensions.getFloatY() * winding.getFloatY(),
                    position.getFloatZ() + dimensions.getFloatZ() * winding.getFloatX()
                );
            } else if (cubeFace == CubeFace.LEFT) {
                this.vertexPositionCursor.set(
                    position.getFloatX() + dimensions.getFloatX(),
                    position.getFloatY() + dimensions.getFloatY() * winding.getFloatY(),
                    position.getFloatZ() + dimensions.getFloatZ() * (1f - winding.getFloatX())
                );
            } else if (cubeFace == CubeFace.TOP) {
                this.vertexPositionCursor.set(
                    position.getFloatX() + dimensions.getFloatX() * winding.getFloatX(),
                    position.getFloatY() + dimensions.getFloatY(),
                    position.getFloatZ() + dimensions.getFloatZ() * (1f - winding.getFloatY())
                );
            } else if (cubeFace == CubeFace.BOTTOM) {
                this.vertexPositionCursor.set(
                    position.getFloatX() + dimensions.getFloatX() * winding.getFloatX(),
                    position.getFloatY(),
                    position.getFloatZ() + dimensions.getFloatZ() * winding.getFloatY()
                );
            }

            this.originPositionCursor.set(dimensions).div(2f).add(position);
            this.vertexPositionCursor.sub(this.originPositionCursor).rotateY(rotation).add(this.originPositionCursor);

            this.positionBuffer.put(this.vertexPositionCursor.x);
            this.positionBuffer.put(this.vertexPositionCursor.y);
            this.positionBuffer.put(this.vertexPositionCursor.z);

            this.normalBuffer.put(cubeFace.normal.getFloatX());
            this.normalBuffer.put(cubeFace.normal.getFloatY());
            this.normalBuffer.put(cubeFace.normal.getFloatZ());

            this.textureOffsetBuffer.put(textureSample.textureOffsets[ix].x);
            this.textureOffsetBuffer.put(textureSample.textureOffsets[ix].y);
            this.packedColorBuffer.put(packedColor);
            this.packedLookupIDsBuffer.put(packedLookupIDs);
        }

        for(var index : QUAD_INDICES) {
            this.indexBuffer.put(index + this.numQuads * 4);
        }

        this.numQuads += 1;
    }

    public void writeMeshBufferToMesh(Mesh mesh) {
        mesh.writeIndexData(this.indexBuffer);
        mesh.writePositionData(this.positionBuffer);
        mesh.writeNormalData(this.normalBuffer);
        mesh.writeTextureOffsetData(this.textureOffsetBuffer);
        mesh.writePackedColorData(this.packedColorBuffer);
        mesh.writePackedLookupIDsData(this.packedLookupIDsBuffer);
    }

    public void clear() {
        this.indexBuffer.clear();
        this.positionBuffer.clear();
        this.normalBuffer.clear();
        this.textureOffsetBuffer.clear();
        this.packedColorBuffer.clear();
        this.packedLookupIDsBuffer.clear();
        this.numQuads = 0;
    }

}

