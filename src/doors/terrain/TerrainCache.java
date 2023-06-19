package doors.terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerrainCache {

    private static int LRU_CACHE_SIZE = 20;
    public static TerrainCache TERRAIN_CACHE = new TerrainCache();

    private List<Terrain> disposeQueue = new ArrayList<>();
    private Map<String,Terrain> terrainMap = new HashMap<>();

    public Terrain getTerrain(String terrainDirectory) {
        Terrain terrain;
        if(!this.terrainMap.containsKey(terrainDirectory)) {
            terrain = new Terrain(terrainDirectory);
            this.terrainMap.put(terrainDirectory, terrain);
            terrain.setup();
        } else {
            terrain = this.terrainMap.get(terrainDirectory);
            this.disposeQueue.remove(terrain);
        }

        this.disposeQueue.add(terrain);
        if(this.disposeQueue.size() > LRU_CACHE_SIZE) {
            var terrainToDestroy = this.disposeQueue.remove(0);
            terrainToDestroy.tearDown();
            this.terrainMap.remove(terrainToDestroy.terrainDirectory);
        }

        return terrain;
    }

}
