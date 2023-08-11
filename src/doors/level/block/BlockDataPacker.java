package doors.level.block;

import doors.level.Level;
import doors.utility.Orientation;

public class BlockDataPacker {

    private Level level;

    public BlockDataPacker(Level level) {
        this.level = level;
    }

    public int packBlockData(BlockData blockData) {
        var packedValue = 0;
        packedValue += blockData.orientation.getIndex();
        packedValue *= 0x10000;
        packedValue += blockData.block == null ? 0 : blockData.block.blockID;
        return packedValue;
    }

    public BlockData unpackBlockData(BlockData blockData, int packedValue) {
        var blockID = packedValue & (0x10000 - 1);
        blockData.block = blockID == 0 ? null : this.level.getBlock(blockID);

        var orientationIndex = packedValue / 0x10000;
        blockData.orientation = Orientation.ORIENTATIONS[orientationIndex];
        return blockData;
    }
    
}
