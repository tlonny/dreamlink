package dreamlink.player;

import org.joml.Vector3i;

import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.world.World;

public class SpawnModule {

    private final IPlayerModuleProvider provider;

    public SpawnModule(IPlayerModuleProvider provider) {
        this.provider = provider;
    }

    private final Vector3i spawnDimensions = new Vector3i();

    public void spawn(String doorName) {
        var kinematicsStateModule = this.provider.getKinematicsStateModule();

        var room = World.instance.getRoom();
        var spawnDoor = room.getDoorByName(doorName);

        if(spawnDoor == null || !spawnDoor.isPlaced()) {
            var dimensions = room.getDimensions(this.spawnDimensions).div(2);
            kinematicsStateModule.position.set(dimensions);
            kinematicsStateModule.resetLaggedHeight();
            return;
        }

        var rotation = kinematicsStateModule.rotation.zero();
        rotation.y = spawnDoor.getOrientation().getOpposite().yaw;

        var position = spawnDoor.getPlanePosition(kinematicsStateModule.position);
        var dimensions = kinematicsStateModule.getDimensions();
        position.y -= 1;
        position.x -= dimensions.x() / 2;
        position.z -= dimensions.z() / 2;
        Vector3fMaths.add(position, spawnDoor.getOrientation().cubeFace.normal);
        kinematicsStateModule.resetLaggedHeight();
    }
    
}
