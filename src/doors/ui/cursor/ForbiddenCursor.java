package doors.ui.cursor;

import doors.core.graphics.sprite.SimpleSprite;
import doors.core.graphics.texture.TextureSample;
import doors.core.ui.Cursor;
import doors.graphics.texture.MenuTexture;
import doors.utility.vector.Vector2in;

public class ForbiddenCursor extends Cursor {

    private static TextureSample TEXTURE_SAMPLE = MenuTexture.MENU_TEXTURE.cursorPointer;
    private static Vector2in ORIGIN = new Vector2in(8, 8);

    public static ForbiddenCursor FORBIDDEN_CURSOR = new ForbiddenCursor();

    public ForbiddenCursor() {
        super(new SimpleSprite(TEXTURE_SAMPLE), ORIGIN);
    }
    
}
