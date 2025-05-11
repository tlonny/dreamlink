package dreamlink.world.room.module.color;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import dreamlink.graphics.program.ShaderProgramColor;
import dreamlink.utility.maths.Vector3fMaths;

public class ColorConfig {

    public static final ColorConfig primary = new ColorConfig(
        "primary_light",
        ShaderProgramColor.primaryLight,
        Vector3fMaths.zero
    );

    public static final ColorConfig secondary = new ColorConfig(
        "secondary_light",
        ShaderProgramColor.secondaryLight,
        Vector3fMaths.zero
    );

    public static final ColorConfig tertiary = new ColorConfig(
        "tertiary_light",
        ShaderProgramColor.tertiaryLight,
        Vector3fMaths.zero
    );

    public static final ColorConfig base = new ColorConfig(
        "base_light",
        ShaderProgramColor.baseLight,
        Vector3fMaths.one
    );

    private static final ColorConfig fog = new ColorConfig(
        "fog",
        ShaderProgramColor.fog,
        Vector3fMaths.zero
    );

    private static final ColorConfig[] colorConfigs = new ColorConfig[] {
        ColorConfig.primary,
        ColorConfig.secondary,
        ColorConfig.tertiary,
        ColorConfig.base,
        ColorConfig.fog
    };

    public static void setup() {
        for(var ix = 0; ix < colorConfigs.length; ix += 1) {
            colorConfigs[ix].index = ix;
        }
    }

    public static int getSize() {
        return ColorConfig.colorConfigs.length;
    }

    public static ColorConfig get(int index) {
        return ColorConfig.colorConfigs[index];
    }

    private int index;
    public final String name;
    public final ShaderProgramColor color;
    public final Vector3fc defaultColor;

    public ColorConfig(String name, ShaderProgramColor color, Vector3fc defaultColor) {
        this.name = name;
        this.color = color;
        this.defaultColor = new Vector3f(defaultColor);
    }

    public int getIndex() {
        return this.index;
    }
    
}
