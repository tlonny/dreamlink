package doors.graphics.ui.border;

import doors.graphics.texture.MenuTexture;
import doors.graphics.texture.TextureSample;

public class SeperatorBorderSchema extends AbstractBorderSchema {

    public static SeperatorBorderSchema SCHEMA = new SeperatorBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTexture.MENU_TEXTURE.seperatorTop;
    }

    @Override
    public TextureSample getBottomTextureSample() {
        return MenuTexture.MENU_TEXTURE.seperatorBottom;
    }

    @Override
    public TextureSample getLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.seperatorLeft;
    }

    @Override
    public TextureSample getRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.seperatorRight;   
    }

    @Override
    public TextureSample getCenterTextureSample() {
        return MenuTexture.MENU_TEXTURE.seperatorCenter;
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.seperatorTopLeft;
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.seperatorTopRight;
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        return MenuTexture.MENU_TEXTURE.seperatorBottomLeft;
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        return MenuTexture.MENU_TEXTURE.seperatorBottomRight;
    }
    
}
