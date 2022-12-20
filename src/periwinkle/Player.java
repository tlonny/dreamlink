package periwinkle;

import periwinkle.terrain.BlockType;
import periwinkle.io.ButtonLatch;
import periwinkle.spatial.PlayerControlSystem;
import periwinkle.spatial.SpatialComponent;
import periwinkle.terrain.BlockFormat;
import periwinkle.utility.VoxelCaster;
import org.joml.*;

public class Player {

    public BlockType blockType = BlockType.MAGMA;

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
                if(Game.WORLD.getBlock(caster.cursor).blockType.blockFormat != BlockFormat.VOID) {
                    Game.WORLD.setBlock(caster.cursor, BlockType.AIR);
                    break;
                }
            }
            return;
        }

        if(Game.MOUSE.isLeftMouseButtonPressed(this.leftMouseLatch)) {
            Vector3i previous = new Vector3i(caster.cursor);
            for(var ix =0; ix < 40; ix += 1) {
                caster.advance();
                if(Game.WORLD.getBlock(caster.cursor).blockType.blockFormat != BlockFormat.VOID) {
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
