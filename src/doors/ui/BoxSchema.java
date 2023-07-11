package doors.ui;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.TextureSample;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.Vector2in;

public class BoxSchema {

    public static BoxSchema WINDOW = new BoxSchema(
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

    public static BoxSchema BUTTON = new BoxSchema(
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

    public static BoxSchema BUTTON_PRESSED = new BoxSchema(
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

    public static BoxSchema BUTTON_DISABLED = new BoxSchema(
        UITextureAtlas.BUTTON_DISABLED_TOP_LEFT,
        UITextureAtlas.BUTTON_DISABLED_LEFT,
        UITextureAtlas.BUTTON_DISABLED_BOTTOM_LEFT,
        UITextureAtlas.BUTTON_DISABLED_TOP,
        UITextureAtlas.BUTTON_DISABLED_CENTER,
        UITextureAtlas.BUTTON_DISABLED_BOTTOM,
        UITextureAtlas.BUTTON_DISABLED_TOP_RIGHT,
        UITextureAtlas.BUTTON_DISABLED_RIGHT,
        UITextureAtlas.BUTTON_DISABLED_BOTTOM_RIGHT
    );

    public static BoxSchema DIALOG_BLUR = new BoxSchema(
        UITextureAtlas.DIALOG_BLUR_TOP_LEFT,
        UITextureAtlas.DIALOG_BLUR_LEFT,
        UITextureAtlas.DIALOG_BLUR_BOTTOM_LEFT,
        UITextureAtlas.DIALOG_BLUR_TOP,
        UITextureAtlas.DIALOG_BLUR_CENTER,
        UITextureAtlas.DIALOG_BLUR_BOTTOM,
        UITextureAtlas.DIALOG_BLUR_TOP_RIGHT,
        UITextureAtlas.DIALOG_BLUR_RIGHT,
        UITextureAtlas.DIALOG_BLUR_BOTTOM_RIGHT
    );

    public static BoxSchema DIALOG_FOCUS = new BoxSchema(
        UITextureAtlas.DIALOG_FOCUS_TOP_LEFT,
        UITextureAtlas.DIALOG_FOCUS_LEFT,
        UITextureAtlas.DIALOG_FOCUS_BOTTOM_LEFT,
        UITextureAtlas.DIALOG_FOCUS_TOP,
        UITextureAtlas.DIALOG_FOCUS_CENTER,
        UITextureAtlas.DIALOG_FOCUS_BOTTOM,
        UITextureAtlas.DIALOG_FOCUS_TOP_RIGHT,
        UITextureAtlas.DIALOG_FOCUS_RIGHT,
        UITextureAtlas.DIALOG_FOCUS_BOTTOM_RIGHT
    );

    public static BoxSchema DIALOG_DISABLED = new BoxSchema(
        UITextureAtlas.DIALOG_DISABLED_TOP_LEFT,
        UITextureAtlas.DIALOG_DISABLED_LEFT,
        UITextureAtlas.DIALOG_DISABLED_BOTTOM_LEFT,
        UITextureAtlas.DIALOG_DISABLED_TOP,
        UITextureAtlas.DIALOG_DISABLED_CENTER,
        UITextureAtlas.DIALOG_DISABLED_BOTTOM,
        UITextureAtlas.DIALOG_DISABLED_TOP_RIGHT,
        UITextureAtlas.DIALOG_DISABLED_RIGHT,
        UITextureAtlas.DIALOG_DISABLED_BOTTOM_RIGHT
    );


    public static BoxSchema BORDER = new BoxSchema(
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
            MeshBuffer meshBuffer,
            Vector2in position, 
            Vector2in dimensions, 
            boolean renderTop,
            boolean renderBottom,
            boolean renderLeft,
            boolean renderRight
    ) {
        var positionCursor = new Vector2in();
        var dimensionsCursor = new Vector2in();

        meshBuffer.writeQuad(
            this.centerTextureSample,
            positionCursor.set(position),
            dimensionsCursor.set(dimensions),
            Vector3fl.WHITE
        );

        if(renderTop) {
            var topDimensions = this.topTextureSample.dimensions;
            meshBuffer.writeQuad(
                this.topTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(dimensions.x, topDimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderBottom) {
            var bottomDimensions = this.bottomTextureSample.dimensions;
            meshBuffer.writeQuad(
                this.bottomTextureSample,
                positionCursor.set(position).add(0, dimensions.y - bottomDimensions.y),
                dimensionsCursor.set(dimensions.x, bottomDimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderLeft) {
            var leftDimensions = this.leftTextureSample.dimensions;
            meshBuffer.writeQuad(
                this.leftTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(leftDimensions.x, dimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderRight) {
            var rightDimensions = this.rightTextureSample.dimensions;
            meshBuffer.writeQuad(
                this.rightTextureSample,
                positionCursor.set(position).add(dimensions.x - rightDimensions.x, 0),
                dimensionsCursor.set(rightDimensions.x, dimensions.y),
                Vector3fl.WHITE
            );
        }

        if(renderTop && renderLeft) {
            var topLeftDimensions = this.topLeftTextureSample.dimensions;
            meshBuffer.writeQuad(
                this.topLeftTextureSample,
                positionCursor.set(position),
                dimensionsCursor.set(topLeftDimensions),
                Vector3fl.WHITE
            );
        }

        if(renderTop && renderRight) {
            var topRightDimensions = this.topRightTextureSample.dimensions;
            meshBuffer.writeQuad(
                this.topRightTextureSample,
                positionCursor.set(position).add(dimensions.x - topRightDimensions.x, 0),
                dimensionsCursor.set(topRightDimensions),
                Vector3fl.WHITE
            );
        }

        if(renderBottom && renderLeft) {
            var bottomLeftDimensions = this.bottomLeftTextureSample.dimensions;
            meshBuffer.writeQuad(
                this.bottomLeftTextureSample,
                positionCursor.set(position).add(0, dimensions.y - bottomLeftDimensions.y),
                dimensionsCursor.set(bottomLeftDimensions),
                Vector3fl.WHITE
            );
        }

        if(renderBottom && renderRight) {
            var bottomRightDimensions = this.bottomRightTextureSample.dimensions;
            meshBuffer.writeQuad(
                this.bottomRightTextureSample,
                positionCursor.set(position).add(dimensions).sub(bottomRightDimensions),
                dimensionsCursor.set(bottomRightDimensions),
                Vector3fl.WHITE
            );
        }
    }

    public void writeBox(MeshBuffer meshBuffer, Vector2in position, Vector2in dimensions) {
        this.writeBox(meshBuffer, position, dimensions, true, true, true, true);
    }

}
