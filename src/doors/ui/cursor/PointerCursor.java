package doors.ui.cursor;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.utility.vector.Vector2in;

public class PointerCursor extends AbstractCursor {

    private static Vector2in OFFSET = new Vector2in(7, 0);
    
    public static PointerCursor POINTER_CURSOR = new PointerCursor();

    public PointerCursor() {
        super(MenuTextureSample.CURSOR_POINTER, OFFSET);
    }

}
