package doors.graphics.texture.channel;

import doors.core.graphics.texture.AbstractTextureChannel;

public class BlockTextureChannel extends AbstractTextureChannel {

    public static BlockTextureChannel BLOCK_TEXTURE_CHANNEL = new BlockTextureChannel();

    @Override
    public int getTextureChannelID() {
        return 3;
    }
    
}
