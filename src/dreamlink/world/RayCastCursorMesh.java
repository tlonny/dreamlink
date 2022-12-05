package dreamlink.world;

import dreamlink.graphics.mesh.terrain.TerrainMesh;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.graphics.mesh.terrain.TerrainQuadData;
import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.maths.Vector3fMaths;

public class RayCastCursorMesh extends TerrainMesh {

    public static final RayCastCursorMesh instance = new RayCastCursorMesh();

    public void setup() {
        var meshBuffer = TerrainMeshBuffer.instance.clear();

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            var quad = new TerrainQuadData();
            quad.set(
                Vector3fMaths.zero,
                Vector3fMaths.zero,
                Vector3fMaths.one,
                cubeFace,
                Orientation.front,
                EntityTextureSample.selector,
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
