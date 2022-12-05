package dreamlink.overlay.simulation.quickbar;

import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.WindowBorderComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.simulation.stampinfo.StampInfoComponent;
import dreamlink.world.World;
import dreamlink.world.room.module.stamp.IStamp;

public class QuickBarRootComponent extends WrapperComponent {

    private static final int numQuickBarSlots = 10;
    private static final int spacing = 5;

    private final IComponent component;

    public QuickBarRootComponent() {
        var layoutComponent = new HorizontalSpanComponent(
            Alignment.start, 
            QuickBarRootComponent.spacing
        );

        for(var ix = 0; ix < QuickBarRootComponent.numQuickBarSlots; ix += 1) {
            var slot = new QuickBarSlotComponent(ix);
            layoutComponent.addComponent(slot);
        }

        this.component = new BoxComponent(
            new WindowBorderComponent(
                new BoxComponent(
                    new PaddingComponent(
                        new VerticalSpanComponent(Alignment.start, QuickBarRootComponent.spacing)
                            .addComponent(
                                new StampInfoComponent(this::getStamp)
                            )
                            .addComponent(layoutComponent),
                        spacing
                    ),
                    BoxDimension.wrap(),
                    BoxDimension.wrap()
                )
            ),
            BoxDimension.grow(),
            BoxDimension.grow(Alignment.end)
        );
    }

    private IStamp getStamp() {
        var room = World.instance.getRoom();
        var index = room.getSelectedPaletteStampSlotIndex();
        return room.getPaletteStamp(index);
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

}
