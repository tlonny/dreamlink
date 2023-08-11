package doors.ui.cursor;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.utility.vector.Vector2in;

public class GrabCursor extends AbstractCursor {

    private static Vector2in OFFSET = new Vector2in(8, 3);

    public static GrabCursor GRAB_CURSOR = new GrabCursor();

    public GrabCursor() {
        super(MenuTextureSample.CURSOR_GRAB, OFFSET);
    }


    
}
