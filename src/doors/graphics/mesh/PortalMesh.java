package doors.graphics.mesh;

import doors.graphics.cube.CubeSchema;
import doors.graphics.rendertarget.VirtualRenderTarget;
import doors.utility.vector.Vector3fl;

public class PortalMesh extends Mesh {
    
    private static CubeSchema PORTAL_SCHEMA = new CubeSchema(
        new Vector3fl(-1f, 0f, -0.25f),
        Vector3fl.ZERO,
        new Vector3fl(2f, 2f, 0.25f),
        VirtualRenderTarget.RENDER_TARGET_PORTAL.screenSample,
        VirtualRenderTarget.RENDER_TARGET_PORTAL.screenSample,
        VirtualRenderTarget.RENDER_TARGET_PORTAL.screenSample,
        VirtualRenderTarget.RENDER_TARGET_PORTAL.screenSample,
        VirtualRenderTarget.RENDER_TARGET_PORTAL.screenSample,
        VirtualRenderTarget.RENDER_TARGET_PORTAL.screenSample
    );

    public static PortalMesh PORTAL_MESH = new PortalMesh();

    public PortalMesh() {
        super();
        this.cullFaces = false;
        this.textureSampleMode = TextureSampleMode.SCREEN;
    }

    @Override
    public void setup() {
        super.setup();
        MeshBuffer.DEFAULT_MESH_BUFFER.clear();
        PORTAL_SCHEMA.writeCubeSchema(MeshBuffer.DEFAULT_MESH_BUFFER);
        this.loadDataFromMeshBuffer(MeshBuffer.DEFAULT_MESH_BUFFER);
    }

}
