package doors.ui.root;

import doors.ui.component.IComponent;
import doors.utility.vector.Vector2in;

public class ComponentCapture {

    public IComponent component = null;
    public Vector2in position = new Vector2in();
    public Vector2in dimensions = new Vector2in();
    public int capturePriority = 0;
}
