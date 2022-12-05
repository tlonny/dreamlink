package dreamlink.world.room.parser;

import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.maths.IntMaths;

public class LightAmountParser {

    private static String lightAmountKeyTemplate = "light.amount.%s";
    private static int defaultLightAmount = 0;
    private static int maxLightAmount = 0xF;
    private static int minLightAmount = 0;

    private String keySuffix;
    private int defaultValue;

    public LightAmountParser(String keySuffix, int defaultValue) {
        this.keySuffix = keySuffix;
        this.defaultValue = defaultValue;
    }

    public LightAmountParser(String keySuffix) {
        this(keySuffix, LightAmountParser.defaultLightAmount);
    }

    public int getLightAmount(CheckedJSONObject blockConfig) {
        var key = String.format(LightAmountParser.lightAmountKeyTemplate, this.keySuffix);
        var amount = blockConfig.optInt(key, this.defaultValue);

        return IntMaths.clamp(
            amount, 
            LightAmountParser.minLightAmount, 
            LightAmountParser.maxLightAmount
        );
    }
    
}
