package doors.ui.component;

import org.lwjgl.glfw.GLFW;

import doors.core.graphics.font.FontDecoration;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.io.Keyboard;
import doors.core.io.TypedCharacters;
import doors.core.ui.AbstractUIComponent;
import doors.core.ui.AbstractUIRoot;
import doors.core.ui.Cursor;
import doors.core.ui.UILayer;
import doors.graphics.font.Font;
import doors.graphics.sprite.box.DialogSprite;
import doors.graphics.sprite.box.DialogState;
import doors.ui.cursor.ForbiddenCursor;
import doors.ui.cursor.PointerCursor;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class TextInputComponent extends AbstractUIComponent {

    private static int PADDING = 3;

    public boolean isDisabled = false;

    private DialogSprite dialogSprite; 
    private int maxLength;
    private IAction0 onChange;
    private Vector2in dimensions;

    private StringBuilder stringBuilder = new StringBuilder();
    private Vector2in positionCursor = new Vector2in();

    public TextInputComponent(AbstractUIRoot root, int maxLength, IAction0 onChange) {
        super(root);
        this.maxLength = maxLength;
        this.onChange = onChange;

        this.dimensions = new Vector2in(Font.CHARACTER_DIMENSIONS)
            .mul(maxLength + 1, 1)
            .add(PADDING * 2);

        this.dialogSprite = new DialogSprite(this.dimensions);
    }

    public TextInputComponent(AbstractUIRoot root, int maxLength) {
        this(root, maxLength, null);
    }

    public void update() {
        super.update();

        if(this.isHovered) {
            this.getCursor().use();
        }

        if (this.isDisabled || !this.isFocused) {
            return;
        }

        var triggerOnChange = false;

        for(var character : TypedCharacters.TYPED_CHARACTERS.characters) {
            if(this.stringBuilder.length() < this.maxLength) {
                this.stringBuilder.append(character);
                triggerOnChange = true;
            }
        }

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_BACKSPACE)) {
            if(this.stringBuilder.length() > 0) {
                this.stringBuilder.deleteCharAt(this.stringBuilder.length() - 1);
                triggerOnChange = true;
            }
        }

        if(triggerOnChange && this.onChange != null) {
            this.onChange.invoke();
        }
    }

    private DialogState getDialogState() {
        if(this.isDisabled) {
            return DialogState.DISABLED;
        }

        if(this.isFocused) {
            return DialogState.FOCUSED;
        }

        return DialogState.BLURRED;
    }

    @Override
    public UILayer getLayer() {
        return UILayer.NORMAL;
    }

    public Cursor getCursor() {
        if(this.isDisabled) {
            return ForbiddenCursor.FORBIDDEN_CURSOR;
        }
        return PointerCursor.POINTER_CURSOR;
    }

    @Override
    public void writeUIComponent(MeshBuffer meshBuffer) {
        this.dialogSprite.dialogState = this.getDialogState();
        this.dialogSprite.writeSprite(meshBuffer, this.position);

        this.positionCursor.set(this.position).add(PADDING);

        var isBlinkingCursor = this.isFocused && (System.currentTimeMillis() / 500) % 2 == 0;
        var text = this.stringBuilder.toString() + (isBlinkingCursor ? "_" : "");
        Font.FONT.writeText(
            meshBuffer, 
            text, 
            this.positionCursor,
            FontDecoration.NORMAL,
            Vector3fl.BLACK
        );
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }
}
