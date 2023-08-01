package doors.ui.component.mainmenu;

import doors.Config;
import doors.graphics.text.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.MenuTexture;
import doors.state.MainMenuGameState;
import doors.ui.component.IComponent;
import doors.ui.component.ButtonComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.layout.ColumnComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.component.layout.RowComponent;
import doors.ui.component.layout.box.BoxComponent;
import doors.ui.component.layout.box.FixedDimension;
import doors.ui.component.textinput.TextInputComponent;
import doors.ui.component.window.WindowComponent;
import doors.ui.root.UIRoot;
import doors.utility.Functional.IAction0;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class MainMenuExploreComponent implements IComponent {

    private static int INPUT_MAX_LENGTH = 36;
    private static int SPACING = 10;
    private static int WINDOW_PADDING = 10;
    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(70, 24);

    private Vector2in originCursor = new Vector2in();
    private WindowComponent windowComponent;
    private ButtonComponent exploreButton;
    private ButtonComponent backButton;

    private TextInputComponent textInputComponent;
    private MainMenuExploreStatusComponent statusComponent;

    public MainMenuExploreComponent() {
        var layoutSpanComponent = new ColumnComponent(SPACING);

        var labelComponent = new TextComponent("Input level to explore:", FontDecoration.NORMAL, Vector3fl.BLACK);
        layoutSpanComponent.children.add(labelComponent);

        this.textInputComponent = new TextInputComponent(INPUT_MAX_LENGTH);
        layoutSpanComponent.children.add(this.textInputComponent);

        var buttonSpan = new RowComponent(SPACING);
        this.exploreButton = this.createButton("Explore", this::gotoExplore);
        buttonSpan.children.add(this.exploreButton);
        this.backButton = this.createButton("Back", this::gotoRootMenu);
        buttonSpan.children.add(this.backButton);
        layoutSpanComponent.children.add(buttonSpan);

        this.statusComponent = new MainMenuExploreStatusComponent();
        layoutSpanComponent.children.add(this.statusComponent);

        this.windowComponent = new WindowComponent(
            MenuTexture.MENU_TEXTURE.iconFolder,
            "Explore Menu",
            new PaddingComponent(layoutSpanComponent, WINDOW_PADDING)
        );
    }

    private ButtonComponent createButton(String text, IAction0 onClick) {
        var boxComponent = new BoxComponent(
            new TextComponent(text, FontDecoration.NORMAL, Vector3fl.BLACK),
            new FixedDimension(BUTTON_DIMENSIONS.x),
            new FixedDimension(BUTTON_DIMENSIONS.y)
        );
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
        this.originCursor.set(Config.CONFIG.getResolution()).sub(this.getDimensions()).div(2);
        this.windowComponent.calculatePosition(this.originCursor);
    }

    @Override
    public void update(UIRoot root) {
        this.windowComponent.update(root);

        var exploreState = MainMenuGameState.MAIN_MENU_GAME_STATE.getLevelCacheEntryState();
        var isDisabled = exploreState != null && !exploreState.isTerminal;
        this.exploreButton.isDisabled = isDisabled;
        this.backButton.isDisabled = isDisabled;
        if(exploreState == null) {
            this.statusComponent.setStatus("", false);
        } else {
            this.statusComponent.setStatus(exploreState.name, exploreState.isError);
        }
    }

    private void gotoRootMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.gotoRootMenu();
    }

    private void gotoExplore() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.gotoExplore(this.textInputComponent.getText());
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.windowComponent.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
