package dreamlink.world.room.module.lightpropagation;

import java.util.ArrayDeque;
import java.util.Queue;

import org.joml.Vector3i;

import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.pool.Resource;
import dreamlink.utility.pool.ResourcePool;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.terrain.TerrainBlockData;
import dreamlink.world.room.module.terrain.TerrainLight;

public class LightPropagationModule {

    private final IRoomModuleProvider provider;
    private final ResourcePool<LightPropagation> propagationPool = new ResourcePool<>(LightPropagation::new);
    private final Queue<Resource<LightPropagation>> workQueue = new ArrayDeque<>();

    public LightPropagationModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    private final Vector3i processLightPropagationAdjacentPosition = new Vector3i();
    private final TerrainBlockData processLightPropagationAdjacentBlockData = new TerrainBlockData();
    private final TerrainBlockData processLightPropagationBlockData = new TerrainBlockData();
    private final LightPropagation processLightPropagationPropagation = new LightPropagation();

    private void processLightPropagation(LightPropagation propagation) {

        var terrainModule = this.provider.getTerrainModule();
        var blockSystem = this.provider.getBlockModule();

        var blockData = terrainModule.getBlockData(
            propagation.position, 
            this.processLightPropagationBlockData
        );

        var block = blockSystem.getBlockByID(blockData.blockID);
        var orientation = Orientation.get(blockData.orientationIndex);

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);

            var remappedExitCubeFace = orientation.remap(cubeFace);

            var adjacentPosition = this.processLightPropagationAdjacentPosition
                .set(propagation.position)
                .add(cubeFace.normal);

            var adjacentBlockData = terrainModule.getBlockData(
                adjacentPosition, 
                this.processLightPropagationAdjacentBlockData
            );

            var adjacentBlock = blockSystem.getBlockByID(adjacentBlockData.blockID);
            var adjacentOrientation = Orientation.get(adjacentBlockData.orientationIndex);
            var remappedEntranceCubeFace = adjacentOrientation.remap(cubeFace).getOpposite();


            if(!adjacentBlock.canTransmitLight(remappedEntranceCubeFace)) {
                continue;
            }

            var isPropagating = false;
            var nextPropagation = this.processLightPropagationPropagation.set(propagation);
            nextPropagation.position.set(adjacentPosition);

            for(var jx = 0; jx < TerrainLight.getSize(); jx += 1) {
                var terrainLight = TerrainLight.get(jx);

                if(!propagation.getIsPropagating(terrainLight)) {
                    continue;
                }

                var canEmit = true
                    && jx < BlockLight.getSize()
                    && block.canEmitLight(remappedExitCubeFace);

                var propagatedLight = Math.max(
                    block.canTransmitLight(remappedExitCubeFace) ? blockData.getLight(terrainLight) - 1 : 0,
                    canEmit ? block.getLight(BlockLight.get(jx)) - 1 : 0
                );

                var destinationLight = adjacentBlockData.getLight(terrainLight);
                if(propagatedLight > destinationLight) {
                    adjacentBlockData.setLight(terrainLight, propagatedLight);
                    isPropagating = true;
                } else {
                    nextPropagation.setIsPropagating(terrainLight, false);
                }
            }

            if(isPropagating) {
                this.addLightPropagation(nextPropagation);
                terrainModule.setBlockDataDirect(adjacentPosition, adjacentBlockData);
            }
        }
    }

    public void addLightPropagation(LightPropagation source) {
        var propagation = this.propagationPool.acquire();
        propagation.value.set(source);
        this.workQueue.add(propagation);
    }

    public void processOutstandingLightingTasks() {
        while(!this.workQueue.isEmpty()) {
            var propagation = this.workQueue.remove();
            this.processLightPropagation(propagation.value);
            propagation.close();
        }
    }
    
}
