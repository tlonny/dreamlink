package periwinkle.terrain;

public class BlockBuffer {

    private final static int LIGHT_LENGTH = 4;
    private final static int LIGHT_MASK = (1 << LIGHT_LENGTH) - 1;

    public BlockType blockType = BlockType.AIR;
    public int localLight;
    public int globalLight;

    public BlockBuffer(int packedValue) {
        this.unpack(packedValue);
    }

    public BlockBuffer() {
        this(0);
    }

    public BlockBuffer(BlockType blockType) {
        this.reset(blockType);
    }

    public BlockBuffer reset(BlockType blockType) {
        this.blockType = blockType;
        this.localLight = blockType.localLight;
        return this;
    }

    public int pack() {
        var value = blockType.blockID;
        value <<= LIGHT_LENGTH;
        value += this.localLight;
        value <<= LIGHT_LENGTH;
        value += this.globalLight;
        return value;
    }

    public BlockBuffer unpack(int value) {
        this.globalLight = value & LIGHT_MASK;
        value >>= LIGHT_LENGTH;
        this.localLight = value & LIGHT_MASK;
        value >>= LIGHT_LENGTH;
        this.blockType = BlockType.BLOCK_ID_LOOKUP.get(value);
        return this;
    }

}
