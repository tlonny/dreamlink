package doors.gamestate;

import doors.io.Mouse;

public class UIGameState extends GameState {

    public static UIGameState MENU_GAME_STATE = new UIGameState();

    public void use() {
        super.use();
        Mouse.MOUSE.setCenterLock(false);
        Mouse.MOUSE.setCursorVisibility(true);
    }
}

