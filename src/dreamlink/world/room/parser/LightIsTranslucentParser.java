package dreamlink.world.room.parser;

import dreamlink.utility.json.CheckedJSONObject;

public class LightIsTranslucentParser {

    private static String isTranslucentKey = "light.is_transmitting";

    private boolean defaultValue;

    public LightIsTranslucentParser(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean getIsTranslucent(CheckedJSONObject blockConfig) {
        return blockConfig.optBoolean(
            LightIsTranslucentParser.isTranslucentKey, 
            this.defaultValue
        );
    }


    
}
