package dreamlink.graphics.program;

public class ShaderProgramColor {

    public static final ShaderProgramColor baseLight = new ShaderProgramColor(
        "base_light_color"
    );

    public static final ShaderProgramColor primaryLight = new ShaderProgramColor(
        "primary_light_color"
    );

    public static final ShaderProgramColor secondaryLight = new ShaderProgramColor(
        "secondary_light_color"
    );

    public static final ShaderProgramColor tertiaryLight = new ShaderProgramColor(
        "tertiary_light_color"
    );

    public static final ShaderProgramColor portalLight = new ShaderProgramColor(
        "portal_light_color"
    );

    private static final ShaderProgramColor[] lightColors = new ShaderProgramColor[] {
        ShaderProgramColor.baseLight,
        ShaderProgramColor.primaryLight,
        ShaderProgramColor.secondaryLight,
        ShaderProgramColor.tertiaryLight,
        ShaderProgramColor.portalLight
    };

    public static int size() {
        return lightColors.length;
    }

    public static ShaderProgramColor get(int index) {
        return lightColors[index];
    }

    public static void setup() {
        for(var ix = 0; ix < lightColors.length; ix += 1) {
            lightColors[ix].index = ix;
        }
    }

    public ShaderProgramColor(String name) {
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    private int index;
    public final String name;
    
}
