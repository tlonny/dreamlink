package doors.menu;

import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacters;
import doors.utility.vector.Vector2in;

public class MenuContext {

    private BaseMenuComponent hoveredComponent;
    private BaseMenuComponent focusedComponent;
    private BaseMenuComponent rootComponent;

    public MenuContext(BaseMenuComponent rootComponent) {
        this.hoveredComponent = null;
        this.focusedComponent = null;
        this.rootComponent = rootComponent;
    }

    public void update() {
        this.rootComponent.calculateLayout();
        this.rootComponent.calculatePosition(Vector2in.ZERO);
        this.fireEvents(this.rootComponent.getHoveredElement());
        this.rootComponent.writeUIComponent();
        var cursor = this.hoveredComponent != null ? this.hoveredComponent.getCursor() : Cursor.CURSOR_ARROW;
        cursor.writeCursor();
    }

    private void fireEvents(BaseMenuComponent hoveredElement) {
        var previousHoveredComponent = this.hoveredComponent;
        var previousFocusedComponent = this.focusedComponent;
        this.hoveredComponent = hoveredElement;

        if(this.hoveredComponent != previousHoveredComponent) {
            if(previousHoveredComponent != null) {
                previousHoveredComponent.onMouseExit();
            }
            if(this.hoveredComponent != null) {
                this.hoveredComponent.onMouseEnter();
            }
        }

        if(Mouse.MOUSE.isLeftMouseButtonPressed()) {
            this.focusedComponent = this.hoveredComponent;
            if(this.focusedComponent != null) {
                this.focusedComponent.onPress();
            }
        }

        if(this.focusedComponent != previousFocusedComponent) {
            if(previousFocusedComponent != null) {
                previousFocusedComponent.onBlur();
            }
            if(this.focusedComponent != null) {
                this.focusedComponent.onFocus();
            }
        }

        if(Mouse.MOUSE.isLeftMouseButtonReleased()) {
            if(this.focusedComponent != null && this.focusedComponent == this.hoveredComponent) {
                this.focusedComponent.onClick();
            }
        }

        if(this.focusedComponent == null) {
            return;
        }

        for(var character : TypedCharacters.TYPED_CHARACTERS.characters) {
            this.focusedComponent.onCharacterTyped(character);
        }

        for(var keyCode : Keyboard.KEYBOARD.pressedKeys) {
            this.focusedComponent.onKeyPressed(keyCode);
        }

    }

}
