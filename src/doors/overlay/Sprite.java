package doors.overlay;

import org.joml.Vector2i;

import doors.Screen;
import doors.graphics.MeshBuffer;
import doors.graphics.TextureSample;
import doors.utility.CubeFace;

public class Sprite {

    public TextureSample textureSample;
    public Vector2i dimensions = new Vector2i();
    public Vector2i position = new Vector2i();

    public void write(MeshBuffer meshBuffer) {
        for(var ix = 0; ix < CubeFace.FRONT.vertices.length; ix +=1) {
            var vertex = CubeFace.FRONT.vertices[ix];
            meshBuffer.position.set(
                (this.position.x + vertex.x * this.dimensions.x) / Screen.RESOLUTION.x * 2f - 1,
                (Screen.RESOLUTION.y - this.dimensions.y - this.position.y + vertex.y * this.dimensions.y) / Screen.RESOLUTION.y * 2f - 1,
                -1f
            );
            meshBuffer.normal.set(CubeFace.FRONT.normal);
            meshBuffer.textureOffset.set(this.textureSample.textureOffsets[ix]);
            meshBuffer.writeVertex();
        }
    }

}
