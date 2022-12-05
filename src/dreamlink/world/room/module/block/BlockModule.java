package dreamlink.world.room.module.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dreamlink.world.room.IRoomModuleProvider;

public class BlockModule {
    
    private final Map<Integer, IBlock> blockMap = new HashMap<>();
    private final List<IBlock> blocks = new ArrayList<>();

    public BlockModule(IRoomModuleProvider provider) {
        this.addBlock(new AirBlock());
        this.addBlock(new BarrierBlock(provider));
    }

    public IBlock getBlockByID(int blockID) {
        return this.blockMap.get(blockID);
    }

    public int getSize() {
        return this.blocks.size();
    }

    public IBlock getBlockByIndex(int index) {
        return this.blocks.get(index);
    }

    public void addBlock(IBlock block) {
        this.blockMap.put(block.getBlockID(), block);
        this.blocks.add(block);
    }

    public void removeBlock(IBlock block) {
        this.blockMap.remove(block.getBlockID());
        this.blocks.remove(block);
    }

}
