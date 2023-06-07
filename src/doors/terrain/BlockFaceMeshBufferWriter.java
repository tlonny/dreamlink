package doors.terrain;

import org.joml.Vector3f;
import org.joml.Vector3i;

import doors.graphics.MeshBuffer;
import doors.graphics.TextureChannel;
import doors.utility.CubeFace;

public class BlockFaceMeshBufferWriter {

    private MeshBuffer meshBuffer;
    private Vector3f[] positionBuffer;

    public BlockFaceMeshBufferWriter(MeshBuffer meshBuffer) {
        this.meshBuffer = meshBuffer;
        this.positionBuffer = new Vector3f[4];
        for(var ix = 0; ix < this.positionBuffer.length; ix +=1) {
            this.positionBuffer[ix] = new Vector3f();
        }
    }

    public void pushBlockFace(Vector3i position, CubeFace cubeFace, Block block, Vector3f color) {
        for(var ix = 0; ix < this.positionBuffer.length; ix += 1) {
            var vertex = cubeFace.vertices[ix];
            this.positionBuffer[ix].set(position).add(vertex);
        }

        var normal = new Vector3f(cubeFace.normal);
        this.meshBuffer.pushQuad(this.positionBuffer, normal, block.textureSample, TextureChannel.TERRAIN_TEXTURE_CHANNEL, color);
    }

}
