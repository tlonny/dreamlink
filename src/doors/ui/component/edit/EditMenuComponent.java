package doors.ui.component.edit;

import doors.Config;
import doors.graphics.font.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.MenuTexture;
import doors.level.Level;
import doors.ui.component.IComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.WindowComponent;
import doors.ui.component.layout.HorizontalAlignment;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.component.layout.VerticalSpanComponent;
import doors.ui.component.table.TableComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class EditMenuComponent implements IComponent {

    private static int NUM_ROWS = 10;
    private static int SPACING = 10;
    private static int WINDOW_PADDING = 10;
    private static Vector2in ROW_DIMENSIONS = new Vector2in(160, 18);

    private Vector2in position = new Vector2in();

    private TextComponent labelComponent = new TextComponent(
        "Select blocks to place:", FontDecoration.NORMAL, Vector3fl.BLACK
    );

    private TableComponent<EditBlockTableRowComponent> tableComponent = new TableComponent<>(
        NUM_ROWS, ROW_DIMENSIONS
    );

    private VerticalSpanComponent layoutComponent = new VerticalSpanComponent(
        HorizontalAlignment.LEFT, SPACING
    );

    private WindowComponent windowComponent;

    public EditMenuComponent() {
        this.layoutComponent.components.add(this.labelComponent);
        this.layoutComponent.components.add(this.tableComponent);
        this.windowComponent = new WindowComponent(
            MenuTexture.MENU_TEXTURE.iconFolder, 
            "Choose blocks:",
            new PaddingComponent(this.layoutComponent, WINDOW_PADDING)
        );
    }

    public void setLevel(Level level) {
        this.tableComponent.rows.clear();
        for(var block : level.terrain.blockMap.values()) {
            this.tableComponent.rows.add(new EditBlockTableRowComponent(block));
        }
    }

    @Override
    public Vector2in getDimensions() {
        return this.windowComponent.getDimensions();
    }

    @Override
    public void calculateDimensions() {
        this.windowComponent.calculateDimensions();
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(Config.RESOLUTION).sub(this.getDimensions()).div(2);
        this.windowComponent.update(this.position, root);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        this.windowComponent.writeUIComponent(spriteBatch);
    }
    
}
