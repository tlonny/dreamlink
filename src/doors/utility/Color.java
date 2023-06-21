package doors.utility;

import doors.utility.geometry.Vector3fl;

public class Color {

    public static Vector3fl fromHex(int hexCode) {
        var b = (hexCode & 0xFF) / 255f;
        hexCode /= 0x100;
        var g = (hexCode & 0xFF) / 255f;
        hexCode /= 0x100;
        var r = (hexCode & 0xFF) / 255f;
        return new Vector3fl(r, g, b);
    }

}
