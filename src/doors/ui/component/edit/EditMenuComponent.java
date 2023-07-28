package doors.ui.component.edit;

import doors.Config;
import doors.graphics.text.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.MenuTexture;
import doors.level.Level;
import doors.state.MainMenuGameState;
import doors.ui.component.ButtonComponent;
import doors.ui.component.IComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.layout.RowComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.component.layout.BoxComponent;
import doors.ui.component.layout.ColumnComponent;
import doors.ui.component.table.TableComponent;
import doors.ui.component.window.WindowComponent;
import doors.ui.root.UIRoot;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class EditMenuComponent implements IComponent {

    private static int SPACING = 10;
    private static int NUM_ROWS = 10;
    private static int ROW_HEIGHT = 18;
    private static int WINDOW_PADDING = 10;
    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(60, 24);

    private Vector2in originCursor = new Vector2in();
    private WindowComponent windowComponent;
    private TableComponent<EditBlockTableRowComponent> tableComponent;

    public EditMenuComponent() {
        var layoutComponent = new ColumnComponent(SPACING);

        var labelComponent = new TextComponent("Select blocks to place:", FontDecoration.NORMAL, Vector3fl.BLACK);
        layoutComponent.children.add(labelComponent);

        this.tableComponent = new TableComponent<>(NUM_ROWS, ROW_HEIGHT);
        layoutComponent.children.add(tableComponent);

        var buttonSpan = new RowComponent(SPACING);
        var saveButton = this.createButton("Save", null);
        buttonSpan.children.add(saveButton);
        var quitButton = this.createButton("Quit", this::gotoMainMenu);
        buttonSpan.children.add(quitButton);

        layoutComponent.children.add(buttonSpan);

        this.windowComponent = new WindowComponent(
            MenuTexture.MENU_TEXTURE.iconFolder, 
            "Choose blocks:",
            new PaddingComponent(layoutComponent, WINDOW_PADDING)
        );
    }

    private ButtonComponent createButton(String text, IAction0 onClick) {
        var boxComponent = new BoxComponent(
            BUTTON_DIMENSIONS,
            BUTTON_DIMENSIONS
        );

        boxComponent.child = new TextComponent(text, FontDecoration.NORMAL, Vector3fl.BLACK);
        return new ButtonComponent(boxComponent, onClick);
    }

    public void setLevel(Level level) {
        this.tableComponent.getRows().clear();
        for(var block : level.blockMap.getBlocks()) {
            this.tableComponent.getRows().add(new EditBlockTableRowComponent(block));
        }
    }

    @Override
    public Vector2in getDimensions() {
        return this.windowComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.windowComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.windowComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.windowComponent.adjustDimensions(this.getDimensions());
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.originCursor.set(Config.RESOLUTION).sub(this.getDimensions()).div(2);
        this.windowComponent.calculatePosition(this.originCursor);
    }

    @Override
    public void update(UIRoot root) {
        this.windowComponent.update(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.windowComponent.writeComponentToSpriteBatch(spriteBatch);
    }

    private void gotoMainMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.gotoRootMenu();
        MainMenuGameState.MAIN_MENU_GAME_STATE.use();
    }
    
}
