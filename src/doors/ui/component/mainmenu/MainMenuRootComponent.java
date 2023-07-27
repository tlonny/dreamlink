package doors.ui.component.mainmenu;

import doors.Config;
import doors.graphics.text.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.MenuTexture;
import doors.io.Window;
import doors.state.MainMenuGameState;
import doors.ui.component.IComponent;
import doors.ui.component.TextureComponent;
import doors.ui.component.ButtonComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.WindowComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.component.layout.VerticalSpanComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class MainMenuRootComponent implements IComponent {

    private static int BUTTON_SPACING = 10;
    private static int WINDOW_PADDING = 10;
    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(150, 24);

    private WindowComponent windowComponent;
    private TextureComponent backgroundComponent = new TextureComponent(MenuTexture.MENU_TEXTURE.background);
    private Vector2in originCursor = new Vector2in();

    public MainMenuRootComponent() {
        var buttonSpan = new VerticalSpanComponent(BUTTON_SPACING);

        buttonSpan.components.add(
            new ButtonComponent(
                BUTTON_DIMENSIONS,
                new TextComponent("Explore", FontDecoration.NORMAL, Vector3fl.BLACK),
                this::gotoExploreMenu
            )
        );

        buttonSpan.components.add(
            new ButtonComponent(
                BUTTON_DIMENSIONS,
                new TextComponent("Edit", FontDecoration.NORMAL, Vector3fl.BLACK),
                this::gotoEditMenu
            )
        );

        buttonSpan.components.add(
            new ButtonComponent(
                BUTTON_DIMENSIONS,
                new TextComponent("Quit", FontDecoration.NORMAL, Vector3fl.BLACK),
                this::quit
            )
        );

        this.windowComponent = new WindowComponent(
            MenuTexture.MENU_TEXTURE.iconFolder,
            "Main Menu",
            new PaddingComponent(buttonSpan, WINDOW_PADDING)
        );
    }


    @Override
    public Vector2in getDimensions() {
        return this.windowComponent.getDimensions();
    }


    @Override
    public void calculateDimensions() {
        this.windowComponent.calculateDimensions();
        this.backgroundComponent.setDimensions(Config.RESOLUTION);
    }


    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.originCursor.set(Config.RESOLUTION).sub(this.getDimensions()).div(2);
        this.windowComponent.update(this.originCursor, root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.backgroundComponent.writeComponentToSpriteBatch(spriteBatch);
        this.windowComponent.writeComponentToSpriteBatch(spriteBatch);
    }

    private void gotoExploreMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.gotoExploreMenu();
    }

    private void gotoEditMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.gotoEditMenu();
    }

    private void quit() {
        Window.WINDOW.setShouldClose();
    }
}
