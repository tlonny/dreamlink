package doors.gamestate;

public class GameState {

    private static GameState GAME_STATE = null;

    public void use() {
        GAME_STATE = this;
    }

    public boolean isUsed() {
        return this == GAME_STATE;
    }

}
