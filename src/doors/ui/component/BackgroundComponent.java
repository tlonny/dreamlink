package doors.ui.component;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.TextureSample;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class BackgroundComponent implements IComponent {

    public TextureSample textureSample;
    public IComponent content;

    public BackgroundComponent(IComponent content, TextureSample textureSample) {
        this.content = content;
        this.textureSample = textureSample;
    }

    @Override
    public Vector2in getDimensions() {
        return this.content.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.content.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.content.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.content.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.content.calculatePosition(origin);
    }

    @Override
    public void update(UIRoot root) {
        this.content.update(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        if(this.textureSample != null) {
            spriteBatch.pushSprite(
                this.textureSample,
                this.getPosition(),
                this.getDimensions(),
                SpriteBatchHeight.UI_NORMAL,
                Vector3fl.WHITE
            );
        }
        this.content.writeComponentToSpriteBatch(spriteBatch);
    }

    
}
