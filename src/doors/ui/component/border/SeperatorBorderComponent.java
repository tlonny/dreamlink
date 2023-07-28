package doors.ui.component.border;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.ui.border.SeperatorBorderSchema;
import doors.ui.component.IComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class SeperatorBorderComponent implements IComponent {

    private static int PADDING = 2;

    private PaddingComponent paddingComponent;

    public SeperatorBorderComponent(IComponent content) {
        this.paddingComponent = new PaddingComponent(content, PADDING);
    }

    public void setContent(IComponent content) {
        this.paddingComponent.content = content;
    }

    @Override
    public Vector2in getDimensions() {
        return this.paddingComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.paddingComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.paddingComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.paddingComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.paddingComponent.calculatePosition(origin);
    }

    @Override
    public void update(UIRoot root) {
        this.paddingComponent.update(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        SeperatorBorderSchema.SCHEMA.writeMenuTemplateToSpriteBatch(spriteBatch, this.getPosition(), this.getDimensions(), SpriteBatchHeight.UI_NORMAL);
        this.paddingComponent.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
