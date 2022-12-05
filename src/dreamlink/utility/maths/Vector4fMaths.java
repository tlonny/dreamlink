package dreamlink.utility.maths;

import org.joml.Vector4f;
import org.joml.Vector4fc;

public class Vector4fMaths {

    private static int offsetX = 24;
    private static int offsetY = 16;
    private static int offsetZ = 8;
    private static int offsetW = 0;

    public static Vector4fc zero = new Vector4f(0f);
    public static Vector4fc one = new Vector4f(1f);

    public static int toHex(Vector4fc color) {
        var packedColor = 0;
        packedColor |= (int)(color.x() * 255) << Vector4fMaths.offsetX;
        packedColor |= (int)(color.y() * 255) << Vector4fMaths.offsetY;
        packedColor |= (int)(color.z() * 255) << Vector4fMaths.offsetZ;
        packedColor |= (int)(color.w() * 255) << Vector4fMaths.offsetW;
        return packedColor;
    }

    public static Vector4f fromHex(Vector4f target, int hex) {
        return target.set(
            ((hex >> Vector4fMaths.offsetX) & 0xFF) / 255f,
            ((hex >> Vector4fMaths.offsetY) & 0xFF) / 255f,
            ((hex >> Vector4fMaths.offsetZ) & 0xFF) / 255f,
            ((hex >> Vector4fMaths.offsetW) & 0xFF) / 255f
        );
    }

    public static Vector4f fromAlpha(Vector4f target, float alpha) {
        return target.set(1f, 1f, 1f, alpha);
    }

    public static Vector4fc halfAlpha =  Vector4fMaths.fromAlpha(new Vector4f(), 0.5f);
    public static Vector4fc white = Vector4fMaths.fromHex(new Vector4f(), 0xFFFFFFFF);
    public static Vector4fc black = Vector4fMaths.fromHex(new Vector4f(), 0x000000FF);
    public static Vector4fc red = Vector4fMaths.fromHex(new Vector4f(), 0xFF0000FF);
    public static Vector4fc yellow = Vector4fMaths.fromHex(new Vector4f(), 0xFFFF00FF);
    public static Vector4fc orange = Vector4fMaths.fromHex(new Vector4f(), 0xFFA500FF);
    public static Vector4fc green = Vector4fMaths.fromHex(new Vector4f(), 0x00FF00FF);
    public static Vector4fc cyan = Vector4fMaths.fromHex(new Vector4f(), 0x00FFFFFF);
    public static Vector4fc blue = Vector4fMaths.fromHex(new Vector4f(), 0x0000FFFF);
    public static Vector4fc magenta = Vector4fMaths.fromHex(new Vector4f(), 0xFF00FFFF);
    public static Vector4fc overlayBackground = Vector4fMaths.fromHex(new Vector4f(), 0x447284FF);
    public static Vector4fc overlayHighlight = Vector4fMaths.fromHex(new Vector4f(), 0x010081FF);
    public static Vector4fc overlayWindow = Vector4fMaths.fromHex(new Vector4f(), 0xEFBCE4FF);
    
}
