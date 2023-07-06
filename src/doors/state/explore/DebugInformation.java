package doors.state.explore;

import doors.Doors;
import doors.core.graphics.sprite.FontDecoration;
import doors.core.utility.vector.Vector3fl;
import doors.graphics.sprite.StandardFont;
import doors.core.utility.vector.Vector2in;

public class DebugInformation {

    public static DebugInformation DEBUG_INFORMATION = new DebugInformation();

    private static long SAMPLE_FRAMES = 20;

    private Vector2in positionCursor;
    private long previousTime;
    private long count;
    private float fps;

    public DebugInformation() {
        this.positionCursor = new Vector2in();
    }

    private void writeLine(String text) {
        StandardFont.STANDARD_FONT.writeText(
            Doors.TEXTURE_CHANNEL_FONT,
            text,
            this.positionCursor,
            FontDecoration.NORMAL,
            Vector3fl.WHITE
        );
        this.positionCursor.y += StandardFont.CHARACTER_DIMENSIONS.y;
    }

    public void update() {

        if(this.count >= SAMPLE_FRAMES) {
            var currentTime = System.currentTimeMillis();
            this.fps = (float)this.count / (currentTime - this.previousTime) * 1000;
            this.previousTime = currentTime;
            this.count = 0;
        }

        this.positionCursor.set(10, 10);
        this.writeLine(String.format("frames per second: %.2f", this.fps));
        this.writeLine(String.format("camera position: %s", Camera.CAMERA.position));
        this.writeLine(String.format("camera rotation: %s", Camera.CAMERA.rotation));

        this.count += 1;
    }

}
