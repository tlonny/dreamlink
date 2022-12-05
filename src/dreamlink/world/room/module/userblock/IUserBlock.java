package dreamlink.world.room.module.userblock;

import dreamlink.world.room.module.block.IBlock;
import dreamlink.world.room.module.stamp.IStamp;

public interface IUserBlock extends IBlock, IStamp {

    public int getBlockID();

    public String getFileName();
    
}
