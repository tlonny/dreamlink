package dreamlink.world.room.module.terrain;

import dreamlink.utility.maths.Orientation;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.block.IBlock;

public class TerrainBlockData {

    private static final int blockIDMask = 0x3FF;
    private static final int blockIDOffset = 0;
    private static final int orientationIDOffset = 10;
    private static final int orientationIDMask = 0x3;
    private static final int lightMask = 0xF;
    private static final int echoOffset = 28;
    private static final int echoMask = 0xF;

    public int blockID;
    public int echo;
    public int orientationIndex;
    private final int[] lightLevels = new int[TerrainLight.getSize()];

    public TerrainBlockData unpack(int value) {
        this.blockID = (value >> blockIDOffset) & blockIDMask;
        this.orientationIndex = (value >> orientationIDOffset) & orientationIDMask;
        this.echo = (value >> echoOffset) & echoMask;
        for(var ix = 0; ix < TerrainLight.getSize(); ix += 1) {
            var terrainLight = TerrainLight.get(ix);
            this.lightLevels[ix] = (value >> terrainLight.byteOffset) & lightMask;
        }
        return this;
    }

    public TerrainBlockData clear() {
        return this.unpack(0);
    }

    public int pack() {
        int packedValue = 0;
        packedValue += this.blockID << blockIDOffset;
        packedValue += this.orientationIndex << orientationIDOffset;
        packedValue += this.echo << echoOffset;
        for(var ix = 0; ix < TerrainLight.getSize(); ix += 1) {
            var terrainLight = TerrainLight.get(ix);
            packedValue += this.lightLevels[ix] << terrainLight.byteOffset;
        }
        return packedValue;
    }

    public TerrainBlockData set(TerrainBlockData terrainBlockData) {
        this.blockID = terrainBlockData.blockID;
        this.echo = terrainBlockData.echo;
        this.orientationIndex = terrainBlockData.orientationIndex;
        for(var ix = 0; ix < this.lightLevels.length; ix += 1) {
            this.lightLevels[ix] = terrainBlockData.lightLevels[ix];
        }
        return this;
    }

    public TerrainBlockData set(IBlock block, Orientation orientation) {
        this.blockID = block.getBlockID();
        this.orientationIndex = orientation.getIndex();
        this.echo = 0;
        this.setLight(TerrainLight.portal, 0);
        for(var ix = 0; ix < BlockLight.getSize(); ix += 1) {
            var blockLight = BlockLight.get(ix);
            this.lightLevels[blockLight.terrainLight.getIndex()] = block.getLight(blockLight);
        }
        return this;
    }

    public int getLight(TerrainLight terrainLight) {
        return this.lightLevels[terrainLight.getIndex()];
    }

    public void setLight(TerrainLight terrainLight, int value) {
        this.lightLevels[terrainLight.getIndex()] = value;
    }

}
