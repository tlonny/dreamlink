package periwinkle.overlay.command_behaviour;

import periwinkle.Game;
import periwinkle.environment.SkyType;

public class SetSkyType implements ICommandBehaviour {

    public void run(String[] args) {
        var skyType = SkyType.SKY_TYPE_NAME_LOOKUP.get(args[1]);
        if(skyType != null)
            Game.SKY_TYPE = skyType;
    }
}
