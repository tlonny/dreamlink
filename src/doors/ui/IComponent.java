package doors.ui;

import org.joml.Vector2i;

public interface IComponent {

    public Vector2i getDimensions();

    public void paint(Vector2i position);

}
