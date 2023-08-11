package doors.level.block;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import doors.graphics.mesh.MeshBuffer;
import doors.graphics.texture.sample.LevelTextureSample;
import doors.graphics.texture.sample.TextureSample;
import doors.level.terrain.TerrainData;
import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.Orientation;
import doors.utility.vector.Vector3fl;
import doors.utility.vector.Vector3in;
import doors.work.WorkUnit;

public class CubeBlock extends AbstractBlock {

    private boolean isSolid;
    private boolean isOpaque;
    private String configPath;
    private String name;

    private Vector3in adjacentPositionCursor = new Vector3in();
    private BlockData blockDataBuffer = new BlockData();
    public WorkUnit setupWorkUnit;

    private Map<CubeFace, TextureSample> textureSampleMap = new HashMap<>();

    public CubeBlock(int blockID, String configPath) {
        super(blockID);
        this.configPath = configPath;
        this.setupWorkUnit = new WorkUnit(this::loadBlockConfig);
        this.setupWorkUnit.submit();
    }

    private void loadBlockConfig() {
        var configString = FileIO.loadText(this.configPath);
        var configObj = new JSONObject(configString);

        this.name = configObj.getString("name");
        this.isSolid = configObj.getBoolean("isSolid");
        this.isOpaque = configObj.getBoolean("isOpaque");

        var textureSamples = configObj.getJSONObject("textureSamples");
        for(var cubeFace : CubeFace.CUBE_FACES) {
            this.textureSampleMap.put(
                cubeFace,
                new LevelTextureSample(textureSamples.getJSONArray(cubeFace.name))
            );
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isSolid() {
        return this.isSolid;
    }

    public boolean isOpaque() {
        return this.isOpaque;
    }

    @Override
    public TextureSample getIconTextureSample() {
        return this.textureSampleMap.get(CubeFace.FRONT);
    }

    @Override
    public void writeBlockToMeshBuffer(
        TerrainData terrainData,
        MeshBuffer meshBuffer, 
        Vector3in position, 
        Orientation orientation
    ) {
        for(var cubeFace : CubeFace.CUBE_FACES) {
            this.adjacentPositionCursor.set(position).add(cubeFace.normal);
            terrainData.getBlockData(this.adjacentPositionCursor, this.blockDataBuffer);

            if(this.blockDataBuffer.block instanceof CubeBlock cubeBlock) {
                if(cubeBlock.isOpaque()) {
                    continue;
                }
            }

            meshBuffer.pushQuad(
                this.textureSampleMap.get(cubeFace), 
                0,
                position,
                - orientation.cubeFace.rotation.y,
                Vector3in.ONE, 
                cubeFace, 
                Vector3fl.WHITE
            );
            
        }
    }
    
}
