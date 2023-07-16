package doors.ui.component;

import org.lwjgl.glfw.GLFW;

import doors.graphics.font.Font;
import doors.graphics.font.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.template.menu.BlurredDialogTemplate;
import doors.graphics.template.menu.DisabledDialogTemplate;
import doors.graphics.template.menu.FocusedDialogTemplate;
import doors.io.Keyboard;
import doors.io.TypedCharacters;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class TextInputComponent implements IComponent {

    private static int PADDING = 3;

    public boolean isDisabled = false;
    public StringBuilder stringBuilder = new StringBuilder();
    public int maxLength;
    public IAction0 onChange;

    private Vector2in originCursor = new Vector2in();
    private Vector2in position = new Vector2in();
    private Vector2in dimensions = new Vector2in();
    private boolean isFocused;
    private boolean isHovered;
    private boolean isBlinkingCursor;

    public TextInputComponent(int maxLength, IAction0 onChange) {
        this.maxLength = maxLength;
        this.onChange = onChange;
    }

    public TextInputComponent(int maxLength) {
        this(maxLength, null);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        this.dimensions
            .set(Font.CHARACTER_DIMENSIONS)
            .mul(this.maxLength + 1, 1)
            .add(PADDING * 2);
    }

    @Override
    public void onMousePress(UIRoot root) {
        root.focusedComponent = this;
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
        this.isFocused = root.focusedComponent == this;
        this.isHovered = root.hoveredComponent == this;
        this.isBlinkingCursor = this.isFocused && (System.currentTimeMillis() / 500) % 2 == 0;

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

            this.stringBuilder.append(character);
            if(this.onChange != null) {
                this.onChange.invoke();
            }
        }

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_BACKSPACE)) {
            if(this.stringBuilder.length() > 0) {
                this.stringBuilder.deleteCharAt(this.stringBuilder.length() - 1);
                if(this.onChange != null) {
                    this.onChange.invoke();
                }
            }
        }

    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        if(this.isDisabled) {
            DisabledDialogTemplate.DISABLED_DIALOG_TEMPLATE.writeMenuTemplate(spriteBatch, this.position, this.dimensions, SpriteBatchHeight.UI_NORMAL);
        } else if(this.isFocused) {
            FocusedDialogTemplate.FOCUSED_DIALOG_TEMPLATE.writeMenuTemplate(spriteBatch, this.position, this.dimensions, SpriteBatchHeight.UI_NORMAL);
        } else {
            BlurredDialogTemplate.BLURRED_DIALOG_TEMPLATE.writeMenuTemplate(spriteBatch, this.position, this.dimensions, SpriteBatchHeight.UI_NORMAL);
        }

        var toRender = this.stringBuilder.toString() + (isBlinkingCursor ? "_" : "");
        this.originCursor.set(this.position).add(PADDING);
        Font.FONT.writeText(spriteBatch, toRender, this.originCursor, SpriteBatchHeight.UI_NORMAL, FontDecoration.NORMAL, Vector3fl.BLACK);
    }
}
