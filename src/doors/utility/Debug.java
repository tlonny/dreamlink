package doors.utility;

import org.joml.Vector2f;
import org.joml.Vector3f;

import doors.graphics.ModelMesh;

public class Debug {

    public static String format(Vector3f vec) {
        return String.format("Vec3f(%.2f, %.2f, %.2f)", vec.x, vec.y, vec.z);
    }
    
    public static String format(Vector2f vec) {
        return String.format("Vec2f(%.2f, %.2f)", vec.x, vec.y);
    }
}
