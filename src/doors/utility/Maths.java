package doors.utility;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class Maths {

    private static float EPSILON = 0.0001f;

    public static Vector3f zeroFuzz(Vector3f target) {
        if(Math.abs(target.x) < EPSILON) {
            target.x = 0;
        }
        if(Math.abs(target.y) < EPSILON) {
            target.y = 0;
        }
        if(Math.abs(target.z) < EPSILON) {
            target.z = 0;
        }
        return target;
    }

    public static Vector3i div(Vector3i target, Vector3i divisor) {
        target.set(
            target.x / divisor.x,
            target.y / divisor.y,
            target.z / divisor.z
        );
        return target;
    }

    public static Vector3i ceil(Vector3i target, Vector3i divisor) {
        target.set(
            Math.ceilDiv(target.x, divisor.x),
            Math.ceilDiv(target.y, divisor.y),
            Math.ceilDiv(target.z, divisor.z)
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

    public static int volume(Vector3i space) {
        return space.x * space.y * space.z;
    }

    public static int serialize(Vector3i position, Vector3i dimensions) {
        var index = 0;
        index += position.z * dimensions.x * dimensions.y;
        index += position.y * dimensions.x;
        index += position.x;
        return index;
    }

    public static Vector3i deserialize(int index, Vector3i dimensions) {
        var position = new Vector3i();
        position.x = index % dimensions.x;
        index /= dimensions.x;
        position.y = index % dimensions.y;
        index /= dimensions.y;
        position.z = index;
        return position;
    }

    public static boolean isWithinBounds(Vector3i position, Vector3i dimensions) {
        if(position.x < 0 || position.x >= dimensions.x) {
            return false;
        }
        if(position.y < 0 || position.y >= dimensions.y) {
            return false;
        }
        if(position.z < 0 || position.z >= dimensions.z) {
            return false;
        }
        return true;
    }
}
