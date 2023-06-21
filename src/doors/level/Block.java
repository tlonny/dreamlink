package doors.level;

import doors.graphics.mesh.MeshBuffer;
import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSample;
import doors.utility.CubeFace;
import doors.utility.geometry.IVector3fl;
import doors.utility.geometry.Vector3fl;
import doors.utility.geometry.Vector3in;

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

    public void writeBlockFace(MeshBuffer meshBuffer, IVector3fl position, CubeFace cubeFace, Block block) {
        meshBuffer.writeQuad(
            block.textureSample, 
            TextureChannel.TERRAIN_TEXTURE_CHANNEL,
            position, 
            DIMENSIONS, 
            cubeFace, 
            Vector3fl.ONE
        );
    }
}
