package doors.graphics.ui.border;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.graphics.texture.sample.TextureSample;

public class SeperatorBorderSchema extends AbstractBorderSchema {

    public static SeperatorBorderSchema SCHEMA = new SeperatorBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTextureSample.SEPERATOR_TOP;
    }

    @Override
    public TextureSample getBottomTextureSample() {
        return MenuTextureSample.SEPERATOR_BOTTOM;
    }

    @Override
    public TextureSample getLeftTextureSample() {
        return MenuTextureSample.SEPERATOR_LEFT;
    }

    @Override
    public TextureSample getRightTextureSample() {
        return MenuTextureSample.SEPERATOR_RIGHT;   
    }

    @Override
    public TextureSample getCenterTextureSample() {
        return MenuTextureSample.SEPERATOR_CENTER;
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        return MenuTextureSample.SEPERATOR_TOP_LEFT;
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        return MenuTextureSample.SEPERATOR_TOP_RIGHT;
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        return MenuTextureSample.SEPERATOR_BOTTOM_LEFT;
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        return MenuTextureSample.SEPERATOR_BOTTOM_RIGHT;
    }
        
}