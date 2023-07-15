package doors.graphics.texture.channel;

import doors.core.graphics.texture.AbstractTextureChannel;

public class MenuTextureChannel extends AbstractTextureChannel {

    public static MenuTextureChannel MENU_TEXTURE_CHANNEL = new MenuTextureChannel();

    @Override
    public int getTextureChannelID() {
        return 1;
    }
    
}
