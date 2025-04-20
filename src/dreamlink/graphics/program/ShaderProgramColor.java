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

    public static final ShaderProgramColor fog = new ShaderProgramColor(
        "fog_color"
    );

    private static final ShaderProgramColor[] colors = new ShaderProgramColor[] {
        ShaderProgramColor.baseLight,
        ShaderProgramColor.primaryLight,
        ShaderProgramColor.secondaryLight,
        ShaderProgramColor.tertiaryLight,
        ShaderProgramColor.portalLight,
        ShaderProgramColor.fog
    };

    public static int size() {
        return colors.length;
    }

    public static ShaderProgramColor get(int index) {
        return colors[index];
    }

    public static void setup() {
        for(var ix = 0; ix < colors.length; ix += 1) {
            colors[ix].index = ix;
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
