package dreamlink.overlay.simulation.edit;

import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.list.ListRowData;
import dreamlink.overlay.component.list.drag.IListDragComponentProvider;
import dreamlink.overlay.component.list.drag.ListDragComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.simulation.stampinfo.StampInfoComponent;
import dreamlink.utility.maths.IntMaths;
import dreamlink.world.World;
import dreamlink.world.room.module.stamp.IStamp;

public class StampListComponent extends WrapperComponent {

    private class InternalListComponentProvider implements IListDragComponentProvider {

        @Override
        public int getRowCount() {
            return World.instance.getRoom().getStampSize();
        }


        @Override
        public void setRowData(int index, ListRowData data) {
            var stamp = World.instance.getRoom().getStampByIndex(index);
            data.text = stamp.getStampName();
            data.icon = stamp.getStampSprite();
        }


        @Override
        public Object getDragPayload(int index) {
            return World.instance.getRoom().getStampByIndex(index);
        }

    }

    private static final int itemSpacing = 5;

    private int hoveredIndex;
    private final IComponent component;

    public StampListComponent() {
        this.component = new VerticalSpanComponent(Alignment.start, StampListComponent.itemSpacing)
            .addComponent(
                new BoxComponent(
                    new ListDragComponent(new InternalListComponentProvider()),
                    BoxDimension.grow(), 
                    BoxDimension.grow(Alignment.start)
                )
            )
            .addComponent(
                new StampInfoComponent(this::getHoveredStamp)
            );
    }

    private IStamp getHoveredStamp() {
        var room = World.instance.getRoom();
        return IntMaths.inRange(room.getStampSize(), this.hoveredIndex)
            ? room.getStampByIndex(this.hoveredIndex)
            : null;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
