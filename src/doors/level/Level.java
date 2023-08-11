package doors.level;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import doors.graphics.texture.LevelTexture;
import doors.level.block.AbstractBlock;
import doors.level.block.BlockDataPacker;
import doors.level.block.CubeBlock;
import doors.level.terrain.TerrainData;
import doors.utility.FileIO;
import doors.work.WorkUnit;

public class Level {

    private static String CUBE_TYPE = "cube";
    private static String MANIFEST_FILE = "manifest.json";

    private Map<Integer, AbstractBlock> blockMap = new HashMap<>();
    private BlockDataPacker blockPacker = new BlockDataPacker(this);

    private LevelTexture levelTexture;
    public TerrainData terrainData;
    public WorkUnit setupWorkUnit = new WorkUnit();

    public Level(String levelDirectory) {
        var manifestPath = Paths.get(levelDirectory, MANIFEST_FILE).toString();
        var manifestStr = FileIO.loadText(manifestPath);
        var manifestObj = new JSONObject(manifestStr);

        var pathsConfig = manifestObj.getJSONObject("paths");

        var texturePath = pathsConfig.getString("texture");
        this.levelTexture = new LevelTexture(texturePath);
        this.setupWorkUnit.registerDependency(this.levelTexture.setupWorkUnit);

        var terrainPath = pathsConfig.getString("terrain");
        this.terrainData = new TerrainData(this.blockPacker, terrainPath);
        this.setupWorkUnit.registerDependency(this.terrainData.setupDependency);

        var blocksConfig = pathsConfig.getJSONArray("blocks");
        for(var ix = 0; ix < blocksConfig.length(); ix++) {
            var blockConfig = blocksConfig.getJSONObject(ix);
            var blockID = blockConfig.getInt("id");
            var blockType = blockConfig.getString("type");
            var blockPath = blockConfig.getString("path");
            if(blockType == CUBE_TYPE) {
                var cubeBlock = new CubeBlock(blockID, blockPath);
                this.setupWorkUnit.registerDependency(cubeBlock.setupWorkUnit);
                this.blockMap.put(blockID, cubeBlock); 
            } else {
                var msg = String.format("Unknown block type: %s", blockType);
                throw new RuntimeException(msg);
            }
        }

        this.setupWorkUnit.submit();
    }

    public AbstractBlock getBlock(int blockID) {
        return this.blockMap.get(blockID);
    }

    public Iterable<AbstractBlock> getBlocks() {
        return this.blockMap.values();
    }

    public void tearDown() {

    }

}
