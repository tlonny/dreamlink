package dreamlink.graphics.mesh.terrain;

import dreamlink.graphics.glconfig.MeshConfig;
import dreamlink.graphics.mesh.Mesh;
import dreamlink.graphics.mesh.vertexbuffer.FloatVertexBuffer;
import dreamlink.graphics.mesh.vertexbuffer.IndexVertexBuffer;
import dreamlink.graphics.mesh.vertexbuffer.IntVertexBuffer;

public class TerrainMesh extends Mesh {

    private static final int positionLocation = 0;
    private static final int positionSize = 3;

    private static final int fulcrumLocation = 1;
    private static final int fulcrumSize = 3;

    private static final int textureOffsetLocation = 2;
    private static final int textureOffsetSize = 4;

    private static final int textureMetaDataLocation = 3;
    private static final int textureMetaDataSize = 1;

    private static final int terrainMetaDataLocation = 4;
    private static final int terrainMetaDataSize = 1;

    private final IndexVertexBuffer indexBuffer = new IndexVertexBuffer();
    private final FloatVertexBuffer positionBuffer = new FloatVertexBuffer(TerrainMesh.positionLocation, TerrainMesh.positionSize);
    private final FloatVertexBuffer fulcrumBuffer = new FloatVertexBuffer(TerrainMesh.fulcrumLocation, TerrainMesh.fulcrumSize);
    private final FloatVertexBuffer textureOffsetBuffer = new FloatVertexBuffer(TerrainMesh.textureOffsetLocation, TerrainMesh.textureOffsetSize);
    private final IntVertexBuffer textureMetaDataBuffer = new IntVertexBuffer(TerrainMesh.textureMetaDataLocation, TerrainMesh.textureMetaDataSize);
    private final IntVertexBuffer terrainMetaDataBuffer = new IntVertexBuffer(TerrainMesh.terrainMetaDataLocation, TerrainMesh.terrainMetaDataSize);
    private int indexCount;

    public TerrainMesh() {
        this.addMeshBuffer(this.indexBuffer);
        this.addMeshBuffer(this.positionBuffer);
        this.addMeshBuffer(this.fulcrumBuffer);
        this.addMeshBuffer(this.textureOffsetBuffer);
        this.addMeshBuffer(this.textureMetaDataBuffer);
        this.addMeshBuffer(this.terrainMetaDataBuffer);
    }

    private final MeshConfig bufferMeshConfig = new MeshConfig();

    public void buffer(TerrainMeshBuffer buffer) {
        this.indexCount = buffer.getIndexCount();
        try(var meshConfig = this.bufferMeshConfig.checkpoint()) {
            meshConfig.setState(this);

            buffer.bufferIndexData(Mesh.intBuffer.clear());
            this.indexBuffer.buffer(Mesh.intBuffer.flip());

            buffer.bufferPositionData(Mesh.floatBuffer.clear());
            this.positionBuffer.buffer(Mesh.floatBuffer.flip());

            buffer.bufferFulcrumData(Mesh.floatBuffer.clear());
            this.fulcrumBuffer.buffer(Mesh.floatBuffer.flip());

            buffer.bufferTextureOffsetData(Mesh.floatBuffer.clear());
            this.textureOffsetBuffer.buffer(Mesh.floatBuffer.flip());

            buffer.bufferTextureMetadataData(Mesh.intBuffer.clear());
            this.textureMetaDataBuffer.buffer(Mesh.intBuffer.flip());

            buffer.bufferTerrainMetadataData(Mesh.intBuffer.clear());
            this.terrainMetaDataBuffer.buffer(Mesh.intBuffer.flip());
        }
    }

    @Override
    public int getIndicesCount() {
        return this.indexCount;
    }


}

