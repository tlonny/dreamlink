package doors.overlay.command_behaviour;

import doors.Game;

public class ToggleNoClip implements ICommandBehaviour {

    public void run(String[] args) {
        Game.PLAYER.playerControlSystem.noClip ^= true;
    }
}

