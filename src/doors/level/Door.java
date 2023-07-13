package doors.level;

import doors.utility.CubeFace;
import doors.utility.vector.Vector3fl;

public class Door {

    public String targetLevel;
    public String targetDoor;
    public Vector3fl position;
    public CubeFace orientation;

    public Door(String targetTerrain, String targetDoor, Vector3fl position, CubeFace orientation) {
        this.targetLevel = targetTerrain;
        this.targetDoor = targetDoor;
        this.position = position;
        this.orientation = orientation;
    }
}
