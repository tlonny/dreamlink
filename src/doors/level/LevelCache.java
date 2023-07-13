package doors.level;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelCache {

    public static String LEVEL_DIRECTORY_ROOT = ".levels";
    private static int LRU_CACHE_SIZE = 20;
    public static LevelCache LEVEL_CACHE = new LevelCache();

    private List<Level> disposeQueue = new ArrayList<>();
    private Map<String,Level> levelMap = new HashMap<>();

    private String getLevelDirectory(String level) {
        var editorPath = Paths.get(LEVEL_DIRECTORY_ROOT, "editor", level).toString();
        if(new File(editorPath).isDirectory()) {
            return editorPath;
        }
        var explorePath = Paths.get(LEVEL_DIRECTORY_ROOT, "explore", level).toString();
        if(new File(explorePath).isDirectory()) {
            return explorePath;
        }
        return null;
    }

    public Level getLevel(String levelName) {

        Level level;

        if(!this.levelMap.containsKey(levelName)) {
            var levelDirectory = this.getLevelDirectory(levelName);
            if(levelDirectory == null) {
                throw new RuntimeException("Level not found: " + levelName);
            }
            level = new Level(levelDirectory);
            this.levelMap.put(levelName, level);
            level.setup();
        } else {
            level = this.levelMap.get(levelName);
            this.disposeQueue.remove(level);
        }

        this.disposeQueue.add(level);
        if(this.disposeQueue.size() > LRU_CACHE_SIZE) {
            var levelToDestroy = this.disposeQueue.remove(0);
            levelToDestroy.tearDown();
            this.levelMap.remove(levelToDestroy.levelDirectory);
        }

        return level;
    }

}
