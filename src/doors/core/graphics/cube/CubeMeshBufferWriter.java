package doors.core.graphics.cube;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.utility.CubeFace;
import doors.core.utility.vector.Vector3fl;

public class CubeMeshBufferWriter {

    private MeshBuffer meshBuffer;

    public CubeMeshBufferWriter(MeshBuffer meshBuffer) {
        this.meshBuffer = meshBuffer;
    }

    public void writeCubeSchema(CubeSchema schema) {
        for(var cubeFace : CubeFace.CUBE_FACES) {
            var textureSample = schema.getTextureSample(cubeFace);
            this.meshBuffer.writeQuad(
                textureSample,
                schema.cubeID,
                schema.position,
                schema.dimensions,
                cubeFace,
                Vector3fl.WHITE
            );
        }
    }

}
