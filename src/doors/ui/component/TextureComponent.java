package doors.ui.component;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.TextureSample;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class TextureComponent implements IExplicitDimensions {

    public TextureSample textureSample;
    public Vector2in position = new Vector2in();

    private Vector2in dimensions = new Vector2in();

    public TextureComponent(TextureSample textureSample, Vector2in dimensions) {
        this.textureSample = textureSample;
        this.dimensions.set(dimensions);
    }

    public TextureComponent(TextureSample textureSample) {
        this(textureSample, textureSample.dimensions);
    }

    public TextureComponent(Vector2in dimensions) {
        this.dimensions.set(dimensions);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {

    }

    @Override
    public void setDimensions(int x, int y) {
        this.dimensions.set(x, y);
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        if(this.textureSample == null) {
            return;
        }
        spriteBatch.pushSprite(
            this.textureSample,
            this.position,
            this.dimensions,
            SpriteBatchHeight.UI_NORMAL,
            Vector3fl.WHITE
        );
    }

    
}
