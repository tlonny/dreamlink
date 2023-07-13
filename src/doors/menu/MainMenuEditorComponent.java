package doors.menu;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import doors.core.Config;
import doors.graphics.texture.FontDecoration;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;
import doors.level.LevelCache;
import doors.state.EditorGameState;
import doors.state.MainMenuGameState;

public class MainMenuEditorComponent extends BaseMenuComponent {

    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(60, 24);
    private static Vector2in WINDOW_PADDING = new Vector2in(10, 10);
    private static Vector2in SELECT_DIMENSIONS = new Vector2in(200, 400);
    private static int SPACING = 10;

    private ButtonComponent goButton;
    private ButtonComponent backButton;
    private TextLabelComponent textLabel;
    private SelectableTableComponent<TextLabelComponent> table;

    private TextLabelComponent selectedLevel;

    public MainMenuEditorComponent() {
        super();

        this.textLabel = this.addChild(new TextLabelComponent("Level select:", FontDecoration.NORMAL, Vector3fl.BLACK));

        var levels = this.buildAvailableLevels();
        this.table = this.addChild(new SelectableTableComponent<TextLabelComponent>(levels, SELECT_DIMENSIONS, this::onChange));

        this.goButton = this.addChild(new ButtonComponent(
            new TextLabelComponent("Go", FontDecoration.NORMAL, Vector3fl.BLACK),
            BUTTON_DIMENSIONS,
            this::gotoExplore
        ));

        this.goButton.isDisabled = true;

        this.backButton = this.addChild(new ButtonComponent(
            new TextLabelComponent("Back", FontDecoration.NORMAL, Vector3fl.BLACK),
            BUTTON_DIMENSIONS,
            this::gotoMainMenu
        ));

    }

    private Collection<TextLabelComponent> buildAvailableLevels() {
        var editorDirectory = Paths.get(LevelCache.LEVEL_DIRECTORY_ROOT, "editor").toString();
        var levels = new ArrayList<TextLabelComponent>();
        for(var file : new File(editorDirectory).listFiles()) {
            if(file.isDirectory()) {
                levels.add(new TextLabelComponent(file.getName(), FontDecoration.NORMAL, Vector3fl.BLACK));
            }
        }
        return levels;
    }

    private void gotoMainMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.use();
    }

    private void gotoExplore() {
        EditorGameState.EDITOR_GAME_STATE.use(this.selectedLevel.text);
    }

    private void onChange() {
        for(var entry : this.table.entries) {
            entry.color.set(this.table.selectedEntry == entry ? Vector3fl.WHITE : Vector3fl.BLACK);
        }
        this.selectedLevel = this.table.selectedEntry;
        this.goButton.isDisabled = this.selectedLevel == null;
    }

    @Override
    public void calculateLayout() {
        super.calculateLayout();
        this.textLabel.offset.set(WINDOW_PADDING);

        this.table.offset.set(this.textLabel.offset);
        this.table.offset.y += this.textLabel.dimensions.y + SPACING;

        this.goButton.offset.set(this.table.offset);
        this.goButton.offset.y += this.table.dimensions.y + SPACING;

        this.backButton.offset.set(this.goButton.offset);
        this.backButton.offset.x += this.goButton.dimensions.x + SPACING;

        this.dimensions.set(
            this.table.offset.x + this.table.dimensions.x,
            this.goButton.offset.y + this.goButton.dimensions.y
        ).add(WINDOW_PADDING);

        this.offset.set(Config.RESOLUTION).sub(this.dimensions).div(2);
    }

    @Override
    public void writeUIComponent() {
        BoxSchema.WINDOW.writeBox(this.globalPosition, this.dimensions);
        super.writeUIComponent();
    }

}
