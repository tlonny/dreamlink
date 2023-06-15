package doors.graphics;

import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import doors.utility.CubeFace;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MeshBuffer {

    private static final int[] QUAD_INDICES = new int[] { 0, 1, 3, 3, 1, 2 };

    public IntBuffer indexBuffer;
    public FloatBuffer positionBuffer;
    public FloatBuffer normalBuffer;
    public FloatBuffer textureOffsetBuffer;
    public IntBuffer packedColorTextureUnitBuffer;

    public int vertexCount;
    public int indexCount;

    public MeshBuffer(int quadCapacity) {
        this.indexBuffer = MemoryUtil.memAllocInt(quadCapacity * 6);
        this.positionBuffer = MemoryUtil.memAllocFloat(quadCapacity * 12);
        this.normalBuffer = MemoryUtil.memAllocFloat(quadCapacity * 12);
        this.textureOffsetBuffer = MemoryUtil.memAllocFloat(quadCapacity * 8);
        this.packedColorTextureUnitBuffer = MemoryUtil.memAllocInt(quadCapacity * 4); 
    }

    public void tearDown() {
        MemoryUtil.memFree(this.indexBuffer);
        MemoryUtil.memFree(this.positionBuffer);
        MemoryUtil.memFree(this.normalBuffer);
        MemoryUtil.memFree(this.textureOffsetBuffer);
        MemoryUtil.memFree(this.packedColorTextureUnitBuffer);
    }

    private int packColorTextureUnit(int normalisedTextureUnitID, Vector3f color) {
        var packed = normalisedTextureUnitID;
        packed *= 0x100;
        packed += (int)(color.x * 0xFF);
        packed *= 0x100;
        packed += (int)(color.y * 0xFF);
        packed *= 0x100;
        packed += (int)(color.z * 0xFF);
        return packed;
    }

    public void pushQuad(Vector3f[] positions, CubeFace cubeFace, TextureSample textureSample, Vector3f color) {
        var packed = this.packColorTextureUnit(textureSample.textureChannel.getSamplerUniformValue(), color);
        for(var ix = 0; ix < 4; ix += 1) {
            this.positionBuffer.put(positions[ix].x);
            this.positionBuffer.put(positions[ix].y);
            this.positionBuffer.put(positions[ix].z);
            this.normalBuffer.put(cubeFace.normalI.x);
            this.normalBuffer.put(cubeFace.normalI.y);
            this.normalBuffer.put(cubeFace.normalI.z);
            this.textureOffsetBuffer.put(textureSample.textureOffsets[ix].x);
            this.textureOffsetBuffer.put(textureSample.textureOffsets[ix].y);
            this.packedColorTextureUnitBuffer.put(packed);
        }

        for(var index : QUAD_INDICES)
            this.indexBuffer.put(index + this.vertexCount);

        this.vertexCount += 4;
        this.indexCount += 6;
    }

    public void flip() {
        this.indexBuffer.flip();
        this.positionBuffer.flip();
        this.normalBuffer.flip();
        this.textureOffsetBuffer.flip();
        this.packedColorTextureUnitBuffer.flip();
    }

    public void clear() {
        this.indexBuffer.clear();
        this.positionBuffer.clear();
        this.normalBuffer.clear();
        this.textureOffsetBuffer.clear();
        this.packedColorTextureUnitBuffer.clear();

        this.indexCount = 0;
        this.vertexCount = 0;
    }

}

