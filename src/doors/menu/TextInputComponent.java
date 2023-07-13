package doors.menu;

import org.lwjgl.glfw.GLFW;

import doors.graphics.texture.FontDecoration;
import doors.graphics.texture.FontTextureAtlas;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class TextInputComponent extends BaseMenuComponent {

    private static Vector2in PADDING = new Vector2in(3, 3);

    private int maxLength;
    private long blinkCursorOffset;
    private IAction0 onChange;
    public StringBuilder stringBuilder;

    public boolean isDisabled;

    public TextInputComponent(int maxLength, IAction0 onChange) {
        super();
        this.stringBuilder = new StringBuilder();
        this.maxLength = maxLength;
        this.onChange = onChange;
        this.componentLayer = ComponentLayer.INTERACTIVE;
    }

    @Override
    public Cursor getCursor() {
        return Cursor.CURSOR_POINTER;
    }

    public TextInputComponent(int maxLength) {
        this(maxLength, null);
    }

    private void resetBlinkCursorOffset() {
        this.blinkCursorOffset = System.currentTimeMillis();
    }

    private void onChange() {
        if(this.onChange != null) {
            this.onChange.invoke();
        }
    }

    private BoxSchema getBoxSchema() {
        if(this.isDisabled) {
            return BoxSchema.DIALOG_DISABLED;
        }
        if(this.isFocused) {
            return BoxSchema.DIALOG_FOCUS;
        }
        return BoxSchema.DIALOG_BLUR;
    }

    @Override
    public void calculateLayout() {
        this.dimensions.set(FontTextureAtlas.CHARACTER_DIMENSIONS);
        this.dimensions.x *= this.maxLength;
        this.dimensions.add(PADDING).add(PADDING);
    }

    @Override
    public void writeUIComponent() {
        this.getBoxSchema().writeBox(this.globalPosition, this.dimensions);

        var isBlinkCursorVisible = this.isFocused && (System.currentTimeMillis() - this.blinkCursorOffset) % 1000 < 500;
        var text = this.stringBuilder.toString() + (isBlinkCursorVisible ? "|" : "");

        var textPosition = new Vector2in(this.globalPosition).add(PADDING);
        FontTextureAtlas.FONT_TEXTURE_ATLAS.writeText(text, textPosition, FontDecoration.NORMAL, Vector3fl.BLACK);
    }

    @Override
    public void onCharacterTyped(char character) {
        if(this.isDisabled) {
            return;
        }
        if(this.stringBuilder.length() < this.maxLength) {
            this.stringBuilder.append(character);
            this.resetBlinkCursorOffset();
            this.onChange();
        }
    }

    @Override
    public void onFocus() {
        super.onFocus();
        this.resetBlinkCursorOffset();
    }

    @Override
    public void onKeyPressed(int keyCode) {
        if(this.isDisabled) {
            return;
        }

        if(keyCode == GLFW.GLFW_KEY_BACKSPACE) {
            if(this.stringBuilder.length() > 0) {
                this.stringBuilder.deleteCharAt(this.stringBuilder.length() - 1);
                this.resetBlinkCursorOffset();
                this.onChange();
            }
        }
    }
}
