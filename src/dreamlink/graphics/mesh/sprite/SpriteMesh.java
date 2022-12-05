package dreamlink.graphics.mesh.sprite;

import dreamlink.graphics.glconfig.MeshConfig;
import dreamlink.graphics.mesh.Mesh;
import dreamlink.graphics.mesh.vertexbuffer.FloatVertexBuffer;
import dreamlink.graphics.mesh.vertexbuffer.IndexVertexBuffer;
import dreamlink.graphics.mesh.vertexbuffer.IntVertexBuffer;

public class SpriteMesh extends Mesh {

    private static final int positionLocation = 0;
    private static final int positionSize = 2;

    private static final int textureOffsetLocation = 1;
    private static final int textureOffsetSize = 4;

    private static final int textureMetaDataLocation = 2;
    private static final int textureMetaDataSize = 1;

    private static final int colorLocation = 3;
    private static final int colorSize = 1;

    private final IndexVertexBuffer indexBuffer = new IndexVertexBuffer();
    private final FloatVertexBuffer positionBuffer = new FloatVertexBuffer(SpriteMesh.positionLocation, SpriteMesh.positionSize);
    private final FloatVertexBuffer textureOffsetBuffer = new FloatVertexBuffer(SpriteMesh.textureOffsetLocation, SpriteMesh.textureOffsetSize);
    private final IntVertexBuffer textureMetaDataBuffer = new IntVertexBuffer(SpriteMesh.textureMetaDataLocation, SpriteMesh.textureMetaDataSize);
    private final IntVertexBuffer colorBuffer = new IntVertexBuffer(SpriteMesh.colorLocation, SpriteMesh.colorSize);
    private int indexCount;

    public SpriteMesh() {
        this.addMeshBuffer(this.indexBuffer);
        this.addMeshBuffer(this.positionBuffer);
        this.addMeshBuffer(this.textureOffsetBuffer);
        this.addMeshBuffer(this.textureMetaDataBuffer);
        this.addMeshBuffer(this.colorBuffer);
    }

    private final MeshConfig bufferMeshConfig = new MeshConfig();

    public void buffer(SpriteMeshBuffer buffer) {
        this.indexCount = buffer.getIndexCount();
        try(var meshConfig = this.bufferMeshConfig.checkpoint()) {
            meshConfig.setState(this);

            buffer.bufferIndexData(SpriteMesh.intBuffer.clear());
            this.indexBuffer.buffer(SpriteMesh.intBuffer.flip());

            buffer.bufferPositionData(SpriteMesh.floatBuffer.clear());
            this.positionBuffer.buffer(SpriteMesh.floatBuffer.flip());

            buffer.bufferTextureOffsetData(SpriteMesh.floatBuffer.clear());
            this.textureOffsetBuffer.buffer(SpriteMesh.floatBuffer.flip());

            buffer.bufferTextureMetadataData(SpriteMesh.intBuffer.clear());
            this.textureMetaDataBuffer.buffer(SpriteMesh.intBuffer.flip());

            buffer.bufferColorData(SpriteMesh.intBuffer.clear());
            this.colorBuffer.buffer(SpriteMesh.intBuffer.flip());
        }
    }

    @Override
    public int getIndicesCount() {
        return this.indexCount;
    }
    
}
