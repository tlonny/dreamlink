package dreamlink.world.room.parser;

import dreamlink.audio.buffer.cache.SoundBufferRef;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class SoundParser {

    private final String key;
    private final IRoomModuleProvider provider;

    public SoundParser(
        IRoomModuleProvider provider, 
        String key
    ) {
        this.key = key;
        this.provider = provider;
    }

    public SoundBufferRef getSound(CheckedJSONObject blockConfig) throws RoomLoadException {
        var soundName = blockConfig.optString(this.key, null);
        if(soundName == null) {
            return null;
        }

        var soundModule = this.provider.getSoundModule();
        var soundBufferRef = soundModule.getSoundBufferRefByName(soundName);

        if(soundBufferRef == null) {
            throw RoomLoadException.missingSound(soundName);
        }

        return soundBufferRef;
    }
    
}
