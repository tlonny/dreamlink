package doors.ui.component.edit;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.template.menu.BlurredDialogTemplate;
import doors.graphics.template.menu.FocusedDialogTemplate;
import doors.io.Keyboard;
import doors.level.terrain.Block;
import doors.ui.component.IComponent;
import doors.ui.component.IconComponent;
import doors.ui.root.UIRoot;
import doors.utility.BoxedValue;
import doors.utility.vector.Vector2in;

public class EditQuickBarSlotComponent implements IComponent {

    private static Vector2in TOP_LEFT_PADDING = new Vector2in(2);
    private static Vector2in BOTTOM_RIGHT_PADDING = new Vector2in(1);
    private static Vector2in SLOT_DIMENSIONS = new Vector2in(32);
    private static Vector2in ICON_DIMENSIONS = new Vector2in(24);

    private Vector2in position = new Vector2in();
    private Vector2in originCursor = new Vector2in();
    private Vector2in dimensions = new Vector2in();
    private IconComponent iconComponent = new IconComponent(ICON_DIMENSIONS);
    private int keyCode;
    private boolean isSelected;
    private BoxedValue<EditQuickBarSlotComponent> selectedSlot;

    public Block block;
    
    public EditQuickBarSlotComponent(int keyCode, BoxedValue<EditQuickBarSlotComponent> selectedSlot) {
        this.keyCode = keyCode;
        this.selectedSlot = selectedSlot;
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void onDragDrop(UIRoot root, IComponent component) {
        if(!(component instanceof EditBlockTableRowComponent)) {
            return;
        }
        var droppedBlock = ((EditBlockTableRowComponent)component).block;
        this.block = droppedBlock;
    }

    @Override
    public void calculateDimensions() {
        this.iconComponent.calculateDimensions();
        this.dimensions
            .set(SLOT_DIMENSIONS)
            .add(TOP_LEFT_PADDING)
            .add(BOTTOM_RIGHT_PADDING);
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
        this.originCursor
            .set(SLOT_DIMENSIONS)
            .sub(ICON_DIMENSIONS)
            .div(2)
            .add(TOP_LEFT_PADDING)
            .add(this.position);

        this.iconComponent.update(this.originCursor, root);
        this.isSelected = this.selectedSlot.value == this;

        var isDragged = root.draggedComponent instanceof EditBlockTableRowComponent;
        var isHovered = root.hoveredComponent == this;

        if(Keyboard.KEYBOARD.isKeyPressed(this.keyCode)) {
            this.selectedSlot.value = this;
        }

        if(isDragged && isHovered) {
            var draggedBlock = ((EditBlockTableRowComponent)root.draggedComponent).block;
            this.iconComponent.textureSample = draggedBlock.textureSample;
        } else if(this.block != null) {
            this.iconComponent.textureSample = this.block.textureSample;
        } else {
            this.iconComponent.textureSample = null;
        }

        root.captureMouse(this, this.position, this.dimensions, 0);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        var template = this.isSelected
            ? FocusedDialogTemplate.FOCUSED_DIALOG_TEMPLATE
            : BlurredDialogTemplate.BLURRED_DIALOG_TEMPLATE;

        template.writeMenuTemplate(
            spriteBatch,
            this.position,
            this.dimensions,
            SpriteBatchHeight.UI_NORMAL
        );

        this.iconComponent.writeUIComponent(spriteBatch);
    }




}
