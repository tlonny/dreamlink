package periwinkle.overlay;

import org.joml.Vector2i;
import org.joml.Vector3f;

import periwinkle.Game;
import periwinkle.graphics.MeshBuffer;
import periwinkle.graphics.Texture;
import periwinkle.utility.CubeFace;

public class SpriteElement {


    public Texture texture;
    public Vector2i dimensions = new Vector2i();
    public Vector2i position = new Vector2i();
    public Vector3f color = new Vector3f(1f, 1f, 1f);

    public SpriteElement(Texture texture) {
        this.texture = texture;
    }

    public SpriteElement() {
        this(null);
    }

    public void batch(MeshBuffer meshBuffer) {
        for(var ix = 0; ix < CubeFace.FRONT.vertices.length; ix +=1) {
            var vertex = CubeFace.FRONT.vertices[ix];
            meshBuffer.position.set(
                (vertex.x * this.dimensions.x + this.position.x) / Game.DISPLAY.dimensions.x * 2f - 1,
                (vertex.y * this.dimensions.y + this.position.y) / Game.DISPLAY.dimensions.y * 2f - 1,
                -1f
            );
            meshBuffer.normal.set(CubeFace.FRONT.normal);
            meshBuffer.textureOffset.set(this.texture.vertices[ix]);
            meshBuffer.color.set(this.color);
            meshBuffer.localLight = 1f;
            meshBuffer.globalLight = 0f;
            meshBuffer.push();
        }
    }

}
