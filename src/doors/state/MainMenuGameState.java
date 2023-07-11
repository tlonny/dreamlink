package doors.state;

import doors.Doors;
import doors.core.config.Config;
import doors.core.io.Mouse;
import doors.core.ui.UIDocument;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;
import doors.ui.UITextureAtlas;
import doors.ui.MainMenuComponent;

public class MainMenuGameState extends GameState {

    public static MainMenuGameState MAIN_MENU_GAME_STATE = new MainMenuGameState();

    private UIDocument document;

    public MainMenuGameState() {
        this.document = new UIDocument(new MainMenuComponent());
    }

    @Override
    public void use() {
        super.use();
        Mouse.MOUSE.centerLock = false;
    }

    @Override
    public void update() {

        Doors.UI_QUAD_BATCH.writeQuad(
            UITextureAtlas.BACKGROUND,
            Vector2in.ZERO,
            Config.CONFIG.getResolution(),
            Vector3fl.WHITE
        );

        this.document.update(Doors.UI_QUAD_BATCH);

    }
}
