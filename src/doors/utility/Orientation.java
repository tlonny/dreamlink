package doors.utility;

import java.util.HashMap;
import java.util.Map;

public class Orientation {

    private static CubeFace[] CUBE_FACE_MAPPING = {
        CubeFace.FRONT,
        CubeFace.LEFT,
        CubeFace.BACK,
        CubeFace.RIGHT
    };

    public static Map<Integer,Orientation> ORIENTATION_MAP = new HashMap<>();

    public static Orientation FRONT = new Orientation("front", 0);

    public static Orientation LEFT = new Orientation("left", 1);

    public static Orientation BACK = new Orientation("back", 2);

    public static Orientation RIGHT = new Orientation("right", 3);

    public int index;
    private String name;

    public Orientation(String name, int index) {
        this.name = name;
        this.index = index;
        ORIENTATION_MAP.put(index, this);
    }

    public CubeFace getCubeFace() {
        return CUBE_FACE_MAPPING[this.index];
    }

    public CubeFace remapCubeFace(CubeFace cubeFace) {
        var cubeFaceIndex = -1;
        for(var i = 0; i < CUBE_FACE_MAPPING.length; i++) {
            if(CUBE_FACE_MAPPING[i] == cubeFace) {
                cubeFaceIndex = i;
                break;
            }
        }

        if(cubeFaceIndex == -1) {
            return cubeFace;
        }

        var remappedindex = Maths.mod(cubeFaceIndex - this.index, CUBE_FACE_MAPPING.length);
        return CUBE_FACE_MAPPING[remappedindex];
    }

    public String toString() {
        return String.format("Orientation(%s)", this.name);
    }

    
}
