package doors.level.cache;

public class LevelCacheEntryState {

    public static LevelCacheEntryState MISSING = new LevelCacheEntryState("missing", false);
    public static LevelCacheEntryState DOWNLOADING = new LevelCacheEntryState("downloading", false);
    public static LevelCacheEntryState DOWNLOADED = new LevelCacheEntryState("downloaded", false);
    public static LevelCacheEntryState LOADING = new LevelCacheEntryState("loading", false);
    public static LevelCacheEntryState LOADED = new LevelCacheEntryState("loaded", false);

    public static LevelCacheEntryState DOWNLOAD_FAILED = new LevelCacheEntryState("download_failed", true);
    public static LevelCacheEntryState LOAD_FAILED = new LevelCacheEntryState("load_failed", true);

    public String name;
    public boolean isError;

    public LevelCacheEntryState(String name, boolean isError) {
        this.name = name;
        this.isError = isError;
    }
}
