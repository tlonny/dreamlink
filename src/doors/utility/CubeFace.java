package doors.utility;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class CubeFace {

    public static final CubeFace FRONT = new CubeFace(
        new Vector3i(0, 0, 1),
        new Vector3f(0, 1, 1),
        new Vector3f(0, 0, 1),
        new Vector3f(1, 0, 1),
        new Vector3f(1, 1, 1)
    );

    public static final CubeFace BACK = new CubeFace(
        new Vector3i(0, 0, -1),
        new Vector3f(1, 1, 0),
        new Vector3f(1, 0, 0),
        new Vector3f(0, 0, 0),
        new Vector3f(0, 1, 0)
    );

    public static final CubeFace LEFT = new CubeFace(
        new Vector3i(-1, 0, 0),
        new Vector3f(0, 1, 0),
        new Vector3f(0,0,0),
        new Vector3f(0,0,1),
        new Vector3f(0,1,1)
    );

    public static final CubeFace RIGHT = new CubeFace(
        new Vector3i(1, 0, 0),
        new Vector3f(1, 1, 1),
        new Vector3f(1, 0, 1),
        new Vector3f(1, 0, 0),
        new Vector3f(1, 1, 0)
    );

    public static final CubeFace TOP = new CubeFace(
        new Vector3i(0,1,0),
        new Vector3f(0,1,0),
        new Vector3f(0,1,1),
        new Vector3f(1,1,1),
        new Vector3f(1,1,0)
    );

    public static final CubeFace BOTTOM = new CubeFace(
        new Vector3i(0,-1,0),
        new Vector3f(0,0,1),
        new Vector3f(0,0,0),
        new Vector3f(1,0,0),
        new Vector3f(1,0,1)
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

    public Vector3i normal;
    public Vector3f floatNormal;
    public Vector3f[] vertices;
    public String name;

    CubeFace(Vector3i normal, Vector3f firstVertex, Vector3f secondVertex, Vector3f thirdVertex, Vector3f fourthVertex) {
        this.normal = normal;
        this.vertices = new Vector3f[] {
            firstVertex, secondVertex, thirdVertex, fourthVertex
        };
    }
}
