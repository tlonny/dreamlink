package doors.level;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.TextureSample;
import doors.core.utility.CubeFace;
import doors.core.utility.vector.IVector3fl;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.Vector3in;

public class Block {
    private static IVector3fl DIMENSIONS = new Vector3in(1, 1, 1);

    public final int blockID;
    public final TextureSample textureSample;
    public final String name;

    public Block(int blockID, String name, TextureSample textureSample) {
        this.blockID = blockID;
        this.name = name;
        this.textureSample = textureSample;
    }

    public void writeBlockFace(MeshBuffer meshBuffer, IVector3fl position, CubeFace cubeFace) {
        meshBuffer.writeQuad(
            this.textureSample, 
            0,
            position,
            DIMENSIONS, 
            cubeFace, 
            Vector3fl.WHITE
        );
    }

}
