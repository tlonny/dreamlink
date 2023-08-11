package doors.level.cache;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import doors.Config;

public class LevelCache {

    private static int CACHE_SIZE = 50;
    public static LevelCache LEVEL_CACHE = new LevelCache(CACHE_SIZE);

    private LinkedList<LevelCacheEntry> accessList = new LinkedList<>();
    private Map<String,LevelCacheEntry> levelMap = new HashMap<>();
    private int cacheSize;

    public LevelCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public LevelCacheEntry getLevelCacheEntry(String levelName) {
        if(!this.levelMap.containsKey(levelName)) {
            var levelDirectory = Paths.get(Config.CONFIG.getCachePath(), levelName).toString();
            var entry = new LevelCacheEntry(levelDirectory);
            this.levelMap.put(levelName, entry);
        } else {
            this.accessList.remove(this.levelMap.get(levelName));
        }
        var entry = this.levelMap.get(levelName);
        this.accessList.addFirst(entry);

        while(this.accessList.size() > this.cacheSize) {
            var lastEntry = this.accessList.removeLast();
            this.levelMap.remove(lastEntry.levelName);
            lastEntry.tearDown();
        }

        return entry;
    }

}
