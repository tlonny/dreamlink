package dreamlink.player;

import org.joml.Vector3i;

import dreamlink.player.ray.RayCastData;
import dreamlink.state.SimulationState;
import dreamlink.utility.maths.Orientation;
import dreamlink.window.Window;
import dreamlink.window.button.Button;
import dreamlink.world.World;
import dreamlink.world.room.module.block.AirBlock;
import dreamlink.world.room.module.terrain.TerrainBlockData;

public class StampApplyModule {

    private final IPlayerModuleProvider provider;
    private final Vector3i lastApplyPosition = new Vector3i();
    private boolean isButtonHeld;

    public StampApplyModule(IPlayerModuleProvider provider) {
        this.provider = provider;
    }

    private final RayCastData updateRayCastData = new RayCastData();
    private final Vector3i updateApplyPosition = new Vector3i();
    private final TerrainBlockData updateBlockData = new TerrainBlockData();

    public void update() {
        if(!SimulationState.instance.getAllowEdit()) {
            return;
        }

        if(Window.instance.isButtonPressed(Button.mouseLeft)) {
            this.isButtonHeld = true;
        } else if(!Window.instance.isButtonDown(Button.mouseLeft)) {
            this.isButtonHeld = false;
            this.lastApplyPosition.zero();
        }

        if(!isButtonHeld) {
            return;
        }

        var room = World.instance.getRoom();
        var selectedStamp = room.getSelectedPaletteStamp();
        if(selectedStamp == null) {
            return;
        }

        var rayCastData = this.provider
            .getRayCastingModule()
            .getRayCastData(this.updateRayCastData);

        if(!rayCastData.isHit) {
            return;
        }

        var applyPosition = this.updateApplyPosition
            .set(rayCastData.blockPosition)
            .add(rayCastData.rayCubeFace.normal);

        var applyBlockData = room.getBlockData(applyPosition, this.updateBlockData);
        if(applyBlockData.blockID != AirBlock.blockID) {
            return;
        }

        var isSameBlock = this.lastApplyPosition.equals(rayCastData.blockPosition);
        if(isSameBlock) {
            return;
        }

        var playerState = this.provider.getKinematicsStateModule();
        var orientation = Orientation.fromYaw(playerState.rotation.y).getOpposite();
        this.lastApplyPosition.set(applyPosition);
        selectedStamp.onStampApply(
            applyPosition,
            rayCastData.rayPosition,
            orientation
        );
    }
    
}
