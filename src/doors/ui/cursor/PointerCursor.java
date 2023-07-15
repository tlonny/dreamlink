package doors.ui.cursor;

import doors.core.graphics.sprite.SimpleSprite;
import doors.core.graphics.texture.TextureSample;
import doors.core.ui.Cursor;
import doors.graphics.texture.MenuTexture;
import doors.utility.vector.Vector2in;

public class PointerCursor extends Cursor {

    private static TextureSample TEXTURE_SAMPLE = MenuTexture.MENU_TEXTURE.cursorPointer;
    private static Vector2in OFFSET = new Vector2in(7, 0);

    public static PointerCursor POINTER_CURSOR = new PointerCursor();

    public PointerCursor() {
        super(new SimpleSprite(TEXTURE_SAMPLE), OFFSET);
    }
    
}
