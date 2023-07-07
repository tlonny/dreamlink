package doors.core.graphics.cube;

import doors.core.graphics.mesh.Mesh;
import doors.core.graphics.mesh.MeshBuffer;

public class CubeMesh extends Mesh {

    private CubeSchema[] schema;

    public CubeMesh(CubeSchema[] schema) {
        this.schema = schema;
    }

    public void setup() {
        super.setup();
        var cubeWriter = new CubeMeshBufferWriter(MeshBuffer.DEFAULT_MESH_BUFFER);
        MeshBuffer.DEFAULT_MESH_BUFFER.clear();
        for(var cubeSchema : this.schema) {
            cubeWriter.writeCubeSchema(cubeSchema);
        }
        MeshBuffer.DEFAULT_MESH_BUFFER.flip();
        this.loadDataFromMeshBuffer(MeshBuffer.DEFAULT_MESH_BUFFER);
    }


}
