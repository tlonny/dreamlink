package doors.ui;

import doors.core.config.Config;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.sprite.FontDecoration;
import doors.core.ui.BaseUIComponent;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;
import doors.state.ExploreGameState;
import doors.state.MainMenuGameState;

public class MainMenuExploreComponent extends BaseUIComponent {

    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(60, 24);
    private static Vector2in WINDOW_PADDING = new Vector2in(10, 10);
    private static int SPACING = 10;
    private static int MAX_TEXT_LENGTH = 20;

    private ButtonComponent goButton;
    private ButtonComponent backButton;
    private TextLabelComponent textLabel;
    private TextInputComponent textInput;

    public MainMenuExploreComponent() {
        super();

        this.textLabel = this.addChild(new TextLabelComponent("Level name:", FontDecoration.NORMAL, Vector3fl.BLACK));

        this.goButton = this.addChild(new ButtonComponent(
            new TextLabelComponent("Go", FontDecoration.NORMAL, Vector3fl.BLACK),
            BUTTON_DIMENSIONS,
            this::gotoExplore
        ));

        this.backButton = this.addChild(new ButtonComponent(
            new TextLabelComponent("Back", FontDecoration.NORMAL, Vector3fl.BLACK),
            BUTTON_DIMENSIONS,
            this::gotoMainMenu
        ));

        this.textInput = this.addChild(new TextInputComponent(MAX_TEXT_LENGTH));
    }

    private void gotoMainMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.use();
    }

    private void gotoExplore() {
        ExploreGameState.EXPLORE_GAME_STATE.use(this.textInput.stringBuilder.toString());
    }

    @Override
    public void calculateLayout() {
        super.calculateLayout();
        this.textLabel.offset.set(WINDOW_PADDING);

        this.textInput.offset.set(this.textLabel.offset);
        this.textInput.offset.y += this.textLabel.dimensions.y + SPACING;

        this.goButton.offset.set(this.textInput.offset);
        this.goButton.offset.y += this.textInput.dimensions.y + SPACING;

        this.backButton.offset.set(this.goButton.offset);
        this.backButton.offset.x += this.goButton.dimensions.x + SPACING;

        this.dimensions.set(
            this.textInput.offset.x + this.textInput.dimensions.x,
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
