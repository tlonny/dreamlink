package periwinkle.overlay.command_behaviour;

import periwinkle.Game;

public class ToggleNoClip implements ICommandBehaviour {

    public void run(String[] args) {
        Game.PLAYER.playerControlSystem.noClip ^= true;
    }
}

