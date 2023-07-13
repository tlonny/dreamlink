package doors.level;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import doors.level.terrain.Terrain;
import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.vector.Vector3fl;

public class Level {

    public String levelDirectory;
    public Terrain terrain;
    public Map<String, Door> doors = new HashMap<>();

    public Level(String levelDirectory) {
        var terrainPath = Paths.get(levelDirectory, "terrain").toString();
        this.terrain = new Terrain(terrainPath);
        this.levelDirectory = levelDirectory;
    }

    private void setupDoors() {
        var doorsPath = Paths.get(this.levelDirectory, "entities", "doors.json").toString();
        var doorsString = FileIO.loadText(doorsPath);
        var doorsConfig = new JSONObject(doorsString);
        for(var doorName : doorsConfig.keySet()) {
            var doorConfig = doorsConfig.getJSONObject(doorName);
            var position = doorConfig.getJSONArray("position");
            var target = doorConfig.getJSONObject("target");
            var door = new Door(
                target.getString("level"),
                target.getString("door"),
                new Vector3fl(
                    position.getFloat(0),
                    position.getFloat(1),
                    position.getFloat(2)
                ),
                CubeFace.CUBE_FACE_MAP.get(doorConfig.getString("orientation"))
            );
            this.doors.put(doorName, door);
        }
    }

    public void setup() {
        this.terrain.setup();
        this.setupDoors();
    }

    public void tearDown() {
    }

}
