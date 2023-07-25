package doors.ui.component.mainmenu;

import java.io.File;

import doors.Config;
import doors.graphics.font.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.MenuTexture;
import doors.state.EditGameState;
import doors.state.MainMenuGameState;
import doors.ui.component.IComponent;
import doors.ui.component.IconComponent;
import doors.ui.component.ButtonComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.WindowComponent;
import doors.ui.component.layout.HorizontalAlignment;
import doors.ui.component.layout.HorizontalSpanComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.component.layout.VerticalSpanComponent;
import doors.ui.component.table.TableComponent;
import doors.ui.root.UIRoot;
import doors.utility.BoxedValue;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class MainMenuEditComponent implements IComponent {

    private static int SPACING = 10;
    private static int NUM_ROWS = 10;
    private static int WINDOW_PADDING = 10;
    private static Vector2in ROW_DIMENSIONS = new Vector2in(160, 18);
    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(60, 24);

    private BoxedValue<MainMenuEditLevelTableRow> selectedLevel = new BoxedValue<>();

    private Vector2in originCursor = new Vector2in();
    private WindowComponent windowComponent;

    private IconComponent backgroundComponent = new IconComponent(MenuTexture.MENU_TEXTURE.background);
    private TableComponent<MainMenuEditLevelTableRow> tableComponent = new TableComponent<>(NUM_ROWS, ROW_DIMENSIONS);
    private ButtonComponent editButtonComponent = new ButtonComponent(
        BUTTON_DIMENSIONS,
        new TextComponent("Edit", FontDecoration.NORMAL, Vector3fl.BLACK),
        this::gotoEdit
    );

    private ButtonComponent quitButtonComponent = new ButtonComponent(
        BUTTON_DIMENSIONS,
        new TextComponent("Back", FontDecoration.NORMAL, Vector3fl.BLACK),
        this::gotoRootMenu
    );

    private VerticalSpanComponent layoutSpanComponent = new VerticalSpanComponent(
        HorizontalAlignment.LEFT, 
        SPACING
    );

    private HorizontalSpanComponent buttonSpanComponent = new HorizontalSpanComponent(SPACING);

    public MainMenuEditComponent() {

        this.layoutSpanComponent.components.add(
            new TextComponent("Select level to edit:", FontDecoration.NORMAL, Vector3fl.BLACK)
        );

        this.layoutSpanComponent.components.add(this.tableComponent);
        this.layoutSpanComponent.components.add(this.buttonSpanComponent);

        this.buttonSpanComponent.components.add(this.editButtonComponent);
        this.buttonSpanComponent.components.add(this.quitButtonComponent);

        this.windowComponent = new WindowComponent(
            MenuTexture.MENU_TEXTURE.iconFolder,
            "Edit Menu",
            new PaddingComponent(this.layoutSpanComponent, WINDOW_PADDING)
        );
    }

    public void setup() {
        var directory = new File(Config.EDIT_LEVEL_PATH);
        for(var file : directory.listFiles(File::isDirectory)) {
            var row = new MainMenuEditLevelTableRow(this.selectedLevel, file.getName());
            this.tableComponent.rows.add(row);
        }
    }

    @Override
    public Vector2in getDimensions() {
        return this.windowComponent.getDimensions();
    }

    @Override
    public void calculateDimensions() {
        this.windowComponent.calculateDimensions();
        this.backgroundComponent.dimensions.set(Config.RESOLUTION);
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.originCursor.set(Config.RESOLUTION).sub(this.getDimensions()).div(2);
        this.windowComponent.update(this.originCursor, root);
        this.editButtonComponent.isDisabled = this.selectedLevel.value == null;
    }

    private void gotoRootMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.gotoRootMenu();
    }

    private void gotoEdit() {
        var level = this.selectedLevel.value.content.text;
        EditGameState.EDIT_GAME_STATE.use(level);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        this.backgroundComponent.writeUIComponent(spriteBatch);
        this.windowComponent.writeUIComponent(spriteBatch);
    }
    
}
