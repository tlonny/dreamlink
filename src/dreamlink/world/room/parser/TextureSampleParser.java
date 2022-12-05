package dreamlink.world.room.parser;

import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class TextureSampleParser {

    private final String key;
    private final IRoomModuleProvider provider;
    private final TextureSample defaultValue;

    public TextureSampleParser(
        IRoomModuleProvider provider, 
        String key, 
        TextureSample defaultValue
    ) {
        this.key = key;
        this.provider = provider;
        this.defaultValue = defaultValue;
    }

    public TextureSampleParser(IRoomModuleProvider provider, String key) {
        this(provider, key, null);
    }

    public TextureSample getTextureSample(CheckedJSONObject blockConfig) throws RoomLoadException{
        var textureSampleName = blockConfig.optString(this.key, null);
        if(textureSampleName == null) {
            return this.defaultValue;
        }

        var textureSampleModule = this.provider.getTextureSampleModule();
        var textureSample = textureSampleModule.getTextureSample(textureSampleName);

        if(textureSample == null) {
            throw RoomLoadException.missingTexture(textureSampleName);
        }

        return textureSample;
    }
    
}
