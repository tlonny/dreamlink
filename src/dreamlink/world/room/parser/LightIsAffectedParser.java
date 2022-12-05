package dreamlink.world.room.parser;

import dreamlink.utility.json.CheckedJSONObject;

public class LightIsAffectedParser {

    private static String isAffectedKey = "light.is_affected";

    private boolean defaultValue;

    public LightIsAffectedParser(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean getIsAffected(CheckedJSONObject blockConfig) {
        return blockConfig.optBoolean(
            LightIsAffectedParser.isAffectedKey, 
            this.defaultValue
        );
    }


    
}
