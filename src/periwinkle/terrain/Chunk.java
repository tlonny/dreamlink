package periwinkle.terrain;

import periwinkle.Game;
import periwinkle.graphics.Mesh;
import org.joml.Vector3i;

public class Chunk {

    public static int BLOCK_LENGTH = 16;
    public static int NUM_BLOCKS = BLOCK_LENGTH * BLOCK_LENGTH * BLOCK_LENGTH;

    private final int[] blockData = new int[NUM_BLOCKS];
    public Mesh mesh = new Mesh(Game.TERRAIN_ATLAS);
    public boolean isDirty;

    private int getIndex(Vector3i v) {
        return v.x + v.y * BLOCK_LENGTH + v.z * BLOCK_LENGTH * BLOCK_LENGTH;
    }

    public int getValue(Vector3i v) {
        var ix = this.getIndex(v);
        return this.blockData[ix];
    }

    public void setValue(Vector3i v, int value) {
        var ix = this.getIndex(v);
        this.blockData[ix] = value;
    }
}
