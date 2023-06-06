package doors.overlay;

import org.joml.Vector2i;

import doors.Game;
import doors.graphics.Mesh;
import doors.graphics.MeshBuffer;
import doors.graphics.ShaderProgram;
import doors.graphics.Texture;
import doors.utility.Maths;

public class Reticule {

    private static int NUM_QUADS = 1;
    public static Vector2i RETICULE_SIZE = new Vector2i(16, 16);

    private Mesh mesh;
    private MeshBuffer meshBuffer;
    public Texture texture;

    public Reticule() {
        this.mesh = new Mesh();
        this.meshBuffer = new MeshBuffer(NUM_QUADS);

        var spriteMeshBufferWriter = new SpriteMeshBufferWriter(this.meshBuffer);
        spriteMeshBufferWriter.pushSprite(
            new Vector2i(
                VirtualScreen.RESOLUTION.x/2 - RETICULE_SIZE.x/2,
                VirtualScreen.RESOLUTION.y/2 - RETICULE_SIZE.y/2
            ),
            RETICULE_SIZE, 
            Game.OVERLAY_TEXTURE.reticule
        );
    }

    public void setup() {
        this.mesh.setup();
        this.mesh.bind();
        this.meshBuffer.flip();
        Mesh.loadFromMeshBuffer(this.meshBuffer);
        this.meshBuffer.clear();
    }

    public void render() {
        this.mesh.bind();
        Game.OVERLAY_TEXTURE.bind();

        ShaderProgram.setModel(Maths.VEC3F_ZERO, Maths.VEC3F_ONE);
        Mesh.render();

    }
}