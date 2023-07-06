package doors.ui.element;

import org.lwjgl.glfw.GLFW;

import doors.core.io.Keyboard;
import doors.core.io.TypedCharacters;
import doors.core.ui.IUIElement;
import doors.core.ui.EventState;
import doors.Doors;
import doors.core.graphics.sprite.FontDecoration;
import doors.ui.BoxDesign;
import doors.core.utility.vector.Vector3fl;
import doors.graphics.sprite.StandardFont;
import doors.core.utility.vector.Vector2in;

public class TextInputElement implements IUIElement {

    private static Vector2in CHARACTER_PADDING = new Vector2in(3,2);

    public StringBuilder text;

    private int maxLength;
    private long pressTime;
    private Vector2in position;
    private Vector2in dimensions;
    private EventState eventState;

    public TextInputElement(int maxLength) {
        this.text = new StringBuilder();
        this.maxLength = maxLength;
        this.position = new Vector2in();
        this.dimensions = new Vector2in();
        this.eventState = new EventState(this);
        this.setMaxLength(maxLength);
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        this.dimensions.set(
            // We add 1 to the max length to account for the blinking cursor
            StandardFont.CHARACTER_DIMENSIONS.x * (maxLength + 1) + CHARACTER_PADDING.x * 2,
            StandardFont.CHARACTER_DIMENSIONS.y + CHARACTER_PADDING.y * 2
        );
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public Vector2in getPosition() {
        return this.position;
    }

    @Override
    public void determineDimensions() {

    }

    @Override
    public void determinePosition(Vector2in origin) {
        this.position.set(origin);
    }

    @Override
    public void update() {
        this.eventState.update();

        if(!this.eventState.isFocused) {
            return;
        }

        for(var character : TypedCharacters.TYPED_CHARACTERS.characters) {
            if(this.text.length() >= this.maxLength) {
                break;
            }

            this.pressTime = System.currentTimeMillis();
            this.text.append(character);
        }

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_BACKSPACE)) {
            if(this.text.length() > 0) {
                this.pressTime = System.currentTimeMillis();
                this.text.deleteCharAt(this.text.length() - 1);
            }
        }
    }

    @Override
    public void writeElement() {
        var cursor = new Vector2in(this.getPosition()).add(CHARACTER_PADDING);
        var boxDesign = this.eventState.isFocused ? BoxDesign.DIALOG : BoxDesign.BUTTON_PRESSED;
        boxDesign.writeBox(this.getPosition(), this.getDimensions());
        var isBlinkingCursor = (System.currentTimeMillis() - this.pressTime) % 1000 < 500 && this.eventState.isFocused;
        var toRender = this.text.toString() + (isBlinkingCursor ? "|" : "");
        StandardFont.STANDARD_FONT.writeText(
            Doors.TEXTURE_CHANNEL_FONT,
            toRender, 
            cursor, 
            FontDecoration.NORMAL, 
            Vector3fl.BLACK
        );
    }
}
