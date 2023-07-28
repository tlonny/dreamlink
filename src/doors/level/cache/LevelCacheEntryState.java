package doors.level.cache;

public class LevelCacheEntryState {

    public static LevelCacheEntryState MISSING = new LevelCacheEntryState("missing", false, false);
    public static LevelCacheEntryState DOWNLOADING = new LevelCacheEntryState("downloading", false, false);
    public static LevelCacheEntryState DOWNLOADED = new LevelCacheEntryState("downloaded", false, false);
    public static LevelCacheEntryState LOADING = new LevelCacheEntryState("loading", false, false);
    public static LevelCacheEntryState LOADED = new LevelCacheEntryState("loaded", true, false);

    public static LevelCacheEntryState DOWNLOAD_FAILED = new LevelCacheEntryState("download_failed", true, true);
    public static LevelCacheEntryState LOAD_FAILED = new LevelCacheEntryState("load_failed", true, true);

    public String name;
    public boolean isError;
    public boolean isTerminal;

    public LevelCacheEntryState(String name, boolean isTerminal, boolean isError) {
        this.name = name;
        this.isTerminal = isTerminal;
        this.isError = isError;
    }
}
