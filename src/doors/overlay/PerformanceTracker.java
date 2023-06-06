package doors.overlay;

import org.joml.Vector2i;

import doors.Game;
import doors.graphics.Mesh;
import doors.graphics.MeshBuffer;
import doors.graphics.ShaderProgram;
import doors.utility.Maths;
import doors.utility.Timer;

public class PerformanceTracker {

    private static int MAX_QUADS = 500;
    private static float EXPONENTIAL_WEIGHTING = 0.01f;
    private static int UPDATE_MS = 50;
    private static Vector2i BASE_POSITION = new Vector2i(4, 4);
    public static Vector2i CHARACTER_DIMENSIONS = new Vector2i(16, 32);

    private Timer updateTimer;
    private Mesh mesh;
    private MeshBuffer meshBuffer;
    private TextMeshBufferWriter textMeshBufferWriter;
    private Vector2i positionCursor;

    public long previousTime;
    public float framePeriod;

    public PerformanceTracker() {
        this.updateTimer = new Timer();
        this.mesh = new Mesh();
        this.meshBuffer = new MeshBuffer(MAX_QUADS);
        this.textMeshBufferWriter = new TextMeshBufferWriter(this.meshBuffer);
        this.positionCursor = new Vector2i(BASE_POSITION);
    }

    public void setup() {
        this.mesh.setup();
        this.previousTime = System.currentTimeMillis();
    }

    private void write(String text) {
        this.textMeshBufferWriter.pushText(this.positionCursor, CHARACTER_DIMENSIONS, text, false);
        this.positionCursor.y += CHARACTER_DIMENSIONS.y;
    }

    public void render() {
        var timeNow = System.currentTimeMillis();
        var newFramePeriod = timeNow - previousTime;
        this.previousTime = timeNow;

        this.framePeriod *= (1-EXPONENTIAL_WEIGHTING);
        this.framePeriod += EXPONENTIAL_WEIGHTING * newFramePeriod;
        this.mesh.bind();

        if(this.updateTimer.millisElapsed() > UPDATE_MS) {
            var fps = 1000f/(this.framePeriod);
            this.updateTimer.resetStartTime();

            this.positionCursor.set(BASE_POSITION);
            this.write(String.format("frames per second: %.2f", fps));
            this.write(String.format(
                    "player position: %.1f, %.1f, %.1f", 
                    Game.PLAYER.spatialComponent.position.x, 
                    Game.PLAYER.spatialComponent.position.y, 
                    Game.PLAYER.spatialComponent.position.z
            ));

            this.meshBuffer.flip();
            Mesh.loadFromMeshBuffer(this.meshBuffer);
            this.meshBuffer.clear();
        }

        Game.OVERLAY_TEXTURE.bind();

        ShaderProgram.setModel(Maths.VEC3F_ZERO, Maths.VEC3F_ONE);
        Mesh.render();
    }

}
