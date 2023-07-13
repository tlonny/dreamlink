package doors.state;

import doors.core.Config;
import doors.core.GameState;
import doors.graphics.mesh.SpriteBatch;
import doors.graphics.texture.MenuTextureAtlas;
import doors.io.Mouse;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;
import doors.menu.MainMenuEditorComponent;
import doors.menu.MenuContext;

public class MainMenuEditorGameState extends GameState {

    public static MainMenuEditorGameState MAIN_MENU_EDITOR_GAME_STATE = new MainMenuEditorGameState();

    private MenuContext context = new MenuContext(new MainMenuEditorComponent());

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

