package doors.ui;

import doors.io.Mouse;
import doors.ui.element.IUIElement;
import doors.utility.Functional.IAction;

public class ClickController {

    public IUIElement element;
    public IAction onClick;

    public boolean isPressed;
    public boolean isDown;
    
    public ClickController(IUIElement element, IAction onClick) {
        this.element = element;
        this.onClick = onClick;
    }

    public void update() {
        var mousePosition = Mouse.MOUSE.position;
        var position = this.element.getPosition();
        var dimensions = this.element.getDimensions();
        var withinBounds = mousePosition.isWithinBounds(position, dimensions);
        var isPressed = Mouse.MOUSE.isLeftMouseButtonPressed();
        var isReleased = Mouse.MOUSE.isLeftMouseButtonReleased();

        if(withinBounds && isPressed) {
            this.isPressed = true;
        } else if (withinBounds && isReleased) {
            this.isPressed = false;
            this.onClick.invoke();
        } else if (isReleased) {
            this.isPressed = false;
        }

        this.isDown = this.isPressed && withinBounds;

    }



}
