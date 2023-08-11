package doors.graphics.ui.border;

import doors.graphics.texture.sample.MenuTextureSample;
import doors.graphics.texture.sample.TextureSample;

public class PressedButtonBorderSchema extends AbstractBorderSchema {

    public static PressedButtonBorderSchema SCHEMA = new PressedButtonBorderSchema();

    @Override

        public TextureSample getTopTextureSample() {
            return MenuTextureSample.BUTTON_PRESSED_TOP;
        }

        @Override
        public TextureSample getBottomTextureSample() {
            return MenuTextureSample.BUTTON_PRESSED_BOTTOM;
        }

        @Override
        public TextureSample getLeftTextureSample() {
            return MenuTextureSample.BUTTON_PRESSED_LEFT;
        }

        @Override
        public TextureSample getRightTextureSample() {
            return MenuTextureSample.BUTTON_PRESSED_RIGHT;
        }

        @Override
        public TextureSample getCenterTextureSample() {
            return MenuTextureSample.BUTTON_PRESSED_CENTER;
        }

        @Override
        public TextureSample getTopLeftTextureSample() {
            return MenuTextureSample.BUTTON_PRESSED_TOP_LEFT;
        }

        @Override
        public TextureSample getTopRightTextureSample() {
            return MenuTextureSample.BUTTON_PRESSED_TOP_RIGHT;
        }

        @Override
        public TextureSample getBottomLeftTextureSample() {
            return MenuTextureSample.BUTTON_PRESSED_BOTTOM_LEFT;
        }

        @Override
        public TextureSample getBottomRightTextureSample() {
            return MenuTextureSample.BUTTON_PRESSED_BOTTOM_RIGHT;
        }
    }
