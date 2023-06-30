package doors.ui.element;

import org.lwjgl.glfw.GLFW;

import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacters;
import doors.graphics.sprite.SpriteBatch;
import doors.graphics.sprite.font.FontTextureAtlas;
import doors.ui.BoxDesign;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;

public class TextInputElement implements IUIElement {

    private static Vector2in CHARACTER_PADDING = new Vector2in(3,2);

    private StringBuilder text;

    private int maxLength;
    private long pressTime;

    private boolean isFocus;
    private Vector2in position;
    private Vector2in dimensions;

    public TextInputElement(int maxLength) {
        this.text = new StringBuilder();
        this.maxLength = maxLength;
        this.position = new Vector2in();
        this.dimensions = new Vector2in();
        this.setMaxLength(maxLength);
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        this.dimensions.set(
            // We add 1 to the max length to account for the blinking cursor
            FontTextureAtlas.CHARACTER_DIMENSIONS.x * (maxLength + 1) + CHARACTER_PADDING.x * 2,
            FontTextureAtlas.CHARACTER_DIMENSIONS.y + CHARACTER_PADDING.y * 2
        );
    }

    public String getText() {
        return this.text.toString();
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
    public void calculateDimensions() {

    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.position.set(origin);
    }

    @Override
    public void update() {
        var mousePosition = Mouse.MOUSE.position;
        var position = this.getPosition();
        var dimensions = this.getDimensions();
        if(Mouse.MOUSE.isLeftMouseButtonPressed()) {
            this.isFocus = mousePosition.isWithinBounds(position, dimensions);
            this.pressTime = System.currentTimeMillis();
        }

        if(this.isFocus) {
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

    }

    @Override
    public void writeElement() {
        var cursor = new Vector2in(this.getPosition()).add(CHARACTER_PADDING);
        BoxDesign.DIALOG.writeBox(this.getPosition(), this.getDimensions());
        var isBlinkingCursor = (System.currentTimeMillis() - this.pressTime) % 1000 < 500 && this.isFocus;
        var toRender = this.getText() + (isBlinkingCursor ? "|" : "");
        SpriteBatch.SPRITE_BATCH.writeText(toRender, cursor, false, Vector3fl.ZERO);
    }
}
