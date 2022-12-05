package periwinkle.overlay;

import periwinkle.Player;
import periwinkle.terrain.BlockType;

public class SetBlockType implements ICommandBehaviour {

    public void run(String[] args) {
        var bt = BlockType.BLOCK_NAME_LOOKUP.get(args[1]);
        if(bt != null)
            Player.PLAYER.blockType = bt;
    }
}
