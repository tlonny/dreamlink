package doors.level.terrain;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import doors.graphics.mesh.MeshBuffer;
import doors.level.block.AbstractBlock;
import doors.level.block.BlockData;
import doors.level.block.BlockDataPacker;
import doors.utility.CubeFace;
import doors.utility.Orientation;
import doors.utility.vector.Vector3fl;
import doors.utility.vector.Vector3in;
import doors.work.WorkUnit;

public class TerrainData {

    private Vector3in dimensions = new Vector3in();
    private Vector3in chunkSpaceDimensions = new Vector3in();

    private Vector3in chunkPositionCursor = new Vector3in();
    private Vector3in blockPositionCursor = new Vector3in();
    private Vector3in globalBlockPositionCursor = new Vector3in();
    private BlockData blockDataBuffer = new BlockData();

    private TerrainChunk[] chunks;
    private String path;
    private BlockDataPacker blockDataPacker;

    public WorkUnit setupDependency = new WorkUnit();

    public TerrainData(BlockDataPacker blockDataPacker, String path) {
        this.blockDataPacker = blockDataPacker;
        this.path = path;

        var loadTerrainData = new WorkUnit(this::loadTerrainData, true);
        loadTerrainData.submit();

        var processDirtyChunks = new WorkUnit(this::processDirtyChunks);
        processDirtyChunks.registerDependency(loadTerrainData);
        processDirtyChunks.submit();
    }

    private void loadTerrainData() {
        try(var stream = new DataInputStream(new FileInputStream(this.path))) {
            this.chunkSpaceDimensions.set(stream.readInt(), stream.readInt(), stream.readInt());
            this.dimensions.set(chunkSpaceDimensions).mul(TerrainChunk.CHUNK_DIMENSIONS);

            var numChunks = this.chunkSpaceDimensions.getIntVolume();
            this.chunks = new TerrainChunk[numChunks];

            var numChunksInFile = stream.readInt();
            for(var ix = 0; ix < numChunksInFile; ix+=1) {
                var chunk = new TerrainChunk();
                chunk.position.set(stream.readInt(), stream.readInt(), stream.readInt());
                for(var jx = 0; jx < chunk.terrainData.length; jx+=1) {
                    chunk.terrainData[jx] = stream.readInt();
                }

                this.chunks[chunk.position.serialize(this.chunkSpaceDimensions)] = chunk;
            }

        } catch (IOException e) {
            var msg = String.format("Unable to load terrain data: %s", this.path);
            throw new RuntimeException(msg);
        }
    }

    private void processDirtyChunks() {
        var finalizeWorkUnit = new WorkUnit(this.setupDependency::submit);

        for(var ix = 0; ix < this.chunks.length; ix+=1) {
            var chunk = this.chunks[ix];
            if(chunk == null) {
                continue;
            }
            var processChunkWorkUnit = new WorkUnit(() -> this.processDirtyChunk(chunk));
            processChunkWorkUnit.submit();
            finalizeWorkUnit.registerDependency(processChunkWorkUnit);
        }

        finalizeWorkUnit.submit();
    }

    public void getBlockData(Vector3in position, BlockData blockData) {
        if(!position.isWithinBounds(Vector3in.ZERO, this.dimensions)) {
            blockData.clear();
            return;
        }

        this.chunkPositionCursor.set(position).div(TerrainChunk.CHUNK_DIMENSIONS);
        var chunkIndex = this.chunkPositionCursor.serialize(this.chunkSpaceDimensions);
        var chunk = this.chunks[chunkIndex];

        if(chunk == null) {
            blockData.clear();
            return;
        }

        this.blockPositionCursor.set(this.chunkPositionCursor).mul(TerrainChunk.CHUNK_DIMENSIONS).mul(-1).add(position);
        var blockIndex = this.blockPositionCursor.serialize(TerrainChunk.CHUNK_DIMENSIONS);
        this.blockDataPacker.unpackBlockData(blockData, chunk.terrainData[blockIndex]);
    }

    public void setBlockData(Vector3in position, BlockData blockData) {
        if(!position.isWithinBounds(Vector3in.ZERO, this.dimensions)) {
            return;
        }

        this.chunkPositionCursor.set(position).div(TerrainChunk.CHUNK_DIMENSIONS);
        var chunkIndex = this.chunkPositionCursor.serialize(this.chunkSpaceDimensions);
        var chunk = this.chunks[chunkIndex];

        var packedValue = this.blockDataPacker.packBlockData(blockData);
        if(chunk == null && packedValue == 0) {
            return;
        }

        this.blockPositionCursor.set(this.chunkPositionCursor).mul(TerrainChunk.CHUNK_DIMENSIONS).mul(-1).add(position);
        var blockIndex = this.blockPositionCursor.serialize(TerrainChunk.CHUNK_DIMENSIONS);
        var oldValue = chunk.terrainData[blockIndex];
        if(oldValue == packedValue) {
            return;
        }

        for(var cubeFace : CubeFace.CUBE_FACES) {
            this.chunkPositionCursor.set(position).add(cubeFace.normal);
            this.chunkPositionCursor.div(TerrainChunk.CHUNK_DIMENSIONS);
            var adjacentChunkIndex = this.chunkPositionCursor.serialize(this.chunkSpaceDimensions);
            var adjacentChunk = this.chunks[adjacentChunkIndex];
            if(adjacentChunk == null || chunk == adjacentChunk) {
                continue;
            }

            if(adjacentChunk.setDirty()) {
                new WorkUnit(() -> this.processDirtyChunk(chunk)).submit();
            }
        }

        if(chunk.setDirty()) {
            new WorkUnit(() -> this.processDirtyChunk(chunk)).submit();
        }
    }

    public void setBlock(Vector3in position, AbstractBlock block, Orientation orientation) {
        this.blockDataBuffer.set(block, orientation);
        this.setBlockData(position, blockDataBuffer);
    }

    private void processDirtyChunk(TerrainChunk chunk) {
        MeshBuffer.DEFAULT_MESH_BUFFER.clear();
        for(var ix = 0; ix < chunk.terrainData.length; ix += 1) {
            this.globalBlockPositionCursor
                .set(ix, TerrainChunk.CHUNK_DIMENSIONS)
                .add(chunk.position);

            this.getBlockData(this.globalBlockPositionCursor, this.blockDataBuffer);
            if(this.blockDataBuffer.block == null) {
                continue;
            }

            this.blockDataBuffer.block.writeBlockToMeshBuffer(
                this,
                MeshBuffer.DEFAULT_MESH_BUFFER,
                this.globalBlockPositionCursor,
                this.blockDataBuffer.orientation
            );
        }
        MeshBuffer.DEFAULT_MESH_BUFFER.writeMeshBufferToMesh(chunk.mesh);
        chunk.setClean();
    }

    public void render() {
        for(var ix = 0; ix < this.chunks.length; ix+=1) {
            var chunk = this.chunks[ix];
            if(chunk == null) {
                continue;
            }
            chunk.mesh.render(chunk.position, Vector3fl.ZERO, Vector3fl.ONE, Vector3fl.WHITE);
        }
    }
        
}
