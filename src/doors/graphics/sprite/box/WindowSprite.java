package doors.graphics.sprite.box;

import doors.core.graphics.sprite.AbstractBoxSprite;
import doors.core.graphics.texture.TextureSample;
import doors.graphics.texture.MenuTexture;
import doors.utility.vector.Vector2in;

public class WindowSprite extends AbstractBoxSprite {

    public WindowSprite(Vector2in dimensions) {
        super(dimensions);
    }

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
