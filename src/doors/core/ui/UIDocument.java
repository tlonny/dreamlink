package doors.core.ui;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.io.Keyboard;
import doors.core.io.Mouse;
import doors.core.io.TypedCharacters;
import doors.core.utility.vector.Vector2in;

public class UIDocument {

    private BaseUIComponent currentHoveredComponent;
    private BaseUIComponent currentFocusedComponent;
    public BaseUIComponent rootComponent;

    public UIDocument(BaseUIComponent rootComponent) {
        this.currentHoveredComponent = null;
        this.currentFocusedComponent = null;
        this.rootComponent = rootComponent;
    }

    public void update(MeshBuffer meshBuffer) {
        this.rootComponent.calculateLayout();
        this.rootComponent.calculatePosition(Vector2in.ZERO);
        this.fireEvents(this.rootComponent.getHoveredElement());
        this.rootComponent.writeUIComponent(meshBuffer);
    }

    private void fireEvents(BaseUIComponent hoveredElement) {
        var previousHoveredComponent = this.currentHoveredComponent;
        var previousFocusedComponent = this.currentFocusedComponent;
        this.currentHoveredComponent = hoveredElement;

        if(this.currentHoveredComponent != previousHoveredComponent) {
            if(previousHoveredComponent != null) {
                previousHoveredComponent.onMouseExit();
            }
            if(this.currentHoveredComponent != null) {
                this.currentHoveredComponent.onMouseEnter();
            }
        }

        if(Mouse.MOUSE.isLeftMouseButtonPressed()) {
            this.currentFocusedComponent = this.currentHoveredComponent;
            if(this.currentFocusedComponent != null) {
                this.currentFocusedComponent.onPress();
            }
        }

        if(this.currentFocusedComponent != previousFocusedComponent) {
            if(previousFocusedComponent != null) {
                previousFocusedComponent.onBlur();
            }
            if(this.currentFocusedComponent != null) {
                this.currentFocusedComponent.onFocus();
            }
        }

        if(Mouse.MOUSE.isLeftMouseButtonReleased()) {
            if(this.currentFocusedComponent != null && this.currentFocusedComponent == this.currentHoveredComponent) {
                this.currentFocusedComponent.onClick();
            }
        }

        if(this.currentFocusedComponent == null) {
            return;
        }

        for(var character : TypedCharacters.TYPED_CHARACTERS.characters) {
            this.currentFocusedComponent.onCharacterTyped(character);
        }

        for(var keyCode : Keyboard.KEYBOARD.pressedKeys) {
            this.currentFocusedComponent.onKeyPressed(keyCode);
        }

    }

}
