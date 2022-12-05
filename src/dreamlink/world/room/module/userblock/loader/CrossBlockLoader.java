package dreamlink.world.room.module.userblock.loader;

import java.io.File;
import java.io.IOException;

import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONDecodeException;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.userblock.CrossBlock;
import dreamlink.world.room.parser.LightAmountParser;
import dreamlink.world.room.parser.LightIsAffectedParser;
import dreamlink.world.room.parser.TextureSampleParser;

public class CrossBlockLoader {

    private static final boolean defaultIsAffectedByLighting = true;
    private static final float defaultGain = 1f;
    private static final String soundGainKey = "sound.gain";
    private static final String textureSampleKey = "texture.sample";
    private static final String iconTextureSampleKey = "texture.sample.icon";

    private final LightIsAffectedParser isAffectedParser;
    private final TextureSampleParser iconTextureSampleParser;
    private final TextureSampleParser textureSampleParser;
    private final LightAmountParser[] lightAmountParsers = new LightAmountParser[BlockLight.getSize()];
    private final IRoomModuleProvider provider;

    public CrossBlockLoader(IRoomModuleProvider provider) {
        this.provider = provider;
        this.isAffectedParser = new LightIsAffectedParser(CrossBlockLoader.defaultIsAffectedByLighting);

        this.iconTextureSampleParser = new TextureSampleParser(
            this.provider, 
            CrossBlockLoader.iconTextureSampleKey,
            EntityTextureSample.placeholder
        );

        this.textureSampleParser = new TextureSampleParser(
            this.provider, 
            CrossBlockLoader.textureSampleKey
        );

        for(var ix = 0; ix < BlockLight.getSize(); ix += 1) {
            var blockLight = BlockLight.get(ix);
            this.lightAmountParsers[ix] = new LightAmountParser(blockLight.name);
        }
    }

    public CrossBlock loadCrossBlock(File file) throws RoomLoadException {
        try {
            var configData = FileFns.readStringFromFile(file);
            var blockConfig = new CheckedJSONObject(configData);
            var blockModule = this.provider.getUserBlockModule();
            var blockID = blockModule.acquireBlockID(file.getName());

            if(blockID == -1) {
                throw RoomLoadException.unableToAcquireUserBlockID();
            }

            var block = new CrossBlock(
                this.provider,
                blockID,
                file.getName(),
                this.isAffectedParser.getIsAffected(blockConfig),
                this.iconTextureSampleParser.getTextureSample(blockConfig),
                this.textureSampleParser.getTextureSample(blockConfig),
                blockConfig.optFloat(CrossBlockLoader.soundGainKey, CrossBlockLoader.defaultGain)
            );

            for(var ix = 0; ix < BlockLight.getSize(); ix += 1) {
                var blockLight = BlockLight.get(ix);
                var parser = this.lightAmountParsers[ix];
                var lightLevel = parser.getLightAmount(blockConfig);
                block.setLight(blockLight, lightLevel);
            }

            return block;
        } catch (IOException e) {
            throw RoomLoadException.invalidUserBlock(file.getName());
        } catch (JSONDecodeException e) {
            throw RoomLoadException.invalidUserBlock(file.getName());
        }
    }
    
}
