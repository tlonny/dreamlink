package doors;

import doors.terrain.Block;
import doors.spatial.PlayerControlSystem;
import doors.spatial.SpatialComponent;

public class Player {

    public Block blockType = null;

    public SpatialComponent spatialComponent = new SpatialComponent();
    public PlayerControlSystem playerControlSystem = new PlayerControlSystem();

    public Player() {
        this.spatialComponent.dimensions.set(0.9f, 1.8f, 0.9f);
    }

    public void simulate() {
        this.spatialComponent.bufferPosition();
        this.playerControlSystem.simulate(this.spatialComponent);
    }
}
