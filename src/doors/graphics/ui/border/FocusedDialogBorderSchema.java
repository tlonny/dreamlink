package doors.graphics.ui.border;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.graphics.texture.sample.TextureSample;

public class FocusedDialogBorderSchema extends AbstractBorderSchema {

    public static FocusedDialogBorderSchema SCHEMA = new FocusedDialogBorderSchema();

    @Override
    public TextureSample getTopTextureSample() {
        return MenuTextureSample.DIALOG_FOCUSED_TOP;
    }

    @Override
        public TextureSample getBottomTextureSample() {
            return MenuTextureSample.DIALOG_FOCUSED_BOTTOM;
        }

        @Override
        public TextureSample getLeftTextureSample() {
            return MenuTextureSample.DIALOG_FOCUSED_LEFT;
        }

        @Override
        public TextureSample getRightTextureSample() {
            return MenuTextureSample.DIALOG_FOCUSED_RIGHT;
        }

        @Override
        public TextureSample getCenterTextureSample() {
            return MenuTextureSample.DIALOG_FOCUSED_CENTER;
        }

        @Override
        public TextureSample getTopLeftTextureSample() {
            return MenuTextureSample.DIALOG_FOCUSED_TOP_LEFT;
        }

        @Override
        public TextureSample getTopRightTextureSample() {
            return MenuTextureSample.DIALOG_FOCUSED_TOP_RIGHT;
        }

        @Override
        public TextureSample getBottomLeftTextureSample() {
            return MenuTextureSample.DIALOG_FOCUSED_BOTTOM_LEFT;
        }

        @Override
        public TextureSample getBottomRightTextureSample() {
            return MenuTextureSample.DIALOG_FOCUSED_BOTTOM_RIGHT;
        }
        
    }
