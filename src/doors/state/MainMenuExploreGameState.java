package doors.state;

import doors.core.Config;
import doors.io.Mouse;
import doors.menu.MenuContext;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;
import doors.graphics.mesh.SpriteBatch;
import doors.graphics.texture.MenuTextureAtlas;
import doors.menu.MainMenuExploreComponent;

public class MainMenuExploreGameState extends GameState {

    public static MainMenuExploreGameState MAIN_MENU_EXPLORE_GAME_STATE = new MainMenuExploreGameState();

    private MenuContext context = new MenuContext(new MainMenuExploreComponent());

    @Override
    public void use() {
        super.use();
        Mouse.MOUSE.unlockMouse();
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

