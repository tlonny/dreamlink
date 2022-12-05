package dreamlink.overlay.home.explore;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.disk.LocalRoomState;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.IButtonComponentProvider;
import dreamlink.overlay.component.list.ListRowData;
import dreamlink.overlay.component.list.select.IListSelectComponentProvider;
import dreamlink.overlay.component.list.select.ListSelectComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.utility.maths.IntMaths;
import dreamlink.utility.maths.Vector4fMaths;

public class LocalRoomSelectComponent extends WrapperComponent {

    private class InternalRoomSelectProvider implements IListSelectComponentProvider {

        @Override
        public int getRowCount() {
            return LocalRoomState.instance.getSize();
        }

        @Override public int getSelectedRowIndex() {
            return LocalRoomSelectComponent.this.selectedIndex;
        }

        @Override
        public void setRowData(int index, ListRowData data) {
            data.text = LocalRoomState.instance.getRoomByIndex(index).roomName;
            data.icon = MenuTextureSample.iconFileStack;
        }

        @Override
        public void onChange(int index) {
            LocalRoomSelectComponent.this.selectedIndex = index;
        }

    }

    private class InternalExploreButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            LocalRoomSelectComponent.this.explore();
        }

        @Override
        public boolean isButtonDisabled() {
            var numRooms = LocalRoomState.instance.getSize();
            return !IntMaths.inRange(numRooms, LocalRoomSelectComponent.this.selectedIndex);
        }


    }

    private static final String exploreButtonText = "Explore";
    private static final String instructionsText = "Select Room:";
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);
    private static final int itemSpacing = 5;
    private static final int minHeight = 180;

    private final IComponent component;
    private final IRoomSelectProvider provider;
    private int selectedIndex = -1;

    public LocalRoomSelectComponent(IRoomSelectProvider provider) {
        this.provider = provider;
        this.component = new VerticalSpanComponent(
            Alignment.center,
            LocalRoomSelectComponent.itemSpacing
        ).addComponent(
            new BoxComponent(
                new TextLineLabelComponent(
                    LocalRoomSelectComponent.instructionsText,
                    FontDecoration.normal,
                    Vector4fMaths.black
                ),
                BoxDimension.grow(Alignment.start),
                BoxDimension.wrap()
            )
        ).addComponent(
            new BoxComponent(
                new ListSelectComponent(new InternalRoomSelectProvider()),
                BoxDimension.grow(),
                BoxDimension.min(LocalRoomSelectComponent.minHeight)
            )
        ).addComponent(
            new ButtonComponent(
                new InternalExploreButtonComponentProvider(),
                new BoxComponent(
                    new TextLineLabelComponent(
                        LocalRoomSelectComponent.exploreButtonText,
                        FontDecoration.normal,
                        Vector4fMaths.black
                    ),
                    BoxDimension.fixed(LocalRoomSelectComponent.buttonDimensions.x()),
                    BoxDimension.fixed(LocalRoomSelectComponent.buttonDimensions.y())
                )
            )
        );
    }

    private void explore() {
        var selectedRoom = LocalRoomState.instance.getRoomByIndex(this.selectedIndex);
        this.provider.onRoomSelect(selectedRoom.roomName);
    }

    public void clear() {
        this.selectedIndex = -1;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
