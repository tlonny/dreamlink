package doors.graphics.ui.border;

import doors.graphics.texture.MenuTexture;
import doors.graphics.texture.TextureSample;

public class WindowBorderSchema extends AbstractBorderSchema {

    public static WindowBorderSchema SCHEMA = new WindowBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTexture.MENU_TEXTURE.windowTop;
    }

    @Override
    public TextureSample getBottomTextureSample() {
        return MenuTexture.MENU_TEXTURE.windowBottom;
    }

    @Override
    public TextureSample getLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.windowLeft;
    }

    @Override
    public TextureSample getRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.windowRight;   
    }

    @Override
    public TextureSample getCenterTextureSample() {
        return MenuTexture.MENU_TEXTURE.windowCenter;
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.windowTopLeft;
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.windowTopRight;
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.windowBottomLeft;
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.windowBottomRight;
    }
    
}
