package doors.ui.component;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.TextureSample;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class IconComponent implements IComponent {

    public TextureSample textureSample;
    public Vector2in dimensions = new Vector2in();
    public Vector2in position = new Vector2in();

    public IconComponent(TextureSample textureSample, Vector2in dimensions) {
        this.textureSample = textureSample;
        this.dimensions.set(dimensions);
    }

    public IconComponent(TextureSample textureSample) {
        this(textureSample, textureSample.dimensions);
    }

    public IconComponent(Vector2in dimensions) {
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
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        if(this.textureSample == null) {
            return;
        }
        spriteBatch.writeSprite(
            this.textureSample,
            this.position,
            this.dimensions,
            SpriteBatchHeight.UI_NORMAL,
            Vector3fl.WHITE
        );
    }

    
}
