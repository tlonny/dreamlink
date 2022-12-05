package dreamlink.world.room.module;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.joml.Vector3i;
import org.joml.Vector3ic;

import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.pool.Resource;
import dreamlink.utility.pool.ResourcePool;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.terrain.TerrainBlockData;

public class EchoModule {
    
    private class InternalEcho {
        public final Vector3i position = new Vector3i();
        public int soundLevel;
    }

    private static final int numBlocks = 0x400;
    private static final int cappedSoundLevel = 0xC;

    private final IRoomModuleProvider provider;
    private final ResourcePool<Vector3i> pool = new ResourcePool<>(Vector3i::new);
    private final ResourcePool<InternalEcho> echoPool = new ResourcePool<>(InternalEcho::new);

    private final Queue<Resource<InternalEcho>> queuedEchos = new ArrayDeque<>();
    private final List<Resource<InternalEcho>> activeEchos = new ArrayList<>();
    private final Queue<Resource<Vector3i>> workQueue = new ArrayDeque<>();
    private final int[] blockSoundLevels;

    public EchoModule(IRoomModuleProvider provider) {
        this.provider = provider;
        this.blockSoundLevels = new int[numBlocks];
    }

    public void queueEchoSource(Vector3ic position, int soundLevel) {
        var echo = this.echoPool.acquire();
        echo.value.position.set(position);
        echo.value.soundLevel = soundLevel;
        this.queuedEchos.add(echo);
    }

    private final TerrainBlockData runEchoBlockData = new TerrainBlockData();

    public void runEcho() {
        var terrainModule = this.provider.getTerrainModule();

        for(var ix = 0; ix < this.blockSoundLevels.length; ix += 1) {
            this.blockSoundLevels[ix] = 0;
        }

        while(!this.queuedEchos.isEmpty()) {
            var echo = this.queuedEchos.remove();
            var blockData = terrainModule.getBlockData(echo.value.position, this.runEchoBlockData);
            blockData.echo = echo.value.soundLevel;
            terrainModule.setBlockDataDirect(echo.value.position, blockData);
            this.activeEchos.add(echo);
            this.addToWorkQueue(echo.value.position);
        }

        while(!this.workQueue.isEmpty()) {
            var echo = this.workQueue.remove();
            this.processEchoPropagation(echo.value);
            echo.close();
        }

        for(var ix = 0; ix < this.activeEchos.size(); ix += 1) {
            var echo = this.activeEchos.get(ix);
            var blockData = terrainModule.getBlockData(echo.value.position, this.runEchoBlockData);
            blockData.echo = 0;
            terrainModule.setBlockDataDirect(echo.value.position, blockData);
            this.addToWorkQueue(echo.value.position);
        }

        while(!this.workQueue.isEmpty()) {
            var echo = this.workQueue.remove();
            this.processEchoResection(echo.value);
            echo.close();
        }

        for(var ix = 0; ix < this.activeEchos.size(); ix += 1) {
            this.activeEchos.get(ix).close();
        }

        this.activeEchos.clear();
    }

    private void addToWorkQueue(Vector3ic source) {
        var echo = this.pool.acquire();
        echo.value.set(source);
        this.workQueue.add(echo);
    }

    private final Vector3i processEchoPropagationAdjacentPosition = new Vector3i();
    private final TerrainBlockData processEchoPropagationAdjacentBlockData = new TerrainBlockData();
    private final TerrainBlockData processEchoPropagationBlockData = new TerrainBlockData();
    private final Vector3i processEchoPropagationPropagation = new Vector3i();

    private void processEchoPropagation(Vector3ic propagation) {
        var terrainModule = this.provider.getTerrainModule();
        var blockSystem = this.provider.getBlockModule();

        var blockData = terrainModule.getBlockData(
            propagation, 
            this.processEchoPropagationBlockData
        );

        this.blockSoundLevels[blockData.blockID] = Math.max(
            this.blockSoundLevels[blockData.blockID],
            blockData.echo
        );

        var block = blockSystem.getBlockByID(blockData.blockID);
        var orientation = Orientation.get(blockData.orientationIndex);

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);

            var remappedExitCubeFace = orientation.remap(cubeFace);
            if(!block.canTransmitSound(remappedExitCubeFace)) {
                continue;
            }

            var adjacentPosition = this.processEchoPropagationAdjacentPosition
                .set(propagation)
                .add(cubeFace.normal);

            var adjacentBlockData = terrainModule.getBlockData(
                adjacentPosition, 
                this.processEchoPropagationAdjacentBlockData
            );

            var adjacentBlock = blockSystem.getBlockByID(adjacentBlockData.blockID);
            var adjacentOrientation = Orientation.get(adjacentBlockData.orientationIndex);
            var remappedEntranceCubeFace = adjacentOrientation.remap(cubeFace).getOpposite();
            var propagatedEcho = blockData.echo - 1;

            if(propagatedEcho <= adjacentBlockData.echo) {
                continue;
            } else if(adjacentBlock.canTransmitSound(remappedEntranceCubeFace)) {
                adjacentBlockData.echo = propagatedEcho;
                terrainModule.setBlockDataDirect(adjacentPosition, adjacentBlockData);
                var nextPropagation = this.processEchoPropagationPropagation.set(adjacentPosition);
                this.addToWorkQueue(nextPropagation);
            } else if(adjacentBlock.canEmitSound(remappedEntranceCubeFace)) {
                adjacentBlockData.echo = propagatedEcho;
                terrainModule.setBlockDataDirect(adjacentPosition, adjacentBlockData);
                this.blockSoundLevels[adjacentBlockData.blockID] = Math.max(
                    this.blockSoundLevels[adjacentBlockData.blockID],
                    propagatedEcho
                );
            }
        }
    }

    private final TerrainBlockData processEchoResectionAdjacentBlockData = new TerrainBlockData();
    private final Vector3i processEchoResectionResection = new Vector3i();

    private void processEchoResection(Vector3ic propagation) {
        var terrainModule = this.provider.getTerrainModule();

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);

            var adjacentPosition = this.processEchoPropagationAdjacentPosition
                .set(propagation)
                .add(cubeFace.normal);

            var adjacentBlockData = terrainModule.getBlockData(
                adjacentPosition, 
                this.processEchoResectionAdjacentBlockData
            );

            if(adjacentBlockData.echo == 0) {
                continue;
            }

            adjacentBlockData.echo = 0;
            var nextResection = this.processEchoResectionResection.set(adjacentPosition);
            this.addToWorkQueue(nextResection);
            terrainModule.setBlockDataDirect(adjacentPosition, adjacentBlockData);
        }
    }

    public float getMaxGainForBlockID(int blockID) {
        var soundLevel = this.blockSoundLevels[blockID];
        var cappedSoundLevel = Math.min(soundLevel, EchoModule.cappedSoundLevel);
        return (float) cappedSoundLevel / EchoModule.cappedSoundLevel;
    }

    public void emitSounds(float gainModulator) {
        var blockModule = this.provider.getBlockModule();

        for(var ix = 0; ix < this.blockSoundLevels.length; ix += 1) {
            var block = blockModule.getBlockByID(ix);
            if(block == null) {
                continue;
            }

            var sound = block.getSound();
            if(sound == null) {
                continue;
            }

            var gain = this.getMaxGainForBlockID(ix);
            sound.soundBuffer.putGain(gain * gainModulator);
        }
    }
    
}
