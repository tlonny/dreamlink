package doors.graphics.sprite.box;

import doors.core.graphics.sprite.AbstractBoxSprite;
import doors.core.graphics.texture.TextureSample;
import doors.graphics.texture.MenuTexture;
import doors.utility.vector.Vector2in;

public class DialogSprite extends AbstractBoxSprite {

    public DialogState dialogState;

    public DialogSprite(Vector2in dimensions, DialogState dialogState) {
        super(dimensions);
        this.dialogState = dialogState;
    }

    public DialogSprite(Vector2in dimensions) {
        this(dimensions, DialogState.FOCUSED);
    }

    @Override
    public TextureSample getTopTextureSample() {
        if(this.dialogState == DialogState.FOCUSED) {
            return MenuTexture.MENU_TEXTURE.dialogFocusTop;
        } else if(this.dialogState == DialogState.BLURRED) {
            return MenuTexture.MENU_TEXTURE.dialogBlurTop;
        } else {
            return MenuTexture.MENU_TEXTURE.dialogDisabledTop;
        }
    }

    @Override
    public TextureSample getBottomTextureSample() {
        if(this.dialogState == DialogState.FOCUSED) {
            return MenuTexture.MENU_TEXTURE.dialogFocusBottom;
        } else if(this.dialogState == DialogState.BLURRED) {
            return MenuTexture.MENU_TEXTURE.dialogBlurBottom;
        } else {
            return MenuTexture.MENU_TEXTURE.dialogDisabledBottom;
        }
    }

    @Override
    public TextureSample getLeftTextureSample() {
        if(this.dialogState == DialogState.FOCUSED) {
            return MenuTexture.MENU_TEXTURE.dialogFocusLeft;
        } else if(this.dialogState == DialogState.BLURRED) {
            return MenuTexture.MENU_TEXTURE.dialogBlurLeft;
        } else {
            return MenuTexture.MENU_TEXTURE.dialogDisabledLeft;
        }
    }

    @Override
    public TextureSample getRightTextureSample() {
        if(this.dialogState == DialogState.FOCUSED) {
            return MenuTexture.MENU_TEXTURE.dialogFocusRight;
        } else if(this.dialogState == DialogState.BLURRED) {
            return MenuTexture.MENU_TEXTURE.dialogBlurRight;
        } else {
            return MenuTexture.MENU_TEXTURE.dialogDisabledRight;
        }
    }

    @Override
    public TextureSample getCenterTextureSample() {
        if(this.dialogState == DialogState.FOCUSED) {
            return MenuTexture.MENU_TEXTURE.dialogFocusCenter;
        } else if(this.dialogState == DialogState.BLURRED) {
            return MenuTexture.MENU_TEXTURE.dialogBlurCenter;
        } else {
            return MenuTexture.MENU_TEXTURE.dialogDisabledCenter;
        }
    }

    @Override
    public TextureSample getTopLeftTextureSample() {
        if(this.dialogState == DialogState.FOCUSED) {
            return MenuTexture.MENU_TEXTURE.dialogFocusTopLeft;
        } else if(this.dialogState == DialogState.BLURRED) {
            return MenuTexture.MENU_TEXTURE.dialogBlurTopLeft;
        } else {
            return MenuTexture.MENU_TEXTURE.dialogDisabledTopLeft;
        }
    }

    @Override
    public TextureSample getTopRightTextureSample() {
        if(this.dialogState == DialogState.FOCUSED) {
            return MenuTexture.MENU_TEXTURE.dialogFocusTopRight;
        } else if(this.dialogState == DialogState.BLURRED) {
            return MenuTexture.MENU_TEXTURE.dialogBlurTopRight;
        } else {
            return MenuTexture.MENU_TEXTURE.dialogDisabledTopRight;
        }
    }

    @Override
    public TextureSample getBottomLeftTextureSample() {
        if(this.dialogState == DialogState.FOCUSED) {
            return MenuTexture.MENU_TEXTURE.dialogFocusBottomLeft;
        } else if(this.dialogState == DialogState.BLURRED) {
            return MenuTexture.MENU_TEXTURE.dialogBlurBottomLeft;
        } else {
            return MenuTexture.MENU_TEXTURE.dialogDisabledBottomLeft;
        }
    }

    @Override
    public TextureSample getBottomRightTextureSample() {
        if(this.dialogState == DialogState.FOCUSED) {
            return MenuTexture.MENU_TEXTURE.dialogFocusBottomRight;
        } else if(this.dialogState == DialogState.BLURRED) {
            return MenuTexture.MENU_TEXTURE.dialogBlurBottomRight;
        } else {
            return MenuTexture.MENU_TEXTURE.dialogDisabledBottomRight;
        }
    }
    
}
