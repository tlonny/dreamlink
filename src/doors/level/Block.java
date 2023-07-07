package doors.level;

import doors.core.graphics.texture.TextureSample;

public class Block {

    public final int blockID;
    public final TextureSample textureSample;
    public final String name;

    public Block(int blockID, String name, TextureSample textureSample) {
        this.blockID = blockID;
        this.name = name;
        this.textureSample = textureSample;
    }

}
