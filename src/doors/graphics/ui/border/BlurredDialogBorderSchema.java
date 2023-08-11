package doors.graphics.ui.border;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.graphics.texture.sample.TextureSample;

public class BlurredDialogBorderSchema extends AbstractBorderSchema {

    public static BlurredDialogBorderSchema SCHEMA = new BlurredDialogBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTextureSample.DIALOG_BLURRED_TOP;
    }

    @Override
    public TextureSample getBottomTextureSample() {
        return MenuTextureSample.DIALOG_BLURRED_BOTTOM;
    }

    @Override
    public TextureSample getLeftTextureSample() {
        return MenuTextureSample.DIALOG_BLURRED_LEFT;
    }

    @Override
    public TextureSample getRightTextureSample() {
        return MenuTextureSample.DIALOG_BLURRED_RIGHT;
    }

    @Override
    public TextureSample getCenterTextureSample() {
        return MenuTextureSample.DIALOG_BLURRED_CENTER;
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        return MenuTextureSample.DIALOG_BLURRED_TOP_LEFT;
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        return MenuTextureSample.DIALOG_BLURRED_TOP_RIGHT;
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        return MenuTextureSample.DIALOG_BLURRED_BOTTOM_LEFT;
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        return MenuTextureSample.DIALOG_BLURRED_BOTTOM_RIGHT;
    }
    
}
