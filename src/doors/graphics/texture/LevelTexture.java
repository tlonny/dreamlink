package doors.graphics.texture;

import doors.graphics.texture.channel.LevelTextureChannel;

public class LevelTexture extends ImageTexture {

    public LevelTexture(String path) {
        super(LevelTextureChannel.LEVEL_TEXTURE_CHANNEL, path);
    }


}
