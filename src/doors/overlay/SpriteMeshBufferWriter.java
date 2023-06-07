package doors.overlay;

import org.joml.Vector2i;
import org.joml.Vector3f;

import doors.Config;
import doors.graphics.MeshBuffer;
import doors.graphics.TextureChannel;
import doors.graphics.TextureSample;
import doors.utility.CubeFace;

public class SpriteMeshBufferWriter {

    private MeshBuffer meshBuffer;

    private Vector3f[] positionBuffer;

    public SpriteMeshBufferWriter(MeshBuffer meshBuffer) {
        this.meshBuffer = meshBuffer;
        this.positionBuffer = new Vector3f[4];
        for(var ix = 0; ix < this.positionBuffer.length; ix +=1) {
            this.positionBuffer[ix] = new Vector3f();
        }
    }

    public void pushSprite(Vector2i position, Vector2i dimensions, TextureSample textureSample, TextureChannel textureChannel, Vector3f color) {
        for(var ix = 0; ix < CubeFace.FRONT.vertices.length; ix +=1) {
            var vertex = CubeFace.FRONT.vertices[ix];
            this.positionBuffer[ix].set(
                (position.x + vertex.x * dimensions.x) / Config.RESOLUTION.x * 2f - 1,
                (Config.RESOLUTION.y - dimensions.y - position.y + vertex.y * dimensions.y) / Config.RESOLUTION.y * 2f - 1,
                1f
            );
        }
        var normal = new Vector3f(CubeFace.FRONT.normal);
        this.meshBuffer.pushQuad(this.positionBuffer, normal, textureSample, textureChannel, color);
    }

}
