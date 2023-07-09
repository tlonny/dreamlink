package doors.core.ui;

import doors.core.io.Mouse;

public class UIState {

    private IUIElement hoveredElement;

    public void update() {
        this.hoveredElement = null;
    }

    public boolean isHovered(IUIElement element) {
        if(this.hoveredElement == null) {
            if(Mouse.MOUSE.position.isWithinBounds(element.getPosition(), element.getDimensions())) {
                this.hoveredElement = element;
            }
        }
        return this.hoveredElement == element;
    }

}
