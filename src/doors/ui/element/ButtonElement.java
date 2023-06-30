package doors.ui.element;

import doors.ui.BoxDesign;
import doors.ui.ClickController;
import doors.utility.Functional.IAction;
import doors.utility.geometry.Vector2in;

public class ButtonElement implements IUIElement {

    private static int BUTTON_PADDING = 2;

    private PaddingElement paddingChild;
    private ClickController clickController;

    private Vector2in position;

    public ButtonElement(IUIElement child, IAction onClick) {
        this.paddingChild = new PaddingElement(child, BUTTON_PADDING);
        this.clickController = new ClickController(this, onClick);
        this.position = new Vector2in();
    }

    public void setChild(IUIElement child) {
        this.paddingChild.child = child;
    }

    @Override
    public void calculateDimensions() {
        this.paddingChild.calculateDimensions();
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        // Because we're re-orienting the child, we need to have an explicit position
        // otherwise the parent is also re-oriented as the position is passed
        // directly from the child...
        this.position.set(origin);
        this.paddingChild.calculatePosition(origin);
    }

    @Override
    public void update() {
        this.paddingChild.update();
        this.clickController.update();

        if(this.clickController.isDown) {
            var newOrigin = new Vector2in(this.position).add(1,1);
            this.paddingChild.calculatePosition(newOrigin);
        }
    }

    @Override
    public void writeElement() {
        var boxDesign = this.clickController.isDown ? BoxDesign.BUTTON_PRESSED : BoxDesign.BUTTON;
        boxDesign.writeBox(this.position, this.getDimensions());
        this.paddingChild.writeElement();
    }

    @Override
    public Vector2in getDimensions() {
        return this.paddingChild.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.paddingChild.getPosition();
    }


}
