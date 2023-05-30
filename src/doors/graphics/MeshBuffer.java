package doors.graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MeshBuffer {

    private static final int[] QUAD_INDICES = new int[] { 0, 1, 3, 3, 1, 2 };

    public FloatBuffer positionBuffer;
    public FloatBuffer normalBuffer;
    public FloatBuffer textureOffsetBuffer;
    public IntBuffer indexBuffer;
    public int indexCount;
    public int vertexCount;

    public Vector3f position = new Vector3f();
    public Vector3f normal = new Vector3f();
    public Vector2f textureOffset = new Vector2f();

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

    private void indexQuad() {
        for(var index : QUAD_INDICES)
            this.indexBuffer.put(index + this.vertexCount - 4);
        this.indexCount += 6;
    }

    public void push() {
        this.positionBuffer.put(this.position.x);
        this.positionBuffer.put(this.position.y);
        this.positionBuffer.put(this.position.z);
        this.normalBuffer.put(this.normal.x);
        this.normalBuffer.put(this.normal.y);
        this.normalBuffer.put(this.normal.z);
        this.textureOffsetBuffer.put(this.textureOffset.x);
        this.textureOffsetBuffer.put(this.textureOffset.y);
        this.vertexCount += 1;
        if(this.vertexCount % 4 == 0) {
            this.indexQuad();
        }
    }

}

