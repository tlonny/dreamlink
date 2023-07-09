package doors.core.ui;

import doors.core.io.Mouse;

public class ElementState {

    private IUIElement element;

    public boolean isOver;
    public boolean isDown;
    public boolean isFocused;

    public boolean isOnClick;
    public boolean isOnPress;
    public boolean isOnRelease;
    public boolean isOnFocus;
    public boolean isOnBlur;

    public ElementState(IUIElement element) {
        this.element = element;
    }

    public void update() {
        // Reset the single frame events
        this.isOnClick = false;
        this.isOnPress = false;
        this.isOnRelease = false;
        this.isOnFocus = false;
        this.isOnBlur = false;

        this.isOver = Mouse.MOUSE.position.isWithinBounds(
            this.element.getPosition(), 
            this.element.getDimensions()
        );

        this.isDown = Mouse.MOUSE.isLeftMouseButtonDown() && this.isOver;

        if(Mouse.MOUSE.isLeftMouseButtonPressed()) {
            this.isOnFocus = this.isOver && !this.isFocused;
            this.isOnBlur = !this.isOver && this.isFocused;
            this.isOnPress = this.isOver;
            this.isFocused = this.isOver;
        } else if(Mouse.MOUSE.isLeftMouseButtonReleased()) {
            this.isOnRelease = this.isOver;
            this.isOnClick = this.isOver && this.isFocused;
        }
    }

}
