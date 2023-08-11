package doors.graphics.ui.border;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.graphics.texture.sample.TextureSample;

public class WindowBorderSchema extends AbstractBorderSchema {

    public static WindowBorderSchema SCHEMA = new WindowBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTextureSample.WINDOW_TOP;
    }

    @Override
    public TextureSample getBottomTextureSample() {
        return MenuTextureSample.WINDOW_BOTTOM;
    }

    @Override
    public TextureSample getLeftTextureSample() {
        return MenuTextureSample.WINDOW_LEFT;
    }

    @Override
    public TextureSample getRightTextureSample() {
        return MenuTextureSample.WINDOW_RIGHT;   
    }

    @Override
    public TextureSample getCenterTextureSample() {
        return MenuTextureSample.WINDOW_CENTER;
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        return MenuTextureSample.WINDOW_TOP_LEFT;
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        return MenuTextureSample.WINDOW_TOP_RIGHT;
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        return MenuTextureSample.WINDOW_BOTTOM_LEFT;
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        return MenuTextureSample.WINDOW_BOTTOM_RIGHT;
    }
    
}
