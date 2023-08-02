package doors.level.block;

import java.util.HashMap;

import doors.graphics.mesh.MeshBuffer;
import doors.graphics.texture.TextureSample;
import doors.utility.CubeFace;
import doors.utility.Orientation;
import doors.utility.vector.IVector3fl;
import doors.utility.vector.Vector3fl;
import doors.utility.vector.Vector3in;

public class Block {
    private static IVector3fl DIMENSIONS = new Vector3in(1, 1, 1);

    private HashMap<CubeFace, TextureSample> textureSamples = new HashMap<>();
    public int blockID;
    public String name;

    public Block(int blockID, String name) {
        this.blockID = blockID;
        this.name = name;
    }

    public void setTextureSample(CubeFace cubeFace, TextureSample textureSample) {
        this.textureSamples.put(cubeFace, textureSample);
    }

    public TextureSample getTextureSample(CubeFace cubeFace) {
        return this.textureSamples.get(cubeFace);
    }

    public void writeBlockFaceToMeshBuffer(MeshBuffer meshBuffer, IVector3fl position, Orientation orientation, CubeFace cubeFace, Block adjacentBlock) {
        if(adjacentBlock != null) {
            return;
        }

        var remappedCubeFace = orientation.remapCubeFace(cubeFace);
        var textureSample = this.textureSamples.get(remappedCubeFace);

        if(orientation.index != 0) {
            System.out.println(cubeFace);
            System.out.println(remappedCubeFace);
        }

        meshBuffer.pushQuad(
            textureSample, 
            0,
            position,
            orientation,
            DIMENSIONS, 
            cubeFace, 
            Vector3fl.WHITE
        );
    }

}
