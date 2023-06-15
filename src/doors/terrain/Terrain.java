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
import doors.graphics.ModelMesh;
import doors.graphics.TextureChannel;
import doors.graphics.TextureSampler;
import doors.graphics.ImageTexture;
import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.Maths;

public class Terrain {

    public static Map<String, Terrain> TERRAIN_LOOKUP = new HashMap<>();
    
    private static Vector3i MAX_CHUNK_SPACE_DIMENSIONS = new Vector3i(8, 8, 8);
    private static Vector3i MAX_DIMENSIONS = new Vector3i(MAX_CHUNK_SPACE_DIMENSIONS).mul(Chunk.DIMENSIONS);
    private static TextureSampler TERRAIN_SAMPLER = new TextureSampler(TextureChannel.TERRAIN_TEXTURE_CHANNEL, new Vector2i(512, 512));

    public Map<Integer, Block> blockMap;
    public ImageTexture texture;

    private Chunk[] chunks;
    private MeshBuffer meshBuffer;
    public String terrainDirectory;
    public Map<String, Door> doors;
    public Door openDoor = null;

    public Terrain(String terrainDirectory) {
        this.terrainDirectory = terrainDirectory;
        this.doors = new HashMap<>();
        this.blockMap = new HashMap<>();
        this.chunks = new Chunk[Maths.volume(MAX_CHUNK_SPACE_DIMENSIONS)];
        this.meshBuffer = new MeshBuffer(Maths.volume(Chunk.DIMENSIONS) * 6);
        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunkPosition = Maths.deserialize(ix, MAX_CHUNK_SPACE_DIMENSIONS).mul(Chunk.DIMENSIONS);
            this.chunks[ix] = new Chunk(chunkPosition);
        }

        TERRAIN_LOOKUP.put(terrainDirectory, this);
    }

    public void setup() {
        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunk = this.chunks[ix];
            chunk.setup();
            var levelDataPath = Paths.get(this.terrainDirectory, String.format("level/chunk-%03d.blob", ix));
            Game.GAME.workQueue.add(() -> chunk.loadFromFile(levelDataPath.toString()));
            Game.GAME.workQueue.add(() -> this.processDirtyChunk(chunk));
        }

        var configPath = Paths.get(this.terrainDirectory, "config.json").toString();
        var configString = FileIO.loadText(configPath);
        var json = new JSONObject(configString);

        var texturePath = Paths.get(this.terrainDirectory, "atlas.png").toString();
        this.texture = new ImageTexture(texturePath);
        Game.GAME.workQueue.add(() -> this.texture.setup());

        var blocks = json.getJSONArray("blocks");
        for(var ix = 0; ix < blocks.length(); ix += 1) {
            var block = blocks.getJSONObject(ix);
            var textureSample = block.getJSONArray("texture_sample");
            this.blockMap.put(ix + 1, new Block(
                ix + 1,
                block.getString("name"),
                TERRAIN_SAMPLER.createTextureSample(
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

        var doors = json.getJSONObject("doors");
        for(var doorName : doors.keySet()) {
            var doorConfig = doors.getJSONObject(doorName);
            var position = doorConfig.getJSONArray("position");
            var target = doorConfig.getJSONObject("target");
            var door = new Door(
                this,
                target.getString("terrain"),
                target.getString("door"),
                new Vector3f(
                    position.getInt(0),
                    position.getInt(1),
                    position.getInt(2)
                ),
                CubeFace.CUBE_FACE_MAP.get(doorConfig.getString("orientation"))
            );
            this.doors.put(doorName, door);
            this.openDoor = door;
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
                var adjacentPosition = new Vector3i(globalBlockPosition).add(cubeFace.normalI);
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
        TextureChannel.TERRAIN_TEXTURE_CHANNEL.useTexture(this.texture);
        var positionCursor = new Vector3f();
        
        for(var ix = 0; ix < this.chunks.length; ix += 1) {
            var chunk = this.chunks[ix];
            positionCursor.set(chunk.position);
            chunk.mesh.render(positionCursor, Maths.VEC3F_ZERO, Maths.VEC3F_ONE, Maths.VEC3F_ONE);
        }
    }

    public void renderPortal() {
        ModelMesh.PORTAL.render(this.openDoor.position, this.openDoor.orientation.rotation, Maths.VEC3F_ONE, Maths.VEC3F_ONE);
    }

    public void simulate() {
        for(var door : this.doors.values()) {
            door.simulate();
        }
    }

}
