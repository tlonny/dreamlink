package doors.ui.element;

import doors.utility.geometry.Vector2in;

public interface IUIElement {

    public Vector2in getDimensions();

    public Vector2in getPosition();

    public void calculateDimensions();

    public void calculatePosition(Vector2in origin);

    public void update();

    public void writeElement();

}
