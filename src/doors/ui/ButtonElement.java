package doors.ui;

import org.joml.Vector2i;

import doors.io.Mouse;
import doors.utility.Color;
import doors.utility.Functional.IAction;

public class ButtonElement implements IElement {

    private static int BUTTON_PADDING = 4;

    private BorderElement borderElement;
    private PaddingElement paddingElement;
    private IAction action;

    public ButtonElement(IElement element, IAction action) {
        this.paddingElement = new PaddingElement(element, BUTTON_PADDING);
        this.borderElement = new BorderElement(paddingElement, Color.BUTTON_ACTIVE);
        this.action = action;
    }

    public ButtonElement(String text, IAction action) {
        this(new TextLineElement(text, false, Color.TEXT), action);
    }

    public void setElement(IElement element) {
        this.paddingElement.setElement(element);
    }

    public void setAction(IAction action) {
        this.action = action;
    }

    public void render(Vector2i position) {
        var dims = this.getDimensions();
        if(Mouse.MOUSE.position.x >= position.x && Mouse.MOUSE.position.x < position.x + dims.x &&
           Mouse.MOUSE.position.y >= position.y && Mouse.MOUSE.position.y < position.y + dims.y) {
            this.borderElement.setColor(Color.BUTTON_HOVER);
            if(Mouse.MOUSE.isLeftMouseButtonPressed()) {
                this.action.invoke();
            }
        } else {
            this.borderElement.setColor(Color.BUTTON_ACTIVE);
        }
        this.borderElement.render(position);
    }

    @Override
    public Vector2i getDimensions() {
        return this.borderElement.getDimensions();
    }

}
