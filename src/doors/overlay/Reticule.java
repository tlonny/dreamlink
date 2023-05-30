package doors.overlay;

import org.joml.Vector2i;

import doors.Game;
import doors.graphics.Mesh;
import doors.graphics.MeshBuffer;

public class Reticule {

    private static Vector2i RETICULE_SIZE = new Vector2i(16, 16);
    private static int NUM_QUADS = 1;

    private MeshBuffer meshBuffer = new MeshBuffer(NUM_QUADS);
    private Mesh mesh = new Mesh();
    private Sprite sprite = new Sprite();

    public Reticule() {
        this.sprite.textureSample = Game.OVERLAY_TEXTURE.reticule;
        this.sprite.dimensions.set(RETICULE_SIZE);
        this.sprite.position.set(
            Game.DISPLAY.dimensions.x/2 - RETICULE_SIZE.x/2,
            Game.DISPLAY.dimensions.y/2 - RETICULE_SIZE.y/2
        );
    }

    public void setup() {
        this.mesh.setup();
        this.sprite.write(this.meshBuffer);
        this.meshBuffer.flip();
        this.mesh.loadFromMeshBuffer(this.meshBuffer);
    }

    public void render() {
        this.mesh.render();
    }

}
