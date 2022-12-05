package dreamlink.utility.maths;

import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

public class Vector3fMaths {

    public static Vector3fc zero = new Vector3f(0f);
    public static Vector3fc one = new Vector3f(1f);

    public static Vector3f fuzz(Vector3f vector, float epsilon) {
        vector.x = FloatMaths.fuzz(vector.x, epsilon);
        vector.y = FloatMaths.fuzz(vector.y, epsilon);
        vector.z = FloatMaths.fuzz(vector.z, epsilon);
        return vector;
    }

    public static float maxComponent(Vector3fc vector) {
        return Math.max(vector.x(), Math.max(vector.y(), vector.z()));
    }

    public static Vector3f addComponent(Vector3f target, int axisID, float amountToAdd) {
        if(axisID == 0) {
            target.x += amountToAdd;
        } else if(axisID == 1) {
            target.y += amountToAdd;
        } else if(axisID == 2) {
            target.z += amountToAdd;
        }
        return target;
    }

    public static float dot(Vector3fc a, Vector3ic b) {
        return a.x() * b.x() + a.y() * b.y() + a.z() * b.z();
    }

    public static Vector3f add(Vector3f vector, Vector3ic other) {
        return vector.add(other.x(), other.y(), other.z());
    }

    public static Vector3f directionFromRotation(Vector3f direction, Vector3fc rotation) {
        return direction.set(0, 0, 1).rotateX(rotation.x()).rotateY(rotation.y());
    }

    public static float distance(Vector3fc vector, Vector3ic other) {
        var dx = vector.x() - other.x();
        var dy = vector.y() - other.y();
        var dz = vector.z() - other.z();
        return (float)Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static Vector3f safeNormalize(Vector3f vector, float epsilon) {
        var magnitude = vector.length();
        return vector.set(
            vector.x / (magnitude + epsilon),
            vector.y / (magnitude + epsilon),
            vector.z / (magnitude + epsilon)
        );
    }
    
}
