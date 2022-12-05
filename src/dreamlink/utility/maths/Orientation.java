package dreamlink.utility.maths;

import java.util.HashMap;
import java.util.Map;

public class Orientation {

    private static final float deg360 = (float)Math.PI * 2f;
    private static final float deg90 = (float)Math.PI / 2f;
    private static final float deg45 = (float)Math.PI / 4f;

    public static final Orientation front = new Orientation(
        Orientation.deg90 * 0f, 
        "front", 
        CubeFace.front
    );

    public static final Orientation left = new Orientation(
        Orientation.deg90 * 1f, 
        "left", 
        CubeFace.left
    );

    public static final Orientation back = new Orientation(
        Orientation.deg90 * 2f, 
        "back", 
        CubeFace.back
    );

    public static final Orientation right = new Orientation(
        Orientation.deg90 * 3f, 
        "right", 
        CubeFace.right
    );

    private static final Orientation[] orientations = new Orientation[] {
        front, left, back, right
    };

    private static Map<String, Orientation> orientationMap = new HashMap<>();
    private static Map<CubeFace, Orientation> orientationCubeFaceMap = new HashMap<>();

    public static Orientation get(String orientationName) {
        return Orientation.orientationMap.get(orientationName);
    }

    public static Orientation get(int index) {
        return Orientation.orientations[index];
    }

    public static Orientation fromYaw(float yaw) {
        yaw = FloatMaths.floatMod(yaw + deg45 , deg360);
        return Orientation.get((int)Math.floor(yaw / deg90));
    }
    
    public static void setup() {
        for(var ix = 0; ix < orientations.length; ix += 1) {
            var orientation = orientations[ix];
            orientation.index = ix;
            Orientation.orientationMap.put(orientation.name, orientation);
            Orientation.orientationCubeFaceMap.put(orientation.cubeFace, orientation);
        }

    }

    private int index;
    public final String name;
    public final CubeFace cubeFace;
    public final float yaw;

    public Orientation(float yaw, String name, CubeFace cubeFace) {
        this.yaw = yaw;
        this.name = name;
        this.cubeFace = cubeFace;
    }

    public int getIndex() {
        return this.index;
    }

    public Orientation getOpposite() {
        var oppositeID = (this.index + 2) % 4;
        return Orientation.get(oppositeID);
    }

    public Orientation getTurn() {
        var orthogonalID = (this.index + 1) % 4;
        return Orientation.get(orthogonalID);
    }

    // Remap an "apparent" cube face to the "real" cube face. I.e. a "LEFT" cubeFace, when
    // viewed from the "BACK" orientation, is actually a "RIGHT" cube face.
    public CubeFace remap(CubeFace cubeFace) {
        var orientation = orientationCubeFaceMap.get(cubeFace);

        if(orientation == null) {
            return cubeFace;
        }

        var orientationID = 4
            + orientation.index
            - this.index;

        return Orientation.get(orientationID % 4).cubeFace;
    }

    public String toString() {
        return String.format("Orientation(%s)", this.name);
    }

    
}
