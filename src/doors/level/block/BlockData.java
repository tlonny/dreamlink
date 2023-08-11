package doors.level.block;

import doors.utility.Orientation;

public class BlockData {

    public AbstractBlock block; 
    public Orientation orientation; 

    public BlockData set(AbstractBlock block, Orientation orientation) {
        this.block = block;
        this.orientation = orientation;
        return this;
    }

    public BlockData clear() {
        return this.set(null, null);
    }
}
