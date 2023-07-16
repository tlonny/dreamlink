package doors.ui.component;

import doors.graphics.font.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.template.menu.WindowTemplate;
import doors.graphics.texture.MenuTexture;
import doors.graphics.texture.TextureSample;
import doors.ui.component.layout.HorizontalSpanComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class WindowComponent implements IComponent {

    private static int WINDOW_PADDING = 3;
    private static Vector2in TITLE_BAR_PADDING = new Vector2in(5, 2);

    private HorizontalSpanComponent horizontalSpanComponent = new HorizontalSpanComponent(TITLE_BAR_PADDING.x);
    private IconComponent titleBarComponent = new IconComponent(MenuTexture.MENU_TEXTURE.highlight);

    public IconComponent iconComponent;
    public TextComponent titleComponent;
    public IComponent contentComponent;

    private Vector2in dimensions = new Vector2in();
    private Vector2in position = new Vector2in();
    private Vector2in originCursor = new Vector2in();


    public WindowComponent(TextureSample icon, String title, IComponent content) {
        this.iconComponent = new IconComponent(icon);
        this.titleComponent = new TextComponent(title, FontDecoration.UNDERLINE, Vector3fl.WHITE);
        this.horizontalSpanComponent.components.add(this.iconComponent);
        this.horizontalSpanComponent.components.add(this.titleComponent);
        this.contentComponent = content;
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        this.horizontalSpanComponent.calculateDimensions();
        this.contentComponent.calculateDimensions();

        var spanDimensions = this.horizontalSpanComponent.getDimensions();
        var contentDimensions = this.contentComponent.getDimensions();

        this.dimensions.set(WINDOW_PADDING * 2);
        this.dimensions.x += contentDimensions.x;
        this.dimensions.y += spanDimensions.y + contentDimensions.y + TITLE_BAR_PADDING.y * 2 + WINDOW_PADDING;

        this.titleBarComponent.dimensions.set(this.dimensions.x, spanDimensions.y + TITLE_BAR_PADDING.y * 2);
        this.titleBarComponent.dimensions.x -= WINDOW_PADDING * 2;
        this.titleBarComponent.calculateDimensions();
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);

        var spanDimensions = this.horizontalSpanComponent.getDimensions();
        var barDimensions = this.titleBarComponent.getDimensions();

        this.originCursor.set(this.position).add(WINDOW_PADDING);
        this.titleBarComponent.update(this.originCursor, root);

        this.originCursor.x += TITLE_BAR_PADDING.x;
        this.originCursor.y += (barDimensions.y - spanDimensions.y) / 2;
        this.horizontalSpanComponent.update(this.originCursor, root);

        this.originCursor.x = origin.x + WINDOW_PADDING;
        this.originCursor.y = origin.y + WINDOW_PADDING * 2 + barDimensions.y;
        this.contentComponent.update(this.originCursor, root);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        WindowTemplate.WINDOW_TEMPLATE.writeMenuTemplate(spriteBatch, this.position, this.dimensions, SpriteBatchHeight.UI_NORMAL);

        this.titleBarComponent.writeUIComponent(spriteBatch);
        this.horizontalSpanComponent.writeUIComponent(spriteBatch);
        this.contentComponent.writeUIComponent(spriteBatch);
    }
    
}
