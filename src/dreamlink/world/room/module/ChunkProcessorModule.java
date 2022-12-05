package dreamlink.world.room.module;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.joml.Vector3i;

import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.utility.maths.Vector3iMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.terrain.TerrainBlockData;
import dreamlink.world.room.module.terrain.TerrainChunk;

public class ChunkProcessorModule {

    private final IRoomModuleProvider provider;
    private final Queue<TerrainChunk> dirtyChunks = new ArrayDeque<>();
    private final Set<TerrainChunk> dirtyChunksSet = new HashSet<>();

    public ChunkProcessorModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    public void addDirtyChunk(TerrainChunk chunk) {
        if(this.dirtyChunksSet.contains(chunk)) {
            return;
        }
        this.dirtyChunks.add(chunk);
        this.dirtyChunksSet.add(chunk);
    }

    private final Vector3i processDirtyChunkPosition = new Vector3i();
    private final TerrainBlockData processDirtyChunkBlockData = new TerrainBlockData();

    private void processDirtyChunk() {
        var chunk = this.dirtyChunks.remove();
        this.dirtyChunksSet.remove(chunk);

        var terrainModule = this.provider.getTerrainModule();
        var blockModule = this.provider.getBlockModule();

        var meshBuffer = TerrainMeshBuffer.instance.clear();
        for(var ix = 0; ix < TerrainChunk.blockCount; ix += 1) {
            var position = Vector3iMaths.unpack(this.processDirtyChunkPosition, ix, TerrainChunk.chunkDimensions);
            position.add(chunk.position);
                
            var blockData = terrainModule.getBlockData(position, this.processDirtyChunkBlockData);
            blockModule.getBlockByID(blockData.blockID).writeToTerrainMeshBuffer(
                meshBuffer,
                position, 
                blockData
            );
        }
        chunk.mesh.buffer(meshBuffer);
    }

    public boolean doWork() {
        if(this.dirtyChunks.isEmpty()) {
            return false;
        }

        this.processDirtyChunk();
        return true;

    }
    
}
