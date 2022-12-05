package dreamlink.player;

import dreamlink.player.ray.RayCastData;
import dreamlink.state.SimulationState;
import dreamlink.window.Window;
import dreamlink.window.button.Button;
import dreamlink.world.World;

public class BlockInteractModule {

    private final IPlayerModuleProvider provider;
    private boolean canInteract;

    public BlockInteractModule(IPlayerModuleProvider provider) {
        this.provider = provider;
    }

    public boolean canInteract() {
        return this.canInteract;
    }

    private RayCastData updateRayData = new RayCastData();

    public void update() {
        var room = World.instance.getRoom();
        this.canInteract = false;

        if(!SimulationState.instance.getAllowOpenDoors()) {
            return;
        }

        var rayCastData = this.provider
            .getRayCastingModule()
            .getRayCastData(this.updateRayData);

        if(!rayCastData.isHit) {
            return;
        }

        var block = room.getBlockByID(rayCastData.blockData.blockID);

        this.canInteract = block.canInteract(
            rayCastData.blockPosition,
            rayCastData.blockData
        );

        if(!this.canInteract || !Window.instance.isButtonPressed(Button.keyE)) {
            return;
        }

        block.onInteract(
            rayCastData.blockPosition,
            rayCastData.blockData
        );
    }
}
