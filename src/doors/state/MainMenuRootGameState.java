package doors.state;

import java.util.Arrays;

import doors.core.config.Config;
import doors.core.io.Mouse;
import doors.core.io.Window;
import doors.core.ui.IUIElement;
import doors.core.ui.VerticalSpanElement;
import doors.core.ui.alignment.HorizontalAlignmentWrapper;
import doors.graphics.ui.UITextureAtlas;
import doors.ui.ContainerElement;
import doors.ui.MenuButtonElement;
import doors.ui.MenuTitleElement;
import doors.ui.WindowElement;
import doors.core.utility.vector.Vector2in;

public class MainMenuRootGameState extends GameState {


    public static MainMenuRootGameState MAIN_MENU_ROOT_GAME_STATE = new MainMenuRootGameState();

    private IUIElement root;

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

    private IUIElement buildWindow() {
        return new WindowElement(
            new VerticalSpanElement(Arrays.asList(
                new HorizontalAlignmentWrapper(new MenuTitleElement(UITextureAtlas.ICON_LOGO, "Doors")),
                new HorizontalAlignmentWrapper(new MenuButtonElement(UITextureAtlas.ICON_EXPLORE, "Explore", MainMenuExploreGameState.MAIN_MENU_EXPLORE_GAME_STATE::use)),
                new HorizontalAlignmentWrapper(new MenuButtonElement(UITextureAtlas.ICON_EDITOR, "Editor", () -> {})),
                new HorizontalAlignmentWrapper(new MenuButtonElement(UITextureAtlas.ICON_SETTINGS, "Settings", () -> {})),
                new HorizontalAlignmentWrapper(new MenuButtonElement(UITextureAtlas.ICON_HELP, "Help", () -> {})),
                new HorizontalAlignmentWrapper(new MenuButtonElement(UITextureAtlas.ICON_QUIT, "Quit", Window.WINDOW::setShouldClose))
            ), 0)
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
