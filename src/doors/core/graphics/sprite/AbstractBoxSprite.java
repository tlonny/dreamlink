package doors.core.graphics.sprite;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.TextureSample;
import doors.utility.vector.Vector2fl;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public abstract class AbstractBoxSprite implements ISprite {

    private Vector2in dimensions;

    public AbstractBoxSprite(Vector2in dimensions) {
        this.dimensions = new Vector2in(dimensions);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }
    
    @Override
    public void writeSprite(
            MeshBuffer meshBuffer,
            Vector2in position
    ) {
        var positionCursor = new Vector2fl();
        var dimensionsCursor = new Vector2fl();

        meshBuffer.writeQuad(
            this.getCenterTextureSample(),
            positionCursor.set(position),
            dimensionsCursor.set(this.dimensions),
            Vector3fl.WHITE
        );

        var topTextureSample = this.getTopTextureSample();
        meshBuffer.writeQuad(
            topTextureSample,
            positionCursor.set(position),
            dimensionsCursor.set(this.dimensions.x, topTextureSample.dimensions.y),
            Vector3fl.WHITE
        );

        var bottomTextureSample = this.getBottomTextureSample();
        meshBuffer.writeQuad(
            bottomTextureSample,
            positionCursor.set(position).add(0, this.dimensions.y - bottomTextureSample.dimensions.y),
            dimensionsCursor.set(this.dimensions.x, bottomTextureSample.dimensions.y),
            Vector3fl.WHITE
        );

        var leftTextureSample = this.getLeftTextureSample();
        meshBuffer.writeQuad(
            leftTextureSample,
            positionCursor.set(position),
            dimensionsCursor.set(leftTextureSample.dimensions.x, this.dimensions.y),
            Vector3fl.WHITE
        );

        var rightTextureSample = this.getRightTextureSample();
        meshBuffer.writeQuad(
            rightTextureSample,
            positionCursor.set(position).add(this.dimensions.x - rightTextureSample.dimensions.x, 0),
            dimensionsCursor.set(rightTextureSample.dimensions.x, this.dimensions.y),
            Vector3fl.WHITE
        );

        var topLeftTextureSample = this.getTopLeftTextureSample();
        meshBuffer.writeQuad(
            topLeftTextureSample,
            positionCursor.set(position),
            dimensionsCursor.set(topLeftTextureSample.dimensions),
            Vector3fl.WHITE
        );

        var topRightTextureSample = this.getTopRightTextureSample();
        meshBuffer.writeQuad(
            topRightTextureSample,
            positionCursor.set(position).add(this.dimensions.x - topRightTextureSample.dimensions.x, 0),
            dimensionsCursor.set(topRightTextureSample.dimensions),
            Vector3fl.WHITE
        );

        var bottomLeftTextureSample = this.getBottomLeftTextureSample();
        meshBuffer.writeQuad(
            bottomLeftTextureSample,
            positionCursor.set(position).add(0, this.dimensions.y - bottomLeftTextureSample.dimensions.y),
            dimensionsCursor.set(bottomLeftTextureSample.dimensions),
            Vector3fl.WHITE
        );

        var bottomRightTextureSample = this.getBottomRightTextureSample();
        meshBuffer.writeQuad(
            bottomRightTextureSample,
            positionCursor.set(position).add(this.dimensions).sub(bottomRightTextureSample.dimensions),
            dimensionsCursor.set(bottomRightTextureSample.dimensions),
            Vector3fl.WHITE
        );
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
