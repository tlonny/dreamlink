package doors.config;

import doors.core.config.IConfigData;
import doors.core.utility.vector.Vector2in;

public class DoorsCoreConfigData implements IConfigData {

    @Override
    public Vector2in getResolution() {
        return new Vector2in(1280, 720).div(2);
    }

    @Override
    public Vector2in getMaxImageTextureDimensions() {
        return new Vector2in(1024, 1024);
    }
}
