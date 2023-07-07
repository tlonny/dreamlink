package doors.entity;

import doors.Doors;
import doors.core.graphics.cube.CubeMesh;
import doors.core.graphics.cube.CubeSchema;
import doors.core.graphics.mesh.TextureSampleMode;
import doors.core.utility.vector.Vector3fl;

public class PortalMesh extends CubeMesh {
    
    private static CubeSchema PORTAL_SCHEMA = new CubeSchema(
        new Vector3fl(-1f, 0f, -0.25f),
        Vector3fl.ZERO,
        new Vector3fl(2f, 2f, 0.25f),
        Doors.RENDER_TARGET_PORTAL.screenSample,
        Doors.RENDER_TARGET_PORTAL.screenSample,
        Doors.RENDER_TARGET_PORTAL.screenSample,
        Doors.RENDER_TARGET_PORTAL.screenSample,
        Doors.RENDER_TARGET_PORTAL.screenSample,
        Doors.RENDER_TARGET_PORTAL.screenSample
    );

    private static CubeSchema[] CUBE_SCHEMAS = new CubeSchema[] {
        PORTAL_SCHEMA
    };

    public static PortalMesh PORTAL_MESH = new PortalMesh();

    public PortalMesh() {
        super(CUBE_SCHEMAS);
        this.cullFaces = false;
        this.textureSampleMode = TextureSampleMode.SCREEN;
    }

}
