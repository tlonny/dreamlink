package doors.level;

import doors.core.graphics.font.FontDecoration;
import doors.core.graphics.mesh.MeshBuffer;
import doors.graphics.font.Font;
import doors.level.camera.Camera;
import doors.state.AbstractGameState;
import doors.utility.vector.Vector3fl;
import doors.utility.CubeFace;
import doors.utility.vector.Vector2in;

public class DebugInformation {

    public static DebugInformation DEBUG_INFORMATION = new DebugInformation();

    private static long SAMPLE_FRAMES = 20;

    private long previousTime;
    private long count;
    private float fps;

    private MeshBuffer meshBuffer = new MeshBuffer(5_000);
    private Vector2in positionCursor = new Vector2in();
    private Vector3fl rotatedNormal = new Vector3fl();

    private void writeLine(String text) {
        Font.FONT.writeText(
            this.meshBuffer,
            text,
            this.positionCursor,
            FontDecoration.NORMAL,
            Vector3fl.WHITE
        );
        this.positionCursor.y += Font.CHARACTER_DIMENSIONS.y;
    }

    public void update(Camera camera) {
        if(this.count >= SAMPLE_FRAMES) {
            var currentTime = System.currentTimeMillis();
            this.fps = (float)this.count / (currentTime - this.previousTime) * 1000;
            this.previousTime = currentTime;
            this.count = 0;
        }

        this.rotatedNormal.set(CubeFace.FRONT.normal);
        this.rotatedNormal.rotateX(camera.rotation.x);
        this.rotatedNormal.rotateY(camera.rotation.y);
        this.positionCursor.set(10, 10);
        this.writeLine(String.format("frames per second: %.2f", this.fps));
        this.writeLine(String.format("camera position: %s", camera.position));
        this.writeLine(String.format("camera velocity: %s", camera.velocity));
        this.writeLine(String.format("camera normal: %s", this.rotatedNormal));
        this.writeLine(String.format("game state: %s", AbstractGameState.USED_GAME_STATE.getClass().getSimpleName()));

        this.count += 1;
    }

}
