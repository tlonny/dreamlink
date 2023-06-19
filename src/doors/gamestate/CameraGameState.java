package doors.gamestate;

import doors.io.Mouse;

public class CameraGameState extends GameState {

    public static CameraGameState CAMERA_GAME_STATE = new CameraGameState();

    public void use() {
        super.use();
        Mouse.MOUSE.setCenterLock(true);
        Mouse.MOUSE.setCursorVisibility(false);
    }
}

