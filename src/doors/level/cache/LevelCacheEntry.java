package doors.level.cache;

import java.nio.file.Paths;

import doors.Config;
import doors.level.Level;
import doors.work.WorkUnit;

public class LevelCacheEntry {

    public WorkUnit setupWorkUnit;
    public Level level = null;

    public String levelName;

    public LevelCacheEntry(String levelName) {
        this.levelName = levelName;

        var downloadLevelWorkUnit = new WorkUnit(this::downloadLevel, true);
        downloadLevelWorkUnit.submit();

        this.setupWorkUnit = new WorkUnit(this::loadLevel);
        this.setupWorkUnit.registerDependency(downloadLevelWorkUnit);
        this.setupWorkUnit.submit();
    }

    private void downloadLevel() {
        // TODO: Download the file from the server
        // Currently just simulating a download...
        try {
            var downloadTime = 1000;
            Thread.sleep(downloadTime);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void loadLevel() {
        var levelDirectory = Paths.get(Config.CONFIG.getCachePath(), this.levelName).toString();
        this.level = new Level(levelDirectory);
    }

    public void tearDown() {
        var tearDownWorkUnit = new WorkUnit(this.level::tearDown);
        tearDownWorkUnit.registerDependency(this.setupWorkUnit);
        tearDownWorkUnit.submit();
    }
    
}
