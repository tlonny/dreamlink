package doors.graphics.ui.border;

import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.sample.TextureSample;
import doors.graphics.spritebatch.SpriteBatch;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public abstract class AbstractBorderSchema {

    private Vector2in positionCursor = new Vector2in();
    private Vector2in dimensionsCursor = new Vector2in();

    public void writeMenuTemplateToSpriteBatch(
        SpriteBatch spriteBatch,
        Vector2in position,
        Vector2in dimensions,
        SpriteBatchHeight height,
        boolean renderTop,
        boolean renderBottom,
        boolean renderLeft,
        boolean renderRight
    ) {
        // Center Block
        var centerTextureSample = this.getCenterTextureSample();
        this.positionCursor.set(position);
        this.dimensionsCursor.set(dimensions);
        spriteBatch.pushSprite(centerTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);

        // Top Block
        if(renderTop) {
            var topTextureSample = this.getTopTextureSample();
            this.positionCursor.set(position);
            this.dimensionsCursor.set(dimensions.x, topTextureSample.dimensions.y);
            spriteBatch.pushSprite(topTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);
        }

        // Bottom Block
        if(renderBottom) {
            var bottomTextureSample = this.getBottomTextureSample();
            this.positionCursor.set(position).add(0, dimensions.y - bottomTextureSample.dimensions.y);
            this.dimensionsCursor.set(dimensions.x, bottomTextureSample.dimensions.y);
            spriteBatch.pushSprite(bottomTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);
        }

        // Left Block
        if(renderLeft) {
            var leftTextureSample = this.getLeftTextureSample();
            this.positionCursor.set(position);
            this.dimensionsCursor.set(leftTextureSample.dimensions.x, dimensions.y);
            spriteBatch.pushSprite(leftTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);
        }
        
        // Right Block
        if(renderRight) {
            var rightTextureSample = this.getRightTextureSample();
            this.positionCursor.set(position).add(dimensions.x - rightTextureSample.dimensions.x, 0);
            this.dimensionsCursor.set(rightTextureSample.dimensions.x, dimensions.y);
            spriteBatch.pushSprite(rightTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);
        }

        // Top Left Block
        if(renderTop && renderLeft) {
            var topLeftTextureSample = this.getTopLeftTextureSample();
            this.positionCursor.set(position);
            this.dimensionsCursor.set(topLeftTextureSample.dimensions);
            spriteBatch.pushSprite(topLeftTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);
        }

        // Top Right Block
        if(renderTop && renderRight) {
            var topRightTextureSample = this.getTopRightTextureSample();
            this.positionCursor.set(position).add(dimensions.x - topRightTextureSample.dimensions.x, 0);
            this.dimensionsCursor.set(topRightTextureSample.dimensions);
            spriteBatch.pushSprite(topRightTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);
        }

        // Bottom Left Block
        if(renderBottom && renderLeft) {
            var bottomLeftTextureSample = this.getBottomLeftTextureSample();
            this.positionCursor.set(position).add(0, dimensions.y - bottomLeftTextureSample.dimensions.y);
            this.dimensionsCursor.set(bottomLeftTextureSample.dimensions);
            spriteBatch.pushSprite(bottomLeftTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);
        }

        // Bottom Right Block
        if(renderBottom && renderRight) {
            var bottomRightTextureSample = this.getBottomRightTextureSample();
            this.positionCursor.set(position).add(dimensions).sub(bottomRightTextureSample.dimensions);
            this.dimensionsCursor.set(bottomRightTextureSample.dimensions);
            spriteBatch.pushSprite(bottomRightTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);
        }
    }

    public void writeMenuTemplateToSpriteBatch(
        SpriteBatch spriteBatch,
        Vector2in position,
        Vector2in dimensions,
        SpriteBatchHeight height
    ) {
        this.writeMenuTemplateToSpriteBatch(spriteBatch, position, dimensions, height, true, true, true, true);
    }

    public abstract TextureSample getTopTextureSample();

    public abstract TextureSample getBottomTextureSample();

    public abstract TextureSample getLeftTextureSample();

    public abstract TextureSample getRightTextureSample();

    public abstract TextureSample getCenterTextureSample();

    public abstract TextureSample getTopLeftTextureSample();

    public abstract TextureSample getTopRightTextureSample();

    public abstract TextureSample getBottomLeftTextureSample();

    public abstract TextureSample getBottomRightTextureSample();
}
