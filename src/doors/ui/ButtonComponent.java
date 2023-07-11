package doors.ui;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.io.Mouse;
import doors.core.ui.BaseUIComponent;
import doors.core.ui.UILayer;
import doors.core.utility.Functional.IAction0;
import doors.core.utility.vector.Vector2in;

public class ButtonComponent extends BaseUIComponent { 

    private IAction0 onClick;
    public boolean isDisabled;
    private BaseUIComponent child;

    public ButtonComponent(BaseUIComponent child, Vector2in dimensions, IAction0 onClick) {
        super();
        this.onClick = onClick;
        this.dimensions.set(dimensions);
        this.child = this.addChild(child);
        this.layer = UILayer.INTERACTIVE;
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
    public void calculateLayout() {
        super.calculateLayout();
        this.child.centerH(this);
        this.child.centerV(this);
    }

    @Override
    public void writeUIComponent(MeshBuffer meshBuffer) {
        this.getBoxSchema().writeBox(meshBuffer, globalPosition, this.dimensions);

        if(this.isPressed() && !this.isDisabled) {
            this.child.globalPosition.add(1, 1);
        }

        super.writeUIComponent(meshBuffer);
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
