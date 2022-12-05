package dreamlink.config;

import java.io.IOException;

import dreamlink.utility.CommonPaths;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONAccessException;
import dreamlink.utility.json.JSONDecodeException;

public class RoomCacheConfig {

    private static final String configFilename = "room_cache.json";
    private static final String cacheSizeKey = "cache_size";
    private static final int defaultCacheSize = 10;
    private static final int minCacheSize = 10;

    public static final RoomCacheConfig instance = new RoomCacheConfig();

    private int cacheSize;

    public void load() {
        var file = CommonPaths.instance.configPath
            .resolve(RoomCacheConfig.configFilename)
            .toFile();

        if(!file.exists()) {
            this.cacheSize = RoomCacheConfig.defaultCacheSize;
            return;
        }

        try {
            var data = FileFns.readStringFromFile(file);
            var config = new CheckedJSONObject(data);
            this.cacheSize = Math.max(
                config.getInt(RoomCacheConfig.cacheSizeKey),
                RoomCacheConfig.minCacheSize
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONDecodeException e) {
            throw new RuntimeException(e);
        } catch (JSONAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public int getCacheSize() {
        return this.cacheSize;
    }
    
}
