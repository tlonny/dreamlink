package doors.graphics.texture;

import doors.utility.geometry.Vector2fl;
import doors.utility.geometry.Vector2in;

public class TextureSampler {

    private Vector2in dimensions;
    private Vector2in scale;

    public TextureSampler(Vector2in dimensions) {
        this.dimensions = dimensions;
        this.scale = Vector2in.ONE;
    }

    public TextureSampler(Vector2in dimensions, Vector2in scale) {
        this.dimensions = dimensions;
        this.scale = scale;
    }

    public TextureSample createTextureSample(Vector2in position, Vector2in dimensions){
        var textureOffsets = new Vector2fl[] {
            new Vector2fl(position).add(0, 0).mul(this.scale).div(this.dimensions),
            new Vector2fl(position).add(0, dimensions.y).mul(this.scale).div(this.dimensions),
            new Vector2fl(position).add(dimensions.x, dimensions.y).mul(this.scale).div(this.dimensions),
            new Vector2fl(position).add(dimensions.x, 0).mul(this.scale).div(this.dimensions)
        };
        return new TextureSample(textureOffsets, new Vector2in(dimensions).mul(this.scale));
    }

    public TextureSample createTextureSample(Vector2in position){
        return this.createTextureSample(position, Vector2in.ONE);
    }
}
