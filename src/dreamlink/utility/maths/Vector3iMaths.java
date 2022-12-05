package dreamlink.utility.maths;

import org.joml.Vector3fc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public class Vector3iMaths {
    
    public static Vector3ic zero = new Vector3i(0);
    public static Vector3ic one = new Vector3i(1);

    public static int volume(Vector3ic vector) {
        return vector.x() * vector.y() * vector.z();
    }

    public static int dot(Vector3ic a, Vector3ic b) {
        return a.x() * b.x() + a.y() * b.y() + a.z() * b.z();
    }

    public static Vector3i addComponent(Vector3i target, int axisID, int amountToAdd) {
        if(axisID == 0) {
            target.x += amountToAdd;
        } else if(axisID == 1) {
            target.y += amountToAdd;
        } else if(axisID == 2) {
            target.z += amountToAdd;
        }
        return target;
    }

    public static Vector3i div(Vector3i target, Vector3ic toDivide) {
        return target.set(
            target.x / toDivide.x(),
            target.y / toDivide.y(),
            target.z / toDivide.z()
        );
    }
    
    public static Vector3i add(Vector3i target, int amountToAdd) {
        return target.set(
            target.x + amountToAdd,
            target.y + amountToAdd,
            target.z + amountToAdd
        );
    }

    public static Vector3i mod(Vector3i target, Vector3ic toMod) {
        return target.set(
            target.x % toMod.x(),
            target.y % toMod.y(),
            target.z % toMod.z()
        );
    }

    public static Vector3i unpack(Vector3i target, int index, Vector3ic dimensions) {
        var x = index % dimensions.x();
        index /= dimensions.x();
        var z = index % dimensions.z();
        index /= dimensions.z();
        var y = index;
        return target.set(x, y, z);
    }

    public static Vector3i castFrom(Vector3i target, Vector3fc vector) {
        return target.set(
            (int)vector.x(),
            (int)vector.y(),
            (int)vector.z()
        );
    }

    public static int pack(Vector3i vector, Vector3ic dimensions) {
        var packedValue = 0;
        packedValue += vector.y * dimensions.x() * dimensions.z();
        packedValue += vector.z * dimensions.x(); 
        packedValue += vector.x;
        return packedValue;
    }

    public static int maxComponent(Vector3ic vector) {
        return Math.max(vector.x(), Math.max(vector.y(), vector.z()));
    }

    public static int minComponent(Vector3ic vector) {
        return Math.min(vector.x(), Math.min(vector.y(), vector.z()));
    }

    
}
