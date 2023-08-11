package doors.graphics.texture.channel;

public class LevelTextureChannel extends AbstractTextureChannel {

    public static LevelTextureChannel LEVEL_TEXTURE_CHANNEL = new LevelTextureChannel();

    @Override
    public int getTextureUnitID() {
        return 4;
    }
    
}
