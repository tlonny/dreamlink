package dreamlink.world.room.module.door;

import org.joml.Vector2i;

import dreamlink.utility.maths.Orientation;

public class DoorSegment {

    public final boolean isLeft;
    public final boolean isTop;
    public final String name;
    private int index;

    private static final DoorSegment[] doorSegments = new DoorSegment[] {
        new DoorSegment("topLeft", true, true),
        new DoorSegment("topRight", false, true),
        new DoorSegment("bottomLeft", true, false),
        new DoorSegment("bottomRight", false, false)
    };

    public static void setup() {
        for(var ix = 0; ix < DoorSegment.doorSegments.length; ix += 1) {
            DoorSegment.doorSegments[ix].index = ix;
        }
    }

    public static int getSize() {
        return DoorSegment.doorSegments.length;
    }

    public static DoorSegment get(int index) {
        return DoorSegment.doorSegments[index];
    }

    public int getIndex() {
        return this.index;
    }

    public DoorSegment(String name, boolean isLeft, boolean isTop) {
        this.name = name;
        this.isLeft = isLeft;
        this.isTop = isTop;
    }

    public Vector2i getBlockOffset(Vector2i target, Orientation orientation) {
        target.y = this.isTop ? 0 : -1;
        target.x = orientation.getTurn().cubeFace.isPositive
            ? this.isLeft ? 0 : -1
            : this.isLeft ? -1 : -0;
        return target;
    }
    
}
