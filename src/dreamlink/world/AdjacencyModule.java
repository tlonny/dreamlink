package dreamlink.world;

import dreamlink.state.SimulationState;
import dreamlink.world.room.module.door.DoorLinkData;

public class AdjacencyModule {

    private final IWorldModuleProvider provider;

    public AdjacencyModule(IWorldModuleProvider provider) {
        this.provider = provider;
    }

    private final DoorLinkData updateLinkData = new DoorLinkData();

    public void update() {
        if(!SimulationState.instance.getAllowOpenDoors()) {
            return;
        }

        var room = this.provider
            .getRoomStateModule()
            .getRoom();

        if(!room.isFinalized()) {
            return;
        }

        for(var ix = 0; ix < room.getDoorSize(); ix += 1) {
            var door = room.getDoorByIndex(ix);
            var linkData = door.resolveLink(this.updateLinkData);
            linkData.targetRoom.load();
        }

    }
    
}
