package dreamlink.world.room.module.door;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import dreamlink.graphics.mesh.terrain.TerrainMesh;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.graphics.mesh.terrain.TerrainQuadData;
import dreamlink.graphics.texture.sample.PortalTextureSample;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.maths.Vector3fMaths;

public class PortalMesh extends TerrainMesh {

    public static final PortalMesh instance = new PortalMesh();

    private static final Vector3fc position = new Vector3f(-1f, -1f, -0.5f);
    private static final Vector3fc dimensions = new Vector3f(2f, 2f, 0.5f);

    public void setup() {
        var meshBuffer = TerrainMeshBuffer.instance.clear();

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            if(cubeFace == CubeFace.front) {
                continue;
            }

            var quad = new TerrainQuadData();
            quad.set(
                PortalMesh.position,
                Vector3fMaths.zero,
                PortalMesh.dimensions,
                cubeFace,
                Orientation.front,
                PortalTextureSample.instance,
                false,
                false,
                0
            );
            meshBuffer.addQuad(quad);
        }

        super.setup();
        this.buffer(meshBuffer);
    }
    
}
