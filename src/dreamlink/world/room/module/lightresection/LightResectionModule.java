package dreamlink.world.room.module.lightresection;

import java.util.ArrayDeque;
import java.util.Queue;

import org.joml.Vector3i;

import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.pool.Resource;
import dreamlink.utility.pool.ResourcePool;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.lightpropagation.LightPropagation;
import dreamlink.world.room.module.terrain.TerrainBlockData;
import dreamlink.world.room.module.terrain.TerrainLight;

public class LightResectionModule {

    private final IRoomModuleProvider provider;
    private final Queue<Resource<LightResection>> workQueue = new ArrayDeque<>();
    private final ResourcePool<LightResection> resectionPool = new ResourcePool<>(LightResection::new);

    public LightResectionModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    private final Vector3i processLightResectionAdjacentPosition = new Vector3i();
    private final TerrainBlockData processLightResectionAdjacentBlockData = new TerrainBlockData();
    private final TerrainBlockData processLightResectionBlockData = new TerrainBlockData();
    private final LightResection processLightResectionResection = new LightResection();
    private final LightPropagation processLightResectionPropagation = new LightPropagation();

    private void processLightResection(LightResection resection) {
        var propagationModule = this.provider.getLightPropagationModule();
        var terrainModule = this.provider.getTerrainModule();
        var blockSystem = this.provider.getBlockModule();

        var blockData = terrainModule.getBlockData(
            resection.position,
            this.processLightResectionBlockData
        );
        var block = blockSystem.getBlockByID(blockData.blockID);
        var orientation = Orientation.get(blockData.orientationIndex);

        for (var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);

            var remappedExitCubeFace = orientation.remap(cubeFace);

            var adjacentPosition = this.processLightResectionAdjacentPosition
                .set(resection.position)
                .add(cubeFace.normal);

            var adjacentBlockData = terrainModule.getBlockData(
                adjacentPosition,
                this.processLightResectionAdjacentBlockData
            );

            var adjacentBlock = blockSystem.getBlockByID(adjacentBlockData.blockID);
            var adjacentOrientation = Orientation.get(adjacentBlockData.orientationIndex);
            var remappedEntranceCubeFace = adjacentOrientation.remap(cubeFace).getOpposite();

            var nextResection = this.processLightResectionResection.set(resection);
            nextResection.position.set(adjacentPosition);

            if(!adjacentBlock.canTransmitLight(remappedEntranceCubeFace)) {
                var propagation = this.processLightResectionPropagation.set(adjacentPosition);
                propagationModule.addLightPropagation(propagation);
                continue;
            }

            var isResecting = false;
            var toPropagate = false;

            for (var jx = 0; jx < TerrainLight.getSize(); jx += 1) {
                var terrainLight = TerrainLight.get(jx);

                if(!resection.getIsResecting(terrainLight)) {
                    continue;
                }

                var canEmit = true
                    && jx < BlockLight.getSize()
                    && block.canEmitLight(remappedExitCubeFace);

                var propagatedLight = Math.max(
                    block.canTransmitLight(remappedExitCubeFace) ? resection.getLight(terrainLight) - 1 : 0,
                    canEmit ? block.getLight(BlockLight.get(jx)) - 1: 0
                );

                var destinationLight = adjacentBlockData.getLight(terrainLight);
                if(destinationLight > 0 && destinationLight <= propagatedLight && adjacentBlock.canTransmitLight(remappedEntranceCubeFace)) {
                    adjacentBlockData.setLight(terrainLight, 0);
                    this.processLightResectionResection.setLight(terrainLight, destinationLight);
                    isResecting = true;
                } else if(destinationLight > propagatedLight) {
                    nextResection.setIsResecting(terrainLight, false);
                    toPropagate = true;
                } else {
                    nextResection.setIsResecting(terrainLight, false);
                }
            }

            if (isResecting) {
                this.addLightResection(this.processLightResectionResection);
                terrainModule.setBlockDataDirect(adjacentPosition, adjacentBlockData);
            } 
            
            if(toPropagate) {
                var propagation = this.processLightResectionPropagation.set(adjacentPosition);
                propagationModule.addLightPropagation(propagation);
            }

        }
    }

    public void addLightResection(LightResection source) {
        var resection = this.resectionPool.acquire();
        resection.value.set(source);
        this.workQueue.add(resection);
    }

    public void processOutstandingLightingTasks() {
        while(!this.workQueue.isEmpty()) {
            var resection = this.workQueue.remove();
            this.processLightResection(resection.value);
            resection.close();
        }
    }
}