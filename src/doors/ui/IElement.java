package doors.ui;

import org.joml.Vector2i;

public interface IElement {

    public Vector2i getDimensions();

    public void render(Vector2i position);

}
