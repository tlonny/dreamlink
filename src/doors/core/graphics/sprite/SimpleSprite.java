package doors.core.graphics.sprite;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.TextureSample;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class SimpleSprite implements ISprite {

    private TextureSample textureSample;
    private Vector2in dimensions;

    public SimpleSprite(TextureSample textureSample, Vector2in dimensions) {
        this.textureSample = textureSample;
        this.dimensions = new Vector2in(dimensions);
    }

    public SimpleSprite(TextureSample textureSample) {
        this(textureSample, textureSample.dimensions);
    }

    @Override
    public void writeSprite(MeshBuffer meshBuffer, Vector2in position) {
        meshBuffer.writeQuad(this.textureSample, position, this.dimensions, Vector3fl.WHITE);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }
    
}
