package doors.graphics.ui.border;

import doors.graphics.texture.MenuTexture;
import doors.graphics.texture.TextureSample;

public class NormalButtonBorderSchema extends AbstractBorderSchema {

    public static NormalButtonBorderSchema SCHEMA = new NormalButtonBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTexture.MENU_TEXTURE.buttonTop;
    }

    @Override
    public TextureSample getBottomTextureSample() {
        return MenuTexture.MENU_TEXTURE.buttonBottom;
    }

    @Override
    public TextureSample getLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.buttonLeft;
    }

    @Override
    public TextureSample getRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.buttonRight;
    }

    @Override
    public TextureSample getCenterTextureSample() {
        return MenuTexture.MENU_TEXTURE.buttonCenter;
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.buttonTopLeft;
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.buttonTopRight;
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.buttonBottomLeft;
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.buttonBottomRight;
    }
}
