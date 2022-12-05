package periwinkle.overlay;

import periwinkle.environment.Sky;
import periwinkle.environment.SkyType;

public class SetSkyType implements ICommandBehaviour {

    public void run(String[] args) {
        var skyType = SkyType.SKY_TYPE_NAME_LOOKUP.get(args[1]);
        if(skyType != null)
            Sky.SKY.skyType = skyType;
    }
}
