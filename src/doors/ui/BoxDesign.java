package doors.ui;

import doors.Screen;
import doors.core.graphics.sprite.SpriteMeshBufferWriter;
import doors.core.graphics.texture.TextureSample;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.Vector2in;

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

    private SpriteMeshBufferWriter spriteWriter;

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
        this.spriteWriter = new SpriteMeshBufferWriter(Screen.SCREEN.meshBuffer);
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

        this.spriteWriter.writeSprite(
            this.centerTextureSample,
            positionCursor.set(position),
            dimensionsCursor.set(dimensions),
            Vector3fl.WHITE
        );

        if(renderTop) {
            var topDimensions = this.topTextureSample.dimensions;
            this.spriteWriter.writeSprite(
                this.topTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(dimensions.x, topDimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderBottom) {
            var bottomDimensions = this.bottomTextureSample.dimensions;
            this.spriteWriter.writeSprite(
                this.bottomTextureSample,
                positionCursor.set(position).add(0, dimensions.y - bottomDimensions.y),
                dimensionsCursor.set(dimensions.x, bottomDimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderLeft) {
            var leftDimensions = this.leftTextureSample.dimensions;
            this.spriteWriter.writeSprite(
                this.leftTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(leftDimensions.x, dimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderRight) {
            var rightDimensions = this.rightTextureSample.dimensions;
            this.spriteWriter.writeSprite(
                this.rightTextureSample,
                positionCursor.set(position).add(dimensions.x - rightDimensions.x, 0),
                dimensionsCursor.set(rightDimensions.x, dimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderTop && renderLeft) {
            var topLeftDimensions = this.topLeftTextureSample.dimensions;
            this.spriteWriter.writeSprite(
                this.topLeftTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(topLeftDimensions),
                Vector3fl.WHITE
            );
        }

        if(renderTop && renderRight) {
            var topRightDimensions = this.topRightTextureSample.dimensions;
            this.spriteWriter.writeSprite(
                this.topRightTextureSample,
                positionCursor.set(position).add(dimensions.x - topRightDimensions.x, 0),
                dimensionsCursor.set(topRightDimensions),
                Vector3fl.WHITE
            );
        }

        if(renderBottom && renderLeft) {
            var bottomLeftDimensions = this.bottomLeftTextureSample.dimensions;
            this.spriteWriter.writeSprite(
                this.bottomLeftTextureSample,
                positionCursor.set(position).add(0, dimensions.y - bottomLeftDimensions.y),
                dimensionsCursor.set(bottomLeftDimensions),
                Vector3fl.WHITE
            );
        }

        if(renderBottom && renderRight) {
            var bottomRightDimensions = this.bottomRightTextureSample.dimensions;
            this.spriteWriter.writeSprite(
                this.bottomRightTextureSample,
                positionCursor.set(position).add(dimensions).sub(bottomRightDimensions),
                dimensionsCursor.set(bottomRightDimensions),
                Vector3fl.WHITE
            );
        }

    }

}
