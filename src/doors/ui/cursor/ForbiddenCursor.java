package doors.ui.cursor;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.utility.vector.Vector2in;

public class ForbiddenCursor extends AbstractCursor {

    private static Vector2in OFFSET = new Vector2in(8, 8);

    public static ForbiddenCursor FORBIDDEN_CURSOR = new ForbiddenCursor();

    public ForbiddenCursor() {
        super(MenuTextureSample.CURSOR_FORBIDDEN, OFFSET);
    }


    
}
