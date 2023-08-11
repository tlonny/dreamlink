package doors.ui.cursor;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.utility.vector.Vector2in;

public class ArrowCursor extends AbstractCursor {

    private static Vector2in OFFSET = new Vector2in(3, 0);

    public static ArrowCursor ARROW_CURSOR = new ArrowCursor();

    public ArrowCursor() {
        super(MenuTextureSample.CURSOR_ARROW, OFFSET);
    }

}
