package doors.ui.cursor;

import doors.core.graphics.sprite.SimpleSprite;
import doors.core.graphics.texture.TextureSample;
import doors.core.ui.Cursor;
import doors.graphics.texture.MenuTexture;
import doors.utility.vector.Vector2in;

public class ArrowCursor extends Cursor {

    private static TextureSample TEXTURE_SAMPLE = MenuTexture.MENU_TEXTURE.cursorArrow;
    private static Vector2in OFFSET = new Vector2in(3, 0);

    public static ArrowCursor ARROW_CURSOR = new ArrowCursor();

    public ArrowCursor() {
        super(new SimpleSprite(TEXTURE_SAMPLE), OFFSET);
    }
    
}
