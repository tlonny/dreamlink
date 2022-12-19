package periwinkle.overlay;

import org.joml.Vector2i;
import org.joml.Vector3f;

import periwinkle.Game;
import periwinkle.graphics.Mesh;
import periwinkle.graphics.MeshBuffer;

public class Reticule {

    private static Vector2i RETICULE_SIZE = new Vector2i(16, 16);
    private static Vector3f ZERO_POSITION = new Vector3f(0, 0, 0);
    private static int NUM_QUADS = 1;

    private MeshBuffer meshBuffer = new MeshBuffer(NUM_QUADS);
    private Mesh mesh = new Mesh(Game.OVERLAY_ATLAS);
    private SpriteElement spriteBuffer = new SpriteElement(Game.OVERLAY_ATLAS.reticule);

    public Reticule() {
        this.spriteBuffer = new SpriteElement(Game.OVERLAY_ATLAS.reticule);
        this.spriteBuffer.dimensions.set(RETICULE_SIZE);
        this.spriteBuffer.position.set(
            Game.DISPLAY.dimensions.x/2 - RETICULE_SIZE.x/2,
            Game.DISPLAY.dimensions.y/2 - RETICULE_SIZE.y/2
        );
    }

    public void setup() {
        this.mesh.setup();
        this.spriteBuffer.batch(this.meshBuffer);
        this.meshBuffer.flip();
        this.mesh.loadFromBuffer(this.meshBuffer);
    }

    public void render() {
        this.mesh.render(ZERO_POSITION);
    }

}
