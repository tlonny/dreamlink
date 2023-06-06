package doors.terrain;

import org.joml.Vector3f;
import org.joml.Vector3i;

import doors.graphics.MeshBuffer;
import doors.utility.CubeFace;

public class BlockFaceMeshBufferWriter {

    public BlockFaceMeshBufferWriter(MeshBuffer meshBuffer) {
        this.meshBuffer = meshBuffer;
    }

    private MeshBuffer meshBuffer;

    public void pushBlockFace(Vector3i position, CubeFace cubeFace, Block block) {
        for(var ix = 0; ix < cubeFace.vertices.length; ix += 1) {
            var vertex = cubeFace.vertices[ix];
            this.meshBuffer.pushVertex(
                new Vector3f(position).add(vertex),
                new Vector3f(cubeFace.normal),
                block.textureSample.textureOffsets[ix]
            );
        }
    }

}
