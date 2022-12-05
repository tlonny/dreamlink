package dreamlink.config;

import java.io.IOException;

import dreamlink.utility.CommonPaths;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONAccessException;
import dreamlink.utility.json.JSONDecodeException;

public class SoundMixerConfig {

    private static final String configFilename = "sound_mixer.json";
    private static final String maxSoundSourcesKey = "max_sound_sources";
    private static final int defaultMaxSoundSourcesKey = 16;
    private static final int minMaxSoundSourcesKey = 4;

    public static final SoundMixerConfig instance = new SoundMixerConfig();

    private int maxSources;

    public void load() {
        var file = CommonPaths.instance.configPath
            .resolve(SoundMixerConfig.configFilename)
            .toFile();

        if(!file.exists()) {
            this.maxSources = SoundMixerConfig.defaultMaxSoundSourcesKey;
            return;
        }

        try {
            var data = FileFns.readStringFromFile(file);
            var config = new CheckedJSONObject(data);
            this.maxSources = Math.max(
                config.getInt(SoundMixerConfig.maxSoundSourcesKey),
                SoundMixerConfig.minMaxSoundSourcesKey
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONDecodeException e) {
            throw new RuntimeException(e);
        } catch (JSONAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public int getMaxSoundSources() {
        return this.maxSources;
    }
    
}
