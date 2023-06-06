package doors.graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MeshBuffer implements IMeshBuffer {

    private static final int[] QUAD_INDICES = new int[] { 0, 1, 3, 3, 1, 2 };

    public FloatBuffer positionBuffer;
    public FloatBuffer normalBuffer;
    public FloatBuffer textureOffsetBuffer;
    public IntBuffer indexBuffer;
    public int indexCount;
    public int vertexCount;

    public MeshBuffer(int quadCapacity) {
        this.positionBuffer = MemoryUtil.memAllocFloat(quadCapacity * 12);
        this.normalBuffer = MemoryUtil.memAllocFloat(quadCapacity * 12);
        this.textureOffsetBuffer = MemoryUtil.memAllocFloat(quadCapacity * 8);
        this.indexBuffer = MemoryUtil.memAllocInt(quadCapacity * 6);
    }

    public void tearDown() {
        MemoryUtil.memFree(this.positionBuffer);
        MemoryUtil.memFree(this.normalBuffer);
        MemoryUtil.memFree(this.textureOffsetBuffer);
        MemoryUtil.memFree(this.indexBuffer);
    }

    public void pushVertex(Vector3f position, Vector3f normal, Vector2f textureOffset) {
        this.positionBuffer.put(position.x);
        this.positionBuffer.put(position.y);
        this.positionBuffer.put(position.z);
        this.normalBuffer.put(normal.x);
        this.normalBuffer.put(normal.y);
        this.normalBuffer.put(normal.z);
        this.textureOffsetBuffer.put(textureOffset.x);
        this.textureOffsetBuffer.put(textureOffset.y);
        this.vertexCount += 1;

        if(this.vertexCount % 4 == 0) {
            for(var index : QUAD_INDICES)
                this.indexBuffer.put(index + this.vertexCount - 4);
            this.indexCount += 6;
        }
    }

    public FloatBuffer getPositionBuffer() {
        return this.positionBuffer;
    }

    public FloatBuffer getNormalBuffer() {
        return this.normalBuffer;
    }

    public FloatBuffer getTextureOffsetBuffer() {
        return this.textureOffsetBuffer;
    }

    public IntBuffer getIndexBuffer() {
        return this.indexBuffer;
    }

    public int getIndexCount() {
        return this.indexCount;
    }

    public void flip() {
        this.positionBuffer.flip();
        this.normalBuffer.flip();
        this.textureOffsetBuffer.flip();
        this.indexBuffer.flip();
    }

    public void clear() {
        this.positionBuffer.clear();
        this.normalBuffer.clear();
        this.textureOffsetBuffer.clear();
        this.indexBuffer.clear();
        this.indexCount = 0;
        this.vertexCount = 0;
    }

}

