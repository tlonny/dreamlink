package doors.ui.component;


import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.border.ButtonBorderComponent;
import doors.ui.component.border.ButtonState;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;

public class ButtonComponent implements IComponent {

    public IAction0 onClick;
    public boolean isDisabled;

    private boolean isPressed;
    private ButtonBorderComponent borderComponent;

    public ButtonComponent(IComponent content, IAction0 onClick) {
        this.borderComponent = new ButtonBorderComponent(content);
        this.onClick = onClick;
    }

    public ButtonComponent(IComponent content) {
        this(content, null);
    }

    @Override
    public void onMouseClick(UIRoot root) {
        if(this.onClick != null && !this.isDisabled) {
            this.onClick.invoke();
        }
    }

    @Override
    public Vector2in getDimensions() {
        return this.borderComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.borderComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.borderComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.borderComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.borderComponent.calculatePosition(origin);
    }

    @Override
    public void update(UIRoot root) {
        this.borderComponent.update(root);
        this.isPressed = root.pressedComponent == this && root.hoveredComponent == this;

        if(root.hoveredComponent == this) {
            root.selectedCursor = PointerCursor.POINTER_CURSOR;
        }

        if(this.isDisabled) {
            this.borderComponent.setState(ButtonState.DISABLED);
        } else if(this.isPressed) {
            this.borderComponent.setState(ButtonState.PRESSED);
        } else {
            this.borderComponent.setState(ButtonState.NORMAL);
        }

        root.captureMouse(this, this.getPosition(), this.getDimensions(), 0);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.borderComponent.writeComponentToSpriteBatch(spriteBatch);
    }
}
