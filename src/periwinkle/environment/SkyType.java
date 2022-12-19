package periwinkle.environment;

import periwinkle.utility.Maths;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public class SkyType {

    public static Map<String, SkyType> SKY_TYPE_NAME_LOOKUP = new HashMap<>();

    private static Vector3f BASE_RAIN_COLOR = Maths.COLOR_WHITE;
    private static float BASE_RAIN_BLEND = 0.4f;

    public static SkyType CLEAR_DAY = new SkyType(
        "clear_day", Maths.getColor(228, 149, 230), 0.7f, 0
    );

    public static SkyType CLEAR_NIGHT = new SkyType(
        "clear_night", Maths.getColor(38, 16, 51), 1f, 0
    );

    public static SkyType RAIN_DAY = new SkyType(
        "rain_day", Maths.getColor(153, 138, 153), 0f, 4
    );

    public static SkyType RAIN_NIGHT = new SkyType(
        "rain_night", Maths.getColor(32, 27, 36), 0f, 4
    );

    public static SkyType STORM_DAY = new SkyType(
        "storm_day", Maths.getColor(81, 75, 82), 0f, 10
    );

    public static SkyType STORM_NIGHT = new SkyType(
        "storm_night", Maths.getColor(12, 12, 13), 0f, 10
    );


    public Vector3f skyColor;
    public Vector3f starColor;
    public Vector3f rainColor;
    public String name;
    public float rainRate;
    public int rainPerSim = 5;

    public SkyType(String name, Vector3f colors, float starClarity, int rainPerSim) {
        this.name = name;
        this.skyColor = colors;
        this.starColor = new Vector3f(1,1,1).lerp(colors, 1 - starClarity);
        this.rainColor = new Vector3f(BASE_RAIN_COLOR).lerp(colors, BASE_RAIN_BLEND);
        this.rainPerSim = rainPerSim;
        SKY_TYPE_NAME_LOOKUP.put(this.name, this);
    }

}
