package doors.ui.component.edit;

import doors.graphics.spritebatch.SpriteBatch;
import doors.io.Keyboard;
import doors.level.block.AbstractBlock;
import doors.ui.component.IComponent;
import doors.ui.component.border.DialogBorderComponent;
import doors.ui.component.border.DialogState;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.component.layout.box.BoxComponent;
import doors.ui.component.layout.box.FixedDimension;
import doors.ui.component.BackgroundComponent;
import doors.ui.root.UIRoot;
import doors.utility.BoxedValue;
import doors.utility.vector.Vector2in;

public class EditQuickBarSlotComponent implements IComponent {

    private static Vector2in SLOT_DIMENSIONS = new Vector2in(32);
    private static int SLOT_PADDING = 4;

    private int keyCode;
    private BoxedValue<EditQuickBarSlotComponent> selectedSlot;

    private DialogBorderComponent borderComponent;
    private BackgroundComponent slotComponent;

    public AbstractBlock block;
    
    public EditQuickBarSlotComponent(int keyCode, BoxedValue<EditQuickBarSlotComponent> selectedSlot) {
        this.keyCode = keyCode;
        this.selectedSlot = selectedSlot;

        var spacerComponent = new BoxComponent(
            null,
            new FixedDimension(SLOT_DIMENSIONS.x),
            new FixedDimension(SLOT_DIMENSIONS.y)
        );

        this.slotComponent = new BackgroundComponent(spacerComponent, null);

        var paddingComponent = new PaddingComponent(this.slotComponent, SLOT_PADDING);
        this.borderComponent = new DialogBorderComponent(paddingComponent);
    }

    public void clear() {
        this.block = null;
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
        this.borderComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.borderComponent.calculatePosition(origin);
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
    public void update(UIRoot root) {
        this.borderComponent.update(root);

        var isDragged = root.draggedComponent instanceof EditBlockTableRowComponent;
        var isHovered = root.hoveredComponent == this;

        if(Keyboard.KEYBOARD.isKeyPressed(this.keyCode)) {
            this.selectedSlot.value = this;
        }

        if(isDragged && isHovered) {
            var draggedBlock = ((EditBlockTableRowComponent)root.draggedComponent).block;
            this.slotComponent.textureSample = draggedBlock.getIconTextureSample();
        } else if(this.block != null) {
            this.slotComponent.textureSample = this.block.getIconTextureSample();
        } else {
            this.slotComponent.textureSample = null;
        }

        this.borderComponent.state = this.selectedSlot.value == this
            ? DialogState.FOCUSED
            : DialogState.BLURRED;

        root.captureMouse(this, this.getPosition(), this.getDimensions(), 0);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.borderComponent.writeComponentToSpriteBatch(spriteBatch);
    }




}
