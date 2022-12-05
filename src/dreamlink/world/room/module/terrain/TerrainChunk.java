package dreamlink.world.room.module.terrain;

import org.joml.Vector3i;
import org.joml.Vector3ic;

import dreamlink.graphics.mesh.terrain.TerrainMesh;
import dreamlink.utility.maths.Vector3iMaths;

public class TerrainChunk {

    private static final int visualDataMask = (1 << 28) - 1;
    private static final int blockIDMask = 0x3FF;
    public static final Vector3ic chunkDimensions = new Vector3i(16);
    public static final int blockCount = Vector3iMaths.volume(TerrainChunk.chunkDimensions);

    private final int[] data = new int[TerrainChunk.blockCount];
    public final TerrainMesh mesh = new TerrainMesh();
    public final Vector3ic position;

    private long tally;

    public TerrainChunk(Vector3i position) {
        this.position = new Vector3i(position);
    }

    public int getData(int index) {
        return this.data[index];
    }

    public boolean isEmpty() {
        return this.tally == 0;
    }

    public boolean setData(int index, int value) {
        // Keep a running tally of the sum of all blockIDs in the chunk
        // If the tally is zero, the chunk is empty...
        var oldData = this.data[index];
        this.tally -= oldData & TerrainChunk.blockIDMask;
        this.tally += value & TerrainChunk.blockIDMask;
        this.data[index] = value;
        return ((oldData ^ value) & TerrainChunk.visualDataMask) != 0;
    }

    public void setup() {
        this.mesh.setup();
    }

    public void destroy() {
        this.mesh.destroy();
    }

    public void render() {
        this.mesh.render();
    }

}
