package doors.graphics.sprite.box;

import doors.core.graphics.sprite.AbstractBoxSprite;
import doors.core.graphics.texture.TextureSample;
import doors.graphics.texture.MenuTexture;
import doors.utility.vector.Vector2in;

public class ButtonSprite extends AbstractBoxSprite {

    public ButtonState buttonState;

    public ButtonSprite(Vector2in dimensions, ButtonState buttonState) {
        super(dimensions);
        this.buttonState = buttonState;
    }

    public ButtonSprite(Vector2in dimensions) {
        this(dimensions, ButtonState.NORMAL);
    }

    @Override
    public TextureSample getTopTextureSample() {
        if(this.buttonState == ButtonState.NORMAL) {
            return MenuTexture.MENU_TEXTURE.buttonTop;
        } else if(this.buttonState == ButtonState.PRESSED) {
            return MenuTexture.MENU_TEXTURE.buttonPressedTop;
        } else {
            return MenuTexture.MENU_TEXTURE.buttonDisabledTop;
        }
    }

    @Override
    public TextureSample getBottomTextureSample() {
        if(this.buttonState == ButtonState.NORMAL) {
            return MenuTexture.MENU_TEXTURE.buttonBottom;
        } else if(this.buttonState == ButtonState.PRESSED) {
            return MenuTexture.MENU_TEXTURE.buttonPressedBottom;
        } else {
            return MenuTexture.MENU_TEXTURE.buttonDisabledBottom;
        }
    }

    @Override
    public TextureSample getLeftTextureSample() {
        if(this.buttonState == ButtonState.NORMAL) {
            return MenuTexture.MENU_TEXTURE.buttonLeft;
        } else if(this.buttonState == ButtonState.PRESSED) {
            return MenuTexture.MENU_TEXTURE.buttonPressedLeft;
        } else {
            return MenuTexture.MENU_TEXTURE.buttonDisabledLeft;
        }
    }

    @Override
    public TextureSample getRightTextureSample() {
        if(this.buttonState == ButtonState.NORMAL) {
            return MenuTexture.MENU_TEXTURE.buttonRight;
        } else if(this.buttonState == ButtonState.PRESSED) {
            return MenuTexture.MENU_TEXTURE.buttonPressedRight;
        } else {
            return MenuTexture.MENU_TEXTURE.buttonDisabledRight;
        }
    }

    @Override
    public TextureSample getCenterTextureSample() {
        if(this.buttonState == ButtonState.NORMAL) {
            return MenuTexture.MENU_TEXTURE.buttonCenter;
        } else if(this.buttonState == ButtonState.PRESSED) {
            return MenuTexture.MENU_TEXTURE.buttonPressedCenter;
        } else {
            return MenuTexture.MENU_TEXTURE.buttonDisabledCenter;
        }
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        if(this.buttonState == ButtonState.NORMAL) {
            return MenuTexture.MENU_TEXTURE.buttonTopLeft;
        } else if(this.buttonState == ButtonState.PRESSED) {
            return MenuTexture.MENU_TEXTURE.buttonPressedTopLeft;
        } else {
            return MenuTexture.MENU_TEXTURE.buttonDisabledTopLeft;
        }
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        if(this.buttonState == ButtonState.NORMAL) {
            return MenuTexture.MENU_TEXTURE.buttonTopRight;
        } else if(this.buttonState == ButtonState.PRESSED) {
            return MenuTexture.MENU_TEXTURE.buttonPressedTopRight;
        } else {
            return MenuTexture.MENU_TEXTURE.buttonDisabledTopRight;
        }
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        if(this.buttonState == ButtonState.NORMAL) {
            return MenuTexture.MENU_TEXTURE.buttonBottomLeft;
        } else if(this.buttonState == ButtonState.PRESSED) {
            return MenuTexture.MENU_TEXTURE.buttonPressedBottomLeft;
        } else {
            return MenuTexture.MENU_TEXTURE.buttonDisabledBottomLeft;
        }
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        if(this.buttonState == ButtonState.NORMAL) {
            return MenuTexture.MENU_TEXTURE.buttonBottomRight;
        } else if(this.buttonState == ButtonState.PRESSED) {
            return MenuTexture.MENU_TEXTURE.buttonPressedBottomRight;
        } else {
            return MenuTexture.MENU_TEXTURE.buttonDisabledBottomRight;
        }
    }
    
}
