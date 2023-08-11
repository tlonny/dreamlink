package doors.graphics.ui.border;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.graphics.texture.sample.TextureSample;

public class DisabledButtonBorderSchema extends AbstractBorderSchema {

    public static DisabledButtonBorderSchema SCHEMA = new DisabledButtonBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTextureSample.BUTTON_DISABLED_TOP;
    }

    @Override
    public TextureSample getBottomTextureSample() {
        return MenuTextureSample.BUTTON_DISABLED_BOTTOM;
    }

    @Override
    public TextureSample getLeftTextureSample() {
        return MenuTextureSample.BUTTON_DISABLED_LEFT;
    }

    @Override
    public TextureSample getRightTextureSample() {
        return MenuTextureSample.BUTTON_DISABLED_RIGHT;
    }

    @Override
    public TextureSample getCenterTextureSample() {
        return MenuTextureSample.BUTTON_DISABLED_CENTER;
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        return MenuTextureSample.BUTTON_DISABLED_TOP_LEFT;
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        return MenuTextureSample.BUTTON_DISABLED_TOP_RIGHT;
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        return MenuTextureSample.BUTTON_DISABLED_BOTTOM_LEFT;
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        return MenuTextureSample.BUTTON_DISABLED_BOTTOM_RIGHT;
    }

}
