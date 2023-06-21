package doors.level;

import doors.utility.geometry.Vector3fl;

public class Portal {

    // Remember that the portal can rotate by 90 degrees, so its important
    // that the X and Z dimensions are the same so that the dimensions are valid
    // invariant of the rotation.
    public static Vector3fl TELEPORTER_DIMENSIONS = new Vector3fl(2.0f, 2.0f, 1.0f);

    public static Portal PORTAL = new Portal();

    public Door startDoor;
    public Terrain startTerrain;

    public Door endDoor;
    public Terrain endTerrain;

    public boolean isOpen() {
        return this.startDoor != null && this.endDoor != null;
    }

    public void render() {
    }

    public void update() {
    }
}
