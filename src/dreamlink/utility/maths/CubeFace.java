package dreamlink.utility.maths;

import java.util.HashMap;
import java.util.Map;

import org.joml.Vector3i;
import org.joml.Vector3ic;

public class CubeFace {

    public static final int XAxis = 0;
    public static final int YAxis = 1;
    public static final int ZAxis = 2;

    public static CubeFace front = new CubeFace(
        0,
        "front",
        new Vector3i(0, 0, 1),
        CubeFace.ZAxis
    );

    public static CubeFace left = new CubeFace(
        1,
        "left",
        new Vector3i(1, 0, 0),
        CubeFace.XAxis
    );

    public static CubeFace top = new CubeFace(
        2,
        "top",
        new Vector3i(0,1,0),
        CubeFace.YAxis
    );

    public static CubeFace back = new CubeFace(
        3,
        "back",
        new Vector3i(0, 0, -1),
        CubeFace.ZAxis
    );


    public static CubeFace right = new CubeFace(
        4,
        "right",
        new Vector3i(-1, 0, 0),
        CubeFace.XAxis
    );


    public static CubeFace bottom = new CubeFace(
        5,
        "bottom",
        new Vector3i(0,-1,0),
        CubeFace.YAxis
    );

    private static final Map<String, CubeFace> cubeFaceMap = new HashMap<>();
    private static final CubeFace[] cubeFaces = new CubeFace[] {
        front,
        left,
        top,
        back,
        right,
        bottom
    };

    public static void setup() {
        for(var ix = 0; ix < cubeFaces.length; ix += 1) {
            var cubeFace = cubeFaces[ix];
            cubeFace.index = ix;
            CubeFace.cubeFaceMap.put(cubeFace.name, cubeFace);
        }
    }

    public static CubeFace get(String cubeFaceName) {
        return cubeFaceMap.get(cubeFaceName);
    }

    public static CubeFace get(int cubeFaceID) {
        return cubeFaces[cubeFaceID];
    }

    public static int getSize() {
        return cubeFaces.length;
    }

    public final Vector3ic normal;
    public final String name;
    public final int axisID;
    public final boolean isPositive;

    private int index;

    CubeFace(int cubeFaceID, String name, Vector3ic normal, int axisID) {
        this.name = name;
        this.normal = new Vector3i(normal);
        this.axisID = axisID;
        this.isPositive = normal.x() + normal.y() + normal.z() > 0;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isSameAxis(CubeFace other) {
        return this.axisID == other.axisID;
    }

    public CubeFace getOpposite() {
        var oppositeID = (this.index + 3) % 6;
        return CubeFace.get(oppositeID);
    }

}
