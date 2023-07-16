package doors.graphics.template.menu;

import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.TextureSample;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public abstract class AbstractMenuTemplate {

    private Vector2in positionCursor = new Vector2in();
    private Vector2in dimensionsCursor = new Vector2in();

    public void writeMenuTemplate(
        SpriteBatch spriteBatch,
        Vector2in position,
        Vector2in dimensions,
        SpriteBatchHeight height
    ) {
        // Center Block
        var centerTextureSample = this.getCenterTextureSample();
        this.positionCursor.set(position);
        this.dimensionsCursor.set(dimensions);
        spriteBatch.writeSprite(centerTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);

        // Top Block
        var topTextureSample = this.getTopTextureSample();
        this.positionCursor.set(position);
        this.dimensionsCursor.set(dimensions.x, topTextureSample.dimensions.y);
        spriteBatch.writeSprite(topTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);

        // Bottom Block
        var bottomTextureSample = this.getBottomTextureSample();
        this.positionCursor.set(position).add(0, dimensions.y - bottomTextureSample.dimensions.y);
        this.dimensionsCursor.set(dimensions.x, bottomTextureSample.dimensions.y);
        spriteBatch.writeSprite(bottomTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);

        // Left Block
        var leftTextureSample = this.getLeftTextureSample();
        this.positionCursor.set(position);
        this.dimensionsCursor.set(leftTextureSample.dimensions.x, dimensions.y);
        spriteBatch.writeSprite(leftTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);
        
        // Right Block
        var rightTextureSample = this.getRightTextureSample();
        this.positionCursor.set(position).add(dimensions.x - rightTextureSample.dimensions.x, 0);
        this.dimensionsCursor.set(rightTextureSample.dimensions.x, dimensions.y);
        spriteBatch.writeSprite(rightTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);

        // Top Left Block
        var topLeftTextureSample = this.getTopLeftTextureSample();
        this.positionCursor.set(position);
        this.dimensionsCursor.set(topLeftTextureSample.dimensions);
        spriteBatch.writeSprite(topLeftTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);

        // Top Right Block
        var topRightTextureSample = this.getTopRightTextureSample();
        this.positionCursor.set(position).add(dimensions.x - topRightTextureSample.dimensions.x, 0);
        this.dimensionsCursor.set(topRightTextureSample.dimensions);
        spriteBatch.writeSprite(topRightTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);

        // Bottom Left Block
        var bottomLeftTextureSample = this.getBottomLeftTextureSample();
        this.positionCursor.set(position).add(0, dimensions.y - bottomLeftTextureSample.dimensions.y);
        this.dimensionsCursor.set(bottomLeftTextureSample.dimensions);
        spriteBatch.writeSprite(bottomLeftTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);

        // Bottom Right Block
        var bottomRightTextureSample = this.getBottomRightTextureSample();
        this.positionCursor.set(position).add(dimensions).sub(bottomRightTextureSample.dimensions);
        this.dimensionsCursor.set(bottomRightTextureSample.dimensions);
        spriteBatch.writeSprite(bottomRightTextureSample, this.positionCursor, this.dimensionsCursor, height, Vector3fl.WHITE);
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
