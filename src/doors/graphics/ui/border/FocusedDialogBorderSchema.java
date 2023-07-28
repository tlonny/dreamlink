package doors.graphics.ui.border;

import doors.graphics.texture.MenuTexture;
import doors.graphics.texture.TextureSample;

public class FocusedDialogBorderSchema extends AbstractBorderSchema {

    public static FocusedDialogBorderSchema SCHEMA = new FocusedDialogBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogFocusedTop;
    }

    @Override
    public TextureSample getBottomTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogFocusedBottom;
    }

    @Override
    public TextureSample getLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogFocusedLeft;
    }

    @Override
    public TextureSample getRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogFocusedRight;
    }

    @Override
    public TextureSample getCenterTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogFocusedCenter;
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogFocusedTopLeft;
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogFocusedTopRight;
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogFocusedBottomLeft;
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.dialogFocusedBottomRight;
    }
    
}
