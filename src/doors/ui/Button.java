package doors.ui;

import org.joml.Vector2i;
import org.joml.Vector3f;

import doors.io.Mouse;
import doors.utility.Color;
import doors.utility.Functional.IAction;

public class Button implements IComponent {

    public TextBlock text;
    public boolean underlined;
    public Vector3f color;
    private IAction action;

    public Button(String text, IAction action) {
        this.text = new TextBlock(text);
        this.action = action;
        this.text.color.set(Color.BUTTON_ACTIVE);
        this.text.underlined = true;
    }

    public void paint(Vector2i position) {
        var dims = this.getDimensions();
        if(Mouse.MOUSE.position.x >= position.x && Mouse.MOUSE.position.x < position.x + dims.x &&
           Mouse.MOUSE.position.y >= position.y && Mouse.MOUSE.position.y < position.y + dims.y) {
            this.text.color.set(Color.BUTTON_HOVER);
            if(Mouse.MOUSE.isLeftMouseButtonPressed()) {
                this.action.invoke();
            }
        } else {
            this.text.color.set(Color.BUTTON_ACTIVE);
        }
        this.text.paint(position);
    }

    @Override
    public Vector2i getDimensions() {
        return this.text.getDimensions();
    }

}
