package doors.ui.cursor;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.sample.TextureSample;
import doors.io.Mouse;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public abstract class AbstractCursor {

    private Vector2in position = new Vector2in();
    public Vector2in dimensions = new Vector2in();
    public Vector2in offset = new Vector2in();
    private TextureSample textureSample;

    public AbstractCursor(TextureSample textureSample, Vector2in offset) {
        this.textureSample = textureSample;
        this.dimensions.set(textureSample.dimensions);
        this.offset.set(offset);
    }


    public void writeCursor(SpriteBatch spriteBatch) {
        this.position.set(Mouse.MOUSE.position).sub(this.offset);
        spriteBatch.pushSprite(this.textureSample, this.position, this.dimensions, SpriteBatchHeight.UI_CURSOR, Vector3fl.WHITE);
    }

}
