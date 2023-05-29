package doors.terrain;

import doors.graphics.TextureSample;

public class Block {

    public int blockID;
    public TextureSample textureSample;
    public String name;

    public Block(int blockID, String name, TextureSample textureSample) {
        this.blockID = blockID;
        this.name = name;
        this.textureSample = textureSample;
    }

}
