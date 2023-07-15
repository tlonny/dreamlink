package doors.state;

public abstract class AbstractGameState {

    public static AbstractGameState USED_GAME_STATE = null;

    public void use() {
        USED_GAME_STATE = this;
    }

    public abstract void update();
}
