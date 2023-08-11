package doors.ui.component.window;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.sample.TextureSample;
import doors.ui.component.IComponent;
import doors.ui.component.border.WindowBorderComponent;
import doors.ui.component.layout.ColumnComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class WindowComponent implements IComponent {

    private static int SPACING = 3;

    public WindowTitleComponent titleComponent;
    public IComponent contentComponent;

    private ColumnComponent layoutComponent = new ColumnComponent(SPACING);
    private WindowBorderComponent borderComponent = new WindowBorderComponent(this.layoutComponent);

    public WindowComponent(TextureSample icon, String title, IComponent content) {
        this.titleComponent = new WindowTitleComponent(icon, title);
        this.contentComponent = content;
        this.layoutComponent.children.add(this.titleComponent);
        this.layoutComponent.children.add(this.contentComponent);
    }

    @Override
    public Vector2in getDimensions() {
        return this.borderComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.borderComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.borderComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.borderComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.borderComponent.calculatePosition(origin);
    }

    @Override
    public void update(UIRoot root) {
        this.borderComponent.update(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.borderComponent.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
