package doors.core.ui;

import doors.core.io.Mouse;

public class EventState {

    private IUIElement element;

    public boolean isMouseOver;
    public boolean isMouseClickStarted;
    public boolean isMouseClicked;
    public boolean isFocused;

    public EventState(IUIElement element) {
        this.element = element;
    }

    public void update() {
        this.isMouseOver = Mouse.MOUSE.position.isWithinBounds(
            this.element.getPosition(), 
            this.element.getDimensions()
        );

        if(Mouse.MOUSE.isLeftMouseButtonPressed()) {
            if(this.isMouseOver) {
                this.isFocused = true;
                this.isMouseClickStarted = true;
            } else {
                this.isFocused = false;
            }
        }

        if(Mouse.MOUSE.isLeftMouseButtonReleased()) {
            if(this.isMouseOver) {
                this.isMouseClicked = true;
            }
            this.isMouseClickStarted = false;
        } else {
            this.isMouseClicked = false;
        }
    }

}
