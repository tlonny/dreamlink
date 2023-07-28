package doors.graphics.ui.border;

import doors.graphics.texture.MenuTexture;
import doors.graphics.texture.TextureSample;

public class DisabledDialogBorderSchema extends AbstractBorderSchema {

    public static DisabledDialogBorderSchema SCHEMA = new DisabledDialogBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogDisabledTop;
    }

    @Override
    public TextureSample getBottomTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogDisabledBottom;
    }

    @Override
    public TextureSample getLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogDisabledLeft;
    }

    @Override
    public TextureSample getRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogDisabledRight;
    }

    @Override
    public TextureSample getCenterTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogDisabledCenter;
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogDisabledTopLeft;
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogDisabledTopRight;
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogDisabledBottomLeft;
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogDisabledBottomRight;
    }
    
}
