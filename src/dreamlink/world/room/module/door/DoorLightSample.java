package dreamlink.world.room.module.door;

import dreamlink.world.room.module.color.ColorConfig;
import dreamlink.world.room.module.terrain.TerrainLight;

public class DoorLightSample {

    public static final DoorLightSample primary = new DoorLightSample(
        ColorConfig.primary,
        TerrainLight.primary
    );

    public static final DoorLightSample secondary = new DoorLightSample(
        ColorConfig.secondary,
        TerrainLight.secondary
    );

    public static final DoorLightSample tertiary = new DoorLightSample(
        ColorConfig.tertiary,
        TerrainLight.tertiary
    );

    private static final DoorLightSample[] doorLightSamples = new DoorLightSample[] {
        DoorLightSample.primary,
        DoorLightSample.secondary,
        DoorLightSample.tertiary
    };

    public static DoorLightSample get(int index) {
        return doorLightSamples[index];
    }

    public static int getSize() {
        return doorLightSamples.length;
    }

    public final ColorConfig colorConfig;
    public final TerrainLight lightChannel;

    public DoorLightSample(ColorConfig colorConfig, TerrainLight lightChannel) {
        this.colorConfig = colorConfig;
        this.lightChannel = lightChannel;
    }

}
