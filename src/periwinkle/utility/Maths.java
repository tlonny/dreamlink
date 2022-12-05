package periwinkle.utility;

import org.joml.Vector3f;

public class Maths {

    public static Maths MATHS = new Maths();

    public Vector3f interpolate(Vector3f start, Vector3f end, float factor) {
        return new Vector3f(
            this.interpolate(start.x, end.x, factor),
            this.interpolate(start.y, end.y, factor),
            this.interpolate(start.z, end.z, factor)
        );
    }

    public float interpolate(float start, float end, float factor) {
        return end * factor + start * (1 - factor);
    }

}
