package doors.ui.component.border;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.ui.border.BlurredDialogBorderSchema;
import doors.graphics.ui.border.DisabledDialogBorderSchema;
import doors.graphics.ui.border.FocusedDialogBorderSchema;
import doors.ui.component.IComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class DialogBorderComponent implements IComponent {

    private static int PADDING_TOP = 2;
    private static int PADDING_LEFT = 2;
    private static int PADDING_BOTTOM = 1;
    private static int PADDING_RIGHT = 1;

    public DialogState state;

    private PaddingComponent paddingComponent;
    private IComponent content;

    public DialogBorderComponent(IComponent content, DialogState state) {
        this.content = content;
        this.paddingComponent = new PaddingComponent(this.content, PADDING_TOP, PADDING_BOTTOM, PADDING_LEFT, PADDING_RIGHT);
        this.state = state;
    }

    public IComponent getContent() {
        return this.paddingComponent.content;
    }

    public void setContent(IComponent content) {
        this.paddingComponent.content = content;
    }

    public DialogBorderComponent(IComponent content) {
        this(content, DialogState.BLURRED);
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
        if(this.state == DialogState.DISABLED) {
            DisabledDialogBorderSchema.SCHEMA.writeMenuTemplateToSpriteBatch(spriteBatch, this.getPosition(), this.getDimensions(), SpriteBatchHeight.UI_NORMAL);
        } else if(this.state == DialogState.BLURRED) {
            BlurredDialogBorderSchema.SCHEMA.writeMenuTemplateToSpriteBatch(spriteBatch, this.getPosition(), this.getDimensions(), SpriteBatchHeight.UI_NORMAL);
        } else {
            FocusedDialogBorderSchema.SCHEMA.writeMenuTemplateToSpriteBatch(spriteBatch, this.getPosition(), this.getDimensions(), SpriteBatchHeight.UI_NORMAL);
        }
        this.paddingComponent.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
