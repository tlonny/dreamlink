package doors.level.terrain;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import doors.queue.WorkQueue;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.ImageTexture;
import doors.graphics.texture.channel.BlockTextureChannel;
import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.vector.Vector3fl;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3in;

public class Terrain {

    private ImageTexture texture;
    private Vector3in chunkDimensions;
    private Vector3in blockDimensions;
    private Chunk[] chunks;

    public Map<Integer, Block> blockMap = new HashMap<>();
    public boolean isReady = false;

    public String terrainDirectory;

    public Terrain(String terrainDirectory) {
        this.terrainDirectory = terrainDirectory;
    }

    private void markAsReady() {
        this.isReady = true;
    }

    public void setup() {
        var configPath = Paths.get(this.terrainDirectory, "terrain.json").toString();
        var configString = FileIO.loadText(configPath);
        var rootConfig = new JSONObject(configString);

        var texturePath = Paths.get(this.terrainDirectory, "atlas.png").toString();
        var textureDimensions = new Vector2in(rootConfig.getJSONArray("textureDimensions"));

        this.texture = new ImageTexture(BlockTextureChannel.BLOCK_TEXTURE_CHANNEL, textureDimensions, texturePath);
        WorkQueue.WORK_QUEUE.addWorkUnit(this.texture::setup);

        var blocks = rootConfig.getJSONArray("blocks");
        for(var ix = 0; ix < blocks.length(); ix += 1) {
            var block = blocks.getJSONObject(ix);
            var textureSample = block.getJSONArray("texture_sample");
            this.blockMap.put(ix + 1, new Block(
                ix + 1,
                block.getString("name"),
                this.texture.createTextureSample(
                    new Vector2in(
                        textureSample.getInt(0),
                        textureSample.getInt(1)
                    ),
                    new Vector2in(
                        textureSample.getInt(2),
                        textureSample.getInt(3)
                    )
                )
            ));
        }

        this.chunkDimensions = new Vector3in(rootConfig.getJSONArray("chunkDimensions"));
        this.blockDimensions = new Vector3in(this.chunkDimensions).mul(Chunk.DIMENSIONS);
        this.chunks = new Chunk[this.chunkDimensions.getIntVolume()];

        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunkPosition = new Vector3in(ix, this.chunkDimensions).mul(Chunk.DIMENSIONS);
            var chunkPath = Paths.get(this.terrainDirectory, "chunks", String.format("chunk-%03d.blob", ix));
            var chunk = new Chunk(chunkPosition, chunkPath.toString());
            this.chunks[ix] = chunk;
            WorkQueue.WORK_QUEUE.addWorkUnit(() -> chunk.setup());
        }

        // Make sure chunks are processed after all chunks are setup to ensure the
        // no redundant mesh is created at chunk boundaries.
        for(var chunk : this.chunks) {
            WorkQueue.WORK_QUEUE.addWorkUnit(() -> this.processDirtyChunk(chunk));
        }

        WorkQueue.WORK_QUEUE.addWorkUnit(this::markAsReady);
    }

    public Block getBlock(Vector3in position) {
        if(!position.isWithinBounds(Vector3in.ZERO, this.blockDimensions)) {
            return null;
        }


        var chunkSpacePosition = new Vector3in(position).div(Chunk.DIMENSIONS);
        var localBlockPosition = new Vector3in(chunkSpacePosition).mul(Chunk.DIMENSIONS).mul(-1).add(position);

        var chunkIndex = chunkSpacePosition.serialize(this.chunkDimensions);
        var chunk = this.chunks[chunkIndex];

        var blockID = chunk.getBlockID(localBlockPosition);
        return blockID == 0 ? null : this.blockMap.get(blockID);
    }


    public void setBlock(Vector3in position, Block block) {
        if(!position.isWithinBounds(Vector3in.ZERO, this.blockDimensions)) {
            return;
        }

        var chunkSpacePosition = new Vector3in(position).div(Chunk.DIMENSIONS);
        var localBlockPosition = new Vector3in(chunkSpacePosition).mul(Chunk.DIMENSIONS).mul(-1).add(position);

        var chunkIndex = chunkSpacePosition.serialize(this.chunkDimensions);
        var chunk = this.chunks[chunkIndex];

        var madeDirty = chunk.setBlockID(localBlockPosition, block == null ? 0 : block.blockID);
        if(madeDirty) {
            WorkQueue.WORK_QUEUE.addWorkUnit(() -> this.processDirtyChunk(chunk));
        }
    }

    private void processDirtyChunk(Chunk chunk) {
        var maxIndex = Chunk.DIMENSIONS.getIntVolume();
        var position = new Vector3fl();

        MeshBuffer.DEFAULT_MESH_BUFFER.clear();
        for(var blockIndex = 0; blockIndex < maxIndex; blockIndex += 1) {
            var localBlockPosition = new Vector3in(blockIndex, Chunk.DIMENSIONS);
            var globalBlockPosition = new Vector3in(localBlockPosition).add(chunk.position);
            var block = this.getBlock(globalBlockPosition);

            if(block == null) {
                continue;
            }

            for(var cubeFace : CubeFace.CUBE_FACES) {
                var adjacentPosition = new Vector3in(globalBlockPosition).add(cubeFace.normal);
                var adjacentBlock = this.getBlock(adjacentPosition);

                if(adjacentBlock != null) {
                    continue;
                }
            
                position.set(localBlockPosition);
                block.writeBlockFace(MeshBuffer.DEFAULT_MESH_BUFFER, position, cubeFace);
            }
        }

        chunk.mesh.loadDataFromMeshBuffer(MeshBuffer.DEFAULT_MESH_BUFFER);
        chunk.isDirty = false;
    }

    private void renderChunks() {
        var positionCursor = new Vector3fl();
        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunk = this.chunks[ix];
            positionCursor.set(chunk.position);
            chunk.mesh.render(positionCursor, Vector3fl.ZERO, Vector3fl.ONE, Vector3fl.WHITE);
        }
    }

    public void render() {
        this.texture.useTexture();
        this.renderChunks();
    }

    public void tearDown() {
    }

}
