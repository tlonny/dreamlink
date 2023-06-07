package doors.terrain;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.json.JSONObject;

import doors.Game;
import doors.graphics.MeshBuffer;
import doors.graphics.ShaderProgram;
import doors.graphics.TextureChannel;
import doors.graphics.TextureData;
import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.Maths;

public class Terrain {
    
    private static Vector3i MAX_CHUNK_SPACE_DIMENSIONS = new Vector3i(8, 8, 8);
    private static Vector3i MAX_DIMENSIONS = new Vector3i(MAX_CHUNK_SPACE_DIMENSIONS).mul(Chunk.DIMENSIONS);
    public static Vector2i TEXTURE_DIMENSIONS = new Vector2i(512, 512);

    public Map<Integer, Block> blockMap;
    public TextureData textureData;
    public Vector3f position;

    private Chunk[] chunks;
    private MeshBuffer meshBuffer;

    public Terrain() {
        this.blockMap = new HashMap<>();
        this.position = new Vector3f();
        this.chunks = new Chunk[Maths.volume(MAX_CHUNK_SPACE_DIMENSIONS)];
        this.meshBuffer = new MeshBuffer(Maths.volume(Chunk.DIMENSIONS) * 6);
        this.textureData = new TextureData(TEXTURE_DIMENSIONS);
        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunkPosition = Maths.deserialize(ix, MAX_CHUNK_SPACE_DIMENSIONS).mul(Chunk.DIMENSIONS);
            this.chunks[ix] = new Chunk(chunkPosition);
        }
    }

    public void setup(String terrainDirectory) {
        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunk = this.chunks[ix];
            chunk.setup();
            var levelDataPath = Paths.get(terrainDirectory, String.format("level/chunk-%03d.blob", ix));
            Game.GAME.workQueue.add(() -> chunk.loadFromFile(levelDataPath.toString()));
            Game.GAME.workQueue.add(() -> this.processDirtyChunk(chunk));
        }

        var configPath = Paths.get(terrainDirectory, "config.json").toString();
        var configString = FileIO.loadText(configPath);
        var json = new JSONObject(configString);

        var texturePath = Paths.get(terrainDirectory, "atlas.png").toString();
        this.textureData.setup(texturePath);

        var blocks = json.getJSONArray("blocks");
        for(var ix = 0; ix < blocks.length(); ix += 1) {
            var block = blocks.getJSONObject(ix);
            var textureSample = block.getJSONArray("texture_sample");
            this.blockMap.put(ix + 1, new Block(
                ix + 1,
                block.getString("name"),
                this.textureData.createTextureSample(
                    new Vector2i(
                        textureSample.getInt(0),
                        textureSample.getInt(1)
                    ),
                    new Vector2i(
                        textureSample.getInt(2),
                        textureSample.getInt(3)
                    )
                )
            ));
        }

    }

    public boolean isCollision(Vector3f position, Vector3f dimensions) {
        var startX = (int)(position.x);
        var startY = (int)(position.y);
        var startZ = (int)(position.z);

        var endX = (int)(position.x + dimensions.x);
        var endY = (int)(position.y + dimensions.y);
        var endZ = (int)(position.z + dimensions.z);

        var cursor = new Vector3i();

        for(var x = startX; x <= endX; x += 1) {
            for(var y = startY; y <= endY; y += 1) {
                for(var z = startZ; z <= endZ; z += 1) {
                    cursor.set(x,y,z);
                    if(this.getBlock(cursor) != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Block getBlock(Vector3i position) {
        if(!Maths.isWithinBounds(position, MAX_DIMENSIONS)) {
            return null;
        }

        var chunkSpacePosition = Maths.div(new Vector3i(position), Chunk.DIMENSIONS);
        var localBlockPosition = new Vector3i(chunkSpacePosition).mul(Chunk.DIMENSIONS).mul(-1).add(position);

        var chunkIndex = Maths.serialize(chunkSpacePosition, MAX_CHUNK_SPACE_DIMENSIONS);
        var chunk = this.chunks[chunkIndex];

        var blockID = chunk.getBlockID(localBlockPosition);
        return blockID == 0 ? null : this.blockMap.get(blockID);
    }


    public void setBlock(Vector3i position, Block block) {
        if(!Maths.isWithinBounds(position, MAX_DIMENSIONS)) {
            return;
        }

        var chunkSpacePosition = Maths.div(new Vector3i(position), Chunk.DIMENSIONS);
        var localBlockPosition = new Vector3i(chunkSpacePosition).mul(Chunk.DIMENSIONS).mul(-1).add(position);

        var chunkIndex = Maths.serialize(chunkSpacePosition, MAX_CHUNK_SPACE_DIMENSIONS);
        var chunk = this.chunks[chunkIndex];

        var madeDirty = chunk.setBlockID(localBlockPosition, block == null ? 0 : block.blockID);
        if(madeDirty) {
            Game.GAME.workQueue.add(() -> this.processDirtyChunk(chunk));
        }
    }

    private void processDirtyChunk(Chunk chunk) {
        var writer = new BlockFaceMeshBufferWriter(this.meshBuffer);
        var maxIndex = Maths.volume(Chunk.DIMENSIONS);
        for(var blockIndex = 0; blockIndex < maxIndex; blockIndex += 1) {
            var localBlockPosition = Maths.deserialize(blockIndex, Chunk.DIMENSIONS);
            var globalBlockPosition = new Vector3i(localBlockPosition).add(chunk.position);
            var block = this.getBlock(globalBlockPosition);

            if(block == null) {
                continue;
            }

            for(var cubeFace : CubeFace.CUBE_FACES) {
                var adjacentPosition = new Vector3i(globalBlockPosition).add(cubeFace.normal);
                var adjacentBlock = this.getBlock(adjacentPosition);

                if(adjacentBlock != null) {
                    continue;
                }

                writer.pushBlockFace(localBlockPosition, cubeFace, block, Maths.VEC3F_ONE);
            }
        }

        this.meshBuffer.flip();
        chunk.mesh.loadFromMeshBuffer(this.meshBuffer);
        this.meshBuffer.clear();
        chunk.isDirty = false;
    }

    public void render() {
        TextureChannel.TERRAIN_TEXTURE_CHANNEL.setTexture(this.textureData);
        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunk = this.chunks[ix];
            ShaderProgram.setModel(new Vector3f(chunk.position).add(this.position), Maths.VEC3F_ONE);
            chunk.mesh.render();
        }
    }

}
