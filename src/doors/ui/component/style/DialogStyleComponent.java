package doors.ui.component.style;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.template.menu.BlurredDialogTemplate;
import doors.graphics.template.menu.DisabledDialogTemplate;
import doors.graphics.template.menu.FocusedDialogTemplate;
import doors.ui.component.IComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class DialogStyleComponent implements IComponent {

    public DialogState state;
    public IComponent content;
    private Vector2in position = new Vector2in();

    public DialogStyleComponent(IComponent content, DialogState state) {
        this.content = content;
        this.state = state;
    }

    public DialogStyleComponent(IComponent content) {
        this(content, DialogState.BLURRED);
    }

    @Override
    public Vector2in getDimensions() {
        return this.content.getDimensions();
    }

    @Override
    public void calculateDimensions() {
        this.content.calculateDimensions();
    }
    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
        this.content.update(origin, root);
    }
    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        if(this.state == DialogState.DISABLED) {
            DisabledDialogTemplate.DISABLED_DIALOG_TEMPLATE.writeMenuTemplateToSpriteBatch(spriteBatch, this.position, this.getDimensions(), SpriteBatchHeight.UI_NORMAL);
        } else if(this.state == DialogState.BLURRED) {
            BlurredDialogTemplate.BLURRED_DIALOG_TEMPLATE.writeMenuTemplateToSpriteBatch(spriteBatch, this.position, this.getDimensions(), SpriteBatchHeight.UI_NORMAL);
        } else {
            FocusedDialogTemplate.FOCUSED_DIALOG_TEMPLATE.writeMenuTemplateToSpriteBatch(spriteBatch, this.position, this.getDimensions(), SpriteBatchHeight.UI_NORMAL);
        }
        this.content.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
