package doors.ui.cursor;

import doors.graphics.texture.MenuTexture;
import doors.utility.vector.Vector2in;

public class ForbiddenCursor extends AbstractCursor {

    private static Vector2in OFFSET = new Vector2in(8, 8);

    public static ForbiddenCursor FORBIDDEN_CURSOR = new ForbiddenCursor();

    public ForbiddenCursor() {
        super(MenuTexture.MENU_TEXTURE.cursorForbidden, OFFSET);
    }


    
}
