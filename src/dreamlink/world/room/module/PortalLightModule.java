package dreamlink.world.room.module;

import java.util.ArrayDeque;
import java.util.Queue;

import org.joml.Vector3i;
import org.joml.Vector3ic;

import dreamlink.utility.pool.Resource;
import dreamlink.utility.pool.ResourcePool;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.terrain.TerrainBlockData;
import dreamlink.world.room.module.terrain.TerrainLight;

public class PortalLightModule {

    private final IRoomModuleProvider provider;

    private final ResourcePool<Vector3i> lightPool = new ResourcePool<>(Vector3i::new);
    private final Queue<Resource<Vector3i>> lights = new ArrayDeque<>();

    public PortalLightModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    private final TerrainBlockData setLightBlockData = new TerrainBlockData();

    public void setLight(Vector3ic position, int lightLevel) {
        var terrainModule = this.provider.getTerrainModule();
        var blockData = terrainModule.getBlockData(position, this.setLightBlockData);
        blockData.setLight(TerrainLight.portal, lightLevel);
        terrainModule.setBlockData(position, blockData);
        var dynamicLight = this.lightPool.acquire();
        dynamicLight.value.set(position);
        this.lights.add(dynamicLight);
    }

    public void clear() {
        var terrainModule = this.provider.getTerrainModule();
        while(!this.lights.isEmpty()) {
            var dynamicLight = this.lights.remove();
            var blockData = terrainModule.getBlockData(dynamicLight.value, this.setLightBlockData);
            blockData.setLight(TerrainLight.portal, 0);
            terrainModule.setBlockData(dynamicLight.value, blockData);
            dynamicLight.close();
        }
    }

    
}
