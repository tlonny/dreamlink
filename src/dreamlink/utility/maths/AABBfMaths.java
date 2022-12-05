package dreamlink.utility.maths;

import org.joml.AABBf;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class AABBfMaths {

    public static AABBf set(AABBf target, AABBf other) {
        target.minX = other.minX;
        target.minY = other.minY;
        target.minZ = other.minZ;
        target.maxX = other.maxX;
        target.maxY = other.maxY;
        target.maxZ = other.maxZ;
        return target; 
    }

    public static float distance(AABBf bounds, Vector3f point) {
        var clampedX = Math.max(bounds.minX, Math.min(bounds.maxX, point.x));
        var clampedY = Math.max(bounds.minY, Math.min(bounds.maxY, point.y));
        var clampedZ = Math.max(bounds.minZ, Math.min(bounds.maxZ, point.z));
        return point.distance(clampedX, clampedY, clampedZ);
    }

    public static AABBf zero(AABBf target) {
        return target
            .setMin(0, 0, 0)
            .setMax(0, 0, 0);
    }

    public static AABBf setUnitCube(AABBf target, Vector3f position) {
        return target
            .setMin(position.x, position.y, position.z)
            .setMax(position.x + 1, position.y + 1, position.z + 1);
    }

    public static AABBf setUnitCube(AABBf target, Vector3i position) {
        return target
            .setMin(position.x, position.y, position.z)
            .setMax(position.x + 1, position.y + 1, position.z + 1);
    }

    public static AABBf project(AABBf target, AABBf other, CubeFace cubeFace) {
        AABBfMaths.set(target, other);
        if(cubeFace.normal.x() != 0) {
            target.minX = target.maxX = cubeFace.normal.x() > 0
                ? other.maxX
                : other.minX;
        }

        if(cubeFace.normal.y() != 0) {
            target.minY = target.maxY = cubeFace.normal.y() > 0
                ? other.maxY
                : other.minY;
        }

        if(cubeFace.normal.z() != 0) {
            target.minZ = target.maxZ = cubeFace.normal.z() > 0
                ? other.maxZ
                : other.minZ;
        }

        return target;
    }

    public static AABBf shrink(AABBf target, float amount) {
        target.maxX -= amount;
        target.maxY -= amount;
        target.maxZ -= amount;
        target.minX += amount;
        target.minY += amount;
        target.minZ += amount;
        return target;
    }
    
}
