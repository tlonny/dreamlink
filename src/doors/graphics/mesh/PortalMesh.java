package doors.graphics.mesh;

import doors.graphics.texture.sample.PortalRenderTextureSample;
import doors.utility.vector.Vector3fl;

public class PortalMesh extends Mesh {

    private static CubeSchema PORTAL_SCHEMA = new CubeSchema(
        new Vector3fl(0, 0f, 0f).sub(1f, 0f, 0.5f),
        Vector3fl.ZERO,
        new Vector3fl(2f, 2f, 0.5f),
        PortalRenderTextureSample.PORTAL_RENDER,
        PortalRenderTextureSample.PORTAL_RENDER,
        PortalRenderTextureSample.PORTAL_RENDER,
        PortalRenderTextureSample.PORTAL_RENDER,
        PortalRenderTextureSample.PORTAL_RENDER,
        PortalRenderTextureSample.PORTAL_RENDER
    );

    public static PortalMesh PORTAL_MESH = new PortalMesh();

    public PortalMesh() {
        super();
        this.cullFaces = false;
        this.textureSampleMode = TextureSampleMode.SCREEN;
        MeshBuffer.DEFAULT_MESH_BUFFER.clear();
        PORTAL_SCHEMA.writeCubeToMeshBuffer(MeshBuffer.DEFAULT_MESH_BUFFER);
        MeshBuffer.DEFAULT_MESH_BUFFER.writeMeshBufferToMesh(this);
    }

}
