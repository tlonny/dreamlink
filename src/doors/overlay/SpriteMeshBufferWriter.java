package doors.overlay;

import org.joml.Vector2i;
import org.joml.Vector3f;

import doors.graphics.MeshBuffer;
import doors.graphics.TextureSample;
import doors.utility.CubeFace;

public class SpriteMeshBufferWriter {

    public SpriteMeshBufferWriter(MeshBuffer meshBuffer) {
        this.meshBuffer = meshBuffer;
    }

    private MeshBuffer meshBuffer;

    public void pushSprite(Vector2i position, Vector2i dimensions, TextureSample textureSample) {
        for(var ix = 0; ix < CubeFace.FRONT.vertices.length; ix +=1) {
            var vertex = CubeFace.FRONT.vertices[ix];

            this.meshBuffer.pushVertex(
                new Vector3f(
                    (position.x + vertex.x * dimensions.x) / VirtualScreen.RESOLUTION.x * 2f - 1,
                    (VirtualScreen.RESOLUTION.y - dimensions.y - position.y + vertex.y * dimensions.y) / VirtualScreen.RESOLUTION.y * 2f - 1,
                    1f
                ),
                new Vector3f(CubeFace.FRONT.normal),
                textureSample.textureOffsets[ix]
            );
        }
    }

}
