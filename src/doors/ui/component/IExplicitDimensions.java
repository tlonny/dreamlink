package doors.ui.component;

import doors.utility.vector.Vector2in;

public interface IExplicitDimensions extends IComponent {

    public void setDimensions(int width, int height);

    public default void setDimensions(Vector2in dimensions) {
        this.setDimensions(dimensions.x, dimensions.y);
    }

}
