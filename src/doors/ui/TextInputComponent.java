package doors.ui;

import org.lwjgl.glfw.GLFW;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.sprite.FontDecoration;
import doors.core.ui.BaseUIComponent;
import doors.core.ui.UILayer;
import doors.core.utility.Functional.IAction1;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;

public class TextInputComponent extends BaseUIComponent {

    private static Vector2in PADDING = new Vector2in(3, 3);

    private int maxLength;
    private long blinkCursorOffset;
    private IAction1<String> onChange;
    public StringBuilder stringBuilder;

    public boolean isDisabled;

    public TextInputComponent(int maxLength, IAction1<String> onChange) {
        super();
        this.stringBuilder = new StringBuilder();
        this.maxLength = maxLength;
        this.onChange = onChange;
        this.layer = UILayer.INTERACTIVE;
    }

    public TextInputComponent(int maxLength) {
        this(maxLength, null);
    }

    private void resetBlinkCursorOffset() {
        this.blinkCursorOffset = System.currentTimeMillis();
    }

    private void onChange() {
        if(this.onChange != null) {
            this.onChange.invoke(this.stringBuilder.toString());
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
        this.dimensions.set(StandardFont.CHARACTER_DIMENSIONS);
        this.dimensions.x *= this.maxLength;
        this.dimensions.add(PADDING).add(PADDING);
    }

    @Override
    public void writeUIComponent(MeshBuffer meshBuffer) {
        this.getBoxSchema().writeBox(meshBuffer, this.globalPosition, this.dimensions);

        var isBlinkCursorVisible = this.isFocused && (System.currentTimeMillis() - this.blinkCursorOffset) % 1000 < 500;
        var text = this.stringBuilder.toString() + (isBlinkCursorVisible ? "|" : "");

        var textPosition = new Vector2in(this.globalPosition).add(PADDING);
        StandardFont.STANDARD_FONT.writeText(meshBuffer, text, textPosition, FontDecoration.NORMAL, Vector3fl.BLACK);
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
