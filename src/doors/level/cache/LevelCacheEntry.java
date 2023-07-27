package doors.level.cache;

import doors.level.Level;

public class LevelCacheEntry {

    public String levelDirectory;
    public LevelCacheEntryState state;

    public Level level = null;
    public boolean isRequested = false;

    public LevelCacheEntry(String levelDirectory, LevelCacheEntryState initialState) {
        this.levelDirectory = levelDirectory;
        this.state = initialState;
    }

    public void setLoaded() {
        this.state = LevelCacheEntryState.LOADED;
    }
    
}
