package doors.overlay;

import org.joml.Vector2i;
import org.joml.Vector3f;

import doors.Config;
import doors.graphics.Mesh;
import doors.graphics.MeshBuffer;
import doors.graphics.TextureSample;
import doors.utility.CubeFace;
import doors.utility.Maths;

public class Overlay {

    private static int MAX_QUADS = 5_000;
    public static Overlay OVERLAY = new Overlay();

    private Mesh mesh;
    private MeshBuffer meshBuffer;
    private Vector3f[] positionBuffer;

    public Overlay() {
        this.mesh = new Mesh();
        this.meshBuffer = new MeshBuffer(MAX_QUADS);
        this.positionBuffer = new Vector3f[4];

        for(var ix = 0; ix < this.positionBuffer.length; ix += 1) {
            this.positionBuffer[ix] = new Vector3f();
        }
    }

    public void setup() {
        this.mesh.setup();
    }

    public void paint(Vector2i position, Vector2i dimensions, TextureSample textureSample, Vector3f color) {
        for(var ix = 0; ix < CubeFace.FRONT.vertices.length; ix +=1) {
            var vertex = CubeFace.FRONT.vertices[ix];
            this.positionBuffer[ix].set(
                (position.x + vertex.x * dimensions.x) / Config.RESOLUTION.x * 2f - 1,
                (Config.RESOLUTION.y - dimensions.y - position.y + vertex.y * dimensions.y) / Config.RESOLUTION.y * 2f - 1,
                1f
            );
        }
        this.meshBuffer.pushQuad(this.positionBuffer, CubeFace.FRONT, textureSample, color);
    }

    public void render() {
        this.meshBuffer.flip();
        this.mesh.loadFromMeshBuffer(this.meshBuffer);
        this.meshBuffer.clear();
        this.mesh.render(Maths.VEC3F_ZERO, Maths.VEC3F_ZERO, Maths.VEC3F_ONE, Maths.VEC3F_ONE);
    }

}

