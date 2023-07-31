package doors.ui.component;

import doors.graphics.spritebatch.SpriteBatch;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class HiddenComponent implements IComponent {

    private IComponent content;
    public boolean isHidden;
    private Vector2in dimensions = new Vector2in();

    public HiddenComponent(IComponent content, boolean isHidden) {
        this.content = content;
        this.isHidden = isHidden;
    }

    public HiddenComponent(IComponent content) {
        this(content, false);
    }

    @Override
    public Vector2in getDimensions() {
        return this.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.content.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.content.calculateDimensions();
        this.dimensions.set(this.isHidden ? Vector2in.ZERO : this.content.getDimensions());
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
        if(this.isHidden) {
            return;
        }
        this.content.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
