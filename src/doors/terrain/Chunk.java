package doors.terrain;

import org.joml.Vector3i;

import doors.graphics.Mesh;
import doors.utility.VectorSpace;

public class Chunk {

    public static Vector3i CHUNK_DIMENSIONS = new Vector3i(16, 16, 16);

    public int[] blockData;
    public VectorSpace blockSpace;
    public Mesh mesh;
    public Vector3i position;
    public boolean isDirty;

    public Chunk() {
        this.blockSpace = new VectorSpace(CHUNK_DIMENSIONS);
        this.mesh = new Mesh();
        this.position = new Vector3i();
        this.blockData = new int[this.blockSpace.getMaxIndex()];
    }

    public void setup() {
        this.mesh.setup();
    }

    public boolean setBlockID(Vector3i position, int blockID) {
        var blockIndex = this.blockSpace.getIndex(position);        
        var oldBlockID = this.blockData[blockIndex];
        var madeDirty = oldBlockID != blockID && !this.isDirty;
        this.blockData[blockIndex] = blockID;
        this.isDirty |= madeDirty;
        return madeDirty;

    }

    public int getBlockID(Vector3i position) {
        var blockIndex = this.blockSpace.getIndex(position);        
        return this.blockData[blockIndex];
    }

}
