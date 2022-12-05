package dreamlink.overlay.simulation.edit.door;

import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.choice.ChoiceComponent;
import dreamlink.world.room.module.door.Door;

public class DoorComponent extends WrapperComponent {

    private class InternalDoorComponentProvider implements IDoorComponentProvider {

        @Override
        public void gotoList() {
            DoorComponent.this.selectedChoiceKey = DoorComponent.listComponentKey;
            DoorComponent.this.doorListComponent.updateIsValidDoor();
        }

        @Override
        public void gotoCreate() {
            DoorComponent.this.selectedChoiceKey = DoorComponent.createComponmentKey;
            DoorComponent.this.doorCreateComponent.clear();
        }

        @Override
        public void gotoEdit(Door door) {
            DoorComponent.this.selectedChoiceKey = DoorComponent.editComponmentKey;
            DoorComponent.this.doorEditComponent.clear(door);
        }

    }

    private static final int listComponentKey = 0;
    private static final int createComponmentKey = 1;
    private static final int editComponmentKey = 2;

    private final IComponent component;
    private final DoorListComponent doorListComponent;
    private final DoorCreateComponent doorCreateComponent;
    private final DoorEditComponent doorEditComponent;

    private int selectedChoiceKey = 0;

    public DoorComponent() {
        var provider = new InternalDoorComponentProvider();
        this.doorListComponent = new DoorListComponent(provider);
        this.doorCreateComponent = new DoorCreateComponent(provider);
        this.doorEditComponent = new DoorEditComponent(provider);

        this.component = new ChoiceComponent<>(this::getChoiceKey)
            .addComponent(DoorComponent.listComponentKey, this.doorListComponent)
            .addComponent(DoorComponent.createComponmentKey, this.doorCreateComponent)
            .addComponent(DoorComponent.editComponmentKey, this.doorEditComponent);
    }

    public void clear() {
        this.doorListComponent.clear();
        this.selectedChoiceKey = DoorComponent.listComponentKey;
    }

    private int getChoiceKey() {
        return this.selectedChoiceKey;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    
}
