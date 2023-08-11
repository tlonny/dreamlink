package doors.graphics.ui.border;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.graphics.texture.sample.TextureSample;

public class NormalButtonBorderSchema extends AbstractBorderSchema {

    public static NormalButtonBorderSchema SCHEMA = new NormalButtonBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTextureSample.BUTTON_TOP;
    }

    @Override
    public TextureSample getBottomTextureSample() {
        return MenuTextureSample.BUTTON_BOTTOM;
    }

    @Override
    public TextureSample getLeftTextureSample() {
        return MenuTextureSample.BUTTON_LEFT;
    }

    @Override
    public TextureSample getRightTextureSample() {
        return MenuTextureSample.BUTTON_RIGHT;
    }

    @Override
    public TextureSample getCenterTextureSample() {
        return MenuTextureSample.BUTTON_CENTER;
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        return MenuTextureSample.BUTTON_TOP_LEFT;
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        return MenuTextureSample.BUTTON_TOP_RIGHT;
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        return MenuTextureSample.BUTTON_BOTTOM_LEFT;
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        return MenuTextureSample.BUTTON_BOTTOM_RIGHT;
    }
}
