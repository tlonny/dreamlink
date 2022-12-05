package dreamlink.overlay.simulation.edit.door;

import org.joml.Vector2i;
import org.joml.Vector2ic;

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
import dreamlink.overlay.component.list.select.ListSelectDropDownComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.text.line.input.ITextLineInputComponentProvider;
import dreamlink.overlay.component.text.line.input.TextLineInputComponent;
import dreamlink.utility.maths.IntMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.world.World;

public class DoorCreateComponent extends WrapperComponent {

    private class InternalDoorNameTextInputComponentProvider implements ITextLineInputComponentProvider {

        @Override
        public String getValue() {
            return DoorCreateComponent.this.doorName;
        }

        @Override
        public void onChange(String value) {
            DoorCreateComponent.this.doorName = value;
            DoorCreateComponent.this.updateIsValidDoor();
        }

    }

    private class InternalTargetDoorTextInputComponentProvider implements ITextLineInputComponentProvider {

        @Override
        public String getValue() {
            return DoorCreateComponent.this.targetDoorName;
        }

        @Override
        public void onChange(String value) {
            DoorCreateComponent.this.targetDoorName = value;
        }

    }

    private class InternalTargetRoomTextInputComponentProvider implements ITextLineInputComponentProvider {

        @Override
        public String getValue() {
            return DoorCreateComponent.this.targetRoomName;
        }

        @Override
        public void onChange(String value) {
            DoorCreateComponent.this.targetRoomName = value;
        }

    }

    private class InternalCreateButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            var room = World.instance.getRoom();
            var doorTemplate = room.getDoorTemplateByIndex(DoorCreateComponent.this.selectedIndex);
            var door = room.createDoor(doorTemplate, DoorCreateComponent.this.doorName);
            door.targetRoomName = DoorCreateComponent.this.targetRoomName;
            door.targetDoorName = DoorCreateComponent.this.targetDoorName;
            DoorCreateComponent.this.provider.gotoList();
        }

        @Override
        public boolean isButtonDisabled() {
            return !DoorCreateComponent.this.isValidDoor;
        }

    }

    private class InternalDoorTemplateSelectComponentProvider implements IListSelectComponentProvider {

        @Override
        public int getRowCount() {
            var room = World.instance.getRoom();
            return room.getDoorTemplateSize();
        }

        @Override
        public int getSelectedRowIndex() {
            return DoorCreateComponent.this.selectedIndex;
        }

        @Override
        public void setRowData(int index, ListRowData data) {
            var room = World.instance.getRoom();
            var doorTemplate = room.getDoorTemplateByIndex(index);
            data.text = doorTemplate.name;
            data.icon = doorTemplate.iconTextureSample;
        }

        @Override
        public void onChange(int index) {
            DoorCreateComponent.this.selectedIndex = index;
            DoorCreateComponent.this.updateIsValidDoor();
        }

    }

    private static final int itemSpacing = 5;
    private static final int dropDownHeight = 120;
    private static final String createDoorInstructionText = "Create Door:";
    private static final String doorNameLabelText = "Door Name:";
    private static final String targetRoomLabelText = "Target Room:";
    private static final String targetDoorLabelText = "Target Door:";

    private static final String saveButtonText = "Save";
    private static final String cancelButtonText = "Cancel";
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);

    private final IComponent component;
    private final IDoorComponentProvider provider;

    private int selectedIndex = -1;
    private boolean isValidDoor;
    private String doorName;
    private String targetRoomName;
    private String targetDoorName;

    public DoorCreateComponent(IDoorComponentProvider provider) {
        this.provider = provider;
        this.component = new VerticalSpanComponent(
            Alignment.start,
            DoorCreateComponent.itemSpacing
        )
            .addComponent(
                new TextLineLabelComponent(
                    DoorCreateComponent.createDoorInstructionText,
                    FontDecoration.normal,
                    Vector4fMaths.black
                )
            )
            .addComponent(
                new ListSelectDropDownComponent(
                    new InternalDoorTemplateSelectComponentProvider(),
                    dropDownHeight
                )
            )
            .addComponent(
                new TextLineLabelComponent(
                    DoorCreateComponent.doorNameLabelText,
                    FontDecoration.normal,    
                    Vector4fMaths.black
                )
            )
            .addComponent(
                new TextLineInputComponent(
                    new InternalDoorNameTextInputComponentProvider()
                )
            )
            .addComponent(
                new TextLineLabelComponent(
                    DoorCreateComponent.targetRoomLabelText,
                    FontDecoration.normal,    
                    Vector4fMaths.black
                )
            )
            .addComponent(
                new TextLineInputComponent(
                    new InternalTargetRoomTextInputComponentProvider()
                )
            )
            .addComponent(
                new TextLineLabelComponent(
                    DoorCreateComponent.targetDoorLabelText,
                    FontDecoration.normal,    
                    Vector4fMaths.black
                )
            )
            .addComponent(
                new TextLineInputComponent(
                    new InternalTargetDoorTextInputComponentProvider()
                )
            )
            .addComponent(
                new HorizontalSpanComponent(
                    Alignment.center, 
                    DoorCreateComponent.itemSpacing
                )
                    .addComponent(
                        new ButtonComponent(
                            new InternalCreateButtonComponentProvider(),
                            new BoxComponent(
                                new TextLineLabelComponent(DoorCreateComponent.saveButtonText, FontDecoration.normal, Vector4fMaths.black),
                                BoxDimension.fixed(DoorCreateComponent.buttonDimensions.x()),
                                BoxDimension.fixed(DoorCreateComponent.buttonDimensions.y())
                            )
                        )
                    )
                    .addComponent(
                        new ButtonComponent(
                            new StaticButtonComponentProvider(this.provider::gotoList),
                            new BoxComponent(
                                new TextLineLabelComponent(DoorCreateComponent.cancelButtonText, FontDecoration.normal, Vector4fMaths.black),
                                BoxDimension.fixed(DoorCreateComponent.buttonDimensions.x()),
                                BoxDimension.fixed(DoorCreateComponent.buttonDimensions.y())
                            )
                        )
                    )
            );
    }

    public void updateIsValidDoor() {
        var room = World.instance.getRoom();
        this.isValidDoor = true
            && IntMaths.inRange(room.getDoorTemplateSize(), this.selectedIndex)
            && !this.doorName.isBlank()
            && room.getDoorByName(doorName) == null;
    }

    public void clear() {
        this.selectedIndex = -1;
        this.doorName = "";
        this.targetRoomName = "";
        this.targetDoorName = "";
        this.isValidDoor = false;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
