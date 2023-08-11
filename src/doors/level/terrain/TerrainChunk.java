package doors.level.terrain;

import doors.graphics.mesh.Mesh;
import doors.utility.vector.Vector3in;

public class TerrainChunk {

    public static Vector3in CHUNK_DIMENSIONS = new Vector3in(16, 16, 16);

    public int[] terrainData = new int[CHUNK_DIMENSIONS.getIntVolume()];
    public Mesh mesh = new Mesh();
    public Vector3in position = new Vector3in();
    private boolean isDirty = true;

    public boolean isDirty() {
        return this.isDirty;
    }

    public boolean setDirty() {
        var wasDirty = this.isDirty;
        this.isDirty = true;
        return !wasDirty;
    }

    public void setClean() {
        this.isDirty = false;
    }
}
