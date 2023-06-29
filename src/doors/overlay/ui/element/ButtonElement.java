package doors.overlay.ui.element;

import doors.io.Mouse;
import doors.overlay.ui.BoxDesign;
import doors.utility.Functional.IAction;
import doors.utility.geometry.Vector2in;

public class ButtonElement implements IUIElement {

    private static int BUTTON_PADDING = 2;

    private PaddingElement paddingChild;
    public IAction onClick;

    private boolean isDown;
    private Vector2in position;

    public ButtonElement(IUIElement child, IAction onClick) {
        this.paddingChild = new PaddingElement(child, BUTTON_PADDING);
        this.onClick = onClick;
        this.position = new Vector2in();
    }

    public void setChild(IUIElement child) {
        this.paddingChild.child = child;
    }

    @Override
    public void setDimensions() {
        this.paddingChild.setDimensions();
    }

    @Override
    public void setPosition(Vector2in origin) {
        // Because we're re-orienting the child, we need to have an explicit position
        // otherwise the parent is also re-oriented as the position is passed
        // directly from the child...
        this.position.set(origin);
        this.paddingChild.setPosition(origin);
    }

    @Override
    public void update() {
        this.paddingChild.update();

        var position = this.getPosition();
        var dimensions = this.getDimensions();
        if(Mouse.MOUSE.position.isWithinBounds(position, dimensions)) {
            if(Mouse.MOUSE.isLeftMouseButtonPressed()) {
                this.isDown = true;
            } else if (this.isDown && Mouse.MOUSE.isLeftMouseButtonReleased()) {
                this.isDown = false;
                this.onClick.invoke();
            }
        } else if (this.isDown) {
            this.isDown = false;
        }

        if(this.isDown) {
            var newOrigin = new Vector2in(position).add(1, 1);
            this.paddingChild.setPosition(newOrigin);
        }
    }

    @Override
    public void writeElement() {
        var boxDesign = this.isDown ? BoxDesign.BUTTON_PRESSED : BoxDesign.BUTTON;
        boxDesign.writeBox(this.position, this.getDimensions());
        this.paddingChild.writeElement();
    }

    @Override
    public Vector2in getDimensions() {
        return this.paddingChild.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.paddingChild.getPosition();
    }


}
