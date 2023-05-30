package doors.overlay;

import org.joml.Vector2i;

import doors.Game;
import doors.graphics.Mesh;
import doors.graphics.MeshBuffer;

public class PerformanceTracker {

    private static int MAX_QUADS = 500;
    private static float EXPONENTIAL_WEIGHTING = 0.01f;

    private MeshBuffer meshBuffer = new MeshBuffer(MAX_QUADS);
    private Mesh mesh = new Mesh();
    private Text text = new Text();
    private Vector2i basePosition = new Vector2i(20, Game.DISPLAY.dimensions.y - 52);

    public long previousTime;
    public float framePeriod = 0f;

    public int numParticles;

    public void setup() {
        this.mesh.setup();
        this.previousTime = System.currentTimeMillis();
    }

    public void render() {
        var timeNow = System.currentTimeMillis();
        var newFramePeriod = timeNow - previousTime;
        this.previousTime = timeNow;

        this.framePeriod *= (1-EXPONENTIAL_WEIGHTING);
        this.framePeriod += EXPONENTIAL_WEIGHTING * newFramePeriod;
        var fps = 1000f/(this.framePeriod);

        this.meshBuffer.clear();
        this.text.position.set(basePosition);
        this.text.text = String.format("frames per second: %.2f", fps);
        this.text.write(this.meshBuffer);
        this.text.position.y -= 32;
        this.text.text = String.format("num particles: %s", this.numParticles);
        this.text.write(this.meshBuffer);
        this.meshBuffer.flip();
        this.mesh.loadFromMeshBuffer(this.meshBuffer);
        this.mesh.render();
    }

}
