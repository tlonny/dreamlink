package doors;

import doors.core.graphics.mesh.Mesh;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.utility.vector.Vector3fl;

public class Screen {

    private static int MAX_QUADS = 5_000;
    public static Screen SCREEN = new Screen();

    public MeshBuffer meshBuffer;
    private Mesh mesh;

    public Screen() {
        this.meshBuffer = new MeshBuffer(MAX_QUADS);
        this.mesh = new Mesh();
    }

    public void setup() {
        this.mesh.setup();
    }

    public void flush() {
        this.meshBuffer.flip();
        this.mesh.loadDataFromMeshBuffer(this.meshBuffer);
        this.mesh.render(Vector3fl.ZERO, Vector3fl.ZERO, Vector3fl.ONE, Vector3fl.WHITE);
        this.meshBuffer.clear();

    }

}
