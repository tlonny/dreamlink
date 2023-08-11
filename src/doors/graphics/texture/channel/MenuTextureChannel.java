package doors.graphics.texture.channel;

public class MenuTextureChannel extends AbstractTextureChannel {

    public static MenuTextureChannel MENU_TEXTURE_CHANNEL = new MenuTextureChannel();

    @Override
    public int getTextureUnitID() {
        return 2;
    }
    
}
