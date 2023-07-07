package doors.level;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.utility.CubeFace;
import doors.core.utility.vector.IVector3fl;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.Vector3in;

public class BlockFaceMeshBufferWriter {

    private static IVector3fl DIMENSIONS = new Vector3in(1, 1, 1);

    private MeshBuffer meshBuffer;

    public BlockFaceMeshBufferWriter(MeshBuffer meshBuffer) {
        this.meshBuffer = meshBuffer;
    }

    public void writeBlockFace(IVector3fl position, CubeFace cubeFace, Block block) {
        meshBuffer.writeQuad(
            block.textureSample, 
            0,
            position,
            DIMENSIONS, 
            cubeFace, 
            Vector3fl.WHITE
        );
    }
}
