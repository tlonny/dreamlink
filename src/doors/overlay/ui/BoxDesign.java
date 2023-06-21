package doors.overlay.ui;

import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSample;
import doors.overlay.SpriteBatch;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;

public class BoxDesign {

    public static BoxDesign WINDOW = new BoxDesign(
        UITextureAtlas.WINDOW_TOP_LEFT,
        UITextureAtlas.WINDOW_LEFT,
        UITextureAtlas.WINDOW_BOTTOM_LEFT,
        UITextureAtlas.WINDOW_TOP,
        UITextureAtlas.WINDOW_CENTER,
        UITextureAtlas.WINDOW_BOTTOM,
        UITextureAtlas.WINDOW_TOP_RIGHT,
        UITextureAtlas.WINDOW_RIGHT,
        UITextureAtlas.WINDOW_BOTTOM_RIGHT
    );

    public static BoxDesign DIALOG = new BoxDesign(
        UITextureAtlas.DIALOG_TOP_LEFT,
        UITextureAtlas.DIALOG_LEFT,
        UITextureAtlas.DIALOG_BOTTOM_LEFT,
        UITextureAtlas.DIALOG_TOP,
        UITextureAtlas.DIALOG_CENTER,
        UITextureAtlas.DIALOG_BOTTOM,
        UITextureAtlas.DIALOG_TOP_RIGHT,
        UITextureAtlas.DIALOG_RIGHT,
        UITextureAtlas.DIALOG_BOTTOM_RIGHT
    );

    public static BoxDesign BUTTON = new BoxDesign(
        UITextureAtlas.BUTTON_TOP_LEFT,
        UITextureAtlas.BUTTON_LEFT,
        UITextureAtlas.BUTTON_BOTTOM_LEFT,
        UITextureAtlas.BUTTON_TOP,
        UITextureAtlas.BUTTON_CENTER,
        UITextureAtlas.BUTTON_BOTTOM,
        UITextureAtlas.BUTTON_TOP_RIGHT,
        UITextureAtlas.BUTTON_RIGHT,
        UITextureAtlas.BUTTON_BOTTOM_RIGHT
    );

    public static BoxDesign BUTTON_PRESSED = new BoxDesign(
        UITextureAtlas.BUTTON_PRESSED_TOP_LEFT,
        UITextureAtlas.BUTTON_PRESSED_LEFT,
        UITextureAtlas.BUTTON_PRESSED_BOTTOM_LEFT,
        UITextureAtlas.BUTTON_PRESSED_TOP,
        UITextureAtlas.BUTTON_PRESSED_CENTER,
        UITextureAtlas.BUTTON_PRESSED_BOTTOM,
        UITextureAtlas.BUTTON_PRESSED_TOP_RIGHT,
        UITextureAtlas.BUTTON_PRESSED_RIGHT,
        UITextureAtlas.BUTTON_PRESSED_BOTTOM_RIGHT
    );

    public static BoxDesign BORDER = new BoxDesign(
        UITextureAtlas.BORDER_TOP_LEFT,
        UITextureAtlas.BORDER_LEFT,
        UITextureAtlas.BORDER_BOTTOM_LEFT,
        UITextureAtlas.BORDER_TOP,
        UITextureAtlas.BORDER_CENTER,
        UITextureAtlas.BORDER_BOTTOM,
        UITextureAtlas.BORDER_TOP_RIGHT,
        UITextureAtlas.BORDER_RIGHT,
        UITextureAtlas.BORDER_BOTTOM_RIGHT
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

    public BoxDesign(
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

    public void writeBox(Vector2in position, Vector2in dimensions) {
        this.writeBox(position, dimensions, true, true, true, true);
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
            TextureChannel.UI_TEXTURE_CHANNEL,
            this.centerTextureSample,
            positionCursor.set(position),
            dimensionsCursor.set(dimensions),
            Vector3fl.ONE
        );

        if(renderTop) {
            SpriteBatch.SPRITE_BATCH.writeSprite(
                TextureChannel.UI_TEXTURE_CHANNEL,
                this.topTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(dimensions.x, this.topTextureSample.dimensions.y),
                Vector3fl.ONE
            );
        }

        if(renderBottom) {
            SpriteBatch.SPRITE_BATCH.writeSprite(
                TextureChannel.UI_TEXTURE_CHANNEL,
                this.bottomTextureSample,
                positionCursor.set(position).add(0, dimensions.y - this.bottomTextureSample.dimensions.y),
                dimensionsCursor.set(dimensions.x, this.bottomTextureSample.dimensions.y),
                Vector3fl.ONE
            );
        }

        if(renderLeft) {
            SpriteBatch.SPRITE_BATCH.writeSprite(
                TextureChannel.UI_TEXTURE_CHANNEL,
                this.leftTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(this.leftTextureSample.dimensions.x, dimensions.y),
                Vector3fl.ONE
            );
        }

        if(renderRight) {
            SpriteBatch.SPRITE_BATCH.writeSprite(
                TextureChannel.UI_TEXTURE_CHANNEL,
                this.rightTextureSample,
                positionCursor.set(position).add(dimensions.x - this.rightTextureSample.dimensions.x, 0),
                dimensionsCursor.set(this.rightTextureSample.dimensions.x, dimensions.y),
                Vector3fl.ONE
            );
        }

        if(renderTop && renderLeft) {
            SpriteBatch.SPRITE_BATCH.writeSprite(
                TextureChannel.UI_TEXTURE_CHANNEL,
                this.topLeftTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(this.topLeftTextureSample.dimensions),
                Vector3fl.ONE
            );
        }

        if(renderTop && renderRight) {
            SpriteBatch.SPRITE_BATCH.writeSprite(
                TextureChannel.UI_TEXTURE_CHANNEL,
                this.topRightTextureSample,
                positionCursor.set(position).add(dimensions.x - this.topRightTextureSample.dimensions.x, 0),
                dimensionsCursor.set(this.topRightTextureSample.dimensions),
                Vector3fl.ONE
            );
        }

        if(renderBottom && renderLeft) {
            SpriteBatch.SPRITE_BATCH.writeSprite(
                TextureChannel.UI_TEXTURE_CHANNEL,
                this.bottomLeftTextureSample,
                positionCursor.set(position).add(0, dimensions.y - this.bottomLeftTextureSample.dimensions.y),
                dimensionsCursor.set(this.bottomLeftTextureSample.dimensions),
                Vector3fl.ONE
            );
        }

        if(renderBottom && renderRight) {
            SpriteBatch.SPRITE_BATCH.writeSprite(
                TextureChannel.UI_TEXTURE_CHANNEL,
                this.bottomRightTextureSample,
                positionCursor.set(position).add(dimensions).sub(this.bottomRightTextureSample.dimensions),
                dimensionsCursor.set(this.bottomRightTextureSample.dimensions),
                Vector3fl.ONE
            );
        }

    }

}
