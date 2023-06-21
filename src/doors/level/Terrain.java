package doors.level;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import doors.WorkQueue;
import doors.graphics.mesh.MeshBuffer;
import doors.graphics.mesh.ModelMesh;
import doors.graphics.texture.ImageTexture;
import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSampler;
import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;
import doors.utility.geometry.Vector3in;

public class Terrain {

    public static Terrain CURRENT_TERRAIN = null;

    public Map<Integer, Block> blockMap;
    public ImageTexture texture;

    private Vector3in chunkDimensions;
    private Vector3in blockDimensions;

    private Chunk[] chunks;
    private MeshBuffer meshBuffer;

    public String terrainDirectory;
    public Map<String, Door> doors;
    public Portal openPortal = null;

    public Terrain(String terrainDirectory) {
        this.terrainDirectory = terrainDirectory;
        this.doors = new HashMap<>();
        this.blockMap = new HashMap<>();
        this.meshBuffer = new MeshBuffer(Chunk.DIMENSIONS.volume() * 6);
    }

    public void use() {
        CURRENT_TERRAIN = this;
    }

    public boolean isCurrentTerrain() {
        return CURRENT_TERRAIN == this;
    }

    private void setupBlocks() {
        var blockConfigPath = Paths.get(this.terrainDirectory, "blocks/blocks.json").toString();
        var blockConfigString = FileIO.loadText(blockConfigPath);
        var blocksJSON = new JSONObject(blockConfigString);

        var texturePath = Paths.get(this.terrainDirectory, "blocks/atlas.png").toString();
        this.texture = new ImageTexture(texturePath);
        WorkQueue.WORK_QUEUE.addWorkUnit(() -> this.texture.setup());

        var textureDimensions = blocksJSON.getJSONArray("texture_dimensions");
        var textureSampler = new TextureSampler(new Vector2in(
            textureDimensions.getInt(0),
            textureDimensions.getInt(1)
        ));

        var blocks = blocksJSON.getJSONArray("blocks");
        for(var ix = 0; ix < blocks.length(); ix += 1) {
            var block = blocks.getJSONObject(ix);
            var textureSample = block.getJSONArray("texture_sample");
            this.blockMap.put(ix + 1, new Block(
                ix + 1,
                block.getString("name"),
                textureSampler.createTextureSample(
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

    public void setup() {
        this.setupBlocks();

        var configPath = Paths.get(this.terrainDirectory, "config.json").toString();
        var configString = FileIO.loadText(configPath);
        var json = new JSONObject(configString);

        var dimensions = json.getJSONArray("chunk_dimensions");
        this.chunkDimensions = new Vector3in(
            dimensions.getInt(0),
            dimensions.getInt(1),
            dimensions.getInt(2)
        );
        this.blockDimensions = new Vector3in(this.chunkDimensions).mul(Chunk.DIMENSIONS);

        this.chunks = new Chunk[this.chunkDimensions.volume()];
        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunkPosition = new Vector3in(ix, this.chunkDimensions).mul(Chunk.DIMENSIONS);
            var chunkPath = Paths.get(this.terrainDirectory, String.format("level/chunk-%03d.blob", ix));
            var chunk = new Chunk(chunkPosition, chunkPath.toString());
            this.chunks[ix] = chunk;
            WorkQueue.WORK_QUEUE.addWorkUnit(() -> chunk.setup());
        }

        // Make sure chunks are processed after all chunks are setup to ensure the
        // no redundant mesh is created at chunk boundaries.
        for(var chunk : this.chunks) {
            WorkQueue.WORK_QUEUE.addWorkUnit(() -> this.processDirtyChunk(chunk));
        }

        var doors = json.getJSONObject("doors");
        for(var doorName : doors.keySet()) {
            var doorConfig = doors.getJSONObject(doorName);
            var position = doorConfig.getJSONArray("position");
            var target = doorConfig.getJSONObject("target");
            var door = new Door(
                this,
                target.getString("terrain"),
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

    public Block getBlock(Vector3in position) {
        if(!this.blockDimensions.isWithinBounds(position)) {
            return null;
        }

        var chunkSpacePosition = new Vector3in(position).div(Chunk.DIMENSIONS);
        var localBlockPosition = chunkSpacePosition.mul(Chunk.DIMENSIONS).mul(-1).add(position);

        var chunkIndex = chunkSpacePosition.serialize(this.chunkDimensions);
        var chunk = this.chunks[chunkIndex];

        var blockID = chunk.getBlockID(localBlockPosition);
        return blockID == 0 ? null : this.blockMap.get(blockID);
    }


    public void setBlock(Vector3in position, Block block) {
        if(!this.blockDimensions.isWithinBounds(position)) {
            return;
        }

        var chunkSpacePosition = new Vector3in(position).div(Chunk.DIMENSIONS);
        var localBlockPosition = chunkSpacePosition.mul(Chunk.DIMENSIONS).mul(-1).add(position);

        var chunkIndex = chunkSpacePosition.serialize(this.chunkDimensions);
        var chunk = this.chunks[chunkIndex];

        var madeDirty = chunk.setBlockID(localBlockPosition, block == null ? 0 : block.blockID);
        if(madeDirty) {
            WorkQueue.WORK_QUEUE.addWorkUnit(() -> this.processDirtyChunk(chunk));
        }
    }

    private void processDirtyChunk(Chunk chunk) {
        var maxIndex = Chunk.DIMENSIONS.volume();
        var position = new Vector3fl();
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
                block.writeBlockFace(this.meshBuffer, position, cubeFace, block);
            }
        }

        this.meshBuffer.flip();
        chunk.mesh.loadFromMeshBuffer(this.meshBuffer);
        this.meshBuffer.clear();
        chunk.isDirty = false;
    }

    private void renderChunks() {
        var positionCursor = new Vector3fl();
        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunk = this.chunks[ix];
            positionCursor.set(chunk.position);
            chunk.mesh.render(positionCursor, Vector3fl.ZERO, Vector3fl.ONE, Vector3fl.ONE);
        }
    }

    public void render() {
        TextureChannel.TERRAIN_TEXTURE_CHANNEL.useTexture(this.texture);
        this.renderChunks();
        for(var door : this.doors.values()) {
            if(Portal.PORTAL.startDoor == door) {
                continue;
            }
            ModelMesh.DOOR.render(door.position, door.orientation.rotation, Vector3fl.ONE, Vector3fl.ONE);
        }
    }

    public void renderToPortalLevel() {
        TextureChannel.TERRAIN_TEXTURE_CHANNEL.useTexture(this.texture);
        this.renderChunks();
        for(var door : this.doors.values()) {
            ModelMesh.DOOR.render(door.position, door.orientation.rotation, Vector3fl.ONE, Vector3fl.ONE);
        }
    }

    public void tearDown() {
    }

}
