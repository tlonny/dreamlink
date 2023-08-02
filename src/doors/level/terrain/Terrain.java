package doors.level.terrain;

import java.nio.file.Paths;

import org.json.JSONObject;

import doors.graphics.mesh.MeshBuffer;
import doors.level.block.Block;
import doors.level.block.BlockMap;
import doors.queue.IncrementalWorkQueue;
import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.vector.Vector3fl;
import doors.utility.vector.Vector3in;

public class Terrain {

    private static String CONFIG_FILE_NAME = "terrain.json";
    private static String CHUNK_FILE_NAME_TEMPLATE = "chunks/chunk-%03d.blob";

    private Vector3in chunkDimensions = new Vector3in();
    private Vector3in blockDimensions = new Vector3in();
    private Chunk[] chunks;

    private Vector3in chunkCursor = new Vector3in();
    private Vector3in blockCursor = new Vector3in();
    private Vector3in positionCursor = new Vector3in();

    private Vector3in localPositionCursor = new Vector3in();
    private Vector3in globalPositionCursor = new Vector3in();
    private Vector3in adjacentGlobalPositionCursor = new Vector3in();

    private BlockMap blockMap;
    public boolean isReady = false;

    public Terrain(String terrainDirectory, BlockMap blockMap) {
        this.blockMap = blockMap;

        var terrainConfigPath = Paths.get(terrainDirectory, CONFIG_FILE_NAME).toString();
        var terrainConfigString = FileIO.loadText(terrainConfigPath);
        var config = new JSONObject(terrainConfigString);

        var chunkDimensionsArray = config.getJSONArray("chunkDimensions");
        this.chunkDimensions.set(
            chunkDimensionsArray.getInt(0),
            chunkDimensionsArray.getInt(1),
            chunkDimensionsArray.getInt(2)
        );

        this.blockDimensions.set(chunkDimensions).mul(Chunk.DIMENSIONS);
        this.chunks = new Chunk[this.chunkDimensions.getIntVolume()];

        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunkPath = Paths.get(terrainDirectory, String.format(CHUNK_FILE_NAME_TEMPLATE, ix)).toString();
            this.chunks[ix] = new Chunk(chunkPath);
            this.chunks[ix].position.set(ix, this.chunkDimensions).mul(Chunk.DIMENSIONS);
        }

        for(var chunk : chunks) {
            IncrementalWorkQueue.INCREMENTAL_WORK_QUEUE.submitTask(chunk::setup);
        }

        // Make sure chunks are processed after all chunks are setup to ensure the
        // no redundant mesh is created at chunk boundaries.
        for(var chunk : this.chunks) {
            IncrementalWorkQueue.INCREMENTAL_WORK_QUEUE.submitTask(() -> this.processDirtyChunk(chunk));
        }
    }

    public Block getBlock(Vector3in position) {
        if(!position.isWithinBounds(Vector3in.ZERO, this.blockDimensions)) {
            return null;
        }

        this.chunkCursor.set(position).div(Chunk.DIMENSIONS);
        this.blockCursor.set(chunkCursor).mul(Chunk.DIMENSIONS).mul(-1).add(position);

        var chunkIndex = this.chunkCursor.serialize(this.chunkDimensions);
        var chunk = this.chunks[chunkIndex];

        var blockIndex = this.blockCursor.serialize(Chunk.DIMENSIONS);
        var blockID = chunk.blockData[blockIndex];
        return this.blockMap.getBlock(blockID);
    }


    public void setBlock(Vector3in position, Block block) {
        if(!position.isWithinBounds(Vector3in.ZERO, this.blockDimensions)) {
            return;
        }


        this.chunkCursor.set(position).div(Chunk.DIMENSIONS);
        var chunk = this.chunks[this.chunkCursor.serialize(this.chunkDimensions)];

        this.blockCursor.set(chunkCursor).mul(Chunk.DIMENSIONS).mul(-1).add(position);
        var blockIndex = this.blockCursor.serialize(Chunk.DIMENSIONS);
        var oldBlockID = chunk.blockData[blockIndex];
        var blockID = block == null ? 0 : block.blockID;

        if(oldBlockID == blockID) {
            return;
        }

        for(var cubeFace : CubeFace.CUBE_FACES) {
            this.positionCursor.set(position).add(cubeFace.normal);
            if(!this.positionCursor.isWithinBounds(Vector3in.ZERO, this.blockDimensions)) {
                continue;
            }

            this.chunkCursor.set(this.positionCursor).div(Chunk.DIMENSIONS);
            var adjacentChunk = this.chunks[this.chunkCursor.serialize(this.chunkDimensions)];
            adjacentChunk.isDirty |= true;
            IncrementalWorkQueue.INCREMENTAL_WORK_QUEUE.submitTask(() -> this.processDirtyChunk(adjacentChunk));
        }

        chunk.blockData[blockIndex] = blockID;
        chunk.isDirty |= true;
        IncrementalWorkQueue.INCREMENTAL_WORK_QUEUE.submitTask(() -> this.processDirtyChunk(chunk));
    }

    private void processDirtyChunk(Chunk chunk) {
        var maxIndex = Chunk.DIMENSIONS.getIntVolume();

        MeshBuffer.DEFAULT_MESH_BUFFER.clear();
        for(var blockIndex = 0; blockIndex < maxIndex; blockIndex += 1) {
            this.localPositionCursor
                .set(blockIndex, Chunk.DIMENSIONS);

            this.globalPositionCursor
                .set(chunk.position)
                .add(this.localPositionCursor);

            var block = this.getBlock(this.globalPositionCursor);

            if(block == null) {
                continue;
            }

            for(var cubeFace : CubeFace.CUBE_FACES) {
                this.adjacentGlobalPositionCursor.set(globalPositionCursor).add(cubeFace.normal);
                var adjacentBlock = this.getBlock(this.adjacentGlobalPositionCursor);
            
                block.writeBlockFaceToMeshBuffer(
                    MeshBuffer.DEFAULT_MESH_BUFFER, 
                    this.localPositionCursor, 
                    cubeFace,
                    adjacentBlock
                );
            }
        }

        MeshBuffer.DEFAULT_MESH_BUFFER.writeMeshTo(chunk.mesh);
        chunk.isDirty = false;
    }

    public void render() {
        this.blockMap.getTexture().useTexture();
        var positionCursor = new Vector3fl();
        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunk = this.chunks[ix];
            positionCursor.set(chunk.position);
            chunk.mesh.render(positionCursor, Vector3fl.ZERO, Vector3fl.ONE, Vector3fl.WHITE);
        }
    }

    public void tearDown() {
    }

}
