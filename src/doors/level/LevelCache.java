package doors.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelCache {

    private static int LRU_CACHE_SIZE = 20;
    public static LevelCache LEVEL_CACHE = new LevelCache();

    private List<Level> disposeQueue = new ArrayList<>();
    private Map<String,Level> levelMap = new HashMap<>();

    public Level getLevel(String levelDirectory) {
        Level level;
        if(!this.levelMap.containsKey(levelDirectory)) {
            level = new Level(levelDirectory);
            this.levelMap.put(levelDirectory, level);
            level.setup();
        } else {
            level = this.levelMap.get(levelDirectory);
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
