package doors.ui.component.window;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.text.FontDecoration;
import doors.graphics.texture.sample.MenuTextureSample;
import doors.graphics.texture.sample.TextureSample;
import doors.ui.component.IComponent;
import doors.ui.component.IconComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.BackgroundComponent;
import doors.ui.component.layout.RowComponent;
import doors.ui.component.layout.alignment.HorizontalAlignment;
import doors.ui.component.layout.alignment.VerticalAlignment;
import doors.ui.component.layout.box.BoxComponent;
import doors.ui.component.layout.box.GrowDimension;
import doors.ui.component.layout.box.WrapDimension;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class WindowTitleComponent implements IComponent {

    private static int TITLE_LEFT_PADDING = 5;
    private static int TITLE_VERTICAL_PADDING = 2;
    private static int TITLE_SPACING = 5;

    private BackgroundComponent backgroundComponent;

    public WindowTitleComponent(TextureSample icon, String title) {
        var titleComponent = new TextComponent(title, FontDecoration.UNDERLINE, Vector3fl.WHITE);
        var iconComponent = new IconComponent(icon);

        var layoutComponent = new RowComponent(TITLE_SPACING);
        layoutComponent.children.add(iconComponent);
        layoutComponent.children.add(titleComponent);

        var paddingComponent = new PaddingComponent(
            layoutComponent, 
            TITLE_VERTICAL_PADDING,
            TITLE_VERTICAL_PADDING,
            TITLE_LEFT_PADDING,
            0
        );

        var boxComponent = new BoxComponent(
            paddingComponent,
            new GrowDimension(),
            new WrapDimension(),
            HorizontalAlignment.LEFT,
            VerticalAlignment.CENTER
        );

        this.backgroundComponent = new BackgroundComponent(boxComponent, MenuTextureSample.HIGHLIGHT);
    }

    @Override
    public Vector2in getDimensions() {
        return this.backgroundComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.backgroundComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.backgroundComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.backgroundComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.backgroundComponent.calculatePosition(origin);
    }

    @Override
    public void update(UIRoot root) {
        this.backgroundComponent.update(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.backgroundComponent.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
