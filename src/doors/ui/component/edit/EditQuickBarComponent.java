package doors.ui.component.edit;

import org.lwjgl.glfw.GLFW;

import doors.Config;
import doors.graphics.spritebatch.SpriteBatch;
import doors.level.block.AbstractBlock;
import doors.ui.component.IComponent;
import doors.ui.component.border.WindowBorderComponent;
import doors.ui.component.layout.RowComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.root.UIRoot;
import doors.utility.BoxedValue;
import doors.utility.vector.Vector2in;

public class EditQuickBarComponent implements IComponent {

    private static int[] KEY_CODES = new int[] {
        GLFW.GLFW_KEY_1,
        GLFW.GLFW_KEY_2,
        GLFW.GLFW_KEY_3,
        GLFW.GLFW_KEY_4,
        GLFW.GLFW_KEY_5,
        GLFW.GLFW_KEY_6,
        GLFW.GLFW_KEY_7,
        GLFW.GLFW_KEY_8,
        GLFW.GLFW_KEY_9,
        GLFW.GLFW_KEY_0
    };

    private static int SCREEN_PADDING = 10;
    private static int SPACING = 5;
    private Vector2in positionCursor = new Vector2in();

    private EditQuickBarSlotComponent[] slots = new EditQuickBarSlotComponent[10];
    private BoxedValue<EditQuickBarSlotComponent> selectedSlot = new BoxedValue<>();
    private WindowBorderComponent borderComponent;

    public EditQuickBarComponent() {
        var layoutComponent = new RowComponent(SPACING);
        for(var ix = 0; ix < 10; ix += 1) {
            var keyCode = KEY_CODES[ix];
            var slot = new EditQuickBarSlotComponent(keyCode, selectedSlot);
            this.slots[ix] = slot;
            layoutComponent.children.add(slot);
        }

        var paddingComponent = new PaddingComponent(layoutComponent, SPACING);
        this.borderComponent = new WindowBorderComponent(paddingComponent);
    }

    public AbstractBlock getSelectedBlock() {
        var slot = this.selectedSlot.value;
        if(slot == null) {
            return null;
        }
        return slot.block;
    }

    public void clear() {
        for(var slot : this.slots) {
            slot.clear();
        }
    }

    @Override
    public Vector2in getDimensions() {
        return this.borderComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.borderComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.borderComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.borderComponent.adjustDimensions(this.getDimensions());
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        var dimensions = this.getDimensions();
        this.positionCursor.x = (Config.CONFIG.getResolution().x - dimensions.x) / 2;
        this.positionCursor.y = Config.CONFIG.getResolution().y - dimensions.y - SCREEN_PADDING;
        this.borderComponent.calculatePosition(this.positionCursor);
    }

    @Override
    public void update(UIRoot root) {
        this.borderComponent.update(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.borderComponent.writeComponentToSpriteBatch(spriteBatch);
    }
}
