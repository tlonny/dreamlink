package doors.graphics.mesh;

import doors.graphics.rendertarget.PortalVirtualRenderTarget;
import doors.graphics.template.CubeTemplate;
import doors.graphics.texture.TextureSample;
import doors.utility.vector.Vector3fl;

public class PortalMesh extends Mesh {

    private static TextureSample PORTAL_TEXTURE_SAMPLE = PortalVirtualRenderTarget.PORTAL_VIRTUAL_RENDER_TARGET.texture.createTextureSample();
    
    private static CubeTemplate PORTAL_SCHEMA = new CubeTemplate(
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
        MeshBuffer.DEFAULT_MESH_BUFFER.clear();
        PORTAL_SCHEMA.writeCubeToMeshBuffer(MeshBuffer.DEFAULT_MESH_BUFFER);
        MeshBuffer.DEFAULT_MESH_BUFFER.writeMeshTo(this);
    }

}
