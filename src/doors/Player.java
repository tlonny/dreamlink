package doors;

import doors.terrain.Block;
import doors.io.ButtonLatch;
import doors.spatial.PlayerControlSystem;
import doors.spatial.SpatialComponent;
import doors.terrain.BlockType;
import doors.utility.VoxelCaster;
import org.joml.*;

public class Player {

    public Block blockType = null;

    public SpatialComponent spatialComponent = new SpatialComponent();
    public PlayerControlSystem playerControlSystem = new PlayerControlSystem();

    private ButtonLatch leftMouseLatch = new ButtonLatch();
    private ButtonLatch rightMouseLatch = new ButtonLatch();

    public Player() {
        this.spatialComponent.dimensions.set(0.9f, 1.8f, 0.9f);
    }

    private void modifyBlocks() {
        var direction = Game.CAMERA.directionVector;
        var caster = new VoxelCaster();
        caster.castRay(Game.CAMERA.position, direction);

        if(Game.MOUSE.isRightMouseButtonPressed(this.rightMouseLatch)) {
            for(var ix =0; ix < 40; ix += 1) {
                caster.advance();
                if(Game.WORLD.getBlock(caster.cursor).blockType.blockType != BlockType.VOID) {
                    Game.WORLD.setBlock(caster.cursor, Block.AIR);
                    break;
                }
            }
            return;
        }

        if(Game.MOUSE.isLeftMouseButtonPressed(this.leftMouseLatch)) {
            Vector3i previous = new Vector3i(caster.cursor);
            for(var ix =0; ix < 40; ix += 1) {
                caster.advance();
                if(Game.WORLD.getBlock(caster.cursor).blockType.blockType != BlockType.VOID) {
                    Game.WORLD.setBlock(previous, this.blockType);
                    break;
                }
                previous.set(caster.cursor);
            }
        }
    }

    public void simulate() {
        this.spatialComponent.bufferPosition();
        this.playerControlSystem.simulate(this.spatialComponent);
        this.modifyBlocks();
    }
}
