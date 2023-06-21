package doors.overlay.ui.element;

import doors.utility.geometry.Vector2in;

public interface IUIElement {

    public Vector2in getDimensions();

    public Vector2in getPosition();

    public void rebuild();

    public void orient(Vector2in origin);

    public void update();

    public void writeElement();

}
