package dreamlink.world.room.module.terrain;

public class TerrainLight {

    public static final TerrainLight primary = new TerrainLight(12);
    public static final TerrainLight secondary = new TerrainLight(16);
    public static final TerrainLight tertiary = new TerrainLight(20);
    public static final TerrainLight portal = new TerrainLight(24);

    private static final TerrainLight[] terrainLights = new TerrainLight[] { 
        primary, 
        secondary, 
        tertiary, 
        portal
    };

    public static int getSize() {
        return terrainLights.length;
    }

    public static TerrainLight get(int index) {
        return terrainLights[index];
    }

    public static void setup() {
        for (var ix = 0; ix < terrainLights.length; ix += 1) {
            terrainLights[ix].index = ix;
        }
    }

    public TerrainLight(int byteOffset) {
        this.byteOffset = byteOffset;
    }

    private int index; 
    public final int byteOffset;

    public int getIndex() {
        return this.index;
    }
}