package doors.debug;

import doors.graphics.mesh.Mesh;
import doors.graphics.mesh.MeshBuffer;
import doors.graphics.texture.sample.EntityTextureSample;
import doors.utility.CubeFace;
import doors.utility.vector.Vector3fl;

public class SimpleMesh extends Mesh {

    public SimpleMesh() {
        super();

        MeshBuffer.DEFAULT_MESH_BUFFER.clear();
        MeshBuffer.DEFAULT_MESH_BUFFER.pushQuad(
            EntityTextureSample.DEBUG,
            0,
            new Vector3fl(0.5f, 0.5f, 0.5f),
            0,
            new Vector3fl(0.2f, 0.2f, 0.2f),
            CubeFace.FRONT,
            Vector3fl.WHITE
        );
        MeshBuffer.DEFAULT_MESH_BUFFER.writeMeshBufferToMesh(this);
    }
    
}
