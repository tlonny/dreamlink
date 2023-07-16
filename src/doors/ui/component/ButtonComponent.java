package doors.ui.component;


import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.template.menu.DisabledButtonTemplate;
import doors.graphics.template.menu.NormalButtonTemplate;
import doors.graphics.template.menu.PressedButtonTemplate;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;

public class ButtonComponent implements IComponent {

    public IAction0 onClick;
    public IComponent content;
    public boolean isDisabled = false;
    private boolean isPressed;

    private Vector2in dimensions;
    private Vector2in position = new Vector2in();
    private Vector2in originCursor = new Vector2in();

    public ButtonComponent(Vector2in dimensions, IComponent content, IAction0 onClick) {
        this.content = content;
        this.onClick = onClick;
        this.dimensions = new Vector2in(dimensions);
    }

    public ButtonComponent(Vector2in dimensions, IComponent content) {
        this(dimensions, content, null);
    }

    @Override
    public void onMouseClick(UIRoot root) {
        if(this.onClick != null) {
            this.onClick.invoke();
        }
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        this.content.calculateDimensions();
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
        this.isPressed = root.pressedComponent == this && root.hoveredComponent == this;

        if(root.hoveredComponent == this) {
            root.selectedCursor = PointerCursor.POINTER_CURSOR;
        }

        this.originCursor.set(this.dimensions).sub(this.content.getDimensions()).div(2).add(this.position);

        if(this.isPressed && !this.isDisabled) {
            this.originCursor.add(1);
        }

        this.content.update(this.originCursor, root);

        root.captureMouse(this, this.position, this.dimensions, 0);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        if(this.isDisabled) {
            DisabledButtonTemplate.DISABLED_BUTTON_TEMPLATE.writeMenuTemplate(spriteBatch, this.position, this.dimensions, SpriteBatchHeight.UI_NORMAL);
        } else if(this.isPressed) {
            PressedButtonTemplate.PRESSED_BUTTON_TEMPLATE.writeMenuTemplate(spriteBatch, this.position, this.dimensions, SpriteBatchHeight.UI_NORMAL);
            this.originCursor.add(1);
        } else {
            NormalButtonTemplate.NORMAL_BUTTON_TEMPLATE.writeMenuTemplate(spriteBatch, this.position, this.dimensions, SpriteBatchHeight.UI_NORMAL);
        }
        this.content.writeUIComponent(spriteBatch);
    }
}
