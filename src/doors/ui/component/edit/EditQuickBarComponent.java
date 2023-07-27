package doors.ui.component.edit;

import org.lwjgl.glfw.GLFW;

import doors.Config;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.template.menu.WindowTemplate;
import doors.level.block.Block;
import doors.ui.component.IComponent;
import doors.ui.component.layout.HorizontalSpanComponent;
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
    private Vector2in position = new Vector2in();

    private BoxedValue<EditQuickBarSlotComponent> selectedSlot = new BoxedValue<>();
    private EditQuickBarSlotComponent[] slots = new EditQuickBarSlotComponent[10];
    private HorizontalSpanComponent layoutComponent = new HorizontalSpanComponent(SPACING);
    private PaddingComponent paddingComponent = new PaddingComponent(this.layoutComponent, SPACING);

    public EditQuickBarComponent() {
        for(var ix = 0; ix < 10; ix += 1) {
            var keyCode = KEY_CODES[ix];
            var slot = new EditQuickBarSlotComponent(keyCode, selectedSlot);
            this.slots[ix] = slot;
            this.layoutComponent.components.add(slot);
        }
    }

    public Block getSelectedBlock() {
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
        return this.paddingComponent.getDimensions();
    }

    @Override
    public void calculateDimensions() {
        this.paddingComponent.calculateDimensions();
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        var dimensions = this.getDimensions();
        this.position.x = (Config.RESOLUTION.x - dimensions.x) / 2;
        this.position.y = Config.RESOLUTION.y - dimensions.y - SCREEN_PADDING;
        this.paddingComponent.update(this.position, root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        WindowTemplate.WINDOW_TEMPLATE.writeMenuTemplateToSpriteBatch(
            spriteBatch,
            this.position,
            this.getDimensions(),
            SpriteBatchHeight.UI_NORMAL
        );
        this.paddingComponent.writeComponentToSpriteBatch(spriteBatch);
    }

    
}
