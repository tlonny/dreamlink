package dreamlink.utility.maths;

import org.joml.Vector2i;
import org.joml.Vector2ic;

public class Vector2iMaths {

    public static Vector2ic zero = new Vector2i(0, 0);
    public static Vector2ic one = new Vector2i(1, 1);

    public static Vector2i div(Vector2i vector, Vector2ic divisor) {
        return vector.set(vector.x / divisor.x(), vector.y / divisor.y());
    }

    public static Vector2i div(Vector2i vector, int divisor) {
        return vector.set(vector.x / divisor, vector.y / divisor);
    }

    public static Vector2i center(Vector2i target, Vector2ic boundingDimensions, Vector2ic dimensions) {
        return target.set(
            (boundingDimensions.x() - dimensions.x()) / 2,
            (boundingDimensions.y() - dimensions.y()) / 2
        );
    }

    public static int getAxisValue(Vector2ic vector, int axis) {
        if(axis == 0) {
            return vector.x();
        } else if(axis == 1) {
            return vector.y();
        } else {
            return 0;
        }
    }

    public static void setAxisValue(Vector2i vector, int axis, int value) {
        if(axis == 0) {
            vector.x = value;
        } else if(axis == 1) {
            vector.y = value;
        }
    }
    
}
