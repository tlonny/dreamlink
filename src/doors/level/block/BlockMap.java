package doors.level.block;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import doors.graphics.texture.AbstractTexture;
import doors.graphics.texture.ImageTexture;
import doors.graphics.texture.channel.BlockTextureChannel;
import doors.queue.IncrementalWorkQueue;
import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.vector.Vector2in;

public class BlockMap {

    private static String BLOCK_CONFIG_NAME = "blocks.json";
    private static String BLOCK_ATLAS_NAME = "atlas.png";
    private static int RESERVED_BLOCK_RANGE = 0x1000;

    private String blockPath;
    private Map<Integer, Block> blockMap = new HashMap<>();
    private ImageTexture imageTexture;

    public BlockMap(String blockPath) {
        this.blockPath = blockPath;

        var blockConfigPath = Paths.get(this.blockPath, BLOCK_CONFIG_NAME).toString();
        var blockAtlasPath = Paths.get(this.blockPath, BLOCK_ATLAS_NAME).toString();

        var configString = FileIO.loadText(blockConfigPath);
        var config = new JSONObject(configString);

        var textureDimensionsArray = config.getJSONArray("textureDimensions");
        var textureDimensions = new Vector2in(
            textureDimensionsArray.getInt(0),
            textureDimensionsArray.getInt(1)
        );

        this.imageTexture = new ImageTexture(
            BlockTextureChannel.BLOCK_TEXTURE_CHANNEL,
            textureDimensions,
            blockAtlasPath
        );

        var blockArray = config.getJSONArray("blocks");
        for(var ix = 0; ix < blockArray.length(); ix += 1) {
            var blockConfig = blockArray.getJSONObject(ix);
            var blockID = blockConfig.getInt("id") + RESERVED_BLOCK_RANGE;
            var blockName = blockConfig.getString("name");
            var textureSampleObject = blockConfig.getJSONObject("textureSample");

            var block = new Block(blockID, blockName);
            for(var cubeFace : CubeFace.CUBE_FACES) {
                var textureSampleArray = textureSampleObject.getJSONArray(cubeFace.name);

                var textureSamplePosition = new Vector2in(
                    textureSampleArray.getInt(0),
                    textureSampleArray.getInt(1)
                );

                var textureSampleDimensions = new Vector2in(
                    textureSampleArray.getInt(2),
                    textureSampleArray.getInt(3)
                );

                var textureSample = this.imageTexture.createTextureSample(
                    textureSamplePosition, 
                    textureSampleDimensions
                );

                block.setTextureSample(cubeFace, textureSample);
            }

            this.blockMap.put(blockID, block);
        }

        IncrementalWorkQueue.INCREMENTAL_WORK_QUEUE.submitTask(() -> this.imageTexture.setup());
    }

    public AbstractTexture getTexture() {
        return this.imageTexture;
    }

    public Iterable<Block> getBlocks() {
        return this.blockMap.values();
    }

    public Block getBlock(int blockID) {
        return this.blockMap.get(blockID);
    }
    
}
