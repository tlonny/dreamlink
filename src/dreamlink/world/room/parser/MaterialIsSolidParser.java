package dreamlink.world.room.parser;

import dreamlink.utility.json.CheckedJSONObject;

public class MaterialIsSolidParser {

    private static String materialIsSolidKey = "material.is_solid";

    private boolean defaultValue;

    public MaterialIsSolidParser(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean getIsSolid(CheckedJSONObject blockConfig) {
        return blockConfig.optBoolean(
            MaterialIsSolidParser.materialIsSolidKey, 
            this.defaultValue
        );
    }


    
}
