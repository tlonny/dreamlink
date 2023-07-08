package doors.ui;

import doors.ui.BoxDesign;
import doors.core.ui.IUIElement;
import doors.core.ui.PaddingElement;
import doors.core.ui.EventState;
import doors.core.utility.Functional.IAction;
import doors.core.utility.vector.Vector2in;

public class ButtonElement implements IUIElement {

    private static int BUTTON_PADDING = 2;

    private PaddingElement paddingChild;
    private EventState eventState;
    public IAction onClick;

    private Vector2in position;

    public ButtonElement(IUIElement child, IAction onClick) {
        this.paddingChild = new PaddingElement(child, BUTTON_PADDING);
        this.onClick = onClick;
        this.eventState = new EventState(this);
        this.position = new Vector2in();
    }

    private boolean isPressed() {
        return this.eventState.isFocused && this.eventState.isDown;
    }

    public void setChild(IUIElement child) {
        this.paddingChild.child = child;
    }

    @Override
    public void determineDimensions() {
        this.paddingChild.determineDimensions();
    }

    @Override
    public void determinePosition(Vector2in origin) {
        // Because we're re-orienting the child, we need to have an explicit position
        // otherwise the parent is also re-oriented as the position is passed
        // directly from the child...
        this.position.set(origin);
        this.paddingChild.determinePosition(origin);
    }

    @Override
    public void update() {
        this.paddingChild.update();
        this.eventState.update();

        if(this.isPressed()) {
            var newOrigin = new Vector2in(this.position).add(1,1);
            this.paddingChild.determinePosition(newOrigin);
        }

        if(this.eventState.isOnClick) {
            this.onClick.invoke();
        }
    }

    @Override
    public void writeElement() {
        var boxDesign = this.isPressed() ? BoxDesign.BUTTON_PRESSED : BoxDesign.BUTTON;
        boxDesign.writeBox(this.position, this.getDimensions());
        this.paddingChild.writeElement();
    }

    @Override
    public Vector2in getDimensions() {
        return this.paddingChild.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.position;
    }


}
