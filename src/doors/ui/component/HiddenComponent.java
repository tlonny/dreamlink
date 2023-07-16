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
    public void calculateDimensions() {
        if(this.isHidden) {
            this.dimensions.set(0);
        }
        this.content.calculateDimensions();
        this.dimensions.set(this.content.getDimensions());
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        if(this.isHidden) {
            return;
        }
        this.content.update(origin, root);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        if(this.isHidden) {
            return;
        }
        this.content.writeUIComponent(spriteBatch);
    }
    
}
