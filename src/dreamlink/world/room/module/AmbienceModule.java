package dreamlink.world.room.module;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import dreamlink.audio.buffer.cache.SoundBufferRef;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONDecodeException;
import dreamlink.utility.maths.FloatMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class AmbienceModule {

    private static final Path ambiencePath = Paths.get("pack/ambience.json");
    private static final String soundKey = "sound";
    private static final String soundGainkey = "sound.gain";
    private static final float defaultGain = 1f;

    private SoundBufferRef ambientSound;
    private final IRoomModuleProvider provider;
    private float gain;

    public AmbienceModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    public void loadData() throws RoomLoadException {
        var path = this.provider.getIOModule().getPath();
        var configFile = path
            .resolve(AmbienceModule.ambiencePath)
            .toFile();

        if(!configFile.exists()) {
            return;
        }

        try {
            var data = FileFns.readStringFromFile(configFile);
            var config = new CheckedJSONObject(data);
            this.gain = FloatMaths.clamp(
                config.optFloat(AmbienceModule.soundGainkey, AmbienceModule.defaultGain),
                0f, 1f
            );

            var soundName = config.optString(AmbienceModule.soundKey, null);
            if(soundName == null) {
                return;
            }

            this.ambientSound = this.provider.getSoundModule().getSoundBufferRefByName(soundName);
            if(this.ambientSound == null) {
                throw RoomLoadException.missingSound(soundName);
            }
        } catch (IOException e) {
            throw RoomLoadException.invalidAmbienceConfig();
        } catch (JSONDecodeException e) {
            throw RoomLoadException.invalidAmbienceConfig();
        }
    }

    public SoundBufferRef getSound() {
        return this.ambientSound;
    }

    public float getGain() {
        return this.gain;
    }
    
}
