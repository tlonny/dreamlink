package dreamlink.world.room.module.terrain;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.joml.Vector3i;
import org.joml.Vector3ic;

import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Vector3iMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;
import dreamlink.world.room.module.block.BarrierBlock;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.lightpropagation.LightPropagation;
import dreamlink.world.room.module.lightresection.LightResection;

public class TerrainModule {

    private static final int maxChunksInDimension = 12;
    private static final Path terrainPath = Paths.get("terrain.dat");
    private static final int dataMask = 0xFFF;

    private final IRoomModuleProvider provider;

    private boolean isWorkCompleted;
    private TerrainChunk[] chunks;
    private Vector3i dimensions;
    private Vector3i chunkSpaceDimensions;

    public TerrainModule(IRoomModuleProvider provider) {
        this.provider = provider;
        this.dimensions = new Vector3i();
        this.chunkSpaceDimensions = new Vector3i();
    }

    public Vector3i getDimensions(Vector3i target) {
        return target.set(this.dimensions);
    }

    public int getSize() {
        return this.chunks.length;
    }

    public TerrainChunk getChunkByIndex(int index) {
        return this.chunks[index];
    }

    public void loadData() throws RoomLoadException {
        var roomPath = this.provider.getIOModule().getPath();
        var blockModule = this.provider.getBlockModule();
        var processorModule = this.provider.getChunkProcessorModule();

        var terrainFile = roomPath
            .resolve(TerrainModule.terrainPath)
            .toFile();

        if(!terrainFile.exists()) {
            throw RoomLoadException.invalidTerrainData();
        }

        try(
            var fileInputStream = new FileInputStream(terrainFile);
            var bufferedInputStream = new BufferedInputStream(fileInputStream);
            var dataInputStream = new DataInputStream(fileInputStream);
        ) {
            this.chunkSpaceDimensions.set(
                dataInputStream.readInt(),
                dataInputStream.readInt(),
                dataInputStream.readInt()
            );

            var minChunks = Vector3iMaths.minComponent(this.chunkSpaceDimensions);
            var maxChunks = Vector3iMaths.maxComponent(this.chunkSpaceDimensions);

            if(minChunks < 1 || maxChunks > TerrainModule.maxChunksInDimension) {
                throw RoomLoadException.invalidTerrainData();
            }

            this.dimensions = new Vector3i()
                .set(this.chunkSpaceDimensions)
                .mul(TerrainChunk.chunkDimensions);

            // Create required empty chunks
            var numChunks = Vector3iMaths.volume(this.chunkSpaceDimensions);
            this.chunks = new TerrainChunk[numChunks];

            var chunkPosition = new Vector3i();
            var blockPosition = new Vector3i();
            var blockData = new TerrainBlockData();

            for(var ix = 0; ix < this.chunks.length; ix += 1) {
                Vector3iMaths.unpack(
                    chunkPosition,
                    ix, 
                    this.chunkSpaceDimensions
                );

                chunkPosition.mul(TerrainChunk.chunkDimensions);
                var chunk = new TerrainChunk(chunkPosition);
                this.chunks[ix] = chunk;
                processorModule.addDirtyChunk(chunk);
            }

            var numChunksInFile = dataInputStream.readInt();
            if(numChunksInFile < 0 || numChunksInFile > this.chunks.length) {
                throw RoomLoadException.invalidTerrainData();
            }

            for(var cx = 0; cx < numChunksInFile; cx += 1) {
                var chunkIndex = dataInputStream.readInt();
                if(chunkIndex < 0 || chunkIndex >= this.chunks.length) {
                    throw RoomLoadException.invalidTerrainData();
                }

                var chunk = this.chunks[chunkIndex];
                for(var bx = 0; bx < TerrainChunk.blockCount; bx += 1) {
                    chunk.setData(bx, dataInputStream.readInt() & TerrainModule.dataMask);
                }
            }

            // Iterate over non-empty chunks and trigger lighting calcs for light-enabled blocks
            for(var ix = 0; ix < this.chunks.length; ix += 1) {
                var chunk = this.chunks[ix];
                if(chunk.isEmpty()) {
                    continue;
                }

                for(var bx = 0; bx < TerrainChunk.blockCount; bx += 1) {
                    Vector3iMaths.unpack(
                        blockPosition,
                        bx, 
                        TerrainChunk.chunkDimensions
                    ).add(chunk.position);

                    this.getBlockData(blockPosition, blockData);
                    var block = blockModule.getBlockByID(blockData.blockID);
                    var hasLight = false;
                    for(var jx = 0; jx < BlockLight.getSize(); jx += 1) {
                        var blockLight = BlockLight.get(jx);
                        var lightAmount = block.getLight(blockLight);
                        hasLight |= lightAmount > 0;
                        blockData.setLight(blockLight.terrainLight, lightAmount);
                    }

                    if(hasLight) {
                        this.setBlockData(blockPosition, blockData);
                    }
                }
            }
        } catch (IOException e) {
            throw RoomLoadException.invalidTerrainData();
        }
    }

    public boolean doWork() {
        if(this.isWorkCompleted) {
            return false;
        }

        for(var chunk : this.chunks) {
            chunk.setup();
        }

        this.isWorkCompleted = true;
        return true;
    }

    public void saveData() {
        var roomPath = this.provider.getIOModule().getPath();
        var terrainFile = roomPath
            .resolve(TerrainModule.terrainPath)
            .toFile();

        terrainFile.getParentFile().mkdirs();

        try(
            var fileOutputStream = new FileOutputStream(terrainFile);
            var bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            var dataOutputStream = new DataOutputStream(fileOutputStream);
        ) {
            dataOutputStream.writeInt(this.chunkSpaceDimensions.x);
            dataOutputStream.writeInt(this.chunkSpaceDimensions.y);
            dataOutputStream.writeInt(this.chunkSpaceDimensions.z);

            var numChunksToSave = 0;
            for(var chunk : this.chunks) {
                if(!chunk.isEmpty()) {
                    numChunksToSave += 1;
                }
            }

            dataOutputStream.writeInt(numChunksToSave);

            for(var cx = 0; cx < this.chunks.length; cx += 1) {
                var chunk = this.chunks[cx];
                if(chunk.isEmpty()) {
                    continue;
                }

                dataOutputStream.writeInt(cx);
                for(var ix = 0; ix < TerrainChunk.blockCount; ix += 1) {
                    dataOutputStream.writeInt(chunk.getData(ix));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       
    }

    private Vector3i getBlockDataBlockPosition = new Vector3i();
    private Vector3i getBlockDataChunkPosition = new Vector3i();

    public TerrainBlockData getBlockData(Vector3ic position, TerrainBlockData blockData) {
        if(position.x() <= 0 || position.x() >= this.dimensions.x - 1) {
            return blockData.unpack(BarrierBlock.blockID);
        } else if(position.y() <= 0 || position.y() >= this.dimensions.y - 1) {
            return blockData.unpack(BarrierBlock.blockID);
        } else if(position.z() <= 0 || position.z() >= this.dimensions.z - 1) {
            return blockData.unpack(BarrierBlock.blockID);
        }

        var chunkPosition = Vector3iMaths.div(
            this.getBlockDataChunkPosition.set(position), 
            TerrainChunk.chunkDimensions
        );

        var chunk = this.chunks[Vector3iMaths.pack(chunkPosition, this.chunkSpaceDimensions)];

        var blockPosition = Vector3iMaths.mod(
            this.getBlockDataBlockPosition.set(position), 
            TerrainChunk.chunkDimensions
        );

        return blockData.unpack(chunk.getData(Vector3iMaths.pack(blockPosition, TerrainChunk.chunkDimensions)));
    }

    private Vector3i setBlockDataDirectBlockPosition = new Vector3i();
    private Vector3i setBlockDataDirectChunkPosition = new Vector3i();

    public void setBlockDataDirect(Vector3ic position, TerrainBlockData blockData) {
        if(position.x() <= 0 || position.x() >= this.dimensions.x - 1) {
            return;
        } else if(position.y() <= 0 || position.y() >= this.dimensions.y - 1) {
            return;
        } else if(position.z() <= 0 || position.z() >= this.dimensions.z - 1) {
            return;
        }

        var chunkPosition = Vector3iMaths.div(
            this.setBlockDataDirectChunkPosition.set(position), 
            TerrainChunk.chunkDimensions
        );
        var chunk = this.chunks[Vector3iMaths.pack(chunkPosition, this.chunkSpaceDimensions)];
        var processorModule = this.provider.getChunkProcessorModule();

        var blockPosition = Vector3iMaths.mod(
            this.setBlockDataDirectBlockPosition.set(position), 
            TerrainChunk.chunkDimensions
        );

        var blockIndex = Vector3iMaths.pack(blockPosition, TerrainChunk.chunkDimensions);
        if(!chunk.setData(blockIndex, blockData.pack())) {
            return;
        }

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            blockPosition.set(cubeFace.normal).add(position);
            if(position.x() < 0 || position.x() > this.dimensions.x - 1) {
                continue;
            } else if(position.y() < 0 || position.y() > this.dimensions.y - 1) {
                continue;
            } else if(position.z() < 0 || position.z() > this.dimensions.z - 1) {
                continue;
            }

            var adjacentChunkPosition = Vector3iMaths.div(blockPosition, TerrainChunk.chunkDimensions);
            var adjacentChunkIndex = Vector3iMaths.pack(
                adjacentChunkPosition, 
                this.chunkSpaceDimensions
            );

            processorModule.addDirtyChunk(this.chunks[adjacentChunkIndex]);
        }
    }

    private final TerrainBlockData setBlockDataOldBlockData = new TerrainBlockData();
    private final LightPropagation setBlockDataLightPropagation = new LightPropagation();
    private final LightResection setBlockDataLightResection = new LightResection();

    public void setBlockData(Vector3ic position, TerrainBlockData blockData) {
        var propagationModule = this.provider.getLightPropagationModule();
        var resectionModule = this.provider.getLightResectionModule();

        var oldBlockData = this.getBlockData(position, this.setBlockDataOldBlockData);
        var resection = this.setBlockDataLightResection.set(position, oldBlockData);
        resectionModule.addLightResection(resection);

        for(var ix = 0; ix < TerrainLight.getSize(); ix += 1) {
            var terrainLight = TerrainLight.get(ix);
            oldBlockData.setLight(terrainLight, 0);
        }

        this.setBlockDataDirect(position, oldBlockData);
        resectionModule.processOutstandingLightingTasks();

        this.setBlockDataDirect(position, blockData);
        
        var propagation = this.setBlockDataLightPropagation.set(position);
        propagationModule.addLightPropagation(propagation);
        propagationModule.processOutstandingLightingTasks();
    }

    public void destroy() {
        if(this.isWorkCompleted) {
            for(var chunk : this.chunks) {
                chunk.destroy();
            }
        }
    }
    
}
