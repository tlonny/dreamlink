package doors.ui;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

import doors.core.config.Config;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.sprite.FontDecoration;
import doors.core.ui.BaseUIComponent;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;
import doors.state.MainMenuGameState;

public class MainMenuEditorComponent extends BaseUIComponent {

    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(60, 24);
    private static Vector2in WINDOW_PADDING = new Vector2in(10, 10);
    private static int SPACING = 10;
    private static int MAX_TEXT_LENGTH = 20;

    private ButtonComponent goButton;
    private ButtonComponent backButton;
    private TextLabelComponent textLabel;
    private TextTableComponent table;

    private String selectedLevel;

    public MainMenuEditorComponent() {
        super();

        this.textLabel = this.addChild(new TextLabelComponent("Level select:", FontDecoration.NORMAL, Vector3fl.BLACK));

        var levels = Arrays.asList("fcuk", "you");
        this.table = this.addChild(new TextTableComponent(levels, MAX_TEXT_LENGTH, 10, this::onSelect));

        this.goButton = this.addChild(new ButtonComponent(
            new TextLabelComponent("Go", FontDecoration.NORMAL, Vector3fl.BLACK),
            BUTTON_DIMENSIONS,
            () -> {}
        ));

        this.goButton.isDisabled = true;

        this.backButton = this.addChild(new ButtonComponent(
            new TextLabelComponent("Back", FontDecoration.NORMAL, Vector3fl.BLACK),
            BUTTON_DIMENSIONS,
            this::gotoMainMenu
        ));

    }

    private void gotoMainMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.use();
    }

    private void onSelect(String selectedLevel) {
        this.selectedLevel = selectedLevel;
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

        this.offset.set(Config.CONFIG.getResolution()).sub(this.dimensions).div(2);
    }

    @Override
    public void writeUIComponent(MeshBuffer meshBuffer) {
        BoxSchema.WINDOW.writeBox(meshBuffer, this.globalPosition, this.dimensions);
        super.writeUIComponent(meshBuffer);
    }

}
