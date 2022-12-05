package dreamlink.world.room.module.userblock.loader;

import java.io.File;
import java.io.IOException;

import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONDecodeException;
import dreamlink.utility.maths.CubeFace;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.userblock.TileBlock;
import dreamlink.world.room.parser.LightAmountParser;
import dreamlink.world.room.parser.LightIsAffectedParser;
import dreamlink.world.room.parser.MaterialIsSolidParser;
import dreamlink.world.room.parser.SoundParser;
import dreamlink.world.room.parser.TextureSampleParser;

public class TileBlockLoader {

    private static final boolean defaultIsAffectedByLighting = true;
    private static final boolean defaultIsSolid = true;
    private static final float defaultGain = 1f;
    private static final String soundGainKey = "sound.gain";
    private static final String textureSampleKeyTemplate = "texture.sample.%s";
    private static final String soundKey = "sound";
    private static final String iconTextureSampleSuffix = "icon";

    private final IRoomModuleProvider provider;
    private final MaterialIsSolidParser isSolidParser;
    private final LightIsAffectedParser isAffectedParser;
    private final TextureSampleParser iconTextureSampleParser;
    private final TextureSampleParser topTextureSampleParser;
    private final TextureSampleParser bottomTextureSampleParser;
    private final SoundParser soundParser;
    private final LightAmountParser[] lightAmountParsers = new LightAmountParser[BlockLight.getSize()];

    public TileBlockLoader(IRoomModuleProvider provider) {
        this.provider = provider;
        this.soundParser = new SoundParser(this.provider, TileBlockLoader.soundKey);
        this.isSolidParser = new MaterialIsSolidParser(TileBlockLoader.defaultIsSolid);
        this.isAffectedParser = new LightIsAffectedParser(TileBlockLoader.defaultIsAffectedByLighting);

        this.iconTextureSampleParser = new TextureSampleParser(
            this.provider, 
            String.format(TileBlockLoader.textureSampleKeyTemplate, TileBlockLoader.iconTextureSampleSuffix),
            EntityTextureSample.placeholder
        );

        this.topTextureSampleParser = new TextureSampleParser(
            this.provider, 
            String.format(TileBlockLoader.textureSampleKeyTemplate, CubeFace.top.name)
        );

        this.bottomTextureSampleParser = new TextureSampleParser(
            this.provider, 
            String.format(TileBlockLoader.textureSampleKeyTemplate, CubeFace.bottom.name)
        );

        for(var ix = 0; ix < BlockLight.getSize(); ix += 1) {
            var blockLight = BlockLight.get(ix);
            this.lightAmountParsers[ix] = new LightAmountParser(blockLight.name);
        }

    }

    public TileBlock loadTileBlock(File file) throws RoomLoadException {
        try {
            var data = FileFns.readStringFromFile(file);
            var blockConfig = new CheckedJSONObject(data);
            var blockModule = this.provider.getUserBlockModule();
            var blockID = blockModule.acquireBlockID(file.getName());

            if(blockID == -1) {
                throw RoomLoadException.unableToAcquireUserBlockID();
            }

            var block = new TileBlock(
                this.provider,
                blockID,
                file.getName(),
                this.isSolidParser.getIsSolid(blockConfig),
                this.isAffectedParser.getIsAffected(blockConfig),
                this.iconTextureSampleParser.getTextureSample(blockConfig),
                this.topTextureSampleParser.getTextureSample(blockConfig),
                this.bottomTextureSampleParser.getTextureSample(blockConfig),
                this.soundParser.getSound(blockConfig),
                blockConfig.optFloat(TileBlockLoader.soundGainKey, TileBlockLoader.defaultGain)
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
