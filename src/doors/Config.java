package doors;

import doors.utility.Color;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;

public class Config {

    public static Vector2in RESOLUTION = new Vector2in(1280, 720);
    public static Vector2in TERRAIN_ATLAS_DIMENSIONS = new Vector2in(512, 512);

    public static Vector3fl COLOR_BACKGROUND   = Color.fromHex(0x000000);
    public static Vector3fl COLOR_FOREGROUND   = Color.fromHex(0x4242C6);
    public static Vector3fl COLOR_OUTER_BORDER = Color.fromHex(0x000000);
    public static Vector3fl COLOR_INNER_BORDER = Color.fromHex(0x9C9CF7);

    public static Vector3fl COLOR_TEXT_HIGHLIGHT = Color.fromHex(0x73F7F7);

}
