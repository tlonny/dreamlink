package doors.terrain;

import org.joml.Vector3f;

import doors.utility.CubeFace;

public class Door {

    public Terrain terrain;
    public String targetTerrain;
    public String targetDoor;
    public Vector3f position;
    public CubeFace orientation;

    public Door(Terrain terrain, String targetTerrain, String targetDoor, Vector3f position, CubeFace orientation) {
        this.terrain = terrain;
        this.targetTerrain = targetTerrain;
        this.targetDoor = targetDoor;
        this.position = position;
        this.orientation = orientation;
    }

    public void open() {
        var targetTerrain = TerrainCache.TERRAIN_CACHE.getTerrain(this.targetTerrain);
        var targetDoor = targetTerrain.doors.get(this.targetDoor);

        this.terrain.openPortal = new Portal(this, this.position, this.orientation);
        targetTerrain.openPortal = new Portal(targetDoor, targetDoor.position, targetDoor.orientation);

        this.terrain.openPortal.targetPortal = targetTerrain.openPortal;
        targetTerrain.openPortal.targetPortal = this.terrain.openPortal;
    }

}
