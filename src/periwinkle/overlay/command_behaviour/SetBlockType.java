package periwinkle.overlay.command_behaviour;

import periwinkle.Game;
import periwinkle.terrain.BlockType;

public class SetBlockType implements ICommandBehaviour {

    public void run(String[] args) {
        var bt = BlockType.BLOCK_NAME_LOOKUP.get(args[1]);
        if(bt != null)
            Game.PLAYER.blockType = bt;
    }
}
