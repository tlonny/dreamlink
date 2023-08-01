package doors.ui.component.border;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.ui.border.DisabledButtonBorderSchema;
import doors.graphics.ui.border.NormalButtonBorderSchema;
import doors.graphics.ui.border.PressedButtonBorderSchema;
import doors.ui.component.IComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class ButtonBorderComponent implements IComponent {

    private static int PADDING_TOP = 1;
    private static int PADDING_LEFT = 1;
    private static int PADDING_BOTTOM = 2;
    private static int PADDING_RIGHT = 2;

    private static int PRESSED_PADDING_TOP = 2;
    private static int PRESSED_PADDING_LEFT = 2;
    private static int PRESSED_PADDING_BOTTOM = 1;
    private static int PRESSED_PADDING_RIGHT = 1;

    public ButtonState state;

    private PaddingComponent paddingComponent;

    public ButtonBorderComponent(IComponent content, ButtonState state) {
        this.paddingComponent = new PaddingComponent(content, PADDING_TOP, PADDING_BOTTOM, PADDING_LEFT, PADDING_RIGHT);
        this.state = state;
    }

    public ButtonBorderComponent(IComponent content) {
        this(content, ButtonState.NORMAL);
    }

    public void setContent(IComponent content) {
        this.paddingComponent.content = content;
    }

    @Override
    public Vector2in getDimensions() {
        return this.paddingComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.paddingComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        if(this.state == ButtonState.PRESSED) {
            this.paddingComponent.paddingTop = PRESSED_PADDING_TOP;
            this.paddingComponent.paddingBottom = PRESSED_PADDING_BOTTOM;
            this.paddingComponent.paddingLeft = PRESSED_PADDING_LEFT;
            this.paddingComponent.paddingRight = PRESSED_PADDING_RIGHT;
        } else {
            this.paddingComponent.paddingTop = PADDING_TOP;
            this.paddingComponent.paddingBottom = PADDING_BOTTOM;
            this.paddingComponent.paddingLeft = PADDING_LEFT;
            this.paddingComponent.paddingRight = PADDING_RIGHT;
        }
        this.paddingComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.paddingComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.paddingComponent.calculatePosition(origin);
    }

    @Override
    public void update(UIRoot root) {
        this.paddingComponent.update(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        if(this.state == ButtonState.NORMAL) {
            NormalButtonBorderSchema.SCHEMA.writeMenuTemplateToSpriteBatch(spriteBatch, this.getPosition(), this.getDimensions(), SpriteBatchHeight.UI_NORMAL);
        } else if(this.state == ButtonState.PRESSED) {
            PressedButtonBorderSchema.SCHEMA.writeMenuTemplateToSpriteBatch(spriteBatch, this.getPosition(), this.getDimensions(), SpriteBatchHeight.UI_NORMAL);
        } else {
            DisabledButtonBorderSchema.SCHEMA.writeMenuTemplateToSpriteBatch(spriteBatch, this.getPosition(), this.getDimensions(), SpriteBatchHeight.UI_NORMAL);
        }
        this.paddingComponent.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
