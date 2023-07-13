package doors.menu;

import doors.graphics.mesh.SpriteBatch;
import doors.graphics.texture.MenuTextureAtlas;
import doors.graphics.texture.TextureSample;
import doors.io.Mouse;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class Cursor {

    public static Cursor CURSOR_ARROW = new Cursor(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.cursorArrow,
        new Vector2in(3, 0)
    );

    public static Cursor CURSOR_POINTER = new Cursor(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.cursorPointer,
        new Vector2in(7, 0)
    );

    public static Cursor CURSOR_FORBIDDEN = new Cursor(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.cursorForbidden,
        new Vector2in(8 , 8)
    );

    public TextureSample textureSample;
    public Vector2in offset;
    private Vector2in adjustedPosition;

    public Cursor(TextureSample textureSample, Vector2in offset) {
        this.textureSample = textureSample;
        this.offset = offset;
        this.adjustedPosition = new Vector2in();
    }

    public void writeCursor() {
        this.adjustedPosition.set(Mouse.MOUSE.position).sub(this.offset);
        SpriteBatch.SPRITE_BATCH.writeSprite(
            this.textureSample,
            adjustedPosition,
            this.textureSample.dimensions,
            Vector3fl.WHITE
        );
    }

}
