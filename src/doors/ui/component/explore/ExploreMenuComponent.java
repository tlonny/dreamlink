package doors.ui.component.explore;

import doors.Config;
import doors.graphics.font.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.MenuTexture;
import doors.state.ExploreGameState;
import doors.state.MainMenuGameState;
import doors.ui.component.IComponent;
import doors.ui.component.ButtonComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.TextInputComponent;
import doors.ui.component.WindowComponent;
import doors.ui.component.layout.HorizontalAlignment;
import doors.ui.component.layout.HorizontalSpanComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.component.layout.VerticalSpanComponent;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class ExploreMenuComponent implements IComponent {

    private static int INPUT_MAX_LENGTH = 24;
    private static int SPACING = 10;
    private static int WINDOW_PADDING = 10;
    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(60, 24);

    private Vector2in originCursor = new Vector2in();
    private WindowComponent windowComponent;

    private TextComponent labelComponent = new TextComponent(
        "Input level to explore:", FontDecoration.NORMAL, Vector3fl.BLACK
    );

    private ButtonComponent exploreButtonComponent = new ButtonComponent(
        BUTTON_DIMENSIONS,
        new TextComponent("Explore", FontDecoration.NORMAL, Vector3fl.BLACK),
        this::gotoExplore
    );

    private ButtonComponent backButtonComponent = new ButtonComponent(
        BUTTON_DIMENSIONS,
        new TextComponent("Quit", FontDecoration.NORMAL, Vector3fl.BLACK),
        this::gotoRootMenu
    );

    private TextInputComponent textInputComponent = new TextInputComponent(INPUT_MAX_LENGTH);

    public ExploreMenuComponent() {
        var span = new VerticalSpanComponent(HorizontalAlignment.LEFT, SPACING);

        span.components.add(labelComponent);
        span.components.add(this.textInputComponent);

        var buttonSpan = new HorizontalSpanComponent(SPACING);
        span.components.add(buttonSpan);

        buttonSpan.components.add(this.exploreButtonComponent);
        buttonSpan.components.add(this.backButtonComponent);

        this.windowComponent = new WindowComponent(
            MenuTexture.MENU_TEXTURE.iconFolder,
            "Explore Menu",
            new PaddingComponent(span, WINDOW_PADDING)
        );
    }

    @Override
    public Vector2in getDimensions() {
        return this.windowComponent.getDimensions();
    }

    @Override
    public void calculateDimensions() {
        this.windowComponent.calculateDimensions();
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.originCursor.set(Config.RESOLUTION).sub(this.getDimensions()).div(2);
        this.windowComponent.update(this.originCursor, root);
    }

    private void gotoRootMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.use();
        MainMenuGameState.MAIN_MENU_GAME_STATE.gotoRootMenu();
    }

    private void gotoExplore() {
        ExploreGameState.EXPLORE_GAME_STATE.use(this.textInputComponent.stringBuilder.toString());
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        this.windowComponent.writeUIComponent(spriteBatch);
    }
    
}
