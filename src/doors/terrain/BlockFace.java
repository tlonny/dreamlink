package doors.terrain;

import org.joml.Vector3i;

import doors.graphics.MeshBuffer;
import doors.utility.CubeFace;

public class BlockFace {

    public Block block;
    public Vector3i position = new Vector3i();
    public CubeFace cubeFace = CubeFace.FRONT;

    public void write(MeshBuffer meshBuffer) {
        for(var ix = 0; ix < this.cubeFace.vertices.length; ix += 1) {
            var vertex = this.cubeFace.vertices[ix];
            meshBuffer.position.set(this.position).add(vertex);
            meshBuffer.textureOffset.set(this.block.textureSample.textureOffsets[ix]);
            meshBuffer.normal.set(cubeFace.normal);
            meshBuffer.writeVertex();
        }
    }

}
