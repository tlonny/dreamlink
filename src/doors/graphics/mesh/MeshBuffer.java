package doors.graphics.mesh;

import org.lwjgl.system.MemoryUtil;

import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSample;
import doors.utility.CubeFace;
import doors.utility.geometry.IVector3fl;
import doors.utility.geometry.Vector2fl;
import doors.utility.geometry.Vector3fl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MeshBuffer {

    private static final int[] QUAD_INDICES = new int[] { 0, 1, 3, 3, 1, 2 };

    private static Vector2fl[] WINDINGS = new Vector2fl[] {
        new Vector2fl(0f, 1f),
        new Vector2fl(0f, 0f),
        new Vector2fl(1f, 0f),
        new Vector2fl(1f, 1f),
    };

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

    private int packColorTextureUnit(int normalisedTextureUnitID, Vector3fl color) {
        var packed = normalisedTextureUnitID;
        packed *= 0x100;
        packed += (int)(color.x * 0xFF);
        packed *= 0x100;
        packed += (int)(color.y * 0xFF);
        packed *= 0x100;
        packed += (int)(color.z * 0xFF);
        return packed;
    }

    public void writeQuad(TextureSample textureSample, TextureChannel textureChannel, IVector3fl position, IVector3fl dimensions, CubeFace cubeFace, Vector3fl color) {
        var packed = this.packColorTextureUnit(textureChannel.getSamplerUniformValue(), color);
        var vertexPosition = new Vector3fl();

        for(var ix = 0; ix < 4; ix += 1) {
            var winding = WINDINGS[ix];
            if (cubeFace == CubeFace.FRONT) {
                vertexPosition.set(
                    position.getFloatX() + dimensions.getFloatX() * winding.x,
                    position.getFloatY() + dimensions.getFloatY() * winding.y,
                    position.getFloatZ() + dimensions.getFloatZ()
                );
            } else if (cubeFace == CubeFace.BACK) {
                vertexPosition.set(
                    position.getFloatX() - dimensions.getFloatX() * winding.x,
                    position.getFloatY() + dimensions.getFloatY() * winding.y,
                    position.getFloatZ()
                );
            } else if (cubeFace == CubeFace.LEFT) {
                vertexPosition.set(
                    position.getFloatX(),
                    position.getFloatY() + dimensions.getFloatY() * winding.y,
                    position.getFloatZ() + dimensions.getFloatX() * winding.x
                );
            } else if (cubeFace == CubeFace.RIGHT) {
                vertexPosition.set(
                    position.getFloatX() + dimensions.getFloatZ(),
                    position.getFloatY() + dimensions.getFloatY() * winding.y,
                    position.getFloatZ() - dimensions.getFloatX() * winding.x
                );
            } else if (cubeFace == CubeFace.TOP) {
                vertexPosition.set(
                    position.getFloatX() + dimensions.getFloatX() * winding.x,
                    position.getFloatY() + dimensions.getFloatY(),
                    position.getFloatZ() - dimensions.getFloatZ() * winding.y
                );
            } else if (cubeFace == CubeFace.BOTTOM) {
                vertexPosition.set(
                    position.getFloatX() + dimensions.getFloatX() * winding.x,
                    position.getFloatY(),
                    position.getFloatZ() + dimensions.getFloatZ() * winding.y
                );
            }

            this.positionBuffer.put(vertexPosition.x);
            this.positionBuffer.put(vertexPosition.y);
            this.positionBuffer.put(vertexPosition.z);
            this.normalBuffer.put(cubeFace.normal.x);
            this.normalBuffer.put(cubeFace.normal.y);
            this.normalBuffer.put(cubeFace.normal.z);
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

