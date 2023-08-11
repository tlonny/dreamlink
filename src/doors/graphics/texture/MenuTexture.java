package doors.graphics.texture;

import doors.graphics.texture.channel.MenuTextureChannel;

public class MenuTexture extends ImageTexture {

    private static String TEXTURE_PATH = "data/texture/ui.png";

    public static MenuTexture MENU_TEXTURE = new MenuTexture();

    public MenuTexture() {
        super(MenuTextureChannel.MENU_TEXTURE_CHANNEL, TEXTURE_PATH);
    }


}
