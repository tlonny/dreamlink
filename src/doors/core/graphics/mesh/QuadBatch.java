package doors.core.graphics.mesh;

import doors.core.utility.vector.Vector3fl;

public class QuadBatch extends MeshBuffer {

    private Mesh mesh;

    public QuadBatch(int quadCapacity) {
        super(quadCapacity);
        this.mesh = new Mesh();
    }

    public void setup() {
        this.mesh.setup();
    }

    public void flush() {
        this.mesh.loadDataFromMeshBuffer(this);
        this.clear();
    }

    public void render() {
        this.mesh.render(
            Vector3fl.ZERO,
            Vector3fl.ZERO,
            Vector3fl.ONE,
            Vector3fl.WHITE
        );
    }
}
