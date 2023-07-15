package doors.ui.component;


import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.sprite.ISprite;
import doors.core.io.Mouse;
import doors.core.ui.AbstractUIComponent;
import doors.core.ui.AbstractUIRoot;
import doors.core.ui.Cursor;
import doors.core.ui.UILayer;
import doors.graphics.sprite.box.ButtonSprite;
import doors.graphics.sprite.box.ButtonState;
import doors.ui.cursor.ForbiddenCursor;
import doors.ui.cursor.PointerCursor;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;

public class ButtonComponent extends AbstractUIComponent {


    private ButtonSprite buttonSprite;
    private ISprite content;
    private IAction0 onClick;
    private Vector2in dimensions;
    private Vector2in positionCursor = new Vector2in();

    public boolean isDisabled = false;

    public ButtonComponent(AbstractUIRoot root, Vector2in dimensions, ISprite content, IAction0 onClick) {
        super(root);
        this.dimensions = new Vector2in(dimensions);
        this.buttonSprite = new ButtonSprite(dimensions);

        this.content = content;
        this.onClick = onClick;
    }

    private ButtonState getButtonState() {
        if(this.isDisabled) {
            return ButtonState.DISABLED;
        }

        if(this.isHovered && this.isFocused && Mouse.MOUSE.isLeftMouseButtonDown()) {
            return ButtonState.PRESSED;
        }

        return ButtonState.NORMAL;
    }

    public void update() {
        super.update();

        if(this.isHovered) {
            this.getCursor().use();
        }

        if(this.isClicked && !this.isDisabled && this.onClick != null) {
            this.onClick.invoke();
        }
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
        this.buttonSprite.buttonState = this.getButtonState();
        this.buttonSprite.writeSprite(meshBuffer, this.position);

        this.positionCursor
            .set(this.dimensions)
            .sub(this.content.getDimensions())
            .div(2)
            .add(this.position);

        if(this.buttonSprite.buttonState == ButtonState.PRESSED) {
            this.positionCursor.add(1, 1);
        }
        
        this.content.writeSprite(meshBuffer, this.positionCursor);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }
    
}
