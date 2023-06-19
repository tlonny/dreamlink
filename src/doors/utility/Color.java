package doors.utility;

import org.joml.Vector3f;

public class Color {

    private static Vector3f fromHex(int r, int g, int b) {
        return new Vector3f(r / 255f, g / 255f, b / 255f);
    }

    public static Vector3f BLACK = fromHex(0x00, 0x00, 0x00);
    public static Vector3f WHITE = fromHex(0xFF, 0xFF, 0xFF);

    public static Vector3f TEXT = fromHex(0x2B, 0x27, 0x2E);

    public static Vector3f BUTTON_ACTIVE = fromHex(0x00, 0x00, 0xFF);
    public static Vector3f BUTTON_HOVER = fromHex(0xFF, 0x00, 0xFF);

    public static Vector3f MENU_BACKGROUND_PRIMARY = fromHex(0xF5, 0xE0, 0xCB);

}
