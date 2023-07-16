package doors.debug;

import doors.graphics.font.Font;
import doors.graphics.font.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.io.Mouse;
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

    private Vector2in positionCursor = new Vector2in();
    private Vector3fl cameraPosition = new Vector3fl();
    private Vector3fl cameraNormal = new Vector3fl();

    private void writeLine(SpriteBatch spriteBatch, String text) {
        Font.FONT.writeText(
            spriteBatch,
            text,
            this.positionCursor,
            SpriteBatchHeight.HUD,
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

        this.cameraPosition.set(camera.position);
        this.cameraNormal.set(CubeFace.FRONT.normal);
        this.cameraNormal.rotateX(camera.rotation.x);
        this.cameraNormal.rotateY(camera.rotation.y);
        this.positionCursor.set(10, 10);
    }

    public void writeDebugInformation(SpriteBatch spriteBatch) {
        this.writeLine(spriteBatch, String.format("frames per second: %.2f", this.fps));
        this.writeLine(spriteBatch, String.format("camera position: %s", this.cameraPosition));
        this.writeLine(spriteBatch, String.format("camera normal: %s", this.cameraNormal));
        this.writeLine(spriteBatch, String.format("game state: %s", AbstractGameState.USED_GAME_STATE.getClass().getSimpleName()));
        this.writeLine(spriteBatch, String.format("mouse position: %s", Mouse.MOUSE.position));
        this.count += 1;
    }

}
