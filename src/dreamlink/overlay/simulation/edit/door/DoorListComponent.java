package dreamlink.overlay.simulation.edit.door;

import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.IButtonComponentProvider;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.list.ListRowData;
import dreamlink.overlay.component.list.select.IListSelectComponentProvider;
import dreamlink.overlay.component.list.select.ListSelectComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.player.Player;
import dreamlink.utility.maths.IntMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.world.World;

public class DoorListComponent extends WrapperComponent {

    private class InternalEditDoorButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            var door = World.instance.getRoom().getDoorByIndex(DoorListComponent.this.selectedIndex);
            DoorListComponent.this.provider.gotoEdit(door);
        }

        @Override
        public boolean isButtonDisabled() {
            return !DoorListComponent.this.isValidDoor;
        }

    }

    private class InternalGotoDoorButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            var door = World.instance.getRoom().getDoorByIndex(DoorListComponent.this.selectedIndex);
            Player.instance.spawn(door.name);
        }

        @Override
        public boolean isButtonDisabled() {
            if(!DoorListComponent.this.isValidDoor) {
                return true;
            }

            var door = World.instance.getRoom().getDoorByIndex(DoorListComponent.this.selectedIndex);
            return door == null || !door.isPlaced();
        }

    }

    private class InternalDeleteDoorButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            var door = World.instance.getRoom().getDoorByIndex(DoorListComponent.this.selectedIndex);
            World.instance.getRoom().removeDoor(door);
            DoorListComponent.this.updateIsValidDoor();
        }

        @Override
        public boolean isButtonDisabled() {
            if(!DoorListComponent.this.isValidDoor) {
                return true;
            }

            var door = World.instance.getRoom().getDoorByIndex(DoorListComponent.this.selectedIndex);
            return door == null || door.isPlaced();
        }

    }

    private class InternalListComponentProvider implements IListSelectComponentProvider {

        @Override
        public int getRowCount() {
            return World.instance.getRoom().getDoorSize();
        }

        @Override
        public int getSelectedRowIndex() {
            return DoorListComponent.this.selectedIndex;
        }

        @Override
        public void setRowData(int index, ListRowData data) {
            var door = World.instance.getRoom().getDoorByIndex(index);
            data.text = door.name;
            data.icon = door.getStampSprite();
        }

        @Override
        public void onChange(int index) {
            DoorListComponent.this.selectedIndex = index;
            DoorListComponent.this.updateIsValidDoor();
        }
    }

    private static final int itemSpacing = 5;
    private static final int buttonHeight = 24;
    private static final String addDoorButtonText = "Add Door";
    private static final String editDoorButtonText = "Edit Door";
    private static final String deleteDoorButtonText = "Delete Door";
    private static final String gotoDoorButtonText = "Goto Door";

    private final IComponent component;
    private final IDoorComponentProvider provider;

    private int selectedIndex = -1;
    private boolean isValidDoor;

    public DoorListComponent(IDoorComponentProvider provider) {
        this.provider = provider;
        this.component = new VerticalSpanComponent(Alignment.start, DoorListComponent.itemSpacing)
            .addComponent(
                new BoxComponent(
                    new ListSelectComponent(new InternalListComponentProvider()),
                    BoxDimension.grow(),
                    BoxDimension.grow(Alignment.start)
                )
            )
            .addComponent(
                new HorizontalSpanComponent(
                    Alignment.center,
                    DoorListComponent.itemSpacing
                ).addComponent(
                    new ButtonComponent(
                        new StaticButtonComponentProvider(this.provider::gotoCreate),
                        new BoxComponent(
                            new TextLineLabelComponent(DoorListComponent.addDoorButtonText, FontDecoration.normal, Vector4fMaths.black),
                            BoxDimension.grow(Alignment.center),
                            BoxDimension.fixed(DoorListComponent.buttonHeight)
                        )
                    )
                ).addComponent(
                    new ButtonComponent(
                        new InternalEditDoorButtonComponentProvider(),
                        new BoxComponent(
                            new TextLineLabelComponent(DoorListComponent.editDoorButtonText, FontDecoration.normal, Vector4fMaths.black),
                            BoxDimension.grow(Alignment.center),
                            BoxDimension.fixed(DoorListComponent.buttonHeight)
                        )
                    )
                ).addComponent(
                    new ButtonComponent(
                        new InternalDeleteDoorButtonComponentProvider(),
                        new BoxComponent(
                            new TextLineLabelComponent(DoorListComponent.deleteDoorButtonText, FontDecoration.normal, Vector4fMaths.black),
                            BoxDimension.grow(Alignment.center),
                            BoxDimension.fixed(DoorListComponent.buttonHeight)
                        )
                    )
                ).addComponent(
                    new ButtonComponent(
                        new InternalGotoDoorButtonComponentProvider(),
                        new BoxComponent(
                            new TextLineLabelComponent(DoorListComponent.gotoDoorButtonText, FontDecoration.normal, Vector4fMaths.black),
                            BoxDimension.grow(Alignment.center),
                            BoxDimension.fixed(DoorListComponent.buttonHeight)
                        )
                    )
                )
            );
    }

    public void clear() {
        this.selectedIndex = -1;
        this.isValidDoor = false;
    }

    public void updateIsValidDoor() {
        var room = World.instance.getRoom();
        this.isValidDoor = IntMaths.inRange(room.getDoorSize(), this.selectedIndex);
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
}
