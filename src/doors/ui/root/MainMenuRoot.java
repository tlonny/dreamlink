package doors.ui.root;

import doors.Config;
import doors.core.graphics.font.FontDecoration;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.io.Window;
import doors.core.ui.AbstractUIRoot;
import doors.core.ui.SpriteComponent;
import doors.core.ui.UILayer;
import doors.graphics.sprite.box.WindowSprite;
import doors.graphics.sprite.ui.TextSprite;
import doors.graphics.texture.MenuTexture;
import doors.state.MainMenuExploreGameState;
import doors.ui.component.ButtonComponent;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class MainMenuRoot extends AbstractUIRoot {

    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(160, 24);
    private static int SPACING = 10;

    private static String EXPLORE_BUTTON = "Explore";
    private static String EDITOR_BUTTON = "Editor";
    private static String QUIT_BUTTON = "Quit";

    private ButtonComponent exploreButton;
    private ButtonComponent editorButton;
    private ButtonComponent quitButton;

    public MainMenuRoot() {
        this.exploreButton = new ButtonComponent(
            this,
            BUTTON_DIMENSIONS,
            new TextSprite(EXPLORE_BUTTON, FontDecoration.NORMAL, Vector3fl.BLACK),
            this::gotoExploreMenu
        );

        this.exploreButton.offset.set(SPACING);

        this.editorButton = new ButtonComponent(
            this,
            BUTTON_DIMENSIONS,
            new TextSprite(EDITOR_BUTTON, FontDecoration.NORMAL, Vector3fl.BLACK),
            null
        );

        this.editorButton.offset.set(this.exploreButton.offset);
        this.editorButton.offset.y += this.exploreButton.getDimensions().y + SPACING;

        this.quitButton = new ButtonComponent(
            this,
            BUTTON_DIMENSIONS,
            new TextSprite(QUIT_BUTTON, FontDecoration.NORMAL, Vector3fl.BLACK),
            Window.WINDOW::setShouldClose
        );

        this.quitButton.offset.set(this.editorButton.offset);
        this.quitButton.offset.y += this.editorButton.getDimensions().y + SPACING;

        var windowDimensions = new Vector2in(this.quitButton.offset).add(this.quitButton.getDimensions()).add(SPACING);
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

    private void gotoExploreMenu() {
        MainMenuExploreGameState.MAIN_MENU_EXPLORE_GAME_STATE.use();
    }
    
}
