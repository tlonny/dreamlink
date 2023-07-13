package doors.menu;

import doors.graphics.mesh.SpriteBatch;
import doors.graphics.texture.MenuTextureAtlas;
import doors.graphics.texture.TextureSample;
import doors.utility.vector.Vector3fl;
import doors.utility.vector.Vector2in;

public class BoxSchema {

    public static BoxSchema WINDOW = new BoxSchema(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.windowTopLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.windowLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.windowBottomLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.windowTop,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.windowCenter,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.windowBottom,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.windowTopRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.windowRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.windowBottomRight
    );

    public static BoxSchema BUTTON = new BoxSchema(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonTopLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonBottomLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonTop,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonCenter,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonBottom,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonTopRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonBottomRight
    );

    public static BoxSchema BUTTON_PRESSED = new BoxSchema(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonPressedTopLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonPressedLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonPressedBottomLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonPressedTop,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonPressedCenter,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonPressedBottom,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonPressedTopRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonPressedRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonPressedBottomRight
    );

    public static BoxSchema BUTTON_DISABLED = new BoxSchema(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonDisabledTopLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonDisabledLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonDisabledBottomLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonDisabledTop,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonDisabledCenter,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonDisabledBottom,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonDisabledTopRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonDisabledRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.buttonDisabledBottomRight
    );

    public static BoxSchema DIALOG_BLUR = new BoxSchema(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogBlurTopLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogBlurLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogBlurBottomLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogBlurTop,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogBlurCenter,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogBlurBottom,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogBlurTopRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogBlurRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogBlurBottomRight
    );

    public static BoxSchema DIALOG_FOCUS = new BoxSchema(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogFocusTopLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogFocusLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogFocusBottomLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogFocusTop,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogFocusCenter,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogFocusBottom,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogFocusTopRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogFocusRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogFocusBottomRight
    );

    public static BoxSchema DIALOG_DISABLED = new BoxSchema(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogDisabledTopLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogDisabledLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogDisabledBottomLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogDisabledTop,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogDisabledCenter,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogDisabledBottom,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogDisabledTopRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogDisabledRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.dialogDisabledBottomRight
    );

    public static BoxSchema BORDER = new BoxSchema(
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.borderTopLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.borderLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.borderBottomLeft,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.borderTop,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.borderCenter,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.borderBottom,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.borderTopRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.borderRight,
        MenuTextureAtlas.MENU_TEXTURE_ATLAS.borderBottomRight
    );

    private TextureSample topLeftTextureSample;
    private TextureSample leftTextureSample;
    private TextureSample bottomLeftTextureSample;

    private TextureSample topTextureSample;
    private TextureSample centerTextureSample;
    private TextureSample bottomTextureSample;

    private TextureSample topRightTextureSample;
    private TextureSample rightTextureSample;
    private TextureSample bottomRightTextureSample;

    public BoxSchema(
        TextureSample topLeftTextureSample, 
        TextureSample leftTextureSample, 
        TextureSample bottomLeftTextureSample, 
        TextureSample topTextureSample, 
        TextureSample centerTextureSample, 
        TextureSample bottomTextureSample, 
        TextureSample topRightTextureSample, 
        TextureSample rightTextureSample, 
        TextureSample bottomRightTextureSample
    ) {
        this.topLeftTextureSample = topLeftTextureSample;
        this.leftTextureSample = leftTextureSample;
        this.bottomLeftTextureSample = bottomLeftTextureSample;
        this.topTextureSample = topTextureSample;
        this.centerTextureSample = centerTextureSample;
        this.bottomTextureSample = bottomTextureSample;
        this.topRightTextureSample = topRightTextureSample;
        this.rightTextureSample = rightTextureSample;
        this.bottomRightTextureSample = bottomRightTextureSample;
    }

    public void writeBox(
            Vector2in position, 
            Vector2in dimensions, 
            boolean renderTop,
            boolean renderBottom,
            boolean renderLeft,
            boolean renderRight
    ) {
        var positionCursor = new Vector2in();
        var dimensionsCursor = new Vector2in();

        SpriteBatch.SPRITE_BATCH.writeSprite(
            this.centerTextureSample,
            positionCursor.set(position),
            dimensionsCursor.set(dimensions),
            Vector3fl.WHITE
        );

        if(renderTop) {
            var topDimensions = this.topTextureSample.dimensions;
            SpriteBatch.SPRITE_BATCH.writeSprite(
                this.topTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(dimensions.x, topDimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderBottom) {
            var bottomDimensions = this.bottomTextureSample.dimensions;
            SpriteBatch.SPRITE_BATCH.writeSprite(
                this.bottomTextureSample,
                positionCursor.set(position).add(0, dimensions.y - bottomDimensions.y),
                dimensionsCursor.set(dimensions.x, bottomDimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderLeft) {
            var leftDimensions = this.leftTextureSample.dimensions;
            SpriteBatch.SPRITE_BATCH.writeSprite(
                this.leftTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(leftDimensions.x, dimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderRight) {
            var rightDimensions = this.rightTextureSample.dimensions;
            SpriteBatch.SPRITE_BATCH.writeSprite(
                this.rightTextureSample,
                positionCursor.set(position).add(dimensions.x - rightDimensions.x, 0),
                dimensionsCursor.set(rightDimensions.x, dimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderTop && renderLeft) {
            var topLeftDimensions = this.topLeftTextureSample.dimensions;
            SpriteBatch.SPRITE_BATCH.writeSprite(
                this.topLeftTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(topLeftDimensions),
                Vector3fl.WHITE
            );
        }

        if(renderTop && renderRight) {
            var topRightDimensions = this.topRightTextureSample.dimensions;
            SpriteBatch.SPRITE_BATCH.writeSprite(
                this.topRightTextureSample,
                positionCursor.set(position).add(dimensions.x - topRightDimensions.x, 0),
                dimensionsCursor.set(topRightDimensions),
                Vector3fl.WHITE
            );
        }

        if(renderBottom && renderLeft) {
            var bottomLeftDimensions = this.bottomLeftTextureSample.dimensions;
            SpriteBatch.SPRITE_BATCH.writeSprite(
                this.bottomLeftTextureSample,
                positionCursor.set(position).add(0, dimensions.y - bottomLeftDimensions.y),
                dimensionsCursor.set(bottomLeftDimensions),
                Vector3fl.WHITE
            );
        }

        if(renderBottom && renderRight) {
            var bottomRightDimensions = this.bottomRightTextureSample.dimensions;
            SpriteBatch.SPRITE_BATCH.writeSprite(
                this.bottomRightTextureSample,
                positionCursor.set(position).add(dimensions).sub(bottomRightDimensions),
                dimensionsCursor.set(bottomRightDimensions),
                Vector3fl.WHITE
            );
        }
    }

    public void writeBox(Vector2in position, Vector2in dimensions) {
        this.writeBox(position, dimensions, true, true, true, true);
    }

}
