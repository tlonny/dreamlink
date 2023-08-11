package doors.utility;

public class Orientation {

    public static Orientation FRONT = new Orientation("front", CubeFace.FRONT);

    public static Orientation LEFT = new Orientation("left", CubeFace.LEFT);

    public static Orientation BACK = new Orientation("back", CubeFace.BACK);

    public static Orientation RIGHT = new Orientation("right", CubeFace.RIGHT);

    public static Orientation[] ORIENTATIONS = {
        FRONT, LEFT, BACK, RIGHT
    };

    private int index = -1;
    private String name;
    public CubeFace cubeFace;

    public Orientation(String name, CubeFace cubeFace) {
        this.name = name;
        this.cubeFace = cubeFace;
    }

    public int getIndex() {
        if(this.index == -1) {
            for(var i = 0; i < ORIENTATIONS.length; i++) {
                if(ORIENTATIONS[i] == this) {
                    this.index = i;
                    break;
                }
            }
        }
        return this.index;
    }

    public Orientation getOpposite() {
        var index = (this.getIndex() + 2) % 4;
        return ORIENTATIONS[index];
    }

    public CubeFace remapCubeFace(CubeFace cubeFace) {
        var cubeFaceIndex = -1;
        for(var ix = 0; ix < ORIENTATIONS.length; ix++) {
            if(ORIENTATIONS[ix].cubeFace == cubeFace) {
                cubeFaceIndex = ix;
                break;
            }
        }

        if(cubeFaceIndex == -1) {
            return cubeFace;
        }

        var remappedindex = Maths.mod(cubeFaceIndex - this.getIndex(), ORIENTATIONS.length);
        return ORIENTATIONS[remappedindex].cubeFace;
    }

    public String toString() {
        return String.format("Orientation(%s)", this.name);
    }

    
}
