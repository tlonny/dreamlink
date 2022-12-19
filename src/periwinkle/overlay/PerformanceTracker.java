package periwinkle.overlay;

import org.joml.Vector2i;
import org.joml.Vector3f;

import periwinkle.Game;
import periwinkle.graphics.Mesh;
import periwinkle.graphics.MeshBuffer;

public class PerformanceTracker {

    private static int MAX_QUADS = 500;
    private static Vector3f POSITION_ZERO = new Vector3f();
    private static float EXPONENTIAL_WEIGHTING = 0.01f;

    private MeshBuffer meshBuffer = new MeshBuffer(MAX_QUADS);
    private Mesh mesh = new Mesh(Game.OVERLAY_ATLAS);
    private TextElement textBuffer = new TextElement();
    private Vector2i basePosition = new Vector2i(20, Game.DISPLAY.dimensions.y - 52);

    public boolean active = false;

    public long previousTime;
    public float framePeriod = 0f;

    public int numParticles;

    public void setup() {
        this.mesh.setup();
        this.previousTime = System.currentTimeMillis();
    }

    public void simulate() {
        var timeNow = System.currentTimeMillis();
        var newFramePeriod = timeNow - previousTime;
        this.previousTime = timeNow;

        this.framePeriod *= (1-EXPONENTIAL_WEIGHTING);
        this.framePeriod += EXPONENTIAL_WEIGHTING * newFramePeriod;

        if(this.active) {
            this.meshBuffer.clear();
            this.textBuffer.position.set(basePosition);
            var fps = 1000f/(this.framePeriod);
            this.textBuffer.text = String.format("frames per second: %.2f", fps);
            this.textBuffer.batch(this.meshBuffer);
            this.textBuffer.position.y -= 32;
            this.textBuffer.text = String.format("num particles: %s", this.numParticles);
            this.textBuffer.batch(this.meshBuffer);
            this.meshBuffer.flip();
            this.mesh.loadFromBuffer(this.meshBuffer);
        }
    }

    public void render() {
        if(this.active) {
            this.mesh.render(POSITION_ZERO);
        }
    }


}
