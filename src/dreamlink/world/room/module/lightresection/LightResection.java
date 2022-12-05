package dreamlink.world.room.module.lightresection;

import org.joml.Vector3i;
import org.joml.Vector3ic;

import dreamlink.world.room.module.terrain.TerrainBlockData;
import dreamlink.world.room.module.terrain.TerrainLight;

public class LightResection {

    public final Vector3i position = new Vector3i();
    private final int[] lightLevels;
    private final boolean[] isResecting;

    public LightResection() {
        this.lightLevels = new int[TerrainLight.getSize()];
        this.isResecting = new boolean[TerrainLight.getSize()];
    }

    public boolean getIsResecting(TerrainLight light) {
        return this.isResecting[light.getIndex()];
    }

    public void setIsResecting(TerrainLight light, boolean isResecting) {
        this.isResecting[light.getIndex()] = isResecting;
    }

    public int getLight(TerrainLight lightType) {
        return this.lightLevels[lightType.getIndex()];
    }

    public void setLight(TerrainLight lightType, int value) {
        this.lightLevels[lightType.getIndex()] = value;
    }

    public LightResection set(Vector3ic position, TerrainBlockData blockData) {
        this.position.set(position);
        for (var ix = 0; ix < TerrainLight.getSize(); ix += 1) {
            var terrainLight = TerrainLight.get(ix);
            this.isResecting[ix] = true;
            this.lightLevels[ix] = blockData.getLight(terrainLight);
        }
        return this;
    }

    public LightResection set(LightResection source) {
        this.position.set(source.position);
        for (var ix = 0; ix < this.lightLevels.length; ix += 1) {
            this.lightLevels[ix] = source.lightLevels[ix];
            this.isResecting[ix] = source.isResecting[ix];
        }
        return this;
    }
}
