package doors.core.ui;

import doors.core.io.Mouse;
import doors.core.utility.vector.Vector2in;

public interface IUIElement {

    public Vector2in getDimensions();

    public Vector2in getPosition();

    public void determineDimensions();

    public void determinePosition(Vector2in offset);

    public void update();

    public void writeElement();

    public default boolean isMouseOver() {
        return Mouse.MOUSE.position.isWithinBounds(this.getPosition(), this.getDimensions());
    }

}
