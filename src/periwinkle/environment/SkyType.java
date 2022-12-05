package periwinkle.environment;

import periwinkle.utility.Maths;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public class SkyType {

    private static Vector3f BASE_RAIN_COLOR = new Vector3f(1f, 1f, 1f);
    private static float BASE_RAIN_BLEND = 0.2f;

    public static SkyType CLEAR_DAY = new SkyType(
        "clear_day", new Vector3f(228, 149, 230).div(255), 0.7f, 0f
    );

    public static SkyType CLEAR_NIGHT = new SkyType(
        "clear_night", new Vector3f(38, 16, 51).div(255), 1f, 0f
    );

    public static SkyType RAIN_DAY = new SkyType(
        "rain_day", new Vector3f(153, 138, 153).div(255), 0f, 0.3f
    );

    public static SkyType RAIN_NIGHT = new SkyType(
        "rain_night", new Vector3f(32, 27, 36).div(255), 0f, 0.3f
    );

    public static SkyType STORM_DAY = new SkyType(
            "storm_day", new Vector3f(81, 75, 82).div(255), 0f, 1f
    );

    public static SkyType STORM_NIGHT = new SkyType(
        "storm_night", new Vector3f(12, 12, 13).div(255), 0f, 1f
    );

    public static SkyType[] SKY_TYPES = new SkyType[] {
        CLEAR_DAY,
        CLEAR_NIGHT,
        RAIN_DAY,
        RAIN_NIGHT,
        STORM_DAY,
        STORM_NIGHT
    };

    public static Map<String, SkyType> SKY_TYPE_NAME_LOOKUP = new HashMap<>();
    public static void init() {
        for(var skyType : SKY_TYPES)
            SKY_TYPE_NAME_LOOKUP.put(skyType.name, skyType);
    }

    public final Vector3f skyColor;
    public final Vector3f starColor;
    public final Vector3f rainColor;
    public final String name;
    public final float rainRate;

    public SkyType(String name, Vector3f colors, float starClarity, float rainRate) {
        this.name = name;
        this.skyColor = colors;
        this.starColor = Maths.MATHS.interpolate(new Vector3f(colors), new Vector3f(1,1,1), starClarity);
        this.rainColor = Maths.MATHS.interpolate(colors, new Vector3f(BASE_RAIN_COLOR), BASE_RAIN_BLEND);
        this.rainRate = rainRate;
    }

}
