package doors.level;

import doors.utility.CubeFace;
import doors.utility.geometry.Vector3fl;

public class Door {

    public Terrain terrain;
    public String targetTerrain;
    public String targetDoor;
    public Vector3fl position;
    public CubeFace orientation;

    public Door(Terrain terrain, String targetTerrain, String targetDoor, Vector3fl position, CubeFace orientation) {
        this.terrain = terrain;
        this.targetTerrain = targetTerrain;
        this.targetDoor = targetDoor;
        this.position = position;
        this.orientation = orientation;
    }

    public void open() {
    }

}
