package dreamlink.graphics.mesh.terrain;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import dreamlink.graphics.mesh.VertexWinding;
import dreamlink.graphics.mesh.strategy.index.IMeshIndexQuadStrategyProvider;
import dreamlink.graphics.mesh.strategy.index.IndexStrategy;
import dreamlink.graphics.mesh.strategy.texture.ITextureStategyProvider;
import dreamlink.graphics.mesh.strategy.texture.TextureStrategy;
import dreamlink.utility.maths.CubeFace;
import dreamlink.world.room.module.terrain.TerrainLight;

public class TerrainMeshBuffer {

    private class InternalIndexStrategyProvider implements IMeshIndexQuadStrategyProvider {

        @Override
        public void addIndex(int index) {
            TerrainMeshBuffer.this.indexData.add(index);
        }

        @Override
        public int getIndexCount() {
            return TerrainMeshBuffer.this.indexData.size();
        }

    }

    private class InternalTextureStrategyProvider implements ITextureStategyProvider {

        @Override
        public void addTextureOffsets(float x, float y, float strideX, float strideY) {
            TerrainMeshBuffer.this.textureOffsetData.add(x);
            TerrainMeshBuffer.this.textureOffsetData.add(y);
            TerrainMeshBuffer.this.textureOffsetData.add(strideX);
            TerrainMeshBuffer.this.textureOffsetData.add(strideY);
        }

        @Override
        public void addMetadata(int metadata) {
            TerrainMeshBuffer.this.textureMetaDataData.add(metadata);
        }

    }

    private final static int primaryLightOffset = 0;
    private final static int secondaryLightOffset = 4;
    private final static int tertiaryLightOffset = 8;
    private final static int portalLightOffset = 12;

    private final static int isHiddenOffset = 16;
    private final static int isAffectedByLightOffset = 17;
    private final static int transformerIndexOffset = 18;

    public static final TerrainMeshBuffer instance = new TerrainMeshBuffer();

    private final List<Integer> indexData = new ArrayList<>();
    private final List<Float> positionData = new ArrayList<>();
    private final List<Float> fulcrumData = new ArrayList<>();
    private final List<Float> textureOffsetData = new ArrayList<>();
    private final List<Integer> textureMetaDataData = new ArrayList<>();
    private final List<Integer> terrainMetaDataData = new ArrayList<>();

    private final IndexStrategy indexStrategy = new IndexStrategy(new InternalIndexStrategyProvider());
    private final TextureStrategy textureStrategy = new TextureStrategy(new InternalTextureStrategyProvider());

    public void addQuad(TerrainQuadData quadData) {
        if(quadData.getTextureSample() == null) {
            return;
        }

        this.indexStrategy.addIndices();
        this.textureStrategy.add(quadData);

        var packedTerrainMetadata = 0;
        packedTerrainMetadata |= quadData.getLight(TerrainLight.primary) << TerrainMeshBuffer.primaryLightOffset;
        packedTerrainMetadata |= quadData.getLight(TerrainLight.secondary) << TerrainMeshBuffer.secondaryLightOffset;
        packedTerrainMetadata |= quadData.getLight(TerrainLight.tertiary) << TerrainMeshBuffer.tertiaryLightOffset;
        packedTerrainMetadata |= quadData.getLight(TerrainLight.portal) << TerrainMeshBuffer.portalLightOffset;
        packedTerrainMetadata |= (quadData.isHidden ? 1 : 0) << TerrainMeshBuffer.isHiddenOffset;
        packedTerrainMetadata |= (quadData.isAffectedByLight ? 1 : 0) << TerrainMeshBuffer.isAffectedByLightOffset;
        packedTerrainMetadata |= quadData.transformerIndex << TerrainMeshBuffer.transformerIndexOffset;

        var cubeFace = quadData.cubeFace;
        var position = quadData.position;
        var dimensions = quadData.dimensions;

        for (var ix = 0; ix < VertexWinding.getSize(); ix += 1) {
            var winding = VertexWinding.get(ix).winding;
            if (cubeFace == CubeFace.front) {
                this.positionData.add(position.x + dimensions.x * winding.x());
                this.positionData.add(position.y + dimensions.y * winding.y());
                this.positionData.add(position.z + dimensions.z);
            } else if (cubeFace == CubeFace.back) {
                this.positionData.add(position.x + dimensions.x * (1f - winding.x()));
                this.positionData.add(position.y + dimensions.y * winding.y());
                this.positionData.add(position.z);
            } else if (cubeFace == CubeFace.right) {
                this.positionData.add(position.x);
                this.positionData.add(position.y + dimensions.y * winding.y());
                this.positionData.add(position.z + dimensions.z * winding.x());
            } else if (cubeFace == CubeFace.left) {
                this.positionData.add(position.x + dimensions.x);
                this.positionData.add(position.y + dimensions.y * winding.y());
                this.positionData.add(position.z + dimensions.z * (1f - winding.x()));
            } else if (cubeFace == CubeFace.top) {
                this.positionData.add(position.x + dimensions.x * winding.x());
                this.positionData.add(position.y + dimensions.y);
                this.positionData.add(position.z + dimensions.z * (1f - winding.y()));
            } else if (cubeFace == CubeFace.bottom) {
                this.positionData.add(position.x + dimensions.x * winding.x());
                this.positionData.add(position.y);
                this.positionData.add(position.z + dimensions.z * winding.y());
            }
            this.terrainMetaDataData.add(packedTerrainMetadata);
            this.fulcrumData.add(quadData.fulcrum.x);
            this.fulcrumData.add(quadData.fulcrum.y);
            this.fulcrumData.add(quadData.fulcrum.z);
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

    public void bufferTerrainMetadataData(IntBuffer buffer) {
        for (var metadata : this.terrainMetaDataData) {
            buffer.put(metadata);
        }
    }

    public void bufferPositionData(FloatBuffer buffer) {
        for (var position : this.positionData) {
            buffer.put(position);
        }
    }

    public void bufferFulcrumData(FloatBuffer buffer) {
        for (var fulcrum : this.fulcrumData) {
            buffer.put(fulcrum);
        }
    }

    public TerrainMeshBuffer clear() {
        this.indexData.clear();
        this.positionData.clear();
        this.textureOffsetData.clear();
        this.textureMetaDataData.clear();
        this.terrainMetaDataData.clear();
        this.fulcrumData.clear();
        return this;
    }

}
