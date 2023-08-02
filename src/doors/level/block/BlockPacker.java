package doors.level.block;

import doors.utility.Orientation;

public class BlockPacker {

    public Orientation orientation;
    public Block block;
    private BlockMap blockMap;

    private int packBlockID() {
        if(this.block == null) {
            return 0;
        }
        return this.block.blockID;
    }

    public BlockPacker(BlockMap blockMap) {
        this.blockMap = blockMap;
    }

    public int packBlock() {
        var packedValue = 0;
        packedValue += this.orientation.index;
        packedValue *= 0x10000;
        packedValue += this.packBlockID();
        return packedValue;
    }

    public void unpackBlock(int value) {
        var blockID = value & (0x10000 - 1);
        this.block = blockID == 0 ? null : this.blockMap.getBlock(blockID);

        var orientationIndex = value / 0x10000;
        this.orientation = Orientation.ORIENTATION_MAP.get(orientationIndex);
    }
    
}
