package periwinkle.overlay.command_behaviour;

import periwinkle.Game;

public class GenerateWorld implements ICommandBehaviour {

    public void run(String[] args) {
        Game.WORLD.disableUpdates();
        Game.GENERATOR.generate();
        Game.WORLD.enableUpdates();
    }

}
