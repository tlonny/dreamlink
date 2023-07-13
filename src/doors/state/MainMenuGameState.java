package doors.state;

import doors.core.Config;
import doors.core.GameState;
import doors.graphics.mesh.SpriteBatch;
import doors.graphics.texture.MenuTextureAtlas;
import doors.io.Mouse;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;
import doors.menu.MainMenuComponent;
import doors.menu.MenuContext;

public class MainMenuGameState extends GameState {

    public static MainMenuGameState MAIN_MENU_GAME_STATE = new MainMenuGameState();

    private MenuContext context;

    public MainMenuGameState() {
        this.context = new MenuContext(new MainMenuComponent());
    }

    @Override
    public void use() {
        super.use();
        Mouse.MOUSE.centerLock = false;
    }

    @Override
    public void update() {

        SpriteBatch.SPRITE_BATCH.writeSprite(
            MenuTextureAtlas.MENU_TEXTURE_ATLAS.background,
            Vector2in.ZERO,
            Config.RESOLUTION,
            Vector3fl.WHITE
        );

        this.context.update();
    }
}
