package doors.level;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import doors.level.block.BlockMap;
import doors.level.terrain.Terrain;
import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.vector.Vector3fl;

public class Level {

    private static String BLOCKS_PATH = "blocks";
    private static String TERRAIN_PATH = "terrain";

    private String levelDirectory;
    public Terrain terrain;
    public BlockMap blockMap;
    public Map<String, Door> doors = new HashMap<>();

    public Level(String levelDirectory) {
        var blocksPath = Paths.get(levelDirectory, BLOCKS_PATH).toString();
        this.levelDirectory = levelDirectory;
        this.blockMap = new BlockMap(blocksPath);

        var terrainPath = Paths.get(this.levelDirectory, TERRAIN_PATH).toString();
        this.terrain = new Terrain(terrainPath, this.blockMap);
        this.loadDoors();
    }

    private void loadDoors() {
        var doorsPath = Paths.get(this.levelDirectory, "doors.json").toString();
        var doorsString = FileIO.loadText(doorsPath);
        var doorsConfig = new JSONObject(doorsString);
        for(var doorName : doorsConfig.keySet()) {
            var doorConfig = doorsConfig.getJSONObject(doorName);
            var position = doorConfig.getJSONArray("position");
            var target = doorConfig.getJSONObject("target");
            var door = new Door(
                doorName,
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

    public void tearDown() {

    }

}
