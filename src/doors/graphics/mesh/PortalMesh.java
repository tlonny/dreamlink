package doors.graphics.mesh;

import doors.core.graphics.mesh.Mesh;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.mesh.TextureSampleMode;
import doors.core.graphics.model.cube.CubeSchema;
import doors.core.graphics.texture.TextureSample;
import doors.graphics.rendertarget.PortalVirtualRenderTarget;
import doors.utility.vector.Vector3fl;

public class PortalMesh extends Mesh {

    private static TextureSample PORTAL_TEXTURE_SAMPLE = PortalVirtualRenderTarget.PORTAL_VIRTUAL_RENDER_TARGET.texture.createTextureSample();
    
    private static CubeSchema PORTAL_SCHEMA = new CubeSchema(
        new Vector3fl(-1f, 0f, -0.25f),
        Vector3fl.ZERO,
        new Vector3fl(2f, 2f, 0.25f),
        PORTAL_TEXTURE_SAMPLE,
        PORTAL_TEXTURE_SAMPLE,
        PORTAL_TEXTURE_SAMPLE,
        PORTAL_TEXTURE_SAMPLE,
        PORTAL_TEXTURE_SAMPLE,
        PORTAL_TEXTURE_SAMPLE
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
