package doors.level;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import doors.Doors;
import doors.core.WorkQueue;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.ImageTexture;
import doors.core.utility.CubeFace;
import doors.core.utility.FileIO;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3in;

public class Level {

    private ImageTexture texture;
    private Vector3in chunkDimensions;
    private Vector3in blockDimensions;
    private Chunk[] chunks;
    private BlockFaceMeshBufferWriter blockWriter;

    public String levelDirectory;
    public Map<Integer, Block> blockMap;
    public Map<String, Door> doors;

    public Level(String levelDirectory) {
        this.levelDirectory = levelDirectory;
        this.doors = new HashMap<>();
        this.blockMap = new HashMap<>();
        this.blockWriter = new BlockFaceMeshBufferWriter(MeshBuffer.DEFAULT_MESH_BUFFER);
    }

    private void setupBlocks(JSONObject rootConfig) {
        var texturePath = Paths.get(this.levelDirectory, "atlas.png").toString();
        var textureDimensions = new Vector2in(rootConfig.getJSONArray("textureDimensions"));

        this.texture = new ImageTexture(Doors.TEXTURE_CHANNEL_BLOCK, textureDimensions, texturePath);
        WorkQueue.WORK_QUEUE.addWorkUnit(() -> this.texture.setup());

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
    }

    private void setupChunks(JSONObject rootConfig) {
        var dimensions = rootConfig.getJSONArray("chunkDimensions");
        this.chunkDimensions = new Vector3in(dimensions);
        this.blockDimensions = new Vector3in(this.chunkDimensions).mul(Chunk.DIMENSIONS);
        this.chunks = new Chunk[this.chunkDimensions.getIntVolume()];

        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunkPosition = new Vector3in(ix, this.chunkDimensions).mul(Chunk.DIMENSIONS);
            var chunkPath = Paths.get(this.levelDirectory, String.format("level/chunk-%03d.blob", ix));
            var chunk = new Chunk(chunkPosition, chunkPath.toString());
            this.chunks[ix] = chunk;
            WorkQueue.WORK_QUEUE.addWorkUnit(() -> chunk.setup());
        }

        // Make sure chunks are processed after all chunks are setup to ensure the
        // no redundant mesh is created at chunk boundaries.
        for(var chunk : this.chunks) {
            WorkQueue.WORK_QUEUE.addWorkUnit(() -> this.processDirtyChunk(chunk));
        }
    }

    private void setupEntities(JSONObject entitiesConfig) {
        var doorsConfig = entitiesConfig.getJSONObject("doors");
        for(var doorName : doorsConfig.keySet()) {
            var doorConfig = doorsConfig.getJSONObject(doorName);
            var position = doorConfig.getJSONArray("position");
            var target = doorConfig.getJSONObject("target");
            var door = new Door(
                target.getString("level"),
                target.getString("door"),
                new Vector3fl(
                    position.getFloat(0),
                    position.getFloat(1),
                    position.getFloat(2)
                ),
                CubeFace.CUBE_FACE_MAP.get(doorConfig.getString("orientation"))
            );
            this.doors.put(doorName, door);
        }
    }

    public void setup() {
        var configPath = Paths.get(this.levelDirectory, "config.json").toString();
        var configString = FileIO.loadText(configPath);
        var rootConfig = new JSONObject(configString);

        var entitiesPath = Paths.get(this.levelDirectory, "entities.json").toString();
        var entitiesString = FileIO.loadText(entitiesPath);
        var entitiesConfig = new JSONObject(entitiesString);
        
        this.setupBlocks(rootConfig);
        this.setupChunks(rootConfig);
        this.setupEntities(entitiesConfig);
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
                this.blockWriter.writeBlockFace(position, cubeFace, block);
            }
        }

        MeshBuffer.DEFAULT_MESH_BUFFER.flip();
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
