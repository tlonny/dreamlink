package doors.terrain;

import java.io.FileInputStream;
import java.io.IOException;

import org.joml.Vector3i;

import doors.graphics.Mesh;
import doors.utility.Maths;

public class Chunk {

    public static Vector3i DIMENSIONS = new Vector3i(16, 16, 16);

    public Mesh mesh = new Mesh();
    public Vector3i position;
    public boolean isDirty;
    public int[] blockData;
    private String path;

    public Chunk(Vector3i position, String path) {
        this.position = position;
        this.path = path;
        this.blockData = new int[Maths.volume(DIMENSIONS)];
    }

    public void setup() {
        this.mesh.setup();
        try(var fileInputStream = new FileInputStream(this.path)) {
            for(var ix = 0; ix < this.blockData.length; ix += 1) {
                var blockID = 0;
                blockID += fileInputStream.read();
                blockID += fileInputStream.read() * 0x100;
                this.blockData[ix] = blockID;
            }

        } catch (IOException e) {
            var msg = String.format("Unable to load chunk: %s", this.path);
            throw new RuntimeException(msg);
        }
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
