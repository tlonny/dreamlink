package doors.level.block;

import doors.graphics.mesh.MeshBuffer;
import doors.graphics.texture.TextureSample;
import doors.utility.CubeFace;
import doors.utility.vector.IVector3fl;
import doors.utility.vector.Vector3fl;
import doors.utility.vector.Vector3in;

public class Block {
    private static IVector3fl DIMENSIONS = new Vector3in(1, 1, 1);

    public int blockID;
    public TextureSample textureSample;
    public String name;

    public Block(int blockID, String name, TextureSample textureSample) {
        this.blockID = blockID;
        this.name = name;
        this.textureSample = textureSample;
    }

    public void writeBlockFaceToMeshBuffer(MeshBuffer meshBuffer, IVector3fl position, CubeFace cubeFace) {
        meshBuffer.pushQuad(
            this.textureSample, 
            0,
            position,
            DIMENSIONS, 
            cubeFace, 
            Vector3fl.WHITE
        );
    }

}
