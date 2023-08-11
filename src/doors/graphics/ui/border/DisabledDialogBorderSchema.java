package doors.graphics.ui.border;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.graphics.texture.sample.TextureSample;

public class DisabledDialogBorderSchema extends AbstractBorderSchema {

    public static DisabledDialogBorderSchema SCHEMA = new DisabledDialogBorderSchema();

    @Override

        public TextureSample getTopTextureSample() {
            return MenuTextureSample.DIALOG_DISABLED_TOP;
        }

        @Override
        public TextureSample getBottomTextureSample() {
            return MenuTextureSample.DIALOG_DISABLED_BOTTOM;
        }

        @Override
        public TextureSample getLeftTextureSample() {
            return MenuTextureSample.DIALOG_DISABLED_LEFT;
        }

        @Override
        public TextureSample getRightTextureSample() {
            return MenuTextureSample.DIALOG_DISABLED_RIGHT;
        }

        @Override
        public TextureSample getCenterTextureSample() {
            return MenuTextureSample.DIALOG_DISABLED_CENTER;
        }

        @Override
        public TextureSample getTopLeftTextureSample() {
            return MenuTextureSample.DIALOG_DISABLED_TOP_LEFT;
        }

        @Override
        public TextureSample getTopRightTextureSample() {
            return MenuTextureSample.DIALOG_DISABLED_TOP_RIGHT;
        }

        @Override
        public TextureSample getBottomLeftTextureSample() {
            return MenuTextureSample.DIALOG_DISABLED_BOTTOM_LEFT;
        }

        @Override
        public TextureSample getBottomRightTextureSample() {
            return MenuTextureSample.DIALOG_DISABLED_BOTTOM_RIGHT;
        }
        
    }
