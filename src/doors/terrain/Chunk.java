package doors.terrain;

import org.joml.Vector3i;

import doors.graphics.Mesh;
import doors.utility.Maths;

public class Chunk {

    public static Vector3i DIMENSIONS = new Vector3i(16, 16, 16);

    public Mesh mesh = new Mesh();
    public Vector3i position;
    public boolean isDirty;
    public int[] blockData;

    public Chunk(Vector3i position) {
        this.position = position;
        this.blockData = new int[Maths.volume(DIMENSIONS)];
    }

    public void setup() {
        this.mesh.setup();
    }

    public boolean setBlockID(Vector3i position, int blockID) {
        var blockIndex = Maths.serialize(position, DIMENSIONS);
        var oldBlockID = this.blockData[blockIndex];
        var madeDirty = oldBlockID != blockID && !this.isDirty;
        this.blockData[blockIndex] = blockID;
        this.isDirty |= madeDirty;
        return madeDirty;

    }

    public int getBlockID(Vector3i position) {
        var blockIndex = Maths.serialize(position, DIMENSIONS);
        return this.blockData[blockIndex];
    }

}
