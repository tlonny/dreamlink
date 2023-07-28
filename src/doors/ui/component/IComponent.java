package doors.ui.component;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public interface IComponent {

    public Vector2in getDimensions();

    public Vector2in getPosition();

    public void calculateDimensions();
    
    public void adjustDimensions(Vector2in availableSpace);

    public void calculatePosition(Vector2in origin);

    public void update(UIRoot root);

    public default void onMousePress(UIRoot root) { }
 
    public default void onMouseRelease(UIRoot root) { }

    public default void onMouseClick(UIRoot root) { }

    public default void onDragStart(UIRoot root) { }

    public default void onDragDrop(UIRoot root, IComponent droppedComponent) { }

    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch);

}
