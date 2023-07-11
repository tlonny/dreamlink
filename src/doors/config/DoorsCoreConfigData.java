package doors.config;

import doors.core.config.IConfigData;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;

public class DoorsCoreConfigData implements IConfigData {

    private static Vector2in RESOLUTION = new Vector2in(1280, 720);
    private static Vector2in MAX_IMAGE_TEXTURE_DIMENSIONS = new Vector2in(1024, 1024);

    public static Vector3fl HYPERLINK_COLOR = Vector3fl.fromHex(0x0000FF);
    public static Vector3fl HYPERLINK_HOVER_COLOR = Vector3fl.fromHex(0xFF00FF);
    public static Vector3fl HYPERLINK_PRESSED_COLOR = Vector3fl.fromHex(0xFF0000);

    public static String EDITOR_LEVEL_DIRECTORY = "scratch/editor";

    @Override
    public Vector2in getResolution() {
        return RESOLUTION;
    }

    @Override
    public Vector2in getMaxImageTextureDimensions() {
        return MAX_IMAGE_TEXTURE_DIMENSIONS;
    }
}
