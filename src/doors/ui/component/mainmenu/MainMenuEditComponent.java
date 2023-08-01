package doors.ui.component.mainmenu;

import java.io.File;

import doors.Config;
import doors.graphics.text.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.MenuTexture;
import doors.state.EditGameState;
import doors.state.MainMenuGameState;
import doors.ui.component.IComponent;
import doors.ui.component.ButtonComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.layout.RowComponent;
import doors.ui.component.layout.box.BoxComponent;
import doors.ui.component.layout.box.FixedDimension;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.component.layout.ColumnComponent;
import doors.ui.component.table.TableComponent;
import doors.ui.component.window.WindowComponent;
import doors.ui.root.UIRoot;
import doors.utility.BoxedValue;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class MainMenuEditComponent implements IComponent {

    private static int SPACING = 10;
    private static int NUM_ROWS = 10;
    private static int ROW_HEIGHT = 18;
    private static int WINDOW_PADDING = 10;
    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(60, 24);

    private BoxedValue<MainMenuEditLevelTableRow> selectedLevel = new BoxedValue<>();

    private Vector2in originCursor = new Vector2in();
    private WindowComponent windowComponent;
    private TableComponent<MainMenuEditLevelTableRow> tableComponent;
    private ButtonComponent editButtonComponent;

    public MainMenuEditComponent() {
        var layoutComponent = new ColumnComponent(SPACING);

        var labelComponent = new TextComponent("Select level to edit:", FontDecoration.NORMAL, Vector3fl.BLACK);
        layoutComponent.children.add(labelComponent);

        this.tableComponent = new TableComponent<>(NUM_ROWS, ROW_HEIGHT);
        layoutComponent.children.add(this.tableComponent);

        var buttonSpan = new RowComponent(SPACING);
        this.editButtonComponent = this.createButton("Edit", this::gotoEdit);
        buttonSpan.children.add(this.editButtonComponent);
        var quitButton = this.createButton("Back", this::gotoRootMenu);
        buttonSpan.children.add(quitButton);
        layoutComponent.children.add(buttonSpan);

        this.windowComponent = new WindowComponent(
            MenuTexture.MENU_TEXTURE.iconFolder,
            "Edit Menu",
            new PaddingComponent(layoutComponent, WINDOW_PADDING)
        );
    }

    private ButtonComponent createButton(String text, IAction0 onClick) {
        var boxComponent = new BoxComponent(
            new TextComponent(text, FontDecoration.NORMAL, Vector3fl.BLACK),
            new FixedDimension(BUTTON_DIMENSIONS.x),
            new FixedDimension(BUTTON_DIMENSIONS.y)
        );
        return new ButtonComponent(boxComponent, onClick);
    }

    public void readLevels() {
        this.tableComponent.getRows().clear();
        var directory = new File(Config.CONFIG.getWorkspacePath());
        for(var file : directory.listFiles(File::isDirectory)) {
            var row = new MainMenuEditLevelTableRow(this.selectedLevel, file.getName());
            this.tableComponent.getRows().add(row);
        }
    }

    @Override
    public Vector2in getDimensions() {
        return this.windowComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return Vector2in.ZERO;
    }

    @Override
    public void calculateDimensions() {
        this.windowComponent.calculateDimensions();
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.originCursor.set(Config.CONFIG.getResolution()).sub(this.getDimensions()).div(2);
        this.windowComponent.calculatePosition(this.originCursor);
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.windowComponent.adjustDimensions(this.getDimensions());
    }

    @Override
    public void update(UIRoot root) {
        this.windowComponent.update(root);
    }

    private void gotoRootMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.gotoRootMenu();
    }

    private void gotoEdit() {
        var levelName = this.selectedLevel.value.getLevelName();
        EditGameState.EDIT_GAME_STATE.use(levelName);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.windowComponent.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
