package doors.overlay;

import org.joml.Vector2i;

import doors.Game;
import doors.graphics.Mesh;
import doors.graphics.MeshBuffer;
import doors.graphics.RenderTarget;
import doors.graphics.ShaderProgram;
import doors.graphics.TextureSample;
import doors.utility.Maths;

public class VirtualScreen extends RenderTarget {

    private static int NUM_QUADS = 1;
    public static Vector2i RESOLUTION = new Vector2i(1280, 720);

    private Mesh mesh;
    private MeshBuffer meshBuffer;

    public VirtualScreen() {
        super(RESOLUTION);
        this.mesh = new Mesh();
        this.meshBuffer = new MeshBuffer(NUM_QUADS);
        var spriteMeshBufferWriter = new SpriteMeshBufferWriter(this.meshBuffer);
        spriteMeshBufferWriter.pushSprite(Maths.VEC2I_ZERO, RESOLUTION, new TextureSample());
    }

    public void setup() {
        super.setup();
        this.mesh.setup();
        this.mesh.bind();

        this.meshBuffer.flip();
        Mesh.loadFromMeshBuffer(this.meshBuffer);
        this.meshBuffer.clear();
    }

    public void render() {
        this.mesh.bind();
        this.texture.bind();

        ShaderProgram.setModel(Maths.VEC3F_ZERO, Maths.VEC3F_ONE);
        Mesh.render();
    }

}
