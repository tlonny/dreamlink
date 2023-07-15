package doors.ui.root;

import doors.Config;
import doors.core.graphics.font.FontDecoration;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.ui.AbstractUIRoot;
import doors.core.ui.SpriteComponent;
import doors.core.ui.UILayer;
import doors.graphics.sprite.box.WindowSprite;
import doors.graphics.sprite.ui.TextSprite;
import doors.graphics.texture.MenuTexture;
import doors.state.MainMenuGameState;
import doors.ui.component.ButtonComponent;
import doors.ui.component.TextInputComponent;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class MainMenuExploreRoot extends AbstractUIRoot {

    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(90, 24);
    private static int SPACING = 10;
    private static int MAX_TEXT_LENGTH = 30;

    private static String EXPLORE_BUTTON = "Explore";
    private static String BACK_BUTTON = "Back";
    private static String LABEL_STR = "Level to explore:";

    private ButtonComponent exploreButton;
    private ButtonComponent backButton;
    private TextInputComponent textInput;

    public MainMenuExploreRoot() {

        var labelSprite = new TextSprite(
            LABEL_STR, FontDecoration.NORMAL, Vector3fl.BLACK
        );

        var labelComponent = new SpriteComponent(this, labelSprite);
        labelComponent.offset.set(SPACING);

        this.textInput = new TextInputComponent(this, MAX_TEXT_LENGTH);
        this.textInput.offset.set(labelComponent.offset);
        this.textInput.offset.y += labelComponent.getDimensions().y + SPACING;

        this.exploreButton = new ButtonComponent(
            this, 
            BUTTON_DIMENSIONS, 
            new TextSprite(EXPLORE_BUTTON, FontDecoration.NORMAL, Vector3fl.BLACK),
            null
        );

        this.exploreButton.offset.set(this.textInput.offset);
        this.exploreButton.offset.y += this.textInput.getDimensions().y + SPACING;

        this.backButton = new ButtonComponent(
            this, 
            BUTTON_DIMENSIONS, 
            new TextSprite(BACK_BUTTON, FontDecoration.NORMAL, Vector3fl.BLACK),
            this::gotoMainMenu
        );

        this.backButton.offset.set(this.exploreButton.offset);
        this.backButton.offset.x += this.exploreButton.getDimensions().x + SPACING;
        
        var windowDimensions = new Vector2in(
            Math.max(
                this.backButton.offset.x + this.backButton.getDimensions().x,
                this.textInput.offset.x + this.textInput.getDimensions().x
            ) + SPACING,
            this.backButton.offset.y + this.backButton.getDimensions().y + SPACING
        );

        var windowSprite = new WindowSprite(windowDimensions);
        new SpriteComponent(this, windowSprite, UILayer.BACKGROUND);

        this.position.set(Config.RESOLUTION).sub(windowDimensions).div(2);

    }

    @Override
    public void writeUIRoot(MeshBuffer meshBuffer) {
        meshBuffer.writeQuad(
            MenuTexture.MENU_TEXTURE.background,
            Vector2in.ZERO,
            Config.RESOLUTION,
            Vector3fl.WHITE
        );
        
        super.writeUIRoot(meshBuffer);
    }

    private void gotoMainMenu() {
        MainMenuGameState.MAIN_MENU_GAME_STATE.use();
    }
    
}
