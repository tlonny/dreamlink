package doors.overlay;

import org.joml.Vector2i;

import doors.Game;
import doors.graphics.MeshBuffer;
import doors.graphics.TextureSample;
import doors.utility.CubeFace;

public class Sprite {

    public TextureSample textureSample;
    public Vector2i dimensions = new Vector2i();
    public Vector2i position = new Vector2i();

    public Sprite(TextureSample textureSample) {
        this.textureSample = textureSample;
    }

    public Sprite() {
        this(null);
    }

    public void writeToMeshBuffer(MeshBuffer meshBuffer) {
        for(var ix = 0; ix < CubeFace.FRONT.vertices.length; ix +=1) {
            var vertex = CubeFace.FRONT.vertices[ix];
            meshBuffer.position.set(
                (vertex.x * this.dimensions.x + this.position.x) / Game.DISPLAY.dimensions.x * 2f - 1,
                (vertex.y * this.dimensions.y + this.position.y) / Game.DISPLAY.dimensions.y * 2f - 1,
                -1f
            );
            meshBuffer.normal.set(CubeFace.FRONT.normal);
            meshBuffer.textureOffset.set(this.textureSample.textureOffsets[ix]);
            meshBuffer.push();
        }
    }

}
