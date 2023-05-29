package doors.utility;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3i;

public class VectorExtension {
    public static Vector3i div(Vector3i target, Vector3i divisor) {
        target.set(
            target.x / divisor.x,
            target.y / divisor.y,
            target.z / divisor.z
        );
        return target;
    }

    public static Vector2f div(Vector2f target, Vector2i divisor) {
        target.set(
            target.x / divisor.x,
            target.y / divisor.y
        );
        return target;
    }
}
