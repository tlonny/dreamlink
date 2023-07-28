package doors.debug;

import doors.graphics.text.Glyph;
import doors.graphics.text.TextFragment;
import doors.graphics.text.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
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
    private TextFragment textFragment = new TextFragment();

    private void writeLine(SpriteBatch spriteBatch, String key, String value) {
        this.textFragment.clear();
        this.textFragment.pushText(String.format("%s: ", key), FontDecoration.NORMAL, Vector3fl.WHITE);
        this.textFragment.pushText(value, FontDecoration.NORMAL, Vector3fl.GREEN);
        this.textFragment.writeTextFragmentToSpriteBatch(spriteBatch, this.positionCursor, SpriteBatchHeight.HUD);
        this.positionCursor.y += Glyph.GLYPH_DIMENSIONS.y;
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

    public void writeDebugInformationToSpriteBatch(SpriteBatch spriteBatch) {
        this.writeLine(spriteBatch, "frames per second", String.format("%.2f", this.fps));
        this.writeLine(spriteBatch, "camera position", this.cameraPosition.toString());
        this.writeLine(spriteBatch, "camera normal", this.cameraNormal.toString());
        this.writeLine(spriteBatch, "game state", AbstractGameState.USED_GAME_STATE.getClass().getSimpleName());
        this.count += 1;
    }

}
