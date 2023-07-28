package doors.ui.component;

import org.lwjgl.glfw.GLFW;

import doors.graphics.text.Glyph;
import doors.graphics.text.TextFragment;
import doors.graphics.text.FontDecoration;
import doors.Config;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.template.menu.BlurredDialogTemplate;
import doors.graphics.template.menu.DisabledDialogTemplate;
import doors.graphics.template.menu.FocusedDialogTemplate;
import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.TypedCharacters;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class TextInputComponent implements IComponent {

    private static int PADDING = 3;

    public boolean isDisabled;
    public int maxLength;
    public IAction0 onChange;

    private StringBuilder stringBuilder = new StringBuilder();
    private Vector2in positionCursor = new Vector2in();
    private Vector2in position = new Vector2in();
    private Vector2in dimensions = new Vector2in();
    private TextFragment textFragment = new TextFragment();
    private int cursorPosition;

    private boolean isFocused;
    private boolean isHovered;

    public TextInputComponent(int maxLength, IAction0 onChange) {
        this.maxLength = maxLength;
        this.onChange = onChange;
    }

    public TextInputComponent(int maxLength) {
        this(maxLength, null);
    }

    public String getText() {
        return this.stringBuilder.toString();
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        this.dimensions
            .set(Glyph.GLYPH_DIMENSIONS)
            .mul(this.maxLength + 1, 1)
            .add(PADDING * 2);
    }

    @Override
    public void onMousePress(UIRoot root) {
        this.positionCursor.set(Mouse.MOUSE.position).sub(this.position).sub(PADDING);
        this.cursorPosition = Math.min(
            this.positionCursor.x / Glyph.GLYPH_DIMENSIONS.x,
            this.stringBuilder.length()
        );
        root.focusedComponent = this;
    }
    
    private void onChange() {
        if(this.onChange != null) {
            this.onChange.invoke();
        }

    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
        this.isFocused = root.focusedComponent == this;
        this.isHovered = root.hoveredComponent == this;

        if(this.isHovered) {
            root.selectedCursor = PointerCursor.POINTER_CURSOR;
        }

        root.captureMouse(this, this.position, this.dimensions, 0);

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
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        if(this.isDisabled) {
            DisabledDialogTemplate.DISABLED_DIALOG_TEMPLATE.writeMenuTemplateToSpriteBatch(spriteBatch, this.position, this.dimensions, SpriteBatchHeight.UI_NORMAL);
        } else if(this.isFocused) {
            FocusedDialogTemplate.FOCUSED_DIALOG_TEMPLATE.writeMenuTemplateToSpriteBatch(spriteBatch, this.position, this.dimensions, SpriteBatchHeight.UI_NORMAL);
        } else {
            BlurredDialogTemplate.BLURRED_DIALOG_TEMPLATE.writeMenuTemplateToSpriteBatch(spriteBatch, this.position, this.dimensions, SpriteBatchHeight.UI_NORMAL);
        }

        this.positionCursor.set(this.position).add(PADDING);
        this.textFragment.writeTextFragmentToSpriteBatch(
            spriteBatch, 
            this.positionCursor, 
            SpriteBatchHeight.UI_NORMAL
        );
    }
}
