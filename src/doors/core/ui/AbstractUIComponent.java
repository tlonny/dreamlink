package doors.core.ui;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.io.Mouse;
import doors.utility.vector.Vector2in;

public abstract class AbstractUIComponent {

    private AbstractUIRoot root;

    public boolean isHovered = false;
    public boolean isFocused = false;
    public boolean isPressed = false;
    public boolean isClicked = false;

    public Vector2in offset = new Vector2in();
    public Vector2in position = new Vector2in();

    public AbstractUIComponent(AbstractUIRoot root) {
        this.root = root;
        this.root.addComponent(this);
    }

    public abstract Vector2in getDimensions();

    public abstract UILayer getLayer();

    public void update() {
        this.isHovered = this.root.hoveredComponent == this; 
        if(Mouse.MOUSE.isLeftMouseButtonPressed()) {
            this.isFocused = this.isHovered;
            this.isPressed = this.isFocused;
        } else {
            this.isPressed = false;
        }

        if(Mouse.MOUSE.isLeftMouseButtonReleased()) {
            this.isClicked = this.isFocused && this.isHovered;
        } else {
            this.isClicked = false;
        }
    }

    public abstract void writeUIComponent(MeshBuffer meshBuffer);
    
}
