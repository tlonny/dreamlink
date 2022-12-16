package periwinkle;

import org.joml.Vector2f;
import org.joml.Vector3f;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.Display;
import periwinkle.graphics.Mesh;
import periwinkle.graphics.MeshBuffer;

public class Reticule {

    private static Vector2f RETICULE_SIZE = new Vector2f(32, 32);

    public static Reticule RETICULE = new Reticule();

    public static void init() {
        RETICULE.setup();
    }

    private final Mesh reticuleMesh = new Mesh(Atlas.UI_ATLAS);
    private final MeshBuffer reticuleBuffer = new MeshBuffer(1);

    public void setup() {
        this.reticuleBuffer.pushSprite(
            new Vector2f(
                Display.DISPLAY.width/2f - RETICULE_SIZE.x/2, 
                Display.DISPLAY.height/2f - RETICULE_SIZE.y/2
            ),
            RETICULE_SIZE,
            Atlas.UI_ATLAS.getSprite("RETICULE")
        );
        this.reticuleBuffer.flip();
        this.reticuleMesh.setup();
        this.reticuleMesh.loadMesh(this.reticuleBuffer);
    }

    public void render() {
        this.reticuleMesh.render(new Vector3f(), new Vector3f(1f, 1f, 1f), 0f);
    }

}
