package dreamlink.player;

import dreamlink.player.ray.RayCastData;
import dreamlink.state.SimulationState;
import dreamlink.window.Window;
import dreamlink.window.button.Button;
import dreamlink.world.World;

public class BlockEraseModule {

    private final IPlayerModuleProvider provider;

    public BlockEraseModule(IPlayerModuleProvider provider) {
        this.provider = provider;
    }

    private final RayCastData updateRayCastData = new RayCastData();

    public void update() {
        if(!SimulationState.instance.getAllowEdit()) {
            return;
        }

        if(!Window.instance.isButtonPressed(Button.mouseRight)) {
            return;
        }

        var rayCastData = this.provider
            .getRayCastingModule()
            .getRayCastData(this.updateRayCastData);

        if(!rayCastData.isHit) {
            return;
        }

        var room = World.instance.getRoom();
        var block = room.getBlockByID(rayCastData.blockData.blockID);
        block.onErase(rayCastData.blockPosition, rayCastData.blockData);
    }
    
}
