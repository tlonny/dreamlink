package doors.ui.component.textinput;

import org.lwjgl.glfw.GLFW;

import doors.graphics.text.Glyph;
import doors.graphics.text.TextFragment;
import doors.graphics.text.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacters;
import doors.ui.component.IComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class TextInputTextComponent implements IComponent {

    public boolean isDisabled;
    public int maxLength;

    private StringBuilder stringBuilder = new StringBuilder();
    private Vector2in positionCursor = new Vector2in();
    private Vector2in position = new Vector2in();
    private Vector2in dimensions = new Vector2in();
    private TextFragment textFragment = new TextFragment();
    private int cursorPosition;
    private boolean isFocused;

    public TextInputTextComponent(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getText() {
        return this.stringBuilder.toString();
    }

    public void setText(String text) {
        this.stringBuilder.setLength(0);
        this.stringBuilder.append(text);
        this.onChange();
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
        this.dimensions.set(Glyph.GLYPH_DIMENSIONS).mul(this.maxLength + 1, 1);
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {

    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.position.set(origin);
    }

    @Override
    public void onMousePress(UIRoot root) {
        this.positionCursor.set(Mouse.MOUSE.position).sub(this.position);
        this.cursorPosition = Math.min(
            this.positionCursor.x / Glyph.GLYPH_DIMENSIONS.x,
            this.stringBuilder.length()
        );
        root.focusedComponent = this;
        this.isFocused = true;
        this.onChange();
    }
    
    private void onChange() {
        this.textFragment.clear();
        var inputString = this.stringBuilder.toString();
        for(var ix = 0; ix <= inputString.length(); ix += 1) {
            var character = ix < inputString.length() ? inputString.charAt(ix) : ' ';
            if(this.cursorPosition == ix && this.isFocused) {
                this.textFragment.pushCharacter(
                    character,
                    FontDecoration.HIGHLIGHT,
                    Vector3fl.WHITE,
                    Vector3fl.BLACK
                );
            } else {
                this.textFragment.pushCharacter(
                    character,
                    FontDecoration.NORMAL,
                    Vector3fl.BLACK
                );

            }
        }
    }

    @Override
    public void update(UIRoot root) {
        this.isFocused = root.focusedComponent == this;

        if(!this.isFocused) {
            return;
        }

        for(var character : TypedCharacters.TYPED_CHARACTERS.characters) {
            if(this.stringBuilder.length() >= this.maxLength) {
                break;
            }

            this.stringBuilder.insert(this.cursorPosition, character);
            this.cursorPosition += 1;
            this.onChange();
        }

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_BACKSPACE)) {
            if(this.stringBuilder.length() > 0 && this.cursorPosition > 0) {
                this.stringBuilder.deleteCharAt(this.cursorPosition - 1);
                this.cursorPosition -= 1;
                this.onChange();
            }
        }

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_DELETE)) {
            if(this.stringBuilder.length() > 0 && this.cursorPosition < this.stringBuilder.length()) {
                this.stringBuilder.deleteCharAt(this.cursorPosition);
                this.onChange();
            }
        }

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_RIGHT)) {
            if(this.cursorPosition < this.stringBuilder.length()) {
                this.cursorPosition += 1;
                this.onChange();
            }
        }

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_LEFT)) {
            if(this.cursorPosition > 0) {
                this.cursorPosition -= 1;
                this.onChange();
            }
        }

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_HOME)) {
            this.cursorPosition = 0;
            this.onChange();
        }

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_END)) {
            this.cursorPosition = this.stringBuilder.length();
            this.onChange();
        }
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.textFragment.writeTextFragmentToSpriteBatch(
            spriteBatch, 
            this.position, 
            SpriteBatchHeight.UI_NORMAL
        );
    }
}
