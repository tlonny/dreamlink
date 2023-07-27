package doors.level.terrain;

import java.io.FileInputStream;
import java.io.IOException;

import doors.graphics.mesh.Mesh;
import doors.utility.vector.Vector3in;

public class Chunk {

    public static Vector3in DIMENSIONS = new Vector3in(16, 16, 16);

    public int[] blockData = new int[DIMENSIONS.getIntVolume()];
    public Mesh mesh = new Mesh();
    public boolean isDirty = false;
    public Vector3in position = new Vector3in();

    private String path;

    public Chunk(String path) {
        this.path = path;
    }

    public void setup() {
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
}
