package doors.menu;

import doors.io.Mouse;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;

public class ButtonComponent extends BaseMenuComponent { 

    private IAction0 onClick;
    public boolean isDisabled;
    private BaseMenuComponent child;

    public ButtonComponent(BaseMenuComponent child, Vector2in dimensions, IAction0 onClick) {
        super();
        this.onClick = onClick;
        this.dimensions.set(dimensions);
        this.child = this.addChild(child);
        this.layer = MenuLayer.INTERACTIVE;
    }

    private BoxSchema getBoxSchema() {
        if(this.isDisabled) {
            return BoxSchema.BUTTON_DISABLED;
        }

        if(this.isPressed()) {
            return BoxSchema.BUTTON_PRESSED;
        }

        return BoxSchema.BUTTON;
    }

    @Override
    public Cursor getCursor() {
        return this.isDisabled ? Cursor.CURSOR_FORBIDDEN : Cursor.CURSOR_POINTER;
    }

    @Override
    public void calculateLayout() {
        super.calculateLayout();
        this.child.centerH(this);
        this.child.centerV(this);
    }

    @Override
    public void writeUIComponent() {
        this.getBoxSchema().writeBox(globalPosition, this.dimensions);

        if(this.isPressed() && !this.isDisabled) {
            this.child.globalPosition.add(1, 1);
        }

        super.writeUIComponent();
    }

    private boolean isPressed() {
        return this.isFocused && this.isHovered && Mouse.MOUSE.isLeftMouseButtonDown();
    }

    @Override
    public void onClick() {
        if(this.isDisabled) {
            return;
        }

        this.onClick.invoke();
    }

}
