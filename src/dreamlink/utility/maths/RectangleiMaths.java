package dreamlink.utility.maths;

import org.joml.Rectanglei;
import org.joml.Vector2ic;

public class RectangleiMaths {

    public static Rectanglei set(Rectanglei target, Vector2ic position, Vector2ic dimensions) {
        target.minX = position.x();
        target.minY = position.y();
        target.maxX = target.minX + dimensions.x();
        target.maxY = target.minY + dimensions.y();
        return target;
    }

    public static Rectanglei set(Rectanglei target, Rectanglei source) {
        target.minX = source.minX;
        target.minY = source.minY;
        target.maxX = source.maxX;
        target.maxY = source.maxY;
        return target;
    }
    
}
