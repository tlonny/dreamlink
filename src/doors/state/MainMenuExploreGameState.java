package doors.state;

import java.util.Arrays;

import doors.core.config.Config;
import doors.core.graphics.sprite.FontDecoration;
import doors.core.io.Mouse;
import doors.core.io.Window;
import doors.core.ui.IUIElement;
import doors.core.ui.PaddingElement;
import doors.core.ui.VerticalSpanElement;
import doors.core.ui.alignment.HorizontalAlignment;
import doors.core.ui.alignment.HorizontalAlignmentWrapper;
import doors.state.explore.ExploreGameState;
import doors.graphics.ui.UITextureAtlas;
import doors.ui.BorderElement;
import doors.ui.ContainerElement;
import doors.ui.MenuButtonElement;
import doors.ui.MenuTitleElement;
import doors.ui.TextInputElement;
import doors.ui.TextLabelElement;
import doors.ui.WindowElement;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.Vector2in;

public class MainMenuExploreGameState extends GameState {

    public static MainMenuExploreGameState MAIN_MENU_EXPLORE_GAME_STATE = new MainMenuExploreGameState();

    private IUIElement root;
    private TextInputElement levelInput;

    public void setup() {
        // We need to do this in setup to stop a circular references to other game states
        this.root = new ContainerElement(this.buildWindow());
    }

    @Override
    public void use() {
        super.use();
        Mouse.MOUSE.centerLock = false;
        Window.WINDOW.setCursorVisibility(true);
    }

    public MainMenuExploreGameState() {
        this.levelInput = new TextInputElement(20);
        this.levelInput.text.append("");
    }

    private void explore() {
        ExploreGameState.EXPLORE_GAME_STATE.use(this.levelInput.text.toString());
    }

    private IUIElement buildWindow() {
        return new WindowElement(
            new VerticalSpanElement(Arrays.asList(
                new HorizontalAlignmentWrapper(
                    new BorderElement(
                        new PaddingElement(
                            new VerticalSpanElement(Arrays.asList(
                                new HorizontalAlignmentWrapper(new MenuTitleElement(UITextureAtlas.ICON_EXPLORE, "Doors.Explore")),
                                new HorizontalAlignmentWrapper(new VerticalSpanElement(Arrays.asList(
                                    new HorizontalAlignmentWrapper(new TextLabelElement("Level:", FontDecoration.NORMAL, Vector3fl.BLACK), HorizontalAlignment.LEFT),
                                    new HorizontalAlignmentWrapper(this.levelInput)
                                ), 0))
                            ), 0),
                            0, 10, 0, 0
                        ),
                        false, true, false, false
                    )
                ),
                new HorizontalAlignmentWrapper(new MenuButtonElement(UITextureAtlas.ICON_TICK, "Go", this::explore)),
                new HorizontalAlignmentWrapper(new MenuButtonElement(UITextureAtlas.ICON_QUIT, "Back", MainMenuRootGameState.MAIN_MENU_ROOT_GAME_STATE::use))
            ), 4)
        );
    }

    @Override
    public void update() {
        this.root.determineDimensions();
        var origin = new Vector2in(Config.CONFIG.getResolution()).sub(this.root.getDimensions()).div(2);
        this.root.determinePosition(origin);
        this.root.update();
        this.root.writeElement();
    }
}
