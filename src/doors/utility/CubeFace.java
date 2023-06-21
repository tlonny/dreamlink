package doors.utility;

import java.util.HashMap;
import java.util.Map;

import doors.utility.geometry.Vector3fl;
import doors.utility.geometry.Vector3in;

public class CubeFace {

    public static Map<String, CubeFace> CUBE_FACE_MAP = new HashMap<>();

    public static final CubeFace FRONT = new CubeFace(
        "front",
        new Vector3in(0, 0, 1),
        new Vector3fl(0f, 0f, 0f)
    );

    public static final CubeFace BACK = new CubeFace(
        "back",
        new Vector3in(0, 0, -1),
        new Vector3fl(0f, 1f, 0f).mul(Math.PI)
    );

    public static final CubeFace LEFT = new CubeFace(
        "left",
        new Vector3in(-1, 0, 0),
        new Vector3fl(0f, -0.5f, 0f).mul(Math.PI)
    );

    public static final CubeFace RIGHT = new CubeFace(
        "right",
        new Vector3in(1, 0, 0),
        new Vector3fl(0f, 0.5f, 0f).mul(Math.PI)
    );

    public static final CubeFace TOP = new CubeFace(
        "top",
        new Vector3in(0,1,0),
        new Vector3fl(0.5f, 0f, 0f).mul(Math.PI)
    );

    public static final CubeFace BOTTOM = new CubeFace(
        "bottom",
        new Vector3in(0,-1,0),
        new Vector3fl(-0.5f, 0f, 0f).mul(Math.PI)
    );

    public static CubeFace[] CUBE_FACES = new CubeFace[] {
        FRONT, BACK, LEFT, RIGHT, TOP, BOTTOM
    };

    public static CubeFace[] FRONT_AND_BACK_CUBE_FACES = new CubeFace[] {
        FRONT, BACK
    };

    public static CubeFace[] HORIZONTAL_CUBE_FACES = new CubeFace[] {
        FRONT, BACK, LEFT, RIGHT
    };

    public Vector3in normal;
    public Vector3fl rotation;
    public String name;

    CubeFace(String name, Vector3in normal, Vector3fl rotation) {
        this.name = name;
        this.rotation = rotation;
        this.normal = normal;
        CUBE_FACE_MAP.put(name, this);
    }
}
