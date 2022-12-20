package periwinkle.utility;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {

    public static Matrix4f IDENTITY = new Matrix4f().identity();

    public static Vector3f ZERO = new Vector3f();
    public static Vector3f COLOR_WHITE = getColor(255, 255, 255);

    public static Vector3f getColor(int red, int green, int blue) {
        return new Vector3f(red/255f, green/255f, blue/255f);
    }

    public static float min(Vector3f v) {
        return Math.min(v.x, Math.min(v.y, v.z));
    }
}
