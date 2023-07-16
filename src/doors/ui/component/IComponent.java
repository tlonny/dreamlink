package doors.ui.component;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public interface IComponent {

    public Vector2in getDimensions();

    public void calculateDimensions();

    public void update(Vector2in origin, UIRoot root);

    public void writeUIComponent(SpriteBatch spriteBatch);

    public default void onMousePress(UIRoot root) {

    }

    public default void onMouseRelease(UIRoot root) {

    }

    public default void onMouseClick(UIRoot root) {

    }

    public default void onDragStart(UIRoot root) {

    }

    public default void onDragDrop(UIRoot root, IComponent droppedComponent) {

    }

}
