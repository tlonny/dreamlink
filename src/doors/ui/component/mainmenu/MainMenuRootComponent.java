package doors.ui.component.mainmenu;

import doors.graphics.text.FontDecoration;
import doors.Config;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.MenuTexture;
import doors.io.Window;
import doors.state.MainMenuGameState;
import doors.ui.component.IComponent;
import doors.ui.component.ButtonComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.component.layout.BoxComponent;
import doors.ui.component.layout.ColumnComponent;
import doors.ui.component.window.WindowComponent;
import doors.ui.root.UIRoot;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class MainMenuRootComponent implements IComponent {

    private static int BUTTON_SPACING = 10;
    private static int WINDOW_PADDING = 10;
    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(150, 24);

    private Vector2in originCursor = new Vector2in();
    private WindowComponent windowComponent;

    public MainMenuRootComponent() {
        var layoutSpanComponent = new ColumnComponent(BUTTON_SPACING);

        layoutSpanComponent.children.add(this.createButton("Explore", this::gotoExploreMenu));
        layoutSpanComponent.children.add(this.createButton("Edit", this::gotoEditMenu));
        layoutSpanComponent.children.add(this.createButton("Quit", this::quit));

        this.windowComponent = new WindowComponent(
            MenuTexture.MENU_TEXTURE.iconFolder,
            "Main Menu",
            new PaddingComponent(layoutSpanComponent, WINDOW_PADDING)
        );
    }

    private ButtonComponent createButton(String text, IAction0 onClick) {
        var boxComponent = new BoxComponent(
            BUTTON_DIMENSIONS,
            BUTTON_DIMENSIONS
        );
        
        boxComponent.child = new TextComponent(text, FontDecoration.NORMAL, Vector3fl.BLACK);
        return new ButtonComponent(boxComponent, onClick);
    }

    @Override
    public Vector2in getDimensions() {
        return this.windowComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.windowComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.windowComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.windowComponent.adjustDimensions(this.getDimensions());
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.originCursor.set(Config.RESOLUTION).sub(this.getDimensions()).div(2);
        this.windowComponent.calculatePosition(this.originCursor);
    }

    @Override
    public void update(UIRoot root) {
        this.windowComponent.update(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
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
