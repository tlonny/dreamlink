package dreamlink.world.room.module.block;

import dreamlink.world.room.module.terrain.TerrainLight;

public class BlockLight {

    public static final BlockLight primary = new BlockLight(
        "primary",
        TerrainLight.primary
    );

    public static final BlockLight secondary = new BlockLight(
        "secondary",
        TerrainLight.secondary
    );

    public static final BlockLight tertiary = new BlockLight(
        "tertiary",
        TerrainLight.tertiary
    );

    private static final BlockLight[] blockLights = new BlockLight[] {
        BlockLight.primary,
        BlockLight.secondary,
        BlockLight.tertiary
    };

    public static int getSize() {
        return blockLights.length;
    }

    public static BlockLight get(int index) {
        return blockLights[index];
    }

    public static void setup() {
        for(var ix = 0; ix < blockLights.length; ix += 1) {
            blockLights[ix].index = ix;
        }
    }

    public final TerrainLight terrainLight;
    public final String name;
    private int index;

    public BlockLight(String name, TerrainLight lightChannel) {
        this.name = name;
        this.terrainLight = lightChannel;
    }

    public int getIndex() {
        return this.index;
    }
    
}
