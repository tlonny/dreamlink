package doors.ui.component.textinput;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.component.IComponent;
import doors.ui.component.border.DialogBorderComponent;
import doors.ui.component.border.DialogState;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;

public class TextInputComponent implements IComponent {

    private static int PADDING = 2;

    private TextInputTextComponent textInputComponent;
    private PaddingComponent paddingComponent;
    private DialogBorderComponent dialogBorderComponent;

    public TextInputComponent(int maxLength, IAction0 onChange) {
        this.textInputComponent = new TextInputTextComponent(maxLength, onChange);
        this.paddingComponent = new PaddingComponent(textInputComponent, PADDING);
        this.dialogBorderComponent = new DialogBorderComponent(paddingComponent);
    }

    public TextInputComponent(int maxLength) {
        this(maxLength, null);
    }

    public String getText() {
        return this.textInputComponent.getText();
    }

    public void setDisabled(boolean isDisabled) {
        this.textInputComponent.isDisabled = isDisabled;
    }

    @Override
    public Vector2in getDimensions() {
        return this.dialogBorderComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.dialogBorderComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.dialogBorderComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.dialogBorderComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.dialogBorderComponent.calculatePosition(origin);
    }
    
    @Override
    public void update(UIRoot root) {
        this.dialogBorderComponent.update(root);

        if(this.textInputComponent.isDisabled) {
            this.dialogBorderComponent.state = DialogState.DISABLED;
        } else if(root.focusedComponent == this.textInputComponent) {
            this.dialogBorderComponent.state = DialogState.FOCUSED;
        } else {
            this.dialogBorderComponent.state = DialogState.BLURRED;
        }

        if(root.hoveredComponent == this) {
            root.selectedCursor = PointerCursor.POINTER_CURSOR;
        }
        
        root.captureMouse(this, this.getPosition(), this.getDimensions(), 0);
    }

    @Override
    public void onMousePress(UIRoot root) {
        this.textInputComponent.onMousePress(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.dialogBorderComponent.writeComponentToSpriteBatch(spriteBatch);
    }
}
