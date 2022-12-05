package dreamlink.overlay.simulation.quickbar;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.dialog.DialogBorderComponent;
import dreamlink.overlay.component.border.dialog.DialogState;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.icon.IconComponent;
import dreamlink.overlay.eventspan.IEvent;
import dreamlink.overlay.eventspan.IEventSpan;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.overlay.eventspan.event.DropEvent;
import dreamlink.overlay.eventspan.event.PressStartEvent;
import dreamlink.world.World;
import dreamlink.world.room.module.stamp.IStamp;

public class QuickBarSlotComponent extends WrapperComponent implements IEventSpan {

    private static final Vector2i slotDimensions = new Vector2i(32);
    private static final int padding = 2;

    private final int slotID;
    private final IComponent component;
    private final Vector2i position = new Vector2i();
    private SpriteHeight spriteHeight;

    public QuickBarSlotComponent(int slotID) {
        this.slotID = slotID;
        this.component = new BoxComponent(
            new DialogBorderComponent(
                this::getDialogState,
                new PaddingComponent(
                    new IconComponent(
                        this::getSprite,
                        QuickBarSlotComponent.slotDimensions
                    ),
                    QuickBarSlotComponent.padding
                )
            ),
            BoxDimension.grow(),
            BoxDimension.grow(Alignment.end)
        );
    }

    private DialogState getDialogState() {
        var room = World.instance.getRoom();
        var selectedSlotID = room.getSelectedPaletteStampSlotIndex();
        return this.slotID == selectedSlotID
            ? DialogState.focused
            : DialogState.blurred;
    }

    private ISpriteTemplate getSprite() {
        var room = World.instance.getRoom();
        var stamp = room.getPaletteStamp(this.slotID);
        return stamp != null ? stamp.getStampSprite() : null;
    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight spriteHeight) {
        this.spriteHeight = spriteHeight;
        this.position.set(position);
        super.computePosition(position, spriteHeight);
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    @Override
    public Vector2i getPosition(Vector2i target) {
        return target.set(this.position);
    }

    @Override
    public SpriteHeight getSpriteHeight() {
        return this.spriteHeight;
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        registry.register(this);
    }

    @Override
    public void onEvent(IEvent event) {
        var room = World.instance.getRoom();
        if(event instanceof PressStartEvent) {
            room.setSelectedPaletteStampSlotIndex(this.slotID);
        }

        if(event instanceof DropEvent dropEvent) {
            if(dropEvent.droppedPayload instanceof IStamp stamp) {
                room.setPaletteStamp(this.slotID, stamp);
            }
        }
    }

    @Override
    public int getDragDistanceThreshold() {
        return Integer.MAX_VALUE;
    }

}