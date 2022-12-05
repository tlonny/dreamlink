package dreamlink.graphics.mesh.sprite;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import dreamlink.graphics.mesh.VertexWinding;
import dreamlink.graphics.mesh.strategy.index.IMeshIndexQuadStrategyProvider;
import dreamlink.graphics.mesh.strategy.index.IndexStrategy;
import dreamlink.graphics.mesh.strategy.texture.ITextureStategyProvider;
import dreamlink.graphics.mesh.strategy.texture.TextureStrategy;
import dreamlink.utility.maths.Vector4fMaths;

public class SpriteMeshBuffer {

    private class InternalIndexStrategyProvider implements IMeshIndexQuadStrategyProvider {

        @Override
        public void addIndex(int index) {
            SpriteMeshBuffer.this.indexData.add(index);
        }

        @Override
        public int getIndexCount() {
            return SpriteMeshBuffer.this.indexData.size();
        }

    }

    private class InternalTextureStrategyProvider implements ITextureStategyProvider {

        @Override
        public void addTextureOffsets(float x, float y, float strideX, float strideY) {
            SpriteMeshBuffer.this.textureOffsetData.add(x);
            SpriteMeshBuffer.this.textureOffsetData.add(y);
            SpriteMeshBuffer.this.textureOffsetData.add(strideX);
            SpriteMeshBuffer.this.textureOffsetData.add(strideY);
        }

        @Override
        public void addMetadata(int metadata) {
            SpriteMeshBuffer.this.textureMetaDataData.add(metadata);
        }

    }

    private final List<Integer> indexData = new ArrayList<>();
    private final List<Float> positionData = new ArrayList<>();
    private final List<Float> textureOffsetData = new ArrayList<>();
    private final List<Integer> textureMetaDataData = new ArrayList<>();
    private final List<Integer> colorData = new ArrayList<>();

    private final IndexStrategy indexStrategy = new IndexStrategy(new InternalIndexStrategyProvider());
    private final TextureStrategy textureStrategy = new TextureStrategy(new InternalTextureStrategyProvider());

    public static final SpriteMeshBuffer instance = new SpriteMeshBuffer();

    public void addQuad(SpriteQuadData quadData) {
        if(quadData.getTextureSample() == null) {
            return;
        }

        this.indexStrategy.addIndices();
        this.textureStrategy.add(quadData);

        var position = quadData.position;
        var dimensions = quadData.dimensions;
        var color = quadData.color;

        var packedColorData = Vector4fMaths.toHex(color);
        for (var ix = 0; ix < VertexWinding.getSize(); ix += 1) {
            var winding = VertexWinding.get(ix).winding;
            this.positionData.add(position.x + dimensions.x * winding.x());
            this.positionData.add(position.y + dimensions.y * winding.y());
            this.colorData.add(packedColorData);
        }

    }

    public int getIndexCount() {
        return this.indexData.size();
    }

    public void bufferIndexData(IntBuffer buffer) {
        for (var index : this.indexData) {
            buffer.put(index);
        }
    }

    public void bufferTextureOffsetData(FloatBuffer buffer) {
        for (var offset : this.textureOffsetData) {
            buffer.put(offset);
        }
    }

    public void bufferTextureMetadataData(IntBuffer buffer) {
        for (var metadata : this.textureMetaDataData) {
            buffer.put(metadata);
        }
    }

    public void bufferColorData(IntBuffer buffer) {
        for (var metadata : this.colorData) {
            buffer.put(metadata);
        }
    }

    public void bufferPositionData(FloatBuffer buffer) {
        for (var position : this.positionData) {
            buffer.put(position);
        }
    }

    public SpriteMeshBuffer clear() {
        this.indexData.clear();
        this.positionData.clear();
        this.textureOffsetData.clear();
        this.textureMetaDataData.clear();
        this.colorData.clear();
        return this;
    }

}
