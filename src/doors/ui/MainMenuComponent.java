package doors.ui;

import doors.core.config.Config;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.sprite.FontDecoration;
import doors.core.io.Window;
import doors.core.ui.BaseUIComponent;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;
import doors.state.MainMenuEditorGameState;
import doors.state.MainMenuExploreGameState;

public class MainMenuComponent extends BaseUIComponent {

    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(160, 24);
    private static Vector2in WINDOW_PADDING = new Vector2in(10, 10);
    private static int SPACING = 10;

    private ButtonComponent exploreButton;
    private ButtonComponent editorButton;
    private ButtonComponent settingsButton;
    private ButtonComponent quitButton;

    public MainMenuComponent() {
        super();

        this.exploreButton = this.addChild(new ButtonComponent(
            new TextLabelComponent("Explore", FontDecoration.NORMAL, Vector3fl.BLACK),
            BUTTON_DIMENSIONS,
            this::gotoMainMenuExplore
        ));

        this.editorButton = this.addChild(new ButtonComponent(
            new TextLabelComponent("Editor", FontDecoration.NORMAL, Vector3fl.BLACK),
            BUTTON_DIMENSIONS,
            this::gotoMainMenuEditor
        ));

        this.settingsButton = this.addChild(new ButtonComponent(
            new TextLabelComponent("Settings", FontDecoration.NORMAL, Vector3fl.BLACK),
            BUTTON_DIMENSIONS,
            () -> {}
        ));

        this.quitButton = this.addChild(new ButtonComponent(
            new TextLabelComponent("Quit", FontDecoration.NORMAL, Vector3fl.BLACK),
            BUTTON_DIMENSIONS,
            Window.WINDOW::setShouldClose
        ));

    }

    private void gotoMainMenuExplore() {
        MainMenuExploreGameState.MAIN_MENU_EXPLORE_GAME_STATE.use();
    }

    private void gotoMainMenuEditor() {
        MainMenuEditorGameState.MAIN_MENU_EDITOR_GAME_STATE.use();
    }

    @Override
    public void calculateLayout() {
        super.calculateLayout();
        this.exploreButton.offset.set(WINDOW_PADDING);

        this.editorButton.offset.set(this.exploreButton.offset);
        this.editorButton.offset.y += BUTTON_DIMENSIONS.y + SPACING;

        this.settingsButton.offset.set(this.editorButton.offset);
        this.settingsButton.offset.y += BUTTON_DIMENSIONS.y + SPACING;

        this.quitButton.offset.set(this.settingsButton.offset);
        this.quitButton.offset.y += BUTTON_DIMENSIONS.y + SPACING;

        this.dimensions.set(this.quitButton.offset).add(BUTTON_DIMENSIONS).add(WINDOW_PADDING);
        this.offset.set(Config.CONFIG.getResolution()).sub(this.dimensions).div(2);
    }

    @Override
    public void writeUIComponent(MeshBuffer meshBuffer) {
        BoxSchema.WINDOW.writeBox(meshBuffer, this.globalPosition, this.dimensions);
        super.writeUIComponent(meshBuffer);
    }
}
