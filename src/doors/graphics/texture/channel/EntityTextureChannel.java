package doors.graphics.texture.channel;

import doors.core.graphics.texture.AbstractTextureChannel;

public class EntityTextureChannel extends AbstractTextureChannel {

    public static EntityTextureChannel ENTITY_TEXTURE_CHANNEL = new EntityTextureChannel();

    @Override
    public int getTextureChannelID() {
        return 2;
    }
    
}
