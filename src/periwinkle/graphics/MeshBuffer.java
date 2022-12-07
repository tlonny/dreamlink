package periwinkle.graphics;

import periwinkle.utility.CubeFace;
import org.joml.Matrix3f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MeshBuffer {

    private static final int[] QUAD_INDICES = new int[] { 0, 1, 3, 3, 1, 2 };

    public FloatBuffer positionBuffer;
    public FloatBuffer normalBuffer;
    public FloatBuffer textureBuffer;
    public FloatBuffer localLightBuffer;
    public FloatBuffer globalLightBuffer;
    public IntBuffer indexBuffer;
    public int indexCount;

    public MeshBuffer(int quadCapacity) {
        this.positionBuffer = MemoryUtil.memAllocFloat(quadCapacity * 12);
        this.normalBuffer = MemoryUtil.memAllocFloat(quadCapacity * 12);
        this.textureBuffer = MemoryUtil.memAllocFloat(quadCapacity * 8);
        this.localLightBuffer = MemoryUtil.memAllocFloat(quadCapacity * 4);
        this.globalLightBuffer = MemoryUtil.memAllocFloat(quadCapacity * 4);
        this.indexBuffer = MemoryUtil.memAllocInt(quadCapacity * 6);
    }

    public void tearDown() {
        MemoryUtil.memFree(this.positionBuffer);
        MemoryUtil.memFree(this.normalBuffer);
        MemoryUtil.memFree(this.textureBuffer);
        MemoryUtil.memFree(this.localLightBuffer);
        MemoryUtil.memFree(this.globalLightBuffer);
        MemoryUtil.memFree(this.indexBuffer);
    }

    public void flip() {
        this.positionBuffer.flip();
        this.normalBuffer.flip();
        this.localLightBuffer.flip();
        this.globalLightBuffer.flip();
        this.textureBuffer.flip();
        this.indexBuffer.flip();
    }

    public void clear() {
        this.positionBuffer.clear();
        this.normalBuffer.clear();
        this.textureBuffer.clear();
        this.localLightBuffer.clear();
        this.globalLightBuffer.clear();
        this.indexBuffer.clear();
        this.indexCount = 0;
    }

    public void indexQuad() {
        var vertexCount = this.indexCount * 4 / 6;
        for(var index : QUAD_INDICES)
            this.indexBuffer.put(index + vertexCount);
        this.indexCount += 6;
    }

    public void pushVertex(Vector3f position, Vector3f normal, Vector2f texture, float localLight, float globalLight) {
        this.positionBuffer.put(position.x);
        this.positionBuffer.put(position.y);
        this.positionBuffer.put(position.z);
        this.normalBuffer.put(normal.x);
        this.normalBuffer.put(normal.y);
        this.normalBuffer.put(normal.z);
        this.textureBuffer.put(texture.x);
        this.textureBuffer.put(texture.y);
        this.localLightBuffer.put(localLight);
        this.globalLightBuffer.put(globalLight);
    }

    public void pushSprite(Vector2f position, Vector2f dimensions, ITextureLookup lookup) {
        var offsets = lookup.getTextureOffsets();
        for(var vertIx = 0; vertIx < CubeFace.FRONT.vertices.length; vertIx += 1) {
            var vertex = CubeFace.FRONT.vertices[vertIx];
            var vertOffset = offsets[vertIx];
            var vertPosition = new Vector3f(
                (vertex.x * dimensions.x + position.x)/Display.DISPLAY.width * 2f - 1,
                (vertex.y * dimensions.y + Display.DISPLAY.height - position.y - dimensions.y)/Display.DISPLAY.height * 2f - 1,
                -1f
            );
            this.pushVertex(vertPosition, new Vector3f(CubeFace.FRONT.normal), vertOffset, 0f, 1f);
        }
        this.indexQuad();
    }

    public void pushParticle(Vector3f position, float width, ITextureLookup lookup, Matrix3f billboardMatrix) {
        var offsets = lookup.getTextureOffsets();
        for(var cubeFace : CubeFace.FRONT_AND_BACK_CUBE_FACES) {
            for (var vertIx = 0; vertIx < cubeFace.vertices.length; vertIx += 1) {
                var vertex = cubeFace.vertices[vertIx];
                var vertOffset = offsets[vertIx];
                var vertPosition = new Vector3f(vertex).mul(width).mul(billboardMatrix).add(position);
                this.pushVertex(vertPosition, new Vector3f(CubeFace.FRONT.normal), vertOffset, 0f, 1f);
            }
            this.indexQuad();
        }
    }
}
