package periwinkle.terrain;

import periwinkle.Game;
import periwinkle.graphics.MeshBuffer;
import periwinkle.utility.CubeFace;
import org.joml.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;

public class World {

    public static Vector3i WORLD_CHUNK_DIMENSIONS = new Vector3i(12, 12, 12);
    public static Vector3i WORLD_BLOCK_DIMENSIONS = new Vector3i(WORLD_CHUNK_DIMENSIONS).mul(Chunk.BLOCK_LENGTH);
    public static int NUM_CHUNKS_PER_WORLD = WORLD_CHUNK_DIMENSIONS.x * WORLD_CHUNK_DIMENSIONS.y * WORLD_CHUNK_DIMENSIONS.z;
    public static int NUM_BLOCKS_PER_HORIZONTAL_SLICE = WORLD_BLOCK_DIMENSIONS.x * WORLD_BLOCK_DIMENSIONS.z;
    public static int MAX_QUADS = Chunk.NUM_BLOCKS * 6;

    private static float MIN_GLOBAL_LIGHT = 0.1f;
    private static float MIN_LOCAL_LIGHT = 0.1f;
    private static Vector3f COLOR_WHITE = new Vector3f(1f, 1f, 1f);

    private final Chunk[] chunks = new Chunk[NUM_CHUNKS_PER_WORLD];
    private final MeshBuffer chunkMeshBuffer = new MeshBuffer(MAX_QUADS);
    private Queue<Vector3i> dirtyChunkQueue = new LinkedList<>();

    private Queue<Vector4i> localPropagationQueue = new LinkedList<>();
    private Queue<Vector4i> localResectionQueue = new LinkedList<>();

    private Queue<Vector4i> globalPropagationQueue = new LinkedList<>();
    private Queue<Vector4i> globalResectionQueue = new LinkedList<>();

    private int[] heightMap = new int[NUM_BLOCKS_PER_HORIZONTAL_SLICE];

    private boolean isUpdatesEnabled;

    public World() {
        for(var ix = 0; ix < NUM_CHUNKS_PER_WORLD; ix += 1)
            this.chunks[ix] = new Chunk();
    }

    public void writeToStream(DataOutputStream stream) throws IOException {
        var position = new Vector3i();
        for(var x = 0; x < WORLD_BLOCK_DIMENSIONS.x; x += 1) {
            for(var y = 0; y < WORLD_BLOCK_DIMENSIONS.y; y += 1) {
                for(var z = 0; z < WORLD_BLOCK_DIMENSIONS.z; z += 1) {
                    position.set(x,y,z);
                    var buffer = this.getBlock(position);
                    stream.writeInt(buffer.blockType.blockID);
                }
            }
        }
    }

    public void readFromStream(DataInputStream stream) throws IOException {
        var position = new Vector3i();
        Game.WORLD.disableUpdates();
        for(var x = 0; x < WORLD_BLOCK_DIMENSIONS.x; x += 1) {
            for(var y = 0; y < WORLD_BLOCK_DIMENSIONS.y; y += 1) {
                for(var z = 0; z < WORLD_BLOCK_DIMENSIONS.z; z += 1) {
                    var blockID = stream.readInt();
                    var blockType = BlockType.BLOCK_ID_LOOKUP.get(blockID);
                    position.set(x,y,z);
                    this.setBlock(position, blockType);
                }
            }
        }
    }

    public void setup() {
        for(var ix = 0; ix < NUM_CHUNKS_PER_WORLD; ix += 1)
            this.chunks[ix].mesh.setup();
    }

    public boolean withinBounds(Vector2i v) {
        return (
            v.x >= 0 && v.x < WORLD_BLOCK_DIMENSIONS.x &&
            v.y >= 0 && v.y < WORLD_BLOCK_DIMENSIONS.z
        );
    }

    public boolean withinBounds(Vector3i v) {
        return (
            v.x >= 0 && v.x < WORLD_BLOCK_DIMENSIONS.x &&
            v.y >= 0 && v.y < WORLD_BLOCK_DIMENSIONS.y &&
            v.z >= 0 && v.z < WORLD_BLOCK_DIMENSIONS.z
        );
    }

    private int getIndex(Vector3i vec) {
        return vec.x + vec.z * WORLD_CHUNK_DIMENSIONS.x + vec.y * WORLD_CHUNK_DIMENSIONS.x * WORLD_CHUNK_DIMENSIONS.z;
    }

    public int getHeight(Vector2i globalPosition) {
        if(!this.withinBounds(globalPosition))
            return Integer.MIN_VALUE;
        var ix = this.getHeightMapIndex(globalPosition);
        return this.heightMap[ix];
    }

    private int getHeightMapIndex(Vector2i vec) {
        return vec.x + vec.y * WORLD_BLOCK_DIMENSIONS.x;
    }

    private Vector3i getChunkPosition(Vector3i globalPosition) {
        return new Vector3i(globalPosition).div(Chunk.BLOCK_LENGTH);
    }

    private Vector3i getBlockPosition(Vector3i globalPosition) {
        return new Vector3i(
            globalPosition.x % Chunk.BLOCK_LENGTH,
            globalPosition.y % Chunk.BLOCK_LENGTH,
            globalPosition.z % Chunk.BLOCK_LENGTH
        );
    }

    public Chunk getChunk(Vector3i chunkPosition) {
        var ix = this.getIndex(chunkPosition);
        return this.chunks[ix];
    }

    private int calculateHeight(Vector2i globalPosition) {
        var heightCursor = new Vector3i(globalPosition.x, 0, globalPosition.y);
        for (var y = WORLD_BLOCK_DIMENSIONS.y - 1; y >= 0; y -= 1) {
            heightCursor.y = y;
            if (this.getBlock(heightCursor).blockType.blockFormat != BlockFormat.VOID)
                return y;
        }
        return 0;
    }

    public void setBlock(Vector3i globalPosition, BlockBuffer buffer) {
        var chunkPosition = this.getChunkPosition(globalPosition);
        var blockPosition = this.getBlockPosition(globalPosition);
        var chunk = this.getChunk(chunkPosition);

        var oldValue = chunk.getValue(blockPosition);
        var newValue = buffer.pack();

        chunk.setValue(blockPosition, newValue);
        for(var cubeFace : CubeFace.CUBE_FACES) {
            var offsetPosition = new Vector3i(globalPosition).add(cubeFace.normal);
            if(!this.withinBounds(offsetPosition))
                continue;
            var offsetChunkPosition = this.getChunkPosition(offsetPosition);
            var offsetChunk = this.getChunk(offsetChunkPosition);
            if(!offsetChunk.isDirty && oldValue != newValue) {
                this.dirtyChunkQueue.add(offsetChunkPosition);
                offsetChunk.isDirty = true;
            }
        }
    }

    public void setBlock(Vector3i globalPosition, BlockType blockType) {
        if(!this.withinBounds(globalPosition))
            return;

        var oldBlock = this.getBlock(globalPosition);
        var newBlock = new BlockBuffer(blockType);

        this.setBlock(globalPosition, newBlock);

        if(this.isUpdatesEnabled) {
            this.localResectionQueue.add(new Vector4i(globalPosition, oldBlock.localLight));
            this.globalResectionQueue.add(new Vector4i(globalPosition, oldBlock.globalLight));
            this.localPropagationQueue.add(new Vector4i(globalPosition, blockType.localLight));

            var coordinate = new Vector2i(globalPosition.x, globalPosition.z);
            var ix = this.getHeightMapIndex(coordinate);
            this.heightMap[ix] = this.calculateHeight(coordinate);
        }
    }

    public BlockBuffer getBlock(Vector3i globalPosition) {
        if(!this.withinBounds(globalPosition))
            return new BlockBuffer();

        var chunkPosition = this.getChunkPosition(globalPosition);
        var blockPosition = this.getBlockPosition(globalPosition);
        var packedValue = this.getChunk(chunkPosition).getValue(blockPosition);
        return new BlockBuffer(packedValue);
    }

    private void buildSolidBlock(BlockBuffer blockBuffer, Vector3i sourcePosition, Vector3i localPosition) {
        for (var cubeFace : CubeFace.CUBE_FACES) {
            var adjacentPosition = new Vector3i(sourcePosition).add(cubeFace.normal);
            var adjacentBlock = this.getBlock(adjacentPosition);
            if(adjacentBlock.blockType.blockFormat == BlockFormat.SOLID)
                continue;

            for (var ix = 0; ix < cubeFace.vertices.length; ix += 1) {
                var vertex = cubeFace.vertices[ix];
                this.chunkMeshBuffer.position.set(localPosition).add(vertex);
                this.chunkMeshBuffer.normal.set(cubeFace.normal);
                this.chunkMeshBuffer.textureOffset.set(blockBuffer.blockType.texture.vertices[ix]);
                var localLight = Math.max(adjacentBlock.localLight / 15f, MIN_LOCAL_LIGHT);
                this.chunkMeshBuffer.localLight = localLight;
                var globalLight = Math.max(adjacentBlock.globalLight / 15f, MIN_GLOBAL_LIGHT);
                this.chunkMeshBuffer.globalLight = globalLight;
                this.chunkMeshBuffer.color = COLOR_WHITE;
                this.chunkMeshBuffer.push();
            }
        }

    }

    private void buildCrossBlock(BlockBuffer blockBuffer, Vector3i sourcePosition, Vector3i localPosition) {
        for (var cubeFace : CubeFace.HORIZONTAL_CUBE_FACES) {
            for (var ix = 0; ix < cubeFace.vertices.length; ix += 1) {
                var normal = new Vector3f(cubeFace.normal);
                var vertex = new Vector3f(cubeFace.vertices[ix])
                        .mul(2f)
                        .sub(1f, 1f, 1f)
                        .sub(normal)
                        .rotateY(45)
                        .mul(0.5f)
                        .add(0.5f, 0.5f, 0.5f);

                this.chunkMeshBuffer.position.set(localPosition).add(vertex);
                this.chunkMeshBuffer.normal.set(cubeFace.normal).rotateY(45);
                this.chunkMeshBuffer.textureOffset.set(blockBuffer.blockType.texture.vertices[ix]);

                var localLight = Math.max(blockBuffer.localLight / 15f, MIN_LOCAL_LIGHT);
                this.chunkMeshBuffer.localLight = localLight;

                var globalLight = Math.max(blockBuffer.globalLight / 15f, MIN_GLOBAL_LIGHT);
                this.chunkMeshBuffer.globalLight = globalLight;
                this.chunkMeshBuffer.color = COLOR_WHITE;
                this.chunkMeshBuffer.push();
            }
        }
    }

    private void buildChunkMesh(Vector3i chunkPosition) {
        var chunk = this.getChunk(chunkPosition);
        this.chunkMeshBuffer.clear();
        for(var x = 0; x < Chunk.BLOCK_LENGTH; x += 1) {
            for (var y = 0; y < Chunk.BLOCK_LENGTH; y += 1) {
                for (var z = 0; z < Chunk.BLOCK_LENGTH; z += 1) {
                    var sourcePosition = new Vector3i(chunkPosition).mul(Chunk.BLOCK_LENGTH).add(x, y, z);
                    var sourceBlock = this.getBlock(sourcePosition);
                    var localPosition = new Vector3i(x, y, z);
                    if(sourceBlock.blockType.blockFormat == BlockFormat.VOID)
                        continue;
                    else if(sourceBlock.blockType.blockFormat == BlockFormat.SOLID)
                        this.buildSolidBlock(sourceBlock, sourcePosition, localPosition);
                    else if(sourceBlock.blockType.blockFormat == BlockFormat.CROSS)
                        this.buildCrossBlock(sourceBlock, sourcePosition, localPosition);
                }
            }
        }
        this.chunkMeshBuffer.flip();
        chunk.mesh.loadFromBuffer(this.chunkMeshBuffer);
        chunk.isDirty = false;
    }

    public void simulate() {
        while(!this.localResectionQueue.isEmpty()) {
            var sourceData = this.localResectionQueue.remove();
            var sourcePosition = new Vector3i(sourceData.x, sourceData.y, sourceData.z);
            this.processLocalResection(sourcePosition, sourceData.w);
        }

        while(!this.localPropagationQueue.isEmpty()) {
            var sourceData = this.localPropagationQueue.remove();
            var sourcePosition = new Vector3i(sourceData.x, sourceData.y, sourceData.z);
            this.processLocalPropagation(sourcePosition, sourceData.w);
        }

        while(!this.globalResectionQueue.isEmpty()) {
            var sourceData = this.globalResectionQueue.remove();
            var sourcePosition = new Vector3i(sourceData.x, sourceData.y, sourceData.z);
            this.processGlobalResection(sourcePosition, sourceData.w);
        }

        while(!this.globalPropagationQueue.isEmpty()) {
            var sourceData = this.globalPropagationQueue.remove();
            var sourcePosition = new Vector3i(sourceData.x, sourceData.y, sourceData.z);
            this.processGlobalPropagation(sourcePosition, sourceData.w);
        }

        while(!this.dirtyChunkQueue.isEmpty())
            this.buildChunkMesh(this.dirtyChunkQueue.remove());

    }

    public void render() {
        Game.SHADER.setGlobalColor(Game.SKY_TYPE.skyColor);
        for(var x = 0; x < WORLD_CHUNK_DIMENSIONS.x; x += 1) {
            for(var y = 0; y < WORLD_CHUNK_DIMENSIONS.y; y += 1) {
                for(var z = 0; z < WORLD_CHUNK_DIMENSIONS.z; z += 1 ) {
                    var chunkPosition = new Vector3i(x,y,z);
                    var globalPosition = new Vector3f(chunkPosition).mul(Chunk.BLOCK_LENGTH);
                    var chunk = this.getChunk(chunkPosition);
                    chunk.mesh.render(globalPosition, Game.SKY_TYPE.skyColor, 0.1f);
                }
            }
        }
    }

    public void disableUpdates() {
        this.isUpdatesEnabled = false;
    }

    public void enableUpdates() {
        this.localPropagationQueue.clear();
        this.localResectionQueue.clear();
        this.globalPropagationQueue.clear();
        this.globalResectionQueue.clear();
        for(var x = 0; x < WORLD_BLOCK_DIMENSIONS.x; x += 1) {
            for (var z = 0; z < WORLD_BLOCK_DIMENSIONS.z; z += 1) {
                for(var y = 0; y < WORLD_BLOCK_DIMENSIONS.y; y += 1) {
                    var position = new Vector3i(x, y, z);
                    var blockBuffer = this.getBlock(position);
                    blockBuffer.localLight = 0;
                    blockBuffer.globalLight = 0;
                    this.setBlock(position, blockBuffer);
                    this.localPropagationQueue.add(new Vector4i(position, blockBuffer.blockType.localLight));
                }
                var coordinate = new Vector2i(x, z);
                var ix = this.getHeightMapIndex(coordinate);
                this.heightMap[ix] = this.calculateHeight(coordinate);

                var position = new Vector3i(x, WORLD_BLOCK_DIMENSIONS.y - 1, z);
                this.globalPropagationQueue.add(new Vector4i(position, 15));
            }
        }
        this.isUpdatesEnabled = true;
    }

    public boolean terrainCollision(Vector3f position, Vector3f dimensions) {
        var startX = (int)(position.x);
        var startY = (int)(position.y);
        var startZ = (int)(position.z);

        var endX = (int)(position.x + dimensions.x);
        var endY = (int)(position.y + dimensions.y);
        var endZ = (int)(position.z + dimensions.z);

        for(var x = startX; x <= endX; x += 1) {
            for(var y = startY; y <= endY; y += 1) {
                for(var z = startZ; z <= endZ; z += 1) {
                    var blockBuffer = this.getBlock(new Vector3i(x,y,z));
                    if(blockBuffer.blockType.blockFormat == BlockFormat.SOLID)
                        return true;
                }
            }
        }
        return false;
    }

    private void processLocalPropagation(Vector3i position, int incomingLight) {
        for(var cubeFace : CubeFace.CUBE_FACES) {
            var transmittedLight = Math.max(incomingLight - 1, 0);
            var adjacentPosition = new Vector3i(position).add(cubeFace.normal);
            var adjacentBlock = this.getBlock(adjacentPosition);
            var light = adjacentBlock.localLight;

            if(!this.withinBounds(adjacentPosition))
                continue;

            if(transmittedLight == 0)
                continue;

            if(adjacentBlock.blockType.blockFormat == BlockFormat.SOLID)
                continue;

            if(light >= transmittedLight)
                continue;

            this.localPropagationQueue.add(new Vector4i(adjacentPosition, transmittedLight));
            adjacentBlock.localLight = transmittedLight;
            this.setBlock(adjacentPosition, adjacentBlock);
        }
    }

    private void processLocalResection(Vector3i position, int previousIncomingLight) {
        for(var cubeFace : CubeFace.CUBE_FACES) {
            var previousTransmittedLight = Math.max(previousIncomingLight - 1, 0);
            var adjacentPosition = new Vector3i(position).add(cubeFace.normal);
            var adjacentBlock = this.getBlock(adjacentPosition);
            var light = adjacentBlock.localLight;

            if(!this.withinBounds(adjacentPosition))
                continue;

            if(light == 0)
                continue;

            if(adjacentBlock.blockType.blockFormat == BlockFormat.SOLID) {
                this.localPropagationQueue.add(new Vector4i(adjacentPosition, light));
                continue;
            }

            if(light > previousTransmittedLight) {
                this.localPropagationQueue.add(new Vector4i(adjacentPosition, light));
                continue;
            }

            this.localResectionQueue.add(new Vector4i(adjacentPosition, light));
            adjacentBlock.localLight = 0;
            this.setBlock(adjacentPosition, adjacentBlock);
        }
    }

    private void processGlobalPropagation(Vector3i position, int incomingLight) {
        for(var cubeFace : CubeFace.CUBE_FACES) {
            var transmittedLight = cubeFace == CubeFace.BOTTOM ? incomingLight : Math.max(incomingLight - 1, 0);
            var adjacentPosition = new Vector3i(position).add(cubeFace.normal);
            var adjacentBlock = this.getBlock(adjacentPosition);
            var light = adjacentBlock.globalLight;

            if(!this.withinBounds(adjacentPosition))
                continue;

            if(transmittedLight == 0)
                continue;

            if(adjacentBlock.blockType.blockFormat == BlockFormat.SOLID)
                continue;

            if(light >= transmittedLight)
                continue;

            this.globalPropagationQueue.add(new Vector4i(adjacentPosition, transmittedLight));
            adjacentBlock.globalLight = transmittedLight;
            this.setBlock(adjacentPosition, adjacentBlock);
        }
    }

    private void processGlobalResection(Vector3i position, int previousIncomingLight) {
        for(var cubeFace : CubeFace.CUBE_FACES) {
            var previousTransmittedLight = cubeFace == CubeFace.BOTTOM ? previousIncomingLight : Math.max(previousIncomingLight - 1, 0);
            var adjacentPosition = new Vector3i(position).add(cubeFace.normal);
            var adjacentBlock = this.getBlock(adjacentPosition);
            var light = adjacentBlock.globalLight;

            if(!this.withinBounds(adjacentPosition))
                continue;

            if(light == 0)
                continue;

            if(adjacentBlock.blockType.blockFormat == BlockFormat.SOLID) {
                this.globalPropagationQueue.add(new Vector4i(adjacentPosition, light));
                continue;
            }

            if(light > previousTransmittedLight) {
                this.globalPropagationQueue.add(new Vector4i(adjacentPosition, light));
                continue;
            }

            this.globalResectionQueue.add(new Vector4i(adjacentPosition, light));
            adjacentBlock.globalLight = 0;
            this.setBlock(adjacentPosition, adjacentBlock);
        }
    }


}
