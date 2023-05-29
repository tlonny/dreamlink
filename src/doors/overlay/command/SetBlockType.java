package doors.overlay.command_behaviour;

import doors.Game;
import doors.terrain.Block;

public class SetBlockType implements ICommandBehaviour {

    public void run(String[] args) {
        var bt = Block.BLOCK_NAME_LOOKUP.get(args[1]);
        if(bt != null)
            Game.PLAYER.blockType = bt;
    }
}
