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
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.text.line.input.ITextLineInputComponentProvider;
import dreamlink.overlay.component.text.line.input.TextLineInputComponent;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.world.room.module.door.Door;

public class DoorEditComponent extends WrapperComponent {

    private class InternalTargetDoorNameTextInputComponentProvider implements ITextLineInputComponentProvider {

        @Override
        public String getValue() {
            return DoorEditComponent.this.targetDoorName;
        }

        @Override
        public void onChange(String value) {
            DoorEditComponent.this.targetDoorName = value;
        }

    }

    private class InternalTargetDoorRoomTextInputComponentProvider implements ITextLineInputComponentProvider {

        @Override
        public String getValue() {
            return DoorEditComponent.this.targetRoomName;
        }

        @Override
        public void onChange(String value) {
            DoorEditComponent.this.targetRoomName = value;
        }

    }
    private class InternalInstructionTextLineComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public void setCharacterState(int characterIndex, CharacterData characterState) {
            if(characterIndex < DoorEditComponent.createDoorInstructionText.length()) {
                characterState.set(
                    DoorEditComponent.createDoorInstructionText.charAt(characterIndex),
                    FontDecoration.normal,
                    Vector4fMaths.black
                );
                return;
            }

            characterIndex -= DoorEditComponent.createDoorInstructionText.length();
            characterState.set(
                DoorEditComponent.this.targetDoor.name.charAt(characterIndex),
                FontDecoration.underline,
                Vector4fMaths.blue
            );
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public int getCharacterSize() {
            return DoorEditComponent.createDoorInstructionText.length() + DoorEditComponent.this.targetDoor.name.length();
        }

    }

    private static final int itemSpacing = 5;
    private static final String createDoorInstructionText = "Edit Door: ";
    private static final String targetRoomLabelText = "Target Room:";
    private static final String targetDoorLabelText = "Target Door:";

    private static final String saveButtonText = "Save";
    private static final String cancelButtonText = "Cancel";
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);

    private final IComponent component;
    private final IDoorComponentProvider provider;

    private Door targetDoor;
    private String targetDoorName;
    private String targetRoomName;

    public DoorEditComponent(IDoorComponentProvider provider) {
        this.provider = provider;
        this.component = new VerticalSpanComponent(
            Alignment.start,
            DoorEditComponent.itemSpacing
        )
            .addComponent(
                new TextLineViewComponent(
                    new InternalInstructionTextLineComponentProvider(),
                    Alignment.start
                )
            )
            .addComponent(
                new TextLineLabelComponent(
                    DoorEditComponent.targetRoomLabelText,
                    FontDecoration.normal,    
                    Vector4fMaths.black
                )
            )
            .addComponent(
                new TextLineInputComponent(
                    new InternalTargetDoorRoomTextInputComponentProvider()
                )
            )
            .addComponent(
                new TextLineLabelComponent(
                    DoorEditComponent.targetDoorLabelText,
                    FontDecoration.normal,    
                    Vector4fMaths.black
                )
            )
            .addComponent(
                new TextLineInputComponent(
                    new InternalTargetDoorNameTextInputComponentProvider()
                )
            )
            .addComponent(
                new HorizontalSpanComponent(
                    Alignment.center, 
                    DoorEditComponent.itemSpacing
                )
                    .addComponent(
                        new ButtonComponent(
                            new StaticButtonComponentProvider(this::save),
                            new BoxComponent(
                                new TextLineLabelComponent(DoorEditComponent.saveButtonText, FontDecoration.normal, Vector4fMaths.black),
                                BoxDimension.fixed(DoorEditComponent.buttonDimensions.x()),
                                BoxDimension.fixed(DoorEditComponent.buttonDimensions.y())
                            )
                        )
                    )
                    .addComponent(
                        new ButtonComponent(
                            new StaticButtonComponentProvider(this.provider::gotoList),
                            new BoxComponent(
                                new TextLineLabelComponent(DoorEditComponent.cancelButtonText, FontDecoration.normal, Vector4fMaths.black),
                                BoxDimension.fixed(DoorEditComponent.buttonDimensions.x()),
                                BoxDimension.fixed(DoorEditComponent.buttonDimensions.y())
                            )
                        )
                    )
            );
    }

    public void clear(Door door) {
        this.targetDoor = door;
        this.targetDoorName = door.targetDoorName;
        this.targetRoomName = door.targetRoomName;
    }

    private void save() {
        this.targetDoor.targetDoorName = this.targetDoorName;
        this.targetDoor.targetRoomName = this.targetRoomName;
        this.provider.gotoList();
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
