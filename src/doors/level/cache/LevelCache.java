package doors.level.cache;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import doors.Config;
import doors.level.Level;
import doors.queue.IncrementalWorkQueue;
import doors.queue.ThreadPoolWorkQueue;

public class LevelCache {

    private static int CACHE_SIZE = 50;
    public static LevelCache LEVEL_CACHE = new LevelCache(CACHE_SIZE);

    private LinkedList<LevelCacheEntry> accessList = new LinkedList<>();
    private Map<String,LevelCacheEntry> levelMap = new HashMap<>();
    private int cacheSize;

    public LevelCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void requestLevel(String levelName) {
        if(!this.levelMap.containsKey(levelName)) {
            var levelDirectory = Paths.get(Config.CACHE_PATH, levelName).toString();
            var initialState = new File(levelDirectory).isDirectory() 
                ? LevelCacheEntryState.DOWNLOADED
                : LevelCacheEntryState.MISSING;

            var entry = new LevelCacheEntry(levelDirectory, initialState);
            this.levelMap.put(levelName, entry);
        }
        var entry = this.levelMap.get(levelName);
        entry.isRequested = true;
    }

    // TODO: mock implementation - need to replace
    private void downloadFile(LevelCacheEntry entry) {
        try {
            var downloadTime = 1000;
            Thread.sleep(downloadTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        entry.state = LevelCacheEntryState.DOWNLOADED;
    }

    public void update() {
        for(var entry : this.levelMap.values()) {
            if(!entry.isRequested) {
                continue;
            }

            if(entry.state == LevelCacheEntryState.MISSING) {
                entry.state = LevelCacheEntryState.DOWNLOADING;
                ThreadPoolWorkQueue.THREAD_POOL_WORK_QUEUE.submitTask(() -> this.downloadFile(entry));
            }

            if(entry.state == LevelCacheEntryState.DOWNLOADED) {
                entry.state = LevelCacheEntryState.LOADING;
                try {
                    entry.level = new Level(entry.levelDirectory);
                // TODO: use a more specific exception
                } catch(Exception e) {
                    entry.state = LevelCacheEntryState.LOAD_FAILED;
                    continue;
                }
                IncrementalWorkQueue.INCREMENTAL_WORK_QUEUE.submitTask(entry::setLoaded);
                continue;
            }

            if(entry.state == LevelCacheEntryState.LOADED || entry.state.isError) {
                entry.isRequested = false;
                this.accessList.remove(entry);
                this.accessList.addLast(entry);
                continue;
            }
        }

        while(this.accessList.size() > this.cacheSize) {
            var entry = this.accessList.removeFirst();
            entry.state = LevelCacheEntryState.DOWNLOADED;
            entry.level.tearDown();
            entry.level = null;
        }
    }

    public LevelCacheEntryState getLevelCacheEntryState(String levelName) {
        return this.levelMap.get(levelName).state;
    }

    public boolean isLevelReady(String levelName) {
        return this.getLevelCacheEntryState(levelName) == LevelCacheEntryState.LOADED;
    }

    public Level getLevel(String levelName) {
        return this.levelMap.get(levelName).level;
    }

}
