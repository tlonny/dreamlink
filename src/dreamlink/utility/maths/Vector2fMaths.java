package dreamlink.utility.maths;

import org.joml.Vector2f;
import org.joml.Vector2fc;
import org.joml.Vector2ic;

public class Vector2fMaths {

    public static Vector2fc zero = new Vector2f(0f);
    public static Vector2fc one = new Vector2f(1f);

    public static Vector2f div(Vector2f vector, Vector2ic toDivide) {
        return vector.div(toDivide.x(), toDivide.y());
    }

    public static Vector2f add(Vector2f vector, Vector2ic toAdd) {
        return vector.add(toAdd.x(), toAdd.y());
    }

    public static Vector2f mul(Vector2f vector, Vector2ic toMultiply) {
        return vector.mul(toMultiply.x(), toMultiply.y());
    }

    public static float getAxisValue(Vector2fc vector, int axis) {
        if(axis == 0) {
            return vector.x();
        } else if(axis == 1) {
            return vector.y();
        } else {
            return 0;
        }
    }

    public static void setAxisValue(Vector2f vector, int axis, float value) {
        if(axis == 0) {
            vector.x = value;
        } else if(axis == 1) {
            vector.y = value;
        }
    }
    
}
